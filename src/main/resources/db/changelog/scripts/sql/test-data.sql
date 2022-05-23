INSERT INTO public.requisites (id, account_number, bik, snils, passport_series, passport_number, inn, first_name,
                               last_name, patronymic_name)
VALUES ('99789968-0736-4313-85ec-dc2f88ae2fb7', '1111111111111111', null, null,
        null, null, '562356', '302948122213', null, null);
INSERT INTO public.requisites (id, account_number, bik, snils, passport_series, passport_number, inn, first_name,
                               last_name, patronymic_name)
VALUES ('8e9f6c99-0909-4dc1-8d96-5b7ffcd31ef4', '1111111111111112', null, null, null,
        '123456', '302948122213', null, null, null);
INSERT INTO public.requisites (id, account_number, bik, snils, passport_series, passport_number, inn, first_name,
                               last_name, patronymic_name)
VALUES ('a2cb63ad-6b5c-437c-aacc-01f7fbcc7b0e', '1111111111111113', null, null, null,
        '562356', '302948122213', null, null, null);
INSERT INTO public.requisites (id, account_number, bik, snils, passport_series, passport_number, inn, first_name,
                               last_name, patronymic_name)
VALUES ('c87daddb-38d9-4244-87f4-3a5317000b45', '14958285854',
        '1111222233334444', null, '4444', '246782', '302948122213', 'Vladimir', 'Kozlov', 'TestPatronymic');
INSERT INTO public.requisites (id, account_number, bik, snils, passport_series, passport_number, inn, first_name,
                               last_name, patronymic_name)
VALUES ('1c278bd7-8624-424a-b560-7db0d8276fe8', '1111111111111114', null, null, null, null,
        '562356', '302948122213', null, null);
INSERT INTO public.requisites (id, account_number, bik, snils, passport_series, passport_number, inn, first_name,
                               last_name, patronymic_name)
VALUES ('5efa7be9-bcbb-4a4a-88ba-4a174d4cb547', '1111111111111115', null, null, null, null,
        null, 'Владимир', 'Козлов', null),
       ('0230f571-083a-4349-8802-a902b975f1c6', '1111111111111117', null, null, null, null,
        null, 'Владимир2', 'Козлов', null);
INSERT INTO public.requisites (id, account_number, bik, snils, passport_series, passport_number, inn, first_name,
                               last_name, patronymic_name)
VALUES ('0230f571-083a-4349-8802-a902b975f1c7', '1111111111111116', null,
        null, null, '562356', '302948122213', null, null, null);


INSERT INTO public.employee (id, employer_id, phone, status, requisites_id, created_at, updated_at)
VALUES ('c632a552-eb81-47df-ba99-38e78136a460', 'c07d87bd-c50b-4b93-aa94-d5951af0593b', '+79216522233', 'APPROVED',
        '99789968-0736-4313-85ec-dc2f88ae2fb7',
        '2021-12-06 01:19:17.529689',
        '2021-10-06 01:19:17.529689');
INSERT INTO public.employee (id, employer_id, phone, status, requisites_id, created_at, updated_at)
VALUES ('cd49035f-8f9d-4a92-86ca-dfa7b7549a5f', 'c07d87bd-c50b-4b93-aa94-d5951af0593b', null, 'NEW',
        '8e9f6c99-0909-4dc1-8d96-5b7ffcd31ef4',
        '2021-11-06 01:19:17.529689',
        '2021-10-06 01:19:17.529689');
INSERT INTO public.employee (id, employer_id, phone, status, requisites_id, created_at, updated_at)
VALUES ('0ae14c30-fadb-429b-b304-e7c8016ac2c4', 'c07d87bd-c50b-4b93-aa94-d5951af0593b', '89216522233', 'ENABLED',
        'a2cb63ad-6b5c-437c-aacc-01f7fbcc7b0e',
        '2021-11-06 01:19:17.529689',
        '2021-10-06 01:19:17.529689');
