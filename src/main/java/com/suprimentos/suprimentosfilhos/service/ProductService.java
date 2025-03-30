package com.suprimentos.suprimentosfilhos.service;

import com.suprimentos.suprimentosfilhos.Repository.ProductRepository;
import com.suprimentos.suprimentosfilhos.domain.Product;
import com.suprimentos.suprimentosfilhos.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }


    public Product saveProduct(ProductDTO dto) {
        Product product = new Product(dto);
        product = productRepository.save(product);
        return product;
    }

    public Product openProduct(Long id) {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        product.open();
        this.productRepository.save(product);
        return product;
    }

    public Product updateProduct(ProductDTO dto) {
        Product product = this.productRepository.findById(dto.id()).orElseThrow(() -> new RuntimeException("Produto encontrado"));
        product.update(dto);
        return this.productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        this.productRepository.deleteById(id);
    }
}
