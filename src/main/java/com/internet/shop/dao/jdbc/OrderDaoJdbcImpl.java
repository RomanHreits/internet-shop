package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.OrderDao;
import com.internet.shop.exceptions.DataProcessingException;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.Order;
import com.internet.shop.model.Product;
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
public class OrderDaoJdbcImpl implements OrderDao {

    @Override
    public List<Order> getUsersOrders(Long userId) {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement =
                        connection.prepareStatement("SELECT * "
                             + "FROM orders WHERE user_id = ? AND is_deleted = FALSE")) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order(resultSet.getLong("order_id"),
                        resultSet.getLong("user_id"));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add data to Orders_Product", e);
        }
        setProductsToOrders(orders);
        return orders;
    }

    @Override
    public Order create(Order order) {
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement("INSERT INTO orders (user_id) VALUES(?)",
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, order.getUserId());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                order.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create Order with id: " + order.getId(), e);
        }
        for (Product product : order.getProducts()) {
            addDataToOrdersProductTable(order.getId(), product.getId());
        }
        return order;
    }

    @Override
    public Order update(Order item) {
        return item;
    }

    @Override
    public Optional<Order> get(Long id) {
        Order order = null;
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement("SELECT order_id, user_id FROM orders "
                        + "WHERE order_id = ? AND is_deleted = FALSE ")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                order = new Order(resultSet.getLong("order_id"),
                        resultSet.getLong("user_id"));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get Order with id: " + id, e);
        }
        order.setProducts(getOrdersProducts(order.getId()));
        return Optional.of(order);
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement("UPDATE orders "
                         + "SET is_deleted = TRUE WHERE order_id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete Order with id: " + id, e);
        }
        return deleteOrderProducts(id);
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement("SELECT * FROM orders WHERE is_deleted = FALSE")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(new Order(resultSet.getLong("order_id"),
                        resultSet.getLong("user_id")));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add data to Orders_Product", e);
        }
        setProductsToOrders(orders);
        return orders;
    }

    private void addDataToOrdersProductTable(long orderId, long productId) {
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("INSERT INTO "
                                + "orders_products (order_id, product_id) VALUES(?,?)")) {
            statement.setLong(1, orderId);
            statement.setLong(2, productId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add data to Orders_Product", e);
        }
    }

    private List<Product> getOrdersProducts(long orderId) {
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement("SELECT * FROM products p JOIN "
                        + "orders_products op ON op.product_id = p.product_id "
                        + "WHERE order_id = ? AND is_deleted = FALSE")) {
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(new Product(resultSet.getLong("product_id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price")));
            }
            return products;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get Order Products!!!", e);
        }
    }

    public boolean deleteOrderProducts(Long id) {
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection
                         .prepareStatement("DELETE FROM orders_products WHERE order_id = ?")) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete Order with id: " + id, e);
        }
    }

    private void setProductsToOrders(List<Order> orders) {
        for (Order order : orders) {
            order.setProducts(getOrdersProducts(order.getId()));
        }
    }
}
