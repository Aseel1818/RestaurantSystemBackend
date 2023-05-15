package com.RestaurantSystemDB.RestaurantSystemDB.Controllers;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Categories;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Items;
import com.RestaurantSystemDB.RestaurantSystemDB.Repositories.ItemsRepository;
import com.RestaurantSystemDB.RestaurantSystemDB.Services.CategoryServices;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
@RequestMapping("/rest/category")
public class CategoriesController {
    private final CategoryServices categoryServices;
    private final ItemsRepository itemsRepository;

    public CategoriesController(CategoryServices categoryServices,
                                ItemsRepository itemsRepository){
        this.categoryServices=categoryServices;
        this.itemsRepository = itemsRepository;
    }
    @GetMapping()
    public ResponseEntity<List<Categories>> getAllCategories(){
        List<Categories> categories = categoryServices.findAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<List<Items>> getItemsByCategoryId(@PathVariable("id") Long id) {
        List<Items> items = itemsRepository.findItemsByCategoryId(id);
        return ResponseEntity.ok(items);
    }


    @GetMapping("/findCategory/{id}")
    public ResponseEntity<Categories> getCategoryById (@PathVariable("id") Long id){
        Categories category = categoryServices.findCategoryById(id);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }

    @PostMapping("/addCategory")
    public ResponseEntity<Categories> addCategory(@RequestBody Categories category){
        Categories  newCategory = categoryServices.addCategory(category);
        return new ResponseEntity<>(newCategory,HttpStatus.CREATED);
    }

    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<Categories> updateCategory(@PathVariable("id") Long id,
                                           @RequestBody Categories category){
        category.setId(id);
        Categories updatedCategory = categoryServices.updateCategory(category);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCategory/{id}")
    @Transactional
    public ResponseEntity<?> deleteCategory (@PathVariable("id") Long id ){
        categoryServices.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
