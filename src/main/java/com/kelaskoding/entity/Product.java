package com.kelaskoding.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("tbl_product")
public class Product {
    
    @Id
    @Column("id")
    private Long id;

    @Column("title")
    private String title;

    @Column("price")
    private double price;
}
