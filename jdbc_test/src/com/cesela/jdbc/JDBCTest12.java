package com.cesela.jdbc;

import com.cesela.jdbc.utils.DBUtil;

import java.sql.*;

/**
 *  这个过程两个任务：
 *      第一：测试DBUtil是否好用
 *      第二：模糊查询怎么写？
 */
public class JDBCTest12 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //获取连接
            conn = DBUtil.getConnection();
            //获取预编译的数据库操作对象
            //错误的写法
            /*
            String sql = "select ename from emp where ename like '_?&'";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"A");
            */

            String sql = "select ename from emp where ename like ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"_A%");
            rs = ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("ename"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            DBUtil.close(conn,ps,rs);
        }
    }
}
