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
        String selectQuery = "SELECT * FROM orders WHERE user_id = ? AND is_deleted = FALSE";
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(selectQuery)) {
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
        String insertQuery = "INSERT INTO orders (user_id) VALUES(?)";
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, order.getUserId());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                order.setId(generatedKeys.getLong(1));
            }
            statement.close();
            addDataToOrdersProductTable(order, connection);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create Order with id: " + order.getId(), e);
        }

        return order;
    }

    @Override
    public Order update(Order order) {
        String updateQuery =
                "UPDATE orders SET user_id = ? WHERE order_id = ? AND id_deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setLong(1, order.getUserId());
            statement.setLong(2, order.getId());
            statement.executeUpdate();
            statement.close();
            deleteOrderProducts(order.getId(), connection);
            addDataToOrdersProductTable(order, connection);
            return order;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update order with id: "
                    + order.getId(), e);
        }
    }

    @Override
    public Optional<Order> get(Long id) {
        String selectQuery = "SELECT order_id, user_id FROM orders WHERE order_id = ? "
                + "AND is_deleted = FALSE ";
        List<Product> products = getOrdersProducts(id);
        Order order = null;
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                order = new Order(resultSet.getLong("order_id"),
                        resultSet.getLong("user_id"));
                order.setProducts(products);
            }
            return Optional.ofNullable(order);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get Order with id: " + id, e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String updateQuery = "UPDATE orders SET is_deleted = TRUE WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete Order with id: " + id, e);
        }
    }

    @Override
    public List<Order> getAll() {
        String selectQuery = "SELECT * FROM orders WHERE is_deleted = FALSE";
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(selectQuery)) {
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

    private void addDataToOrdersProductTable(Order order, Connection connection) {
        String insertQuery = "INSERT INTO orders_products (order_id, product_id) VALUES(?,?)";
        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            for (Product product : order.getProducts()) {
                statement.setLong(1, order.getId());
                statement.setLong(2, product.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add data to Orders_Product table", e);
        }
    }

    private List<Product> getOrdersProducts(Long orderId) {
        String selectQuery = "SELECT * FROM products p JOIN orders_products op "
                + "ON op.product_id = p.product_id WHERE order_id = ? AND is_deleted = FALSE";
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement(selectQuery)) {
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

    public boolean deleteOrderProducts(Long id, Connection connection) {
        String deleteQuery = "DELETE FROM orders_products WHERE order_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
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
