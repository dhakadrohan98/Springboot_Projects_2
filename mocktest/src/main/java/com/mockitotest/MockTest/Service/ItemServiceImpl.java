package com.mockitotest.MockTest.Service;

import com.mockitotest.MockTest.Entity.Item;
import com.mockitotest.MockTest.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item saveItem(Item item) {
        return this.itemRepository.save(item);
    }

    @Override
    public List<Item> getAllItems() {
        return this.itemRepository.findAll();
    }

    @Override
    public Item getItemById(Long id) {
        return this.itemRepository.findById(id).get();
    }

    @Override
    public Item getItemByName(String name) {
        return itemRepository.findByNameIgnoreCase(name);
    }

    @Override
    public Item updateItem(Long id, Item item) {
        return this.itemRepository.save(item);
    }

    @Override
    public void deleteItem(Long id) {
        this.itemRepository.deleteById(id);
    }
}
