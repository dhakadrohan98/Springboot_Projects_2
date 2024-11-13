package com.mockitotest.MockTest.integrationtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mockitotest.MockTest.Entity.Item;
import com.mockitotest.MockTest.Repository.ItemRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ItemControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Item item;
    private Item item1;
    private Item item2;

    @BeforeEach
    void setup() {
        //given precondition or setup
        item = new Item(5L, "Harry potter and Chamber of Secrets", 50, 700);
        item1 = new Item("Harry Potter and the Order of the Phoenix", 30, 300);
        item2 = new Item("Harry potter and the Goblet of fire", 30, 500);
//        itemRepository.deleteAll();
    }

    @Test
    public void testCreateItem() throws Exception {
        //when - action or behaviour that we are going to test
        // Convert Java object to JSON string
        String itemJsonString = objectMapper.writeValueAsString(item2);

        ResultActions response = mockMvc.perform(post("/api/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(itemJsonString)
        );
        //then - verify the result or output using assert statements
        //verify the status of rest API's response
        //now verify the response of rest API contain a valid Json values
        //verify the actual json values with the expected json values
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",
                        CoreMatchers.is(item2.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity",
                        CoreMatchers.is(item2.getQuantity())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price",
                        CoreMatchers.is(item2.getPrice())));
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        //given - precondition or setup
        List<Item> listOfItems = new ArrayList<>();
        listOfItems.add(item);
        listOfItems.add(item1);
        listOfItems.add(item2);

        //when - action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(get("/api/items"));

        //then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()",
                        CoreMatchers.is(listOfItems.size())));
    }

    //positive scenarios - valid employee id
    @Test
    public void testGetItemById() throws Exception {
        //given - precondition or setup
        long itemId = 5L;

        //when - action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(get("/api/items/{id}", itemId));

        //then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id",
                        CoreMatchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",
                        CoreMatchers.is(item.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price",
                        CoreMatchers.is(item.getPrice())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity",
                        CoreMatchers.is(item.getQuantity())));
    }

    //search employee by name
    @Test
    @DisplayName("testing search employee by name")
    public void testGetItemByName() throws Exception {
        //given precondition or setup
        String name = "Harry potter and Chamber of Secrets";

        //when - action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(get("/api/items/search")
                .param("name", name)
                .contentType(MediaType.APPLICATION_JSON));

        //then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",
                        CoreMatchers.is(item.getName())));

    }
}
