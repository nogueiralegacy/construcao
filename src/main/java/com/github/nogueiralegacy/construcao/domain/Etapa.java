package com.github.nogueiralegacy.construcao.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
public class Etapa {
    @Setter(onMethod_ = @Deprecated)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;

    protected Etapa() {}

    public Etapa(String titulo) {
        this.titulo = titulo;
    }
}
