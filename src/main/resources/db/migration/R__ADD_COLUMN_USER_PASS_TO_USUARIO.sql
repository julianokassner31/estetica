ALTER TABLE usuario
 ADD COLUMN IF NOT EXISTS username varchar(100) default null;

ALTER TABLE usuario
    ADD COLUMN IF NOT EXISTS pass text default null;

ALTER TABLE usuario
    ADD COLUMN IF NOT EXISTS enabled boolean default true;