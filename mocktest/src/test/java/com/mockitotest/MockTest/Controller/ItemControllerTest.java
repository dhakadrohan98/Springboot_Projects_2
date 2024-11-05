package com.mockitotest.MockTest.Controller;

import com.mockitotest.MockTest.Entity.Item;
import com.mockitotest.MockTest.Service.ItemService;
import jakarta.servlet.ServletContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcWebClientAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    //Trying to mokc the service layer to test the Controller class
//    @Test
//    public void testCreateItem() throws Exception {
//        Item item = new Item();
//        item.setName("Test Item");
//        item.setPrice(10.0);
//        item.setQuantity(3);
//
//        when(itemService.saveItem(any(Item.class))).thenReturn(item);
//        mockMvc.perform(post("/api/items")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"name\": \"Test Item\", \"price\": \"10.0\", \"quantity\": \"100\"}")
//                .andExpect(status().isOk()))
//                .andExpect(jsonPath("$.name").value("Test item"));
//
//        verify(itemService, times(1)).createItem(any(Item.class));
//    }

//    public RequestBuilder buildCreateItemRequest(Item item) {
//        return post("/api/items").co
//    }
}