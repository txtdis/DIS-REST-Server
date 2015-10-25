begin;

\copy item_family from '/Users/txtDIS/db-convert/data/item_family.txt'(delimiter '|');
SELECT setval('item_family_id_seq', max(id)) FROM item_family;
 
\copy item_tree from '/Users/txtDIS/db-convert/data/item_tree.txt'(delimiter '|');
SELECT setval('item_tree_id_seq', max(id)) FROM item_tree;

\copy item from '/Users/txtDIS/db-convert/data/item.txt'(delimiter '|');
SELECT setval('item_id_seq', max(id)) FROM item;

\copy qty_per_uom from '/Users/txtDIS/db-convert/data/qty_per_uom.txt'(delimiter '|');
SELECT setval('qty_per_uom_id_seq', max(id)) FROM qty_per_uom;

\copy price from '/Users/txtDIS/db-convert/data/pricing.txt'(delimiter '|');
SELECT setval('price_id_seq', max(id)) FROM price;

\copy volume_discount from '/Users/txtDIS/db-convert/data/volume_discount.txt'(delimiter '|');
SELECT setval('volume_discount_id_seq', max(id)) FROM volume_discount;

\copy customer from '/Users/txtDIS/db-convert/data/customer.txt'(delimiter '|');
SELECT setval('customer_id_seq', max(id)) FROM customer;

\copy credit_detail from '/Users/txtDIS/db-convert/data/credit_detail.txt'(delimiter '|');
SELECT setval('credit_detail_id_seq', max(id)) FROM credit_detail;

\copy discount from '/Users/txtDIS/db-convert/data/customer_discount.txt'(delimiter '|');
SELECT setval('discount_id_seq', max(id)) FROM discount;

\copy route from '/Users/txtDIS/db-convert/data/route.txt'(delimiter '|');
SELECT setval('route_id_seq', max(id)) FROM route;

\copy routing from '/Users/txtDIS/db-convert/data/routing.txt'(delimiter '|');
SELECT setval('routing_id_seq', max(id)) FROM routing;

\copy booking from '/Users/txtDIS/db-convert/data/booking.txt'(delimiter '|');
SELECT setval('booking_id_seq', max(id)) FROM booking;

\copy invoice from '/Users/txtDIS/db-convert/data/invoicing.txt'(delimiter '|');
SELECT setval('invoice_id_seq', max(id)) FROM invoice;

\copy sold_detail from '/Users/txtDIS/db-convert/data/booking_detail.txt'(delimiter '|');
SELECT setval('sold_detail_id_seq', max(id)) FROM sold_detail;

\copy booking_discount from '/Users/txtDIS/db-convert/data/booking_discount.txt'(delimiter '|');

\copy invoice_discount from '/Users/txtDIS/db-convert/data/invoicing_discount.txt'(delimiter '|');

-- delete from remittance;
-- \copy remittance from '/Users/txtDIS/db-convert/data/remittance.txt'(delimiter '|');
-- SELECT setval('remittance_id_seq', max(id)) FROM remittance;

-- delete from remittance_detail;
-- \copy remittance_detail from '/Users/txtDIS/db-convert/data/remittance_detail.txt'(delimiter '|');
-- SELECT setval('remittance_detail_id_seq', max(id)) FROM remittance_detail;


end;

--\i '/Users/txtDIS/db-convert/sql/copyDB.sql';