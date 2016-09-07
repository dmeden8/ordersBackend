INSERT INTO caffe_orders.user_account (username, password, name, email, oib, address, owner_name, tel_number, status, register_time) 
  VALUES ('grga', '$2a$10$rcxWBuu4eOqSNErWHOH3COKGBgdU/C/mWhdczf6jz8Vhhap/j6klq','Grga','grga@gmail.com', '90682024349', 'Krležina 15', 'Ivan Grgiæ', '052/542-735', 'N', '2016-01-01T13:12:59.498+02:00[Europe/Zagreb]');
INSERT INTO caffe_orders.user_account (username, password, name, email, oib, address, owner_name, tel_number, status, register_time)  
  VALUES ('bass', '$2a$10$T2CW77Dsi.S72or/hobnwOp07B8BRas4qK4Djb2GkWYIIZ15O7/FS','Bass','bass@gmail.com', '60682074349', 'Vrbik 8', 'Marko Iviæ', '01/3076-235', 'N', '2016-01-02T15:12:59.498+02:00[Europe/Zagreb]');
INSERT INTO caffe_orders.user_account (username, password, name, email, oib, address, owner_name, tel_number, status, register_time)  
  VALUES ('e&d', '$2a$07$OAjZ7JA6WliDuYEDA54LFufMjPrWaQMdGFWXZ43vMAq0gQoiNjyfS','E&D','ed@gmail.com', '40642024349', 'Trg Maršala Tita 1', 'David Juran', '052/542-123', 'N', '2016-01-06T18:12:59.498+02:00[Europe/Zagreb]');
INSERT INTO caffe_orders.user_account (username, password, name, email, oib, address, owner_name, tel_number, status, register_time)  
  VALUES ('cabahia', '$2a$04$Hh22zHUlCl8nUFkGxI2F.uutTVgRXjswWCNEofUmGOqGO22ksXk/y','Cabahia','cabahia@gmail.com', '11182024349', 'Arsenalska 28', 'Nevio Meden', '051/842-735', 'N', '2016-01-10T13:12:59.498+02:00[Europe/Zagreb]');

INSERT INTO caffe_orders.tenant (name) VALUES ('grga');
INSERT INTO caffe_orders.tenant (name) VALUES ('deniken');

INSERT INTO caffe_orders.user_tenant (user_account_id, tenant_id) VALUES (2,1);
INSERT INTO caffe_orders.user_tenant (user_account_id, tenant_id) VALUES (3,1);
INSERT INTO caffe_orders.user_tenant (user_account_id, tenant_id) VALUES (4,1);

INSERT INTO caffe_orders.role (authority) VALUES ('ROLE_ADMIN');
INSERT INTO caffe_orders.role (authority) VALUES ('ROLE_USER');

INSERT INTO caffe_orders.user_role (user_account_id, role_id) VALUES (1, 1);
INSERT INTO caffe_orders.user_role (user_account_id, role_id) VALUES (1, 2);
INSERT INTO caffe_orders.user_role (user_account_id, role_id) VALUES (2, 2);
INSERT INTO caffe_orders.user_role (user_account_id, role_id) VALUES (3, 2);
INSERT INTO caffe_orders.user_role (user_account_id, role_id) VALUES (4, 2);

INSERT INTO caffe_orders.item (name, price, tenant_id) VALUES ('Coca Cola 0,2' , 5.45, 1);
INSERT INTO caffe_orders.item (name, price, tenant_id) VALUES ('Coca Cola 0,33' , 7.35, 1);
INSERT INTO caffe_orders.item (name, price, tenant_id) VALUES ('Jack Daniels 0,7' , 38.75, 1);
INSERT INTO caffe_orders.item (name, price, tenant_id) VALUES ('Cedevita 10' , 2.15, 1);
INSERT INTO caffe_orders.item (name, price, tenant_id) VALUES ('Ožujsko 0,5' , 7.45, 1);