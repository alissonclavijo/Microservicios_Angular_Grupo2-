package ec.edu.espe.usersapp.services;

import ec.edu.espe.usersapp.entity.User;
import ec.edu.espe.usersapp.UsersApplication;
import ec.edu.espe.usersapp.repositories.UsuarioRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = UsersApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @Transactional
    public void testSaveAndFindUser() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");

        User savedUser = userService.save(user);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();

        User foundUser = userService.getById(savedUser.getId()).orElse(null);
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getId()).isEqualTo(savedUser.getId());
    }

    @Test
    @Transactional
    public void testSaveAndDeleteUser() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");

        User savedUser = userService.save(user);
        assertThat(savedUser).isNotNull();
        userService.delete(savedUser.getId());

        User foundUser = userService.getById(savedUser.getId()).orElse(null);
        assertThat(foundUser).isNull();
    }

    @Test
    @Transactional
    public void testSaveAndUpdateUser() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");

        User savedUser = userService.save(user);
        assertThat(savedUser).isNotNull();

        savedUser.setName("Jane Doe");
        User updatedUser = userService.save(savedUser);
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getId()).isEqualTo(savedUser.getId());
        assertThat(updatedUser.getName()).isEqualTo("Jane Doe");

        User foundUser = userService.getById(updatedUser
                .getId()).orElse(null);
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getId()).isEqualTo(updatedUser.getId());
        assertThat(foundUser.getName()).isEqualTo("Jane Doe");
    }
}
