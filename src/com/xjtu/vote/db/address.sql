use mydb3;
drop table if exists address;
create table if not exists address(
	id int primary key auto_increment,
	location varchar(10) not null,
	ip varchar(30) not null
);
insert into address(location,ip) values('广州','127.0.0.1');
insert into address(location,ip) values('北京','192.168.10.1');
insert into address(location,ip) values('上海','192.168.10.2');
insert into address(location,ip) values('长沙','192.168.10.3');
insert into address(location,ip) values('武汉','192.168.10.4');
