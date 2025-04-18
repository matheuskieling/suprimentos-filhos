package com.suprimentos.suprimentosfilhos.service;

import com.suprimentos.suprimentosfilhos.domain.UnitOfProduct;
import com.suprimentos.suprimentosfilhos.repository.ProductRepository;
import com.suprimentos.suprimentosfilhos.domain.Product;
import com.suprimentos.suprimentosfilhos.domain.User;
import com.suprimentos.suprimentosfilhos.dto.request.ProductRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UnitOfProductService unitOfProductService;

    public List<Product> getAllProductsForUser(UUID userId) {
        return this.productRepository.findAllByUserId(userId);
    }


    public Product saveProduct(ProductRequestDTO dto) {
        User user = this.userService.findById(dto.userId());
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        Product product = new Product();
        product.setName(dto.name());
        product.setImgPath(dto.imgPath());
        product.setUnit(dto.unit());
        product.setQuantity(dto.quantity());
        product.setQuantityUsedPerDay(dto.quantityUsedPerDay());
        product.setNotificationWindowInDays(dto.notificationWindowInDays());
        product.setUser(user);
        product.setLeftQuantity(dto.quantity());
        product.setUnits(new ArrayList<>());
        product = productRepository.save(product);
        UnitOfProduct unit = new UnitOfProduct(Date.from(Instant.now()), product);
        unit = unitOfProductService.saveUnitOfProduct(unit);
        product.getUnits().add(unit);
        product = productRepository.save(product);
        return product;
    }

    public Product updateProduct(ProductRequestDTO dto) {
        Product product = this.productRepository.findById(dto.id()).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        product.update(dto);
        return this.productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        this.productRepository.deleteById(id);
    }

    public Product addUnit(Long id) {
        Product product = this.productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        UnitOfProduct unit = new UnitOfProduct(Date.from(Instant.now()), product);
        unit = unitOfProductService.saveUnitOfProduct(unit);
        product.addUnit(unit);
        productRepository.save(product);
        return product;
    }
}
