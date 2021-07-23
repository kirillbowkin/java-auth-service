CREATE TABLE users_roles (
  user_id uuid NOT NULL REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE ,
  role_id uuid NOT NULL REFERENCES roles(id) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT users_roles_key PRIMARY KEY (user_id, role_id)
);