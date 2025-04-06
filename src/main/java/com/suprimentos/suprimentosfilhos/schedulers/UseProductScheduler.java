package com.suprimentos.suprimentosfilhos.schedulers;

import com.suprimentos.suprimentosfilhos.domain.Product;
import com.suprimentos.suprimentosfilhos.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UseProductScheduler {

    private final ProductRepository productRepository;

    public UseProductScheduler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Scheduled(cron = "0 0 10 * * *")
    @Transactional
    public void updateProductQuantities() {
        List<Product> products = this.productRepository.findByLeftQuantityGreaterThan(0);

        for (Product product : products) {
            product.useProduct();
        }

        this.productRepository.saveAll(products);

    }
}
