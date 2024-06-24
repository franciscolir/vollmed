ALTER TABLE consultas ADD COLUMN activo tinyint;
update consultas set activo = 1;
ALTER TABLE consultas modify activo tinyint not null;