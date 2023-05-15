package com.RestaurantSystemDB.RestaurantSystemDB.Repositories;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoriesRepository  extends JpaRepository<Categories,Long>
{
    void deleteCategoryById(Long id);
    Optional<Categories> findCategoryById(Long id);


    @Query(nativeQuery = true,value = "SELECT * FROM `categories`")
    List<Categories> getAll();
}