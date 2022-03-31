package com.example.application_in_servlet.repository;

import com.example.application_in_servlet.Result;
import com.example.application_in_servlet.dao.UserDao;
import com.example.application_in_servlet.db.AppDataBase;
import com.example.application_in_servlet.entity.User;
import com.example.application_in_servlet.util.StringUtil;

import java.util.List;

/**
 * 处理用户相关业务
 */
public class UserRepository {
    private final UserDao dao;

    private UserRepository() {
        dao = new UserDao(AppDataBase.getConnection());
    }

    private static UserRepository INSTANCE = null;

    public static UserRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository();
        }
        return INSTANCE;
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
            User user = new User(email, password, username);
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
        return register(email, null, password);
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
}
