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
    private String id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ProfessorEntity professor;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CourseEntity course;

    @Column(name = "semester")
    private String semester;

    @Column(name = "year")
    private Integer year;
}
