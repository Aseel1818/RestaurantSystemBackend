package com.RestaurantSystemDB.RestaurantSystemDB.Controllers;

import com.RestaurantSystemDB.RestaurantSystemDB.Models.Tables;
import com.RestaurantSystemDB.RestaurantSystemDB.Payload.UpdateTablesPayload;
import com.RestaurantSystemDB.RestaurantSystemDB.Services.TablesServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/rest/table")
@CrossOrigin(origins = "*")
public class TablesController {

    private final TablesServices tablesServices;

    public TablesController(TablesServices tablesServices) {
        this.tablesServices = tablesServices;
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/tables")
    public ResponseEntity<List<Tables>> getAllTables() {
        List<Tables> tables = tablesServices.findAllTables();
        return new ResponseEntity<>(tables, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/findTable/{id}")
    public ResponseEntity<Tables> getTableById(@PathVariable("id") Long id) {
        Tables table = tablesServices.findTableById(id);
        return new ResponseEntity<>(table, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")

    @PostMapping("/addTable")

    public ResponseEntity<Tables> addTable(@RequestBody Tables table) {
        Tables newTable = tablesServices.addTable(table);
        return new ResponseEntity<>(newTable, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PutMapping("/updateTable")
    public ResponseEntity<List<Tables>> updateTable(@RequestBody UpdateTablesPayload tableIDs) {
        List<Tables> result = new ArrayList<>();
        for (int i = 0; i < tableIDs.getTableIDs().size(); i++) {
            Long tableId = tableIDs.getTableIDs().get(i);
            Tables table = tablesServices.findTableById(tableId);
            table.setStatus(!table.isStatus());
            Tables updatedTable = tablesServices.updateTable(table);
            result.add(updatedTable);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteTable/{id}")
    public ResponseEntity<?> deleteTable(@PathVariable("id") Long id) {
        tablesServices.deleteTable(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
