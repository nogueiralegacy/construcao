package com.github.nogueiralegacy.construcao.api;

import com.github.nogueiralegacy.construcao.domain.Projeto;
import com.github.nogueiralegacy.construcao.service.ProjetoService;
import com.github.nogueiralegacy.construcao.utils.dto.ProjetoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/projeto")
public class ProjetoController {
    private final ProjetoService projetoService;

    public ProjetoController( ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping
    public Iterable<Projeto> getProjetos() {
        return projetoService.findAll();
    }

    @PostMapping
    public ResponseEntity<String> setProjeto(@RequestBody ProjetoDTO projetoDTO) {
       try {
           projetoService.saveProjeto(projetoDTO);
       }
       catch (IllegalArgumentException e) {
           return ResponseEntity.badRequest().body("Erro ao criar projeto: " + e.getMessage());
       }

        return ResponseEntity.ok("Projeto criado com sucesso!");
    }

    @PostMapping("/{id_projeto}/participante/{nickname}")
    public ResponseEntity<String> addParticipante(@PathVariable("id_projeto") Long idProjeto, @PathVariable("nickname") String nickname) {
        try {
            projetoService.addParticipante(idProjeto, nickname);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro ao adicionar participante: " + e.getMessage());
        }

        return ResponseEntity.ok("Participante adicionado com sucesso!");
    }
}
