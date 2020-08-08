package com.paymentgateway.demo.controller;


import com.paymentgateway.demo.model.Product;
import com.paymentgateway.demo.service.ProductService;
import com.paymentgateway.demo.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Constants.PRODUCTS_API_ENDPOINT)
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.get();
    }
}
