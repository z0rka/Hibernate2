alter table my_shop.address add column fk_client_id integer constraint fk_data_primary_key references my_shop.client;
alter table my_shop.order_item add column fk_product_id integer constraint fk_data_primary_key1 references my_shop.product;
alter table my_shop.order add column fk_client_id integer constraint fk_data_primary_key2 references my_shop.client;
alter table my_shop.order_item add column fk_order_id integer constraint fk_data_primary_key3 references my_shop.order;