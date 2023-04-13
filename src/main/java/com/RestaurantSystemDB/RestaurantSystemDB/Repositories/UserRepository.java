package com.RestaurantSystemDB.RestaurantSystemDB.Repositories;


import com.RestaurantSystemDB.RestaurantSystemDB.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //List<User> getUsers();
    Optional<User> findByUserName(String username);

    //Boolean isUserNameExists(String username);
}
