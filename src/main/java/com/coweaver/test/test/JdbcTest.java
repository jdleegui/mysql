package com.coweaver.test.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Add Maven
// http://stackoverflow.com/questions/15561038/can-i-install-the-mysql-jdbc-connector-using-mvn-installinstall-file
// <dependency>
//  <groupId>mysql</groupId>
//  <artifactId>mysql-connector-java</artifactId>
//  <version>5.1.6</version>
// </dependency>
// sample code : http://www.tutorialspoint.com/jdbc/jdbc-sample-code.htm 

public class JdbcTest {

	public JdbcTest(String host, String user, String pass) {
		// TODO_Auto-generated constructor stub
		// TODO_Auto-generated constructor stub
		// STEP 1. Import required packages
		// JDBC driver name and database URL
		// static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		// static final String DB_URL = "jdbc:mysql://localhost/EMP";
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			String db_url = "jdbc:mysql://" + host + "/NetMngr";
			conn = DriverManager.getConnection(db_url, user, pass);
			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "select id, section, entry, value from config";
			ResultSet rs = stmt.executeQuery(sql);
			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				System.out.println(
						rs.getString("id") + "," + 
						rs.getString("section") + "," +
						rs.getString("entry") + ","+ 
						rs.getString("value"));
			}
			// STEP 6: Clean-up environment
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
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// e.printStackTrace();
			} // nothing we can do
		} // end try
		System.out.println("Goodbye!");

	}

}
