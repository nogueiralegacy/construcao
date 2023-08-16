package com.github.nogueiralegacy.construcao.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.nogueiralegacy.construcao.domain.Usuario;
import com.github.nogueiralegacy.construcao.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path ="/usuario")
public class UsuarioController {
   private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public Iterable<Usuario> getUsuarios() {
        return usuarioService.findAll();
    }

    @GetMapping("/{nickname}")
    public ResponseEntity<String> findByNickname(@PathVariable("nickname") String nickname) {
        Usuario usuario;
        try {
            usuario = usuarioService.findByNickname(nickname);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro ao buscar usuario: " + e.getMessage());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String usuarioJson;
        try {
            usuarioJson = objectMapper.writeValueAsString(usuario);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao converter usuario para JSON");
        }

        return ResponseEntity.ok(usuarioJson);
    }
}
