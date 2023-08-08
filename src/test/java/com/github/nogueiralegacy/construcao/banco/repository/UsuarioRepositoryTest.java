package com.github.nogueiralegacy.construcao.banco.repository;


import com.github.nogueiralegacy.construcao.AuxiliarTest;
import com.github.nogueiralegacy.construcao.domain.Usuario;
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
    AuxiliarTest auxiliarTest;
    @Autowired
    UsuarioRepository usuarioRepository;

    Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = auxiliarTest.setUpUsuario();
    }

    @AfterEach
    void tearDown() {
        auxiliarTest.tearDownUsuario(usuario);
        usuario = null;
    }

    @Test
    void buscandoUsuarioSalvoPeloUsername() {
        Optional<Usuario> usuarioRetornado = usuarioRepository.findByNickname("testador");

        assertFalse(usuarioRetornado.isEmpty());
        assertEquals(usuario, usuarioRetornado.get());
    }

}
