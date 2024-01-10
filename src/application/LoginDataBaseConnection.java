package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class LoginDataBaseConnection {
	public Connection con;

	public Connection getConnection() {
		String dbUsername = "root";
		String dbPassword = "root123";
		String URL = "127.0.0.1";
		String port = "3306";
		String dbName = "UniqueMus";
		String dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName
				+ "?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=ConvertToNull&serverTimezone=GMT";
		try {

			Properties p = new Properties();
			p.setProperty("user", dbUsername);
			p.setProperty("password", dbPassword);
			p.setProperty("useSSL", "false");
			p.setProperty("autoReconnect", "true");
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbURL, p);

		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}

		return con;
	}

}
