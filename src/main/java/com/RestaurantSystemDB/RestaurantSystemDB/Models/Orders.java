package com.RestaurantSystemDB.RestaurantSystemDB.Models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

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

    @OneToMany(mappedBy = "orders")
    private List<OrderDetails> orderDetail;

    public Orders() {
    }

    public Orders(float total, String note) {
        this.total = total;
        this.note = note;
        this.id = id;
    }

    public List<OrderDetails> getDetails() {
        return orderDetail;
    }

    public long getOrderID() {
        return id;
    }

    public void setOrderId(Long id) {
        this.id = id;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
