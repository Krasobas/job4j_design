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
	   
/*1. Написать запрос получение всех продуктов с типом "СЫР"*/
select p.*, t.name from product as p
inner join type as t
on p.type_id = t.id
where t.name = 'СЫР';

/*2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"*/
select * from product as p
where lower(p.name) like '%мороженое%';

/*3. Написать запрос, который выводит все продукты, срок годности которых уже истек*/
select p.name as product, p.expired_date as was_expired, abs(date_part('day', p.expired_date - now())) as days_ago
from product as p
where p.expired_date < now();

/*4. Написать запрос, который выводит самый дорогой продукт.*/
select p.name as product, p.price
from product as p
where p.price = (select max(p.price) from product as p);

/*5. Написать запрос, который выводит для каждого типа 
количество продуктов к нему принадлежащих. В виде имя_типа, количество*/
select t.name as type, count(p.id)
from product as p
inner join type as t
on p.type_id = t.id
group by t.name;

/*6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"*/
select t.name, p.name from product as p
inner join type as t
on p.type_id = t.id
where t.name = 'СЫР' or t.name = 'МОЛОКО'
order by t.name asc;

/*7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.*/
select t.name as type, count(p.id)
from product as p
inner join type as t
on p.type_id = t.id
group by t.name
having count(p.id) < 10;

/*8. Вывести все продукты и их тип.*/
select t.name as type, p.name as product
from product as p
inner join type as t
on p.type_id = t.id
order by t.name asc;


