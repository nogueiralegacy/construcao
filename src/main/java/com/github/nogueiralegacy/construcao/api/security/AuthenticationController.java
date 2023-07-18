package com.github.nogueiralegacy.construcao.api.security;

import com.github.nogueiralegacy.construcao.banco.BCryptHasher;
import com.github.nogueiralegacy.construcao.banco.repository.UsuarioRepository;
import com.github.nogueiralegacy.construcao.utils.dto.LoginDTO;
import com.github.nogueiralegacy.construcao.utils.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password());
        var authentication = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO registerDTO) {
        if (usuarioRepository.findByNickname(registerDTO.nickname()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        else {
            String passwordHash = new BCryptHasher().encode(registerDTO.password());
            usuarioRepository.save(registerDTO.toUsuario(passwordHash));

            return ResponseEntity.ok().build();
        }
    }
}
