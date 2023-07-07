package com.RestaurantSystemDB.RestaurantSystemDB.Services;

import com.RestaurantSystemDB.RestaurantSystemDB.Exceptions.TableNotFoundException;
import com.RestaurantSystemDB.RestaurantSystemDB.Models.Tables;
import com.RestaurantSystemDB.RestaurantSystemDB.Repositories.TablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TablesServices {

    private final TablesRepository tablesRepository;

    @Autowired
    public TablesServices(TablesRepository tablesRepository) {
        this.tablesRepository = tablesRepository;
    }

    public Tables addTable(Tables table) {
        return tablesRepository.save(table);
    }

    public Tables updateTable(Tables updatedTable) {
        return tablesRepository.save(updatedTable);
    }

    public void deleteTable(Long id) {
        tablesRepository.deleteTableById(id);
    }

    public List<Tables> findAllTables() {
        return tablesRepository.findAll().stream()
                .filter(table -> !table.getIsDeleted())
                .collect(Collectors.toList());
    }
    public Tables findTableById(Long id) {
        if (id == null) {
            return null;
        }
        return tablesRepository.findTableById(id)
                .orElseThrow(() -> new TableNotFoundException("Table with this id " + id + " does not exist"));
    }
}
