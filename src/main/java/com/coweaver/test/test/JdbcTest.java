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

	public JdbcTest(String host, String user, String pass) {
		// TODO_Auto-generated constructor stub
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			String db_url = "jdbc:mysql://" + host + ":3306/NetMngr";
			conn = DriverManager.getConnection(db_url, user, pass);
			System.out.println("Creating statement...");

			stmt = conn.createStatement();
			String rsql = "select id, section, entry, value from config";
			ResultSet rs = stmt.executeQuery(rsql);

			while (rs.next()) {
				System.out.printf("%-10s,%-10s,%-10s,%-10s\n",
					rs.getString("id"), 
					rs.getString("section"),
					rs.getString("entry"), 
					rs.getString("value"));
			}

			String wsql = "insert into config values(?,?,?,?)";
			pstmt = conn.prepareStatement(wsql);
			pstmt.setString(1,"j");
			pstmt.setString(2,"j");
			pstmt.setString(3,"j");
			pstmt.setString(4,"j");
			pstmt.executeUpdate();

			String dsql = "delete from config where id = 'j'";
			stmt.executeUpdate(dsql);

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO_Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO_Auto-generated catch block
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (pstmt != null)
				{
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// e.printStackTrace();
			} // nothing we can do
		} // end try
		System.out.println("End of Read!");
   
	}

}
