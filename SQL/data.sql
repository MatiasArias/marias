INSERT INTO items(item_code,name,description,stock) VALUES (1,'Yerba','Paquete de 1 kg de yerba',90);
INSERT INTO items(item_code,name,description,stock) VALUES (2,'Azucar','Paquete de 1 kg de azucar',87);
INSERT INTO items(item_code,name,description,stock) VALUES (3,'Pan','Bolsa de ½ kg de pan',105);
INSERT INTO items(item_code,name,description,stock) VALUES (4,'Leche larga vida','Caja tetrapack de leche',103);
INSERT INTO items(item_code,name,description,stock) VALUES (5,'Bolsa de caramelos','Bolsa de 250 g de caramelos',66);
INSERT INTO items(item_code,name,description,stock) VALUES (6,'Queso cremoso','Paquete de 250 g de queso',42);


INSERT INTO customers(dni,first_name,last_name,phone_number,registration_date) VALUES ('33445566','Juan','Piquete','011-1567899879','2015-05-15');
INSERT INTO customers(dni,first_name,last_name,phone_number,registration_date) VALUES ('34347788','Carlos','Calabresa','011-1564566549','2016-06-16');
INSERT INTO customers(dni,first_name,last_name,phone_number,registration_date) VALUES ('33556677','Esteban','Quito','011-1561233219','2014-04-14');
INSERT INTO customers(dni,first_name,last_name,phone_number,registration_date) VALUES ('33445588','Andrea','Lira','0342-155398022','2011-01-11');
INSERT INTO customers(dni,first_name,last_name,phone_number,registration_date) VALUES ('22445566','Laura','Agnay','0342-156378022','2013-03-13');
INSERT INTO customers(dni,first_name,last_name,phone_number,registration_date) VALUES ('25335999','Marixa','Bella','0342-154348022','2012-02-12');

INSERT INTO sales(id_customer,id_item,sale_datetime,amount)VALUES('33445566',1,'2019-01-01 11:30',5);
INSERT INTO sales(id_customer,id_item,sale_datetime,amount)VALUES('34347788',2,'2019-02-01 12:30',4);
INSERT INTO sales(id_customer,id_item,sale_datetime,amount)VALUES('33556677',1,'2019-05-01 10:30',7);
INSERT INTO sales(id_customer,id_item,sale_datetime,amount)VALUES('33445588',2,'2019-04-13 09:30',3);
INSERT INTO sales(id_customer,id_item,sale_datetime,amount)VALUES('22445566',3,'2019-07-13 09:00',2);
INSERT INTO sales(id_customer,id_item,sale_datetime,amount)VALUES('25335999',3,'2019-01-14 10:00',1);
INSERT INTO sales(id_customer,id_item,sale_datetime,amount)VALUES('33445566',4,'2019-02-14 11:00',1);
INSERT INTO sales(id_customer,id_item,sale_datetime,amount)VALUES('34347788',1,'2019-06-14 18:00',4);
INSERT INTO sales(id_customer,id_item,sale_datetime,amount)VALUES('33556677',5,'2019-06-14 18:05',6);
INSERT INTO sales(id_customer,id_item,sale_datetime,amount)VALUES('33445588',4,'2019-07-14 18:30',3);
INSERT INTO sales(id_customer,id_item,sale_datetime,amount)VALUES('22445566',6,'2019-08-14 18:00',5);
INSERT INTO sales(id_customer,id_item,sale_datetime,amount)VALUES('25335999',3,'2019-09-14 19:00',2);
