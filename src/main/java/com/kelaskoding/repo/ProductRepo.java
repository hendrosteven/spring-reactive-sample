package com.kelaskoding.repo;

import com.kelaskoding.entity.Product;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

import reactor.core.publisher.Flux;

public interface ProductRepo extends ReactiveSortingRepository<Product, Long> {
    
    @Query("SELECT * FROM tbl_product WHERE title LIKE :title")
    Flux<Product> findByTitle(String title);

}
