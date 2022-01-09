package com.osintsev.onlinekeyboard.service;


import com.osintsev.onlinekeyboard.model.Category;
import com.osintsev.onlinekeyboard.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    //get all
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
    //get by id
    public Optional<Category> getCategoryById(int id){
        return categoryRepository.findById(id);
    }

    //adding
    public void addCategory(Category category){
        categoryRepository.save(category);
    }
    // deleting
    public void removeCategoryById(int id){
        categoryRepository.deleteById(id);
    }


}
