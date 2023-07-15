package com.github.nogueiralegacy.construcao.api;

import com.github.nogueiralegacy.construcao.banco.orm.Projeto;
import com.github.nogueiralegacy.construcao.banco.repository.ProjetoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/projeto")
public class ProjetoController {
    private final ProjetoRepository projetoRepository;

    public ProjetoController(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    @GetMapping
    public Iterable<Projeto> getProjetos() {
        return projetoRepository.findAll();
    }
}
