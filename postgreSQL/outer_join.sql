/*Даны две сущности, представленные в таблицах departments и emploers.
Отношение one-to-many. В таблицах только поле name.
1. Создать таблицы и заполнить их начальными данными*/
create table departments(
	id serial primary key,
	name varchar(255)
);

create table emploers(
	id serial primary key,
	name varchar(255),
	department_id int references departments(id)
);

insert into departments(name) values
('backend'), ('frontend'), ('designer'), ('marketing');
insert into emploers(name, department_id) values
('java1', 1), ('java2', 1),
('js1', 2), ('js3', 2),
('ps1', 3), ('ps2', 3);
insert into emploers(name) values ('java3'), ('js2'), ('ps3');

/*2. Выполнить запросы с left, rigth, full, cross соединениями*/
select * from emploers as e left join departments as d
on e.department_id = d.id;
select * from emploers as e right join departments as d
on e.department_id = d.id;
select * from emploers as e full join departments as d
on e.department_id = d.id;
select * from emploers cross join departments;

/*3. Используя left join найти департаменты, у которых нет работников*/
select * from departments as d left join emploers as e
on d.id = e.department_id
where e.id is null;

/*4. Используя left и right join написать запросы, 
которые давали бы одинаковый результат 
(порядок вывода колонок в эти запросах также должен быть идентичный).*/
select e.*, d.* from emploers as e left join departments as d on e.department_id = d.id;
select e.*, d.* from departments as d right join emploers as e on e.department_id = d.id;

/*5. Создать таблицу teens с атрибутами name, gender и заполнить ее. 
Используя cross join составить все возможные разнополые пары*/
create type sex as enum('m', 'w');
create table teens(
	id serial primary key,
	name varchar(255),
	gender sex
);

insert into teens(name, gender) values
('Nik', 'm'), ('Max', 'm'), ('Luc', 'm'),
('Di', 'w'), ('Ann', 'w'), ('Eva', 'w');

select  * from
(select name from teens t
where t.gender = 'm') as m
cross join
(select name from teens t
where t.gender = 'w') as w;
