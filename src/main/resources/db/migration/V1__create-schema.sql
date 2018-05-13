DROP TABLE IF EXISTS universe.customer;

CREATE TABLE universe.customer
(
    id bigserial NOT NULL,
    first_name character varying(30),
    last_name character varying(30),
    CONSTRAINT customer_pkey PRIMARY KEY (id)
);

ALTER TABLE universe.customer
    OWNER to admin;
