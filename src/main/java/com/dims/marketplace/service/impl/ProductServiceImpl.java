package com.dims.marketplace.service.impl;

import com.dims.marketplace.dto.product.create.ProductRequest;
import com.dims.marketplace.dto.product.response.ProductDetailResponse;
import com.dims.marketplace.dto.product.response.ProductListResponse;
import com.dims.marketplace.dto.product.response.ProductResponse;
import com.dims.marketplace.dto.product.update.UpdateProductRequest;
import com.dims.marketplace.dto.product.variant.VariantResponse;
import com.dims.marketplace.entity.Product;
import com.dims.marketplace.entity.Variant;
import com.dims.marketplace.entity.User;
import com.dims.marketplace.exceptions.DuplicateException;
import com.dims.marketplace.exceptions.NotFoundException;
import com.dims.marketplace.repository.ProductRepository;
import com.dims.marketplace.repository.ProductVariantRepository;
import com.dims.marketplace.service.inter.ProductService;
import com.dims.marketplace.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductVariantRepository variantRepository;
    private final UserService userService;

    @Override
    public Product createProduct(ProductRequest request) {

        User user = userService.getUserById(request.getUserId());

        Set<String> skuSet = new HashSet<>();

        for (var v : request.getVariants()) {
            if (!skuSet.add(v.getSku())) {
                throw new DuplicateException("Duplicate SKU in request: " + v.getSku());
            }
        }

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setCreatedBy(user);

        List<Variant> variants = request.getVariants()
                .stream()
                .map(v -> {
                    Variant variant = new Variant();
                    variant.setSku(v.getSku());
                    variant.setPrice(v.getPrice());
                    variant.setSize(v.getSize());
                    variant.setStock(v.getStock());
                    variant.setProduct(product);
                    return variant;
                })
                .toList();

        product.setVariants(variants);

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(UUID id, UpdateProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + id));

        // PATCH logic
        if (request.getName() != null && !request.getName().isBlank()) {
            product.setName(request.getName());
        }

        if (request.getDescription() != null && !request.getDescription().isBlank()) {
            product.setDescription(request.getDescription());
        }

        // 🔥 Replace variant (simple version dulu)
        if (request.getVariants() != null) {

            List<Variant> variants = request.getVariants()
                    .stream()
                    .map(v -> {
                        Variant variant = new Variant();
                        variant.setSku(v.getSku());
                        variant.setPrice(v.getPrice());
                        variant.setSize(v.getSize());
                        variant.setStock(v.getStock());
                        variant.setProduct(product);
                        return variant;
                    })
                    .toList();

            product.setVariants(variants);
        }

        return productRepository.save(product);
    }

    @Override
    public Page<ProductListResponse> getAllProducts(Pageable pageable) {

        return productRepository.findAll(pageable)
                .map(product -> {

                    BigDecimal minPrice = product.getVariants().stream()
                            .map(Variant::getPrice)
                            .min(BigDecimal::compareTo)
                            .orElse(BigDecimal.ZERO);

                    return new ProductListResponse(
                            product.getId(),
                            product.getName(),
                            product.getDescription(),
                            minPrice,
                            product.getCreatedAt()
                    );
                });
    }

    @Override
    public ProductDetailResponse getProductById(UUID id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + id));

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

        return new ProductDetailResponse(
                product.getId(),
                product.getCreatedBy().getId(),
                product.getName(),
                product.getDescription(),
                product.getCreatedAt(),
                variants
        );
    }

    @Override
    public void deleteProduct(UUID productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + productId));

        productRepository.delete(product);
    }
}