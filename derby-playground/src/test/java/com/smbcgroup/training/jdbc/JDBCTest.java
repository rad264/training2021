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
		conn.prepareStatement("CREATE TABLE Employees(id INT PRIMARY KEY, name VARCHAR(64) NOT NULL)").execute();
	}

	private static void populateTables() throws SQLException {
		conn.prepareStatement("INSERT INTO Employees(id, name) VALUES(1452, 'Alison Smith')").execute();
	}
	
	@Test
	public void testSelect1() throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Employees WHERE id = ?");
		stmt.setInt(1, 1452);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		assertEquals("Alison Smith", rs.getString("name"));
	}

}
