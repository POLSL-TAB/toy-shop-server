INSERT INTO products(name)
VALUES ('prod1'),
       ('prod2'),
       ('prod3'),
       ('prod4');
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