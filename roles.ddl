DO
$$BEGIN
IF EXISTS (SELECT FROM pg_roles WHERE rolname = 'admin') THEN
	EXECUTE 'REVOKE ALL PRIVILEGES ON DATABASE movie_rental FROM "admin"'; 
	DROP ROLE IF EXISTS "admin";
END IF;
END$$;

DO
$$BEGIN
IF EXISTS (SELECT FROM pg_roles WHERE rolname = 'employee') THEN
	EXECUTE 'REVOKE SELECT, UPDATE, INSERT, DELETE ON film FROM employee'; 
	EXECUTE 'REVOKE SELECT, UPDATE ON "order" FROM employee';
	EXECUTE 'REVOKE SELECT ON client_data FROM employee';
	EXECUTE 'REVOKE SELECT ON personal_data FROM employee';
	EXECUTE 'REVOKE SELECT ON address FROM employee';
	EXECUTE 'REVOKE SELECT ON city FROM employee';
	DROP ROLE IF EXISTS employee;
END IF;
END$$;

DO
$$BEGIN
IF EXISTS (SELECT FROM pg_roles WHERE rolname = 'client') THEN
    EXECUTE 'REVOKE SELECT, UPDATE ON personal_data FROM client';
    EXECUTE 'REVOKE SELECT, UPDATE ON address FROM client';
	EXECUTE 'REVOKE SELECT, UPDATE, INSERT ON "order" FROM client';
	DROP ROLE IF EXISTS client;
END IF;
END$$;

DO
$$BEGIN
IF EXISTS (SELECT FROM pg_roles WHERE rolname = 'anonymus_user') THEN
    EXECUTE 'REVOKE SELECT ON film FROM anonymus_user';
	DROP ROLE IF EXISTS anonymus_user;
END IF;
END$$;


CREATE USER anonymus_user;
GRANT SELECT ON film TO anonymus_user;

CREATE USER client INHERIT;
GRANT SELECT ON "order" TO client;
GRANT anonymus_user TO client;

CREATE USER employee INHERIT;
GRANT UPDATE, INSERT, DELETE ON film TO employee; 
GRANT UPDATE ON "order" TO employee;
GRANT SELECT ON client_data TO employee;
GRANT SELECT ON personal_data TO employee;
GRANT SELECT ON address TO employee;
GRANT SELECT ON city TO employee;
GRANT client TO employee;

CREATE USER "admin";
GRANT ALL PRIVILEGES ON DATABASE movie_rental TO "admin";