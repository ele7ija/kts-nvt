insert into user(id, email, password, first_name, last_name, role, enabled) values(1, "mika.mika@gmail.com", "$2y$10$VRvAb7u77qQcsWDI6Fz.wO6pB/b3fTkTL6sYNcm9qceB5QOWBj3Ju", "Mika", "Mika", "USER", true);
insert into user(id, email, password, first_name, last_name, role, enabled) values(2, "milan.milan@gmail.com", "$2y$10$/PQ6flYUXDYQUyqUPYB4BeaGfw9EVWest0Qg1s8LBmybjAEiglbIK", "Milan", "Milan", "ADMIN", true);
insert into user(id, email, password, first_name, last_name, role, enabled) values(3, "pera.pera@gmail.com", "$2y$10$fKJLrmLX5b8DEA2iIV9BJ.attAgPF7boOv63z6.aGh0Bb8YUKavDC", "Pera", "Pera", "SUPER_ADMIN", true);
insert into user(id, email, password, first_name, last_name, role, enabled) values(4, "ana.ana@gmail.com", "$2a$10$5//2XuWiARzPIzj9z8Vv.uqN2Pv9J..W1rzyLVLKlhaILY4vP/mMi", "Ana", "Ana", "SUPER_ADMIN", true);

insert into cultural_offering_type(id, type_name) values (10, "Institucija");
insert into cultural_offering_type(id, type_name) values (11, "Kulturno dobro");
insert into cultural_offering_type(id, type_name) values (12, "Manifestacija");
insert into cultural_offering_type(id, type_name) values (13, "Dummy manifestacija");

insert into cultural_offering_sub_type(id, sub_type_name, cultural_offering_type_id) values (10, "Muzej", 10);
insert into cultural_offering_sub_type(id, sub_type_name, cultural_offering_type_id) values (14, "Galerija", 10);
insert into cultural_offering_sub_type(id, sub_type_name, cultural_offering_type_id) values (15, "Pozoriste", 10);
insert into cultural_offering_sub_type(id, sub_type_name, cultural_offering_type_id) values (11, "Spomenik", 11);
insert into cultural_offering_sub_type(id, sub_type_name, cultural_offering_type_id) values (16, "Gradjevina", 11);
insert into cultural_offering_sub_type(id, sub_type_name, cultural_offering_type_id) values (17, "Arheolosko nalaziste", 11);
insert into cultural_offering_sub_type(id, sub_type_name, cultural_offering_type_id) values (12, "Muzicki festival", 12);
insert into cultural_offering_sub_type(id, sub_type_name, cultural_offering_type_id) values (18, "Filmski festival", 12);
insert into cultural_offering_sub_type(id, sub_type_name, cultural_offering_type_id) values (19, "Sajam", 12);
insert into cultural_offering_sub_type(id, sub_type_name, cultural_offering_type_id) values (13, "Muzicki festival za brisanje", 12);