INSERT INTO public.employee (id, employer_id, phone, status, requisites_id, created_at, updated_at)
VALUES ('1b20cfb7-b3cf-4be5-be05-4157cc9b193e', 'c07d87bd-c50b-4b93-aa94-d5951af0593b', '89216522233', 'NEW',
        'c87daddb-38d9-4244-87f4-3a5317000b45',
        '2021-11-02 01:19:17.529000',
        '2021-10-06 01:19:17.529689');
INSERT INTO public.employee (id, employer_id, phone, status, requisites_id, created_at, updated_at)
VALUES ('9eb70d0b-9f40-4b27-a84e-a9338314e57e', 'c07d87bd-c50b-4b93-aa94-d5951af0593b', '89216522233', 'ENABLED',
        '1c278bd7-8624-424a-b560-7db0d8276fe8',
        '2021-11-07 01:19:17.529689',
        '2021-10-06 01:19:17.529689');
INSERT INTO public.employee (id, employer_id, phone, status, requisites_id, created_at, updated_at)
VALUES ('932bb706-1756-40f1-9829-216d8e059fed', 'c07d87bd-c50b-4b93-aa94-d5951af0593b', null, 'APPROVED',
        '5efa7be9-bcbb-4a4a-88ba-4a174d4cb547',
        '2022-01-13 03:35:05.412115',
        '2022-01-13 03:35:05.412115');
INSERT INTO public.employee (id, employer_id, phone, status, requisites_id, created_at, updated_at)
VALUES ('61c2c44e-747c-4331-be49-2b98c4bd83f3', 'c07d87bd-c50b-4b93-aa94-d5951af0593b', '89216522233', 'SIGN_OFFER',
        '0230f571-083a-4349-8802-a902b975f1c7',
        '2021-11-05 01:19:17.529000',
        '2021-10-06 01:19:17.529689'),
('61c2c44e-747c-4331-be49-2b98c4bd83f2', 'c07d87bd-c50b-4b93-aa94-d5951af0593b', '89216522233', 'SIGN_OFFER',
    '0230f571-083a-4349-8802-a902b975f1c6',
    '2021-11-05 01:19:17.529000',
    '2021-10-06 01:19:17.529689');


INSERT INTO public.position (id, is_checked_by_list, created_at, updated_at, employee_id)
VALUES ('3cfe5e70-5bd9-4a2b-a918-8d3cfd7fb986', true, '2021-10-06 01:19:17.529689',
        '2021-10-06 01:19:17.529689', 'cd49035f-8f9d-4a92-86ca-dfa7b7549a5f');
INSERT INTO public.position (id, is_checked_by_list, created_at, updated_at, employee_id)
VALUES ('15e19f9b-cd43-4637-8684-d81ec8a4fa8d', true, '2021-10-06 01:19:17.529689',
        '2021-10-06 01:19:17.529689', '1b20cfb7-b3cf-4be5-be05-4157cc9b193e');
INSERT INTO public.position (id, is_checked_by_list, created_at, updated_at, employee_id)
VALUES ('02d62eb6-17e9-4274-b363-37fc59dc0567', true, '2021-10-06 01:19:17.529689',
        '2021-10-06 01:19:17.529689', '61c2c44e-747c-4331-be49-2b98c4bd83f3');
INSERT INTO public.position (id, is_checked_by_list, created_at, updated_at, employee_id)
VALUES ('cfb5e35f-dd3e-4c0b-af3f-18a71c947b92', true, '2021-10-06 01:19:17.529689',
        '2021-10-06 01:19:17.529689', '9eb70d0b-9f40-4b27-a84e-a9338314e57e');
INSERT INTO public.position (id, is_checked_by_list, created_at, updated_at, employee_id)
VALUES ('65d661bc-773b-486b-b2d1-619ade8170f6', true, '2021-10-06 01:19:17.529689',
        '2021-10-06 01:19:17.529689', '0ae14c30-fadb-429b-b304-e7c8016ac2c4');
