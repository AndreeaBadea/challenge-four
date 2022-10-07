INSERT INTO players (age, email, gender, is_subscriber, username)
VALUES (20, 'test1@gmail.com', 'FEMALE', TRUE, 'anna.18'),
       (33, 'test2@gmail.com', 'MALE', FALSE, 'tom.tom'),
       (25, 'test3@gmail.com', 'FEMALE', TRUE, 'emma20'),
       (35, 'test4@gmail.com', 'MALE', FALSE, 'bob_abc');

INSERT INTO sessions (difficulty_level, score, player_id)
VALUES (20, 500, 1), (15, 55, 1),
       (10, 666, 2), (40, 34, 2),
       (30, 666, 3), (25, 53, 3), (13, 344, 3),
       (14, 2345,4), (40, 343, 4), (30, 333, 4), (23, 5335, 4);

INSERT INTO invaders (x_coordinate, y_coordinate)
VALUES (20, 11), (1, 11), (1, 5), (6, 7), (8, 9), (13, 11), (4, 6), (7, 8), (9, 25), (13, 16);

INSERT INTO sessions_space_invaders (sessions_id, space_invaders_id)
VALUES (1, 1), (2, 2),
       (3, 3), (4, 4),
       (5, 5), (6, 6),
       (7, 7), (7, 8),
       (8, 9), (8, 10);