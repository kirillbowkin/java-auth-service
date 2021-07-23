CREATE TABLE roles (
  id uuid DEFAULT uuid_generate_v4() NOT NULL PRIMARY KEY,
-- starts with ROLE_, role name (word after ROLE_) is lowercase from 3 to 30 '_' required
  name VARCHAR(35) NOT NULL UNIQUE CHECK ( name ~* 'ROLE_[A-Z_]{3,30}' )
);