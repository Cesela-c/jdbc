package com.cesela.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *  sql脚本
 *  drop table if exists t_act;
 *  create table t_act(
 *      actno int,
 *      belance double(7,2) //注意，7表示有效数字的个数，2表示小数位的个数。
 *  );
 *  insert into t_act(actno,balance) values(111,20000);
 *  insert into t_act(actno,balance) values(222,0);
 *  commit;
 *  select * from t_act;
 *
 *  重点三行代码?
 *      conn.setAutoCommit(false);
 *      conn.commit();
 *      conn.rollback();
 */
public class JDBCTest11 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            //1、注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2、获取连接
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bjpowernode","root","0716.");
            //将自动提交机制修改为手动提交
            conn.setAutoCommit(false);

            //3、获取预编译数据库操作对象
            String sql = "update t_act set balance = ? where actno = ?";
            ps = conn.prepareStatement(sql);

            //给？传值
            ps.setDouble(1,10000);
            ps.setInt(2,111);
            int count = ps.executeUpdate();

            //String s = null;
            //s.toString();

            //给？传值
            ps.setDouble(1,10000);
            ps.setInt(2,222);
            count += ps.executeUpdate();

            System.out.println(count == 2 ? "转账成功" : "转账失败");

            //程序能够狗走到这里说明以上程序没有异常，事务结束，手动提交数据
            conn.commit(); //提交事务

        } catch (Exception e) {
            if (conn != null){
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {
            if (ps !=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps !=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
