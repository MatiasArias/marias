-- Mostrar Nombre y Apellido de los clientes y Nombre del artículo vendido, pero ordenando por la fecha de ventas en forma descendente
SELECT
CONCAT(c.first_name," ",c.last_name) "Cliente",
i.name "Item",
s.sale_datetime "Fecha de venta" 
FROM sales s 
INNER JOIN customers c ON (s.id_customer = c.dni) 
INNER JOIN items i ON (s.id_item = i.item_code) 
ORDER BY s.sale_datetime DESC ;

-- Listar los Nombres de los artículos y la suma de las Unidades vendidas. Ordenar alfabéticamente en forma ascendente por el nombre del artículo
SELECT
i.name "Item",
SUM(s.amount) "Unidades vendidas"
FROM sales s
INNER JOIN items i ON (s.id_item = i.item_code) 
GROUP BY i.name
ORDER BY i.name ASC;

-- El comerciante ahora solicita determinar la cantidad de ventas realizadas a un cliente dado. 
SELECT ventas_cliente('33445566') "Ventas cliente"; 

-- El primer procedimiento se utilizará para insertar/actualizar datos de Clientes. Deberá llamarse guardar_datos_cliente, recibe parámetro análogos a cada
-- columna de la tabla Cliente y no posee un tipo de retorno.
call guardar_datos_cliente('25335999','Maria','Benitez','0342-154139913','2022-12-18');
call guardar_datos_cliente('43132313','Matias','Arias','0342-154266141','2018-12-09');
select * from customers;

-- clientes_que_superan tendremos que utilizar la función creada antes (ventas_cliente) para obtener las
-- ventas y luego filtrar sólo aquellos clientes que superen la cantidad de ventas indicada
DROP TABLE IF EXISTS clientes;
CREATE TEMPORARY TABLE clientes (
 dni VARCHAR(9) NOT NULL,
 first_name VARCHAR(50) NOT NULL,
 last_name VARCHAR(50) NOT NULL,
 phone_number VARCHAR(15) NOT NULL,
 registration_date DATE DEFAULT (CURRENT_DATE) NOT NULL
    );
call clientes_que_superan(5);
select * from clientes;