insert into location(id, latitude, longitude, name) values (10, 45.1643711, 19.7208663, "Ignjata Pavlasa 10, Novi Sad");
insert into location(id, latitude, longitude, name) values (11, 45.2466602, 19.8044225, "Trg republike BB, Novi Sad");
insert into location(id, latitude, longitude, name) values (12, 45.2, 20.5, "Vladimira Nazora 1, Petrovaradin");
insert into location(id, latitude, longitude, name) values (13, 45.0, 20.4, "Vladimira Nazora 1, Petrovaradin");
insert into location(id, latitude, longitude, name) values (14, 45.015, 20.0, "Vladimira Nazora 1, Petrovaradin");
insert into location(id, latitude, longitude, name) values (15, 44.0, 20.5, "Vladimira Nazora 1, Petrovaradin");
insert into location(id, latitude, longitude, name) values (16, 44.5, 20.012, "Vladimira Nazora 1, Petrovaradin");
insert into location(id, latitude, longitude, name) values (17, 43.0, 20.123, "Vladimira Nazora 1, Petrovaradin");
insert into location(id, latitude, longitude, name) values (18, 43.5, 20.7, "Vladimira Nazora 1, Petrovaradin");
insert into location(id, latitude, longitude, name) values (19, 42.2, 22.01122, "Vladimira Nazora 1, Petrovaradin");
insert into location(id, latitude, longitude, name) values (20, 42.5, 21.05, "Vladimira Nazora 1, Petrovaradin");
insert into location(id, latitude, longitude, name) values (21, 41.7, 20.7567, "Vladimira Nazora 1, Petrovaradin");
insert into location(id, latitude, longitude, name) values (22, 41.5, 20.8657, "Vladimira Nazora 1, Petrovaradin");
insert into location(id, latitude, longitude, name) values (23, 43.23, 20.54, "Vladimira Nazora 1, Petrovaradin");
insert into location(id, latitude, longitude, name) values (24, 43.0123, 21.41, "Vladimira Nazora 1, Petrovaradin");
insert into location(id, latitude, longitude, name) values (25, 43.0151, 21.0141, "Vladimira Nazora 1, Petrovaradin");
insert into location(id, latitude, longitude, name) values (26, 44.04, 21.5124, "Vladimira Nazora 1, Petrovaradin");
insert into location(id, latitude, longitude, name) values (27, 44.551, 21.98, "Vladimira Nazora 1, Petrovaradin");
insert into location(id, latitude, longitude, name) values (28, 43.0, 22.723, "Vladimira Nazora 1, Petrovaradin");
insert into location(id, latitude, longitude, name) values (29, 43.5132, 22.7123, "Vladimira Nazora 1, Petrovaradin");
insert into location(id, latitude, longitude, name) values (30, 42.8417, 22.2340, "Vladimira Nazora 1, Petrovaradin");
insert into location(id, latitude, longitude, name) values (31, 42.101, 22.812, "Vladimira Nazora 1, Petrovaradin");
insert into location(id, latitude, longitude, name) values (32, 42.7456, 22.11953, "Vladimira Nazora 1, Petrovaradin");
insert into location(id, latitude, longitude, name) values (33, 42.5645, 21.412, "Vladimira Nazora 1, Petrovaradin");
insert into location(id, latitude, longitude, name) values (34, 42.19193, 21.96, "Vladimira Nazora 1, Petrovaradin");
insert into location(id, latitude, longitude, name) values (35, 42.564, 21.8456, "Vladimira Nazora 1, Petrovaradin");

insert into cultural_offering(id, description, name, cultural_offering_sub_type_id, cultural_offering_type_id, location_id) values (10, "Muzej savremenih umetnosti je osnovan 1936.", "Muzej savremenih umetnosti", 10, 10, 10);
insert into cultural_offering(id, description, name, cultural_offering_sub_type_id, cultural_offering_type_id, location_id) values (13, "Muzej Vojvodine je osnovan 1956.", "Muzej Vojvodine", 10, 10, 13);
insert into cultural_offering(id, description, name, cultural_offering_sub_type_id, cultural_offering_type_id, location_id) values (14, "Muzej je osnovan 1870.", "Narodni Muzej", 10, 10, 14);
insert into cultural_offering(id, description, name, cultural_offering_sub_type_id, cultural_offering_type_id, location_id) values (15, "Galerija je osnovana 1948.", "Galerija Matice Srpske", 14, 10, 15);
insert into cultural_offering(id, description, name, cultural_offering_sub_type_id, cultural_offering_type_id, location_id) values (16, "Festival se odrzava od 2016.", "Zaduzbina Pavla Beljanskog", 14, 10, 16);
insert into cultural_offering(id, description, name, cultural_offering_sub_type_id, cultural_offering_type_id, location_id) values (17, "Festival se odrzava od 2016.", "Srpsko Narodno pozoriste", 15, 10, 17);
insert into cultural_offering(id, description, name, cultural_offering_sub_type_id, cultural_offering_type_id, location_id) values (18, "Festival se odrzava od 2016.", "Pozoriste Mladih", 15, 10, 18);
insert into cultural_offering(id, description, name, cultural_offering_sub_type_id, cultural_offering_type_id, location_id) values (19, "Festival se odrzava od 2016.", "Pozoriste lutaka", 15, 10, 19);

