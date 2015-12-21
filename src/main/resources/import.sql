insert into users(id, name, login, password) values (1,'George','admin','$2a$10$ahAMyHCRqFJv2wEguP.Fw.GtCAB/Ala8q3K6H8cf9C/69mrLi19e6');
insert into users(id, name, login, password) values (2,'User1','user','$2a$10$ahAMyHCRqFJv2wEguP.Fw.GtCAB/Ala8q3K6H8cf9C/69mrLi19e6');

 
insert into roles(id, name) values (1,'ROLE_USER');
insert into roles(id, name) values (2,'ROLE_ADMIN');
insert into roles(id, name) values (3,'ROLE_GUEST');

insert into user_role(user_id, role_id) values (1,1);
insert into user_role(user_id, role_id) values (1,2);
insert into user_role(user_id, role_id) values (2,1);
insert into user_role(user_id, role_id) values (3,1);

insert into oauth_client_details values ('clientapp', NULL, '{}', 'USER,ADMIN', 'password,refresh_token', '', '123456', NULL, 'restservice', 'read,write', '');

insert into restourants(id, description, name) values (1, 'Test Restaurant', 'Good Food');





