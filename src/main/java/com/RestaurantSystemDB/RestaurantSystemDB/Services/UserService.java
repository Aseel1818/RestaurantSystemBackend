package com.RestaurantSystemDB.RestaurantSystemDB.Services;


import com.RestaurantSystemDB.RestaurantSystemDB.Models.Role;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.User;
import com.RestaurantSystemDB.RestaurantSystemDB.Repositories.RoleRepository;
import com.RestaurantSystemDB.RestaurantSystemDB.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository ;
    @Autowired
    private RoleRepository roleRepository;

    public User registorNewUser(User user ){
        return userRepository.save(user);


    }
    public void InitializaUsersData(){
        Role adminRole= new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("This is an admin Page");
        roleRepository.save(adminRole);
        //////////////////////////////
        Role userRole =new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("This is an user Page");
        roleRepository.save(userRole);
        ///////////////////
        User adminUser = new User();
        adminUser.setUserId(1);
        adminUser.setUsername("Admin");
        adminUser.setPassword("Test");
        Set<Role> adminRoles=new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userRepository.save(adminUser);
        /////////////////////////
        User user = new User();
        user.setUserId(2);
        user.setUsername("Israa");
        user.setPassword("@ITest");
        Set<Role> userRoles=new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);
        userRepository.save(user);





    }
}
