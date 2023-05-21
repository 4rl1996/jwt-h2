INSERT INTO service_user (id, username, password) VALUES (1, 'admin@mail.ru', '$2a$10$cwwdkf11UjR7dNPQNys4AejcnUQTn1bwvB/g9gGcdplnppD4SLfrC'); --55666 pass
INSERT INTO service_user (id, username, password) VALUES (2, 'hr@mail.ru', '$2a$09$nDByt20QhlvcClEm.cl/fesApWH0x5VkKfBQZjyahgfcMTL1AxCxe'); --77888 pass
INSERT INTO service_user (id, username, password) VALUES (3, 'manager@mail.ru', '$2a$09$nDByt20QhlvcClEm.cl/fesApWH0x5VkKfBQZjyahgfcMTL1AxCxe'); --77888 pass

INSERT INTO roles(user_id, role_name) VALUES (1, 'ADMIN');
INSERT INTO roles(user_id, role_name) VALUES (2, 'HR');
INSERT INTO roles(user_id, role_name) VALUES (3, 'MANAGER');

CREATE UNIQUE INDEX unique_username on service_user(username);

-- CREATE SEQUENCE user_sequence AS INTEGER START WITH 4;