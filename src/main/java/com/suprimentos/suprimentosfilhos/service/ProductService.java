package com.suprimentos.suprimentosfilhos.service;

import com.suprimentos.suprimentosfilhos.Repository.ProductRepository;
import com.suprimentos.suprimentosfilhos.domain.Product;
import com.suprimentos.suprimentosfilhos.domain.User;
import com.suprimentos.suprimentosfilhos.dto.request.ProductRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
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
        product = productRepository.save(product);
        return product;
    }

    public Product openProduct(Long id) {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        product.open();
        this.productRepository.save(product);
        return product;
    }

    public Product updateProduct(ProductRequestDTO dto) {
        Product product = this.productRepository.findById(dto.id()).orElseThrow(() -> new RuntimeException("Produto encontrado"));
        product.update(dto);
        return this.productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        this.productRepository.deleteById(id);
    }
}
