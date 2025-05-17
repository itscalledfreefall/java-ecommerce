package com.itscalledfreefall.ecommerce.model;

import jakarta.persistence.*;
@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id ;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @OneToOne(optional = false,orphanRemoval = true)
    @JoinColumn(name = "product_id", nullable = false, unique = true)
    private Product product;



}
