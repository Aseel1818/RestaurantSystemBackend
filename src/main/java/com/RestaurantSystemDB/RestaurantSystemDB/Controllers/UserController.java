package com.RestaurantSystemDB.RestaurantSystemDB.Controllers;

import com.RestaurantSystemDB.RestaurantSystemDB.Audit.AuditLog;
import com.RestaurantSystemDB.RestaurantSystemDB.Audit.AuditorAwareImpl;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.ERole;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Role;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.User;
import com.RestaurantSystemDB.RestaurantSystemDB.Payload.Request.SignupRequest;
import com.RestaurantSystemDB.RestaurantSystemDB.Payload.Response.MessageResponse;
import com.RestaurantSystemDB.RestaurantSystemDB.Repositories.RoleRepository;
import com.RestaurantSystemDB.RestaurantSystemDB.Repositories.UserRepository;
import com.RestaurantSystemDB.RestaurantSystemDB.Services.AuditLogService;
import com.RestaurantSystemDB.RestaurantSystemDB.Services.UserService;
import com.RestaurantSystemDB.RestaurantSystemDB.jwt.JwtUtils;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/users")

public class UserController {

    private final AuditLogService auditLogService;
    private final UserRepository yourEntityRepository;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private UserService userService;


    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    public UserController(AuditLogService auditLogService, UserRepository yourEntityRepository) {
        this.auditLogService = auditLogService;
        this.yourEntityRepository = yourEntityRepository;
    }

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/adduser")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        User user = new User(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ROLE_ADMIN":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        String tableName = AuditorAwareImpl.getTableName(User.class);
        auditLogService.createAuditLog(user.getId(), tableName, "create");
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
    /*@GetMapping("/get/{userId}")
    public ResponseEntity<User> getUserByUserId(@PathVariable(value = "userId") int userId) {
        return new ResponseEntity<>(userService.getUserByUserId(userId).get(), HttpStatus.OK);
    }*/





}