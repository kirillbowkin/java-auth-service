-- ROLE_USER to {LIKE_POSTS, COMMENT_POSTS}
INSERT INTO roles_authorities (role_id, authority_id) VALUES ('613bb9f1-1607-4f8d-8cd2-f08217d60829', '6a56758f-b66b-45ac-b3ee-39755db821a6');
INSERT INTO roles_authorities (role_id, authority_id) VALUES ('613bb9f1-1607-4f8d-8cd2-f08217d60829', 'ecadf000-0580-4d4f-88e7-02803e6475e9');

-- ROLE_ADMIN to {GET_USERS, ADD_USERS, DELETE_USERS, EDIT_USERS}
INSERT INTO roles_authorities (role_id, authority_id) VALUES ('8ed0f60b-71e5-427f-a485-7bdec5a64768', '74948207-d4eb-444e-a8ac-43e6047afa57');
INSERT INTO roles_authorities (role_id, authority_id) VALUES ('8ed0f60b-71e5-427f-a485-7bdec5a64768', '805529b2-be07-4b6d-ad41-21011b1fdd82');
INSERT INTO roles_authorities (role_id, authority_id) VALUES ('8ed0f60b-71e5-427f-a485-7bdec5a64768', '8197efb0-971c-4876-8bfd-4af4dc1ae31a');
INSERT INTO roles_authorities (role_id, authority_id) VALUES ('8ed0f60b-71e5-427f-a485-7bdec5a64768', 'b4ddd2db-069a-4f76-9aec-b206a0b050d5');