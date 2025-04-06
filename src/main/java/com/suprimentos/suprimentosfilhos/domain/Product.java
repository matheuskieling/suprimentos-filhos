package com.suprimentos.suprimentosfilhos.domain;

import com.suprimentos.suprimentosfilhos.dto.request.ProductRequestDTO;
import jakarta.persistence.*;

import java.time.Instant;
import java.time.ZoneId;
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


    public void setLeftQuantity(Integer leftQuantity) {
        this.leftQuantity = leftQuantity;
    }

    private Integer leftQuantity;

    @Column(nullable = false)
    private String name;

    private String imgPath;

    private Date openedOn;

    private Date notificationDate;

    @Column(nullable = false)
    private Integer notificationWindowInDays;

    @JoinColumn(nullable = false, name="user_id")
    @ManyToOne
    private User user;


    public Product() {}

    public Product(Long id, Integer quantity, Integer quantityUsedPerDay, String unit, Date endsIn, String name, String imgPath, Date openedOn, Integer notificationWindowInDays, User user) {
        this.id = id;
        this.quantity = quantity;
        this.quantityUsedPerDay = quantityUsedPerDay;
        this.unit = unit;
        this.endsIn = endsIn;
        this.name = name;
        this.leftQuantity = quantity;
        this.imgPath = imgPath;
        this.openedOn = openedOn;
        this.notificationWindowInDays = notificationWindowInDays;
        this.user = user;
    }

    public void open() {
        this.openedOn = new Date();
        Integer daysBeforeEnding = this.quantity / this.quantityUsedPerDay;
        this.endsIn = Date.from(this.openedOn.toInstant().plus(daysBeforeEnding, ChronoUnit.DAYS));
        this.setNotificationDate();
        this.useProduct();
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
}
