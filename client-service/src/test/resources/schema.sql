CREATE SCHEMA IF NOT EXISTS project;
CREATE TABLE IF NOT EXISTS project.client
(
    id bigserial NOT NULL,
    first_name character varying,
    middle_name character varying,
    last_name character varying
);