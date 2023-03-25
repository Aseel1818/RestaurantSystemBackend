package com.RestaurantSystemDB.RestaurantSystemDB.Models;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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

        private Integer quantity;

        @Nullable
        private String note;


        @OneToOne(fetch = FetchType.LAZY, optional = true)
        @JoinColumn(name = "item_id",referencedColumnName = "id")
        private Items item;

}

