package com.itscalledfreefall.ecommerce.model;

import jakarta.persistence.*;
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private long id ;
    @Column(name = "product_name", nullable = false, unique = true)
    private String productName;
    @Column(name = "price",nullable = false)
    private double price;
    @Column(name = "short_description",nullable = false)
    private String shortDescription;
    @Column(name = "long_description")
    private String longDescription;
    @OneToOne(mappedBy = "product",optional = false,cascade = CascadeType.REMOVE,orphanRemoval = true)
    private Inventory inventory;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
