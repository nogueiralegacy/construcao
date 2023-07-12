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
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String nickname;
    @Column(unique = true, nullable = false)
    private String email;
    private String avatar;

    @OneToMany(mappedBy = "criador")
    private Set<Projeto> projetos;

    protected Usuario() {}

    public Usuario(String username, String password, String nickname, String email, String avatar) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.avatar = avatar;
    }

    public Usuario(String username, String password, String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }
}
