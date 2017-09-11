CREATE TABLE IF NOT EXISTS users(id bigserial,
 name varchar(256) not null,
 email varchar(256) not null unique,
 created_at timestamp not null,
 updated_at timestamp not null
);
