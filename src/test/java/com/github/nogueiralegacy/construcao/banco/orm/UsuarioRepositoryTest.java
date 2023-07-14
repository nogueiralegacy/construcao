package com.github.nogueiralegacy.construcao.banco.orm;


import com.github.nogueiralegacy.construcao.banco.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.Set;

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
        usuario.setProjetos(Set.of());
    }

    @Test
    void buscandoUsuarioSalvoPeloUsername() {
        usuario = usuarioRepository.save(usuario);

        Optional<Usuario> usuarioRetornado = usuarioRepository.findByNickname("testador");

        assertFalse(usuarioRetornado.isEmpty());
        assertEquals(usuario, usuarioRetornado.get());

        usuarioRepository.delete(usuario);
    }

}
