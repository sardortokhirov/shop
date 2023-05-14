package com.example.shop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * Date-5/8/2023
 * Time-7:28 AM
 */
@Entity
@Table(name = "product")
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1,
            initialValue = 30
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    @Column(name = "product_id")
    private Long id;
    @Size(min = 2, max = 50,message = "productName have to be 2< x <50")
    @Column(name = "product_name")
    private String productName;

    private String description;
    @Positive
    private Integer price;
    @Column(name = "supplier_id")
    private Integer supplierId;

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Product(String productName, String description, Integer price, Integer supplierId) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.supplierId = supplierId;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", supplierId=" + supplierId +
                '}';
    }
}
