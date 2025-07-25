package com.suprimentos.suprimentosfilhos.repository;

import com.suprimentos.suprimentosfilhos.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByLeftQuantityGreaterThan(int leftQuantity);
    List<Product> findAllByNotificationDateIsNotNull();

    @Query("SELECT p FROM Product p WHERE p.user.id = :userId")
    List<Product> findAllByUserId(UUID userId);
}
