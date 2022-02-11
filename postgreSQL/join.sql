create table owner(
	id serial primary key,
	name varchar(255),
	age smallint,
	status varchar(255)
);

create table pet(
	id serial primary key,
	name varchar(255),
	age smallint,
	species varchar(255),
	fictional bool,
	owner_id int references owner(id)
);

insert into owner(name, age, status)
values('Harry', 17, 'student');
insert into owner(name, age, status)
values('Dumbledore', 245, 'rector');
insert into owner(name, age, status)
values('Hagrid', 45, 'teacher');
insert into owner(name, age, status)
values('Hermione', 17, 'student');

insert into pet(name, age, species, fictional, owner_id)
values('Hedwig', 4, 'Snowy owl', false, 1);
insert into pet(name, age, species, fictional, owner_id)
values('Fawkes', '90', 'Phoenix', true, 2);
insert into pet(name, age, species, fictional, owner_id)
values('Buckbeak', 33, 'Hippogriff ', true, 3);
insert into pet(name, age, species, fictional, owner_id)
values('Crookshanks', 4, 'cat', false, 4);

select o.name as Wizard, p.name as Animal
from owner as o 
inner join pet as p
on p.owner_id = o.id
where  o.status = 'student';

select o.name as Wizard, p.species as Species
from owner as o 
inner join pet as p
on p.owner_id = o.id
where p.fictional is true;

select o.name as Wizard, p.name as Animal, p.species as Species
from owner as o 
inner join pet as p
on p.owner_id = o.id
where o.age > 16 and o.age < 50 and p.age > 10;


