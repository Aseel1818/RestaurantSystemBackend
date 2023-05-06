package com.RestaurantSystemDB.RestaurantSystemDB.Controllers;

import com.RestaurantSystemDB.RestaurantSystemDB.Models.Items;
import com.RestaurantSystemDB.RestaurantSystemDB.Services.ItemsServices;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/item")
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
public class ItemController {

    private final ItemsServices itemsServices;

    public ItemController(ItemsServices itemsServices) {
        this.itemsServices = itemsServices;
    }
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
    public ResponseEntity<Items> addItem(@RequestBody Items item) {
        Items newItem = itemsServices.addItem(item);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/updateItem/{id}")
    public ResponseEntity<Items> updateItem(@PathVariable("id") Long id,
                                            @RequestBody Items item) {
        item.setId(id);
        Items updatedItem = itemsServices.updateItem(item);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteItem/{id}")
    @Transactional
    public ResponseEntity<?> deleteItem(@PathVariable("id") Long id) {
        itemsServices.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
