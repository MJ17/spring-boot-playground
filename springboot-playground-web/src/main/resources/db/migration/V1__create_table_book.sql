CREATE SCHEMA IF NOT EXISTS "springboot-playground";

CREATE USER IF NOT EXISTS tester PASSWORD 'tester';

ALTER USER tester ADMIN true;

CREATE TABLE book
(
    id VARCHAR(32) PRIMARY KEY,
    title VARCHAR(200),
    author VARCHAR(100),
    publisher VARCHAR(100)
)
