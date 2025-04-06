package com.suprimentos.suprimentosfilhos.repository;

import com.suprimentos.suprimentosfilhos.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByLeftQuantityGreaterThan(int leftQuantity);
    List<Product> findAllByNotificationDateIsNotNull();
}
