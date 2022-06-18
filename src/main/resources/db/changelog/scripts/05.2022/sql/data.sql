insert into requisites (id, account_number, bik, kpp, snils, passport_series, passport_number, inn, bank_name, corr, first_name, last_name, patronymic_name, passport_issued, passport_issuer_code, passport_issued_date, registration_address)
values ('5471e442-dcf7-11ec-9d64-0242ac120002','00000000000000000000','163947269','3819358104','39481380054','4529','294823','458135492857','SomeBank','777498832679125488245','Test','Test','Test','435','3453','2022-05-26 16:28:11','Somewhere'),
       ('8fe0b4c2-dcf7-11ec-9d64-0242ac120002','11112222333344445555','123445667','6428434845','79642154177','7785','751967','775149424617','SomeAnotherBank','23414988326791252354',null,null,null,'435','3453','2022-05-26 16:28:11','Somewhere');

insert into salary(id, available_cash, rate, earned_for_month, date)
values ('3be2bdec-dcf8-11ec-9d64-0242ac120002',30000,40000,30000,'2022-05-26 13:32:16.722');

insert into employer(id, name, email, requisites_id)
values ('7afcccda-dcf7-11ec-9d64-0242ac120002','testCompany','some@email.ru','8fe0b4c2-dcf7-11ec-9d64-0242ac120002');

insert into employee(id, phone, status,employer_id, salary_id, requisites_id)
values ('82290bf8-dcf8-11ec-9d64-0242ac120002','89991002244','ENABLED_EMPLOYEE','7afcccda-dcf7-11ec-9d64-0242ac120002','3be2bdec-dcf8-11ec-9d64-0242ac120002','5471e442-dcf7-11ec-9d64-0242ac120002');