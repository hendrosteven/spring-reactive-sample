package com.kelaskoding.controller;

import com.kelaskoding.dto.SearchRequest;
import com.kelaskoding.entity.Product;
import com.kelaskoding.repo.ProductRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductRepo productRepo;

    @PostMapping
    public Mono<Product> createOneProduct(@RequestBody Product product){
        return productRepo.save(product);
    }

    @GetMapping
    public ResponseEntity<Flux<Product>> findAll(){
        return ResponseEntity.ok(productRepo.findAll());
    }

    @GetMapping("/{id}")
    public Mono<Product> findOne(@PathVariable("id") Long id){
        return productRepo.findById(id);
    }

    @PostMapping("/search")
    public Flux<Product> findByTitle(@RequestBody SearchRequest search){
        return productRepo.findByTitle("%"+search.getKey()+"%");
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deletById(@PathVariable("id") Long id){
        return productRepo.deleteById(id);
    }

    @PutMapping("/{id}")
    public Mono<Product> updateOne(@PathVariable("id") Long id, @RequestBody Product product){
        return productRepo.findById(id).map(p -> {
            p.setTitle(product.getTitle());
            p.setPrice(product.getPrice());
            return p;
        }).flatMap(p -> productRepo.save(p));
    }

}
