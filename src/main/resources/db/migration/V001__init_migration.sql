CREATE SCHEMA IF NOT EXISTS my_shop;

CREATE SEQUENCE IF NOT EXISTS my_shop.my_shop_id_seq;

CREATE TABLE IF NOT EXISTS my_shop.client
(
    id            integer NOT NULL DEFAULT nextval('my_shop.my_shop_id_seq') primary key,
    name          text    NOT NULL,
    email         text    NOT NULL,
    phone         text    NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_product ON my_shop.client USING hash (name);

CREATE TABLE IF NOT EXISTS my_shop.address
(
    id           integer NOT NULL DEFAULT nextval('my_shop.my_shop_id_seq') primary key,
    country      text    NOT NULL,
    city         text    NOT NULL,
    street       text    NOT NULL,
    house        text    NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_product ON my_shop.address USING hash (street);


CREATE TABLE IF NOT EXISTS my_shop.order
(
    id           integer NOT NULL DEFAULT nextval('my_shop.my_shop_id_seq') primary key

);

CREATE INDEX IF NOT EXISTS idx_user_status ON my_shop.order USING hash (id);

CREATE TABLE IF NOT EXISTS my_shop.order_item
(
    id            integer NOT NULL DEFAULT nextval('my_shop.my_shop_id_seq') primary key,

    count         integer NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_user_status ON my_shop.order USING hash (id);

CREATE TABLE IF NOT EXISTS my_shop.product
(
    id          integer          NOT NULL DEFAULT nextval('my_shop.my_shop_id_seq') primary key,
    name        text             NOT NULL,
    description text             NOT NULL,
    price       double precision NOT NULL
);
