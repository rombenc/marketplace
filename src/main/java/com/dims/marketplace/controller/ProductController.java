package com.dims.marketplace.controller;

import com.dims.marketplace.dto.ApiResponse;
import com.dims.marketplace.dto.product.create.ProductRequest;
import com.dims.marketplace.dto.product.response.ProductDetailResponse;
import com.dims.marketplace.dto.product.response.ProductListResponse;
import com.dims.marketplace.dto.product.response.ProductResponse;
import com.dims.marketplace.dto.product.update.UpdateProductRequest;
import com.dims.marketplace.dto.product.variant.VariantResponse;
import com.dims.marketplace.entity.Product;
import com.dims.marketplace.entity.User;
import com.dims.marketplace.entity.Variant;
import com.dims.marketplace.service.inter.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> create(@RequestBody ProductRequest request) {

        Product product = productService.createProduct(request);

        List<VariantResponse> variants = product.getVariants()
                .stream()
                .map(v -> new VariantResponse(
                        v.getId(),
                        v.getSku(),
                        v.getSize(),
                        v.getPrice(),
                        v.getStock(),
                        v.getCreatedAt()
                ))
                .toList();

        BigDecimal minPrice = variants.stream()
                .map(VariantResponse::getPrice)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        ProductResponse productResponse = new ProductResponse(
                product.getId(),
                product.getCreatedBy().getId(),
                product.getName(),
                product.getDescription(),
                minPrice,
                product.getCreatedAt(),
                variants
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "Product created successfully", productResponse));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ProductListResponse>>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<ProductListResponse> data = productService.getAllProducts(pageable);

        return ResponseEntity.ok(new ApiResponse<>(200, "Success", data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDetailResponse>> getById(@PathVariable UUID id) {

        ProductDetailResponse data = productService.getProductById(id);

        return ResponseEntity.ok(new ApiResponse<>(200, "Success", data));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> update(
            @PathVariable UUID id,
            @RequestBody UpdateProductRequest request) {

        Product product = productService.updateProduct(id, request);

        List<VariantResponse> variants = product.getVariants()
                .stream()
                .map(v -> new VariantResponse(
                        v.getId(),
                        v.getSku(),
                        v.getSize(),
                        v.getPrice(),
                        v.getStock(),
                        v.getCreatedAt()
                ))
                .toList();

        BigDecimal minPrice = variants.stream()
                .map(VariantResponse::getPrice)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        ProductResponse productResponse = new ProductResponse(
                product.getId(),
                product.getCreatedBy().getId(),
                product.getName(),
                product.getDescription(),
                minPrice,
                product.getCreatedAt(),
                variants
        );

        return ResponseEntity.ok(new ApiResponse<>(200, "Product updated successfully", productResponse));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {

        productService.deleteProduct(id);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Product deleted successfully", null)
        );
    }
}