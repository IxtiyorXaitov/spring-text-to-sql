CREATE TABLE department (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    building VARCHAR(50)
);

CREATE TABLE professor (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    title VARCHAR(50),
    department_id BIGINT REFERENCES department(id)
);

CREATE TABLE student (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    enrollment_year INT
);

CREATE TABLE course (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    credits INTEGER,
    department_id BIGINT REFERENCES department(id)
);

CREATE TABLE course_professor (
    id BIGSERIAL PRIMARY KEY,
    professor_id BIGINT REFERENCES professor(id),
    course_id BIGINT REFERENCES course(id),
    semester VARCHAR(10),
    year INT
);

CREATE TABLE course_student (
    id BIGSERIAL PRIMARY KEY,
    student_id BIGINT REFERENCES student(id),
    course_id BIGINT REFERENCES course(id),
    grade VARCHAR(2),
    semester VARCHAR(10),
    year INT
);
