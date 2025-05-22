-- Справочник товаров
CREATE TABLE IF NOT EXISTS products (
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    name        TEXT    NOT NULL,
    description TEXT,
    price       REAL    NOT NULL
);

-- Остатки на складе
CREATE TABLE IF NOT EXISTS inventory (
    product_id   INTEGER PRIMARY KEY,
    quantity     INTEGER NOT NULL,
    last_updated TEXT    NOT NULL,
    FOREIGN KEY(product_id) REFERENCES products(id)
);

-- Учёт продаж
CREATE TABLE IF NOT EXISTS sales (
    id         INTEGER PRIMARY KEY AUTOINCREMENT,
    product_id INTEGER NOT NULL,
    quantity   INTEGER NOT NULL,
    sale_date  TEXT    NOT NULL,
    FOREIGN KEY(product_id) REFERENCES products(id)
);
