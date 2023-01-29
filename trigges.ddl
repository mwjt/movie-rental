--Rent_Film
CREATE OR REPLACE FUNCTION Rent_Film_function()
RETURNS TRIGGER AS $$
BEGIN
  UPDATE "order" 
    SET return_date = rental_date + INTERVAL '1 month';
  UPDATE "film" 
    SET amount = amount - 1 
    WHERE id = NEW.film_id;
  UPDATE "client_data"
    SET account_balance = account_balance - (SELECT price_per_month FROM "film" WHERE id = NEW.film_id)
    WHERE id = NEW.client_id;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS Rent_Film ON "order";
CREATE TRIGGER Rent_Film
AFTER INSERT 
ON "order"
FOR EACH ROW
EXECUTE PROCEDURE Rent_Film_function();

--Extend_Rent
CREATE OR REPLACE FUNCTION Extend_Rent_function()
RETURNS TRIGGER AS $$
BEGIN
  UPDATE "order"
    SET return_date = OLD.return_date + INTERVAL '1 month'
    WHERE id = NEW.id;
  UPDATE "client_data"
    SET account_balance = account_balance - (SELECT price_per_month FROM "film" WHERE id = NEW.id)
    WHERE id = NEW.client_id;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS Extend_Rent ON "order";
CREATE TRIGGER Extend_Rent
AFTER UPDATE OF return_date
ON "order"
FOR EACH ROW
WHEN (pg_trigger_depth() = 0)
EXECUTE PROCEDURE Extend_Rent_function();

--Charge_Fine
CREATE OR REPLACE FUNCTION Charge_Fine_function()
RETURNS TRIGGER AS $$
BEGIN
  UPDATE "client_data"
    SET account_balance = account_balance - (SELECT price FROM "film" WHERE id = NEW.film_id)
    WHERE id = NEW.client_id;

  DELETE FROM "order" WHERE id = NEW.id;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS Charge_Fine ON "order";
CREATE TRIGGER Charge_Fine
AFTER UPDATE OF status
ON "order"
FOR EACH ROW
WHEN (OLD.status <> 'damaged' AND NEW.status = 'damaged')
EXECUTE PROCEDURE Charge_Fine_function();

--End_Order
CREATE OR REPLACE FUNCTION End_Order_function()
RETURNS TRIGGER AS $$
BEGIN
  DELETE FROM "order" WHERE id = NEW.id;
  UPDATE "film"
    SET amount = amount + 1
    WHERE id = NEW.film_id;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS End_Order ON "order";
CREATE TRIGGER End_Order
AFTER UPDATE OF status
ON "order"
FOR EACH ROW
WHEN (OLD.status <> 'ended' AND NEW.status = 'ended')
EXECUTE PROCEDURE End_Order_function();

--Remove_Film
CREATE OR REPLACE FUNCTION Remove_Film_function()
RETURNS TRIGGER AS $$
BEGIN
	DELETE FROM "order" WHERE film_id = OLD.id;
  RETURN OLD;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS Remove_Film ON "film";
CREATE TRIGGER Remove_Film
BEFORE DELETE 
ON "film"
FOR EACH ROW
EXECUTE PROCEDURE Remove_Film_function();

--Remove_Client_Data_Before
CREATE OR REPLACE FUNCTION Remove_Client_Data_Before_function()
RETURNS TRIGGER AS $$
BEGIN
  DELETE FROM "order" WHERE client_id = OLD.id;
  RETURN OLD;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS Remove_Client_Data_Before ON "client_data";
CREATE TRIGGER Remove_Client_Data_Before
BEFORE DELETE 
ON "client_data"
FOR EACH ROW
EXECUTE PROCEDURE Remove_Client_Data_Before_function();

--Remove_Client_Data_After
CREATE OR REPLACE FUNCTION Remove_Client_Data_After_function()
RETURNS TRIGGER AS $$
BEGIN
  DELETE FROM "address" WHERE id = OLD.address_id;
  DELETE FROM "personal_data" WHERE id = OLD.personal_data_id;
  RETURN OLD;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS Remove_Client_Data_After ON "client_data";
CREATE TRIGGER Remove_Client_Data_After
AFTER DELETE 
ON "client_data"
FOR EACH ROW
EXECUTE PROCEDURE Remove_Client_Data_After_function();

--Remove_Employee
CREATE OR REPLACE FUNCTION Remove_Employee_function()
RETURNS TRIGGER AS $$
BEGIN
  UPDATE "order"
    SET employee_id = NULL
    WHERE employee_id = OLD.id;
  RETURN OLD;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS Remove_Employee ON "employee";
CREATE TRIGGER Remove_Employee
BEFORE DELETE 
ON "employee"
FOR EACH ROW
EXECUTE PROCEDURE Remove_Employee_function();