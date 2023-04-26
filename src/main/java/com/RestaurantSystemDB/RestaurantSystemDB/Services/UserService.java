package com.RestaurantSystemDB.RestaurantSystemDB.Services;

import com.RestaurantSystemDB.RestaurantSystemDB.Models.Role;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.User;
import com.RestaurantSystemDB.RestaurantSystemDB.Repositories.RoleRepository;
import com.RestaurantSystemDB.RestaurantSystemDB.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public User registorNewUser(User user) {
        log.info("user is saved to the db", user.getUsername());
        return userRepository.save(user);


    }
    public List<User> getUsers() {
        log.info("Retreving All users ");
        return userRepository.findAll();
    }
}
