INSERT INTO city (name) VALUES 
	('Wroclaw'),
	('Krakow'),
	('Praga');
INSERT INTO address (street, building_number, flat_number, postal_code, city_id) VALUES 
	('Polna', 3, 6, 55-12, 2),
	('Dluga', 5, NULL, 55-1, 2),
	('Obornicka', 3, 9, 55-12, 1);
INSERT INTO "user" (username, password, email) VALUES 
	('Maciek123', '$2a$10$GaexJc5MyIXfT1nwTFrMKezQtAj72e.pzdLPA5.RMiQVXvdEgtIq6', '123@gmail.com'),
	('KAm1l', '$2a$10$Wt0zHWRbLEXYVqa7vvq2neqs2oBygeCrf11nVqtIXOEt/Aj1uN23O', 'slu@gmail.com'),
	('Mocarz', '$2a$10$SgByirnhPfVof0YD25/47.iWxW3Vfw94GXE4u4967Td35Akgu2yYG', 'mlt@gmail.com');
INSERT INTO user_role VALUES
	(1, 1),
	(2, 1),
	(3, 2);
INSERT INTO personal_data (name, surname, phone_number, user_id) VALUES 
	('Maciek', 'Kowalski', 554123125, 1), 
	('Kamil', 'Sluchawski', 554121515, 2), 
	('Marta', 'Mlot', 534231250, 3);
INSERT INTO client_data (account_balance, address_id, personal_data_id) VALUES 
	(0, 1, 1),
	(100.4, 2, 2),
	(250.9, 3, 3);
INSERT INTO employee (personal_data_id) VALUES 
	(3);
INSERT INTO film (name, description, price, price_per_month, amount) VALUES 
	('Skarb Narodow', NULL, 45, 5, 4), 
	('Batman', NULL, 20, 5, 4),
	('Przygody Guliwiera', NULL, 70, 5, 4);
