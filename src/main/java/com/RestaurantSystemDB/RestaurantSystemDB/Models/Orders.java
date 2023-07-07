package com.RestaurantSystemDB.RestaurantSystemDB.Models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Data
@Entity
public class Orders extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)

    private Long id;
    private String note;

    private Float total;

    private String userName;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="table_id")
    private Tables table;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns = @JoinColumn(name = "orders_id"),
            inverseJoinColumns = @JoinColumn(name = "order_details_id"),
            name = "orders_order_detail"
    )
    private List<OrderDetails> orderDetail;

    @Builder
    public Orders(Long id, String note, Float total,
                  List<OrderDetails> orderDetail, Boolean isDeleted, LocalDateTime creationDate,
                  LocalDateTime updateDate, LocalDateTime deleteDate,String userName,Tables table,User user) {        this.id = id;
        this.note = note;
        this.total = total;
        this.orderDetail = orderDetail;
        this.creationDate = Timestamp.valueOf(creationDate.atOffset(ZoneOffset.UTC).toLocalDateTime());
        this.isDeleted = isDeleted;
        this.deleteDate = deleteDate == null ? null : Timestamp.valueOf(deleteDate.atOffset(ZoneOffset.UTC).toLocalDateTime());
        this.updateDate = updateDate == null ? null : Timestamp.valueOf(updateDate.atOffset(ZoneOffset.UTC).toLocalDateTime());
        this.userName=userName;
        this.user=user;
        this.table=table;
    }

    public Orders() {
    }
}
