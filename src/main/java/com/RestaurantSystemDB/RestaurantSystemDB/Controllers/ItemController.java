package com.RestaurantSystemDB.RestaurantSystemDB.Controllers;

import com.RestaurantSystemDB.RestaurantSystemDB.Audit.AuditorAwareImpl;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Items;
import com.RestaurantSystemDB.RestaurantSystemDB.Repositories.ItemsRepository;
import com.RestaurantSystemDB.RestaurantSystemDB.Services.AuditLogService;
import com.RestaurantSystemDB.RestaurantSystemDB.Services.ItemsServices;
import com.RestaurantSystemDB.RestaurantSystemDB.Services.UserDetailsImpl;
import com.RestaurantSystemDB.RestaurantSystemDB.jwt.JwtUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/item")
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
public class ItemController {
    @Autowired
    private AuditLogService auditLogService;
    @Autowired
    private ItemsRepository itemsRepository ;
    private final ItemsServices itemsServices;

    public ItemController(ItemsServices itemsServices, AuditLogService auditLogService, JwtUtils jwtUtils, ItemsRepository itemsRepository) {
        this.itemsServices = itemsServices;
        this.auditLogService = auditLogService;
        this.itemsRepository = itemsRepository;    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/items")
    public ResponseEntity<?>getAll(){
        return ResponseEntity.ok(itemsServices.getAll());
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/findItem/{id}")
    public ResponseEntity<Items> getItemById(@PathVariable("id") Long id) {
        Items item = itemsServices.findItemById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addItem")
    public ResponseEntity<Items> addItem(@RequestBody Items item,
                                         @RequestHeader(name = "Authorization") String token) {
        Items newItem = itemsServices.addItem(item);
        Long userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        String tableName = AuditorAwareImpl.getTableName(Items.class);
        auditLogService.createAuditLog(tableName, "create", userId);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/updateItem/{id}")
    public ResponseEntity<Items> updateItem(@PathVariable("id") Long id,
                                            @RequestBody Items item,@RequestHeader(name = "Authorization") String token) {
        item.setId(id);
        Items updatedItem = itemsServices.updateItem(item);
        Long userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        String tableName = AuditorAwareImpl.getTableName(Items.class);
        auditLogService.createAuditLog(tableName, "update", userId);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteItem/{id}")
    @Transactional
    public ResponseEntity<?> deleteItem(@PathVariable("id") Long id,@RequestHeader(name = "Authorization") String token ) {
        itemsServices.deleteItem(id);
        Long userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        String tableName = AuditorAwareImpl.getTableName(Items.class);
        auditLogService.createAuditLog(tableName, "delete", userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
