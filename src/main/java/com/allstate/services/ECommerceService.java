package com.allstate.services;

import com.allstate.entities.Product;
import com.allstate.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ECommerceService {

    private IProductRepository repository;

    @Autowired
    public void setRepository(IProductRepository repository) {
        this.repository = repository;
    }

    public Product create (Product p){
        return this.repository.save(p);
    }

    public Product findOneById (int id){
        return this.repository.findOne(id);
    }
    public Iterable<Product> findAll (){
        return this.repository.findAll();
    }

    public Product findOneByName (String name) {
        return this.repository.findByName(name);
    }


    public Product findOneByStocknum (String stocknum) {
        return this.repository.findByStocknum(stocknum);
    }

    public void deleteOne(int id) {
        this.repository.delete(id);
    }

    public Product update(int id, Product p) {
        Product pOld = this.repository.findOne(id);
        pOld.setName(p.getName());
        pOld.setListprice(p.getListprice());
        pOld.setStocknum(p.getStocknum());
        return this.repository.save(pOld);
    }

    public int getAvgRating() {
        List<Product> prodList= (ArrayList<Product>)this.repository.findAll();
        int temp = 0;
        for(Product p : prodList){
            temp += p.getRating();
        }
        return temp/prodList.size();
    }

    public int getTotalReviews(){
        List<Product> prodList= (ArrayList<Product>)this.repository.findAll();
        int temp = 0;
        for(Product p : prodList){
            temp += p.getReviews();
        }
        return temp;
    }

    public int getTotalProducts(){
        List<Product> prodList= (ArrayList<Product>)this.repository.findAll();
        int temp = 0;
        for(Product p : prodList){
            temp += p.getQuantity();
        }
        return temp;
    }

    public ArrayList<Product> getAllRestricted(){
        List<Product> prodList= (ArrayList<Product>)this.repository.findAll();
        ArrayList<Product> resList=  new ArrayList() ;
        for(Product p : prodList){
           if(p.isRestricted()){
            resList.add(p);
           }
        }
        return resList;
    }
}
