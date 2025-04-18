package com.suprimentos.suprimentosfilhos.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.suprimentos.suprimentosfilhos.domain.enums.ProductCategory;
import com.suprimentos.suprimentosfilhos.dto.request.ProductRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
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

    private Integer leftQuantity;

    @Column(nullable = false)
    private String name;

    private String imgPath;

    private Date notificationDate;

    @Column(nullable = false)
    private Integer notificationWindowInDays;

    @JoinColumn(nullable = false, name="user_id")
    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "product")
    private List<UnitOfProduct> units;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    public Product() {}

    public void addUnit(UnitOfProduct unit) {
        this.units.add(unit);
        this.leftQuantity += this.quantity;
        this.calculateEndDateAndNotificationDate();
    }



    public void useProduct() {
        this.leftQuantity = Math.max(0, this.leftQuantity - this.quantityUsedPerDay);
    }

    public void update(ProductRequestDTO productRequestDTO) {
        this.category = productRequestDTO.category();
        this.name = productRequestDTO.name();
        this.imgPath = productRequestDTO.imgPath();
        this.unit = productRequestDTO.unit();
        this.quantity = productRequestDTO.quantity();
        this.quantityUsedPerDay = productRequestDTO.quantityUsedPerDay();

        this.calculateEndDateAndNotificationDate();
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

    public Integer getNotificationWindowInDays() {
        return notificationWindowInDays;
    }

    public void setNotificationWindowInDays(Integer notificationWindowInDays) {
        this.notificationWindowInDays = notificationWindowInDays;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate() {
        Instant instant = this.endsIn.toInstant().minus(this.notificationWindowInDays, ChronoUnit.DAYS);
        ZoneId zoneId = ZoneId.systemDefault();

        Instant startOfDay = instant
                .atZone(zoneId)
                .toLocalDate()
                .atStartOfDay(zoneId)
                .toInstant();

        this.notificationDate = Date.from(startOfDay);
    }

    public Integer getLeftQuantity() {
        return leftQuantity;
    }

    public void setLeftQuantity(Integer leftQuantity) {
        this.leftQuantity = leftQuantity;
    }

    public List<UnitOfProduct> getUnits() {
        return units;
    }

    public void setUnits(List<UnitOfProduct> units) {
        this.units = units;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public void calculateEndDateAndNotificationDate() {
        Integer daysUntilProductEnd = this.leftQuantity / this.quantityUsedPerDay;
        this.endsIn = Date.from(Instant.now().plus(daysUntilProductEnd, ChronoUnit.DAYS));
        this.setNotificationDate();
    }
}
