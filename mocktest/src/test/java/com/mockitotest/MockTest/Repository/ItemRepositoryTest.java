package com.mockitotest.MockTest.Repository;

import com.mockitotest.MockTest.Entity.Item;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//@DataJpaTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @BeforeEach
    public void setUp() {
        //Initialize the repository with the 2 test items
        //given - precondition or setup
        Item item1 = new Item("java by E Balagurusamy",75, 650.0);
        Item item2 = new Item("C language by E Balagurusamy",45, 550.0);
        Item createdItem1 = itemRepository.save(item1);
        Item createdItem2 = itemRepository.save(item2);
//        assertThat(createdItem1).isNotNull();
//        assertThat(createdItem2).isNotNull();
    }

//    @Test
    public void testFindByNameIgnoreCase() {
        //when - action or the behaviour that we are testing
        Item itemResult = itemRepository.findByNameIgnoreCase("java by E Balagurusamy");
        //then - verify the output
        assertThat(itemResult).isNotNull();
    }
}
