create table type(
	id serial primary key, 
	name varchar(255)
);
create table product(
	id serial primary key, 
	name varchar(255), 
	type_id int references type(id), 
	expired_date date, 
	price float
);

insert into type(name) values ('СЫР'), ('МОЛОКО'), ('МОРОЖЕНОЕ'), ('ТВОРОГ'), ('СМЕТАНА');
insert into product(name, type_id, expired_date, price)
values ('Сыр мацарелла', 1, now() + interval '2 day', 199.99);
	   ('Сыр плавленный', 1, now() + interval '4 day', 39.9), 
	   ('Сыр Гауда', 1, now() + interval '10 day', 599.99),
	   ('Сыр Пармезан', 1, now() + interval '20 day', 999.99), 
	   ('Молоко пастеризованное', 2, now() - interval '2 day', 59.99), 
	   ('Молоко топленое', 2, now() + interval '4 day', 59.99),
	   ('Мороженое пломбир', 3, now() + interval '3 day', 33.99), 
	   ('Мороженое эскимо', 3, now() + interval '3 day', 43.99),
	   ('Мороженое лакомка', 3, now() + interval '3 day', 53.99)
	   ('Мороженое лакомка', 3, now() + interval '3 day', 53.99);
	   
select p.*, t.name from product as p
inner join type as t
on p.type_id = t.id
where t.name = 'СЫР';

select p.*, t.name from product as p
inner join type as t
on p.type_id = t.id
where p.name like '%мороженое%' or p.name like '%Мороженое%';

select p.name as product, p.expired_date as was_expired, p.expired_date - now() as days_ago
from product as p
where p.expired_date < now();

select t.name as type, p.name as product, p.price
from product as p
inner join type as t
on p.type_id = t.id
where p.price = (select max(p.price) from product as p);

select t.name as type, count(p.id)
from product as p
inner join type as t
on p.type_id = t.id
group by t.name;

select t.name, p.name from product as p
inner join type as t
on p.type_id = t.id
where t.name = 'СЫР' or t.name = 'МОЛОКО'
order by t.name asc;

select t.name as type, count(p.id)
from product as p
inner join type as t
on p.type_id = t.id
group by t.name
having count(p.id) < 10;

select t.name as type, p.name as product
from product as p
inner join type as t
on p.type_id = t.id
order by t.name asc;


