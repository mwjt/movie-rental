DROP TABLE IF EXISTS "order", "address", "city", "employee", "client_data", "personal_data", "film", "user", "role", "user_role";
DROP TYPE IF EXISTS "erole";

CREATE TABLE "order" (
	id SERIAL NOT NULL, 
	status varchar(50) NOT NULL, 
	rental_date date NOT NULL, 
	return_date date NOT NULL, 
	client_id int4 NOT NULL, 
	film_id int4 NOT NULL, 
	employee_id int4 NOT NULL, 
	PRIMARY KEY (id));
	
CREATE TABLE address (
	id SERIAL NOT NULL, 
	street varchar(100) NOT NULL, 
	building_number varchar(5) NOT NULL, 
	flat_number varchar(5), 
	postal_code varchar(10) NOT NULL, 
	city_id int4 NOT NULL, 
	PRIMARY KEY (id));
	
CREATE TABLE city (
	id SERIAL NOT NULL, 
	name varchar(100) NOT NULL UNIQUE, 
	PRIMARY KEY (id));

CREATE TABLE employee (
	id SERIAL NOT NULL, 
	personal_data_id int4 NOT NULL, 
	PRIMARY KEY (id));

CREATE TABLE client_data (
	id SERIAL NOT NULL, 
	account_balance numeric(19, 2) NOT NULL, 
	address_id int4 NOT NULL, 
	personal_data_id int4 NOT NULL, 
	PRIMARY KEY (id));

CREATE TABLE personal_data (
	id SERIAL NOT NULL, 
	name varchar(50) NOT NULL, 
	surname varchar(50) NOT NULL, 
	phone_number numeric(9, 0) NOT NULL, 
	user_id int4 NOT NULL, 
	PRIMARY KEY (id));

CREATE TABLE film (
	id SERIAL NOT NULL, 
	name varchar(100) NOT NULL, 
	description varchar(255), 
	price numeric(5, 2) NOT NULL, 
	price_per_month numeric(5, 2) NOT NULL, 
	amount int4 NOT NULL, 
	PRIMARY KEY (id));

CREATE TABLE "user" (
	id SERIAL NOT NULL, 
	username varchar(255) NOT NULL UNIQUE, 
	password varchar(255) NOT NULL, 
	email varchar(255) NOT NULL UNIQUE, 
	PRIMARY KEY (id));

CREATE TYPE erole AS ENUM ('ROLE_ADMIN', 'ROLE_EMPLOYEE', 'ROLE_USER');
CREATE TABLE role (
	id SERIAL NOT NULL, 
	name erole NOT NULL UNIQUE, 
	PRIMARY KEY (id));

CREATE TABLE user_role (
	user_id int4 NOT NULL, 
	role_id int4 NOT NULL);

ALTER TABLE address ADD CONSTRAINT FKaddress195262 FOREIGN KEY (city_id) REFERENCES city (id);
ALTER TABLE client_data ADD CONSTRAINT FKclient_dat195398 FOREIGN KEY (address_id) REFERENCES address (id);
ALTER TABLE client_data ADD CONSTRAINT FKclient_dat534997 FOREIGN KEY (personal_data_id) REFERENCES personal_data (id);
ALTER TABLE "order" ADD CONSTRAINT FKorder253086 FOREIGN KEY (client_id) REFERENCES client_data (id);
ALTER TABLE employee ADD CONSTRAINT FKemployee794377 FOREIGN KEY (personal_data_id) REFERENCES personal_data (id);
ALTER TABLE "order" ADD CONSTRAINT FKorder610054 FOREIGN KEY (film_id) REFERENCES film (id);
ALTER TABLE "order" ADD CONSTRAINT FKorder401022 FOREIGN KEY (employee_id) REFERENCES employee (id);
ALTER TABLE personal_data ADD CONSTRAINT FKpersonal_d211891 FOREIGN KEY (user_id) REFERENCES "user" (id);
ALTER TABLE user_role ADD CONSTRAINT FKuser_role943458 FOREIGN KEY (user_id) REFERENCES "user" (id);
ALTER TABLE user_role ADD CONSTRAINT FKuser_role868982 FOREIGN KEY (role_id) REFERENCES role (id);

INSERT INTO "role"("name") VALUES ('ROLE_USER'), ('ROLE_EMPLOYEE'), ('ROLE_ADMIN');