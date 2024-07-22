package ec.edu.espe.usersapp.services;

import ec.edu.espe.usersapp.entity.User;
import ec.edu.espe.usersapp.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class UserServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        User user1 = new User(1L, "John Doe", "john.doe@example.com", "password");
        User user2 = new User(2L, "Jane Doe", "jane.doe@example.com", "password");

        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userService.getAll();

        assertEquals(2, users.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void testGetById() {
        User user = new User(1L, "John Doe", "john.doe@example.com", "password");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getById(1L);

        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    void testSave() {
        User user = new User(1L, "John Doe", "john.doe@example.com", "password");

        when(usuarioRepository.save(any(User.class))).thenReturn(user);

        User result = userService.save(user);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
        verify(usuarioRepository, times(1)).save(user);
    }

    @Test
    void testDelete() {
        when(usuarioRepository.existsById(1L)).thenReturn(true);

        userService.delete(1L);

        verify(usuarioRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteNotFound() {
        when(usuarioRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            userService.delete(1L);
        });

        assertEquals("404 NOT_FOUND \"El id del usuario no existe o es incorrecto.\"", exception.getMessage());
        verify(usuarioRepository, times(0)).deleteById(1L);
    }

    @Test
    void testUpdate() {
        User user = new User(1L, "John Doe", "john.doe@example.com", "password");

        when(usuarioRepository.existsById(1L)).thenReturn(true);
        when(usuarioRepository.save(any(User.class))).thenReturn(user);

        userService.update(user);

        verify(usuarioRepository, times(1)).save(user);
    }

    @Test
    void testUpdateNotFound() {
        User user = new User(1L, "John Doe", "john.doe@example.com", "password");

        when(usuarioRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            userService.update(user);
        });

        assertEquals("404 NOT_FOUND \"El id del usuario no existe o es incorrecto.\"", exception.getMessage());
        verify(usuarioRepository, times(0)).save(user);
    }

}
