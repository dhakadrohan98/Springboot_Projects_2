package com.mockitotest.MockTest.Repository;

import com.mockitotest.MockTest.Entity.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    private Item item1;
    private Item item2;
    private Item item3;
    private Item createdItem1;
    private Item createdItem2;
    private Item createdItem3;


    @BeforeEach
    public void setUp() {
        //Save 3 test items in item repository
        //given - precondition or setup
        item1 = new Item("java by E Balagurusamy",75, 650.0);
        item2 = new Item("C language by E Balagurusamy",45, 550.0);
        item3 = new Item("Harry Potter and the Sorcerer’s Stone", 100,550);
        createdItem1 = itemRepository.save(item1);
        createdItem2 = itemRepository.save(item2);
        createdItem3 = itemRepository.save(item3);
    }

    @DisplayName("Save employee")
    @Test
    public void testSaveItem() {
        //given precondition or setup
         Item item = new Item(" Harry Potter and the Sorcerer’s Stone", 100, 550);

         //when - action or the behaviour that we are testing
        Item savedItem = itemRepository.save(item);

        //then - verify the output
        assertThat(savedItem).isNotNull();
        assertThat(savedItem.getId()).isGreaterThan(0);
    }

    @DisplayName("Get all employee")
    @Test
    public void testFindAllItem() {
        //given precondition or setup
        List<Item> list = new ArrayList<>();
        list.add(createdItem1);
        list.add(createdItem2);
        list.add(createdItem3);
        //when - action or the behaviour that we are testing
        List<Item> resultList = itemRepository.findAll();
        //then - verify the output
        assertThat(resultList).isNotNull();
        assertThat(resultList.size()).isEqualTo(list.size());
    }

    @DisplayName("Search Item by id")
    @Test
    public void testFindItemById() {
        //given precondition or setup
        long id = 3l;
        //when - action or the behaviour that we are testing
        Item returnedItemById = itemRepository.findById(id).get();
        //then - verify the output
        assertThat(returnedItemById).isNotNull();
        assertThat(returnedItemById.getId()).isEqualTo(id);
        assertThat(returnedItemById.getName()).isEqualTo("Harry Potter and the Sorcerer’s Stone");
    }

    @DisplayName("Find employee by name")
    @Test
    public void testFindByNameIgnoreCase() {
        //when - action or the behaviour that we are testing
        Item itemResult = itemRepository.findByNameIgnoreCase("java by E Balagurusamy");
        //then - verify the output
        assertThat(itemResult).isNotNull();
        assertThat(itemResult.getName()).isEqualTo(createdItem1.getName());
    }

    @DisplayName("Delete item by id")
    @Test
    public void testDeleteItem() {
        long id = 3l;
        itemRepository.deleteById(id);
        Optional<Item> deletedItem = itemRepository.findById(id);
        assertThat(deletedItem.isPresent()).isEqualTo(false);
    }
}
