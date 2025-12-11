create table movies
(
    id           int auto_increment
        primary key,
    title        varchar(255) not null,
    director     varchar(255) null,
    release_year date         null,
    rating       int          null
);

create table users
(
    id       int auto_increment
        primary key,
    username varchar(255)         not null,
    password varchar(255)         null,
    is_admin tinyint(1) default 0 not null
);

