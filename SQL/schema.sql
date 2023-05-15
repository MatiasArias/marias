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