package com.paymentgateway.demo.dao;

import com.paymentgateway.demo.model.Product;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private EntityManager entityManager;


    @Override
    public List<Product> get() {
        Session currSession = entityManager.unwrap(Session.class);
        Query<Product> query = currSession.createQuery("FROM Product", Product.class);
        return query.getResultList();

    }

    @Override
    public Product get(int id) {
        return null;
    }

    @Override
    public Product save(Product employee) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
