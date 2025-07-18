package com.ixtiyor.springtexttosql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "course_student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseStudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private StudentEntity student;

    @ManyToOne(fetch = FetchType.LAZY)
    private CourseEntity course;

    @Column(name = "grade")
    private String grade;

    @Column(name = "semester")
    private String semester;

    @Column(name = "year")
    private Integer year;
}
