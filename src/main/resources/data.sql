-- Insert users
INSERT INTO app_user (id, username, password, role) VALUES
(1, 'admin', '$2a$10$CmCRCricC/a7y4GaFUZVfuy8JoJcoGWDhlY2HxSIVjzL2dvMybL4m', 'ADMIN'),
(2, 'user1', '$2a$10$Vpm1SJnrKrg4TtigMRhuM.7wyGV46qKfVmBG6MoxJDdA8U6W/CX8y', 'USER'),
(3, 'user2', '$2a$10$ceRnmfKlmXGJsUjof3ljXuMQ2IhFQbIem25P.o8jtisP79ICjFrvy', 'USER');

-- Insert comments
INSERT INTO comment (id, content, author_id) VALUES
(1, 'This is the first comment.', 2),
(2, 'This is the second comment.', 2),
(3, 'This is the third comment.', 2),
(4, 'This is the fourth comment.', 3),
(5, 'This is the fifth comment.', 3),
(6, 'This is the sixth comment.', 3);