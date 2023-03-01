package com.RestaurantSystemDB.RestaurantSystemDB.Services;
import com.RestaurantSystemDB.RestaurantSystemDB.Exceptions.ItemNotFoundException;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Items;
import com.RestaurantSystemDB.RestaurantSystemDB.Repositories.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemsServices {

    private final ItemsRepository itemsRepository;

    @Autowired
    public ItemsServices(ItemsRepository itemsRepository){
        this.itemsRepository=itemsRepository;
    }

    public Items addItem (Items item){
        return  itemsRepository.save(item);

    }


    public Items updateItem(Items item) {
        Items existingItem = itemsRepository.findById(item.getItemID()).get();
        existingItem.setItemName(item.getItemName());
        existingItem.setItemImageURL(item.getItemImageURL());
        existingItem.setItemPrice(item.getItemPrice());

        Items updatedItem = itemsRepository.save(existingItem);
        return updatedItem;
    }

    public void deleteItem(Long id){
        itemsRepository.deleteItemById(id);
    }

    public List<Items> findAllItems(){
        return itemsRepository.findAll();
    }

    public Items findItemById(Long id ){
        return itemsRepository.findItemById(id).orElseThrow(()->new ItemNotFoundException("Item with this id "+id+"does not exist"));


    }
}
