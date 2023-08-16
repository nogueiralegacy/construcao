package com.github.nogueiralegacy.construcao.api;

import com.github.nogueiralegacy.construcao.domain.Usuario;
import com.github.nogueiralegacy.construcao.service.UsuarioService;
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
    public ResponseEntity findByNickname(@PathVariable("nickname") String nickname) {
        Usuario usuario;
        try {
            usuario = usuarioService.findByNickname(nickname);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro ao buscar usuario: " + e.getMessage());
        }

        return ResponseEntity.ok(usuario);
    }
}
