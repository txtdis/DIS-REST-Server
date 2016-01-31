BEGIN;

COPY item_family FROM '/Users/txtDIS/db-convert/data/item_family.csv'(FORMAT 'csv', DELIMITER '|', HEADER);

SELECT setval ('item_family_id_seq', max (id)) FROM item_family;

COPY item_tree FROM '/Users/txtDIS/db-convert/data/item_tree.csv'(FORMAT 'csv', DELIMITER '|', HEADER);

SELECT setval ('item_tree_id_seq', max (id)) FROM item_tree;

COPY item FROM '/Users/txtDIS/db-convert/data/item.csv'(FORMAT 'csv', DELIMITER '|', HEADER);

SELECT setval ('item_id_seq', max (id)) FROM item;

COPY qty_per_uom FROM '/Users/txtDIS/db-convert/data/qty_per_uom.csv'(FORMAT 'csv', DELIMITER '|', HEADER);

SELECT setval ('qty_per_uom_id_seq', max (id)) FROM qty_per_uom;

COPY price FROM '/Users/txtDIS/db-convert/data/pricing.csv'(FORMAT 'csv', DELIMITER '|', HEADER);

SELECT setval ('price_id_seq', max (id)) FROM price;

COPY volume_discount FROM '/Users/txtDIS/db-convert/data/volume_discount.csv'(FORMAT 'csv', DELIMITER '|', HEADER);

SELECT setval ('volume_discount_id_seq', max (id)) FROM volume_discount;

COPY customer FROM '/Users/txtDIS/db-convert/data/customer.csv'(FORMAT 'csv', DELIMITER '|', HEADER);

SELECT setval ('customer_id_seq', max (id)) FROM customer;

COPY credit_detail FROM '/Users/txtDIS/db-convert/data/credit_detail.csv'(FORMAT 'csv', DELIMITER '|', HEADER);

SELECT setval ('credit_detail_id_seq', max (id)) FROM credit_detail;

COPY discount FROM '/Users/txtDIS/db-convert/data/customer_discount.csv'(FORMAT 'csv', DELIMITER '|', HEADER);

SELECT setval ('discount_id_seq', max (id)) FROM discount;

COPY route FROM '/Users/txtDIS/db-convert/data/route.csv'(FORMAT 'csv', DELIMITER '|', HEADER);

SELECT setval ('route_id_seq', max (id)) FROM route;

INSERT INTO route (name, created_by, created_on)
     VALUES ('PRE-SELL 1', 'SYSGEN', current_timestamp);

COPY routing FROM '/Users/txtDIS/db-convert/data/routing.csv'(FORMAT 'csv', DELIMITER '|', HEADER);

SELECT setval ('routing_id_seq', max (id)) FROM routing;

COPY billing FROM '/Users/txtDIS/db-convert/data/billing.csv'(FORMAT 'csv', DELIMITER '|', HEADER);

SELECT setval ('billing_id_seq', max (booking_id)) FROM billing;

COPY billing_detail FROM '/Users/txtDIS/db-convert/data/billing_detail.csv'(FORMAT 'csv', DELIMITER '|', HEADER);

SELECT setval ('billing_detail_id_seq', max (id)) FROM billing_detail;

COPY billing_discount FROM '/Users/txtDIS/db-convert/data/billing_discount.csv'(FORMAT 'csv', DELIMITER '|', HEADER);

COPY remittance FROM '/Users/txtDIS/db-convert/data/remittance.csv'(FORMAT 'csv', DELIMITER '|', HEADER);

SELECT setval ('remittance_id_seq', max (id)) FROM remittance;

COPY remittance_detail FROM '/Users/txtDIS/db-convert/data/remittance_detail.csv'(FORMAT 'csv', DELIMITER '|', HEADER);

SELECT setval ('remittance_detail_id_seq', max (id)) FROM remittance_detail;

COPY invoice_booklet FROM '/Users/txtDIS/db-convert/data/invoice_booklet.csv'(FORMAT 'csv', DELIMITER '|', HEADER);

SELECT setval ('invoice_booklet_id_seq', max (id)) FROM invoice_booklet;

CREATE TEMP TABLE temp_routing
(
   customer_id   int,
   route_id      int
)
ON COMMIT DROP;

COPY temp_routing FROM '/Users/txtDIS/db-convert/data/routing_update.csv'(FORMAT 'csv', DELIMITER '|');

INSERT INTO routing (created_by,
                     created_on,
                     start_date,
                     route_id,
                     customer_id)
   SELECT 'SYSGEN',
          current_timestamp,
          current_date,
          route_id,
          customer_id
     FROM temp_routing;

INSERT INTO account (created_by,
                     created_on,
                     seller,
                     start_date,
                     route_id)
     VALUES ('SYSGEN',
             current_timestamp,
             'NOEL',
             '2014-01-01',
             4),
            ('SYSGEN',
             current_timestamp,
             'MARIVIC',
             '2014-01-01',
             7),
            ('SYSGEN',
             current_timestamp,
             'JOSEPHINE',
             '2014-11-17',
             5),
            ('SYSGEN',
             current_timestamp,
             'HOMER',
             '2015-06-01',
             8),
            ('SYSGEN',
             current_timestamp,
             'ROELYN',
             '2014-08-27',
             1);

INSERT INTO stock_take (id,
                        created_by,
                        created_on,
                        stock_take_date,
                        checker_username,
                        taker_username,
                        warehouse_id)
     VALUES (0,
             'SYSGEN',
             current_timestamp,
             '2015-11-29',
             'MARIVIC',
             'SHERYL',
             1);

CREATE TEMP TABLE temp_count
(
   item_id   int,
   qty       numeric (10, 4)
)
ON COMMIT DROP;

COPY temp_count FROM '/Users/txtDIS/db-convert/data/inventory.csv'(FORMAT 'csv', DELIMITER '|', HEADER);

INSERT INTO stock_take_detail (qty,
                               quality,
                               uom,
                               item_id,
                               stock_take_id)
   SELECT qty, 0, 0, item_id, 0 FROM temp_count;

INSERT INTO stocks (good_qty, item_id)
   SELECT qty, item_id FROM temp_count;


END;
--\i '/Users/txtDIS/db-convert/sql/copyDB.sql';
