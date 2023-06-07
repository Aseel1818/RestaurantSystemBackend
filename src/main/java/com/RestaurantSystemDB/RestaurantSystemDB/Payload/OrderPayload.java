package com.RestaurantSystemDB.RestaurantSystemDB.Payload;

import jakarta.annotation.Nullable;
import lombok.Data;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Tables;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.User;

import java.util.Date;
import java.util.List;

@Data
public class OrderPayload {
    private Long id;
    @Nullable
    private String note;

    private Float total;
    @Nullable
    private Long tableID;
    private Date payment_Date;
    private Date update_Date;
    private Date created_Date;

    private User user;
    private Tables table;

    private List<OrderDetailsPayload> orderDetail;
}
