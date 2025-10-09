WITH new_customer AS (
INSERT INTO customer (customer_type)
VALUES ('BUSINESS')
    RETURNING customer_id
    )
INSERT INTO customer_business (customer_id, legal_name)
SELECT customer_id, 'Test Business 1000'
FROM new_customer;