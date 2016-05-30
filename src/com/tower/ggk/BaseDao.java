package com.tower.ggk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BaseDao {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
		//加载驱动
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//得到连接
		//Connection ct=DriverManager.getConnection("jdbc:oracle:thin:@123.126.34.92:1521:daptestdb", "SZ_CHECK", "ldau8xWY");
		Connection ct=DriverManager.getConnection("jdbc:oracle:thin:@123.126.34.82:1525:orcl", "RESTW_T07", "kb*Mc310");
		
		
		Statement sm=ct.createStatement();
		ResultSet rs=sm.executeQuery("select * from dual");
		while(rs.next())
		{
		System.out.println("用户名:"+rs.getString(1));
		}
		rs.close();
		sm.close();
		ct.close(); 
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		}
	
}
