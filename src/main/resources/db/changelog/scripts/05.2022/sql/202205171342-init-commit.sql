
create table employee
(
    id            uuid                    not null
        constraint employee_pk
            primary key,
    phone         varchar(15),
    status        varchar(50),
    created_at    timestamp default now() not null,
    updated_at    timestamp default now() not null
);

comment on table employee is 'Таблица хранящая данные о сотрудниках';

comment on column employee.phone is 'Телефон работника';
comment on column employee.status is 'Статус пользователя';
comment on column employee.created_at is 'Дата создания ';
comment on column employee.updated_at is 'Дата обновления ';

create table employer
(
    id                  uuid                    not null
        constraint employer_pk
            primary key,
    name                varchar                 not null,
    created_at          timestamp default now() not null,
    updated_at          timestamp default now() not null,
    email               varchar
);

comment on table employer is 'Таблица хранящая данные о работодателе';

comment on column employer.name is 'Название работодателя';
comment on column employer.created_at is 'Дата создания';
comment on column employer.updated_at is 'Дата обновления';
comment on column employer.email is 'Email работодателя';


-- auto-generated definition
create table requisites
(
    id                   uuid                    not null
        constraint requisites_pk
            primary key,
    account_number       varchar(100),
    bik                  varchar(100),
    kpp                  varchar(100),
    snils                varchar(100),
    passport_series      varchar(4),
    passport_number      varchar(6),
    inn                  varchar(12),
    bank_name            varchar,
    corr                 varchar,
    first_name           varchar(150),
    last_name            varchar(150),
    patronymic_name      varchar(150),
    created_at           timestamp default now() not null,
    updated_at           timestamp default now() not null,
    passport_issued      varchar(255),
    passport_issued_code varchar(6),
    passport_issued_date timestamp,
    registration_address varchar(225)
);

comment on table requisites is 'Таблица контактов работодателя';

comment on column requisites.account_number is 'Номер счета';
comment on column requisites.bik is 'БИК';
comment on column requisites.kpp is 'КПП';
comment on column requisites.snils is 'Cнилс (без проблелов и разделителей)';
comment on column requisites.passport_series is 'Серия паспорта';
comment on column requisites.passport_number is 'Номер паспорта';
comment on column requisites.inn is 'ИНН';
comment on column requisites.bank_name is 'Наименование банка';
comment on column requisites.corr is 'КОРР счет';
comment on column requisites.first_name is 'Имя работника';
comment on column requisites.last_name is 'Фамилия работника';
comment on column requisites.patronymic_name is 'Отчество работника';
comment on column requisites.created_at is 'Дата создания ';
comment on column requisites.updated_at is 'Дата обновления ';
comment on column requisites.passport_issued is 'Орган выдавший паспорт';
comment on column requisites.passport_issued_code is 'Код подразделения органа, выдавшего паспорт';
comment on column requisites.passport_issued_date is 'Дата выдачи паспорта работника';
comment on column requisites.registration_address is 'Адрес регистрации работника';

create table salary
(
    id               uuid                    not null
        constraint salary_pk
            primary key,
    available_cash   bigint    default 0,
    salary_update_at timestamp,
    created_at       timestamp default now() not null,
    updated_at       timestamp default now() not null,
    rate             bigint default 0,
    earned_for_month bigint    default 0,
    date           timestamp default now() not null
);

comment on table salary is 'Таблица хранящая данные о счете работника';

comment on column salary.available_cash is 'Средства доступные для вывода. Изменяется только работодателем. Сумма указанна в копейках';
comment on column salary.salary_update_at is 'дата обновления информации о счете';
comment on column salary.created_at is 'Дата создания';
comment on column salary.updated_at is 'Дата обновления';
comment on column salary.rate is 'Ставка сотрудника';
comment on column salary.earned_for_month is 'Средства, заработанный за месяц без учета досрочных вычетов. Изменяется только работодателем.';
comment on column salary.date is 'Дата трудоустройства';

-- auto-generated definition
create table transaction
(
    id                 uuid                                            not null
        constraint transaction_pk
            primary key,
    total_sum          bigint    default 0,
    date               timestamp default now()                         not null,
    status             varchar                                         not null,
    approve_status     varchar                                         not null,
    created_at         timestamp default now()                         not null,
    updated_at         timestamp default now()                         not null
);

comment on table transaction is 'Таблица хранящая данные о транзакции выплат';

comment on column transaction.total_sum is 'Общая сумма выплат';
comment on column transaction.date is 'Дата выполнения транзакции';
comment on column transaction.status is 'статус выполнения';
comment on column transaction.approve_status is 'Статус подтверждения';
comment on column transaction.created_at is 'Дата создания ';
comment on column transaction.updated_at is 'Дата обновления ';

alter table employee
    add column employer_id   uuid                    not null
            constraint employee_employer_id_fk
                references employer,
    add column salary_id   uuid                    not null
            constraint employee_salary_id_fk
                references salary,
    add column requisites_id uuid                    not null
            constraint employee_requisites_id_fk
                references requisites;

alter table employer
    add column requisites_id uuid                    not null
        constraint employee_requisites_id_fk
            references requisites;

alter table transaction
    add column employee_id        uuid                                            not null
        constraint transaction_employee_id_fk
            references employee;

alter table salary
    add column employee_id      uuid                    not null
        constraint salary_employee_id_fk
            references employee;

comment on column employee.employer_id is 'Идентификатор  работодателя';
comment on column employee.salary_id is 'Идентификатор счета работника';
comment on column employee.requisites_id is 'Идентификатор реквизитов работника';
comment on column employer.requisites_id is 'Идентификатор реквизитов работодателя';
comment on column transaction.employee_id is 'Идентификатор  работника';
comment on column salary.employee_id is 'Идентификатор работника';