SELECT
CONCAT(c.first_name," ",c.last_name) "Cliente",
i.name "Item",
s.sale_datetime "Fecha de venta" 
FROM sales s 
INNER JOIN customers c ON (s.id_customer = c.dni) 
INNER JOIN items i ON (s.id_item = i.item_code) 
ORDER BY s.sale_datetime DESC ;
