create table autor(
	id serial primary key,
	fname varchar(255),
	lname varchar(255)
);
create table book(
	id serial primary key,
	name varchar(255),
	year smallint,
	autor_id int references autor(id)
);

insert into autor(fname, lname) values ('Leo', 'Tolstoy');
insert into autor(fname, lname) values ('Nikolai', 'Leskov');
insert into book(name, year, autor_id) values ('War and Peace', 1867, 1);
insert into book(name, year, autor_id) values ('Anna Karenina', 1878, 1);

select * from autor where id in (select autor_id from book);