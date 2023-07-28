package com.github.nogueiralegacy.construcao.banco.orm;


import com.github.nogueiralegacy.construcao.banco.repository.UsuarioRepository;
import com.github.nogueiralegacy.construcao.domain.Usuario;
import com.github.nogueiralegacy.construcao.domain.UsuarioRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsuarioRepositoryTest {
    @Autowired
    UsuarioRepository usuarioRepository;

    Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();

        usuario.setNome("Testando");
        usuario.setNickname("testador");
        usuario.setEmail("test@gmail.com");
        usuario.setPassword("test123");
        usuario.setRole(UsuarioRole.USER);

        usuarioRepository.save(usuario);
    }

    @AfterEach
    void tearDown() {
        usuarioRepository.delete(usuario);
        usuario = null;
    }

    @Test
    void buscandoUsuarioSalvoPeloUsername() {
        Optional<Usuario> usuarioRetornado = usuarioRepository.findByNickname("testador");

        assertFalse(usuarioRetornado.isEmpty());
        assertEquals(usuario, usuarioRetornado.get());
    }

}
