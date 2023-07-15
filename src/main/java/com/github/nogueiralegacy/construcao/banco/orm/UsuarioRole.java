package com.github.nogueiralegacy.construcao.banco.orm;

public enum UsuarioRole {
    ADMIN("admin"),
    USER("user");

    private final String role;

    UsuarioRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
