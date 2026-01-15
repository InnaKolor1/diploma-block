  --юзеры--
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(32) NOT NULL UNIQUE,
    first_name VARCHAR(16) NOT NULL,
    last_name VARCHAR(16) NOT NULL,
    phone VARCHAR(18),
    role VARCHAR(10) NOT NULL,
    image VARCHAR(255),
    password VARCHAR(255) NOT NULL
);

 --объявления--
CREATE TABLE IF NOT EXISTS ads(
    id SERIAL PRIMARY KEY,
    title VARCHAR(32) NOT NULL,
    price INTEGER NOT NULL,
    description VARCHAR(64),
    image VARCHAR(255),
    author_id INTEGER NOT NULL,
    FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE CASCADE
);

 --комменты--
CREATE TABLE IF NOT EXISTS comments (
    id SERIAL PRIMARY KEY,
    text VARCHAR(64) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    author_id INTEGER NOT NULL,
    ad_id INTEGER NOT NULL,
    FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (ad_id) REFERENCES ads(id) ON DELETE CASCADE
);

 --индексы--
CREATE INDEX IF NOT EXISTS idx_ads_author ON ads(author_id);
CREATE INDEX IF NOT EXISTS idx_comments_ad ON comments(ad_id);
CREATE INDEX IF NOT EXISTS idx_comments_author ON comments(author_id);