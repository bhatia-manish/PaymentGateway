package com.paymentgateway.demo.service;

import com.paymentgateway.demo.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> get();

    Product get(int id);

    Product save(Product employee);

    void delete(int id);
}
