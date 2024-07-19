package com.example.msvc.cursos.services;

import com.example.msvc.cursos.clients.UserClientRest;
import com.example.msvc.cursos.models.User;
import com.example.msvc.cursos.models.entity.Course;
import com.example.msvc.cursos.models.entity.CourseUser;
import com.example.msvc.cursos.repositories.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    UserClientRest userClientRest;

    @Override
    @Transactional(readOnly = true)
    public List<Course> list(){
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Course> byId(Long id){
        return courseRepository.findById(id);
    }

    @Override
    @Transactional
    public Course save(Course course){
        return courseRepository.save(course);
    }

    @Override
    @Transactional(readOnly = true)
    public void delete(Long id){
        courseRepository.deleteById(id);
    }

    @Override
    public Optional<User> addUser(User user, Long courseId){
        Optional<Course> o = courseRepository.findById(courseId);
        if(o.isPresent())
        {
            User userMicro = userClientRest.detail(user.getId());

            Course course = o.get();
            CourseUser courseUser = new CourseUser();
            courseUser.setUserId(user.getId());

            course.addCourseUser(courseUser);
            courseRepository.save(course);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> createUser(User user, Long courseId){
        Optional<Course> o = courseRepository.findById(courseId);
        if(o.isPresent())
        {
            User userMicro = userClientRest.create(user);

            Course course = o.get();
            CourseUser courseUser = new CourseUser();
            courseUser.setUserId(userMicro.getId());

            course.addCourseUser(courseUser);
            courseRepository.save(course);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> deleteUser(Long id, Long cursoId){
        Optional<Course> o = courseRepository.findById(cursoId);
        if(o.isPresent())
        {
            User userMicro = userClientRest.detail(id);

            Course course = o.get();
            course.getCourseUsers().removeIf(cu -> cu.getUserId().equals(id));
            courseRepository.save(course);
        }
        return Optional.empty();
    }

}
