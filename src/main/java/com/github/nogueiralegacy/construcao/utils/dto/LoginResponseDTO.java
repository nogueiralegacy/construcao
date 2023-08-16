package com.github.nogueiralegacy.construcao.utils.dto;

import java.time.Instant;

public record LoginResponseDTO(String token, Instant expirationDate) {
}
