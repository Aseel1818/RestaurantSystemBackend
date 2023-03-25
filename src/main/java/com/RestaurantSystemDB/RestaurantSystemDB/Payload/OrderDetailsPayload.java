package com.RestaurantSystemDB.RestaurantSystemDB.Payload;


import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class OrderDetailsPayload {
    private int quantity;

    @Nullable
    private String note;

    private Long itemId;

    private long orderId;

}