INSERT INTO public.position (id, is_checked_by_list, created_at, updated_at, employee_id)
VALUES ('e33ad031-7760-4f77-ad8a-1770a1bd9570', true, '2021-10-06 01:19:17.529689',
        '2021-10-06 01:19:17.529689', 'c632a552-eb81-47df-ba99-38e78136a460');
INSERT INTO public.position (id, is_checked_by_list, created_at, updated_at, employee_id)
VALUES ('98b43195-283e-4e38-ad31-008cde4a4b1e', false, '2022-01-13 03:35:05.440114',
        '2022-01-13 03:35:05.440114', '932bb706-1756-40f1-9829-216d8e059fed');


INSERT INTO public.salary (id, available_cash, salary_update_at, created_at, updated_at, rate,
                           earned_for_month, position_id, period)
VALUES ('77fc3325-9181-45e0-9590-ea736ec8ea25', 200, '2022-01-01 00:00:00.443000', '2022-01-01 00:00:00.443000',
        '2022-01-01 00:00:00.443000', null, 200, '02d62eb6-17e9-4274-b363-37fc59dc0567',
        '2022-01-01 00:00:00.443000');
INSERT INTO public.salary (id, available_cash, salary_update_at, created_at, updated_at, rate,
                           earned_for_month, position_id, period)
VALUES ('36b463f7-3078-4165-89eb-2f926fc5e64b', 5000, '2022-01-01 00:00:00.443000', '2022-01-01 00:00:00.443000',
        '2022-01-01 00:00:00.443000', null, 5000, '15e19f9b-cd43-4637-8684-d81ec8a4fa8d',
        '2022-01-01 00:00:00.443000');
INSERT INTO public.salary (id, available_cash, salary_update_at, created_at, updated_at, rate,
                           earned_for_month, position_id, period)
VALUES ('473e4dff-6002-47d3-b176-1b4c219a78d5', 10000, '2022-01-04 00:00:00.443000', '2022-01-01 00:00:00.443000',
        '2022-01-01 00:00:00.443000', null, 10000, '3cfe5e70-5bd9-4a2b-a918-8d3cfd7fb986',
        '2022-01-01 00:00:00.443000');
INSERT INTO public.salary (id, available_cash, salary_update_at, created_at, updated_at, rate,
                           earned_for_month, position_id, period)
VALUES ('4f961161-6cf2-42fb-9087-1bd2296241ee', 1000, '2021-12-01 00:00:00.443000', '2021-12-01 00:00:00.443000',
        '2021-12-01 00:00:00.443000', null, 1000, '65d661bc-773b-486b-b2d1-619ade8170f6',
        '2021-12-01 00:00:00.443000');
INSERT INTO public.salary (id, available_cash, salary_update_at, created_at, updated_at, rate,
                           earned_for_month, position_id, period)
VALUES ('6e7c874a-32cd-42f6-849d-20b95cfecaaf', 1000, '2022-01-02 00:00:00.443000', '2022-01-01 00:00:00.443000',
        '2022-01-01 00:00:00.443000', null, 988, '65d661bc-773b-486b-b2d1-619ade8170f6',
        '2022-01-01 00:00:00.443000');
INSERT INTO public.salary (id, available_cash, salary_update_at, created_at, updated_at, rate,
                           earned_for_month, position_id, period)
VALUES ('0dce06b3-a860-4f20-90c2-642694e2b723', 1000, '2022-01-03 00:00:00.443000', '2022-01-01 00:00:00.443000',
        '2022-01-01 00:00:00.443000', null, 1000, 'cfb5e35f-dd3e-4c0b-af3f-18a71c947b92',
        '2022-01-01 00:00:00.443000');



INSERT INTO public.transaction (id, employee_id, total_sum, date, status, approve_status, sign_id, notification_id,
                                created_at, updated_at, document_id)
