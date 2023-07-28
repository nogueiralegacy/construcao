package com.github.nogueiralegacy.construcao.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Projeto {
    @Setter(onMethod_ = @Deprecated)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String nome;
    private String descricao;
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_usuario ")
    private Usuario criador;

    @JsonManagedReference
    @ManyToMany
    @JoinTable(
            name = "projeto_participante",
            joinColumns = @JoinColumn(name = "id_projeto"),
            inverseJoinColumns = @JoinColumn(name = "id_usuario")
    )
    private Set<Usuario> participantes = new HashSet<>();

    protected Projeto() {}
    public Projeto(String nome, Usuario criador) {
        this.nome = nome;
        this.dataCriacao = LocalDateTime.now();
        this.criador = criador;
    }

    public void addParticipante(Usuario usuario) {
        participantes.add(usuario);
        usuario.getProjetos().add(this);
    }

    public void removeParticipante(Usuario usuario) {
        participantes.remove(usuario);
        usuario.getProjetos().remove(this);
    }
}
