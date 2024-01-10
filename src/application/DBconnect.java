package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javafx.application.Application;
import javafx.stage.Stage;

public class DBconnect extends Application{
		
	private static String dbURL;
	private static String dbUsername = "root";
	private static String dbPassword = "root123";
	private static String URL = "127.0.0.1";
	private static String port = "3306";
	private static String dbName = "UniqueMus";
	private static Connection con;

	
	
public static Connection getConnect(){
		
		dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=ConvertToNull&serverTimezone=GMT";

		System.out.println(dbURL);
		Properties p = new Properties();
		p.setProperty("user", dbUsername);
		p.setProperty("password", dbPassword);
		p.setProperty("useSSL", "false");
		p.setProperty("autoReconnect", "true");
		
		//Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			con = DriverManager.getConnection (dbURL, p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return con;
	}
	
	
	
	public DBconnect() {
		// TODO Auto-generated constructor stub
	}



	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub

	}

}
