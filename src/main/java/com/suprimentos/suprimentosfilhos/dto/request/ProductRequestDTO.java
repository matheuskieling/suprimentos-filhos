package com.suprimentos.suprimentosfilhos.dto.request;

import com.suprimentos.suprimentosfilhos.domain.enums.ProductCategory;

import java.util.Date;
import java.util.UUID;

public record ProductRequestDTO(Long id,
                                Integer quantity,
                                Integer quantityUsedPerDay,
                                String unit,
                                ProductCategory category,
                                String name,
                                String imgPath,
                                Integer notificationWindowInDays,
                                UUID userId) {
}
