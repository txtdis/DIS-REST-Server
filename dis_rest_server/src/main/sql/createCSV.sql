BEGIN;

--COPY (

  SELECT -id,
         'SYSGEN' AS created_by,
         time_stamp AS created_on,
         name,
         tier_id AS tier
    FROM item_family
   WHERE id <> 0
ORDER BY id
--) TO '/Users/txtDIS/db-convert/data/item_family.csv' (FORMAT 'csv', DELIMITER '|', HEADER)
;
--COPY (

  SELECT row_number () OVER (ORDER BY time_stamp) AS id,
         'SYSGEN' AS created_by,
         time_stamp AS created_on,
         -child_id AS family_id,
         -parent_id AS parent_id
    FROM item_tree
   WHERE parent_id < 0 AND child_id < 0
ORDER BY id
--) TO '/Users/txtDIS/db-convert/data/item_tree.csv' (FORMAT 'csv', DELIMITER '|', HEADER)
;
DELETE FROM item_tree
      WHERE child_id = 696 AND parent_id = -244;

DELETE FROM item_tree
      WHERE child_id = 675 AND parent_id = -23;

DELETE FROM item_tree
      WHERE child_id = 847 AND parent_id = -283;

DELETE FROM item_tree
      WHERE child_id = 845 AND parent_id = -237;

--COPY (

  SELECT h.id,
         CASE
            WHEN h.user_id IN ('txtdis', 'postgres') THEN 'SYSGEN'
            ELSE upper (h.user_id)
         END
            AS created_by,
         h.time_stamp AS created_on,
         CASE
            WHEN short_id IS NULL THEN ('DISABLED ' || h.id)
            ELSE short_id
         END
            AS name,
         NULL AS deactivated_by,
         NULL AS deactivated_on,
         name AS description,
         not_discounted,
         (type_id - 1) AS type,
         unspsc_id AS vendor_id,
         -parent_id AS family_id
    FROM item_header h LEFT JOIN item_tree t ON h.id = t.child_id
ORDER BY id
--) TO '/Users/txtDIS/db-convert/data/item.csv' (FORMAT 'csv', DELIMITER '|', HEADER);
;
--COPY (

  SELECT row_number () OVER (ORDER BY item_id, uom) AS id,
         CASE
            WHEN user_id IN ('txtdis', 'postgres') THEN 'SYSGEN'
            ELSE upper (user_id)
         END
            AS createdby,
         time_stamp AS createdon,
         buy AS ispurchased,
         qty,
         report AS isreported,
         sell AS issold,
         uom,
         item_id
    FROM qty_per q INNER JOIN item_header AS i ON q.item_id = i.id
ORDER BY id
--) TO '/Users/txtDIS/db-convert/data/qty_per_uom.csv' (FORMAT 'csv', DELIMITER '|', HEADER);
;
DELETE FROM price
      WHERE price IS NULL;

UPDATE price
   SET user_id = 'kimberly'
 WHERE item_id = 518;

UPDATE price
   SET user_id = 'sheryl'
 WHERE item_id = 115 AND start_date = '2014-04-06';

UPDATE price
   SET user_id = 'sheryl'
 WHERE item_id = 302 AND start_date = '2014-07-01';

UPDATE price
   SET user_id = 'sheryl'
 WHERE item_id = 647 AND start_date = '2014-01-22';

DELETE FROM price
      WHERE item_id IN (SELECT id
                          FROM ITEM_HEADER
                         WHERE type_id = 6);

INSERT INTO price (item_id,
                   price,
                   start_date,
                   tier_id)
   SELECT id,
          -1 AS price,
          cast (time_stamp AS date),
          0 AS tier_id
     FROM ITEM_HEADER
    WHERE type_id = 6;

INSERT INTO price (item_id,
                   price,
                   start_date,
                   tier_id)
   SELECT id,
          -1 AS price,
          cast (time_stamp AS date),
          1 AS tier_id
     FROM ITEM_HEADER
    WHERE type_id = 6;

INSERT INTO price (item_id,
                   price,
                   start_date,
                   tier_id)
   SELECT id,
          -1 AS price,
          cast (time_stamp AS date),
          2 AS tier_id
     FROM ITEM_HEADER
    WHERE type_id = 6;

INSERT INTO price (item_id,
                   price,
                   start_date,
                   tier_id)
   SELECT id,
          -1 AS price,
          cast (time_stamp AS date),
          3 AS tier_id
     FROM ITEM_HEADER
    WHERE type_id = 6;

INSERT INTO price (item_id,
                   price,
                   start_date,
                   tier_id)
   SELECT id,
          -1 AS price,
          cast (time_stamp AS date),
          4 AS tier_id
     FROM ITEM_HEADER
    WHERE type_id = 6;

--COPY (

  SELECT row_number () OVER (ORDER BY time_stamp, item_id, tier_id) AS id,
         CASE
            WHEN user_id IN ('txtdis', 'postgres') THEN 'SYSGEN'
            ELSE upper (user_id)
         END
            AS created_by,
         time_stamp AS created_on,
         price,
         start_date,
         CASE WHEN tier_id = 0 THEN 5 WHEN tier_id = 1 THEN NULL ELSE 9 END
            AS channellimit_id,
         CASE WHEN tier_id = 0 THEN 1 WHEN tier_id = 1 THEN 2 ELSE 3 END
            AS type_id,
         item_id
    FROM price
   WHERE tier_id IN (0, 1, 3)
ORDER BY id
--) TO '/Users/txtDIS/db-convert/data/pricing.csv' (FORMAT 'csv', DELIMITER '|', HEADER)
;
--COPY (

  SELECT row_number () OVER (ORDER BY time_stamp) AS id,
         CASE
            WHEN user_id IN ('txtdis', 'postgres') THEN 'SYSGEN'
            ELSE upper (user_id)
         END
            AS created_by,
         time_stamp AS created_on,
         per_qty AS cutoff,
         less AS discount,
         start_date,
         0 AS type,
         uom,
         NULL AS channel_limit_id,
         item_id
    FROM volume_discount
ORDER BY id
--) TO '/Users/txtDIS/db-convert/data/volume_discount.csv' (FORMAT 'csv', DELIMITER '|', HEADER)
;
UPDATE customer_header
   SET name = 'DUPLICATE OF 227'
 WHERE id = 149;

UPDATE customer_header
   SET name = 'DUPLICATE OF 462'
 WHERE id = 71;

UPDATE customer_header
   SET name = 'DUPLICATE OF 278'
 WHERE id = 512;

UPDATE customer_header
   SET name = 'DUPLICATE OF 331'
 WHERE id = 577;

UPDATE customer_header
   SET name = 'DUPLICATE OF 843'
 WHERE id = 839;

UPDATE customer_header
   SET name = 'DUPLICATE OF 167'
 WHERE id = 847;

UPDATE customer_header
   SET name = 'DUPLICATE OF 677'
 WHERE id = 930;

UPDATE customer_header
   SET name = 'LITAS STORE'
 WHERE id = 377;

UPDATE customer_header
   SET name = 'ROSE SFDM'
 WHERE id = 691;

UPDATE customer_header
   SET name = 'ROLAND SHORTAGE'
 WHERE id = 816;

UPDATE customer_header
   SET user_id = 'postgres'
 WHERE id IN (432, 532, 530);

UPDATE address
   SET district = 18
 WHERE district = 1 AND street LIKE '%PABAHAY%';

UPDATE address
   SET district = 40
 WHERE district = 1 AND street LIKE '%BRIGHT%';

UPDATE address
   SET district = 35
 WHERE customer_id = 712;

UPDATE address
   SET district = 36
 WHERE customer_id = 716;

UPDATE address
   SET district = 34
 WHERE customer_id = 756;

DELETE FROM customer_header
      WHERE id = 827;

DELETE FROM customer_header
      WHERE name IS NULL;

DELETE FROM discount
      WHERE customer_id = 863;

DELETE FROM credit
      WHERE customer_id = 863;

DELETE FROM account
      WHERE customer_id = 863;

DELETE FROM customer_header
      WHERE id = 863;

DELETE FROM discount
      WHERE customer_id = 1063;

DELETE FROM credit
      WHERE customer_id = 1063;

