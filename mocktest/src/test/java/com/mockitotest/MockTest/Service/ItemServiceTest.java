package com.mockitotest.MockTest.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import com.mockitotest.MockTest.Entity.Item;
import com.mockitotest.MockTest.Repository.ItemRepository;
import org.assertj.core.api.Assert;
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
        item1 = new Item(1L, "Notebook", 50, 2.0);
        item2 = new Item(1L, "JavaBook", 100, 600.0);
    }

    @Test
    void testSaveItem() {
        when(itemRepository.save(item1)).thenReturn(item1);
        Item createdItem = itemServiceImpl.saveItem(item1);
        assertEquals(item1, createdItem);
        verify(itemRepository, times(1)).save(item1);
    }

}
