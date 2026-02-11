CREATE TABLE if not exists client (
    id bigserial NOT NULL,
    first_name     varchar                          not null,
    middle_name     varchar                         not null,
    last_name     varchar                           not null
);