CREATE TABLE roles
(
    id                  SERIAL                  PRIMARY KEY,
    role_name           VARCHAR(20)             NOT NULL
);

CREATE TABLE users_auto
(
    id                  BIGSERIAL               PRIMARY KEY,
    name                VARCHAR(50)             NOT NULL                 UNIQUE,
    password            VARCHAR(255)            NOT NULL,
    email               VARCHAR(50)             NOT NULL                 UNIQUE,
    enabled             BOOLEAN                 NOT NULL                 DEFAULT FALSE


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
    deleted             BOOLEAN                 NOT NULL                 DEFAULT FALSE,
    date                DATE                    DEFAULT                  CURRENT_DATE,

    FOREIGN KEY(author_id) REFERENCES users_auto(id) ON DELETE CASCADE
);

CREATE TABLE users_roles
(
    user_id             BIGINT                  NOT NULL,
    role_id             INT                     NOT NULL,

    PRIMARY KEY(user_id, role_id),

    FOREIGN KEY(user_id) REFERENCES users_auto(id) ON DELETE CASCADE,
    FOREIGN KEY(role_id) REFERENCES roles(id) ON DELETE CASCADE
);