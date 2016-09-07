DROP TABLE IF EXISTS caffe_orders.category CASCADE;

CREATE TABLE IF NOT EXISTS caffe_orders.category
(
  id bigserial NOT NULL,
  name character varying(255) NOT NULL,
  parent_id bigint,
  tenant_id bigint NOT NULL,
  image_url character varying(255),
  CONSTRAINT category_pkey PRIMARY KEY (id),
  CONSTRAINT category_name_uk UNIQUE (name),
  CONSTRAINT category_fkey FOREIGN KEY (parent_id) REFERENCES caffe_orders.category(id)
    ON DELETE NO ACTION ON UPDATE NO ACTION	
) WITH (
  OIDS = FALSE
);
ALTER TABLE caffe_orders.category
  OWNER TO caffe;

  

INSERT INTO caffe_orders.category (name, parent_id, tenant_id) VALUES ('root',NULL,1);
INSERT INTO caffe_orders.category (name, parent_id, tenant_id, image_url) VALUES ('Alkoholna piæa',1,1,'../../../images/alkoholna.jpg');
INSERT INTO caffe_orders.category (name, parent_id, tenant_id, image_url) VALUES ('Bezalkoholna piæa',1,1,'../../../images/bezalkoholna.jpg');
INSERT INTO caffe_orders.category (name, parent_id, tenant_id, image_url) VALUES ('Ostalo',1,1,'../../../images/ostalo.jpg');
INSERT INTO caffe_orders.category (name, parent_id, tenant_id, image_url) VALUES ('Vino',2,1,'../../../images/vina.jpg');
INSERT INTO caffe_orders.category (name, parent_id, tenant_id, image_url) VALUES ('Pivo',2,1,'../../../images/pivo.jpg');
INSERT INTO caffe_orders.category (name, parent_id, tenant_id, image_url) VALUES ('Žestoka',2,1,'../../../images/zestoko.jpg');
INSERT INTO caffe_orders.category (name, parent_id, tenant_id, image_url) VALUES ('Voda',3,1,'../../../images/voda.jpg');
INSERT INTO caffe_orders.category (name, parent_id, tenant_id, image_url) VALUES ('Gazirani sokovi',3,1,'../../../images/gazirano.jpg');
INSERT INTO caffe_orders.category (name, parent_id, tenant_id, image_url) VALUES ('Sokovi',3,1,'../../../images/sokovi.jpg');
INSERT INTO caffe_orders.category (name, parent_id, tenant_id, image_url) VALUES ('Pribor za jelo',4,1,'../../../images/pribor.jpg');


DROP TABLE IF EXISTS caffe_orders.item CASCADE;

CREATE TABLE IF NOT EXISTS caffe_orders.item
(
  id bigserial NOT NULL,
  name character varying(255) NOT NULL,
  measure character varying(255) NOT NULL,
  external_id bigint NOT NULL,
  mpc_price numeric(9,2) NOT NULL,
  vpc_price numeric(9,2) NOT NULL,
  tenant_id bigint NOT NULL,
  category_id bigint NOT NULL,
  CONSTRAINT item_pkey PRIMARY KEY (id),
  CONSTRAINT item_name_uk UNIQUE (name),
  CONSTRAINT item_external_id_uk UNIQUE (external_id),
  CONSTRAINT tenant_fkey FOREIGN KEY (tenant_id) REFERENCES caffe_orders.tenant(id)
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT category_fkey FOREIGN KEY (category_id) REFERENCES caffe_orders.category(id)
      ON UPDATE NO ACTION ON DELETE NO ACTION
) WITH (
  OIDS = FALSE
);
ALTER TABLE caffe_orders.item
  OWNER TO caffe;  
  
INSERT INTO caffe_orders.item (name, measure, external_id, mpc_price, vpc_price, tenant_id, category_id) VALUES ('Ribar', '1,0 L' , 1000, 10.45, 7.70, 1, 5);
INSERT INTO caffe_orders.item (name, measure, external_id, mpc_price, vpc_price, tenant_id, category_id) VALUES ('Tga za Jug', '1,0 L' , 1001, 15.35, 10.95, 1, 5);
INSERT INTO caffe_orders.item (name, measure, external_id, mpc_price, vpc_price, tenant_id, category_id) VALUES ('Graševina', '0,75 L' , 1002, 37.30, 20.25, 1, 5);

