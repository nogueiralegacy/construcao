package com.github.nogueiralegacy.construcao.entities;

import java.sql.Connection;

public class Login {
    private String login;
    private String senha;
    private String email;

    private Connection conexao;

    public Login(String login, String senha, String email, Connection conexao) {
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.conexao = conexao;
    }
}
