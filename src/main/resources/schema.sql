CREATE TABLE users (
    username VARCHAR(100) NOT NULL PRIMARY KEY,
    password VARCHAR(200) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE authorities (
    username VARCHAR(100) NOT NULL,
    authority VARCHAR(100) NOT NULL,
    CONSTRAINT fk_auth_users FOREIGN KEY (username) REFERENCES users(username)
);

CREATE UNIQUE INDEX ix_auth_username
    ON authorities (username, authority);
