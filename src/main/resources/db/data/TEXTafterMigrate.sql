INSERT INTO tbl_permission (description) values ('ROLE_CREATE_CATEGORY');
INSERT INTO tbl_permission (description) values ('ROLE_SEARCH_CATEGORY');
INSERT INTO tbl_permission (description) values ('ROLE_CREATE_USER');
INSERT INTO tbl_permission (description) values ('ROLE_SEARCH_USER');
INSERT INTO tbl_permission (description) values ('ROLE_CREATE_NEWS');

INSERT INTO tbl_category (id_category, title, description, slug, active) values (1, 'Projeto social', 'Projeto social de Manaus', 'projeto-social', true);
INSERT INTO tbl_category (id_category, title, description, slug, active) values (2, 'Evento', 'Evento de break', 'evento', true);

INSERT INTO tbl_user (id_user, name_user, password_user, about_user, profession_user, photo_user, active) values (1, 'Gabriel Filipy', 'admin', 'Sou apaixonado por cultura', 'Software Enginer', 'PHOTO-001', true);

INSERT INTO tbl_news (id_news, title, content, photo_news, date, order_id, code_category, code_user, active) values 
	(1, 'Aniversário Monster Kings Crew 2022', 'A Monster King´s estará realizando um evento de Break essa semana', 'FOTO-DO-EVENTO', '2022-03-05', 1, 1, 1, true);