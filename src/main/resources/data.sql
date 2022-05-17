INSERT INTO roles (name) VALUES ('USER');
INSERT INTO roles (name) VALUES ('ADMIN');

INSERT INTO users (username, password, enabled) VALUES ('test_user', '$2a$12$z25bgtwjTM9jPSzouy0RBefetahe.dRoaQoVsH27pv5yxVAfS8nym', '1'); -- password is user
INSERT INTO users (username, password, enabled) VALUES ('test_admin', '$2a$12$qYKL//SLM863Ok1h93hMGO4XPuv6jTvTbZKGYGc3qLob3j/6bunnK', '1'); -- password is admin

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);