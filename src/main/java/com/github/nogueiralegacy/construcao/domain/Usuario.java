package com.github.nogueiralegacy.construcao.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Usuario {

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
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UsuarioRole role;

    @JsonBackReference
    @ManyToMany(mappedBy = "participantes")
    private Set<Projeto> projetos = new HashSet<>();

    protected Usuario() {}

    public Usuario(String nickname, String password, String nome, String email, UsuarioRole role) {
        this.nickname = nickname;
        this.password = password;
        this.nome = nome;
        this.email = email;
        this.role = role;
    }

    public void addProjeto(Projeto projeto) {
        projetos.add(projeto);
        projeto.getParticipantes().add(this);
    }

    public void removeProjeto(Projeto projeto) {
        projetos.remove(projeto);
        projeto.getParticipantes().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (!nickname.equals(usuario.nickname)) return false;
        return password.equals(usuario.password);
    }

    @Override
    public int hashCode() {
        int result = nickname.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
