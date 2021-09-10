package com.smbcgroup.training.jdbc;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JDBCTest {
	
	private static Connection conn;
	
	@BeforeClass
	public static void setupConnection() throws Exception {
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		conn = DriverManager.getConnection("jdbc:derby:target/testdb;create=true");
	}
	
	@Before
	public void setupTables() throws SQLException {
		dropTables();
		createTables();
		populateTables();
	}

	private static void dropTables() {
		dropTable("Employees");
	}

	private static void dropTable(String table) {
		try {
			conn.prepareStatement("DROP TABLE " + table).execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void createTables() throws SQLException {
		conn.prepareStatement("CREATE TABLE Employees(id INT PRIMARY KEY, name VARCHAR(64) NOT NULL, department VARCHAR(64), salary NUMERIC(10,2))").execute();
	}

	private static void populateTables() throws SQLException {
		conn.prepareStatement("INSERT INTO Employees(id, name, department) VALUES(1452, 'Alison Smith', 'Loans')").execute();
		conn.prepareStatement("INSERT INTO Employees(id, name, department) VALUES(1453, 'John Doe', 'Loans')").execute();
		conn.prepareStatement("INSERT INTO Employees(id, name, department) VALUES(1454, 'Jane Smith', 'Marketing')").execute();
		conn.prepareStatement("INSERT INTO Employees(id, name, department) VALUES(1455, 'John Jones', 'Marketing')").execute();
		conn.prepareStatement("INSERT INTO Employees(id, name, department) VALUES(1456, 'Ron Gold', 'Trade')").execute();
	}
	
	@Test
	public void testSelect1() throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Employees WHERE id = ?");
		stmt.setInt(1, 1452);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		assertEquals("Alison Smith", rs.getString("name"));
	}
	
	@Test
	public void testSelect2() throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Employees WHERE id = ?");
		stmt.setInt(1, 1452);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		assertEquals(1452, rs.getInt("id"));
	}
	
	@Test
	public void testSelect3() throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Employees WHERE name = ?");
		stmt.setString(1, "John Doe");
		ResultSet rs = stmt.executeQuery();
		rs.next();
		assertEquals(1453, rs.getInt("id"));
	}
	
	@Test
	public void testSelect4() throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Employees WHERE department = ?");
		stmt.setString(1, "Marketing");
		ResultSet rs = stmt.executeQuery();
		rs.next();
		assertEquals("Jane Smith", rs.getString("name"));
		rs.next();
		assertEquals("John Jones", rs.getString("name"));
	}
	
	@Test
	public void testSelect5() throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Employees WHERE department = ?");
		stmt.setString(1, "Marketing");
		ResultSet rs = stmt.executeQuery();
		rs.next();
		assertEquals("Jane Smith", rs.getString("name"));
		rs.next();
		assertEquals("John Jones", rs.getString("name"));
	}

}
