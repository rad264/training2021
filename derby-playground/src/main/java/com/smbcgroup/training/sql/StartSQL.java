package com.smbcgroup.training.sql;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StartSQL {

	public static void main(String[] args) throws Exception {
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:derby:db;create=true")) {
			readFromConsole(conn);
		}
	}

	private static void readFromConsole(Connection conn) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("Enter SQL:");
			String sql = reader.readLine();
			if ("exit".equals(sql)) {
				System.out.println("Goodbye");
				break;
			}
			try(PreparedStatement stmt = conn.prepareStatement(sql)) {
				if (stmt.execute())
					new ResultSetPrinter(stmt.getResultSet()).print();
			} catch (SQLException e) {
				System.out.println("Failed to execute SQL: " + e.getMessage());
			}
		}
	}

}
