INSERT INTO service_user (id, username, password) VALUES (1, 'test1@mail.ru', '55666');
INSERT INTO service_user (id, username, password) VALUES (2, 'test2@mail.ru', '77888');

INSERT INTO roles(user_id, role_name) VALUES (1, 'ADMIN');
INSERT INTO roles(user_id, role_name) VALUES (1, 'USER');
INSERT INTO roles(user_id, role_name) VALUES (2, 'USER');