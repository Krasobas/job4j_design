create table car(
	id serial primary key,
	name varchar(255),
	year smallint,
	color varchar(255),
	owner_id int
);
create table car_number(
	id serial primary key,
	number varchar(255),
	region smallint,
	car_id int references car(id) unique
);

insert into car(name, year, color) values('Lada Granta', 2010, 'white');
insert into car(name, year, color) values('Ford Focus', 2012, 'blue');

insert into car_number(number, region, car_id) values('A123BC', 174, 1);
insert into car_number(number, region, car_id) values('A123BC', 33, 2);

select number, region from car_number 
					  where car_id in (select id from car 
									   			 where (color = 'blue' and name = 'Ford Focus'));