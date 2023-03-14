package com.RestaurantSystemDB.RestaurantSystemDB.Models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Tables implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)

    private Long id;
    private boolean status;
    private String name;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "order_id", nullable = true)
    private Orders orders;

    public Tables() {
    }

    public Tables(boolean status, String name) {
        this.status = status;
        this.name = name;
        this.id = id;
    }

    public long getTableID() {
        return id;
    }

    public void setTableId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
