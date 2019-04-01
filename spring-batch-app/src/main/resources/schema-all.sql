DROP TABLE terminatedUsers IF EXISTS;

CREATE TABLE terminatedUsers (
    user_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    user_name VARCHAR(20),
    termination_date VARCHAR(20)
);