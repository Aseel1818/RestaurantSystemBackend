package com.RestaurantSystemDB.RestaurantSystemDB.Resources;

import com.RestaurantSystemDB.RestaurantSystemDB.Models.Tables;
import com.RestaurantSystemDB.RestaurantSystemDB.Services.TablesServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping()
@CrossOrigin(origins = "*")

public class TablesResources {

    private final TablesServices tablesServices;

    public TablesResources(TablesServices tablesServices) {
        this.tablesServices = tablesServices;
    }

    @GetMapping("/tables")
    public ResponseEntity<List<Tables>> getAllTables() {
        List<Tables> tables = tablesServices.findAllTables();
        return new ResponseEntity<>(tables, HttpStatus.OK);
    }

    @GetMapping("/findTable/{id}")
    public ResponseEntity<Tables> getTableById(@PathVariable("id") Long id) {
        Tables table = tablesServices.findTableById(id);
        return new ResponseEntity<>(table, HttpStatus.OK);
    }

    @PostMapping("/addTable")

    public ResponseEntity<Tables> addTable(@RequestBody Tables table) {
        Tables newTable = tablesServices.addTable(table);
        return new ResponseEntity<>(newTable, HttpStatus.CREATED);
    }

    
    @PutMapping("/updateTable/{id}")
    public ResponseEntity<Tables> updateTable(@PathVariable("id") Long id, @RequestBody Tables updatedTable) {
        Boolean newStatus = !updatedTable.isStatus();
        updatedTable.setStatus(newStatus);
        updatedTable = tablesServices.updateTable(updatedTable);
        return new ResponseEntity<>(updatedTable, HttpStatus.OK);
    }


    @DeleteMapping("/deleteTable/{id}")
    public ResponseEntity<?> deleteTable(@PathVariable("id") Long id) {
        tablesServices.deleteTable(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
