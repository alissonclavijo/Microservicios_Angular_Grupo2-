package com.example.msvc.cursos.controllers;

import com.example.msvc.cursos.models.User;
import com.example.msvc.cursos.models.entity.Course;
import com.example.msvc.cursos.services.CourseService;

import feign.FeignException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<Course> list(){
        return courseService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> byId(@PathVariable Long id){
        Optional<Course> course = courseService.byId(id);
        if(course.isPresent()){
            return ResponseEntity.ok(course.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Course course) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(course));
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Course course) {
        Optional<Course> courseOptional = courseService.byId(course.getId());
        if(courseOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(course));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Course> courseOptional = courseService.byId(id);
        if(courseOptional.isPresent()){
            courseService.delete(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/asignar-user/{idCurso}")
    public ResponseEntity<?> asignUser(@RequestBody User user, @PathVariable Long id){
        Optional<User> o;
        try {
            o = courseService.addUser(user, id);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("mensaje", "Error al agregar user" + e.getLocalizedMessage()));
        }

        if (o.isPresent()) {
            return ResponseEntity.ok(o.get());
        }
        return ResponseEntity.notFound().build();
    }


}
