INSERT INTO employ_pos(empl_id, job_description, position)
VALUES (1, '����� ������ ���', '��������'),
       (2, '�� ����� ����� ������ ���', '��� ���������'),
       (3, '�������� ������, ���-�� ������', '��������'),
       (4, '������� �������', '���������')
ON CONFLICT (empl_id)
    DO NOTHING;

