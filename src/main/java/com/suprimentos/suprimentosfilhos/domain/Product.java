package com.suprimentos.suprimentosfilhos.domain;

import com.suprimentos.suprimentosfilhos.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.temporal.ChronoUnit;
import java.util.Date;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer quantityUsedPerDay;

    @Column(nullable = false)
    private String unit;

    private Date endsIn;

    @Column(nullable = false)
    private String name;

    private String imgPath;

    private Date openedOn;

    @Column(nullable = false)
    private Integer notificationWindowInDays;

    public Product() {}

    public Product(Long id, Integer quantity, Integer quantityUsedPerDay, String unit, Date endsIn, String name, String imgPath, Date openedOn, Integer notificationWindowInDays) {
        this.id = id;
        this.quantity = quantity;
        this.quantityUsedPerDay = quantityUsedPerDay;
        this.unit = unit;
        this.endsIn = endsIn;
        this.name = name;
        this.imgPath = imgPath;
        this.openedOn = openedOn;
        this.notificationWindowInDays = notificationWindowInDays;
    }

    public Product(ProductDTO productDTO) {
        this.name = productDTO.name();
        this.imgPath = productDTO.imgPath();
        this.unit = productDTO.unit();
        this.quantity = productDTO.quantity();
        this.quantityUsedPerDay = productDTO.quantityUsedPerDay();
        this.notificationWindowInDays = productDTO.notificationWindowInDays();
    }

    public void open() {
        this.openedOn = new Date();
        Integer daysBeforeEnding = this.quantity / this.quantityUsedPerDay;
        this.endsIn = Date.from(this.openedOn.toInstant().plus(daysBeforeEnding, ChronoUnit.DAYS));
    }

    public void update(ProductDTO productDTO) {
        this.name = productDTO.name();
        this.imgPath = productDTO.imgPath();
        this.unit = productDTO.unit();
        this.quantity = productDTO.quantity();
        this.quantityUsedPerDay = productDTO.quantityUsedPerDay();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantityUsedPerDay() {
        return quantityUsedPerDay;
    }

    public void setQuantityUsedPerDay(Integer quantityUsedPerDay) {
        this.quantityUsedPerDay = quantityUsedPerDay;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Date getEndsIn() {
        return endsIn;
    }

    public void setEndsIn(Date endsIn) {
        this.endsIn = endsIn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Date getOpenedOn() {
        return openedOn;
    }

    public void setOpenedOn(Date openedOn) {
        this.openedOn = openedOn;
    }

    public Integer getNotificationWindowInDays() {
        return notificationWindowInDays;
    }

    public void setNotificationWindowInDays(Integer notificationWindowInDays) {
        this.notificationWindowInDays = notificationWindowInDays;
    }
}
