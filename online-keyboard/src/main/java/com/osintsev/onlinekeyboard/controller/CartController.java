package com.osintsev.onlinekeyboard.controller;


import com.osintsev.onlinekeyboard.model.Cart;
import com.osintsev.onlinekeyboard.model.Category;
import com.osintsev.onlinekeyboard.model.Product;
import com.osintsev.onlinekeyboard.model.User;
import com.osintsev.onlinekeyboard.repository.CartRepository;
import com.osintsev.onlinekeyboard.repository.ProductRepository;
import com.osintsev.onlinekeyboard.repository.UserRepository;
import com.osintsev.onlinekeyboard.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CartRepository cartRepository;



    @GetMapping("/addToCart/{id}")
    public String addToCart(@AuthenticationPrincipal User user,@PathVariable("id") Product product){
        Optional<User> userToSave =  userRepository.findUserByEmail(user.getEmail());

        List<Cart> cartList = cartRepository.findAll();
     for (int i = 0; i < cartList.size(); i++){
         if (cartList.get(i).getProduct() == product && cartList.get(i).getUser() == userToSave.get()){
             return "redirect:/shop";
         }
     }


            Cart cart = new Cart();
            cart.setProduct(product);

            cart.setUser(userToSave.get());
            cartRepository.save(cart);

            return "redirect:/shop";

    }

    @GetMapping("/cart")
    public String cartGet(@AuthenticationPrincipal User user,Model model){

      //  model.addAttribute("cartCount",GlobalData.cart.size());\
        //model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        Optional<User> userForId = userRepository.findUserByEmail(user.getEmail());
        int userId =  userForId.get().getId();
        model.addAttribute("cart",cartService.getCartProductsById(userId));
        List<Product> productList = cartService.getCartProductsById(userId);
        double total = 0;
        for (int i = 0;i < productList.size();i++){
            total += productList.get(i).getPrice();
        }
        model.addAttribute("total",total);

        return "cart";
    }
    @GetMapping("/cart/removeItem/{id}")
    //TODO the item doesn't have an id, so it just writes 0 instead of id
    public String deleteFromCart(@AuthenticationPrincipal User user,@PathVariable long id){

        cartRepository.getByUser_EmailAndProduct_Id(user.getEmail(),id);
        return "cart";
    }

}
