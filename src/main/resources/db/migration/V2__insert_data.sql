INSERT INTO roles (role_name)
        VALUES
    ('ADMIN'),
    ('USER');


INSERT INTO users_auto (name, password, role, email)
        VALUES
    ('Anton',  '123456785',  1, 'anton@mail.ru'),
    ('Oleg',   '745343473',  2, 'Oleg@mail.ru'),
    ('Anna',   '452625647',  2, 'Anna@mail.ru'),
    ('Andrey', '123444325',  2, 'Andrey@mail.ru'),
    ('Egor',   '176345575',  1, 'Egor@mail.ru');


INSERT INTO advertisements (name_advertisement, text, author_id, car_model, year)
        VALUES
    ('advertisements1', 'Text advertisements-1', 1,'Mazda',  2020),
    ('advertisements2', 'Text advertisements-2', 2, 'Audi',  2019),
    ('advertisements3', 'Text advertisements-3', 3, 'BMW',   2016),
    ('advertisements4', 'Text advertisements-4', 4, 'Honda', 2020),
    ('advertisements5', 'Text advertisements-5', 5, 'Suzuki',2016);