create table contact
(
    id          uuid                    not null
        constraint contacts_pk
            primary key,
    employer_id uuid                    not null
        constraint contacts_employer_id_fk
            references employer,
    position    varchar(50),
    name        varchar(150),
    phone       varchar(20),
    email       varchar(100),
    created_at  timestamp default now() not null,
    updated_at  timestamp default now() not null
);

comment on column contact.position is 'Должность';
comment on column contact.name is 'Имя контактного лица';
comment on column contact.phone is 'Телефон контактного лица';
comment on column contact.email is 'Email контактного лица';
comment on column contact.created_at is 'Дата создания ';
comment on column contact.updated_at is 'Дата обновления ';

alter table employer
    drop column email;


insert into contact(id, employer_id, position, name, phone, email)
VALUES ('fb56ac96-de5c-11ec-9d64-0242ac120002','7afcccda-dcf7-11ec-9d64-0242ac120002','MANAGER','Иванов Александр Иванович','89998887766','manager@email.com'),
       ('956cd418-de5d-11ec-9d64-0242ac120002','7afcccda-dcf7-11ec-9d64-0242ac120002','EMPLOYERS_CONTACT','Матвеев Игорь Александрович','89995554758','contact2@email.com'),
       ('8909e0b2-de5d-11ec-9d64-0242ac120002','7afcccda-dcf7-11ec-9d64-0242ac120002','EMPLOYERS_CONTACT','Александров Олег Игоревич','88884441122','contact1@email.com')