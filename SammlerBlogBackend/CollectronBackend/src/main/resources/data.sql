-- some test data fort the h2 db


INSERT INTO type (name, description) VALUES ('car', 'a vehicle with 4 wheels');
INSERT INTO type (name, description) VALUES ('miniature', 'miniatures are small models of larger things');
INSERT INTO type (name, description) VALUES ('stamps', 'post stamps were seriously popular in 20th century');
INSERT INTO type (name, description) VALUES ('super hero equipments', 'all kind of stolen gadgets from sups');

INSERT INTO collectible (name, description, estimated_value, for_sale, image_url, type_id) VALUES ('Daihatsu Midget', 'This is a description', 5000.35, true, 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/1957_Daihatsu_Midget_01.jpg/250px-1957_Daihatsu_Midget_01.jpg', 1);
INSERT INTO collectible (name, description, estimated_value, for_sale, image_url, type_id) VALUES ('Thrud the Barbarian', 'And here is another description', 500.94, false, 'https://upload.wikimedia.org/wikipedia/en/5/5e/Thrud_painted_cover.jpg', 1);
INSERT INTO collectible (name, description, estimated_value, for_sale, image_url, type_id) VALUES ('Hammer of Thor', 'Everybody knows it', 5000000.99, true, 'https://www.powercoin.it/20578-big_default/thor-hammer-mjoelnir-silber-muenze-10-solomon-islands-2021.jpg', 4);
INSERT INTO collectible (name, description, estimated_value, for_sale, image_url, type_id) VALUES ('Blue Mauritius', 'Queen Elisabeth spit can be found on the backside of the stamp', 10000000.00, false, 'https://upload.wikimedia.org/wikipedia/commons/thumb/1/16/Modry_mauritius.jpg/100px-Modry_mauritius.jpg', 3);

INSERT INTO blog_post (title, article, time_stamp, collectible_id) VALUES ('Ancient Microcar', 'The Daihatsu Midget is a single-seater mini-truck, later a microvan/kei truck made by Japanese automaker Daihatsu', '2007-12-03T10:15:30', 1);
INSERT INTO blog_post (title, article, time_stamp, collectible_id) VALUES ('Bad Boy', 'Games Workshop made Arnold Schwarzenegger''s Conan cry', '2007-12-03T10:15:30', 2);
INSERT INTO blog_post (title, article, time_stamp, collectible_id) VALUES ('I found it in a public toilet', 'Probably, Thor has forgotten its hammer and I was at the right time at the right place.', '2008-02-03T10:15:30', 3);
INSERT INTO blog_post (title, article, time_stamp, collectible_id) VALUES ('Ooops', 'Ive tried the hammer. NExt time, I should hold it the upside down. Regards from hospital.', '2008-12-03T10:15:30', 3);

--admin password for tests: 12345
INSERT INTO user_profile (id, name, role, password) VALUES (0, 'admin', 'ROLE_ADMIN', '$2a$12$6dafwhkajhwYy4a7/MZpJuS05Vj9.kfL3Zzj67ezrX4wqIbIFRJGG');
INSERT INTO user_profile (id, name, role, password) VALUES (1, 'viewer', 'ROLE_VIEWER', '$2a$12$6dafwhkajhwYy4a7/MZpJuS05Vj9.kfL3Zzj67ezrX4wqIbIFRJGG');
