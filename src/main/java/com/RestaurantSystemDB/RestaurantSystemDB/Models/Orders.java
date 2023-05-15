package com.RestaurantSystemDB.RestaurantSystemDB.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
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

    private Long tables;
    @Temporal(TemporalType.TIMESTAMP)
    private Date payment_date;



    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns =@JoinColumn(name = "orders_id") ,
            inverseJoinColumns = @JoinColumn(name = "order_details_id"),
            name ="orders_order_detail"
    )
    private List<OrderDetails> orderDetail;
}
