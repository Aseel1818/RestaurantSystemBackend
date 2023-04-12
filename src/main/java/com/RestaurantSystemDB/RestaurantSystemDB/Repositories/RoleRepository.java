package com.RestaurantSystemDB.RestaurantSystemDB.Repositories;


import com.RestaurantSystemDB.RestaurantSystemDB.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository  extends JpaRepository<Role,String> {
}
