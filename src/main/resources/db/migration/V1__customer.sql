CREATE TYPE suffix AS ENUM ('JR', 'SR', 'III', 'IV', 'V', 'VI', 'VII', 'VIII', 'IX', 'X');
CREATE CAST (character varying AS suffix) with inout as assignment;

CREATE TABLE customer_individual
(
    customer_id BIGSERIAL PRIMARY KEY,
    first_name  TEXT NOT NULL,
    middle_name TEXT,
    last_name   TEXT NOT NULL,
    suffix      suffix
);
ALTER SEQUENCE customer_individual_customer_id_seq RESTART 1000000000000000000;

CREATE TABLE customer_business
(
    customer_id BIGSERIAL PRIMARY KEY,
    legal_name  TEXT NOT NULL
);
ALTER SEQUENCE customer_business_customer_id_seq RESTART 2000000000000000000;