CREATE TABLE "USER" ( id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), email VARCHAR(255), created_date timestamp(6), last_modified_date timestamp(6));

INSERT INTO "USER" (name, email) VALUES ('Alice', 'alice@example.com');
INSERT INTO "USER" (name, email) VALUES ('Bob', 'bob@example.com');
INSERT INTO "USER" (name, email) VALUES ('Charlie', 'charlie@example.com');
