CREATE TABLE Movie (
    id   INTEGER      NOT NULL,
    title VARCHAR(128) NOT NULL,
    actors VARCHAR(128) NOT NULL,
    release VARCHAR(10) NOT NULL,
    description VARCHAR(128) NOT NULL,
    rating INTEGER,
    director VARCHAR(128) NOT NULL,
    reviews VARCHAR(1024)
    );