CREATE TABLE product_variants (
    id UUID PRIMARY KEY,
    product_id UUID NOT NULL,
    sku VARCHAR(100) UNIQUE,
    price NUMERIC(12,2) NOT NULL,
    -- timestamps
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_variant_product
                              FOREIGN KEY (product_id)
                              REFERENCES products(id)
                              ON DELETE CASCADE
);