package com.github.nogueiralegacy.construcao.banco.orm;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

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

    protected Projeto() {}

    public Projeto(String nome, String descricao, LocalDateTime dataCriacao, Usuario criador) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.criador = criador;
    }
}
