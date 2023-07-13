package com.github.nogueiralegacy.construcao.banco.orm;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Entity
public class Usuario {

    @Getter(onMethod_ = @Deprecated)
    @Setter(onMethod_ = @Deprecated)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String nome;
    @Column(unique = true, nullable = false)
    private String email;
    private String avatar;

    @OneToMany(mappedBy = "criador")
    private Set<Projeto> projetos;

    protected Usuario() {}

    public Usuario(String nickname, String password, String nome, String email, String avatar) {
        this.nickname = nickname;
        this.password = password;
        this.nome = nome;
        this.email = email;
        this.avatar = avatar;
    }

    public Usuario(String nickname, String password, String nome, String email) {
        this.nickname = nickname;
        this.password = password;
        this.nome = nome;
        this.email = email;
    }
}
