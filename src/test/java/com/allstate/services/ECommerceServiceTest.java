package com.allstate.services;

import com.allstate.entities.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = {"/sql/seed.sql"})
public class ECommerceServiceTest {

        @Autowired
        private ECommerceService service;


        @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldCreateAProduct() throws Exception{
        Product p = new Product();
        p.setId(2);
        p.setName("Apple");
        p.setStocknum("A123SL");
        p.setListprice(8);
        Product res = this.service.create(p);
        assertEquals("Apple",res.getName());
        assertEquals("A123SL",res.getStocknum());
        assertEquals(2,res.getId());

    }

    @Test(expected = DataIntegrityViolationException.class)
    public void shouldNotCreateProductNameDuplicates() throws Exception{
        Product p = new Product();
        p.setId(2);
        p.setName("tata-salt");
        p.setStocknum("tata1");
        Product res = this.service.create(p);
    }


    @Test
    public void shouldFindAProduct() throws Exception{
        Product res = this.service.findOneById(1);
        assertEquals("tata-salt",res.getName());
        assertEquals("tata1",res.getStocknum());
        assertEquals(1,res.getId());
    }

    @Test
    public void shouldFindAProductByName() throws Exception{
        Product res = this.service.findOneByName("tata-salt");
        assertEquals("tata-salt",res.getName());
        assertEquals("tata1",res.getStocknum());
        assertEquals(1,res.getId());
    }

    @Test
    public void shouldFindAProductByStockNumber() throws Exception{
        Product res = this.service.findOneByStocknum("tata1");
        assertEquals("tata-salt",res.getName());
        assertEquals("tata1",res.getStocknum());
        assertEquals(1,res.getId());
    }

    @Test
    public void shouldFindAllProduct() throws Exception{
        List<Product> reslist = (ArrayList<Product>)this.service.findAll();
        assertEquals("tata-salt",reslist.get(0).getName());
        assertEquals("tata1",reslist.get(0).getStocknum());
        assertEquals(1, reslist.get(0).getId());
    }

    @Test
    public void shouldDeleteProductById() throws Exception{
        this.service.deleteOne(1);
        List<Product> reslist = (ArrayList<Product>)this.service.findAll();
        assertEquals(0, reslist.size());
    }

    @Test
    public void shouldUpdateProduct() throws Exception{
        Product p = new Product();
        p.setName("Apple");
        p.setStocknum("A123SL");
        p.setListprice(8);
        Product res = this.service.update(1, p);
        assertEquals("Apple",res.getName());
        assertEquals("A123SL",res.getStocknum());
        assertEquals(1,res.getId());
    }

    @Test
    public void shouldGetAvgOfRatings() throws Exception{
        Product p = new Product();
        p.setName("Apple");
        p.setStocknum("A123SL");
        p.setListprice(8);
        p.setRating(4);
        this.service.create(p);
        assertEquals(3, this.service.getAvgRating());
    }

    @Test
    public void shouldGetTotalNumOfReviews() throws Exception{
        Product p = new Product();
        p.setName("Apple");
        p.setStocknum("A123SL");
        p.setListprice(8);
        p.setRating(4);
        p.setReviews(12);
        this.service.create(p);
        assertEquals(14, this.service.getTotalReviews());
    }

    @Test
    public void shouldGetTotalProducts() throws Exception{
        Product p = new Product();
        p.setName("Apple");
        p.setStocknum("A123SL");
        p.setListprice(8);
        p.setRating(4);
        p.setReviews(12);
        p.setQuantity(10);
        this.service.create(p);
        assertEquals(15,this.service.getTotalProducts());
    }

    @Test
    public void shouldFindRestricted() throws Exception{
        Product p = new Product();
        p.setName("Apple");
        p.setStocknum("A123SL");
        p.setRestricted(true);
        this.service.create(p);
        assertEquals(1, this.service.getAllRestricted().size());
    }


}