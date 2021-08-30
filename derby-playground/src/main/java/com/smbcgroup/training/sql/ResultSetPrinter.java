package com.smbcgroup.training.sql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetPrinter {

	private ResultSet resultSet;
	private int columns;
	private int[] tabWidths;

	public ResultSetPrinter(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	private String[] readColumnNames(ResultSetMetaData meta) throws SQLException {
		String[] colNames = new String[columns];
		tabWidths = new int[columns];
		for (int i = 0; i < columns; i++) {
			String colName = meta.getColumnName(i + 1);
			colNames[i] = colName;
			tabWidths[i] = getTabWidth(colName);
		}
		return colNames;
	}

	private int getTabWidth(String colName) {
		return (int) Math.ceil((colName.length() + 1)/8);
	}

	public void print() throws SQLException {
		ResultSetMetaData meta = resultSet.getMetaData();
		columns = meta.getColumnCount();
		String[] colNames = readColumnNames(meta);
		List<String[]> rows = readRows();
		if (columns == 1 && "1".equals(colNames[0]) && rows.size() == 1) {
			System.out.println(rows.get(0)[0]);
		} else {
			printRow(colNames);
			for (String [] row : rows)
				printRow(row);
		}
	}

	private void printRow(String[] row) {
		StringBuilder line = new StringBuilder();
		for (int i = 0; i < columns; i++) {
			String col = row[i];
			line.append(col);
			int diff = tabWidths[i] - getTabWidth(col);
			for (int t = 0; t <= diff; t++)
				line.append("\t");
		}
		System.out.println(line.toString());
	}

	private List<String[]> readRows() throws SQLException {
		List<String[]> rows = new ArrayList<>();
		while (resultSet.next()) {
			String[] cols = new String[columns];
			for (int i = 0; i < columns; i++) {
				Object o = resultSet.getObject(i + 1);
				String col = o != null ? o.toString() : "";
				cols[i] = col;
				tabWidths[i] = Math.max(tabWidths[i], getTabWidth(col));
			}
			rows.add(cols);
		}
		return rows;
	}

}
