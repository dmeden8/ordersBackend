DROP SCHEMA IF EXISTS caffe_orders CASCADE;

CREATE SCHEMA IF NOT EXISTS caffe_orders AUTHORIZATION caffe;


CREATE TABLE IF NOT EXISTS caffe_orders.user_account
(
  id bigserial NOT NULL,
  username character varying(255) NOT NULL,
  password character varying(255) NOT NULL,
  name character varying(255) NOT NULL,
  email character varying(255) NOT NULL,
  oib character varying(255) NOT NULL,
  address character varying(255) NOT NULL,
  owner_name character varying(255) NOT NULL,
  tel_number character varying(255) NOT NULL,
  register_time character varying(255) NOT NULL,
  activation_time character varying(255),
  status character varying(10) NOT NULL,
  CONSTRAINT account_pkey PRIMARY KEY (id),
  CONSTRAINT account_username_uk UNIQUE (username),
  CONSTRAINT account_email_uk UNIQUE (email),
  CONSTRAINT account_oib_uk UNIQUE (oib),
  CONSTRAINT users_check_status CHECK (status = ANY (ARRAY['N'::text, 'A'::text, 'S'::text]))
) WITH (
  OIDS = FALSE
);
ALTER TABLE caffe_orders.user_account
  OWNER TO caffe;
  
  
CREATE TABLE IF NOT EXISTS caffe_orders.role
(
  id bigserial NOT NULL,
  authority character varying(255) NOT NULL,
  CONSTRAINT role_pkey PRIMARY KEY (id),
  CONSTRAINT role_authority_uk UNIQUE (authority)
) WITH (
  OIDS = FALSE
);
ALTER TABLE caffe_orders.role
  OWNER TO caffe;
  

CREATE TABLE IF NOT EXISTS caffe_orders.user_role
(
  user_account_id bigint NOT NULL,
  role_id bigint NOT NULL,
  CONSTRAINT account_role_pkey PRIMARY KEY (user_account_id, role_id),
  CONSTRAINT account_role_uk UNIQUE (user_account_id, role_id),
  CONSTRAINT account_fkey FOREIGN KEY (user_account_id) REFERENCES caffe_orders.user_account(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT role_fkey FOREIGN KEY (role_id) REFERENCES caffe_orders.role(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
) WITH (
  OIDS = FALSE
);
ALTER TABLE caffe_orders.user_role
  OWNER TO caffe;  
  
 
CREATE TABLE IF NOT EXISTS caffe_orders.tenant
(
  id bigserial NOT NULL,
  name character varying(255) NOT NULL,
  CONSTRAINT tenant_pkey PRIMARY KEY (id)
) WITH (
  OIDS = FALSE
);
ALTER TABLE caffe_orders.tenant
  OWNER TO caffe;
  
   
CREATE TABLE IF NOT EXISTS caffe_orders.user_tenant
(
  user_account_id bigint NOT NULL,
  tenant_id bigint NOT NULL,
  CONSTRAINT user_tenant_pkey PRIMARY KEY (user_account_id, tenant_id),
  CONSTRAINT user_tenant_uk UNIQUE (user_account_id, tenant_id),
  CONSTRAINT user_account_fkey FOREIGN KEY (user_account_id) REFERENCES caffe_orders.user_account(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT tenant_fkey FOREIGN KEY (tenant_id) REFERENCES caffe_orders.tenant(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
) WITH (
  OIDS = FALSE
);
ALTER TABLE caffe_orders.user_tenant
  OWNER TO caffe; 
  
  
CREATE TABLE IF NOT EXISTS caffe_orders.orders
(
  id bigserial NOT NULL,
  creation_time character varying(255) NOT NULL,
  user_account_id bigint NOT NULL,
  tenant_id bigint NOT NULL,
  total_price numeric(9,2) NOT NULL,
  status character varying(10) NOT NULL,
  CONSTRAINT orders_pkey PRIMARY KEY (id),
  CONSTRAINT orders_check_status CHECK (status = ANY (ARRAY['N'::text, 'I'::text, 'P'::text, 'F'::text])),
  CONSTRAINT user_account_fkey FOREIGN KEY (user_account_id) REFERENCES caffe_orders.user_account(id)
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tenant_fkey FOREIGN KEY (tenant_id) REFERENCES caffe_orders.tenant(id)
      ON UPDATE NO ACTION ON DELETE NO ACTION
) WITH (
  OIDS = FALSE
);
ALTER TABLE caffe_orders.orders
  OWNER TO caffe;  
  
  
CREATE TABLE IF NOT EXISTS caffe_orders.item
(
  id bigserial NOT NULL,
  name character varying(255) NOT NULL,
  price numeric(9,2) NOT NULL,
  tenant_id bigint NOT NULL,
  CONSTRAINT item_pkey PRIMARY KEY (id),
  CONSTRAINT item_name_uk UNIQUE (name),
  CONSTRAINT tenant_fkey FOREIGN KEY (tenant_id) REFERENCES caffe_orders.tenant(id)
      ON UPDATE NO ACTION ON DELETE NO ACTION
) WITH (
  OIDS = FALSE
);
ALTER TABLE caffe_orders.item
  OWNER TO caffe;  
  
  
CREATE TABLE IF NOT EXISTS caffe_orders.order_item
(
  id bigserial NOT NULL,
  order_id bigint NOT NULL,
  item_id bigint NOT NULL,
  amount bigint NOT NULL,  
  CONSTRAINT order_fkey FOREIGN KEY (order_id) REFERENCES caffe_orders.orders(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT item_fkey FOREIGN KEY (item_id) REFERENCES caffe_orders.item(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
) WITH (
  OIDS = FALSE
);
ALTER TABLE caffe_orders.order_item
  OWNER TO caffe;    
  
  


