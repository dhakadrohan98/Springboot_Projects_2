package com.mockitotest.MockTest.Service;

import com.mockitotest.MockTest.Entity.Item;
import com.mockitotest.MockTest.Repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ItemServiceBDDTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemServiceImpl itemServiceImpl;

    private Item item;

    @BeforeEach
    public void setup() {
        item = new Item(1L,"harry potter by JK Rowling", 20, 400);
    }

    @DisplayName("Junit test to saveItem() method")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject() {
        //given - precondition or setup
        given(itemRepository.save(item)).willReturn(item);

        //when - action or the behaviour that we are going test in Service layer
        Item savedItem = itemServiceImpl.saveItem(item);

        //print the objects to verify that repository & service are mocked properly
        System.out.println(itemRepository);
        System.out.println(itemServiceImpl);
        System.out.println(savedItem);
        //then - verify the output
        Assertions.assertThat(savedItem).isNotNull();
        Assertions.assertThat(savedItem.getName()).isEqualToIgnoringCase("harry potter by JK Rowling");

        then(itemRepository).should().save(item);
    }

    @DisplayName("test getByItemByName() method")
    @Test
    public void testGetItemByName() {
        //given precondition or setup (calling the method of ItemRepository in
        // the same way as getItemByName() method did)
        given(itemRepository.findByNameIgnoreCase(item.getName())).willReturn(item);

        //when - action or behaviour we are going to test in the service layer
        Item itemByName = itemServiceImpl.getItemByName(item.getName());

        //print the objects to verify that repository & service are mocked or not
        System.out.println(itemRepository);
        System.out.println(itemServiceImpl);
        System.out.println(itemByName);

        //then - verify the output
        Assertions.assertThat(itemByName).isNotNull();
        Assertions.assertThat(itemByName.getName()).isEqualToIgnoringCase("harry potter by JK rowling");

        then(itemRepository).should().findByNameIgnoreCase(item.getName());
    }

}
