package com.suprimentos.suprimentosfilhos.controller;

import com.suprimentos.suprimentosfilhos.domain.Product;
import com.suprimentos.suprimentosfilhos.dto.request.ProductRequestDTO;
import com.suprimentos.suprimentosfilhos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Product>> getAllProducts(@PathVariable UUID userId) {
        List<Product> list = this.productService.getAllProductsForUser(userId);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        Product savedProduct = this.productService.saveProduct(productRequestDTO);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        Product updatedProduct = this.productService.updateProduct(productRequestDTO);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        this.productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/addUnit/{id}")
    public ResponseEntity<Product> addUnitToProduct(@PathVariable Long id) {
        Product product = this.productService.addUnit(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
