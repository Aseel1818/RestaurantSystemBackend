package com.RestaurantSystemDB.RestaurantSystemDB.payload;

import com.RestaurantSystemDB.RestaurantSystemDB.Models.OrderDetails;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Tables;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
public class OrderPayload {
    private Long id;
    @Nullable
    private String note;

    private Float total;
    @Nullable
    private Long tables;

    @OneToMany
    private List<OrderDetailsPayload> orderDetail;
}