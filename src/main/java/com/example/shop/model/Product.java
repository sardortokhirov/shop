package com.example.shop.entity;

import jakarta.persistence.*;

/**
 * Date-5/8/2023
 * Time-7:28 AM
 */
@Entity
@Table(name="product")
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name")
    private String productName;

    private String description;

    private Integer price;



}
