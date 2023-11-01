create table if not EXISTS worker
(
    worker_id    BIGINT GENERATED BY DEFAULT  AS IDENTITY NOT NULL,
    work_now     BOOLEAN not null,
    name         VARCHAR not null,
    surname      VARCHAR not null,
    patronymic   VARCHAR not null,
    birthday     DATE,
    phone_number VARCHAR not null,
    CONSTRAINT pk_worker PRIMARY KEY (worker_id)
);

create table if not EXISTS organization
(
    org_id BIGINT GENERATED BY DEFAULT  AS IDENTITY NOT NULL,
    name VARCHAR,
    inn VARCHAR,
    ogrn VARCHAR,
    address VARCHAR,
    phone_number VARCHAR,
    zip VARCHAR,
    workers BIGINT REFERENCES worker (worker_id),
    CONSTRAINT pk_organization PRIMARY KEY (org_id)

);
create table if not EXISTS employ_pos
(
    empl_id BIGINT GENERATED BY DEFAULT  AS IDENTITY NOT NULL,
    position VARCHAR not null,
    job_description VARCHAR not null,
    CONSTRAINT pk_empl PRIMARY KEY (empl_id)
    );
