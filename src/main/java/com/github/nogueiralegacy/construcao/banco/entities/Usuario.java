package com.github.nogueiralegacy.construcao.banco.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "usuario")
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

    protected Usuario() {}

    public Usuario(String username, String password, String nickname, String email, String avatar) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.avatar = avatar;
    }

}
