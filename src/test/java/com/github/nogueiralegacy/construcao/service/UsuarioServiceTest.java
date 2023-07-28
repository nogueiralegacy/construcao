package com.github.nogueiralegacy.construcao.service;

import com.github.nogueiralegacy.construcao.domain.Usuario;
import com.github.nogueiralegacy.construcao.domain.UsuarioRole;
import com.github.nogueiralegacy.construcao.banco.repository.UsuarioRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsuarioServiceTest {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

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
    void findByNicknameValido() {
        String nickname = "testador";
        Usuario usuarioRetornado = usuarioService.findByNickname(nickname);

        assertEquals(usuario, usuarioRetornado);
    }

    @Test
    void findByNicknameInvalido() {
        String nickname = "NAO EXISTE";

        assertThrows(IllegalArgumentException.class, () -> usuarioService.findByNickname(nickname));
    }

    @Test
    void findByNicknameNull() {
        String nickname = null;

        assertThrows(IllegalArgumentException.class, () -> usuarioService.findByNickname(nickname));
    }

    @Test
    void  findByNicknamesValido() {
        String[] nicknames = {"testador"};

        Usuario usuarioRetornado = usuarioService.findByNicknames(nicknames).iterator().next();

        assertEquals(usuario, usuarioRetornado);
    }

    @Test
    void findByNicknamesInvalido() {
        String[] nicknames = {"NAO EXISTE"};

        assertThrows(IllegalArgumentException.class, () -> usuarioService.findByNicknames(nicknames));
    }

    @Test
    void findByNicknamesNull() {
        String[] nicknames = null;

        Set<Usuario> resultadoEsperado = new HashSet<>();

        assertEquals(resultadoEsperado, usuarioService.findByNicknames(nicknames));
    }
}
