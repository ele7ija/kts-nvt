insert into user(id, email, password, first_name, last_name, role, enabled) values(1, "mika.mika@gmail.com", "$2y$10$VRvAb7u77qQcsWDI6Fz.wO6pB/b3fTkTL6sYNcm9qceB5QOWBj3Ju", "Mika", "Mika", "USER", true);
insert into user(id, email, password, first_name, last_name, role, enabled) values(2, "milan.milan@gmail.com", "$2y$10$/PQ6flYUXDYQUyqUPYB4BeaGfw9EVWest0Qg1s8LBmybjAEiglbIK", "Milan", "Milan", "ADMIN", true);
insert into user(id, email, password, first_name, last_name, role, enabled) values(3, "pera.pera@gmail.com", "$2y$10$fKJLrmLX5b8DEA2iIV9BJ.attAgPF7boOv63z6.aGh0Bb8YUKavDC", "Pera", "Pera", "SUPER_ADMIN", true);
insert into user(id, email, password, first_name, last_name, role, enabled) values(4, "ana.ana@gmail.com", "$2a$10$5//2XuWiARzPIzj9z8Vv.uqN2Pv9J..W1rzyLVLKlhaILY4vP/mMi", "Ana", "Ana", "SUPER_ADMIN", true);
insert into user(id, email, password, first_name, last_name, role, enabled) values(5, "sanja.sanjic@gmail.com", "$2a$10$5//2XuWiARzPIzj9z8Vv.uqN2Pv9J..W1rzyLVLKlhaILY4vP/mMi", "Sanja", "Sanja", "USER", false);

insert into image_model(id, name, pic_byte, type) values(1, "Image 1", NULL, "png");
insert into image_model(id, name, pic_byte, type) values(2, "Image 2", NULL, "png");
insert into image_model(id, name, pic_byte, type) values(3, "Image 3", NULL, "png");
insert into image_model(id, name, pic_byte, type) values(4, "Image za brisanje", NULL, "png");

insert into cultural_offering_type(id, type_name, image_model_id) values (10, "Institucija", 1);
insert into cultural_offering_type(id, type_name, image_model_id) values (11, "Kulturno dobro", 2);
insert into cultural_offering_type(id, type_name, image_model_id) values (12, "Manifestacija", 3);
insert into cultural_offering_type(id, type_name, image_model_id) values (13, "Manifestacija za brisanje", 4);

insert into cultural_offering_sub_type(id, sub_type_name, cultural_offering_type_id) values (10, "Muzej", 10);
insert into cultural_offering_sub_type(id, sub_type_name, cultural_offering_type_id) values (11, "Spomenik", 11);
insert into cultural_offering_sub_type(id, sub_type_name, cultural_offering_type_id) values (12, "Muzicki festival", 12);
insert into cultural_offering_sub_type(id, sub_type_name, cultural_offering_type_id) values (13, "Muzicki festival za brisanje", 13);

insert into location(id, latitude, longitude, name) values (10, 45.5, 20.0, "Ignjata Pavlasa 10, Novi Sad");
insert into location(id, latitude, longitude, name) values (11, 45.5, 20.0, "Trg republike BB, Novi Sad");
insert into location(id, latitude, longitude, name) values (12, 45.5, 20.0, "Vladimira Nazora 1, Petrovaradin");

insert into cultural_offering(id, description, name, cultural_offering_sub_type_id, cultural_offering_type_id, location_id) values (10, "Muzej savremenih umetnosti je osnovan 1936.", "Muzej savremenih umetnosti", 10, 10, 10);
insert into cultural_offering(id, description, name, cultural_offering_sub_type_id, cultural_offering_type_id, location_id) values (11, "Spomenik je zvanicno predstavljen 2019.", "Spomenik kralju Peri", 11, 11, 11);
insert into cultural_offering(id, description, name, cultural_offering_sub_type_id, cultural_offering_type_id, location_id) values (12, "Festival se odrzava od 2016.", "Festival ulicnih sviraca", 12, 12, 12);

insert into comment(id, date, text, cultural_offering_id, user_id) values (10, "2020-12-09 22:03:11.547", "Ovo je super muzej.", 10, 1);
insert into comment(id, date, text, cultural_offering_id, user_id) values (11, "2020-12-09 22:03:11.547", "Ovo je super spomenik.", 11, 2);
insert into comment(id, date, text, cultural_offering_id, user_id) values (12, "2020-12-09 22:03:11.547", "Ovo je super festival.", 12, 3);

insert into news(id, date, text, title, cultural_offering_id, user_id) values (10, "2020-12-09 22:03:11.547", "Bice otvoreno do x.x.", "Nova izlozba se otvara", 10, 2);
insert into news(id, date, text, title, cultural_offering_id, user_id) values (11, "2020-12-09 22:03:11.547", "Neki tekst", "Spomenik je obnovljen", 11, 2);
insert into news(id, date, text, title, cultural_offering_id, user_id) values (12, "2020-12-09 22:03:11.547", "Festival se ne odrzava ove godine", "Festival otkazan", 11, 2);
insert into news(id, date, text, title, cultural_offering_id, user_id) values (13, "2020-12-09 22:03:11.547", "Festival se ne odrzava ove godine", "Festival otkazan", 10, 3); -- to update
insert into news(id, date, text, title, cultural_offering_id, user_id) values (14, "2020-12-09 22:03:11.547", "Festival se ne odrzava ove godine", "Festival otkazan", 10, 3); -- to delete

insert into subscription(id, cultural_offering_id, user_id) values (10, 10, 1);
insert into subscription(id, cultural_offering_id, user_id) values (11, 11, 1);
insert into subscription(id, cultural_offering_id, user_id) values (12, 12, 1);
insert into subscription(id, cultural_offering_id, user_id) values (13, 12, 3); -- to update
insert into subscription(id, cultural_offering_id, user_id) values (14, 12, 1); -- to delete

insert into verification_token(id, expiry_date, token, user_id) values(1, null, "token", 5);