package com.example.msvc.cursos.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name="course_user")
public class CourseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_course", unique = true)
    private Long userId;

    public Long getIdCourse(){
        return userId;
    }

    public void setIdCourse(Long idCourse){
        this.userId = idCourse;
    }

    public Long getUserId(){
        return userId;
    }

    public void setUserId(Long UserId){
        this.userId = userId;
    }

}
