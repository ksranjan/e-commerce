package com.allstate.controllers;


import com.allstate.entities.Product;
import com.allstate.services.ECommerceService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ECommerceController.class)
public class ECommerceControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ECommerceService service;

    private Product product;


    @Before
    public void setUp() throws Exception {
        product = new Product();
        product.setId(2);
        product.setName("Apple");
        product.setStocknum("A123SL");
        product.setListprice(8);
    }

    @After
    public void tearDown() throws Exception {

    }
    

    @Test
    public void shouldCreateProduct() throws Exception {
        given(this.service.create(Mockito.any(Product.class)))
                .willReturn(product);

        MockHttpServletRequestBuilder request = post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Apple\"}");

        this.mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.name", is("Apple")));
    }
    @Test
    public void shouldFindProductById() throws Exception {
        given(this.service.findOneById(1))
                .willReturn(product);

        MockHttpServletRequestBuilder request = get("/products/1");

        this.mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.name", is("Apple")));
    }

}