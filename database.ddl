DROP TABLE IF EXISTS "Order", "Address", "City", "Employee", "ClientData", "PersonalData", "Films";

CREATE TABLE "Order" (
	OrderID SERIAL NOT NULL, 
	Status varchar(50) NOT NULL, 
	RentalDate date NOT NULL, 
	ReturnDate date NOT NULL, 
	ClientID int4 NOT NULL, 
	FilmID int4, 
	EmployeeID int4, 
	PRIMARY KEY (OrderID));
CREATE TABLE "Address" (
	AddressID SERIAL NOT NULL, 
	Street varchar(100) NOT NULL, 
	BuildingNumber varchar(5) NOT NULL, 
	FlatNumber varchar(5), 
	PostalCode varchar(10) NOT NULL,  
	CityID int4 NOT NULL, 
	PRIMARY KEY (AddressID));
CREATE TABLE "City" (
	CityID SERIAL NOT NULL, 
	City varchar(100) NOT NULL UNIQUE, 
	PRIMARY KEY (CityID));
CREATE TABLE "Employee" (
	EmployeeID SERIAL NOT NULL, 
	PersonalDataID int4, 
	PRIMARY KEY (EmployeeID));
CREATE TABLE "ClientData" (
	ClientID SERIAL NOT NULL, 
	AccountBalance numeric(19, 2) NOT NULL, 
	AddressID int4 NOT NULL, 
	PersonalDataID int4, 
	PRIMARY KEY (ClientID));
CREATE TABLE "PersonalData" (
	PersonalDataID SERIAL NOT NULL, 
	Name varchar(50) NOT NULL, 
	Surname varchar(50) NOT NULL, 
	Email varchar(100) NOT NULL UNIQUE, 
	PhoneNumber numeric(9, 0) NOT NULL, 
	Login varchar(100) NOT NULL UNIQUE, 
	Password varchar(255) NOT NULL, 
	PRIMARY KEY (PersonalDataID));
CREATE TABLE "Films" (
	FilmID SERIAL NOT NULL, 
	Name varchar(100) NOT NULL, 
	Description varchar(255), 
	Price numeric(5, 2) NOT NULL, 
	PricePerMonth numeric(5, 2) NOT NULL, 
	Amount int4 NOT NULL, 
	PRIMARY KEY (FilmID));

ALTER TABLE "Address" ADD CONSTRAINT FKAddress873157 FOREIGN KEY (CityID) REFERENCES "City" (CityID);
ALTER TABLE "ClientData" ADD CONSTRAINT FKClientData358449 FOREIGN KEY (AddressID) REFERENCES "Address" (AddressID);
ALTER TABLE "ClientData" ADD CONSTRAINT FKClientData810950 FOREIGN KEY (PersonalDataID) REFERENCES "PersonalData" (PersonalDataID);
ALTER TABLE "Order" ADD CONSTRAINT FKOrder656903 FOREIGN KEY (ClientID) REFERENCES "ClientData" (ClientID);
ALTER TABLE "Employee" ADD CONSTRAINT FKEmployee854123 FOREIGN KEY (PersonalDataID) REFERENCES "PersonalData" (PersonalDataID);
ALTER TABLE "Order" ADD CONSTRAINT FKOrder301979 FOREIGN KEY (FilmID) REFERENCES "Films" (FilmID);
ALTER TABLE "Order" ADD CONSTRAINT FKOrder932455 FOREIGN KEY (EmployeeID) REFERENCES "Employee" (EmployeeID);
