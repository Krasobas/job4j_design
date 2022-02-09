create table programming(
	id serial primary key,
	name text,
	autor varchar(255),
	year smallint,
	isbn bigint,
	available boolean
);
insert into programming(name, autor, year, isbn, available) 
	   values('Thinking in Java', 'Eckel', 1998, 9780131872486, true);
insert into programming(name, autor, year, isbn, available) 
	   values('Head First Java', 'Sierra', 2003, 0596004656, false);
update programming set available = false;
select * from programming;
delete from programming;
drop table programming;

