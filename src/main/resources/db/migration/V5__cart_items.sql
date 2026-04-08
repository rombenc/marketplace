CREATE TABLE cart_items (
    id UUID PRIMARY KEY,
    cart_id UUID NOT NULL,
    product_Variant_id UUID NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),

    -- timestamps
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_cart_item_cart
                        FOREIGN KEY (cart_id)
                        REFERENCES carts(id)
                        ON DELETE CASCADE,
    CONSTRAINT fk_cart_item_variant
                        FOREIGN KEY (product_Variant_id)
                        REFERENCES product_variants(id),
    CONSTRAINT unique_cart_variant
                        UNIQUE (cart_id, product_Variant_id)
)