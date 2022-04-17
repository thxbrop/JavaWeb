package com.example.oems.db;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

/**
 * 管理数据库连接
 */
public class AppDataBase {

    private AppDataBase() {

    }

    public static Connection getConnection() {
        Connection connection = null;
        Properties properties = new Properties();
        try {
            properties.load(AppDataBase.class.getClassLoader().getResourceAsStream("application.properties"));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            connection = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
