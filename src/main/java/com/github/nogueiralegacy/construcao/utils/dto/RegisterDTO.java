package com.github.nogueiralegacy.construcao.utils.dto;

import com.github.nogueiralegacy.construcao.banco.orm.Usuario;
import com.github.nogueiralegacy.construcao.banco.orm.UsuarioRole;

public record RegisterDTO(String nickname, String password, String nome, String email, String avatar, UsuarioRole role) {
    public Usuario toUsuario(String passwordHash) {
       Usuario usuario = new Usuario();
        usuario.setNickname(nickname);
        usuario.setPassword(passwordHash);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setAvatar(avatar);
        usuario.setRole(role);
        return usuario;
    }
}
