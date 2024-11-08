package com.mockitotest.MockTest.Service;

import com.mockitotest.MockTest.Entity.Item;
import com.mockitotest.MockTest.Repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ItemServiceBDDTest {

    private ItemRepository itemRepository;

    private ItemServiceImpl itemServiceImpl;

    @BeforeEach
    public void setup() {
        itemRepository = Mockito.mock(ItemRepository.class);
        itemServiceImpl = new ItemServiceImpl(itemRepository);
    }

    @DisplayName("Junit test to saveItem() method")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject() {
        //given - precondition or setup
        Item item = new Item(1L,"harry potter by JK Rowling", 20, 400);
        BDDMockito.given(itemRepository.save(item)).willReturn(item);

        //when - action or the behaviour that we are going test
        Item savedItem = itemRepository.save(item);

        //then - verify the output
        Assertions.assertThat(savedItem).isNotNull();
        Assertions.assertThat(savedItem.getName()).isEqualToIgnoringCase("harry potter by JK Rowling");

        BDDMockito.then(itemRepository).should().save(item);
    }

}
