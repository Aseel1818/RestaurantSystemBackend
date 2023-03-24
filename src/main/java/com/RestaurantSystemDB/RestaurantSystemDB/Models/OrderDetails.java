package com.RestaurantSystemDB.RestaurantSystemDB.Models;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(nullable = false,updatable = false)

        private Long id ;

        private int quantity;

        @Nullable
        private String note;


        @OneToOne(fetch = FetchType.LAZY, optional = true)
        @JoinColumn(name = "item_id")
        private Items item;

        @ManyToOne
        @JoinColumn(name = "order_id", referencedColumnName = "id")
        private Orders order;

}

