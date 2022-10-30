DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(50)        NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    email    VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(60)        NOT NULL
);