CREATE TABLE users (
    id UUID PRIMARY KEY,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    quantity BIGINT NOT NULL,
    quantity_used_per_day BIGINT NOT NULL,
    unit VARCHAR(50) NOT NULL,
    ends_in TIMESTAMP,
    name VARCHAR(255) NOT NULL,
    img_path VARCHAR(500),
    opened_on TIMESTAMP,
    notification_date TIMESTAMP,
    notification_window_in_days INTEGER NOT NULL,
    user_id UUID CONSTRAINT fk_product_users REFERENCES users(id) ON DELETE CASCADE
);


