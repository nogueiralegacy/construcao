package com.github.nogueiralegacy.construcao.api;

import com.github.nogueiralegacy.construcao.banco.orm.Usuario;
import com.github.nogueiralegacy.construcao.banco.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path ="/usuario")
public class UsuarioController {
   private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<Usuario> getUsuarios() {
        Iterable<Usuario> usuarios = usuarioRepository.findAll();
        List<Usuario> usuarioList = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            usuarioList.add(usuario);
        }

        return usuarioList;
    }
}
