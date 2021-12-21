INSERT INTO roles (id, role_name) VALUES (1, 'ADMIN');
INSERT INTO roles (id, role_name) VALUES (2, 'USER');

INSERT INTO room_number (id, number) VALUES (1, 1);
INSERT INTO room_number (id, number) VALUES (2, 2);
INSERT INTO room_number (id, number) VALUES (3, 3);
INSERT INTO room_number (id, number) VALUES (4, 4);
INSERT INTO room_number (id, number) VALUES (5, 5);

INSERT INTO users (id, email, first_name, last_name, password, role_id)
VALUES (6,
        'vol@gmail.com',
        'Vol',
        'Mel',
        '$2a$10$Uay/pbEGEKunRq6V2cDMAuorDBab6rmqVRuJG/f68jJlm713etze2',
        1);

INSERT INTO country (id, name) VALUES (7, 'Ukraine');

INSERT INTO hotel (id, hotel_name, country_id) VALUES (8, 'Super Mega Grand Hotel', 7);
INSERT INTO hotel (id, hotel_name, country_id) VALUES (9, 'Mega Grand Hotel', 7);

INSERT INTO room (id, hotel_id, number_id, room_type, room_prise)
VALUES (5, 8, 1, 'STANDARD', 100);
INSERT INTO room (id, hotel_id, number_id, room_type, room_prise)
VALUES (6, 8, 2, 'SUPERIOR', 200);
INSERT INTO room (id, hotel_id, number_id, room_type, room_prise)
VALUES (7, 8, 3, 'LUX', 500);
INSERT INTO room (id, hotel_id, number_id, room_type, room_prise)
VALUES (8, 9, 1, 'STANDARD', 100);
INSERT INTO room (id, hotel_id, number_id, room_type, room_prise)
VALUES (9, 9, 2, 'SUPERIOR', 200);