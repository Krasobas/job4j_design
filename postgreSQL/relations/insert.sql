
insert into role(title) values ('something1');
insert into role(title) values ('something2');
insert into role(title) values ('something3');

insert into users(login, role_id) values ('first', 1);
insert into users(login, role_id) values ('second', 1);
insert into users(login, role_id) values ('third', 2);
insert into users(login, role_id) values ('fourth', 3);
insert into users(login, role_id) values ('fifth', 3);
insert into users(login, role_id) values ('sixth', 2);

insert into rules(rule_value) values('rule1');
insert into rules(rule_value) values('rule2');
insert into rules(rule_value) values('rule3');

insert into role_rules(role_id, rules_id) values(1, 1);
insert into role_rules(role_id, rules_id) values(1, 2);
insert into role_rules(role_id, rules_id) values(2, 1);
insert into role_rules(role_id, rules_id) values(2, 3);
insert into role_rules(role_id, rules_id) values(3, 1);
insert into role_rules(role_id, rules_id) values(3, 2);

insert into category(category_value) values ('category1');
insert into category(category_value) values ('category2');
insert into category(category_value) values ('category3');

insert into state(state_value) values ('state1');
insert into state(state_value) values ('state2');
insert into state(state_value) values ('state3');

insert into item(item_value, users_id, category_id, state_id) values('item1', 1, 1, 1 );
insert into item(item_value, users_id, category_id, state_id) values('item2', 2, 2, 2 );
insert into item(item_value, users_id, category_id, state_id) values('item3', 3, 3, 3 );

insert into comments(comment_value, item_id) values('comment1', 1);
insert into comments(comment_value, item_id) values('comment2', 1);
insert into comments(comment_value, item_id) values('comment3', 2);
insert into comments(comment_value, item_id) values('comment4', 2);
insert into comments(comment_value, item_id) values('comment5', 3);
insert into comments(comment_value, item_id) values('comment6', 3);

insert into attaches(attaches_value, item_id) values('attaches1', 1);
insert into attaches(attaches_value, item_id) values('attaches2', 1);
insert into attaches(attaches_value, item_id) values('attaches3', 2);
insert into attaches(attaches_value, item_id) values('attaches4', 2);
insert into attaches(attaches_value, item_id) values('attaches5', 3);
insert into attaches(attaches_value, item_id) values('attaches6', 3);