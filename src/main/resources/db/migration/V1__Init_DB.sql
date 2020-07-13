
create table task (
	id  bigserial not null,
	create_on_date timestamp,
	deadline date,
	description varchar(255),
	importance boolean,
	name varchar(255),
	status varchar(255),
	user_id int8,
	primary key (id)
	);

create table user_role (
	user_id int8 not null,
	roles varchar(255)
	);


create table usr (
	id  bigserial not null,
	active boolean,
	email varchar(255),
	password varchar(255),
	username varchar(255),
	primary key (id)
	);




alter table if exists task
add constraint task_user_fk
foreign key (user_id) references usr;



alter table if exists user_role
add constraint role_user_fk
foreign key (user_id) references usr;