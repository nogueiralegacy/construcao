package com.github.nogueiralegacy.construcao.api;

import com.github.nogueiralegacy.construcao.banco.orm.Projeto;
import com.github.nogueiralegacy.construcao.banco.repository.ProjetoRepository;
import com.github.nogueiralegacy.construcao.service.ProjetoService;
import com.github.nogueiralegacy.construcao.utils.dto.ProjetoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/projeto")
public class ProjetoController {
    private final ProjetoRepository projetoRepository;
    private final ProjetoService projetoService;

    public ProjetoController(ProjetoRepository projetoRepository, ProjetoService projetoService) {
        this.projetoRepository = projetoRepository;
        this.projetoService = projetoService;
    }

    @GetMapping
    public Iterable<Projeto> getProjetos() {
        return projetoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity setProjeto(@RequestBody ProjetoDTO projetoDTO) {
       try {
           projetoService.saveProjeto(projetoDTO);
       }
       catch (Exception e) {
           return ResponseEntity.badRequest().body(e.getMessage());
       }

        return ResponseEntity.ok().build();
    }
}
