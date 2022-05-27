alter table requisites
    rename column passport_issued_code to passport_issuer_code;
alter table salary
    DROP COLUMN employee_id