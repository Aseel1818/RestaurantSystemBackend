package com.RestaurantSystemDB.RestaurantSystemDB.Payload;
import lombok.Data;
import java.util.List;
@Data
public class UpdateTablesPayload {
    private List<Long> tableIDs;
}
