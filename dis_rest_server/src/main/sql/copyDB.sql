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

COPY routing FROM '/Users/txtDIS/db-convert/data/routing.csv'(FORMAT 'csv', DELIMITER '|', HEADER);

SELECT setval ('routing_id_seq', max (id)) FROM routing;

COPY billing FROM '/Users/txtDIS/db-convert/data/billing.csv'(FORMAT 'csv', DELIMITER '|', HEADER);

SELECT setval ('billing_id_seq', max (id)) FROM billing;

COPY billing_detail FROM '/Users/txtDIS/db-convert/data/billing_detail.csv'(FORMAT 'csv', DELIMITER '|', HEADER);

SELECT setval ('billing_detail_id_seq', max (id)) FROM billing_detail;

COPY billing_discount FROM '/Users/txtDIS/db-convert/data/billing_discount.csv'(FORMAT 'csv', DELIMITER '|', HEADER);

COPY remittance FROM '/Users/txtDIS/db-convert/data/remittance.csv'(FORMAT 'csv', DELIMITER '|', HEADER);

SELECT setval ('remittance_id_seq', max (id)) FROM remittance;

COPY remittance_detail FROM '/Users/txtDIS/db-convert/data/remittance_detail.csv'(FORMAT 'csv', DELIMITER '|', HEADER);

SELECT setval ('remittance_detail_id_seq', max (id)) FROM remittance_detail;

END;

--\i '/Users/txtDIS/db-convert/sql/copyDB.sql';
