package com.suprimentos.suprimentosfilhos.dto;

import java.util.Date;

public record ProductDTO(Long id,
                         Integer quantity,
                         Integer quantityUsedPerDay,
                         String unit,
                         String name,
                         String imgPath,
                         Date openedOn,
                         Integer notificationWindowInDays) {
}
