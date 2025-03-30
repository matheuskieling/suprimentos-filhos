package com.suprimentos.suprimentosfilhos.controller;

import com.suprimentos.suprimentosfilhos.domain.Product;
import com.suprimentos.suprimentosfilhos.dto.ProductDTO;
import com.suprimentos.suprimentosfilhos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> list = this.productService.getAllProducts();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@RequestBody ProductDTO productDTO) {
        Product savedProduct = this.productService.saveProduct(productDTO);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDTO productDTO) {
        Product updatedProduct = this.productService.updateProduct(productDTO);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        this.productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/open/{id}")
    public ResponseEntity<Product> openProduct(@PathVariable Long id) {
        Product product = this.productService.openProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
