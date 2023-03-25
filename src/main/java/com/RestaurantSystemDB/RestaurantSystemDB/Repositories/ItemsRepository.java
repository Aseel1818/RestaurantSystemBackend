package com.RestaurantSystemDB.RestaurantSystemDB.Repositories;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Categories;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ItemsRepository extends JpaRepository<Items,Long> {
    void deleteItemById(Long id);

    Optional<Items> findItemById(Long id);

   /* @Query(nativeQuery = true, value = "SELECT * FROM items WHERE category_id = ?1")
 List<Items> findItemsByCategory(@Param("id") Long id);
*/

}
