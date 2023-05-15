package com.RestaurantSystemDB.RestaurantSystemDB.Repositories;


import com.RestaurantSystemDB.RestaurantSystemDB.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //List<User> getUsers();
    default Optional<User> findByUsername(String username) {
        return null;
    }

    //Boolean isUserNameExists(String username);
}
