package com.ixtiyor.springtexttosql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "course_professor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseProfessorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProfessorEntity professor;

    @ManyToOne(fetch = FetchType.LAZY)
    private CourseEntity course;

    @Column(name = "semester")
    private String semester;

    @Column(name = "year")
    private Integer year;
}
