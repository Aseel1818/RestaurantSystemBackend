package com.RestaurantSystemDB.RestaurantSystemDB.Services;

import com.RestaurantSystemDB.RestaurantSystemDB.Exceptions.CategoryNotFoundException;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Categories;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Items;
import com.RestaurantSystemDB.RestaurantSystemDB.Repositories.CategoriesRepository;
import com.RestaurantSystemDB.RestaurantSystemDB.Repositories.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServices {

    private final CategoriesRepository categoriesRepository;
    private final ItemsRepository itemsRepository;
    @Autowired
    public CategoryServices(CategoriesRepository categoriesRepository, ItemsRepository itemsRepository) {
        this.categoriesRepository = categoriesRepository;
        this.itemsRepository = itemsRepository;
    }

    public Categories addCategory(Categories category) {
        return categoriesRepository.save(category);

    }

    public List<Items> findItemsByCategoryId(Long categoryId) {
        return itemsRepository.findItemsByCategoryId(categoryId);
    }

    public Categories updateCategory(Categories category) {
        Categories existingCategory = categoriesRepository.findById(category.getId()).get();
        existingCategory.setName(category.getName());
        Categories updatedCategory = categoriesRepository.save(existingCategory);
        return updatedCategory;
    }

    public void deleteCategory(Long id) {
        categoriesRepository.deleteCategoryById(id);
    }

    public List<Categories> findAllCategories() {
        return categoriesRepository.getAll().stream()
                .filter(category -> !category.getIsDeleted())
                .collect(Collectors.toList());
    }
    public Categories findCategoryById(Long id) {
        return categoriesRepository.findCategoryById(id).orElseThrow(() -> new CategoryNotFoundException("Category with this id " + id + "does not exist"));
    }
}
