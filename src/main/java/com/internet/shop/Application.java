package com.internet.shop;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.service.ProductService;

public class Application {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private static ProductService productService = (ProductService) injector
            .getInstance(ProductService.class);
    private static final Product keyboard = new Product("Keyboard", 1200);
    private static final Product headphone = new Product("headphone", 2000);
    private static final Product laptop = new Product("laptop", 25400);

    public static void main(String[] args) {
        productService.create(keyboard);
        productService.create(headphone);
        productService.create(laptop);

        System.out.println(productService.get(1L).toString());
        System.out.println(productService.getAll().toString());
        productService.update(new Product(1L, "updateProduct", 111));
        System.out.println(productService.get(1L).toString());
        productService.deleteById(1L);
        System.out.println(productService.getAll().toString());
    }
}
