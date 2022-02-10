create table film(
	id serial primary key,
	title varchar(255),
	year smallint
);
create table actor(
	id serial primary key,
	fname varchar(255),
	lname varchar(255)
);
create table films_actors(
	id serial primary key,
	film_id int references film(id),
	actor_id int references actor(id)
);

insert into film(title) values('Fury');
insert into film(title) values('Snatch');
insert into film(title) values('Wrath of Man');

insert into actor(fname, lname) values('Brad', 'Pitt');
insert into actor(fname, lname) values('Jason', 'Statham');

insert into films_actors(film_id, actor_id) values(1, 1);
insert into films_actors(film_id, actor_id) values(2, 1);
insert into films_actors(film_id, actor_id) values(2, 2);
insert into films_actors(film_id, actor_id) values(3, 2);

select id, fname, lname from actor where id in (select actor_id from films_actors 
								   where film_id in (select id from film 
								   where title = 'Snatch'));