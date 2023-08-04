package com.github.nogueiralegacy.construcao;

import com.github.nogueiralegacy.construcao.banco.repository.UsuarioRepository;
import com.github.nogueiralegacy.construcao.domain.Usuario;
import com.github.nogueiralegacy.construcao.domain.UsuarioRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuxiliarTest {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario setUpUsuario() {
        Usuario usuario = new Usuario();

        usuario.setNome("Testando");
        usuario.setNickname("testador");
        usuario.setEmail("test@gmail.com");
        usuario.setPassword("test123");
        usuario.setRole(UsuarioRole.USER);

        return usuarioRepository.save(usuario);
    }

    public void tearDownUsuario(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }
}
