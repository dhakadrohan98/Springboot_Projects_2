package com.mockitotest.MockTest.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.mockitotest.MockTest.Entity.Item;
import com.mockitotest.MockTest.Repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//integrates Mockito with JUnit 5.
@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    /**
     * @Mock and @InjectMocks are used to mock dependencies and inject them
     */
    @Mock
    public ItemRepository itemRepository;

    @InjectMocks
    private ItemServiceImpl itemServiceImpl;

    private Item item1;
    private Item item2;

    @BeforeEach
    public void setUp() {
        item1 = new Item(1L, "Note", 55, 20.0);
        item2 = new Item(1L, "Java", 105, 600.0);
    }

    @Test
    public void testCreatItem() {
        Item item = new Item();
        item.setName("Groking the coding interview");
        item.setPrice(600);
        item.setQuantity(1000);
        when(itemRepository.save(item)).thenReturn(item);

        Item createdItem = itemServiceImpl.saveItem(item);
        System.out.println(createdItem);
        assertNotNull(createdItem);
        //comparison based on id which is null
        assertEquals(createdItem.getId(), item.getId());
        //comparison based on item's name
        assertEquals("Grokking the coding interview", createdItem.getName(), "String should be equal");
        verify(itemRepository, times(1)).save(item);
    }

    @Test
    void testSaveItem() {
        when(itemRepository.save(item1)).thenReturn(item1);

        Item createdItem = itemServiceImpl.saveItem(item1);
        assertNotNull(createdItem);
        assertEquals(item1, createdItem);
        verify(itemRepository, times(1)).save(item1);
    }

    @Test
    public void testGetItemById() {
        Item item = new Item();
        item.setId(1L);
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));

        Item result = itemServiceImpl.getItemById(1L);
        System.out.println(result);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(itemRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllItems() {
        List<Item> itemList = Arrays.asList(item1, item2);

        when(itemRepository.findAll()).thenReturn(itemList);

        List<Item> list = itemServiceImpl.getAllItems();
        assertNotNull(list);
        assertEquals(2, list.size());
        verify(itemRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateItem() {
        Item item = new Item();
        item.setId(2L);
        item.setName("updated item");
        item.setPrice(700);
        item.setQuantity(500);

        when(itemRepository.save(item)).thenReturn(item);

        Item updatedItem = itemServiceImpl.updateItem(2L, item);
        assertNotNull(updatedItem);
        assertEquals("updated item", updatedItem.getName(), "Name " +
                "should be equals");
        verify(itemRepository, times(1)).save(item);
    }

    @Test
    public void testDeleteItemById() {
        Long itemId1 = 1L;
        doNothing().when(itemRepository).deleteById(itemId1);
        Long itemId2 = 2L;
        doNothing().when(itemRepository).deleteById(itemId2);

        itemServiceImpl.deleteItem(itemId1);
        itemServiceImpl.deleteItem(itemId2);
        verify(itemRepository, times(1)).deleteById(itemId1);
    }

}
