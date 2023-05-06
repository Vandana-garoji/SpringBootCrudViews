package com.vandyStore.controller;

import com.vandyStore.model.Product;
import com.vandyStore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;



    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "list";
    }


    @GetMapping("/{productId}")
    public String viewProduct(@PathVariable int productId, Model model) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));
        model.addAttribute("product", product);
        return "view";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "new";
    }

    @PostMapping
    public String createProduct(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/{productId}/edit")
    public String editProduct(@PathVariable int productId, Model model) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));
        model.addAttribute("product", product);
        return "edit";
    }

    @PostMapping("/{productId}")
    public String updateProduct(@PathVariable int productId, @ModelAttribute Product product) {
        product.setProductId(productId);
        productRepository.save(product);
        return "redirect:/products";
    }

    @PostMapping("/{productId}/delete")
    public String deleteProduct(@PathVariable int productId) {
        productRepository.deleteById(productId);
        return "redirect:/products";
    }



}

