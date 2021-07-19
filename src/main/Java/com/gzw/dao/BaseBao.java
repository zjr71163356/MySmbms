package com.gzw.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

// 操作数据库的公共类
public class BaseBao {
    private static String driver;
    private static String url;
    private static String userName;
    private static String password;

    // 静态代码块，类加载是就初始化
    static {
        Properties properties = new Properties();
        InputStream is = BaseBao.class.getClassLoader().getResourceAsStream("db.properties");

        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        userName = properties.getProperty("userName");
        password = properties.getProperty("password");



    }
    // 获取数据库的连接
    public static Connection getConnection(){

        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,userName,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    // 编写查询公共类
    public static ResultSet execute(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet, String sql, Object[] params) throws SQLException {
        // 预编译的sql语句
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            // 占位符从1开始，但是数组从0开始
            preparedStatement.setObject(i+1,params[i]);
        }
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    // 编写增删改工具类
    public static int execute(Connection connection, String sql, Object[] params,PreparedStatement preparedStatement) throws SQLException {
        int updateRows = 0;
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            // 占位符从1开始，但是数组从0开始
            preparedStatement.setObject(i+1,params[i]);
        }
        updateRows = preparedStatement.executeUpdate();
        return updateRows;
    }

    // 关闭连接，释放资源
    public static boolean closeResource(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        boolean flag = true;
        if (resultSet != null){
            try {
                resultSet.close();
                // GC垃圾回收
                resultSet = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                // 关闭失败
                flag = false;
            }
        }
        if (preparedStatement != null){
            try {
                preparedStatement.close();
                // GC垃圾回收
                preparedStatement = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                // 关闭失败
                flag = false;
            }
        }
        if (connection != null){
            try {
                connection.close();
                // GC垃圾回收
                connection = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                // 关闭失败
                flag = false;
            }
        }
        return flag;
    }
}
