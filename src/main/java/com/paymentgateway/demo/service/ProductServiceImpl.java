package com.paymentgateway.demo.service;

import com.paymentgateway.demo.dao.ProductDAO;
import com.paymentgateway.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;

    @Transactional
    @Override
    public List<Product> get() {
        return productDAO.get();
    }

    @Transactional
    @Override
    public Product get(int id) {
        return null;
    }

    @Transactional
    @Override
    public Product save(Product employee) {
        return null;
    }

    @Transactional
    @Override
    public void delete(int id) {

    }
}
