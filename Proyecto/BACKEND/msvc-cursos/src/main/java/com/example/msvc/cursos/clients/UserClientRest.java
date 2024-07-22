package com.example.msvc.cursos.clients;

import com.example.msvc.cursos.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "msvc-users", url = "http://localhost:8001")
public interface UserClientRest {

    @GetMapping("/users/{id}")
    User detail(@PathVariable("id") Long id);

    @PostMapping("/users")
    User create(User user);
}
