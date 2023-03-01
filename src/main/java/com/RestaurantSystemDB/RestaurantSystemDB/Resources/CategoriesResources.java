package com.RestaurantSystemDB.RestaurantSystemDB.Resources;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Categories;
import com.RestaurantSystemDB.RestaurantSystemDB.Services.CategoryServices;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Category")
public class CategoriesResources {
    private final CategoryServices categoryServices;

    public CategoriesResources(CategoryServices categoryServices){
        this.categoryServices=categoryServices;
    }

    @GetMapping("/allCategories")
    public ResponseEntity<List<Categories>> getAllCategories(){
        List<Categories> categories = categoryServices.findAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
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
        category.setCategoryId(id);
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
