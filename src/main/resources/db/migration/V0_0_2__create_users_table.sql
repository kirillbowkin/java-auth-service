CREATE TABLE users (
  id uuid DEFAULT uuid_generate_v4() NOT NULL PRIMARY KEY,
-- from 3 to 16, lowercase, numbers, '-', '_' only
  username VARCHAR(16) NOT NULL UNIQUE CHECK ( username ~* '^[a-z0-9_-]{3,16}$' ),
-- from 8 to 16, at least one letter, at least one number, available special characters: '@$!%*#?&'
--   password VARCHAR(16) NOT NULL CHECK ( password ~* '^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d@$!%*#?&]{8,16}$' ),
  password VARCHAR(255) NOT NULL,
  email VARCHAR(120) NOT NULL UNIQUE CHECK ( email ~* '^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$' )
);