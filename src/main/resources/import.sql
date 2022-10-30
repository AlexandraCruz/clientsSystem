/* Populate tables*/
truncate table clientes ;
commit;
INSERT INTO clientes (id, nombre, apellido, email, create_at) VALUES (3, 'Andrés', 'Guzmán', 'correo@gmail.com', '2014-08-20');
INSERT INTO clientes (id, nombre, apellido, email, create_at) VALUES (4, 'Fulano', 'De Tal', 'correo2@gmail.com', '2014-08-20');
