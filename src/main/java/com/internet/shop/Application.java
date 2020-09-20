package com.internet.shop;

import com.internet.shop.dao.ProductDao;
import com.internet.shop.dao.jdbc.ProductDaoJdbcImpl;
import com.internet.shop.model.Product;

public class Application {
    private static final Product keyboard = new Product("Keyboard", 1200);
    private static final Product headphone = new Product("headphone", 2000);
    private static final Product laptop = new Product("laptop", 25400);

    public static void main(String[] args) {
        ProductDao productDao = new ProductDaoJdbcImpl();
        productDao.create(keyboard);
        productDao.create(headphone);
        productDao.create(laptop);

        System.out.println(productDao.get(1L).toString());
        System.out.println(productDao.getAll().toString());
        productDao.update(new Product(1L, "updateProduct", 111));
        System.out.println(productDao.get(1L).toString());
        productDao.delete(1L);
        System.out.println(productDao.getAll().toString());
    }
}
