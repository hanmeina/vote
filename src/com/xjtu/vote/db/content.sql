use mydb3;
drop table if exists content;
create table content(
 id int primary key auto_increment,
 age int,
 description varchar(50) not null,
 vid int,
 constraint vid_FK foreign key(vid) references vote(id) 
);
insert into content(age,description,vid) values(55,'魏国军事家',1);
insert into content(age,description,vid) values(50,'蜀国军事家',2);
insert into content(age,description,vid) values(52,'吴国军事家',3);
insert into content(age,description,vid) values(48,'蜀国书记',4);
select * from content;