VALUES ('2a4bc043-42ea-4279-858a-e702a43c91dd', '61c2c44e-747c-4331-be49-2b98c4bd83f3', 4000,
        '2021-12-04 04:26:28.630000', 'SUCCESS', 'APPROVED', null, '90efb7be-86fd-41d6-a306-c4f090e56a70',
        '2021-12-04 04:26:28.630000', '2021-12-04 04:26:28.630000', null);
INSERT INTO public.transaction (id, employee_id, total_sum, date, status, approve_status, sign_id, notification_id,
                                created_at, updated_at, document_id)
VALUES ('675445a4-2392-482f-a152-baa477d14777', '1b20cfb7-b3cf-4be5-be05-4157cc9b193e', 700,
        '2021-12-05 03:26:28.630000', 'SUCCESS', 'APPROVED', null, '90efb7be-86fd-41d6-a306-c4f090e56a70',
        '2021-12-05 03:26:28.630000', '2021-12-05 03:26:28.630000', null);
INSERT INTO public.transaction (id, employee_id, total_sum, date, status, approve_status, sign_id, notification_id,
                                created_at, updated_at, document_id)
VALUES ('5e8cd4f0-d15c-4232-9577-29be1bd544f1', '0ae14c30-fadb-429b-b304-e7c8016ac2c4', 1000,
        '2021-12-02 06:26:28.630000', 'SUCCESS', 'EXCEEDED', '5352fdc1-6ba8-4b74-9371-8a2109a7d30b',
        '90efb7be-86fd-41d6-a306-c4f090e56a70', '2021-12-02 06:26:28.630000', '2021-12-02 06:26:28.630000', null);
INSERT INTO public.transaction (id, employee_id, total_sum, date, status, approve_status, sign_id, notification_id,
                                created_at, updated_at, document_id)
VALUES ('5327e39e-9068-4891-b799-765437dca15d', '9eb70d0b-9f40-4b27-a84e-a9338314e57e', 4000,
        '2021-12-03 05:26:28.630000', 'SUCCESS', 'EXPIRED', 'e5c2cedd-7a72-4363-b5e8-ba3d00f6b1c2',
        '90efb7be-86fd-41d6-a306-c4f090e56a70', '2021-12-03 05:26:28.630000', '2021-12-03 05:26:28.630000', null);
INSERT INTO public.transaction (id, employee_id, total_sum, date, status, approve_status, sign_id, notification_id,
                                created_at, updated_at, document_id)
VALUES ('ef35477c-382b-45fe-b258-a13da91aa5ff', 'cd49035f-8f9d-4a92-86ca-dfa7b7549a5f', 1000,
        '2021-12-06 02:26:28.630000', 'SUCCESS', 'TO_APPROVE', null, '90efb7be-86fd-41d6-a306-c4f090e56a70',
        '2021-12-06 02:26:28.630000', '2021-12-06 02:26:28.630000', null);
INSERT INTO public.transaction (id, employee_id, total_sum, date, status, approve_status, sign_id, notification_id,
                                created_at, updated_at, document_id)
VALUES ('e86de2e6-e4eb-4bb2-aefd-dab8f3f555e2', 'cd49035f-8f9d-4a92-86ca-dfa7b7549a5f', 1000,
        '2021-12-06 02:26:28.630000', 'AWAITING_CONFIRMATION', 'TO_APPROVE', null,
        '90efb7be-86fd-41d6-a306-c4f090e56a70', '2021-12-06 02:26:28.630000', '2021-12-06 02:26:28.630000', null);
INSERT INTO public.transaction (id, employee_id, total_sum, date, status, approve_status, sign_id, notification_id,
                                created_at, updated_at, document_id)
VALUES ('98553250-0485-4cae-94ce-e96a2863c883', '9eb70d0b-9f40-4b27-a84e-a9338314e57e', 100,
        '2021-12-06 02:26:28.630000', 'AWAITING_CONFIRMATION', 'TO_APPROVE', null, null, '2022-01-16 00:46:44.813917',
        '2022-01-16 00:46:44.813917', null);



