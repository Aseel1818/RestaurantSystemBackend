package com.RestaurantSystemDB.RestaurantSystemDB.Controllers;


import com.RestaurantSystemDB.RestaurantSystemDB.Models.User;
import com.RestaurantSystemDB.RestaurantSystemDB.Services.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/api"})
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping({"/forAdmin"})
    public String forAdmin() {
        return "This url is only accessible to Admin side ";
    }

    @GetMapping({"/forUser"})
    public String forUser() {
        return "This url is only accessible to the USER side ";
    }

    @GetMapping({"/users"})
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostConstruct
    public void initRoleAndUser() {
        userService.InitializaUsersData();
    }

    @PostMapping({"/user/add"})
    public User registorNewUser(@RequestBody User user) {

        return userService.registorNewUser(user);
    }


}
