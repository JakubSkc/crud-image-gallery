package com.jakubskc.crudimagegallery.controller;

import com.jakubskc.crudimagegallery.entity.Product;
import com.jakubskc.crudimagegallery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService service;

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product){
        return service.saveProduct(product);
    }
    @PostMapping("/addProducts")
    public List<Product> addProducts(@RequestBody List<Product> products){
        return service.saveProducts(products);
    }

    @GetMapping("/products")
    public List<Product> findAllProducts(){
        return service.getProducts();
    }
    @GetMapping("/productById/{id}")
    public Product findProductById(@PathVariable int id){
        return service.getProductById(id);
    }
    @GetMapping("/productByName/{name}")
    public Product findProductByName(@PathVariable String name){
        return service.getProductByName(name);
    }

    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product){
        return service.updateProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id){
        return service.deleteProduct(id);
    }

    @GetMapping("/productImage/{id}")
    public ResponseEntity<byte[]> getProductImage(@PathVariable int id) {
        Product product = service.getProductById(id);
        if (product != null && product.getImage() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentLength(product.getImage().length);
            return new ResponseEntity<>(product.getImage(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
