package com.jrs_qms_llh.commons.utils;

import com.mysql.jdbc.Driver;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * jdbc工具
 *
 * @author 黄浩文
 */
public final class JdbcUtil {
    private static String DRIVER;
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;
    private static Connection conn = null;

    static {
        Config config = new Config("config/db.properties");
        DRIVER = config.get("jdbc.driver");
        URL = config.get("jdbc.url");
        USERNAME = config.get("jdbc.username");
        PASSWORD = config.get("jdbc.password");
        try {
            //手动注册驱动，性能更好
            Driver driver = (Driver) Class.forName(DRIVER).newInstance();
            DriverManager.registerDriver(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到数据库连接对象
     *
     * @return
     */
    public final static Connection getConn() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 资源关闭的通用方法
     *
     * @param rs
     * @param stmt
     * @param conn
     */
    public final static void closeConn(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 通用的更新方法
     *
     * @param sql    数据库查询语句
     * @param params 占位符的参数列表
     * @return 如果修改成功返回受影响行数，失败返回0
     * @author 黄浩文
     */
    public final static int executeUpdate(String sql, Object... params) {
        int row = 0;
        Connection conn = getConn();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt = JdbcUtil.conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            row = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(null, pstmt, conn);
        }
        return row;
    }

    /**
     * 方法功能说明： 通用的查询方法。 TODO 约定 ：字段名必须全部小写，跟表字段名一样
     *
     * @param sql    数据库查询语句
     * @param clazz  实体类对象的类对象
     * @param params 占位符的属性列表
     * @return 返回查询到的list集合
     * @author 黄浩文
     */
    public final static <T> List<T> executeQuery(String sql, Class<T> clazz, Object... params) {
        List<T> list = new ArrayList<>();
        Connection conn = getConn();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = JdbcUtil.conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            rs = pstmt.executeQuery();
            ResultSetMetaData rm = rs.getMetaData();
            int count = rm.getColumnCount();// 获得总列数
            while (rs.next()) {
                T t = clazz.newInstance();// 创建对象
                for (int i = 1; i <= count; i++) {
                    String name = rm.getColumnName(i).toLowerCase();// 获得列名
                    Field f = clazz.getDeclaredField(name);// 获得属性
                    f.setAccessible(true);// 设置属性访问权
                    f.set(t, rs.getObject(i));// 设置属性
                }
                list.add(t);// 把对象添加到集合
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn(rs, pstmt, conn);
        }
        return list;
    }

}