INSERT INTO public.payment (id, sum, date, processed, created_at, updated_at, commission, type, description,
                            transaction_id, position_id, period, vedomost_id)
VALUES ('ef35477c-382b-45fe-b258-a13da91aa5ff', 1000, '2021-12-06 02:26:28.630000', false, '2021-12-06 02:26:28.630000',
        '2021-12-06 02:26:28.630000', null, 'ODP_PAYMENT', 'выплата осуществленная сервисом odp',
        'ef35477c-382b-45fe-b258-a13da91aa5ff', '3cfe5e70-5bd9-4a2b-a918-8d3cfd7fb986', '2022-01-12 01:47:10.549869',
        null);
INSERT INTO public.payment (id, sum, date, processed, created_at, updated_at, commission, type, description,
                            transaction_id, position_id, period, vedomost_id)
VALUES ('bcf9779d-0f29-4410-852c-248056a4b992', 4000, '2021-12-04 04:26:28.630000', true, '2021-12-04 04:26:28.630000',
        '2021-12-04 04:26:28.630000', null, 'ODP_PAYMENT', 'выплата осуществленная сервисом odp',
        '2a4bc043-42ea-4279-858a-e702a43c91dd', '02d62eb6-17e9-4274-b363-37fc59dc0567', '2022-01-12 01:47:10.584179',
        null);
INSERT INTO public.payment (id, sum, date, processed, created_at, updated_at, commission, type, description,
                            transaction_id, position_id, period, vedomost_id)
VALUES ('497af915-b3d0-420c-ab58-b1018161e134', 4000, '2021-12-02 06:26:28.630000', true, '2021-12-02 06:26:28.630000',
        '2021-12-02 06:26:28.630000', null, 'ODP_PAYMENT', 'выплата осуществленная сервисом odp',
        '5e8cd4f0-d15c-4232-9577-29be1bd544f1', '65d661bc-773b-486b-b2d1-619ade8170f6', '2022-01-12 01:47:10.599387',
        'f5c70056-7d86-4486-b215-838c083ad42a');
INSERT INTO public.payment (id, sum, date, processed, created_at, updated_at, commission, type, description,
                            transaction_id, position_id, period, vedomost_id)
VALUES ('299b1823-5e11-49f9-af08-29a54ecef1b2', 700, '2021-10-06 04:21:15.009000', false, '2021-10-06 01:21:09.519463',
        '2021-10-06 01:21:09.519463', null, 'ODP_PAYMENT', 'выплата осуществленная сервисом odp',
        '675445a4-2392-482f-a152-baa477d14777', '15e19f9b-cd43-4637-8684-d81ec8a4fa8d', '2022-01-12 01:47:10.568668',
        null);


INSERT INTO public.service_stop_interval (id, employee_id, "from", "to", created_at, updated_at)
VALUES ('f5478678-4ab0-4707-a408-217668e5bc4c', 'cd49035f-8f9d-4a92-86ca-dfa7b7549a5f', '2021-10-08 01:54:06.444000',
        null, '2021-10-08 01:54:11.265000', '2021-10-08 01:54:13.257000');
INSERT INTO public.service_stop_interval (id, employee_id, "from", "to", created_at, updated_at)
VALUES ('01d6e526-16f1-4c6c-9b09-445815663dac', '1b20cfb7-b3cf-4be5-be05-4157cc9b193e', '2021-10-08 01:55:01.632000',
        null, '2021-10-07 22:55:13.931460', '2021-10-07 22:55:13.931460');
INSERT INTO public.service_stop_interval (id, employee_id, "from", "to", created_at, updated_at)
VALUES ('c8e4490f-ec26-4daa-8f2a-729b0109a2ca', '1b20cfb7-b3cf-4be5-be05-4157cc9b193e', '2021-10-04 01:55:27.891000',
        '2021-10-08 01:55:34.181000', '2021-10-07 22:55:37.088243', '2021-10-07 22:55:37.088243');

