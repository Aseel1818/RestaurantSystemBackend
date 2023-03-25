package com.RestaurantSystemDB.RestaurantSystemDB.Repositories;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface OrdersRepository  extends JpaRepository<Orders,Long>{
    void deleteOrderById(Long id);

    Optional<Orders> findOrderById(Long id);

    @Query(nativeQuery = true,value = "SELECT * FROM `orders`")
    List<Orders> getAll();
}