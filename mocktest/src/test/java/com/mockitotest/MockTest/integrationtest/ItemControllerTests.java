package com.mockitotest.MockTest.integrationtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mockitotest.MockTest.Entity.Item;
import com.mockitotest.MockTest.Repository.ItemRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class ItemControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Environment environment;

    private static Item item;
    private static Item item1;
    private static Item item2;

    static {
        item = new Item("Harry potter and Chamber of Secrets", 50, 700);
        item1 = new Item("Harry Potter and the Order of the Phoenix", 30, 300);
        item2 = new Item("Harry potter and the Goblet of fire", 30, 500);
    }

//    @BeforeEach
//    public void setup() throws Exception {
//        //given precondition or setup
//        item = new Item("Harry potter and Chamber of Secrets", 50, 700);
//        item1 = new Item("Harry Potter and the Order of the Phoenix", 30, 300);
//        item2 = new Item("Harry potter and the Goblet of fire", 30, 500);
//        testCreateItem();
////        itemRepository.deleteAll();
//    }

    @BeforeEach
    public void printBaseURIAndContextPath() {
       String port = environment.getProperty("server.port");
       String contextPath = environment.getProperty("server.servlet.context-path");
//       String baseUri =
    }

    @Test
    @Order(1)
    public void testCreateItem() throws Exception {
        //when - action or behaviour that we are going to test
        // Convert Java object to JSON string
        String itemJsonString1 = objectMapper.writeValueAsString(item);
        String itemJsonString2 = objectMapper.writeValueAsString(item1);
        String itemJsonString3 = objectMapper.writeValueAsString(item2);

        ResultActions response1 = mockMvc.perform(post("/api/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(itemJsonString1)
        );
        ResultActions response2 = mockMvc.perform(post("/api/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(itemJsonString2)
        );
        ResultActions response3 = mockMvc.perform(post("/api/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(itemJsonString3)
        );
        //then - verify the result or output using assert statements
        //verify the status of rest API's response
        //now verify the response of rest API contain a valid Json values
        //verify the actual json values with the expected json values
        response1.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",
                        CoreMatchers.is(item.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity",
                        CoreMatchers.is(item.getQuantity())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price",
                        CoreMatchers.is(item.getPrice())));

        response2.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",
                        CoreMatchers.is(item1.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity",
                        CoreMatchers.is(item1.getQuantity())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price",
                        CoreMatchers.is(item1.getPrice())));

        response3.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",
                        CoreMatchers.is(item2.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity",
                        CoreMatchers.is(item2.getQuantity())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price",
                        CoreMatchers.is(item2.getPrice())));
    }

    @Test
    @Order(2)
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
    @Order(3)
    public void testGetItemById() throws Exception {
        //given - precondition or setup
        long itemId = 5L;

        //when - action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(get("/api/item/{id}", itemId));

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
    @Order(4)
    public void testGetItemByName() throws Exception {
        //given precondition or setup
        String name = "Harry potter and Chamber of Secrets";

        //when - action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(get("/api/items/search")
                .param("name", name)
                .contentType(MediaType.APPLICATION_JSON));

        //then - verify the outputa
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",
                        CoreMatchers.is(name)));

    }


}
