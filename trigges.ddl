--RentFilmTrigger
CREATE OR REPLACE FUNCTION RentFilmTrigger_function()
RETURNS TRIGGER AS $$
BEGIN
	UPDATE "Order" SET returndate = rentaldate + INTERVAL '1 month';
    UPDATE "Films" SET amount = amount - 1 WHERE filmid = NEW.filmid;
	
	UPDATE "ClientData"
    SET accountbalance = accountbalance - (SELECT pricepermonth FROM "Films" WHERE filmid = NEW.filmid)
    WHERE clientid = NEW.clientid;
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;
	
CREATE TRIGGER RentFilmTrigger
AFTER INSERT ON "Order"
FOR EACH ROW
EXECUTE PROCEDURE RentFilmTrigger_function();


--ExtendRentTrigger
CREATE OR REPLACE FUNCTION ExtendRentTrigger_function()
RETURNS TRIGGER AS $$
BEGIN
  UPDATE "Order"
  SET returndate = OLD.returndate + INTERVAL '1 month'
  WHERE orderid = NEW.orderid;

  UPDATE "ClientData"
  SET accountbalance = accountbalance - (SELECT pricepermonth FROM "Films" WHERE filmid = NEW.filmid)
  WHERE clientid = NEW.clientid;
  RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER ExtendRentTrigger
AFTER UPDATE OF returndate
ON "Order"
FOR EACH ROW
WHEN (pg_trigger_depth() = 0)
EXECUTE PROCEDURE ExtendRentTrigger_function();


--ChargeFineTrigger
CREATE OR REPLACE FUNCTION ChargeFineTrigger_function()
RETURNS TRIGGER AS $$
BEGIN
  UPDATE "ClientData"
  SET accountbalace = accountbalace - (SELECT price FROM "films" WHERE filmid = NEW.filmid)
  WHERE clientid = NEW.clientid;

  DELETE FROM "Order" WHERE orderid = NEW.orderid;
  RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER ChargeFineTrigger
AFTER UPDATE OF status
ON "Order"
FOR EACH ROW
WHEN (OLD.status <> 'Damaged' AND NEW.status = 'Damaged')
EXECUTE PROCEDURE ChargeFineTrigger_function();

--CREATE TRIGGER EndOrderTrigger
CREATE OR REPLACE FUNCTION EndOrderTrigger_function()
RETURNS TRIGGER AS $$
BEGIN
  DELETE FROM orders WHERE id = NEW.id;

  UPDATE "Films"
  SET amount = amount + 1
  WHERE filmid = NEW.filmid; --lub WHERE filmid = NEW.filmid;
  RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER EndOrderTrigger
AFTER UPDATE OF status
ON "Order"
FOR EACH ROW
WHEN (OLD.status <> 'Ended' AND NEW.status = 'Ended')
EXECUTE PROCEDURE EndOrderTrigger_function();

--CREATE TRIGGER Remove_Film
CREATE OR REPLACE FUNCTION Remove_Film_function()
RETURNS TRIGGER AS $$
BEGIN
  UPDATE "Order"
  SET filmid = NULL
  WHERE filmid = OLD.filmid;
  RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER Remove_Film
BEFORE DELETE ON "Films"
FOR EACH ROW
EXECUTE PROCEDURE Remove_Film_function();

--CREATE TRIGGER Remove_ClientData
CREATE OR REPLACE FUNCTION Remove_ClientData_function()
RETURNS TRIGGER AS $$
BEGIN
  DELETE FROM "Address" WHERE addressid = OLD.addressid;
  DELETE FROM "PersonalData" WHERE personaldataid = OLD.personaldataid;
  DELETE FROM "Order" WHERE orderid = OLD.orderid;
  RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER Remove_ClientData
BEFORE DELETE ON "ClientData"
FOR EACH ROW
EXECUTE PROCEDURE Remove_ClientData_function();

--CREATE TRIGGER Remove_Employee
CREATE OR REPLACE FUNCTION Remove_Employee_function()
RETURNS TRIGGER AS $$
BEGIN
  UPDATE "Order"
  SET employeeid = NULL
  WHERE employeeid = OLD.employeeid;
  RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER Remove_Employee
BEFORE DELETE ON "Employee"
FOR EACH ROW
EXECUTE PROCEDURE Remove_Employee_function();

