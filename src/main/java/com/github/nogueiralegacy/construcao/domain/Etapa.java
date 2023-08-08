package com.github.nogueiralegacy.construcao.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Etapa {
    @Setter(onMethod_ = @Deprecated)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;

    @OneToMany
    @JoinColumn(name = "id_etapa")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Tarefa> tarefas = new HashSet<>();

    protected Etapa() {}

    public Etapa(String titulo) {
        this.titulo = titulo;
    }

    public void addTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    public void removeTarefa(Tarefa tarefa) {
        tarefas.remove(tarefa);
    }
}
