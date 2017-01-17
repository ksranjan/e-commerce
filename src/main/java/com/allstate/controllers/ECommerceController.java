package com.allstate.controllers;


import com.allstate.entities.Product;
import com.allstate.services.ECommerceService;
import com.fasterxml.jackson.databind.ser.std.IterableSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value ="/products")
public class ECommerceController {

    private ECommerceService service;

    @Autowired
    public void setService(ECommerceService service) {
        this.service = service;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Product create (@RequestBody Product p){
        return this.service.create(p);
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public Iterable<Product> findAll(){
        return this.service.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product findOne(@PathVariable int id){
        return this.service.findOneById(id);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Product findByName(@RequestParam Map<String, String> query){
        if(query.keySet().toArray()[0].toString().equals("name")) {
            return this.service.findOneByName(query.get("name"));
        } else{
            return this.service.findOneByStocknum(query.get("stocknum"));
        }
    }

}
