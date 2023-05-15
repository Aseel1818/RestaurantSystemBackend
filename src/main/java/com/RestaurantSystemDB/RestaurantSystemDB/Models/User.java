package com.RestaurantSystemDB.RestaurantSystemDB.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)

    private Long userId;
    private String username;
    private String password;

    public User(String username, String password, Collection<GrantedAuthority> mapRolesToAuth) {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE", joinColumns = {
            @JoinColumn(name = "USER_ID")
    },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )

    private Set<Role> role = new HashSet<>();

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }
}
