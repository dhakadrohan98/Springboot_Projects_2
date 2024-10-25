package com.mockitotest.MockTest.Controller;

import com.mockitotest.MockTest.Entity.Item;
import com.mockitotest.MockTest.Service.ItemServiceImpl;
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
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Item item1 = this.itemService.saveItem(item);
        return new ResponseEntity<>(item1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> allItems = this.itemService.getAllItems();
        return new ResponseEntity<>(allItems, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        Item item = this.itemService.getItemById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
