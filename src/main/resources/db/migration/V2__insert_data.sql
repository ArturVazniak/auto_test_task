INSERT INTO roles (role_name)
        VALUES
            ('ADMIN'),
            ('USER');

INSERT INTO users_auto (name, password, email, enabled)
        VALUES
            ('Anton',  '$2y$12$uABVjiuAKNJhISeNqbVrZuFSO8xrBRi6rQgB9Eykda4511J/HyaFa',   'anton@mail.ru', true),
            ('Oleg',   '$2y$12$uABVjiuAKNJhISeNqbVrZuFSO8xrBRi6rQgB9Eykda4511J/HyaFa',   'Oleg@mail.ru', true);



INSERT INTO advertisements (name_advertisement, text, author_id, car_model, year, price)
        VALUES
            ('advertisements1', 'Text advertisements-1', 1,'Mazda',  2020, 2400.33),
            ('advertisements2', 'Text advertisements-2', 2, 'Audi',  2019, 2400.33),
            ('advertisements3', 'Text advertisements-3', 1, 'BMW',   2016, 7741.91),
            ('advertisements4', 'Text advertisements-4', 2, 'Honda', 2020, 4327.58),
            ('advertisements5', 'Text advertisements-5', 1, 'Suzuki',2016, 10321.62);

INSERT INTO users_roles (user_id, role_id)
        VALUES
            (1, 1),
            (2, 2);