package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.ShoppingCartDao;
import com.internet.shop.exceptions.DataProcessingException;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class ShoppingCartJdbcImpl implements ShoppingCartDao {

    @Override
    public Optional<ShoppingCart> getByUserId(Long userId) {
        ShoppingCart cart = null;
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection
                         .prepareStatement("SELECT * FROM shopping_carts "
                             + "WHERE user_id = ? AND is_deleted = FALSE ")) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cart = new ShoppingCart(resultSet.getLong("cart_id"),
                        resultSet.getLong("user_id"));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get shopping_cart with userId: " + userId, e);
        }
        cart.setProducts(getShoppingCartProducts(cart.getId()));
        return Optional.of(cart);
    }

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement("INSERT INTO shopping_carts (user_id) VALUES(?)",
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, shoppingCart.getUserId());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                shoppingCart.setId(generatedKeys.getLong(1));
            }
            for (Product product : shoppingCart.getProducts()) {
                addDataToShoppingCartProductsTable(shoppingCart.getId(), product.getId());
            }
            return shoppingCart;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create ShoppingCart with id: "
                    + shoppingCart.getId(), e);
        }
    }

    @Override
    public ShoppingCart update(ShoppingCart cart) {
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection
                         .prepareStatement("DELETE FROM shopping_cart_products "
                             + "WHERE cart_id = ?")) {
            statement.setLong(1, cart.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update ShoppingCart with id: "
                    + cart.getId(), e);
        }
        return addProductsToShoppingCart(cart);
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        ShoppingCart cart = null;
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * "
                        + "FROM shopping_carts WHERE cart_id = ? AND is_deleted = FALSE")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cart = new ShoppingCart(resultSet.getLong("cart_id"),
                        resultSet.getLong("user_id"));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get shopping_cart with id: " + id, e);
        }
        if (cart != null) {
            cart.setProducts(getShoppingCartProducts(cart.getId()));
            return Optional.of(cart);
        }
        throw new DataProcessingException("Can't get shopping_cart with id: " + id);
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE shopping_carts SET is_deleted = TRUE WHERE cart_id = ?";
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement(query)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add products to shoppingCart", e);
        }
    }

    @Override
    public List<ShoppingCart> getAll() {
        List<ShoppingCart> carts = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement("SELECT * "
                         + "FROM shopping_carts WHERE is_deleted = FALSE")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ShoppingCart cart = new ShoppingCart(resultSet.getLong("cart_id"),
                        resultSet.getLong("user_id"));
                carts.add(cart);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get shopping_carts", e);
        }
        for (ShoppingCart cart : carts) {
            cart.setProducts(getShoppingCartProducts(cart.getId()));
        }
        return carts;
    }

    private void addDataToShoppingCartProductsTable(long cartId, long productId) {
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("INSERT INTO shopping_cart_products "
                             + "(cart_id, product_id) VALUES(?,?)")) {
            statement.setLong(1, cartId);
            statement.setLong(2, productId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add data to "
                    + "shopping_cart_products table", e);
        }
    }

    private List<Product> getShoppingCartProducts(long cartId) {
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement("SELECT * FROM products p JOIN "
                        + "shopping_cart_products scp ON scp.product_id = p.product_id "
                        + "WHERE cart_id = ? AND is_deleted = FALSE")) {
            statement.setLong(1, cartId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(new Product(resultSet.getLong("product_id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price")));
            }
            return products;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get ShoppingCart Products!!!", e);
        }
    }

    private ShoppingCart addProductsToShoppingCart(ShoppingCart cart) {
        String query = "INSERT INTO shopping_cart_products (cart_id, product_id) VALUES(?,?)";
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement(query)) {
            for (Product product : cart.getProducts()) {
                statement.setLong(1, cart.getId());
                statement.setLong(2, product.getId());
                statement.executeUpdate();
            }
            return cart;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add products to shoppingCart", e);
        }
    }
}
