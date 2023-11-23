INSERT INTO employ_pos(empl_id, cod_type, job_description, position)
VALUES (1, '001', '����� ������ ���', '��������'),
       (2, '002', '�� ����� ����� ������ ���', '��� ���������'),
       (3, '301', '�������� ������, ���-�� ������', '��������'),
       (4, '201', '������� �������', '���������')
ON CONFLICT (empl_id)
    DO NOTHING;

insert into organization (org_id, address, inn, name, ogrn, phone_number, zip)
VALUES (1, '������, ���������� �.2', '1234567891', '���� � ������', '1234567891234', '89998887766', '111000')
ON CONFLICT (org_id)
    DO NOTHING;

INSERT INTO worker (worker_id, birthday, delete, name, patronymic, phone_number, surname, work_now, org_id,
                    family_status, minor_children, gender)
VALUES ('1', '2007-11-02', FALSE, '����', '����������', '+79998885544', '������', TRUE, 1, 'SINGLE', 'true', 'MALE'),
       ('2', '2000-11-02', FALSE, '������', '����������', '+79990005544', '�����������', TRUE, 1, 'MARRIED', 'true',
        'MALE')
ON CONFLICT (worker_id)
    DO NOTHING;

insert into work_history (work_history_id, start_work, work_now, empl_id, worker_id)
VALUES (1, '2023-10-15', True, 3, 1),
       (2, '2023-11-15', True, 3, 2)
ON CONFLICT (work_history_id)
    DO NOTHING;
insert into work_history (work_history_id, end_work, start_work, work_now, empl_id, worker_id)
VALUES (3, '2023-10-15', '2022-01-15', false, 3, 1)
ON CONFLICT (work_history_id)
    DO NOTHING;
insert into type_document (type_dock_id, actual, code_type_document, name_document)
VALUES (1, true, '001', '�������'),
       (2, true, '002', '������ � ������ �����������')
ON CONFLICT (type_dock_id)
    DO NOTHING;

insert into passport (passport_id, actual, issued, number, series, who_issued, type_dock_id, worker_worker_id)
VALUES (1, true,'2023-10-15', '5615', '123456', 'Best police department from south BUTOVO', 1,1),
 (2, true,'2023-10-15', '5614', '123457', 'Best police department from north BUTOVO', 1,2)