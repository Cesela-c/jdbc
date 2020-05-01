package com.cesela.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *  注册驱动的另一种方式（这种方式常用)
 */
public class JDBCTest03 {
    public static void main(String[] args) {
        try {
            //1、注册驱动
            //这是驱动的第一种写法
            //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            //注册驱动的第二种方式：常用的。
            //为什么这种方式常用？因为参数是一个字符串，字符串可以写到xxxx.properties文件中。
            //以下方法不需要接收返回值，因为我们只想用它的类加载动作。
            Class.forName("com.mysql.jdbc.Driver");
            //2、获取链接
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bjpowernode","root","0716.");
            System.out.println(conn);
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
