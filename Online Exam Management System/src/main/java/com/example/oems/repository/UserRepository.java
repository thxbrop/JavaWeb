package com.example.oems.repository;

import com.example.oems.Result;
import com.example.oems.dao.UserDao;
import com.example.oems.db.AppDataBase;
import com.example.oems.entity.User;
import com.example.oems.util.StringUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 处理用户相关业务
 */
public class UserRepository {
    private static UserRepository INSTANCE = null;
    private static Connection connection;
    private final UserDao dao;

    private UserRepository() {
        dao = new UserDao(connection);
    }

    public static UserRepository getInstance() {
        if (connection == null) {
            connection = AppDataBase.getConnection();
        }
        if (INSTANCE == null) {
            INSTANCE = new UserRepository();
        }
        return INSTANCE;
    }

    public void close() {
        try {
            connection.close();
            INSTANCE = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection = null;
    }

    /**
     * 用户注册
     *
     * @param email    邮箱，不能为空
     * @param username 用户名
     * @param password 密码，不能为空
     * @return 注册执行结果
     */
    public Result<User> register(String email, String username, String password) {
        if (StringUtil.isNullOrEmpty(email) || StringUtil.isNullOrEmpty(password))
            return new Result<>("Email and password must not be empty");
        List<User> list = dao.getByEmail(email);
        if (list.isEmpty()) {
            User user = new User(email, password, username, User.ROLE_DEFAULT);
            dao.insert(user);
            return new Result<>(user);
        }
        return new Result<>("Email has been registered");
    }

    /**
     * 用户注册
     *
     * @param email    邮箱，不能为空
     * @param password 密码，不能为空
     * @return 注册执行结果
     */
    public Result<User> register(String email, String password) {
        return register(email, "User_" + System.currentTimeMillis(), password);
    }

    /**
     * 用户登录
     *
     * @param email    邮箱，不能为空
     * @param password 密码，不能为空
     * @return 登录执行结果
     */
    public Result<User> login(String email, String password) {
        if (StringUtil.isNullOrEmpty(email) || StringUtil.isNullOrEmpty(password))
            return new Result<>("Email and password must not be empty");
        List<User> list = dao.getByEmail(email);
        if (list.isEmpty() || list.stream().noneMatch(user -> user.getEmail().equals(email) && user.getPassword().equals(password))) {
            return new Result<>("The user does not exist or the password is entered incorrectly");
        } else {
            return new Result<>(list.get(0));
        }
    }

    public Result<User> getByEmail(String email) {
        if (!StringUtil.isNullOrEmpty(email)) {
            List<User> list = dao.getByEmail(email);
            if (!list.isEmpty()) {
                return new Result<>(list.get(0));
            }
        }
        return new Result<>("The user does not exist");
    }

    public List<User> getAll() {
        return dao.getAll();
    }


    public void delete(String email) {
        dao.deleteByEmail(email);
    }

    public Result<User> update(String email, String username, String password, Integer role) {
        if (StringUtil.isNullOrEmpty(email)) {
            return new Result<>("Email required");
        }
        if (!StringUtil.isNullOrEmpty(username)) {
            dao.updateUsername(email, username);
        }
        if (!StringUtil.isNullOrEmpty(password)) {
            dao.updatePassword(email, password);
        }
        if (role != null) {
            dao.updateRole(email, role);
        }
        return getByEmail(email);
    }
}
