DROP TABLE IF EXISTS user_roles;

CREATE TABLE IF NOT EXISTS user_roles
(
    user_id SERIAL PRIMARY KEY REFERENCES users (id),
    role_id int4 REFERENCES roles (id)

);