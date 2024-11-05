package com.mockitotest.MockTest.Service;

import com.mockitotest.MockTest.Entity.Item;

import java.util.List;

public interface ItemService {

    public Item saveItem(Item item);

    public List<Item> getAllItems();

    public Item getItemById(Long id);

    public Item updateItem(Long id, Item item);

    public void deleteItem(Long id);
}
