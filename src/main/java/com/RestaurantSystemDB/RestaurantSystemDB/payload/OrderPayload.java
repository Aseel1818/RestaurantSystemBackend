package com.RestaurantSystemDB.RestaurantSystemDB.payload;

import com.RestaurantSystemDB.RestaurantSystemDB.Models.OrderDetails;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Tables;
import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;
@Data
public class OrderPayload {
    @Nullable
    private String note;

    private Float total;
    @Nullable
    private Long tables;

    private List<OrderDetailsPayload> orderDetail;
}
