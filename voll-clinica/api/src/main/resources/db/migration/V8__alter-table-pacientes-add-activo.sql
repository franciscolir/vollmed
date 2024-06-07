ALTER TABLE pacientes ADD COLUMN activo tinyint;
update pacientes set activo = 1;
ALTER TABLE pacientes modify activo tinyint not null;