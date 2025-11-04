package db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DB {
	private static Properties props = null;
	
	private static Connection loadProperties(Connection conn) {
		if(props == null) {
			props = new Properties();
		}
		try (InputStream is = DB.class.getClassLoader().getResourceAsStream("db.properties")) {
			props.load(is);
			conn = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password") );
			
		}catch (Exception e) {
			throw new DbException(e.getMessage());
		}
		return conn;
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			return loadProperties(conn);
		}catch (Exception e) {
			throw new DbException(e.getMessage());
		}
	}
}
