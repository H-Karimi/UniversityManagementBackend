truncate table professor cascade ;
truncate table semester cascade;
truncate table faculty cascade;
truncate table student cascade;
truncate table course cascade;

INSERT INTO course(id, credit, title)
VALUES (1, 3, 'Math'),
       (2, 3, 'C Programming'),
       (3, 1, 'Lab'),
       (4, 2, 'Technical Language');

INSERT INTO faculty (id, head_professor_id, title)
VALUES (1, 1, 'Computer Engineering'),
       (2, 3, 'Computer Science');

INSERT INTO professor (id, birth_date, name, faculty)
VALUES (1, '1990-12-29 03:30:00.000000', 'Asghar', '1'),
       (2, '1980-12-01 03:30:00.000000', 'Akbar', '1'),
       (3, '1978-06-06 03:30:00.000000', 'Moshtoba', '2'),
       (4, '1985-01-30 03:30:00.000000', 'Jelal', '2');

INSERT INTO semester (id, end_date, start_date)
VALUES (1, '2020-02-15 00:00:00.000000', '2020-06-15 00:00:00.000000'),
       (2, '2020-09-15 00:00:00.000000', '2021-01-15 00:00:00.000000'),
       (3, '2021-02-15 00:00:00.000000', '2021-06-15 00:00:00.000000');

INSERT INTO student (id, birth_date, name, year, faculty)
VALUES (1, '2002-12-29 03:30:00.000000', 'Jafar', 1, 1),
       (2, '2000-12-29 03:30:00.000000', 'Ahmad', 3, 2),
       (3, '2000-05-01 03:30:00.000000', 'Mamad', 3, 1),
       (4, '2001-10-03 03:30:00.000000', 'Hasan', 2, 2);

INSERT INTO teaching_course(id, course_id, professor_id, semester_id)
VALUES (1, 2, 2, 2),
       (2, 3, 1, 2),
       (3, 4, 3, 3),
       (4, 1, 4, 1),
       (5, 2, 2, 1);

INSERT INTO taking_course(id, teaching_course_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5);

INSERT INTO courses_taken_by_students (student_id, course_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (2, 5),
       (3, 4),
       (3, 3),
       (3, 1),
       (4, 2),
       (4, 5);