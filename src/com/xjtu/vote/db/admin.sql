use mydb3;
drop table if exists  admin ;
create table admin(
 id int primary key auto_increment,
 username varchar(20) not null,
 password varchar(6) not null
);
insert into admin(username,password) values("jack","000000");
insert into admin(username,password) values("marry","111111");
select * from admin;








