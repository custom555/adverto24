INSERT INTO
    application_user(first_name,last_name,email,password,telephone_no,city)
VALUES
    ('Jan','Kowalski','jan.kowalski@example.com','{noop}password','123 456 789','Warszawa'),
    ('Paweł','Nowak','pawel.nowak@example.com','{noop}password','789 456 123','Poznań');

INSERT INTO user_role(name,description)
VALUES
    ('ADMIN','pełne uprawnienia'),
    ('USER','możliwość dodawania ogłoszeń');

INSERT INTO user_roles(user_id,role_id)
VALUES
    (1,1),
    (2,2);