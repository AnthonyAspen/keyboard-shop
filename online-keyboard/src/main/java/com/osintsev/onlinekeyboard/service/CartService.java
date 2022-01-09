package com.osintsev.onlinekeyboard.service;


import com.osintsev.onlinekeyboard.model.Cart;
import com.osintsev.onlinekeyboard.model.Product;
import com.osintsev.onlinekeyboard.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    // get user cart
    public List<Product> getCartProductsById(int id){
        List<Cart> cart = cartRepository.findAllByUser_Id(id);
        List<Product> productList = new ArrayList<>();
        for (int i = 0;i<cart.size();i++){
            productList.add(cart.get(i).getProduct());
        }
        return productList;
    }

    //add in cart
    public void addInCart(Cart cart){
        cartRepository.save(cart);
    }

    // delete from cart

    public void deleteFromCart(int id){cartRepository.deleteById(id);}


}
