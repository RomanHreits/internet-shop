package com.internet.shop.db;

import com.internet.shop.model.Product;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static final List<Product> products = new ArrayList<>();
    private static Long idGenerator = 0L;

    public static Product addProduct(Product product) {
        product.setId(++idGenerator);
        products.add(product);
        return product;
    }
}
