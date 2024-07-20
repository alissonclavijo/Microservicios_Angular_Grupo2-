package com.example.msvc.cursos.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="course_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_course", unique = true)
    private Long userId;

}
