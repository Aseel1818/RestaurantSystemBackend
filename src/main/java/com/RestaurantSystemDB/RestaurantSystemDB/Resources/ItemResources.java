package com.RestaurantSystemDB.RestaurantSystemDB.Resources;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Items;
import com.RestaurantSystemDB.RestaurantSystemDB.Services.ItemsServices;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Item")
public class ItemResources {

    private final ItemsServices itemsServices;

    public ItemResources(ItemsServices itemsServices){
        this.itemsServices=itemsServices;
    }

    @GetMapping("/allItems")
    public ResponseEntity<List<Items>> getAllItems(){
        List<Items> items = itemsServices.findAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/findItem/{id}")
    public ResponseEntity<Items> getItemById (@PathVariable("id") Long id){
        Items item = itemsServices.findItemById(id);
        return new ResponseEntity<>(item,HttpStatus.OK);
    }



    @PostMapping("/addItem")

    public ResponseEntity<Items> addItem(@RequestBody Items item){
        Items  newItem = itemsServices.addItem(item);
        return new ResponseEntity<>(newItem,HttpStatus.CREATED);
    }

    @PutMapping("/updateItem/{id}")
    public ResponseEntity<Items> updateItem(@PathVariable("id") Long id,
                                                     @RequestBody Items item){
        item.setItemId(id);
        Items updatedItem = itemsServices.updateItem(item);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }


    @DeleteMapping("/deleteItem/{id}")
    @Transactional
    public ResponseEntity<?> deleteItem (@PathVariable("id") Long id ){
        itemsServices.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
