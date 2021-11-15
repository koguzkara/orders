create SEQUENCE  IF NOT EXISTS hibernate_sequence  AS BIGINT INCREMENT  BY 1 Start WITH 1;

create table products(
                           id integer PRIMARY KEY,
                           name varchar(64) unique not null,
                           description varchar(128) not null,
                           is_active boolean default true,
                           created timestamp not null,
                           updated timestamp not null
);

create index inx_product_name on products(name);


create table product_price(
    id integer PRIMARY KEY,
    product_id  integer  not null,
    amount  numeric(12,4) not null,
    currency varchar(10) not null,
    is_active boolean default true,
    start_date timestamp  not null,
    end_date  timestamp
);

Alter table product_price add  CONSTRAINT  FK_PRODUCT_PRICE_ID FOREIGN KEY (product_id) REFERENCES Products(id);

create table orders(
    id integer  PRIMARY KEY,
    email varchar(64) not null,
    order_date timestamp not null
);

create index inx_order_date on orders(order_date);

create table order_product(
    id integer  PRIMARY KEY,
    size integer not null,
    order_id integer not null,
    product_price_id integer not null
);


alter table order_product add CONSTRAINT FK_ORDER_PRODUCT_ID FOREIGN KEY (order_id)
    REFERENCES  orders(id);


alter table order_product add CONSTRAINT FK_ORDER_PRODUCT_PRICE_ID FOREIGN KEY (product_price_id)
    REFERENCES  product_price(id);




