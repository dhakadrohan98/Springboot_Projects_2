package com.mockitotest.MockTest.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mockitotest.MockTest.Entity.Item;
import com.mockitotest.MockTest.Service.ItemServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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

    @BeforeEach
    public void setUp() {
        item = new Item("harry potter by JK Rowling", 20, 400);
    }

    @Test
    public void testCreateItem() throws Exception {
        //given precondition or setup
        BDDMockito.given(itemService.saveItem(ArgumentMatchers.any(Item.class)))
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
        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",
                        CoreMatchers.is(item.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity",
                        CoreMatchers.is(item.getQuantity())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price",
                        CoreMatchers.is(item.getPrice())));

    }
}