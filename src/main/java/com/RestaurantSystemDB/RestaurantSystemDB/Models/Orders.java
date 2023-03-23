package com.RestaurantSystemDB.RestaurantSystemDB.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)


    private Long id;
    private String note;

    private Float total;


    @OneToOne(mappedBy = "orders", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Tables tables;

    @OneToMany
    private List<OrderDetails> orderDetail;
}
