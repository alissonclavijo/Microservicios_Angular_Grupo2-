package com.example.msvc.cursos.services;

import com.example.msvc.cursos.clients.UserClientRest;
import com.example.msvc.cursos.models.User;
import com.example.msvc.cursos.models.entity.Course;
import com.example.msvc.cursos.models.entity.CourseUser;
import com.example.msvc.cursos.repositories.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CourseServiceImplTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private UserClientRest userClientRest;

    @InjectMocks
    private CourseServiceImpl courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        Course course1 = new Course();
        course1.setId(1L);
        course1.setName("Course 1");

        Course course2 = new Course();
        course2.setId(2L);
        course2.setName("Course 2");

        when(courseRepository.findAll()).thenReturn(Arrays.asList(course1, course2));

        var courses = courseService.list();

        assertEquals(2, courses.size());
        verify(courseRepository, times(1)).findAll();
    }

    @Test
    void testAddUser() {
        Course course = new Course();
        course.setId(1L);
        course.setName("Course 1");

        User user = new User(2L, "John Doe", "john.doe@example.com", "password");

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(userClientRest.detail(2L)).thenReturn(user);
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        var result = courseService.addUser(new User(2L), 1L);

        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
        verify(courseRepository, times(1)).findById(1L);
        verify(userClientRest, times(1)).detail(2L);
        verify(courseRepository, times(1)).save(any(Course.class));
    }

    @Test
    void testById() {
        Course course = new Course();
        course.setId(1L);
        course.setName("Course 1");

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        var result = courseService.byId(1L);

        assertTrue(result.isPresent());
        assertEquals("Course 1", result.get().getName());
        verify(courseRepository, times(1)).findById(1L);
    }

    @Test
    void testSave() {
        Course course = new Course();
        course.setId(1L);
        course.setName("Course 1");

        when(courseRepository.save(any(Course.class))).thenReturn(course);

        var result = courseService.save(course);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void testDelete() {
        courseService.delete(1L);

        verify(courseRepository, times(1)).deleteById(1L);
    }

    @Test
    void testCreateUser() {
        Course course = new Course();
        course.setId(1L);
        course.setName("Course 1");

        User user = new User(2L, "John Doe", "john.doe@example.com", "password");

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(userClientRest.create(any(User.class))).thenReturn(user);
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        var result = courseService.createUser(new User(null, "John Doe", "john.doe@example.com", "password"), 1L);

        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
        verify(courseRepository, times(1)).findById(1L);
        verify(userClientRest, times(1)).create(any(User.class));
        verify(courseRepository, times(1)).save(any(Course.class));
    }

}
