package com.example.oems.db;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 管理数据库连接
 */
public class AppDataBase {

    private static DataSource dataSource;

    private AppDataBase() {
    }

    public static Connection getConnection() {
        if (dataSource == null) {
            Properties properties = new Properties();
            try {
                properties.load(AppDataBase.class.getClassLoader().getResourceAsStream("application.properties"));
                dataSource = DruidDataSourceFactory.createDataSource(properties);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
