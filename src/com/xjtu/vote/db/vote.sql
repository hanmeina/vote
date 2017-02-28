use mydb3;
drop table if exists vote;
create table vote(
 id int primary key auto_increment,
 content varchar(30) not null,
 ticket int default 1
);
insert into vote(content) values('²Ü²Ù');
insert into vote(content) values('Áõ±¸');
insert into vote(content) values('ËïÈ¨');
insert into vote(content) values('Öî¸ğÁÁ');
select * from vote;




