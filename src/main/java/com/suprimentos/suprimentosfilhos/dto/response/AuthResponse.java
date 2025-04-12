package com.suprimentos.suprimentosfilhos.dto.response;

import java.util.UUID;

public class AuthResponse {
    private String token;
    private UUID userId;

public AuthResponse(String token, UUID userId) {
        this.userId = userId;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public UUID getUserId() {
        return userId;
    }
}