DELETE FROM account
      WHERE customer_id = 1063;

DELETE FROM customer_header
      WHERE id = 1063;

ALTER TABLE phone_number ALTER number TYPE text;

--COPY (

  SELECT DISTINCT
         CASE WHEN ch.id = 0 THEN 574 ELSE ch.id END AS id,
         CASE
            WHEN ch.user_id IN ('txtdis', 'postgres') THEN 'SYSGEN'
            ELSE upper (ch.user_id)
         END
            AS createdby,
         ch.time_stamp AS createdon,
         CASE
            WHEN ch.name IN ('CUEVAS BAKERY',
                             'CENON',
                             'CAMILLE STORE',
                             'EMMA STORE',
                             'JENNY STORE',
                             'CITA STORE',
                             'AIDA STORE',
                             'JOSEPHINE STORE',
                             'CORA STORE',
                             'SUSAN',
                             'JOSIE',
                             'LIZA STORE',
                             'GINA STORE',
                             'LILY STORE',
                             'MHAIKEL STORE',
                             'HELEN STORE',
                             'LORNA STORE',
                             'MILA STORE',
                             'LEMON HIPOLITO',
                             'ROSE STORE',
                             'LOURDES STORE',
                             'CARMEN',
                             'CUEVAS BREAD',
                             'JASCON',
                             'SACOPLA',
                             'JERRY',
                             'TINDAHAN SA KANTO',
                             'ZENY STORE',
                             'MALOU STORE',
                             'GRACE STORE',
                             'SALLY STORE',
                             'MICHELLE',
                             'FLOR',
                             'DINO',
                             'ROSE',
                             'MINA',
                             'RUDY STORE',
                             'JANETH',
                             'JOY',
                             'LILIBETH STORE',
                             'VICKY STORE',
                             'MHAIRA',
                             'CLARISE',
                             'JULIAN',
                             'ALONA',
                             'LINDA STORE')
            THEN
                  ch.name
               || ' ('
               || (CASE
                      WHEN brgy.name IS NULL THEN 'UNKNOWN'
                      ELSE brgy.name
                   END)
               || ') '
            ELSE
               ch.name
         END
            AS name,
         NULL AS deactivated_by,
         NULL AS deactivated_on,
         cd.name AS contact_name,
         cd.surname AS credit_contact_surname,
         designation AS contact_title,
         CASE WHEN number IS NOT NULL THEN '+63' || number ELSE number END
            AS mobile,
         street,
         CASE
            WHEN ch.type_id = 10
            THEN
               (CASE WHEN ch.id IN (559, 560) THEN 0 ELSE 3 END)
            WHEN ch.type_id = 12
            THEN
               2
            ELSE
               1
         END
            AS type,
         2 AS visit_frequency,
         CASE WHEN ch.type_id = 8 THEN 2 ELSE NULL END
            AS alternate_pricing_type_id,
         CASE
            WHEN district IS NULL AND city = 2 THEN 27
            WHEN district IS NULL THEN 34
            ELSE district
         END
            AS barangay_id,
         CASE
            WHEN ch.type_id IN (1, 9, 15)
            THEN
               5
            WHEN ch.type_id IN (0,
                                3,
                                10,
                                11,
                                12,
                                13,
                                14,
                                16)
            THEN
               7
            WHEN ch.type_id = 7
            THEN
               8
            WHEN ch.type_id = 8
            THEN
               9
            ELSE
               ch.type_id
         END
            AS channel_id,
         CASE WHEN city IS NULL THEN 33 ELSE city END AS city_id,
         branch_of AS parent_id,
         CASE
            WHEN ch.type_id IN (9, 15)
            THEN
               1
            WHEN ch.type_id IN (1,
                                10,
                                11,
                                13,
                                14)
            THEN
               NULL
            WHEN ch.type_id = 8
            THEN
               3
            ELSE
               2
         END
            AS primary_pricing_type_id,
         CASE WHEN province IS NULL THEN 1 ELSE province END AS province_id
    FROM customer_header AS ch
         LEFT JOIN address AS ad ON ch.id = ad.customer_id
         LEFT JOIN area AS brgy ON ad.district = brgy.id
         LEFT JOIN credit AS cr ON ch.id = cr.customer_id
         LEFT JOIN contact_detail AS cd
            ON ch.id = cd.customer_id AND cd.customer_id <> 0
         LEFT JOIN phone_number AS ph ON cd.id = ph.contact_id
ORDER BY id
--) TO '/Users/txtDIS/db-convert/data/customer.csv' (FORMAT 'csv', DELIMITER '|', HEADER)
;
--COPY (

  SELECT row_number () OVER (ORDER BY customer_id, start_date DESC) AS id,
         CASE
            WHEN user_id IN ('txtdis', 'postgres') THEN 'SYSGEN'
            ELSE upper (user_id)
         END
            AS created_by,
         time_stamp AS created_on,
         CASE
            WHEN credit_limit IS NULL
            THEN
               CASE WHEN term > 0 THEN 1000 ELSE 0 END
            ELSE
               credit_limit
         END
            AS credit_limit,
         CASE WHEN grace_period IS NULL THEN 0 ELSE grace_period END,
         start_date,
         term,
         customer_id
    FROM credit
ORDER BY id
--) TO '/Users/txtDIS/db-convert/data/credit_detail.csv' (FORMAT 'csv', DELIMITER '|', HEADER)
;
--COPY (

  SELECT row_number ()
            OVER (ORDER BY
                     time_stamp,
                     customer_id,
                     family_id,
                     level)
            AS id,
         user_id AS created_by,
         time_stamp AS created_on,
         level,
         per_cent AS percent,
         start_date,
         -family_id AS family_limit_id,
         customer_id
    FROM ( (SELECT time_stamp,
                   1 AS level,
                   CASE WHEN level_1 = 0 THEN NULL ELSE level_1 END AS per_cent,
                   start_date,
                   CASE
                      WHEN user_id = 'txtdis' OR user_id = 'postgres'
                      THEN
                         'SYSGEN'
                      ELSE
                         upper (user_id)
                   END
                      AS user_id,
                   family_id,
                   customer_id
              FROM discount)
          UNION                                                          --ALL
          SELECT time_stamp,
                 2 AS level,
                 level_2 AS per_cent,
                 start_date,
                 CASE
                    WHEN user_id = 'txtdis' OR user_id = 'postgres'
                    THEN
                       'SYSGEN'
                    ELSE
                       upper (user_id)
                 END
                    AS user_id,
                 family_id,
                 customer_id
            FROM discount
           WHERE level_2 > 0) AS u
ORDER BY id
--) TO '/Users/txtDIS/db-convert/data/customer_discount.csv' (FORMAT 'csv', DELIMITER '|', HEADER)
;
--COPY (

  SELECT id + 1 AS id,
         CASE
            WHEN user_id IN ('txtdis', 'postgres') THEN 'SYSGEN'
            ELSE upper (user_id)
         END
            AS created_by,
         time_stamp AS created_on,
         name
    FROM route
ORDER BY id
--) TO '/Users/txtDIS/db-convert/data/route.csv' (FORMAT 'csv', DELIMITER '|', HEADER)
;
--COPY (

  SELECT row_number () OVER (ORDER BY time_stamp) AS id,
         CASE
            WHEN user_id IN ('txtdis', 'postgres') THEN 'SYSGEN'
            ELSE upper (user_id)
         END
            AS created_by,
         time_stamp AS created_on,
         start_date,
         route_id + 1 AS route_id,
         customer_id
    FROM account
   WHERE route_id <> -1
ORDER BY id
--) TO '/Users/txtDIS/db-convert/data/routing.csv' (FORMAT 'csv', DELIMITER '|', HEADER)
;
--COPY (

WITH                                                               --RECURSIVE
    parent_child                                       --(child_id, parent_id)
     AS (SELECT it.child_id, it.parent_id
           FROM item_tree AS it
         UNION                                                           --ALL
         SELECT parent_child.child_id, it.parent_id
           FROM item_tree it
                JOIN parent_child ON it.child_id = parent_child.parent_id),
     family
     AS (SELECT                                    --DISTINCT ON (sd.sales_id)
               sd.sales_id,
                last_value (pc.parent_id)
                   OVER (PARTITION BY child_id ORDER BY parent_id ASC)
                   AS family_id
           FROM sales_detail AS sd
                INNER JOIN parent_child AS pc ON sd.item_id = pc.child_id
          WHERE sd.line_id = 1),
     booking_discount
     AS (SELECT                                     --DISTINCT ON (s.sales_id)
               s.sales_id AS id,
                last_value (level_1)
                   OVER (PARTITION BY s.sales_id ORDER BY d.start_date DESC)
                   AS level_1,
                last_value (level_2)
                   OVER (PARTITION BY s.sales_id ORDER BY d.start_date DESC)
                   AS level_2
           FROM sales_header AS s
                INNER JOIN family AS f ON s.sales_id = f.sales_id
                INNER JOIN discount AS d
                   ON     s.customer_id = d.customer_id
                      AND f.family_id = d.family_id
                      AND d.start_date <= s.sales_date),
     booking_detail
     AS (SELECT                        --DISTINCT ON (sh.sales_id, sh.item_id)
               sh.sales_id,
                sh.qty,
                  (  (CASE
                         WHEN abs (sh.item_id) IN (328, 483)
                         THEN
                              (    last_value (
                                      pd.price)
                                   OVER (PARTITION BY sh.sales_id, sh.item_id
                                         ORDER BY pd.start_date DESC)
                                 * 3
                               - 5)
                            / 3
                         ELSE
                            (CASE
                                WHEN sh.uom = 5
                                THEN
                                   1
                                ELSE
                                   (CASE
                                       WHEN (last_value (
                                                p.price)
                                             OVER (
                                                PARTITION BY sh.sales_id,
                                                             sh.item_id
                                                ORDER BY p.start_date DESC))
                                               IS NULL
                                       THEN
                                          (CASE
                                              WHEN pd.price IS NULL
                                              THEN
                                                 0
                                              ELSE
                                                 last_value (
                                                    pd.price)
                                                 OVER (
                                                    PARTITION BY sh.sales_id,
                                                                 sh.item_id
                                                    ORDER BY
                                                       pd.start_date DESC)
                                           END)
                                       ELSE
                                          last_value (
                                             p.price)
                                          OVER (
                                             PARTITION BY sh.sales_id,
                                                          sh.item_id
                                             ORDER BY p.start_date DESC)
                                    END)
                             END)
                      END)
                   - CASE
                        WHEN abs (sh.item_id) IN (328, 483) AND sh.qty > 3
                        THEN
                           5 / 3
                        ELSE
                           0
                     END)
                * qp.qty
                   AS price
           FROM sales_detail AS sh
                INNER JOIN sales_header AS s ON sh.sales_id = s.sales_id
                INNER JOIN qty_per AS qp
                   ON abs (sh.item_id) = qp.item_id AND sh.uom = qp.uom
                INNER JOIN customer_header AS c ON c.id = s.customer_id
                INNER JOIN channel_price_tier AS cpt
                   ON c.type_id = cpt.channel_id
                LEFT JOIN price AS p
                   ON     cpt.tier_id = p.tier_id
                      AND p.start_date <= s.sales_date
                      AND p.item_id = abs (sh.item_id)
                LEFT JOIN price AS pd
                   ON     pd.tier_id = 1
                      AND pd.start_date <= s.sales_date
                      AND pd.item_id = abs (sh.item_id)
          WHERE s.sales_date >= '2014-01-01'),
     subtotal
     AS (  SELECT sales_id AS id, sum (qty * price) AS subtotal
             FROM booking_detail
         GROUP BY sales_id),
     net
     AS (SELECT s.id,
                round (
                     subtotal
                   * (  1
                      -   CASE WHEN level_1 IS NULL THEN 0 ELSE level_1 END
                        / 100)
                   * (  1
                      -   CASE WHEN level_2 IS NULL THEN 0 ELSE level_2 END
                        / 100),
                   2)
                   AS value
           FROM subtotal AS s LEFT JOIN booking_discount AS d ON s.id = d.id)
  SELECT                                           --DISTINCT ON (sh.sales_id)
        sh.sales_id AS id,
         CASE
            WHEN    sh.user_id IN ('txtdis', 'postgres', 'vier')
                 OR sh.sales_id IN (10264, 15950, 13800)
                 OR sh.user_id = '7'
            THEN
               'SYSGEN'
            ELSE
               upper (sh.user_id)
         END
            AS created_by,
         sh.time_stamp AS created_on,
         NULL AS audited_by,
         NULL AS audited_on,
         NULL AS is_valid,
           sales_date
         + CASE
              WHEN last_value (cd.term)
                   OVER (PARTITION BY sh.sales_id ORDER BY cd.start_date DESC)
                      IS NULL
              THEN
                 0
              ELSE
                 last_value (cd.term)
                    OVER (PARTITION BY sh.sales_id ORDER BY cd.start_date DESC)
           END
            AS due_date,
         round (subtotal, 2) AS gross,
         sales_date AS order_date,
         NULL AS remarks,
         round (value, 2) AS total,
         CASE
            WHEN sh.user_id IN ('txtdis', 'postgres', 'vier') THEN 'SYSGEN'
            ELSE upper (spo.user_id)
         END
            AS printed_by,
         spo.time_stamp AS printed_on,
         CASE
            WHEN sh.customer_id = 0 OR sh.customer_id IS NULL THEN 574
            ELSE sh.customer_id
         END
            AS customer_id,
         NULL AS pick_list_id
    FROM sales_header AS sh
         LEFT JOIN sales_print_out AS spo ON sh.sales_id = spo.sales_id
         LEFT JOIN account AS acc
            ON     sh.customer_id = acc.customer_id
               AND acc.start_date <= sh.sales_date
         LEFT JOIN subtotal AS st ON st.id = sh.sales_id
         LEFT JOIN net AS n ON n.id = sh.sales_id
         LEFT JOIN credit AS cd
            ON     sh.customer_id = cd.customer_id
               AND cd.start_date <= sh.sales_date
   WHERE sh.sales_date >= '2014-01-01'
ORDER BY id
--) TO '/Users/txtDIS/db-convert/data/booking.csv' (FORMAT 'csv', DELIMITER '|', HEADER)
;
--COPY (


WITH                                                               --RECURSIVE
    parent_child                                       --(child_id, parent_id)
     AS (SELECT it.child_id, it.parent_id
           FROM item_tree AS it
         UNION                                                           --ALL
         SELECT parent_child.child_id, it.parent_id
           FROM item_tree it
                JOIN parent_child ON it.child_id = parent_child.parent_id),
     family
     AS (SELECT                       --DISTINCT ON (sh.invoice_id, sh.series)
               sh.invoice_id,
                sh.series,
                last_value (pc.parent_id)
                   OVER (PARTITION BY child_id ORDER BY parent_id ASC)
                   AS family_id
           FROM invoice_detail AS sd
                INNER JOIN invoice_header AS sh
                   ON sd.invoice_id = sh.invoice_id AND sd.series = sh.series
                INNER JOIN parent_child AS pc ON sd.item_id = pc.child_id
                INNER JOIN discount AS d
                   ON     d.customer_id = sh.customer_id
                      AND d.family_id = pc.parent_id
          WHERE sd.line_id = 1
         UNION                                                           --ALL
         SELECT                                 --DISTINCT ON (sh.delivery_id)
               -sh.delivery_id AS invoice_id,
                cast (NULL AS text) AS series,
                last_value (pc.parent_id)
                   OVER (PARTITION BY child_id ORDER BY parent_id ASC)
                   AS family_id
           FROM delivery_detail AS sd
                INNER JOIN delivery_header AS sh
                   ON sd.delivery_id = sh.delivery_id
                INNER JOIN parent_child AS pc ON sd.item_id = pc.child_id
                INNER JOIN discount AS d
                   ON     d.customer_id = sh.customer_id
                      AND d.family_id = pc.parent_id
          WHERE sd.line_id = 1),
     billing_discount
     AS (SELECT                         --DISTINCT ON (s.invoice_id, s.series)
               s.invoice_id AS id,
                s.series,
                last_value (
                   level_1)
                OVER (PARTITION BY s.invoice_id, s.series
                      ORDER BY d.start_date DESC)
                   AS level_1,
                last_value (
                   level_2)
                OVER (PARTITION BY s.invoice_id, s.series
                      ORDER BY d.start_date DESC)
                   AS level_2
           FROM invoice_header AS s
                INNER JOIN family AS f
                   ON s.invoice_id = f.invoice_id AND f.series = s.series
                INNER JOIN discount AS d
                   ON     s.customer_id = d.customer_id
                      AND f.family_id = d.family_id
                      AND d.start_date <= s.invoice_date
         UNION                                                           --ALL
         SELECT                                  --DISTINCT ON (s.delivery_id)
               -s.delivery_id AS id,
                cast (NULL AS text) AS series,
                last_value (level_1)
                OVER (PARTITION BY s.delivery_id ORDER BY d.start_date DESC)
                   AS level_1,
                last_value (level_2)
                OVER (PARTITION BY s.delivery_id ORDER BY d.start_date DESC)
                   AS level_2
           FROM delivery_header AS s
                INNER JOIN family AS f ON -s.delivery_id = f.invoice_id
                INNER JOIN discount AS d
                   ON     s.customer_id = d.customer_id
                      AND f.family_id = d.family_id
                      AND d.start_date <= s.delivery_date),
     billing_detail
     AS (SELECT           --DISTINCT ON (sh.invoice_id, sh.series, sh.item_id)
               sh.invoice_id,
                sh.series,
                sh.qty,
                  (  (CASE
                         WHEN abs (sh.item_id) IN (328, 483)
                         THEN
                              (    last_value (
                                      pd.price)
                                   OVER (
                                      PARTITION BY sh.invoice_id,
                                                   sh.series,
                                                   sh.item_id
                                      ORDER BY pd.start_date DESC)
                                 * 3
                               - 5)
                            / 3
                         ELSE
                            CASE
                               WHEN sh.uom = 5
                               THEN
                                  1
                               ELSE
                                  CASE
                                     WHEN (last_value (
                                              p.price)
                                           OVER (
                                              PARTITION BY sh.invoice_id,
                                                           sh.series,
                                                           sh.item_id
                                              ORDER BY p.start_date DESC))
                                             IS NULL
                                     THEN
                                        CASE
                                           WHEN pd.price IS NULL
                                           THEN
                                              0
                                           ELSE
                                              last_value (
                                                 pd.price)
                                              OVER (
                                                 PARTITION BY sh.invoice_id,
                                                              sh.series,
                                                              sh.item_id
                                                 ORDER BY pd.start_date DESC)
                                        END
                                     ELSE
                                        last_value (
                                           p.price)
                                        OVER (
                                           PARTITION BY sh.invoice_id,
                                                        sh.series,
                                                        sh.item_id
                                           ORDER BY p.start_date DESC)
                                  END
                            END
                      END)
                   - CASE
                        WHEN abs (sh.item_id) IN (328, 483) AND sh.qty > 3
                        THEN
                           5 / 3
                        ELSE
                           0
                     END)
                * qp.qty
                   AS price
           FROM invoice_detail AS sh
                INNER JOIN invoice_header AS s
                   ON sh.invoice_id = s.invoice_id AND sh.series = s.series
                INNER JOIN qty_per AS qp
                   ON abs (sh.item_id) = qp.item_id AND sh.uom = qp.uom
                INNER JOIN customer_header AS c ON c.id = s.customer_id
                INNER JOIN channel_price_tier AS cpt
                   ON c.type_id = cpt.channel_id
                LEFT JOIN price AS p
                   ON     cpt.tier_id = p.tier_id
                      AND p.start_date <= s.invoice_date
                      AND p.item_id = abs (sh.item_id)
                LEFT JOIN price AS pd
                   ON     pd.tier_id = 1
                      AND pd.start_date <= s.invoice_date
                      AND pd.item_id = abs (sh.item_id)
          WHERE s.invoice_date >= '2014-01-01'
         UNION                                                           --ALL
         SELECT                     --DISTINCT ON (sh.delivery_id, sh.item_id)
               -sh.delivery_id AS invoice_id,
                cast (NULL AS text) AS series,
                sh.qty,
                  (  CASE
                        WHEN abs (sh.item_id) IN (328, 483)
                        THEN
                             (    last_value (
                                     pd.price)
                                  OVER (
                                     PARTITION BY sh.delivery_id, sh.item_id
                                     ORDER BY pd.start_date DESC)
                                * 3
                              - 5)
                           / 3
                        ELSE
                           CASE
                              WHEN sh.uom = 5
                              THEN
                                 1
                              ELSE
                                 CASE
                                    WHEN (last_value (
                                             p.price)
                                          OVER (
                                             PARTITION BY sh.delivery_id,
                                                          sh.item_id
                                             ORDER BY p.start_date DESC))
                                            IS NULL
                                    THEN
                                       CASE
                                          WHEN pd.price IS NULL
                                          THEN
                                             0
                                          ELSE
                                             last_value (
                                                pd.price)
                                             OVER (
                                                PARTITION BY sh.delivery_id,
                                                             sh.item_id
                                                ORDER BY pd.start_date DESC)
                                       END
                                    ELSE
                                       last_value (
                                          p.price)
                                       OVER (
                                          PARTITION BY sh.delivery_id,
                                                       sh.item_id
                                          ORDER BY p.start_date DESC)
                                 END
                           END
                     END
                   - CASE
                        WHEN abs (sh.item_id) IN (328, 483) AND sh.qty > 3
                        THEN
                           5 / 3
                        ELSE
                           0
                     END)
                * qp.qty
                   AS price
           FROM delivery_detail AS sh
                INNER JOIN delivery_header AS s
                   ON sh.delivery_id = s.delivery_id
                INNER JOIN qty_per AS qp
                   ON abs (sh.item_id) = qp.item_id AND sh.uom = qp.uom
                INNER JOIN customer_header AS c ON c.id = s.customer_id
                INNER JOIN channel_price_tier AS cpt
                   ON c.type_id = cpt.channel_id
                LEFT JOIN price AS p
                   ON     cpt.tier_id = p.tier_id
                      AND p.start_date <= s.delivery_date
                      AND p.item_id = abs (sh.item_id)
                LEFT JOIN price AS pd
                   ON     pd.tier_id = 1
                      AND pd.start_date <= s.delivery_date
                      AND pd.item_id = abs (sh.item_id)
          WHERE s.delivery_date >= '2014-01-01'),
     subtotal
     AS (  SELECT invoice_id AS id, series, sum (qty * price) AS subtotal
             FROM billing_detail
         GROUP BY invoice_id, series),
     net
     AS (SELECT s.id,
                s.series,
                round (
                     subtotal
                   * (  1
                      -   CASE WHEN level_1 IS NULL THEN 0 ELSE level_1 END
                        / 100)
                   * (  1
                      -   CASE WHEN level_2 IS NULL THEN 0 ELSE level_2 END
                        / 100),
                   2)
                   AS value
           FROM subtotal AS s
                LEFT JOIN billing_discount AS d
                   ON s.id = d.id AND s.series = d.series),
     payment
     AS (  SELECT d.order_id, d.series, sum (d.payment) AS payment
             FROM remit_detail AS d
                  INNER JOIN remit_header AS h ON h.remit_id = d.remit_id
            WHERE h.remit_date <= current_date
         GROUP BY order_id, series),
     billing
     AS (SELECT                       --DISTINCT ON (sh.invoice_id, sh.series)
               CASE
                   WHEN sh.user_id IN ('txtdis', 'postgres', 'vier')
                   THEN
                      'SYSGEN'
                   WHEN sh.user_id IN ('jessa', 'donna')
                   THEN
                      'GLAZEL'
                   ELSE
                      upper (sh.user_id)
                END
                   AS created_by,
                sh.time_stamp AS created_on,
                NULL AS audited_by,
                NULL AS audited_on,
                NULL AS is_valid,
                  CASE
                     WHEN invoice_date IS NULL
                     THEN
                        cast (sh.time_stamp AS date)
                     ELSE
                        invoice_date
                  END
                + CASE
                     WHEN last_value (
                             cd.term)
                          OVER (PARTITION BY sh.invoice_id, sh.series
                                ORDER BY cd.start_date DESC)
                             IS NULL
                     THEN
                        0
                     ELSE
                        last_value (
                           cd.term)
                        OVER (PARTITION BY sh.invoice_id, sh.series
                              ORDER BY cd.start_date DESC)
                  END
                   AS due_date,
                round (subtotal, 2) AS gross,
                CASE
                   WHEN invoice_date IS NULL
                   THEN
                      cast (sh.time_stamp AS date)
                   ELSE
                      invoice_date
                END
                   AS order_date,
                CASE
                   WHEN actual IS NULL OR actual = 0 THEN 'CANCELLED'
                   ELSE NULL
                END
                   AS remarks,
                round (value, 2) AS total,
                actual,
                CASE
                   WHEN sh.user_id IN ('txtdis', 'postgres', 'vier')
                   THEN
                      'SYSGEN'
                   WHEN sh.user_id IN ('jessa', 'donna')
                   THEN
                      'GLAZEL'
                   ELSE
                      upper (sh.user_id)
                END
                   AS billed_by,
                sh.time_stamp AS billed_on,
                CASE
                   WHEN ref_id IN
                           (SELECT DISTINCT ref_id
                              FROM invoice_header
                             WHERE     invoice_date >= '2014-01-01'
                                   AND ref_id NOT IN
                                          (SELECT sales_id
                                             FROM sales_header
                                            WHERE sales_date >= '2014-01-01'))
                   THEN
                      NULL
                   ELSE
                      ref_id
                END
                   AS booking_id,
                CASE
                   WHEN    actual IS NULL
                        OR actual = 0
                        OR   actual
                           - CASE
                                WHEN payment IS NULL THEN 0
                                ELSE payment
                             END <= 0
                   THEN
                      TRUE
                   ELSE
                      FALSE
                END
                   AS is_fully_paid,
                sh.invoice_id AS id_no,
                NULL AS prefix,
                TRUE AS is_printed,
                NULL AS purchasing_id,
                NULL AS received_by,
                NULL AS received_on,
                NULL AS receiving_id,
                CASE
                   WHEN char_length (trim (sh.series)) = 0 THEN NULL
                   ELSE trim (sh.series)
                END
                   AS suffix,
                round (
                     CASE WHEN actual IS NULL THEN 0 ELSE actual END
                   - CASE WHEN payment IS NULL THEN 0 ELSE payment END,
                   2)
                   AS unpaid,
                CASE
                   WHEN sh.customer_id = 0 OR sh.customer_id IS NULL THEN 574
                   ELSE sh.customer_id
                END
                   AS customer_id,
                NULL AS pick_list_id
           FROM invoice_header AS sh
                LEFT JOIN account AS acc
                   ON     sh.customer_id = acc.customer_id
                      AND acc.start_date <= sh.invoice_date
                LEFT JOIN payment AS p
                   ON sh.invoice_id = p.order_id AND sh.series = p.series
                LEFT JOIN subtotal AS st
                   ON sh.invoice_id = st.id AND sh.series = st.series
                LEFT JOIN net AS n
                   ON sh.invoice_id = n.id AND sh.series = n.series
                LEFT JOIN credit AS cd
                   ON     sh.customer_id = cd.customer_id
                      AND cd.start_date <= sh.invoice_date
         UNION                                                           --ALL
         SELECT                                 --DISTINCT ON (sh.delivery_id)
               CASE
                   WHEN sh.user_id IN ('txtdis', 'postgres', 'vier')
                   THEN
                      'SYSGEN'
                   WHEN sh.user_id IN ('jessa', 'donna')
                   THEN
                      'GLAZEL'
                   ELSE
                      upper (sh.user_id)
                END
                   AS created_by,
                sh.time_stamp AS created_on,
                NULL AS audited_by,
                NULL AS audited_on,
                NULL AS is_valid,
                  CASE
                     WHEN delivery_date IS NULL
                     THEN
                        cast (sh.time_stamp AS date)
                     ELSE
                        delivery_date
                  END
                + CASE
                     WHEN last_value (
                             cd.term)
                          OVER (PARTITION BY sh.delivery_id
                                ORDER BY cd.start_date DESC)
                             IS NULL
                     THEN
                        0
                     ELSE
                        last_value (
                           cd.term)
                        OVER (PARTITION BY sh.delivery_id
                              ORDER BY cd.start_date DESC)
                  END
                   AS due_date,
                round (subtotal, 2) AS gross,
                CASE
                   WHEN delivery_date IS NULL
                   THEN
                      cast (sh.time_stamp AS date)
                   ELSE
                      delivery_date
                END
                   AS order_date,
                CASE
                   WHEN actual IS NULL OR actual = 0 THEN 'CANCELLED'
                   ELSE NULL
                END
                   AS remarks,
                round (value, 2) AS total,
                actual,
                CASE
                   WHEN sh.user_id IN ('txtdis', 'postgres', 'vier')
                   THEN
                      'SYSGEN'
                   WHEN sh.user_id IN ('jessa', 'donna')
                   THEN
                      'GLAZEL'
                   ELSE
                      upper (sh.user_id)
                END
                   AS billed_by,
                sh.time_stamp AS billed_on,
                CASE
                   WHEN ref_id IN
                           (SELECT DISTINCT ref_id
                              FROM delivery_header
                             WHERE     delivery_date >= '2014-01-01'
                                   AND ref_id NOT IN
                                          (SELECT sales_id
                                             FROM sales_header
                                            WHERE sales_date >= '2014-01-01'))
                   THEN
                      NULL
                   ELSE
                      ref_id
                END
                   AS booking_id,
                CASE
                   WHEN    actual IS NULL
                        OR actual = 0
                        OR   actual
                           - CASE
                                WHEN payment IS NULL THEN 0
                                ELSE payment
                             END <= 0
                   THEN
                      TRUE
                   ELSE
                      FALSE
                END
                   AS is_fully_paid,
                -sh.delivery_id AS id_no,
                NULL AS prefix,
                TRUE AS is_printed,
                NULL AS purchasing_id,
                NULL AS received_by,
                NULL AS received_on,
                NULL AS receiving_id,
                NULL AS suffix,
                  CASE WHEN actual IS NULL THEN 0 ELSE actual END
                - CASE WHEN payment IS NULL THEN 0 ELSE payment END
                   AS unpaid,
                CASE
                   WHEN sh.customer_id = 0 OR sh.customer_id IS NULL THEN 574
                   ELSE sh.customer_id
                END
                   AS customer_id,
                NULL AS pick_list_id
           FROM delivery_header AS sh
                LEFT JOIN account AS acc
                   ON     sh.customer_id = acc.customer_id
                      AND acc.start_date <= sh.delivery_date
                LEFT JOIN payment AS p ON -sh.delivery_id = p.order_id
                LEFT JOIN subtotal AS st ON -sh.delivery_id = st.id
                LEFT JOIN net AS n ON -sh.delivery_id = n.id
                LEFT JOIN credit AS cd
                   ON     sh.customer_id = cd.customer_id
                      AND cd.start_date <= sh.delivery_date)
  SELECT row_number () OVER (ORDER BY created_on, id_no, suffix) AS id, *
    FROM billing
   WHERE order_date >= '2014-01-01'
ORDER BY id
--) TO '/Users/txtDIS/db-convert/data/billing.csv' (FORMAT 'csv', DELIMITER '|', HEADER)
;
DELETE FROM qty_per
      WHERE item_id IN (SELECT id
                          FROM ITEM_HEADER
                         WHERE type_id = 6);

