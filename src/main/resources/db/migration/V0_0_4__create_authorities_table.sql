CREATE TABLE authorities (
    id uuid DEFAULT uuid_generate_v4() NOT NULL PRIMARY KEY,
-- from 3 to 30, uppercase, '_'
    name VARCHAR(30) NOT NULL UNIQUE CHECK ( name ~* '^[A-Z_]{3,30}' )
);