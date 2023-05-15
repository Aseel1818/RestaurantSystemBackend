package com.RestaurantSystemDB.RestaurantSystemDB.Repositories;


import com.RestaurantSystemDB.RestaurantSystemDB.Models.ERole;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {

    Optional<Role> findByName(ERole name);
}
