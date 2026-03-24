CREATE TABLE carts (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_cart_user
                   FOREIGN KEY (user_id)
                   REFERENCES users(id)
                   ON DELETE CASCADE
)