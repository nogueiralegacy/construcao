package com.github.nogueiralegacy.construcao.banco.entities;


import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UsuarioRepositoryTest {
    @Autowired
    UsuarioRepository usuarioRepository;

    Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();

        usuario.setUsername("Naldo");
        usuario.setEmail("naldo@gmail.com");
        usuario.setNickname("Naldinho");
        usuario.setPassword("naldo123");
    }

    @Test
    void buscandoUsuarioSalvoPeloUsername() {
        usuario = usuarioRepository.save(usuario);

        Usuario usuarioRetornado = usuarioRepository.findByUsername("Naldo");
        assertEquals(usuario, usuarioRetornado);

        usuarioRepository.delete(usuario);
    }

}