INSERT INTO qty_per (item_id,
                     qty,
                     uom,
                     sell)
   (SELECT id,
           1 AS qty,
           5 AS uom,
           TRUE AS sell
      FROM ITEM_HEADER
     WHERE type_id = 6);

--COPY (


WITH details
     AS (SELECT                     --DISTINCT ON (sh.delivery_id, sh.item_id)
               sh.qty,
                sh.uom,
                CASE WHEN sh.item_id < 0 THEN 1 ELSE 0 END AS quality,
                  CASE
                     WHEN abs (sh.item_id) IN (328, 483) AND sh.qty > 3
                     THEN
                          (    last_value (
                                  pd.price)
                               OVER (PARTITION BY sh.delivery_id, sh.item_id
                                     ORDER BY pd.start_date DESC)
                             * 3
                           - 5)
                        / 3
                     ELSE
                        CASE
                           WHEN (last_value (
                                    p.price)
                                 OVER (
                                    PARTITION BY sh.delivery_id, sh.item_id
                                    ORDER BY p.start_date DESC))
                                   IS NULL
                           THEN
                              CASE
                                 WHEN pd.price IS NULL
                                 THEN
                                    0
                                 ELSE
                                    last_value (
                                       pd.price)
                                    OVER (
                                       PARTITION BY sh.delivery_id,
                                                    sh.item_id
                                       ORDER BY pd.start_date DESC)
                              END
                           ELSE
                              last_value (
                                 p.price)
                              OVER (PARTITION BY sh.delivery_id, sh.item_id
                                    ORDER BY p.start_date DESC)
                        END
                  END
                -   CASE
                       WHEN abs (sh.item_id) IN (328, 483) AND sh.qty > 3
                       THEN
                          5 / 3
                       ELSE
                          0
                    END
                  * qp.qty
                   AS price,
                abs (sh.item_id) AS item_id,
                -sh.delivery_id AS id_no,
                ' ' AS series,
                CASE
                   WHEN s.ref_id < 7550 THEN cast (NULL AS int)
                   ELSE s.ref_id
                END
                   AS booking_id,
                s.time_stamp
           FROM delivery_detail AS sh
                INNER JOIN delivery_header AS s
                   ON sh.delivery_id = s.delivery_id
                INNER JOIN qty_per AS qp
                   ON abs (sh.item_id) = qp.item_id AND sh.uom = qp.uom
                INNER JOIN customer_header AS c ON c.id = s.customer_id
                INNER JOIN channel_price_tier AS cpt
                   ON c.type_id = cpt.channel_id
                LEFT JOIN price AS p
                   ON     cpt.tier_id = p.tier_id
                      AND p.start_date <= s.delivery_date
                      AND p.item_id = abs (sh.item_id)
                LEFT JOIN price AS pd
                   ON     pd.tier_id = 1
                      AND pd.start_date <= s.delivery_date
                      AND pd.item_id = abs (sh.item_id)
          WHERE s.delivery_date >= '2014-01-01'
         UNION                                                           --ALL
         SELECT           --DISTINCT ON (sh.invoice_id, sh.series, sh.item_id)
               sh.qty,
                sh.uom,
                CASE WHEN sh.item_id < 0 THEN 1 ELSE 0 END AS quality,
                    qp.qty
                  * CASE
                       WHEN abs (sh.item_id) IN (328, 483)
                       THEN
                            (    last_value (
                                    pd.price)
                                 OVER (
                                    PARTITION BY sh.invoice_id,
                                                 sh.series,
                                                 sh.item_id
                                    ORDER BY pd.start_date DESC)
                               * 3
                             - 5)
                          / 3
                       ELSE
                          CASE
                             WHEN (last_value (
                                      p.price)
                                   OVER (
                                      PARTITION BY sh.invoice_id,
                                                   sh.series,
                                                   sh.item_id
                                      ORDER BY p.start_date DESC))
                                     IS NULL
                             THEN
                                CASE
                                   WHEN pd.price IS NULL
                                   THEN
                                      0
                                   ELSE
                                      last_value (
                                         pd.price)
                                      OVER (
                                         PARTITION BY sh.invoice_id,
                                                      sh.series,
                                                      sh.item_id
                                         ORDER BY pd.start_date DESC)
                                END
                             ELSE
                                last_value (
                                   p.price)
                                OVER (
                                   PARTITION BY sh.invoice_id,
                                                sh.series,
                                                sh.item_id
                                   ORDER BY p.start_date DESC)
                          END
                    END
                - CASE
                     WHEN abs (sh.item_id) IN (328, 483) AND sh.qty > 3
                     THEN
                        5 / 3
                     ELSE
                        0
                  END
                   AS price,
                abs (sh.item_id) AS item_id,
                sh.invoice_id AS id_no,
                sh.series,
                CASE
                   WHEN s.ref_id < 7550 THEN cast (NULL AS int)
                   ELSE s.ref_id
                END
                   AS booking_id,
                s.time_stamp
           FROM invoice_detail AS sh
                INNER JOIN invoice_header AS s
                   ON sh.invoice_id = s.invoice_id
                INNER JOIN qty_per AS qp
                   ON abs (sh.item_id) = qp.item_id AND sh.uom = qp.uom
                INNER JOIN customer_header AS c ON c.id = s.customer_id
                INNER JOIN channel_price_tier AS cpt
                   ON c.type_id = cpt.channel_id
                LEFT JOIN price AS p
                   ON     cpt.tier_id = p.tier_id
                      AND p.start_date <= s.invoice_date
                      AND p.item_id = abs (sh.item_id)
                LEFT JOIN price AS pd
                   ON     pd.tier_id = 1
                      AND pd.start_date <= s.invoice_date
                      AND pd.item_id = abs (sh.item_id)
          WHERE s.invoice_date >= '2014-01-01'),
     detail AS (SELECT * FROM details),
     headers
     AS (SELECT time_stamp AS created_on, invoice_id AS id_no, series
           FROM invoice_header
          WHERE invoice_date >= '2014-01-01'
         UNION                                                           --ALL
         SELECT time_stamp AS created_on,
                -sh.delivery_id AS id_no,
                ' ' AS series
           FROM delivery_header AS sh
          WHERE delivery_date >= '2014-01-01'),
     header
     AS (SELECT row_number () OVER (ORDER BY created_on, id_no, series)
                   AS invoice_id,
                *
           FROM headers),
     billing
     AS (SELECT qty,
                uom,
                quality,
                price,
                item_id,
                invoice_id,
                booking_id,
                time_stamp
           FROM detail AS d
                INNER JOIN header AS h
                   ON d.id_no = h.id_no AND d.series = h.series),
     booking
     AS (SELECT                        --DISTINCT ON (sh.sales_id, sh.item_id)
               sh.qty,
                sh.uom,
                CASE WHEN sh.item_id < 0 THEN 1 ELSE 0 END AS quality,
                  CASE
                     WHEN abs (sh.item_id) IN (328, 483)
                     THEN
                          (    last_value (
                                  pd.price)
                               OVER (PARTITION BY sh.sales_id, sh.item_id
                                     ORDER BY pd.start_date DESC)
                             * 3
                           - 5)
                        / 3
                     ELSE
                        CASE
                           WHEN (last_value (
                                    p.price)
                                 OVER (PARTITION BY sh.sales_id, sh.item_id
                                       ORDER BY p.start_date DESC))
                                   IS NULL
                           THEN
                              CASE
                                 WHEN pd.price IS NULL
                                 THEN
                                    0
                                 ELSE
                                    last_value (
                                       pd.price)
                                    OVER (
                                       PARTITION BY sh.sales_id, sh.item_id
                                       ORDER BY pd.start_date DESC)
                              END
                           ELSE
                              last_value (
                                 p.price)
                              OVER (PARTITION BY sh.sales_id, sh.item_id
                                    ORDER BY p.start_date DESC)
                        END
                  END
                -   CASE
                       WHEN abs (sh.item_id) IN (328, 483) AND sh.qty > 3
                       THEN
                          5 / 3
                       ELSE
                          0
                    END
                  * qp.qty
                   AS price,
                abs (sh.item_id) AS item_id,
                cast (NULL AS int) AS invoice_id,
                s.sales_id AS booking_id,
                s.time_stamp
           FROM sales_detail AS sh
                INNER JOIN sales_header AS s ON sh.sales_id = s.sales_id
                INNER JOIN qty_per AS qp
                   ON abs (sh.item_id) = qp.item_id AND sh.uom = qp.uom
                INNER JOIN customer_header AS c ON c.id = s.customer_id
                INNER JOIN channel_price_tier AS cpt
                   ON c.type_id = cpt.channel_id
                LEFT JOIN price AS p
                   ON     cpt.tier_id = p.tier_id
                      AND p.start_date <= s.sales_date
                      AND p.item_id = abs (sh.item_id)
                LEFT JOIN price AS pd
                   ON     pd.tier_id = 1
                      AND pd.start_date <= s.sales_date
                      AND pd.item_id = abs (sh.item_id)
          WHERE s.sales_date >= '2014-01-01'),
     book_n_bill
     AS (SELECT CASE WHEN u.qty IS NULL THEN b.qty ELSE u.qty END AS qty,
                CASE WHEN u.uom IS NULL THEN b.uom ELSE u.uom END AS uom,
                CASE WHEN u.quality IS NULL THEN b.quality ELSE u.quality END
                   AS quality,
                CASE WHEN u.price IS NULL THEN b.price ELSE u.price END
                   AS price,
                CASE WHEN u.item_id IS NULL THEN b.item_id ELSE u.item_id END
                   AS item_id,
                CASE
                   WHEN u.invoice_id IS NULL THEN b.invoice_id
                   ELSE u.invoice_id
                END
                   AS billing_id,
                CASE
                   WHEN b.time_stamp IS NULL THEN u.time_stamp
                   ELSE b.time_stamp
                END
                   AS time_stamp
           FROM booking AS b
                FULL OUTER JOIN billing AS u
                   ON     b.item_id = u.item_id
                      AND b.qty = u.qty
                      AND b.uom = u.uom
                      AND b.quality = u.quality
                      AND u.booking_id = b.booking_id)
  SELECT row_number () OVER (ORDER BY time_stamp ASC) AS id,
         round (qty, 4) AS initial_qty,
         round (price, 2) AS price,
         quality,
         NULL AS returned_qty,
         uom,
         item_id,
         billing_id
    FROM book_n_bill
ORDER BY id
--) TO '/Users/txtDIS/db-convert/data/billing_detail.csv' (FORMAT 'csv', DELIMITER '|', HEADER)
;
UPDATE discount
   SET level_1 = 2
 WHERE customer_id = 7 AND family_id = -1 AND start_date = '2014-11-16';


