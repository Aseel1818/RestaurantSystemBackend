package com.RestaurantSystemDB.RestaurantSystemDB.Repositories;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ItemsRepository extends JpaRepository<Items,Long> {
    void deleteItemById(Long id);

   @Query("SELECT i FROM Items i WHERE i.id = :id")
   Optional<Items> findItemById(@Param("id") Long id);

   @Query("SELECT i FROM Items i WHERE i.category.id = :categoryId")
   List<Items> findItemsByCategoryId(@Param("categoryId") Long categoryId);
   @Query(nativeQuery = true,value = "SELECT * FROM `items`")
   List<Items> getAll();
}
