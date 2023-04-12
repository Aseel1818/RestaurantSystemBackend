package com.RestaurantSystemDB.RestaurantSystemDB.Repositories;


import com.RestaurantSystemDB.RestaurantSystemDB.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer > {
}
