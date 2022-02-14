create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('iphone', 999.99), ('mac', 9999.99), ('ipad', 1999.99);
insert into people(name) values ('Monica'), ('Chandler'), ('Joey'), ('Rachel'), ('Phoebe'), ('Ross');
insert into devices_people(device_id, people_id) values (3, 1);
insert into devices_people(device_id, people_id) values (1, 2), (2, 2);
insert into devices_people(device_id, people_id) values (1, 3), (3, 3);
insert into devices_people(device_id, people_id) values (1, 4), (2, 4), (3, 4);
insert into devices_people(device_id, people_id) values (1, 5);
insert into devices_people(device_id, people_id) values (2, 6);

select avg(d.price) as avg_price_of_all
from devices as d;

select p.name, count(d) as devices, avg(d.price) as avg_price
from devices_people as dp
inner join people as p
on dp.people_id = p.id
inner join devices as d
on dp.device_id = d.id
group by p.name

select p.name, count(d) as devices, avg(d.price) as avg_price
from devices_people as dp
inner join people as p
on dp.people_id = p.id
inner join devices as d
on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000
order by devices desc;
