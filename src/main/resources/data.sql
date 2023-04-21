INSERT INTO products(name, description, price, stock)
VALUES ('prod1', 'opis prod1', '11.11', 11),
       ('prod2', 'opis prod2', '22.22', 22),
       ('prod3', 'opis prod3', '33.33', 33),
       ('prod4', 'opis prod4', '44.44', 44);
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