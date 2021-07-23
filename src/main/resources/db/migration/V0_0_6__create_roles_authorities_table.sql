CREATE TABLE roles_authorities (
    role_id uuid NOT NULL REFERENCES roles(id) ON UPDATE CASCADE ON DELETE CASCADE ,
    authority_id uuid NOT NULL REFERENCES authorities(id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT roles_authorities_key PRIMARY KEY (role_id, authority_id)
);