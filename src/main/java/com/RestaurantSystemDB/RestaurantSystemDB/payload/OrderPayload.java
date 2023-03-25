package com.RestaurantSystemDB.RestaurantSystemDB.payload;

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

    private List<OrderDetailsPayload> orderDetail;
}
