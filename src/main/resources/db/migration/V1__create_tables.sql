CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    quantity BIGINT NOT NULL,
    quantity_used_per_day BIGINT NOT NULL,
    unit VARCHAR(50) NOT NULL,
    ends_in TIMESTAMP,
    name VARCHAR(255) NOT NULL,
    img_path VARCHAR(500),
    opened_on TIMESTAMP,
    notification_window_in_days INTEGER NOT NULL
)


