package com.RestaurantSystemDB.RestaurantSystemDB.Repositories;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Long> {

    void deleteOrderDetailById(Long id);

    Optional<OrderDetails> findOrderDetailById(Long id);
}
