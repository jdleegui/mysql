package com.coweaver.test.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcConfig {

	private Connection conn = null;

	public JdbcConfig(String host, Integer port, String user, String pass) {
		// TODO_Auto-generated constructor stub

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String db_url = "jdbc:mysql://" + host + ":" + port + "/NetMngr";
			conn = DriverManager.getConnection(db_url, user, pass);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void Close() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void Del(String id, String section, String entry, String value) {
		
		if (conn != null) {
			
			PreparedStatement pstmt = null;
			
			try{
				
				pstmt = conn.prepareStatement("delete from config where where id = ? and section = ? and entry = ?;");
				
				pstmt.setString(1, id);
				pstmt.setString(2, section);
				pstmt.setString(3, entry);				
				
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				try {
					if (pstmt != null) {
						pstmt.close();
						pstmt = null;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void Add(String id, String section, String entry, String value) {
		
		if (conn != null) {
			
			PreparedStatement pstmt = null;
			
			try{
				
				pstmt = conn.prepareStatement("insert into config (id, section, entry, value) values (?,?,?,?);");
				pstmt.setString(1, id);
				pstmt.setString(2, section);
				pstmt.setString(3, entry);
				pstmt.setString(4, value);
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				try {
					if (pstmt != null) {
						pstmt.close();
						pstmt = null;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void Set(String id, String section, String entry, String value) {
		
		if (conn != null) {
			
			PreparedStatement pstmt = null;
			try{
				
				pstmt = conn.prepareStatement("update config set value = ? where where id = ? and section = ? and entry = ?;");
				pstmt.setString(1, value);
				pstmt.setString(2, id);
				pstmt.setString(3, section);
				pstmt.setString(4, entry);
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				try {
					if (pstmt != null) {
						pstmt.close();
						pstmt = null;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public int Get(String id, String section, String entry, int ret) {
		return Integer.parseInt(Get(id,section,entry,Integer.toString(ret)));
	}
	
	public String Get(String id, String section, String entry, String ret) {
		String value = ret;
		if (conn != null) {
			
			ResultSet rs = null;
			PreparedStatement pstmt = null;
			
			try {
				pstmt = conn.prepareStatement("select value from config where id = ? and section = ? and entry = ?;");
				pstmt.setString(1, id);
				pstmt.setString(2, section);
				pstmt.setString(3, entry);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					value = rs.getString("value");
					break;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				try {
					if (rs != null) {
						rs.close();
						rs = null;
					}
					if (pstmt != null) {
						pstmt.close();
						pstmt = null;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}

}
