insert into user(id, email, password, first_name, last_name, role, enabled) values(1, "mika.mika@gmail.com", "$2y$10$VRvAb7u77qQcsWDI6Fz.wO6pB/b3fTkTL6sYNcm9qceB5QOWBj3Ju", "Mika", "Mika", "USER", true);
insert into user(id, email, password, first_name, last_name, role, enabled) values(2, "milan.milan@gmail.com", "$2y$10$/PQ6flYUXDYQUyqUPYB4BeaGfw9EVWest0Qg1s8LBmybjAEiglbIK", "Milan", "Milan", "ADMIN", true);
insert into user(id, email, password, first_name, last_name, role, enabled) values(3, "pera.pera@gmail.com", "$2y$10$fKJLrmLX5b8DEA2iIV9BJ.attAgPF7boOv63z6.aGh0Bb8YUKavDC", "Pera", "Pera", "SUPER_ADMIN", true);

insert into image_model(id, name, pic_byte, type) values(1, "Image 1", NULL, "png");
insert into image_model(id, name, pic_byte, type) values(2, "Image 2", NULL, "png");
insert into image_model(id, name, pic_byte, type) values(3, "Image 3", NULL, "png");