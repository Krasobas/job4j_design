create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values('goldfish', 12000, date '1234-01-01');
insert into fauna(name, avg_age, discovery_date) values('okunfish', 13000, date '1312-01-01');
insert into fauna(name, avg_age) values('karasfish', 140);
insert into fauna(name, avg_age) values('shark', 40000);
insert into fauna(name, avg_age, discovery_date) values('kotopes', 30, date '1955-01-01');

select * from fauna where name like '%fish%';
select * from fauna where avg_age > 10000 and avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where extract (year from discovery_date) < '1950';