--COPY (

WITH                                                               --RECURSIVE
    parent_child                                       --(child_id, parent_id)
     AS (SELECT it.child_id, it.parent_id
           FROM item_tree AS it
         UNION                                                           --ALL
         SELECT parent_child.child_id, it.parent_id
           FROM item_tree it
                JOIN parent_child ON it.child_id = parent_child.parent_id),
     family
     AS (SELECT                                    --DISTINCT ON (sh.sales_id)
               sh.sales_id,
                last_value (pc.parent_id)
                   OVER (PARTITION BY child_id ORDER BY parent_id ASC)
                   AS family_id
           FROM sales_detail AS sd
                INNER JOIN sales_header AS sh ON sd.sales_id = sh.sales_id
                INNER JOIN parent_child AS pc ON sd.item_id = pc.child_id
                INNER JOIN discount AS d
                   ON     d.customer_id = sh.customer_id
                      AND d.family_id = pc.parent_id
          WHERE sd.line_id = 1),
     customer_discounts
     AS (SELECT time_stamp,
                1 AS level,
                CASE WHEN level_1 = 0 THEN NULL ELSE level_1 END AS per_cent,
                start_date,
                CASE
                   WHEN user_id IN ('txtdis', 'postgres') THEN 'SYSGEN'
                   ELSE upper (user_id)
                END
                   AS user_id,
                family_id,
                customer_id
           FROM discount
         UNION                                                           --ALL
         SELECT time_stamp,
                2 AS level,
                level_2 AS per_cent,
                start_date,
                CASE
                   WHEN user_id IN ('txtdis', 'postgres') THEN 'SYSGEN'
                   ELSE upper (user_id)
                END
                   AS user_id,
                family_id,
                customer_id
           FROM discount
          WHERE level_2 > 0),
     customer_discount
     AS (SELECT row_number ()
                   OVER (ORDER BY
                            time_stamp,
                            customer_id,
                            family_id,
                            level)
                   AS discount_id,
                level,
                start_date,
                family_id,
                customer_id
           FROM customer_discounts),
     sales_discount
     AS (SELECT                                     --DISTINCT ON (s.sales_id)
               s.sales_id AS booking_id,
                d.customer_id,
                f.family_id,
                first_value (
                   d.start_date)
                OVER (PARTITION BY s.sales_id, d.customer_id, d.family_id
                      ORDER BY d.start_date DESC)
                   AS start_date
           FROM sales_header AS s
                INNER JOIN family AS f ON s.sales_id = f.sales_id
                INNER JOIN discount AS d
                   ON     s.customer_id = d.customer_id
                      AND f.family_id = d.family_id
                      AND d.start_date <= s.sales_date
          WHERE s.sales_date >= '2014-01-01')
  SELECT s.booking_id, cd.discount_id
    FROM sales_discount AS s
         INNER JOIN customer_discount AS cd
            ON     s.customer_id = cd.customer_id
               AND s.family_id = cd.family_id
               AND cd.start_date = s.start_date
ORDER BY s.booking_id, cd.discount_id
--) TO '/Users/txtDIS/db-convert/data/booking_discount.csv' (FORMAT 'csv', DELIMITER '|', HEADER)
;
--COPY (

WITH                                                               --RECURSIVE
    parent_child                                       --(child_id, parent_id)
     AS (SELECT it.child_id, it.parent_id
           FROM item_tree AS it
         UNION                                                           --ALL
         SELECT parent_child.child_id, it.parent_id
           FROM item_tree it
                JOIN parent_child ON it.child_id = parent_child.parent_id),
     billings
     AS (SELECT                       --DISTINCT ON (sh.invoice_id, sh.series)
               sh.time_stamp AS created_on,
                sh.invoice_id AS id_no,
                sh.series AS suffix,
                customer_id,
                invoice_date AS order_date
           FROM invoice_header AS sh
          WHERE sh.invoice_date >= '2014-01-01'
         UNION                                                           --ALL
         SELECT                                 --DISTINCT ON (sh.delivery_id)
               sh.time_stamp AS created_on,
                -sh.delivery_id AS id_no,
                cast (NULL AS text) AS suffix,
                customer_id,
                delivery_date AS order_date
           FROM delivery_header AS sh
          WHERE delivery_date >= '2014-01-01'),
     billing
     AS (SELECT row_number () OVER (ORDER BY created_on) AS id, *
           FROM billings),
     family
     AS (SELECT                       --DISTINCT ON (sh.invoice_id, sh.series)
               sh.invoice_id AS id_no,
                sh.series AS suffix,
                last_value (pc.parent_id)
                   OVER (PARTITION BY child_id ORDER BY parent_id ASC)
                   AS family_id
           FROM invoice_detail AS sd
                INNER JOIN invoice_header AS sh
                   ON sd.invoice_id = sh.invoice_id AND sd.series = sh.series
                INNER JOIN parent_child AS pc ON sd.item_id = pc.child_id
                INNER JOIN discount AS d
                   ON     d.customer_id = sh.customer_id
                      AND d.family_id = pc.parent_id
          WHERE sd.line_id = 1
         UNION                                                           --ALL
         SELECT                                 --DISTINCT ON (sh.delivery_id)
               -sh.delivery_id AS id_no,
                NULL AS suffix,
                last_value (pc.parent_id)
                   OVER (PARTITION BY child_id ORDER BY parent_id ASC)
                   AS family_id
           FROM delivery_detail AS sd
                INNER JOIN delivery_header AS sh
                   ON sd.delivery_id = sh.delivery_id
                INNER JOIN parent_child AS pc ON sd.item_id = pc.child_id
                INNER JOIN discount AS d
                   ON     d.customer_id = sh.customer_id
                      AND d.family_id = pc.parent_id
          WHERE sd.line_id = 1),
     discounts
     AS (SELECT time_stamp,
                1 AS level,
                CASE WHEN level_1 = 0 THEN NULL ELSE level_1 END AS per_cent,
                start_date,
                family_id,
                customer_id
           FROM discount
         UNION                                                           --ALL
         SELECT time_stamp,
                2 AS level,
                level_2 AS per_cent,
                start_date,
                family_id,
                customer_id
           FROM discount
          WHERE level_2 > 0),
     customer_discount
     AS (SELECT row_number ()
                   OVER (ORDER BY
                            time_stamp,
                            customer_id,
                            family_id,
                            level)
                   AS discount_id,
                level,
                start_date,
                family_id,
                customer_id
           FROM discounts),
     invoice_discount
     AS (SELECT                                           --DISTINCT ON (s.id)
               s.id,
                d.customer_id,
                f.family_id,
                last_value (
                   d.start_date)
                OVER (PARTITION BY s.id, d.customer_id, d.family_id
                      ORDER BY d.start_date DESC)
                   AS start_date
           FROM billing AS s
                INNER JOIN family AS f
                   ON s.id_no = f.id_no AND f.suffix = s.suffix
                INNER JOIN discount AS d
                   ON     s.customer_id = d.customer_id
                      AND f.family_id = d.family_id
                      AND d.start_date <= s.order_date)
  SELECT s.id, cd.discount_id
    FROM invoice_discount AS s
         INNER JOIN customer_discount AS cd
            ON     s.customer_id = cd.customer_id
               AND s.family_id = cd.family_id
               AND cd.start_date = s.start_date
ORDER BY s.id, cd.discount_id
--) TO '/Users/txtDIS/db-convert/data/billing_discount.csv' (FORMAT 'csv', DELIMITER '|', HEADER)
;
--COPY (

WITH headers
     AS (SELECT time_stamp AS created_on, invoice_id AS id_no, series
           FROM invoice_header
          WHERE invoice_date >= '2014-01-01'
         UNION                                                           --ALL
         SELECT time_stamp AS created_on,
                -sh.delivery_id AS id_no,
                ' ' AS series
           FROM delivery_header AS sh
          WHERE delivery_date >= '2014-01-01'),
     header
     AS (SELECT row_number () OVER (ORDER BY created_on, id_no, series)
                   AS invoice_id,
                *
           FROM headers),
     tso
     AS (SELECT                                    --DISTINCT ON (rd.order_id)
               rd.order_id AS remit_id,
                upper (rh.user_id) AS depositor,
                CASE WHEN rh.bank_id = 560 THEN NULL ELSE rh.bank_id END
                   AS deposit_bank_id,
                rh.remit_date AS deposit_date,
                rh.remit_time AS deposit_time,
                rh.time_stamp AS depositor_on
           FROM remit_detail AS rd
                INNER JOIN remit_header AS rh
                   ON     rd.remit_id = rh.remit_id
                      AND rd.series = 'R'
                      AND rh.remit_date >= '2014-01-01')
  SELECT                                           --DISTINCT ON (rh.remit_id)
        rh.remit_id AS id,
         upper (rh.user_id) AS created_by,
         rh.time_stamp AS created_on,
         NULL AS account_id,
         NULL AS audited_by,
         NULL AS audited_on,
         CASE
            WHEN rh.remit_time <> '00:00:00' OR rh.bank_id = 559 THEN NULL
            ELSE rh.ref_id
         END
            AS check_id,
         NULL AS collector,
         CASE
            WHEN    (rh.remit_time = '00:00:00' AND rh.bank_id <> 559)
                 OR rh.bank_id = 560
            THEN
               tso.deposit_date + tso.deposit_time
            ELSE
               rh.remit_date + rh.remit_time
         END
            AS deposited_on,
         CASE
            WHEN    (rh.remit_time = '00:00:00' AND rh.bank_id <> 559)
                 OR rh.bank_id = 560
            THEN
               tso.depositor
            ELSE
               upper (rh.user_id)
         END
            AS depositor,
         CASE
            WHEN    (rh.remit_time = '00:00:00' AND rh.bank_id <> 559)
                 OR rh.bank_id IN (0, 560)
            THEN
               tso.depositor_on
            ELSE
               rh.time_stamp
         END
            AS depositor_on,
         NULL AS is_valid,
         remit_date AS payment_date,
         NULL AS received_by,
         NULL AS received_on,
         NULL AS remarks,
         rh.total AS value,
         CASE
            WHEN    (rh.remit_time = '00:00:00' AND rh.bank_id <> 559)
                 OR rh.bank_id IN (0, 560)
            THEN
               tso.deposit_bank_id
            ELSE
               rh.bank_id
         END
            AS depositor_bank_id,
         CASE
            WHEN rh.remit_time <> '00:00:00' OR rh.bank_id IN (0, 559)
            THEN
               NULL
            ELSE
               rh.bank_id
         END
            AS drawee_bank_id,
         CASE
            WHEN rh.remit_time <> '00:00:00' OR rh.bank_id IN (0, 559)
            THEN
               NULL
            ELSE
               CASE
                  WHEN rd.order_id < 0
                  THEN
                     CASE
                        WHEN dh.customer_id = 0 THEN NULL
                        ELSE dh.customer_id
                     END
                  ELSE
                     CASE
                        WHEN ih.customer_id = 0 THEN NULL
                        ELSE ih.customer_id
                     END
               END
         END
            AS payor_id
    FROM remit_header AS rh
         INNER JOIN remit_detail AS rd
            ON     rd.remit_id = rh.remit_id
               AND rd.line_id = 1
               AND rh.remit_date >= '2014-01-01'
         INNER JOIN header AS h
            ON rd.order_id = h.id_no AND rd.series = h.series
         LEFT JOIN delivery_header AS dh ON dh.delivery_id = -rd.order_id
         LEFT JOIN invoice_header AS ih ON ih.invoice_id = rd.order_id
         LEFT JOIN tso ON tso.remit_id = rh.remit_id
ORDER BY id
--) TO '/Users/txtDIS/db-convert/data/remittance.csv' (FORMAT 'csv', DELIMITER '|', HEADER)
;
--COPY (

WITH headers
     AS (SELECT time_stamp AS created_on, invoice_id AS id_no, series
           FROM invoice_header
          WHERE invoice_date >= '2014-01-01'
         UNION                                                           --ALL
         SELECT time_stamp AS created_on,
                -sh.delivery_id AS id_no,
                ' ' AS series
           FROM delivery_header AS sh
          WHERE delivery_date >= '2014-01-01'),
     header
     AS (SELECT row_number () OVER (ORDER BY created_on, id_no, series)
                   AS order_id,
                *
           FROM headers),
     tso
     AS (SELECT DISTINCT rd.order_id
           FROM remit_detail AS rd
                INNER JOIN remit_header AS rh
                   ON     rd.remit_id = rh.remit_id
                      AND rd.series = 'R'
                      AND rh.remit_date >= '2014-01-01'),
     remittance
     AS (SELECT DISTINCT rh.remit_id
           FROM remit_header AS rh
                INNER JOIN remit_detail AS rd
                   ON     rd.remit_id = rh.remit_id
                      AND rd.line_id = 1
                      AND rh.remit_date >= '2014-01-01'
                INNER JOIN header AS h
                   ON rd.order_id = h.id_no AND rd.series = h.series
                LEFT JOIN delivery_header AS dh
                   ON dh.delivery_id = -rd.order_id
                LEFT JOIN invoice_header AS ih ON ih.invoice_id = rd.order_id
                LEFT JOIN tso ON tso.order_id = rh.remit_id)
  SELECT                                  --DISTINCT ON (rd.remit_id, line_id)
        row_number () OVER (ORDER BY rd.remit_id, line_id) AS id,
         CASE WHEN rd.payment IS NULL THEN 0 ELSE rd.payment END AS payment,
         h.order_id,
         rd.remit_id AS remittance_id
    FROM remit_detail AS rd
         INNER JOIN remit_header AS rh ON rd.remit_id = rh.remit_id
         INNER JOIN remittance AS r ON r.remit_id = rh.remit_id
         INNER JOIN header AS h
            ON rd.order_id = h.id_no AND rd.series = h.series
   WHERE remit_date >= '2014-01-01' AND rd.series <> 'R'
ORDER BY rd.remit_id, line_id
--) TO '/Users/txtDIS/db-convert/data/remittance_detail.csv' (FORMAT 'csv', DELIMITER '|', HEADER)
;
END;
--\i '/Users/txtDIS/db-convert/sql/createCSV.sql';
