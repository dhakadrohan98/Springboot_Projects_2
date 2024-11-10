package com.mockitotest.MockTest.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mockitotest.MockTest.Entity.Item;
import com.mockitotest.MockTest.Service.ItemServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.mockito.ArgumentMatchers;
import static org.mockito.BDDMockito.*;


import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemServiceImpl itemService;

    @Autowired
    private ObjectMapper objectMapper;
    //used to convert object into json

    private Item item;
    private Item item1;
    private Item item2;

    @BeforeEach
    public void setUp() {
        item = new Item("Harry potter and Chamber of Secrets", 50, 700);
        item1 = new Item("Harry potter and the Philosopher's Stone", 30, 300);
        item2 = new Item(20L,"Harry potter and the Goblet of fire", 30, 500);
    }

    @Test
    public void testCreateItem() throws Exception {
        //given precondition or setup
        given(itemService.saveItem(ArgumentMatchers.any(Item.class)))
                .willAnswer(invocation -> invocation.getArgument(0));
        //when - action or behaviour that we are going to test
        // Convert Java object to JSON string
        String itemJsonString = objectMapper.writeValueAsString(item);

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
                        CoreMatchers.is(item.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity",
                        CoreMatchers.is(item.getQuantity())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price",
                        CoreMatchers.is(item.getPrice())));
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        //given - precondition or setup
        List<Item> listOfItems = new ArrayList<>();
        listOfItems.add(item);
        listOfItems.add(item1);
        //stubbing method call
        given(itemService.getAllItems()).willReturn(listOfItems);

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
        long itemId = 20L;
        given(itemService.getItemById(itemId)).willReturn(item2);

        //when - action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(get("/api/items/{id}", itemId));

        //then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id",
                        CoreMatchers.is(20)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",
                        CoreMatchers.is(item2.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price",
                        CoreMatchers.is(item2.getPrice())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity",
                        CoreMatchers.is(item2.getQuantity())));
    }
}