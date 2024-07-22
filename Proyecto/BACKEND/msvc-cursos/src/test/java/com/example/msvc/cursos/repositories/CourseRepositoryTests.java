package com.example.msvc.cursos.repositories;

import com.example.msvc.cursos.MsvcCoursesApplication;
import com.example.msvc.cursos.models.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = MsvcCoursesApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
public class CourseRepositoryTests {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void testSaveAndFindCourse() {
        Course course = new Course();
        course.setName("Curso de Prueba");

        course = courseRepository.save(course);
        Course foundCourse = courseRepository.findById(course.getId()).orElse(null);

        assertThat(foundCourse).isNotNull();
        assertThat(foundCourse.getName()).isEqualTo("Curso de Prueba");
    }
}
