package com.RestaurantSystemDB.RestaurantSystemDB.Models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
public class Items implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)

    private Long id;


    private String name;

    private String imageUrl;

    private float price;
    @OneToOne(mappedBy = "item", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private OrderDetails orderDetails;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories category;


    public Items() {
    }


    public Items(String name, float price, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.price = price;
        this.name = name;


    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


}

