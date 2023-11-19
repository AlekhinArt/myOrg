INSERT INTO employ_pos(empl_id, job_description, position)
VALUES (1, 'Самый важный тут', 'Директор'),
       (2, 'Не менее самый важный тут', 'Зам директора'),
       (3, 'Работает руками, что-то делает', 'Работник'),
       (4, 'Считает денюжку', 'Бухгалтер')
ON CONFLICT (empl_id)
    DO NOTHING;

