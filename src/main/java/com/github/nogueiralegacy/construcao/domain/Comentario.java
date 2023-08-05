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
public class Comentario {
    @Setter(onMethod_ = @Deprecated)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(nullable = false, name = "id_criador")
    private Usuario criador;
    private String descricao;

    //Nessa caso, caso a coluna id_comentario esteja null significa que
    // esse comentario é um comentario raiz, ou seja, responde diretamente
    // uma tarefa.
    @OneToMany
    @JoinColumn(name = "id_comentario")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Comentario> respostas = new HashSet<>();

    protected Comentario(){}

    public void addResposta(Comentario comentario) {
        respostas.add(comentario);
    }

    public void removeResposta(Comentario comentario) {
        respostas.remove(comentario);
    }
}
