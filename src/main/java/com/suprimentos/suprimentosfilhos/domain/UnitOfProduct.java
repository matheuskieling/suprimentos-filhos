package com.suprimentos.suprimentosfilhos.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.suprimentos.suprimentosfilhos.dto.request.ProductRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Entity
@Table(name = "unit_of_product")
@Getter
@Setter
public class UnitOfProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date buyDate;

    @JoinColumn(nullable = false, name="product_id")
    @ManyToOne
    @JsonIgnore
    private Product product;

    public UnitOfProduct() {}

    public UnitOfProduct(Date buyDate, Product product) {
        this.buyDate = buyDate;
        this.product = product;
    }


}
