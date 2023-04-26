package com.RestaurantSystemDB.RestaurantSystemDB.Controllers;

import com.RestaurantSystemDB.RestaurantSystemDB.Models.Tables;
import com.RestaurantSystemDB.RestaurantSystemDB.Services.TablesServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/rest/table")
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
public class TablesController {

    private final TablesServices tablesServices;

    public TablesController(TablesServices tablesServices) {
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
