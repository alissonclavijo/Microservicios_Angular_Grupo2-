package ec.edu.espe.usersapp.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-courses", url = "http://localhost:8002")
public interface CourseClientRest {

    @GetMapping("/courses/users/{userId}/exists")
    boolean isUserInAnyCourse(@PathVariable("userId") Long userId);
}



