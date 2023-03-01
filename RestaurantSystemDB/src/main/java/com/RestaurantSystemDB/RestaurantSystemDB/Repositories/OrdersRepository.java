package com.RestaurantSystemDB.RestaurantSystemDB.Repositories;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface OrdersRepository  extends JpaRepository<Orders,Long>{
    void deleteOrderById(Long id);

    Optional<Orders> findOrderById(Long id);
}
