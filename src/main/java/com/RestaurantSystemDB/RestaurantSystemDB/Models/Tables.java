package com.RestaurantSystemDB.RestaurantSystemDB.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tables extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)

    private Long id;
    private boolean status;
    private String name;


    public Tables(boolean status, String name) {
        this.status = status;
        this.name = name;
        this.id = id;
    }
}
