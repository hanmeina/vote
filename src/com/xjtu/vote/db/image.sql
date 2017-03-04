use mydb3;
drop table if exists image;
create table if not exists image(
	id int primary key auto_increment,
	path varchar(40) not null,
	cid int,
	constraint cid_FK foreign key(cid) references content(id) 
);
insert into image(path,cid) values('images/cc.jpg',1);
insert into image(path,cid) values('images/lb.jpg',2);
insert into image(path,cid) values('images/sq.jpg',3);
insert into image(path,cid) values('images/zgl.jpg',4);
select * from image;