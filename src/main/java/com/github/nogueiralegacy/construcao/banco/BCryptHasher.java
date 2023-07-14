package com.github.nogueiralegacy.construcao.banco;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

public final class BCryptHasher {
    private static final int strenght = 10;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(strenght, new SecureRandom());

    public String encode(String password) {
        return encoder.encode(password);
    }

    public boolean matches(String password, String hash) {
        return encoder.matches(password, hash);
    }
}
