package com.osintsev.onlinekeyboard.repository;

import com.osintsev.onlinekeyboard.model.Cart;
import com.osintsev.onlinekeyboard.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Integer> {


    List<Cart>findAllByUser_Id(int id);
    Cart getByUser_EmailAndProduct_Id(String email,long id);


}
