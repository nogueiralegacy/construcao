package com.github.nogueiralegacy.construcao.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Tarefa {
    @Setter(onMethod_ = @Deprecated)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(nullable = false, name = "id_criador")
    private Usuario criador;
    @Column(nullable = false)
    private String titulo;
    private String descricao;

    @ManyToMany
    @JoinTable(
            name = "tarefa_responsavel",
            joinColumns = @JoinColumn(name = "id_tarefa"),
            inverseJoinColumns = @JoinColumn(name = "id_usuario")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Usuario> responsaveis = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "id_tarefa")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Comentario> comentarios = new HashSet<>();

    protected Tarefa(){}

    public Tarefa(Usuario criador, String titulo, String descricao) {
        this.criador = criador;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public void addResponsavel(Usuario usuario) {
        responsaveis.add(usuario);
    }

    public void removeResponsavel(Usuario usuario) {
        responsaveis.remove(usuario);
    }

    public void addComentario(Comentario comentario) {
        comentarios.add(comentario);
    }

    public void removeComentario(Comentario comentario) {
        comentarios.add(comentario);
    }
}
