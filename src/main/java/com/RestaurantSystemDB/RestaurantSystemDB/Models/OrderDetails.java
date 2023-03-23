package com.RestaurantSystemDB.RestaurantSystemDB.Models;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@Entity
public class OrderDetails implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(nullable = false,updatable = false)

        private Long id ;

        private int quantity;

        @Nullable
        private String note;

        private Items item;



}

