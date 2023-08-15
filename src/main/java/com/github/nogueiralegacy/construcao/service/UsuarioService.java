package com.github.nogueiralegacy.construcao.service;

import com.github.nogueiralegacy.construcao.banco.BCryptHasher;
import com.github.nogueiralegacy.construcao.domain.Usuario;
import com.github.nogueiralegacy.construcao.banco.repository.UsuarioRepository;
import com.github.nogueiralegacy.construcao.utils.dto.UsuarioDTO;
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

    public void saveUsuario(UsuarioDTO usuarioDTO) throws IllegalArgumentException {
        if (!isUsuarioValido(usuarioDTO)) {
            throw new IllegalArgumentException("Formato de usuario inválido, os campos 'nickname', 'password', 'nome', 'email' e 'role' são obrigatórios");
        }
        if (usuarioExistsByNickname(usuarioDTO.nickname())) {
            throw new IllegalArgumentException("Nickname já cadastrado");
        }

        if (usuarioExistsByEmail(usuarioDTO.email())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        String passwordHash = new BCryptHasher().encode(usuarioDTO.password());

        Usuario usuario = new Usuario(usuarioDTO.nickname(), passwordHash, usuarioDTO.nome(), usuarioDTO.email(), usuarioDTO.role());

        usuarioRepository.save(usuario);
    }

    private boolean isUsuarioValido(UsuarioDTO usuarioDTO) {
        return usuarioDTO != null && usuarioDTO.nickname() != null && usuarioDTO.password() != null && usuarioDTO.nome() != null && usuarioDTO.email() != null && usuarioDTO.role() != null;
    }

    public boolean usuarioExistsByNickname(String nickname) {
        if (nickname == null) {
            throw new IllegalArgumentException("O parametro 'nickname' não pode ser nulo");
        }
        return usuarioRepository.existsByNickname(nickname);
    }

    public boolean usuarioExistsByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("O parametro 'email' não pode ser nulo");
        }
        return usuarioRepository.existsByEmail(email);
    }

    public Iterable<Usuario> findAll() {
        return usuarioRepository.findAll();
    }
}
