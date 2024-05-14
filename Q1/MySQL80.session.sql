CREATE TABLE customers (
    customer_id INT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100),
    phone_number VARCHAR(13),
    `rank` int
);

CREATE TABLE orders (
order_id INT PRIMARY KEY,
customer_id INT,
order_date DATE,
total_amount DECIMAL(10, 2),
FOREIGN KEY (customer_id) REFERENCES customers (customer_id)
);

-- ข้อ 1.1 : insert data
INSERT INTO customers(customer_id, first_name, last_name, email, phone_number)
VALUES (1, 'John', 'Doe', 'john.doe@example.com', '1234567890')

-- pre data
INSERT INTO customers(customer_id, first_name, last_name, email, phone_number)
VALUES 
    (2, 'Varanyu', 'Leelasopin', 'Varanyu50015@gmail.com', '0830762642'),
    (3, 'Benjamin', 'Mungmee', 'BenMung@hotmail,com', '0885943062')

INSERT INTO orders(order_id, customer_id, order_date, total_amount)
VALUES 
    (1, 1, '2015-06-28', 2500),
    (2, 1, '2016-03-18', 1220.75),
    (3, 2, '2020-12-05', 200000),
    (4, 3, '2024-02-10', 200)

-- test 1.2: name "j", "J"
SELECT * FROM orders as o 
JOIN customers as c
ON o.customer_id = c.customer_id 
WHERE c.first_name LIKE '%j%' OR c.first_name LIKE '%J%'

-- test 1.3: rank -> 10
SELECT SUM(total_amount), first_name
FROM orders as o
JOIN customers as c
ON o.customer_id = c.customer_id
GROUP BY c.customer_id

UPDATE customers
SET `rank` = NULL
WHERE customer_id IN (
    SELECT customer_id FROM orders
    GROUP BY customer_id
    HAVING SUM(total_amount) > 100000
)

UPDATE customers
SET `rank` = 10
WHERE customer_id IN (
    SELECT customer_id FROM orders
    GROUP BY customer_id
    HAVING SUM(total_amount) > 100000
)

-- test 1.4 : phone number
INSERT INTO customers(customer_id, first_name, last_name, email, phone_number)
VALUES
    (4, 'apple', 'test', 'Iphone@apple.com', '0090100000'),
    (5, 'android', 'test', 'Sumsung@android.com', '008030000')

INSERT INTO orders(order_id, customer_id, order_date, total_amount)
VALUES 
    (5, 4, '1995-05-05', 10),
    (6, 5, '1999-09-09', 20)

DELETE FROM orders
WHERE total_amount < 100

DELETE FROM customers
WHERE SUBSTRING(phone_number, 3, 1) > SUBSTRING(phone_number, 5, 1)