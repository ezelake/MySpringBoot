package ceq.maven.h2;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {

	static Connection conn = null;

	static {
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static public Connection getConnection() {
		return conn;
	}
	
}
