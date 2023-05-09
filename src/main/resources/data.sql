INSERT INTO products(name, description, price, stock)
VALUES ('prod1', 'opis prod1', '11.11', 11),
       ('prod2', 'opis prod2', '22.22', 22),
       ('prod3', 'opis prod3', '33.33', 33),
       ('prod4', 'opis prod4', '44.44', 44),
       ('prod5', 'opis prod5', '55.55', 55),
       ('prod6', 'opis prod6', '66.66', 66);
INSERT INTO users(email, password)
VALUES ('example@user', '$2a$10$WEtFNRNvy6n/p4vcA2u8K.qfhGRfKVZqQcbxMGoiDbEzhWblcKoQy'),
       ('example@staff', '$2a$10$WEtFNRNvy6n/p4vcA2u8K.qfhGRfKVZqQcbxMGoiDbEzhWblcKoQy'),
       ('example@admin', '$2a$10$WEtFNRNvy6n/p4vcA2u8K.qfhGRfKVZqQcbxMGoiDbEzhWblcKoQy');
INSERT INTO roles(name)
VALUES ('ROLE_USER'),
       ('ROLE_STAFF'),
       ('ROLE_ADMIN');
INSERT INTO user_roles(user_id, role_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);
INSERT INTO cart_items(user_id, product_id, quantity)
VALUES (1, 1, 2),
       (1, 2, 3);
INSERT INTO pictures(data, product_id)
VALUES (FILE_READ('src/main/resources/images/1_1.jpg'), 1),
       (FILE_READ('src/main/resources/images/1_2.jpg'), 1),
       (FILE_READ('src/main/resources/images/2_1.jpg'), 2),
       (FILE_READ('src/main/resources/images/2_2.jpg'), 2),
       (FILE_READ('src/main/resources/images/3_1.jpg'), 3),
       (FILE_READ('src/main/resources/images/3_2.jpg'), 3),
       (FILE_READ('src/main/resources/images/4_1.jpg'), 4),
       (FILE_READ('src/main/resources/images/4_2.jpg'), 4),
       (FILE_READ('src/main/resources/images/5_1.jpg'), 5),
       (FILE_READ('src/main/resources/images/5_2.jpg'), 5),
       (FILE_READ('src/main/resources/images/6_1.jpg'), 6),
       (FILE_READ('src/main/resources/images/6_2.jpg'), 6);
