package com.cesela.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * 将连接数据库的所有信息配置到配置文件中
 * 实际开发过程中不建议把连接数据写死在java程序中。
 */
public class JDBCTest04 {
    public static void main(String[] args) {

        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");

        Connection conn = null;
        Statement stmt = null;
        try {
            //1、注册驱动
            Class.forName(driver);
            //2、获取连接
            conn = DriverManager.getConnection(url, user, password);
            //3、获取数据库操作对象
            stmt = conn.createStatement();
            //4、执行SQL语句
            //String sql = "delete from dept where deptno = 40"
            //JDBC中的sql语句不需要提供分号结尾。
            String sql = "update dept set dname = 'xiaoshou2',loc = 'tianj2'where deptno = 20";
            int count = stmt.executeUpdate(sql);
            System.out.println(count == 1 ? "修改成功" : "修改失败");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6、释放资源
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
    }
}
