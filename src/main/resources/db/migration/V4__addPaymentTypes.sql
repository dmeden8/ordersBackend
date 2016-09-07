DROP TABLE IF EXISTS caffe_orders.payment_type CASCADE;

CREATE TABLE IF NOT EXISTS caffe_orders.payment_type
(
  id bigserial NOT NULL,
  description character varying(255) NOT NULL,
  tenant_id bigint NOT NULL,
  CONSTRAINT payment_type_pkey PRIMARY KEY (id),
  CONSTRAINT payment_type_uk UNIQUE (description)
) WITH (
  OIDS = FALSE
);
ALTER TABLE caffe_orders.payment_type
  OWNER TO caffe;
  
  
INSERT INTO caffe_orders.payment_type (description, tenant_id) VALUES ('Gotovina',1);
INSERT INTO caffe_orders.payment_type (description, tenant_id) VALUES ('Virman - 7 dana',1);
INSERT INTO caffe_orders.payment_type (description, tenant_id) VALUES ('Virman - 15 dana',1);
INSERT INTO caffe_orders.payment_type (description, tenant_id) VALUES ('Virman - 30 dana',1);

ALTER TABLE caffe_orders.orders ADD COLUMN payment_type_id bigint NOT NULL DEFAULT 1;

ALTER TABLE caffe_orders.orders ADD CONSTRAINT payment_type_fkey FOREIGN KEY (payment_type_id) REFERENCES caffe_orders.payment_type(id)
      ON UPDATE NO ACTION ON DELETE NO ACTION;
