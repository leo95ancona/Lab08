package it.polito.tdp.borders.db;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.DataSources;

public class DBConnect {

	static private final String jdbcUrl = "jdbc:mysql://localhost/countries?user=root&password=root";
	static private DBConnect instance = null;
	static private DataSource ds;

	private DBConnect() {
		instance = this;
	}

	public static DBConnect getInstance() {
		if (instance == null)
			return new DBConnect();
		else {
			return instance;
		}
	}

	public Connection getConnection() {
		
		if (ds==null){
			try {
				ds = DataSources.pooledDataSource(DataSources.unpooledDataSource(jdbcUrl));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1);
			}
		}
		
		try {

			Connection conn = ds.getConnection();
			return conn;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Cannot get connection " + jdbcUrl, e);
		}
	}

}
