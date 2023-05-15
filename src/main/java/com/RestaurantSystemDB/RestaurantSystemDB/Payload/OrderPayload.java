package com.RestaurantSystemDB.RestaurantSystemDB.Payload;

import jakarta.annotation.Nullable;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderPayload {
    private Long id;
    @Nullable
    private String note;

    private Float total;
    @Nullable
    private Long tables;
    private Date payment_Date;
    private Date update_Date;
    private Date created_Date;

    private List<OrderDetailsPayload> orderDetail;
}
