DROP TABLE IF EXISTS Comment;

CREATE TABLE Comment (
                         id SERIAL PRIMARY KEY,
                         email VARCHAR(255),
                         name VARCHAR(255),
                         content VARCHAR(255),
                         post_id INT NOT NULL,
                         CONSTRAINT fk_post FOREIGN KEY(post_id) REFERENCES Post(id)
)