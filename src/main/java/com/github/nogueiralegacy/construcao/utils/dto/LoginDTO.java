package com.github.nogueiralegacy.construcao.utils.dto;

import com.github.nogueiralegacy.construcao.api.security.Login;
import com.github.nogueiralegacy.construcao.banco.orm.Usuario;

public record LoginDTO (String username, String password) {

    public static Login usuarioToLogin(Usuario usuario) {
        return new Login(usuario.getNickname(), usuario.getPassword(), usuario.getRole());
    }

}
