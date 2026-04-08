CREATE TABLE products (
                          id UUID PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,

    -- relation to user (admin/creator)
                          created_by UUID,
                          CONSTRAINT fk_product_created_by FOREIGN KEY (created_by) REFERENCES users(id),

    -- timestamps
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);