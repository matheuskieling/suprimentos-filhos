CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE product (
                         id SERIAL PRIMARY KEY,
                         quantity INT NOT NULL,
                         left_quantity INT NOT NULL,
                         quantity_used_per_day INT NOT NULL,
                         unit VARCHAR(50) NOT NULL,
                         ends_in TIMESTAMP,
                         name VARCHAR(255) NOT NULL,
                         img_path VARCHAR(500),
                         category VARCHAR(50),
                         notification_date TIMESTAMP,
                         notification_window_in_days INTEGER NOT NULL,
                         user_id UUID CONSTRAINT fk_product_users REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE unit_of_product (
                                 id SERIAL PRIMARY KEY,
                                 buy_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 product_id INT NOT NULL CONSTRAINT fk_unit_product REFERENCES product(id) ON DELETE CASCADE
);


