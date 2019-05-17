create table if not exists urldata
(
    id                identity     not null,
    short_url_code    varchar(32)  not null,
    long_url          varchar(255) not null,
    primary key (id)
);

create unique index short_url_index on urldata (short_url_code);