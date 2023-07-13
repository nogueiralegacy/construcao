package com.github.nogueiralegacy.construcao.banco.orm;


import com.github.nogueiralegacy.construcao.banco.repository.UsuarioRepository;
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

        usuario.setNome("Naldo");
        usuario.setNickname("naldinho");
        usuario.setEmail("naldo@gmail.com");
        usuario.setPassword("naldo123");
    }

    @Test
    void buscandoUsuarioSalvoPeloUsername() {
        usuario = usuarioRepository.save(usuario);

        Usuario usuarioRetornado = usuarioRepository.findByNickname("naldinho");
        assertEquals(usuario, usuarioRetornado);

        usuarioRepository.delete(usuario);
    }

}
