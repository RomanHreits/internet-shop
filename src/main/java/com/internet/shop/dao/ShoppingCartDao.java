package com.internet.shop.dao;

import com.internet.shop.model.ShoppingCart;
import java.util.Optional;

public interface ShoppingCartDao {

    ShoppingCart create(ShoppingCart shoppingCart);

    ShoppingCart update(ShoppingCart shoppingCart);

    boolean delete(Long id);

    Optional<ShoppingCart> get(Long id);

    Optional<ShoppingCart> getByUserId(Long userId);
}
