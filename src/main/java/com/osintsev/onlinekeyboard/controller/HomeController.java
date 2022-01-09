package com.osintsev.onlinekeyboard.controller;

import com.osintsev.onlinekeyboard.model.Product;
import com.osintsev.onlinekeyboard.model.Role;
import com.osintsev.onlinekeyboard.model.User;
import com.osintsev.onlinekeyboard.service.CartService;
import com.osintsev.onlinekeyboard.service.CategoryService;
import com.osintsev.onlinekeyboard.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    CartService cartService;

    @GetMapping({"/","/home"})
    public String home(@AuthenticationPrincipal User user,Model model){
     if (user != null) {
         List<Role> list = user.getRoles();
         for (int i =0;i<list.size();i++){
             if (list.get(i).getName().equals("ROLE_ADMIN")){
                 model.addAttribute("user",list.get(i).getName());
             }
         }

     }
     List<Product> productList = productService.getAllProducts();
     model.addAttribute("products",productList);
        return "index";
    }
    @GetMapping("/shop")
    public String shop(Model model){
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("products",productService.getAllProducts());

        return "shop";
    }
    @GetMapping("/shop/category/{id}")
    public String shopByCategory(@PathVariable int id, Model model){
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("products",productService.getAllProductsByCategoryId(id));

        return "shop";
    }
    @GetMapping("/shop/viewproduct/{id}")
    public String viewProductById(@PathVariable int id, Model model){
        model.addAttribute("product",productService.getProductById(id).get());

        return "viewProduct";
    }
}
