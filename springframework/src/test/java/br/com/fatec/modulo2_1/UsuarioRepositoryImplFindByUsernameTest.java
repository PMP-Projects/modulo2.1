package br.com.fatec.modulo2_1;

import br.com.fatec.modulo2_1.entity.Usuario;
import br.com.fatec.modulo2_1.execption.UsuarioPersistenceException;
import br.com.fatec.modulo2_1.repository.UsuarioRepositoryImpl;
import br.com.fatec.modulo2_1.repository.client.UsuarioRepositoryWithMongodb;
import br.com.fatec.modulo2_1.repository.orm.UsuarioOrm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioRepositoryImplFindByUsernameTest {

    private PasswordEncoder encoder;
    private UsuarioRepositoryWithMongodb repository;
    private UsuarioRepositoryImpl usuarioRepository;

    @BeforeEach
    void setUp() {
        encoder = mock(PasswordEncoder.class);
        repository = mock(UsuarioRepositoryWithMongodb.class);
        usuarioRepository = new UsuarioRepositoryImpl(encoder, repository);
    }

    @Test
    void findByUsername_shouldReturnUserSuccessfully() {
        UsuarioOrm orm = new UsuarioOrm("1", "john", "encodedPassword");
        when(repository.findByUsername("john")).thenReturn(Optional.of(orm));

        Usuario usuario = usuarioRepository.findByUsername("john");

        assertNotNull(usuario);
        assertEquals("john", usuario.username());
    }


    @Test
    void findByUsername_shouldThrowUsernameNotFoundException() {
        when(repository.findByUsername("john")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class,
                () -> usuarioRepository.findByUsername("john"));
    }

    @Test
    void findByUsername_shouldThrowUsuarioPersistenceException_onOtherErrors() {
        when(repository.findByUsername("john")).thenThrow(new RuntimeException("Mongo down"));

        UsuarioPersistenceException exception = assertThrows(UsuarioPersistenceException.class,
                () -> usuarioRepository.findByUsername("john"));

        assertTrue(exception.getMessage().contains("Erro ao buscar usuário"));
    }
}
