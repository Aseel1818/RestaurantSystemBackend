package com.RestaurantSystemDB.RestaurantSystemDB.Services;
import com.RestaurantSystemDB.RestaurantSystemDB.Exceptions.CategoryNotFoundException;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Categories;
import com.RestaurantSystemDB.RestaurantSystemDB.Repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServices {

    private final CategoriesRepository categoriesRepository;

    @Autowired
    public CategoryServices(CategoriesRepository categoriesRepository){
        this.categoriesRepository=categoriesRepository;
    }

    public Categories addCategory (Categories category){
    return  categoriesRepository.save(category);

    }


    public Categories updateCategory(Categories category) {
        Categories existingCategory = categoriesRepository.findById(category.getCategoryID()).get();
        existingCategory.setCategoryName(category.getCategoryName());
        existingCategory.setCategoryImageURL(category.getCategoryImageURL());
        Categories updatedCategory = categoriesRepository.save(existingCategory);
        return updatedCategory;
    }

    public void deleteCategory(Long id){
categoriesRepository.deleteCategoryById(id);
    }

    public List<Categories> findAllCategories(){
        return categoriesRepository.findAll();
    }

    public Categories findCategoryById(Long id ){
        return categoriesRepository.findCategoryById(id).orElseThrow(()->new CategoryNotFoundException("Category with this id "+id+"does not exist"));


    }
}
