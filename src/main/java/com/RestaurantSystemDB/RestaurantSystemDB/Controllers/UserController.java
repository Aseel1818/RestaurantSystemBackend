package com.RestaurantSystemDB.RestaurantSystemDB.Controllers;

import com.RestaurantSystemDB.RestaurantSystemDB.Audit.AuditorAwareImpl;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.ERole;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Role;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.User;
import com.RestaurantSystemDB.RestaurantSystemDB.Payload.Request.SignupRequest;
import com.RestaurantSystemDB.RestaurantSystemDB.Payload.Response.MessageResponse;
import com.RestaurantSystemDB.RestaurantSystemDB.Repositories.ItemsRepository;
import com.RestaurantSystemDB.RestaurantSystemDB.Repositories.RoleRepository;
import com.RestaurantSystemDB.RestaurantSystemDB.Repositories.UserRepository;
import com.RestaurantSystemDB.RestaurantSystemDB.Services.AuditLogService;
import com.RestaurantSystemDB.RestaurantSystemDB.Services.UserDetailsImpl;
import com.RestaurantSystemDB.RestaurantSystemDB.Services.UserService;
import com.RestaurantSystemDB.RestaurantSystemDB.jwt.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/users")

public class UserController {
    private final UserRepository yourEntityRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private AuditLogService auditLogService;
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemsRepository itemsRepository ;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    private final AuditorAwareImpl auditorAware;

    public UserController(AuditLogService auditLogService, UserRepository yourEntityRepository, JwtUtils jwtUtils) {
        this.auditLogService = auditLogService;
        this.yourEntityRepository = yourEntityRepository;
        this.auditorAware = new AuditorAwareImpl(jwtUtils, userRepository);
    }

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/adduser")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest,
                                          @RequestHeader (name="Authorization") String token) {
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

        String token2 = token.split(" ")[1];
        if (token2.length() == 0) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid token."));
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        System.out.println(userPrincipal.getId());
        Long userId = userPrincipal.getId();

        String tableName = AuditorAwareImpl.getTableName(User.class);
        auditLogService.createAuditLog( tableName, "create", userId);

        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}