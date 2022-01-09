package com.osintsev.onlinekeyboard.service;


import com.osintsev.onlinekeyboard.model.Product;
import com.osintsev.onlinekeyboard.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    //get all
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    //get by id
    public Optional<Product> getProductById(long id){
        return productRepository.findById(id);
    }
    // get all by category

    public List<Product> getAllProductsByCategoryId(int id){
        return productRepository.findAllByCategory_Id(id);
   }

    //adding
    public void addProduct(Product product){
        productRepository.save(product);
    }
    // deleting
    public void removeProductById(long id){
        productRepository.deleteById(id);
    }



}