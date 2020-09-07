package com.internet.shop.service.impl;

import com.internet.shop.dao.ShoppingCartDao;
import com.internet.shop.lib.Inject;
import com.internet.shop.lib.Service;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return shoppingCartDao.create(shoppingCart);
    }

    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        ShoppingCart dbShoppingCart = shoppingCartDao.get(shoppingCart.getId()).get();
        dbShoppingCart.getProducts().add(product);
        return shoppingCartDao.update(dbShoppingCart);
    }

    @Override
    public ShoppingCart deleteProduct(ShoppingCart shoppingCart, Product product) {
        ShoppingCart dbShoppingCart = shoppingCartDao.get(shoppingCart.getId()).get();
        dbShoppingCart.getProducts().remove(product);
        return shoppingCartDao.update(dbShoppingCart);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        ShoppingCart dbShoppingCart = shoppingCartDao.get(shoppingCart.getId()).get();
        dbShoppingCart.getProducts().clear();
        shoppingCartDao.update(dbShoppingCart);
    }

    @Override
    public ShoppingCart getByUserId(Long userId) {
        return shoppingCartDao.getByUserId(userId).get();
    }

    @Override
    public boolean delete(ShoppingCart shoppingCart) {
        return shoppingCartDao.delete(shoppingCart.getId());
    }
}
