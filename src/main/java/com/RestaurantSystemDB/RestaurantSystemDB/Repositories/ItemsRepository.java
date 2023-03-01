package com.RestaurantSystemDB.RestaurantSystemDB.Repositories;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ItemsRepository extends JpaRepository<Items,Long> {

    void deleteItemById(Long id);

    Optional<Items> findItemById(Long id);
}
