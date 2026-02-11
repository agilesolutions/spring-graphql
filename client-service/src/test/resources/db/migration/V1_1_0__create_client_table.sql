CREATE TABLE if not exists client (
    id          BIGINT primary key auto_increment   not null,
    first_name     varchar                          not null,
    middle_name     varchar                         not null,
    last_name     varchar                           not null
);