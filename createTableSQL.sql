use discord_clone;
drop TABLE USER;
create TABLE USER(
	_id varchar(50) not null,
    email varchar(30) not null,
    password varchar(40) not null,
    name varchar(30) not null,
    avatar varchar(50) not null,
    primary key(_id,email)
);
drop table SERVER;
create TABLE SERVER(
	_id varchar(50) not null,
    name varchar(40) not null,
    host varchar(50) not null,
    primary key(_id),
    foreign key(host) references USER (_id)
);
drop table CHANNEL;
create table CHANNEL(
	_id varchar(50) not null,
    name varchar(40) not null,
    type int not null,
    server varchar(50) not null,
    primary key(_id),
    foreign key(server) references SERVER(_id)
);

drop table INVITATION;
create table INVITATION (
	_id varchar(50) not null,
    from_user varchar(50) not null,
    to_user varchar(50) not null,
    primary key(_id),
    foreign key(from_user) references USER(_id),
    foreign key(to_user) references USER(_id)
);

drop table MESSAGE;
create table MESSAGE (
	_id varchar(50) not null,
    content varchar(1000) not null,
    from_user varchar(50) not null,
    channel varchar(50) not null,
    created_at timestamp not null default current_timestamp,
    primary key(_id),
    foreign key(from_user) references USER(_id),
    foreign key(channel) references CHANNEL(_id)
);