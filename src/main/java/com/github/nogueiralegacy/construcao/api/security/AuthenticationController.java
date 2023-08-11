package com.github.nogueiralegacy.construcao.api.security;

import com.github.nogueiralegacy.construcao.banco.BCryptHasher;
import com.github.nogueiralegacy.construcao.banco.repository.UsuarioRepository;
import com.github.nogueiralegacy.construcao.service.UsuarioService;
import com.github.nogueiralegacy.construcao.utils.dto.LoginDTO;
import com.github.nogueiralegacy.construcao.utils.dto.LoginResponseDTO;
import com.github.nogueiralegacy.construcao.utils.dto.RegisterUsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, UsuarioService usuarioService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.usuarioService = usuarioService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password());
        var authentication = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Login) authentication.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterUsuarioDTO registerUsuarioDTO) {
        try{
            if (!usuarioService.usuarioExists(registerUsuarioDTO.nickname())) {
                usuarioService.saveUsuario(registerUsuarioDTO);

                return ResponseEntity.ok("Usuário registrado com sucesso!");
                }

            return ResponseEntity.badRequest().body("Usuário já cadastrado");
        } catch(IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro ao criar usuário: " + e.getMessage());
        }
    }
}
