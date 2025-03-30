package com.suprimentos.suprimentosfilhos.dto.request;

import java.util.UUID;

public record UserRequestDTO(UUID id, String password, String email) {
}
