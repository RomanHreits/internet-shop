package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.UserDao;
import com.internet.shop.exceptions.DataProcessingException;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.Role;
import com.internet.shop.model.User;
import com.internet.shop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Dao
public class UserDaoJdbc implements UserDao {

    @Override
    public Optional<User> findByLogin(String login) {
        User user = null;
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement("SELECT * FROM users "
                        + "WHERE login = ? AND is_deleted = FALSE")) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = conversionToUser(resultSet);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't find User with login: " + login, e);
        }
        user.setRoles(getUserRoles(user.getId()));
        return Optional.of(user);
    }

    @Override
    public User create(User user) {
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement("INSERT INTO users "
                                + "(name, login, password) VALUES(?,?,?)",
                        Statement.RETURN_GENERATED_KEYS)) {
            setValues(statement, user);
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create User with id: " + user.getId(), e);
        }
        for (Role role : user.getRoles()) {
            Long roleId = getRoleByLogin(role.getRoleName().toString());
            addUserRoles(user.getId(), roleId);
        }
        return user;
    }

    @Override
    public User update(User user) {
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement("UPDATE users SET name = ?, "
                        + "login = ?, password = ? WHERE user_id = ? AND is_deleted = FALSE")) {
            setValues(statement, user);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create User with id: " + user.getId(), e);
        }
        deleteUserRole(user.getId());
        for (Role role : user.getRoles()) {
            addUserRoles(user.getId(), role.getId());
        }
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement("SELECT * FROM users "
                        + "WHERE user_id = ? AND is_deleted = FALSE")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = conversionToUser(resultSet);
                user.setRoles(getUserRoles(user.getId()));
                return Optional.ofNullable(user);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't find User with id: " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE "
                        + "users SET is_deleted = TRUE WHERE user_id = ?")) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete User with id: " + id, e);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection
                         .prepareStatement("SELECT * FROM users WHERE is_deleted = FALSE")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = conversionToUser(resultSet);
                user.setRoles(getUserRoles(user.getId()));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get Users from DB", e);
        }
    }

    private void setValues(PreparedStatement ps, User user) throws SQLException {
        ps.setString(1, user.getName());
        ps.setString(2, user.getLogin());
        ps.setString(3, user.getPassword());
    }

    private User conversionToUser(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("user_id");
        String name = resultSet.getString("name");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        return new User(id, name, login, password);
    }

    private void addUserRoles(long userId, long roleId) {
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement("INSERT "
                         + "INTO user_roles (user_id, role_id) VALUES(?,?)")) {
            statement.setLong(1, userId);
            statement.setLong(2, roleId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add user_role", e);
        }
    }

    private Set<Role> getUserRoles(long userId) {
        Set<Role> roles = new HashSet<>();
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection
                         .prepareStatement("SELECT r.role_id, r.role_name FROM roles r "
                             + "INNER JOIN user_roles ur ON r.role_id = ur.role_id "
                                 + "WHERE ur.user_id = ?")) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Role role = Role.of(resultSet.getString("role_name"));
                role.setId(resultSet.getLong("role_id"));
                roles.add(role);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't get userRoles", e);
        }
        return roles;
    }

    private Long getRoleByLogin(String roleName) {
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection
                         .prepareStatement("SELECT role_id FROM roles WHERE role_name = ?")) {
            statement.setString(1, roleName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("role_id");
            }
            throw new DataProcessingException("");
        } catch (SQLException e) {
            throw new RuntimeException("", e);
        }
    }

    private void deleteUserRole(Long userId) {
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection
                         .prepareStatement("DELETE FROM user_roles WHERE user_id = ?")) {
            statement.setLong(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("", e);
        }
    }
}
