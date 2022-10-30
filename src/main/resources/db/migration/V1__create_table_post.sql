DROP TABLE IF EXISTS Post;

CREATE TABLE IF NOT EXISTS Post
(
    id     SERIAL PRIMARY KEY,
    title       VARCHAR(50) UNIQUE NOT NULL,
    description VARCHAR(255)       NOT NULL,
    content     VARCHAR(255)       NOT NULL
);