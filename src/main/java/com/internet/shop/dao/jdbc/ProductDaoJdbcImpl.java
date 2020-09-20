package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.ProductDao;
import com.internet.shop.exceptions.DataProcessingException;
import com.internet.shop.lib.Dao;
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
public class ProductDaoJdbcImpl implements ProductDao {
    private static final String INSERT_SQL =
            "INSERT INTO products (productName, price) VALUES (?, ?)";
    private static final String UPDATE_SQL =
            "UPDATE products SET productName =?,price=? WHERE product_id = ?";
    private static final String SELECT_ALL_SQL =
            "SELECT * FROM products WHERE is_deleted = 0";
    private static final String SELECT_ONE_SQL =
            "SELECT * FROM products WHERE product_id = ? AND is_deleted = 0";
    private static final String DELETE_SQL =
            "UPDATE products SET is_deleted = 1 WHERE product_id = ?";

    @Override
    public Product create(Product item) {
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection
                        .prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            setValues(preparedStatement, item);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                item.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException ex) {
            throw new DataProcessingException("Can't insert product", ex);
        }
        return item;
    }

    @Override
    public Product update(Product item) {
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection
                        .prepareStatement(UPDATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            setValues(preparedStatement, item);
            preparedStatement.setLong(3, item.getId());
            if (preparedStatement.executeUpdate() > 0) {
                return item;
            } else {
                throw new DataProcessingException("Can't update product");
            }
        } catch (SQLException ex) {
            throw new DataProcessingException("Can't update product", ex);
        }
    }

    @Override
    public Optional<Product> get(Long id) {
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection
                        .prepareStatement(SELECT_ONE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(conversionToProduct(resultSet));
            } else {
                throw new DataProcessingException("Can't get product");
            }
        } catch (SQLException ex) {
            throw new DataProcessingException("Can't get product", ex);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection
                        .prepareStatement(DELETE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DataProcessingException("Can't delete product", ex);
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection
                        .prepareStatement(SELECT_ALL_SQL, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(conversionToProduct(resultSet));
            }
            return products;
        } catch (SQLException ex) {
            throw new DataProcessingException("Can't delete product", ex);
        }
    }

    private void setValues(PreparedStatement ps, Product product) throws SQLException {
        ps.setString(1, product.getName());
        ps.setDouble(2, product.getPrice());
    }

    private Product conversionToProduct(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("product_id");
        double price = resultSet.getDouble("price");
        String name = resultSet.getString("productName");
        return new Product(id, name, price);
    }
}
