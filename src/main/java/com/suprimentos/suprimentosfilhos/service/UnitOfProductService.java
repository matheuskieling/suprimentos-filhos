package com.suprimentos.suprimentosfilhos.service;

import com.suprimentos.suprimentosfilhos.domain.Product;
import com.suprimentos.suprimentosfilhos.domain.UnitOfProduct;
import com.suprimentos.suprimentosfilhos.domain.User;
import com.suprimentos.suprimentosfilhos.dto.request.ProductRequestDTO;
import com.suprimentos.suprimentosfilhos.repository.ProductRepository;
import com.suprimentos.suprimentosfilhos.repository.UnitOfProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UnitOfProductService {

    @Autowired
    private UnitOfProductRepository unitOfProductRepository;

    public UnitOfProduct saveUnitOfProduct(UnitOfProduct unitOfProduct) {
        return this.unitOfProductRepository.save(unitOfProduct);
    }

}
