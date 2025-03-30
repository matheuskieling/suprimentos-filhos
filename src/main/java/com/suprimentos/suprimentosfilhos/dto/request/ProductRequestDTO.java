package com.suprimentos.suprimentosfilhos.dto.request;

import java.util.Date;
import java.util.UUID;

public record ProductRequestDTO(Long id,
                                Integer quantity,
                                Integer quantityUsedPerDay,
                                String unit,
                                String name,
                                String imgPath,
                                Date openedOn,
                                Integer notificationWindowInDays,
                                UUID userId) {
}
