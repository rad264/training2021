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
//		dropTables();
//		createTables();
//		populateTables();
	}

	private static void dropTables() {
		dropTable("Employees");
		dropTable("Departments");
	}

	private static void dropTable(String table) {
		try {
			conn.prepareStatement("DROP TABLE " + table).execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void createTables() throws SQLException {
		conn.prepareStatement("CREATE TABLE Departments (id INT PRIMARY KEY NOT NULL, name VARCHAR(64), manager_name VARCHAR(64))").execute();
		conn.prepareStatement("CREATE TABLE Employees (id INT PRIMARY KEY NOT NULL, name VARCHAR(64), wage DECIMAL(30, 2), Department_id INT, FOREIGN KEY (Department_id) REFERENCES Departments(id))").execute();
	}

	private static void populateTables() throws SQLException {
		conn.prepareStatement("INSERT INTO Departments(id, name, manager_name) values (2, 'Loans', 'Alex Smith'), (3, 'JRIA', 'Beatriz Smith')").execute();
		conn.prepareStatement("INSERT INTO Employees(id, name, wage, department_id) VALUES (1453, 'Bob Smith', 60000, 1), (1454, 'Chris Smith', 50000, 1), (1455, 'Dinesh Smith', 50000, 2), (1456, 'Emily Smith', 90000, 3), (1457, 'Felix Smith', 80000, 3), (1458, 'Gonzo Smith', 100000, 3)"
				+ "").execute();
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
	public void testHighestToLowest() throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Employees e LEFT JOIN Departments d ON e.department_id = d.id WHERE e.wage > ? ORDER BY wage DESC");
		stmt.setInt(1, 50000);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		assertEquals("Gonzo Smith", rs.getString("name"));
		rs.next();
		assertEquals("Emily Smith", rs.getString("name"));
		rs.next();
		assertEquals("Felix Smith", rs.getString("name"));
		rs.next();
		assertEquals("Alison Smith", rs.getString("name"));
		rs.next();
		assertEquals("Bob Smith", rs.getString("name"));
	}
	
	@Test
	public void testNumberOfTreasuryEmployees() throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM Employees e LEFT JOIN Departments d ON e.department_id = d.id WHERE d.name = ?");
		stmt.setString(1, "Treasury");
		ResultSet rs = stmt.executeQuery();
		rs.next();
		assertEquals(3, rs.getInt(1));
	}
}
