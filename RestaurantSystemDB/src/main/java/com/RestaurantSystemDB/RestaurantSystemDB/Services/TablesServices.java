package com.RestaurantSystemDB.RestaurantSystemDB.Services;
import com.RestaurantSystemDB.RestaurantSystemDB.Exceptions.TableNotFoundException;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Tables;
import com.RestaurantSystemDB.RestaurantSystemDB.Repositories.TablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TablesServices {

    private final TablesRepository tablesRepository;

    @Autowired
    public TablesServices(TablesRepository tablesRepository){
        this.tablesRepository=tablesRepository;
    }

    public Tables addTable (Tables table){
        return  tablesRepository.save(table);

    }


    public Tables updateTable(Tables table) {
        Tables existingTable = tablesRepository.findById(table.getTableID()).get();
        existingTable.setTableStatus(table.getTableStatus());
        existingTable.setTableName(table.getTableName());
        Tables updatedTable = tablesRepository.save(existingTable);
        return updatedTable;
    }

    public void deleteTable(Long id){
        tablesRepository.deleteTableById(id);
    }

    public List<Tables> findAllTables(){
        return tablesRepository.findAll();
    }

    public Tables findTableById(Long id ){
        return tablesRepository.findTableById(id).orElseThrow(()->new TableNotFoundException("Table with this id "+id+"does not exist"));


    }
}
