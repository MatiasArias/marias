DROP SCHEMA IF EXISTS store;
CREATE SCHEMA store;
USE store;

DROP TABLE IF EXISTS customers;
CREATE TABLE customers (
 dni VARCHAR(9) NOT NULL,
 first_name VARCHAR(50) NOT NULL,
 last_name VARCHAR(50) NOT NULL,
 phone_number VARCHAR(15) NOT NULL,
 registration_date DATE DEFAULT (CURRENT_DATE) NOT NULL,
 PRIMARY KEY (dni)
);

DROP TABLE IF EXISTS items;
CREATE TABLE items (
item_code INT NOT NULL,
name VARCHAR(50) NOT NULL,
description VARCHAR(144) NOT NULL,
stock INT NOT NULL,
PRIMARY KEY(item_code)
);

DROP TABLE IF EXISTS sales;
CREATE TABLE sales(
id_customer VARCHAR(9) NOT NULL,
id_item INT NOT NULL,
amount INT NOT NULL,
sale_datetime DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
PRIMARY KEY (id_customer,id_item,sale_datetime),
 CONSTRAINT `fk_sales_customer` FOREIGN KEY (id_customer) REFERENCES customers (dni) ON DELETE RESTRICT ON UPDATE CASCADE,
 CONSTRAINT `fk_sales_items` FOREIGN KEY (id_item) REFERENCES items (item_code) ON DELETE RESTRICT ON UPDATE CASCADE
);

DELIMITER $$
CREATE FUNCTION `ventas_cliente` (
dni varchar(9)
)
RETURNS INTEGER
READS SQL DATA
BEGIN
DECLARE customer_sales INTEGER;
SELECT
SUM(s.amount)
FROM sales s
WHERE s.id_customer = dni
INTO customer_sales;
RETURN customer_sales;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `guardar_datos_cliente`(
dni_p VARCHAR(9),
first_name_p VARCHAR(50),
last_name_p VARCHAR(50),
phone_number_p VARCHAR(15),
registration_date_p DATE
)
BEGIN 	
  DECLARE listo BOOLEAN DEFAULT FALSE;
  DECLARE saved BOOLEAN DEFAULT FALSE;
  DECLARE dni_cursor integer;
  DECLARE c1 CURSOR FOR SELECT dni FROM customers;
  DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET listo=TRUE;
  OPEN c1;
  c1_loop:LOOP
  FETCH c1 INTO dni_cursor;
   IF dni_cursor=dni_p then
	UPDATE customers SET first_name=first_name_p,last_name=last_name_p,phone_number=phone_number_p,registration_date=registration_date_p WHERE dni=dni_p;
	SET saved= TRUE;
    END IF;
  IF listo=TRUE THEN 
	IF saved=FALSE THEN
		INSERT INTO customers(dni,first_name,last_name,phone_number,registration_date) VALUES (dni_p,first_name_p,last_name_p,phone_number_p,registration_date_p);
    END IF;
    LEAVE c1_loop;
    END IF;
END LOOP c1_loop;
CLOSE c1;
END $$
DELIMITER ;
