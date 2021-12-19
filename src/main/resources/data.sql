INSERT INTO roles (id, role_name) VALUES (1, 'ADMIN');
INSERT INTO roles (id, role_name) VALUES (2, 'USER');

INSERT INTO users (id, email, first_name, last_name, password, role_id)
VALUES (7,
        'vol@gmail.com',
        'Vol',
        'Mel',
        '$2a$10$Uay/pbEGEKunRq6V2cDMAuorDBab6rmqVRuJG/f68jJlm713etze2',
        1);

INSERT INTO country (id, name) VALUES (7, 'Ukraine');