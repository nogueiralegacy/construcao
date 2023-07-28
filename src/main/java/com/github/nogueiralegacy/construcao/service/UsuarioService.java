package com.github.nogueiralegacy.construcao.service;

import com.github.nogueiralegacy.construcao.banco.orm.Usuario;
import com.github.nogueiralegacy.construcao.banco.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    public Usuario findByNickname(String nickname) {
        if (nickname == null) {
            throw new IllegalArgumentException("O parametro 'nickname' não pode ser nulo");
        }

        Optional<Usuario> usuario = usuarioRepository.findByNickname(nickname);

        if (usuario.isPresent()) {
            return usuario.get();
        }
        throw new IllegalArgumentException("Usuario não encotrado " + nickname);
    }

    public Set<Usuario> findByNicknames(String[] nicknames) throws IllegalArgumentException {
        if (nicknames == null) {
            return new HashSet<>();
        }

        Set<Usuario> usuarios = new HashSet<>();
        for (String nickname : nicknames) {
            usuarios.add(this.findByNickname(nickname));
        }
        return usuarios;
    }
}
