

INSERT INTO genero (nombre) VALUES
('Accion'),
('Comedia'),
('Drama'),
('Aventura'),
('Ciencia_Ficcion'),
('Terror'),
('Fantasia'),
('Thriller'),
('Romance'),
('Misterio'),
('Crimen'),
('Suspense'),
('Distopia'),
('Novela'),
('Fabula'),
('Epica'),
('Poema');


INSERT INTO libro (id,titulo, anho, genero_id, autor, idioma, sinopsis,fecha_Alta,archivo) VALUES
(1001, 'El Quijote', 1605, 14, 'Miguel de Cervantes', 0, 'Una historia sobre un hidalgo.', CURRENT_TIMESTAMP, '1El Quijote.jpg'),
(1002, 'Cien Anos de Soledad', 1967, 14,'Gabriel Garcia Marquez', 0, 'La historia de la familia Buendia.', CURRENT_TIMESTAMP, '2Cien Anos de Soledad.jpg'),
(1003, '1984', 1949, 13, 'George Orwell', 2, 'Un futuro distópico y opresivo.', CURRENT_TIMESTAMP, '31984.jpg'),
(1004, 'Matar a un Ruisenor', 1960, 14, 'Harper Lee', 2, 'Un caso de racismo en Estados Unidos.', CURRENT_TIMESTAMP, '4Matar a un Ruisenor.jpg'),
(1005, 'Orgullo y Prejuicio', 1813, 14, 'Jane Austen', 2, 'La historia de las hermanas Bennet.', CURRENT_TIMESTAMP, '5Orgullo y Prejuicio.jpg'),
(1006, 'El Principito', 1943,15, 'Antoine de Saint-Exupery', 1, 'La travesia de un joven principe.', CURRENT_TIMESTAMP, ''),
(1007, 'Ulises', 1922, 14, 'James Joyce', 2, 'Un dia en la vida de Leopold Bloom.', CURRENT_TIMESTAMP, ''),
(1008, 'Crimen y Castigo', 1866, 14, 'Fiódor Dostoyevski', 3, 'Un hombre lidia con las consecuencias de un asesinato.', CURRENT_TIMESTAMP, ''),
(1009, 'Don Juan Tenorio', 1844, 3, 'Jose Zorrilla', 0, 'Las aventuras de Don Juan.', CURRENT_TIMESTAMP, ''),
(10010, 'La Odisea', 1800, 15, 'Homero', 6, 'El viaje de Odiseo de regreso a Itaca.', CURRENT_TIMESTAMP, ''),
(10011, 'El Nombre de la Rosa', 1980, 14, 'Umberto Eco', 4, 'Un misterio en una abadia medieval.', CURRENT_TIMESTAMP, ''),
(10012, 'El Gran Gatsby', 1925, 14, 'F. Scott Fitzgerald', 2, 'La vida y misterios de Jay Gatsby.', CURRENT_TIMESTAMP, ''),
(10013, 'La Divina Comedia', 1320, 17, 'Dante Alighieri', 4, 'El viaje a traves del Infierno, Purgatorio y Paraiso.', CURRENT_TIMESTAMP, ''),
(10014, 'Fausto', 1808, 3, 'Johann Wolfgang von Goethe', 6, 'El pacto de Fausto con el diablo.', CURRENT_TIMESTAMP, ''),
(10015, 'El Guardian entre el Centeno', 1951, 14, 'J.D. Salinger', 2, 'Las experiencias del joven Holden Caulfield.', CURRENT_TIMESTAMP, '');


INSERT INTO Usuario (id,nombre, password,rol) VALUES
(1001,'Ale','$2a$10$LthiZoaf2.lqURi7FHO8wOuisnvBPuaVqWaR5bmgrEKqcYdJhS1sy','Administrador'),
(1002,'David','$2a$10$LthiZoaf2.lqURi7FHO8wOuisnvBPuaVqWaR5bmgrEKqcYdJhS1sy','Usuario'),
(1003,'Miguel','$2a$10$LthiZoaf2.lqURi7FHO8wOuisnvBPuaVqWaR5bmgrEKqcYdJhS1sy','Manager');



INSERT INTO valoracion (puntos, observacion, libro_id,Usuario_id) VALUES
(5,'valoracion 1', 1001,1001),
(3,'valoracion 2', 1001,1002),
(5,'valoracion 3', 1001,1003),
(4,'valoracion 1', 1002,1002),
(5,'valoracion 2', 1002,1001),
(4,'valoracion 3', 1002,1003);