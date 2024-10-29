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

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

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
        assertNotNull(createdItem);
        assertEquals("Grokking the coding interview", createdItem.getName(), "String should be equal");
        verify(itemRepository, times(1)).save(item);
    }

    @Test
    void testSaveItem() {
        when(itemRepository.save(item1)).thenReturn(item1);
        Item createdItem = itemServiceImpl.saveItem(item1);
        assertEquals(item1, createdItem);
        verify(itemRepository, times(1)).save(item1);
    }

}
