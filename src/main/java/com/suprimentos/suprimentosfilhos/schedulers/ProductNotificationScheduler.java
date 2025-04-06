package com.suprimentos.suprimentosfilhos.schedulers;

import com.suprimentos.suprimentosfilhos.domain.Product;
import com.suprimentos.suprimentosfilhos.repository.ProductRepository;
import com.suprimentos.suprimentosfilhos.service.EmailService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Component
public class ProductNotificationScheduler {

    private final ProductRepository productRepository;
    private final EmailService emailService;

    public ProductNotificationScheduler(ProductRepository productRepository, EmailService emailService) {
        this.productRepository = productRepository;
        this.emailService = emailService;
    }

    @Scheduled(cron = "0 0 10 * * *")
    public void notifyLowStockProducts() {
        LocalDate today = LocalDate.now(ZoneId.systemDefault());

        List<Product> products = productRepository.findAllByNotificationDateIsNotNull();

        for (Product product : products) {
            LocalDate notificationDate = product.getNotificationDate()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            if (notificationDate.isEqual(today)) {
                emailService.sendLowStockEmail(product);
            }
        }
    }
}
