INSERT INTO users (email, first_name, last_name, phone, role, password) VALUES
('admin@mail.ru', 'Админ', 'Админович', '+790001112223', 'ADMIN', '$2a$10$NlL5b0v9K1Zz6pQ8J8qyOeXrVdYwTgHfLmNpQsRtUvWxYzA1B2C3D4E5F6G7H8I9J0'),
('user@mail.ru', 'Юзер', 'Юзерович', '+79000111220038', 'USER', '$2a$10$NlL5b0v9K1Zz6pQ8J8qyOeXrVdYwTgHfLmNpQsRtUvWxYzA1B2C3D4E5F6G7H8I9J0'),
('petr@mail.ru', 'Тестер', 'Тестерович', '+90011166478', 'USER', '$2a$10$NlL5b0v9K1Zz6pQ8J8qyOeXrVdYwTgHfLmNpQsRtUvWxYzA1B2C3D4E5F6G7H8I9J0')
ON CONFLICT (email) DO NOTHING;

INSERT INTO ads (title, price, description, author_id) VALUES
('Продам гараж', 5, 'Каноничный батин гараж', 52),
('Куплю гитару', 7000, 'Гитара акустическая Fender', 10),
('Продам квартиру', 30 000 000, 'Квартира в самом сердце нашего прекрасного города', 1),
('Отдам котеечку-котофеечку', 0, 'Милый котенок, 2 месяца', 3);

INSERT INTO comments (text, ad_id, author_id) VALUES
('Отличный гараж, жалко расставаться!', 1, 1),
('Акустическая или электро-акустика?', 1, 2),
('Когда возможно посмотреть?', 3, 3),
('Можно забрать ?', 3, 2);