INSERT INTO caffe_orders.item (name, measure, external_id, mpc_price, vpc_price, tenant_id, category_id) VALUES ('Ožujsko limenka', '0,5 L' , 1003, 7.45, 5.20, 1, 6);
INSERT INTO caffe_orders.item (name, measure, external_id, mpc_price, vpc_price, tenant_id, category_id) VALUES ('Ožujsko boca', '0,5 L' , 1004, 15.35, 10.75, 1, 6);
INSERT INTO caffe_orders.item (name, measure, external_id, mpc_price, vpc_price, tenant_id, category_id) VALUES ('Karlovaèko limenka', '0,33 L' , 1005, 7.30, 5.45, 1, 6);

INSERT INTO caffe_orders.item (name, measure, external_id, mpc_price, vpc_price, tenant_id, category_id) VALUES ('Jack Daniels', '0,7 L' , 1006, 107.45, 85.20, 1, 7);
INSERT INTO caffe_orders.item (name, measure, external_id, mpc_price, vpc_price, tenant_id, category_id) VALUES ('Pelinkovac', '1,0 L' , 1007, 55.35, 40.75, 1, 7);
INSERT INTO caffe_orders.item (name, measure, external_id, mpc_price, vpc_price, tenant_id, category_id) VALUES ('Amaro', '1,0 L' , 1008, 87.30, 55.45, 1, 7);

INSERT INTO caffe_orders.item (name, measure, external_id, mpc_price, vpc_price, tenant_id, category_id) VALUES ('Jana', '1,5 L' , 1009, 7.45, 5.20, 1, 8);
INSERT INTO caffe_orders.item (name, measure, external_id, mpc_price, vpc_price, tenant_id, category_id) VALUES ('Studena mala', '0,5 L' , 1010, 5.35, 4.75, 1, 8);
INSERT INTO caffe_orders.item (name, measure, external_id, mpc_price, vpc_price, tenant_id, category_id) VALUES ('Studena velika', '1,5 L' , 1011, 7.30, 5.45, 1, 8);

INSERT INTO caffe_orders.item (name, measure, external_id, mpc_price, vpc_price, tenant_id, category_id) VALUES ('Coca-cola limenka', '0,5 L' , 1012, 6.45, 4.20, 1, 9);
INSERT INTO caffe_orders.item (name, measure, external_id, mpc_price, vpc_price, tenant_id, category_id) VALUES ('Coca-cola boca', '0,5 L' , 1013, 5.35, 3.75, 1, 9);
INSERT INTO caffe_orders.item (name, measure, external_id, mpc_price, vpc_price, tenant_id, category_id) VALUES ('Fanta boca', '0,5 L' , 1014, 5.30, 3.45, 1, 9);

INSERT INTO caffe_orders.item (name, measure, external_id, mpc_price, vpc_price, tenant_id, category_id) VALUES ('Lero jabuka', '1,0 L' , 1015, 11.45, 8.20, 1, 10);
INSERT INTO caffe_orders.item (name, measure, external_id, mpc_price, vpc_price, tenant_id, category_id) VALUES ('Pago malina', '0,25 L' , 1016, 8.35, 6.75, 1, 10);
INSERT INTO caffe_orders.item (name, measure, external_id, mpc_price, vpc_price, tenant_id, category_id) VALUES ('Cedevita', '1,0 kg' , 1017, 27.30, 18.45, 1, 10);

INSERT INTO caffe_orders.item (name, measure, external_id, mpc_price, vpc_price, tenant_id, category_id) VALUES ('Tanjuri plastièni', '20 kom' , 1018, 14.45, 11.20, 1, 11);
INSERT INTO caffe_orders.item (name, measure, external_id, mpc_price, vpc_price, tenant_id, category_id) VALUES ('Èaše kartonske', '50 kom' , 1019, 25.35, 20.75, 1, 11);
INSERT INTO caffe_orders.item (name, measure, external_id, mpc_price, vpc_price, tenant_id, category_id) VALUES ('Salvete', '100 kom' , 1020, 37.30, 20.45, 1, 11);


