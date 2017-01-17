package com.allstate.repositories;

import com.allstate.entities.Product;
import org.springframework.data.repository.CrudRepository;


public interface IProductRepository extends CrudRepository<Product, Integer> {
    public Product findByName(String name);
    public Product findByStocknum(String stocknum);

}
