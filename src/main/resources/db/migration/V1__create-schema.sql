DROP TABLE IF EXISTS universe.customers;

CREATE TABLE universe.customers
(
    id bigserial NOT NULL,
    first_name character varying(30),
    last_name character varying(30),
    CONSTRAINT customer_pkey PRIMARY KEY (id)
);

ALTER TABLE universe.customers
    OWNER to admin;
