CREATE TYPE suffix AS ENUM ('JR', 'SR', 'III', 'IV', 'V', 'VI', 'VII', 'VIII', 'IX', 'X');
CREATE CAST (varchar AS suffix) WITH INOUT AS IMPLICIT;
CREATE TYPE customer_type AS ENUM ('BUSINESS', 'INDIVIDUAL');
CREATE CAST (varchar AS customer_type) WITH INOUT AS IMPLICIT;

CREATE SEQUENCE customer_business_seq START WITH 1000000000000000000;
CREATE SEQUENCE customer_individual_seq START WITH 2000000000000000000;

CREATE TABLE customer
(
    customer_id BIGINT PRIMARY KEY,
    customer_type customer_type NOT NULL
);

CREATE TABLE customer_business
(
    customer_id BIGINT PRIMARY KEY REFERENCES customer(customer_id) ON DELETE CASCADE,
    legal_name  TEXT NOT NULL
);

CREATE TABLE customer_individual
(
    customer_id BIGINT PRIMARY KEY REFERENCES customer(customer_id) ON DELETE CASCADE,
    first_name  TEXT NOT NULL,
    middle_name TEXT,
    last_name   TEXT NOT NULL,
    suffix      suffix
);

CREATE OR REPLACE FUNCTION assign_customer_id()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.customer_type = 'BUSINESS' THEN
        NEW.customer_id := nextval('customer_business_seq');
    ELSIF NEW.customer_type = 'INDIVIDUAL' THEN
        NEW.customer_id := nextval('customer_individual_seq');
    ELSE
        RAISE EXCEPTION 'Invalid customer_type provided: %', NEW.customer_type;
    END IF;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER before_insert_customer
    BEFORE INSERT ON customer
    FOR EACH ROW
    EXECUTE FUNCTION assign_customer_id();

