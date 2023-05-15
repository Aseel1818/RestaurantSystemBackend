package com.RestaurantSystemDB.RestaurantSystemDB.Repositories;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Tables;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TablesRepository  extends JpaRepository<Tables,Long> {

    void deleteTableById(Long id);

    Optional<Tables> findTableById(Long id);
}