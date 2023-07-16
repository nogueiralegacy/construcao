package com.github.nogueiralegacy.construcao.utils.dto;

import com.github.nogueiralegacy.construcao.api.security.Login;
import com.github.nogueiralegacy.construcao.banco.orm.Usuario;
import com.github.nogueiralegacy.construcao.banco.orm.UsuarioRole;
import lombok.Getter;

@Getter
public class LoginDTO {
    private final String username;
    private final String password;
    private final UsuarioRole role;

    public LoginDTO(String username, String password, UsuarioRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public static Login usuarioToLogin(Usuario usuario) {
        return new Login(usuario.getNickname(), usuario.getPassword(), usuario.getRole());
    }

}
