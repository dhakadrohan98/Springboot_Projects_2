package com.mockitotest.MockTest.Controller;

import com.mockitotest.MockTest.Entity.Item;
import com.mockitotest.MockTest.Service.ItemServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemServiceImpl itemService;

    @PostMapping
    @Operation(summary = "Create a item")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Item created"),
        @ApiResponse(responseCode = "400", description = "Invalid Input")
    })
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Item item1 = this.itemService.saveItem(item);
        return new ResponseEntity<>(item1, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all items")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Found all items"),
            @ApiResponse(responseCode = "404", description = "Items not found")
    })
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> allItems = this.itemService.getAllItems();
        return new ResponseEntity<>(allItems, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an item by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the items"),
            @ApiResponse(responseCode = "500", description = "Item not found")
    })
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        Item item = this.itemService.getItemById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an item by its id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Item updated"),
            @ApiResponse(responseCode = "404", description = "Item not found"),
            @ApiResponse(responseCode = "400", description = "Invalid Input")
    })
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item item) {
        Item itemToUpdate = this.itemService.getItemById(id);
        itemToUpdate.setName(item.getName());
        itemToUpdate.setQuantity(item.getQuantity());
        itemToUpdate.setPrice(item.getPrice());
        itemService.saveItem(itemToUpdate);
        return new ResponseEntity<>(itemToUpdate, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an item by id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Item deleted"),
            @ApiResponse(responseCode = "404", description = "Item not found")
    })
    public void deleteItem(@PathVariable Long id) {
        this.itemService.deleteItem(id);
    }
}
