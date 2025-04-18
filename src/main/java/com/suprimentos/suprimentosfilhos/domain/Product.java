package com.suprimentos.suprimentosfilhos.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.suprimentos.suprimentosfilhos.dto.request.ProductRequestDTO;
import jakarta.persistence.*;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public Product() {}

    public Product(Long id, Integer quantity, Integer quantityUsedPerDay, String unit, Date endsIn, String name, String imgPath, Integer notificationWindowInDays, User user) {
        this.id = id;
        this.quantity = quantity;
        this.quantityUsedPerDay = quantityUsedPerDay;
        this.unit = unit;
        this.endsIn = endsIn;
        this.name = name;
        this.leftQuantity = quantity;
        this.imgPath = imgPath;
        this.notificationWindowInDays = notificationWindowInDays;
        this.user = user;
        this.units = new ArrayList<>();
        UnitOfProduct unitOfProduct = new UnitOfProduct(Date.from(Instant.now()), this);
        this.units.add(unitOfProduct);
    }

    public void addUnit(UnitOfProduct unit) {
        this.units.add(unit);
        this.leftQuantity += this.quantity;
        Integer daysBeforeEnding = this.quantity / this.quantityUsedPerDay;
        this.endsIn = Date.from(Instant.now().plus(daysBeforeEnding, ChronoUnit.DAYS));
        this.setNotificationDate();
    }

    public void useProduct() {
        this.leftQuantity = Math.max(0, this.leftQuantity - this.quantityUsedPerDay);
    }

    public void update(ProductRequestDTO productRequestDTO) {
        this.name = productRequestDTO.name();
        this.imgPath = productRequestDTO.imgPath();
        this.unit = productRequestDTO.unit();
        this.quantity = productRequestDTO.quantity();
        this.quantityUsedPerDay = productRequestDTO.quantityUsedPerDay();

        if (productRequestDTO.notificationWindowInDays() != this.notificationWindowInDays && this.endsIn != null) {
            this.notificationWindowInDays = productRequestDTO.notificationWindowInDays();
            this.setNotificationDate();
        }
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

    public void calculateEndDateAndNotificationDate() {
        Integer daysUntilProductEnd = this.leftQuantity / this.quantityUsedPerDay;
        this.endsIn = Date.from(Instant.now().plus(daysUntilProductEnd, ChronoUnit.DAYS));
        this.notificationDate = Date.from(this.endsIn.toInstant().minus(this.notificationWindowInDays, ChronoUnit.DAYS));
    }
}
