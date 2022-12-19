DROP TABLE IF EXISTS "order", "address", "city", "employee", "client_data", "personal_data", "film", "user", "role", "user_role";
DROP TYPE IF EXISTS "erole";
DROP SEQUENCE IF EXISTS "order_seq", "address_seq", "city_seq", "employee_seq", "client_data_seq", "personal_data_seq", "film_seq", "user_seq", "role_seq";


CREATE SEQUENCE order_seq;
CREATE TABLE "order" (
	id integer NOT NULL DEFAULT nextval('order_seq'), 
	status varchar(50) NOT NULL, 
	rental_date date NOT NULL, 
	return_date date, 
	client_id int4 NOT NULL, 
	film_id int4 NOT NULL, 
	employee_id int4 NOT NULL, 
	PRIMARY KEY (id));
	
CREATE SEQUENCE address_seq;
CREATE TABLE address (
	id integer NOT NULL DEFAULT nextval('address_seq'),
	street varchar(100) NOT NULL, 
	building_number varchar(5) NOT NULL, 
	flat_number varchar(5), 
	postal_code varchar(10) NOT NULL, 
	city_id int4 NOT NULL, 
	PRIMARY KEY (id));

CREATE SEQUENCE city_seq;
CREATE TABLE city (
	id integer NOT NULL DEFAULT nextval('city_seq'),
	name varchar(100) NOT NULL UNIQUE, 
	PRIMARY KEY (id));

CREATE SEQUENCE employee_seq;
CREATE TABLE employee (
	id integer NOT NULL DEFAULT nextval('employee_seq'),
	personal_data_id int4 NOT NULL, 
	PRIMARY KEY (id));

CREATE SEQUENCE client_data_seq;
CREATE TABLE client_data (
	id integer NOT NULL DEFAULT nextval('client_data_seq'),
	account_balance numeric(19, 2) NOT NULL, 
	address_id int4 NOT NULL, 
	personal_data_id int4 NOT NULL, 
	PRIMARY KEY (id));

CREATE SEQUENCE personal_data_seq;
CREATE TABLE personal_data (
	id integer NOT NULL DEFAULT nextval('personal_data_seq'),
	name varchar(50) NOT NULL, 
	surname varchar(50) NOT NULL, 
	phone_number numeric(9, 0) NOT NULL, 
	user_id int4 NOT NULL, 
	PRIMARY KEY (id));

CREATE SEQUENCE film_seq;
CREATE TABLE film (
	id integer NOT NULL DEFAULT nextval('film_seq'),
	name varchar(100) NOT NULL, 
	description varchar(255), 
	price numeric(5, 2) NOT NULL, 
	price_per_month numeric(5, 2) NOT NULL, 
	amount int4 NOT NULL, 
	PRIMARY KEY (id));

CREATE SEQUENCE user_seq;
CREATE TABLE "user" (
	id integer NOT NULL DEFAULT nextval('user_seq'),
	username varchar(255) NOT NULL UNIQUE, 
	password varchar(255) NOT NULL, 
	email varchar(255) NOT NULL UNIQUE, 
	PRIMARY KEY (id));

CREATE SEQUENCE role_seq;
CREATE TYPE erole AS ENUM ('ROLE_ADMIN', 'ROLE_EMPLOYEE', 'ROLE_USER');
CREATE TABLE role (
	id integer NOT NULL DEFAULT nextval('role_seq'),
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

ALTER SEQUENCE order_seq owned by "order".id;
ALTER SEQUENCE address_seq owned by address.id;
ALTER SEQUENCE city_seq owned by city.id;
ALTER SEQUENCE employee_seq owned by employee.id;
ALTER SEQUENCE client_data_seq owned by client_data.id;
ALTER SEQUENCE personal_data_seq owned by personal_data.id;
ALTER SEQUENCE film_seq owned by film.id;
ALTER SEQUENCE user_seq owned by "user".id;
ALTER SEQUENCE role_seq owned by role.id;

INSERT INTO "role"("name") VALUES ('ROLE_USER'), ('ROLE_EMPLOYEE'), ('ROLE_ADMIN');