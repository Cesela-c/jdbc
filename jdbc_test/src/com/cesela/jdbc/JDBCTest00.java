package com.cesela.jdbc;

import java.io.Closeable;
import java.sql.*;
import java.util.ResourceBundle;

public class JDBCTest00 {
    public static void main(String[] args) {

		ResourceBundle bundle = ResourceBundle.getBundle("jdbc-mysql");
    	String driver = bundle.getString("driver");
		String url = bundle.getString("url");
		String user = bundle.getString("user");
		String password = bundle.getString("password");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
		
		try{

			Class.forName(driver);

			conn = DriverManager.getConnection(url,user,password);
			String sql = "select empno as a,ename,sal from emp";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()){
				String empno = rs.getString(1);
				String ename = rs.getString(2);
				String sal = rs.getString(3);
				System.out.println(empno + "," + ename + "," + sal);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{

			if(rs != null){
				try{
					rs.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(ps != null){
				try{
					ps.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}

    }

}
