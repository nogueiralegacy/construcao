package com.github.nogueiralegacy.construcao.utils.dto;

import com.github.nogueiralegacy.construcao.domain.Usuario;
import com.github.nogueiralegacy.construcao.domain.UsuarioRole;

public record RegisterUsuarioDTO(String nickname, String password, String nome, String email, String avatar, UsuarioRole role) {
    public Usuario toUsuario(String passwordHash) {
        Usuario usuario = new Usuario(nickname, passwordHash, nome, email, role);
        usuario.setAvatar(avatar);
        return usuario;
    }
}
