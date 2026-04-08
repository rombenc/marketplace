CREATE TABLE carts (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL UNIQUE,
    -- timestamps
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_cart_user
                   FOREIGN KEY (user_id)
                   REFERENCES users(id)
                   ON DELETE CASCADE
)