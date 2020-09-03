package com.internet.shop;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.service.impl.ProductService;

public class Application {
    private static final Injector injector = Injector.getInstance("com.internet.shop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        for (int i = 0; i < 10; i++) {
            String name = "banana" + i;
            double price = 0.0 + i;
            productService.create(new Product(name, price));
        }
        System.out.println(productService.getAll().toString());
        System.out.println("----><----");
        for (int i = 0; i < 10; i++) {
            String name = "banana" + i * i;
            double price = 0.0 + i * i;
            Product product = new Product(name, price);
            product.setId(i + 1L);
            productService.update(product);
        }

        System.out.println(productService.getAll().toString());
        System.out.println("----><----");
        for (int i = 0; i < 10; i++) {
            productService.delete(i + 1L);
        }
        System.out.println(productService.getAll().toString());
    }
}
