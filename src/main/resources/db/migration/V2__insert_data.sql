-- Departments
INSERT INTO department (name, building)
VALUES ('Computer Science', 'Engineering Hall'),
       ('Mathematics', 'Science Center'),
       ('History', 'Liberal Arts Building');

-- Professors
INSERT INTO professor (name, title, department_id)
VALUES ('Dr. Alice Smith', 'Associate Professor', 1),
       ('Dr. Bob Johnson', 'Professor', 2),
       ('Dr. Carol White', 'Assistant Professor', 3),
       ('John Smith', 'Professor', 1),
       ('Alice Johnson', 'Associate Professor', 2),
       ('Robert Brown', 'Assistant Professor', 3);

-- Students
INSERT INTO student (name, email, enrollment_year)
VALUES ('John Doe', 'john.doe@example.com', 2022),
       ('Jane Roe', 'jane.roe@example.com', 2023),
       ('Mark Lee', 'mark.lee@example.com', 2024),
       ('Emma Williams', 'emma@example.com', 2022),
       ('Liam Johnson', 'liam@example.com', 2023),
       ('Olivia Davis', 'olivia@example.com', 2024),
       ('Noah Miller', 'noah@example.com', 2024),
       ('Sophia Wilson', 'sophia@example.com', 2023);

-- Courses
INSERT INTO course (name, credits, department_id)
VALUES ('Introduction to AI', 4, 1),
       ('Linear Algebra', 3, 2),
       ('World History', 3, 3),
       ('Database Systems', 4, 1),
       ('Data Structures', 3, 1);

-- Teaches
INSERT INTO course_professor (professor_id, course_id, semester, year)
VALUES (1, 1, 'Fall', 2024),
       (2, 2, 'Spring', 2024),
       (3, 3, 'Fall', 2024),
       (4, 1, 'Fall', 2024),
       (4, 4, 'Fall', 2024);

-- Enrollments
INSERT INTO course_student (student_id, course_id, grade, semester, year)
VALUES (1, 1, 'A', 'Fall', 2024),
       (2, 1, 'B', 'Fall', 2024),
       (2, 2, 'A-', 'Spring', 2024),
       (3, 3, 'B+', 'Fall', 2024),
       (3, 1, 'A', 'Fall', 2024),
       (4, 1, 'B+', 'Fall', 2024),
       (3, 5, 'A-', 'Spring', 2024),
       (4, 5, 'B', 'Spring', 2024),
       (2, 3, 'A', 'Fall', 2023),
       (5, 3, 'B', 'Fall', 2023);
