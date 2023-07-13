package com.github.nogueiralegacy.construcao.banco.orm;


import com.github.nogueiralegacy.construcao.banco.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class UsuarioRepositoryTest {
    @Autowired
    UsuarioRepository usuarioRepository;

    Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();

        usuario.setNome("Naldo");
        usuario.setNickname("naldinho");
        usuario.setEmail("naldo@gmail.com");
        usuario.setPassword("naldo123");
        usuario.setProjetos(Set.of());
    }

    @Test
    void buscandoUsuarioSalvoPeloUsername() {
        usuario = usuarioRepository.save(usuario);

        Optional<Usuario> usuarioRetornado = usuarioRepository.findByNickname("naldinho");

        assertFalse(usuarioRetornado.isEmpty());
        assertEquals(usuario, usuarioRetornado.get());

        usuarioRepository.delete(usuario);
    }

}
