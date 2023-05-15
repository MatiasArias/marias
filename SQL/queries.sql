SELECT
CONCAT(c.first_name," ",c.last_name) "Cliente",
i.name "Item",
s.sale_datetime "Fecha de venta" 
FROM sales s 
INNER JOIN customers c ON (s.id_customer = c.dni) 
INNER JOIN items i ON (s.id_item = i.item_code) 
ORDER BY s.sale_datetime DESC ;

SELECT
i.name "Item",
SUM(s.amount) "Unidades vendidas"
FROM sales s
INNER JOIN items i ON (s.id_item = i.item_code) 
GROUP BY i.name
ORDER BY i.name ASC;

SELECT ventas_cliente('33445566') "Ventas cliente"; 

