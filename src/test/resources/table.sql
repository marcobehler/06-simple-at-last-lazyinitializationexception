
create table series (

id int auto_increment primary key,
name varchar,
description varchar

)

insert into series (name, description) values ('Hibernate Layz Init Series', 'this is a description');

commit;


create table episodes (


id int auto_increment primary key,
name varchar,
description varchar,
series_id int,
foreign key (series_id) REFERENCES series (id)

)

insert into epsiodes (name, description, series_id) values ('our first episode', 'this is such a great episode', 1);
commit;