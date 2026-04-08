CREATE TABLE order_items (
                             id UUID PRIMARY KEY,
                             order_id UUID NOT NULL,
                             product_variant_id UUID,
                             product_name VARCHAR(255) NOT NULL,
                             variant_name VARCHAR(255),
                             price NUMERIC(15,2) NOT NULL,
                             quantity INT NOT NULL,

                             CONSTRAINT fk_order
                                 FOREIGN KEY(order_id) REFERENCES orders(id)
                                     ON DELETE CASCADE,

                             CONSTRAINT fk_variant_order
                                 FOREIGN KEY(product_variant_id) REFERENCES product_variants(id)
)