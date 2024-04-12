insert into users(username, password, enabled) values('admin','admin',true);
insert into users(username, password, enabled) values('user','user',true);
insert into users(username, password, enabled) values('adviser','adviser',true);

insert into authorities(username, authority) values('admin','ADMIN');
insert into authorities(username, authority) values('user','USER');
insert into authorities(username, authority) values('adviser','ADVISER');

