package com.github.nogueiralegacy.construcao.utils.dto;

import com.github.nogueiralegacy.construcao.domain.UsuarioRole;

public record UsuarioDTO(String nickname, String password, String nome, String email, String avatar, UsuarioRole role) {
}
