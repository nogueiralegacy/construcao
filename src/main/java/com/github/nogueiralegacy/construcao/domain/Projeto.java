package com.github.nogueiralegacy.construcao.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
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
    @JoinColumn(nullable = false, name = "id_criador")
    private Usuario criador;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "projeto_participante",
            joinColumns = @JoinColumn(name = "id_projeto"),
            inverseJoinColumns = @JoinColumn(name = "id_usuario")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Usuario> participantes = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_projeto")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Etapa> etapas = new HashSet<>();

    protected Projeto() {}

    public Projeto(String nome, Usuario criador) {
        this.nome = nome;
        this.criador = criador;
    }

    public void addParticipante(Usuario usuario) {
        participantes.add(usuario);
    }

    public void removeParticipante(Usuario usuario) {
        participantes.remove(usuario);
    }

    public void addEtapa(Etapa etapa) {
        etapas.add(etapa);
    }

    public void removeEtapa(Etapa etapa) {
        etapas.remove(etapa);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Projeto projeto = (Projeto) o;

        return Objects.equals(nome, projeto.nome);
    }

    @Override
    public int hashCode() {
        return nome != null ? nome.hashCode() : 0;
    }
}
