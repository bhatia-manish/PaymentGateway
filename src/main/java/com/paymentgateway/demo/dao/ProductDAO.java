package com.paymentgateway.demo.dao;

import com.paymentgateway.demo.model.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> get();

    Product get(int id);

    Product save(Product employee);

    void delete(int id);
}
