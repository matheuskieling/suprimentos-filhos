package com.suprimentos.suprimentosfilhos.repository;

import com.suprimentos.suprimentosfilhos.domain.Product;
import com.suprimentos.suprimentosfilhos.domain.UnitOfProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UnitOfProductRepository extends JpaRepository<UnitOfProduct, Long> {
}