insert into cultural_offering(id, description, name, cultural_offering_sub_type_id, cultural_offering_type_id, location_id) values (11, "Spomenik je zvanicno predstavljen 2019.", "Spomenik kralju Peri", 11, 11, 20);
insert into cultural_offering(id, description, name, cultural_offering_sub_type_id, cultural_offering_type_id, location_id) values (20, "Festival se odrzava od 2016.", "Spomenik Sloboda", 11, 11, 21);
insert into cultural_offering(id, description, name, cultural_offering_sub_type_id, cultural_offering_type_id, location_id) values (21, "Festival se odrzava od 2016.", "Spomenik Stefanu Nemanji", 11, 11, 22);
insert into cultural_offering(id, description, name, cultural_offering_sub_type_id, cultural_offering_type_id, location_id) values (22, "Festival se odrzava od 2016.", "Tanurdziceva palata", 16, 11, 23);
insert into cultural_offering(id, description, name, cultural_offering_sub_type_id, cultural_offering_type_id, location_id) values (23, "Festival se odrzava od 2016.", "Kuca Dositeja Obradovica", 16, 11, 24);
insert into cultural_offering(id, description, name, cultural_offering_sub_type_id, cultural_offering_type_id, location_id) values (24, "Festival se odrzava od 2016.", "Kuca Vojvode Zivojina Misica", 16, 11, 25);
insert into cultural_offering(id, description, name, cultural_offering_sub_type_id, cultural_offering_type_id, location_id) values (25, "Festival se odrzava od 2016.", "Arh. nalaziste Vinca", 17, 11, 26);
insert into cultural_offering(id, description, name, cultural_offering_sub_type_id, cultural_offering_type_id, location_id) values (26, "Festival se odrzava od 2016.", "Arh. nalaziste Lepenski Vir", 17, 11, 27);

insert into cultural_offering(id, description, name, cultural_offering_sub_type_id, cultural_offering_type_id, location_id) values (12, "Festival se odrzava od 2016.", "Festival ulicnih sviraca", 12, 12, 28);
insert into cultural_offering(id, description, name, cultural_offering_sub_type_id, cultural_offering_type_id, location_id) values (27, "Festival se odrzava od 2016.", "Exit festival", 12, 12, 29);
insert into cultural_offering(id, description, name, cultural_offering_sub_type_id, cultural_offering_type_id, location_id) values (28, "Festival se odrzava od 2016.", "Nishvile Jazz fest", 12, 12, 30);
insert into cultural_offering(id, description, name, cultural_offering_sub_type_id, cultural_offering_type_id, location_id) values (29, "Festival se odrzava od 2016.", "Cinema city NS", 18, 12, 31);
insert into cultural_offering(id, description, name, cultural_offering_sub_type_id, cultural_offering_type_id, location_id) values (30, "Festival se odrzava od 2016.", "Cinema city BG", 18, 12, 32);
insert into cultural_offering(id, description, name, cultural_offering_sub_type_id, cultural_offering_type_id, location_id) values (31, "Festival se odrzava od 2016.", "Sajam Turizma", 19, 12, 33);
insert into cultural_offering(id, description, name, cultural_offering_sub_type_id, cultural_offering_type_id, location_id) values (32, "Festival se odrzava od 2016.", "Sajam Obrazovanja", 19, 12, 34);
insert into cultural_offering(id, description, name, cultural_offering_sub_type_id, cultural_offering_type_id, location_id) values (33, "Festival se odrzava od 2016.", "Sajam Lova i Ribolova", 19, 12, 35);


insert into comment(id, date, text, cultural_offering_id, user_id) values (10, "2020-12-09 22:03:11.547", "Ovo je super muzej.", 10, 1);
insert into comment(id, date, text, cultural_offering_id, user_id) values (11, "2020-12-09 22:03:11.547", "Ovo je super spomenik.", 11, 2);
insert into comment(id, date, text, cultural_offering_id, user_id) values (12, "2020-12-09 22:03:11.547", "Ovo je super festival.", 12, 3);

insert into rating(id, date, value, cultural_offering_id, user_id) values (10, "2020-12-09 22:03:11.547", 3, 10, 1);
insert into rating(id, date, value, cultural_offering_id, user_id) values (11, "2020-12-09 22:03:11.547", 4, 11, 2);
insert into rating(id, date, value, cultural_offering_id, user_id) values (12, "2020-12-09 22:03:11.547", 5, 12, 3);

insert into news(id, date, text, title, cultural_offering_id, user_id) values (10, "2020-12-09 22:03:11.547", "Bice otvoreno do x.x.", "Nova izlozba se otvara", 10, 2);
insert into news(id, date, text, title, cultural_offering_id, user_id) values (11, "2020-12-09 22:03:11.547", "Neki tekst", "Spomenik je obnovljen", 11, 2);
insert into news(id, date, text, title, cultural_offering_id, user_id) values (12, "2020-12-09 22:03:11.547", "Festival se ne odrzava ove godine", "Festival otkazan", 11, 2);

insert into subscription(id, cultural_offering_id, user_id) values (10, 10, 1);
insert into subscription(id, cultural_offering_id, user_id) values (11, 11, 1);
insert into subscription(id, cultural_offering_id, user_id) values (12, 12, 1);
