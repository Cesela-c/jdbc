package com.cesela.jdbc.utils;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

/**
 *  JDBC工具类，简化JDBC编程
 */
public class DBUtil {
    private DBUtil(){}

    //静态代码块在类加载时执行，并且只执行一次。
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return 连接对象
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","0716.");
    }

    /**
     * 关闭资源
     * @param conn 连接对象
     * @param ps 数据库操作对象
     * @param rs 结果集
     */
    public static void close(Connection conn, Statement ps, ResultSet rs){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null){
            try {
               conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
