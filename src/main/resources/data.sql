INSERT INTO employ_pos(id, cod_type, job_description, position)
VALUES (1, '001', 'Самый важный тут', 'Директор'),
       (2, '002', 'Не менее самый важный тут', 'Зам директора'),
       (3, '301', 'Работает руками, что-то делает', 'Работник'),
       (4, '201', 'Считает денюжку', 'Бухгалтер')
ON CONFLICT (id)
    DO NOTHING;


insert into support_org(send_email_birthday)
VALUES (true);

insert into organization (address, inn, name, ogrn, phone_number, zip, support_org_sup_id, email)
VALUES ('Москва, Пионерская д.2', '1234567891', 'Рога и копыта', '1234567891234', '89998887766', '111000', 1,
        'teman94@mail.ru')
;

INSERT INTO worker (birthday, delete, name, patronymic, phone_number, surname, work_now, org_id,
                    family_status, minor_children, gender, email)
VALUES ('2007-11-02', FALSE, 'Иван', 'Васильевич', '+79998885544', 'Пупкин', TRUE, 1, 'SINGLE', 'true', 'MALE',
        'superMan@mail.ru'),
       ('2000-11-02', FALSE, 'Максим', 'Валерьевич', '+79990005544', 'Приколдесов', TRUE, 1, 'MARRIED', 'true',
        'MALE', 'bigBoss@mail.ru')
ON CONFLICT (id)
    DO NOTHING;
insert into salary(base_rate, index_rate)
VALUES (100000, 1.25),
       (140000, 1.15),
       (102000, 1.25);
insert into work_history (start_work, work_now, empl_id, worker_id, salary_id)
VALUES ('2023-10-15', True, 3, 1, 1),
       ('2023-11-15', True, 3, 2, 2)
ON CONFLICT (id)
    DO NOTHING;


insert into work_history (end_work, start_work, work_now, empl_id, worker_id, salary_id)
VALUES ('2023-10-15', '2022-01-15', false, 3, 1, 3)
ON CONFLICT (id)
    DO NOTHING;

insert into type_document (type_dock_id, actual, code_type_document, name_document, identity)
VALUES (1, true, '001', 'Паспорт РФ', true),
       (2, true, '002', 'Диплом о высшем образовании', false)
ON CONFLICT (type_dock_id)
    DO NOTHING;

insert into paper_document (id, actual, issued, number, series, who_issued, type_document, worker_id)
VALUES (1, true, '2023-10-15', '5615', '123456', 'Best police department from south BUTOVO', '001', 1),
       (2, true, '2023-10-15', '5614', '123457', 'Best police department from north BUTOVO', '001', 2)