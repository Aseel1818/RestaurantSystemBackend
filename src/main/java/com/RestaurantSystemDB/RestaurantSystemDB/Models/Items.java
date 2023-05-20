package com.RestaurantSystemDB.RestaurantSystemDB.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Items extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)

    private Long id;


    private String name;

    private String imageUrl;

    private Float price;

    /*@OneToOne(mappedBy = "item", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private OrderDetails orderDetails;*/

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories category;

}

