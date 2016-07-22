package com.coweaver.test.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Maven : ( http://stackoverflow.com/questions/15561038/can-i-install-the-mysql-jdbc-connector-using-mvn-installinstall-file )
// <dependency>  <groupId>mysql</groupId>  <artifactId>mysql-connector-java</artifactId>  <version>5.1.6</version> </dependency>
// Read : ( http://www.tutorialspoint.com/jdbc/jdbc-sample-code.htm )
// JDBC driver name and database URL
// static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
// static final String DB_URL = "jdbc:mysql://localhost/EMP";
	
public class JdbcTest {

	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public JdbcTest(String host, Integer port, String user, String pass) {
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String db_url = "jdbc:mysql://" + host + ":"+port+"/NetMngr"; 
			conn = DriverManager.getConnection(db_url, user, pass);
 
			stmt = conn.createStatement(); 
			rs = stmt.executeQuery("select id as c1, section as c2, entry as c3, value as c4 from config");
			while (rs.next()) { 
				System.out.printf("%-10s,%-10s,%-10s,%-10s\n", rs.getString("c1"), rs.getString("c2"), rs.getString("c3"), rs.getString("c4")); 
			}

			try {
				stmt.executeUpdate("delete from config where id = 'j'");
				stmt.executeUpdate("delete from config where id = 'e'");			
			} catch (SQLException e) {  
				e.printStackTrace(); 
			}
			
			pstmt = conn.prepareStatement("insert into config values(?,?,?,?)"); 
			pstmt.setString(1,"j"); 
			pstmt.setString(2,"j"); 
			pstmt.setString(3,"j"); 
			pstmt.setString(4,"j"); 
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("select id as c1, section as c2, entry as c3, value as c4 from config where id = 'j'");
			rs = pstmt.executeQuery();
			while (rs.next()) { 
				System.out.printf("%-10s,%-10s,%-10s,%-10s\n", rs.getString("c1"), rs.getString("c2"), rs.getString("c3"), rs.getString("c4")); 
			}
			
			pstmt = conn.prepareStatement("delete from config where id = ?;");
			pstmt.setString(1, "j");
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}			
   
	}

}
