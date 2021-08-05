INSERT INTO users (id, username, password, email)
VALUES ('624a174b-0a9e-49be-9116-2b0b2aee4014', 'admin', '$2a$12$Neo1AdkqDWa2fTeLsAn7T.t9S4/70zDE7Ab2sCPVJ9wy18qNdESpW', 'admin@gmail.com');

-- ROLE_USER
INSERT INTO users_roles(user_id, role_id)
VALUES ('624a174b-0a9e-49be-9116-2b0b2aee4014', '613bb9f1-1607-4f8d-8cd2-f08217d60829');

-- ROLE_ADMIN
INSERT INTO users_roles(user_id, role_id)
VALUES ('624a174b-0a9e-49be-9116-2b0b2aee4014', '8ed0f60b-71e5-427f-a485-7bdec5a64768');