package com.example.oems.dao;

import com.example.oems.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements BaseDao<User> {
    private final Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(User user) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO user (email, username, password,role) VALUES (?,?,?,?)")) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getRole());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE id = ?")) {
            statement.setInt(1, user.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUsername(String email, String username) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE user SET username=? WHERE email = ?")) {
            statement.setString(1, username);
            statement.setString(2, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePassword(String email, String password) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE user SET password=? WHERE email = ?")) {
            statement.setString(1, password);
            statement.setString(2, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRole(String email, int role) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE user SET role=? WHERE email = ?")) {
            statement.setInt(1, role);
            statement.setString(2, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getById(int id) {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE id = ? LIMIT 1")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("username"),
                        resultSet.getInt("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 通过邮箱查询用户
     *
     * @param email 邮箱
     * @return 邮箱符合给定值的用户集合
     */
    public List<User> getByEmail(String email) {
        List<User> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE email = ?")) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                list.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("username"),
                        resultSet.getInt("role")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM user")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("username"),
                        resultSet.getInt("role")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
