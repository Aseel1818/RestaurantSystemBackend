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

    /*public void addRoleToUser(String userName, String roleName) {
        log.info("Adding role{} to user{} is done ", roleName, userName);

        User user = userRepository.findUserByName(userName);
        Role role = roleRepository.findByName(roleName);
        user.getRole().add(role);


    }
*/
    /*public User getUser(String userName) {
        log.info("Retreving user{} ", userName);

        return userRepository.findUserByName(userName);

    }*/
    public List<User> getUsers() {
        log.info("Retreving All users ");
        return userRepository.findAll();
    }

    public void InitializaUsersData() {
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("This is an admin Page");
        roleRepository.save(adminRole);
        //////////////////////////////
        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("This is an user Page");
        roleRepository.save(userRole);
        ///////////////////
        User adminUser = new User();
        adminUser.setUsername("Admin");
        adminUser.setPassword("Test");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userRepository.save(adminUser);
        /////////////////////////
        User user = new User();
        user.setUsername("Israa");
        user.setPassword("@ITest");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);
        userRepository.save(user);


    }
}
