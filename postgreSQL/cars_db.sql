/*1. Нужно написать SQL скрипты:

Создать структур данных в базе.
Таблицы.
   Кузов. Двигатель, Коробка передач.*/
create table carcase(
	id serial primary key,
	name varchar(255)
);
create table engine(
	id serial primary key,
	name varchar(255)
);
create table transmission(
	id serial primary key,
	name varchar(255)
);
/*Создать структуру Машина. Машина не может существовать без данных из п.1.*/
create table car(
	id serial primary key,
	name varchar(255),
	carcase_id int references carcase(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);
/*Заполнить таблицы через insert.*/
insert into carcase(name) values ('hatchback'), ('sedan'), ('pickup');
insert into engine(name) values ('petrol'), ('diesel'), ('electrical');
insert into transmission(name) values ('manual'), ('auto'), ('hybrid');
insert into car(name, carcase_id, engine_id, transmission_id) values
('Tayota Camry', 2, 1, 2), ('Skoda Octavia', 2, 2, 1), ('Volkswagen Amarok', 3, 1, 1);
/*2. Создать SQL запросы:
1) Вывести список всех машин и все привязанные к ним детали.*/
select c.name as car_model, cc.name as carcase_type, e.name as engine_type, t.name as transmission
from car as c
inner join carcase as cc
on c.carcase_id = cc.id
inner join engine as e
on c.engine_id = e.id
inner join transmission as t
on c.transmission_id = t.id;
/*2) Вывести отдельно детали (1 деталь - 1 запрос), 
которые не используются НИ в одной машине, кузова, двигатели, коробки передач.*/
select cc.name as carcase from carcase as cc 
left join car as c
on c.carcase_id = cc.id
where c.id is null;

select e.name as engine from engine as e
left join car as c
on c.engine_id = e.id
where c.id is null;

select t.name as transmission from transmission as t
left join car as c
on c.transmission_id = t.id
where c.id is null;