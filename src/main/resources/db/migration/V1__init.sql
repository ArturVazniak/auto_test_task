CREATE TABLE roles
(
    id                  SERIAL               PRIMARY KEY,
    role_name           VARCHAR(20)             NOT NULL
);

CREATE TABLE users_auto
(
    id                  BIGSERIAL               PRIMARY KEY,
    name                VARCHAR(50)             NOT NULL,
    password            VARCHAR(50)             NOT NULL,
    role                INT                     NOT NULL,
    email               VARCHAR(50)             NOT NULL,

        FOREIGN KEY(role) REFERENCES roles(id)
);

CREATE TABLE advertisements
(
    id                  BIGSERIAL               PRIMARY KEY,
    name_advertisement  VARCHAR(100)            NOT NULL,
    text                TEXT,
    author_id           BIGINT,
    car_model           VARCHAR(100)            NOT NULL,
    year                INT                     NOT NULL,
    price               DOUBLE PRECISION,
    deleted             BOOLEAN                 NOT NULL          DEFAULT FALSE,
    date                DATE                    DEFAULT           CURRENT_DATE,

        FOREIGN KEY(author_id) REFERENCES users_auto(id)
);