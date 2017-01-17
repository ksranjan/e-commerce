use e_commerce_test;
TRUNCATE TABLE product;

INSERT INTO product ( name, stocknum, listprice, discount, rating, reviews , quantity, restricted) values
  ('tata-salt', 'tata1', 10, 1, 2,2,5, false);