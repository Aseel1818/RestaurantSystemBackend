package com.RestaurantSystemDB.RestaurantSystemDB.Controllers;


import com.RestaurantSystemDB.RestaurantSystemDB.Models.User;
import com.RestaurantSystemDB.RestaurantSystemDB.Services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @PostConstruct
    public void initRoleAndUser(){
        userService.InitializaUsersData();
    }
    @PostMapping({"/register"})
    public User registorNewUser(@RequestBody User user) {
        return userService.registorNewUser(user);
    }
    @GetMapping({"/forAdmin"})
    public String forAdmin(){
        return "This url is only accessible to Admin side ";
    }
    @GetMapping({"/forUser"})
    public String forUser(){
        return "This url is only accessible to the USER side ";
    }



}
