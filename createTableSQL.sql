use discord_clone;


drop table MESSAGE;
drop table INVITATION;
drop table CHANNEL;
drop table SERVER;
drop TABLE USER;

create TABLE USER(
	_id int auto_increment not null,
    email varchar(30) not null,
    password varchar(100) not null,
    name varchar(30) not null,
    avatar varchar(50) not null,
    is_enabled tinyint(1) default null,
    certified_key varchar(50) default null,
    primary key(_id,email)
);
create TABLE SERVER(
	_id int auto_increment not null,
    name varchar(40) not null,
    host int not null,
    primary key(_id),
    foreign key(host) references USER (_id)
);
create table CHANNEL(
	_id int auto_increment not null,
    name varchar(40) not null,
    type int not null,
    server int not null,
    primary key(_id),
    foreign key(server) references SERVER(_id)
);

create table INVITATION (
	_id int auto_increment not null,
    from_user int not null,
    to_user int not null,
    primary key(_id),
    foreign key(from_user) references USER(_id),
    foreign key(to_user) references USER(_id)
);
create table MESSAGE (
	_id int auto_increment not null,
    content varchar(1000) not null,
    from_user int not null,
    channel int not null,
    created_at timestamp not null default current_timestamp,
    primary key(_id),
    foreign key(from_user) references USER(_id),
    foreign key(channel) references CHANNEL(_id)
);
