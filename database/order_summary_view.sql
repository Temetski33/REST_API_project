CREATE OR REPLACE VIEW order_summary AS
SELECT
    o.id AS order_id,
    o.order_date,
    o.status,
    c.id AS customer_id,
    CONCAT(c.first_name, ' ', c.last_name) AS customer_name,
    COUNT(oi.product_id) AS item_count
FROM orders o
JOIN customers c ON c.id = o.customer_id
LEFT JOIN orderitems oi ON oi.order_id = o.id
GROUP BY o.id, o.order_date, o.status, c.id, c.first_name, c.last_name;
