create table if not exists shorturl
(
    id          integer      not null,
    short_url   varchar(32)  not null,
    long_url    varchar(255) not null,
    create_time timestamp    not null,
    primary key (id)
);
