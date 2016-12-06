package database;
import java.sql.DriverManager;
import java.sql.Connection;

public class Main {
	

	// Database information and credentials
	private static Connection conn;
	private static final String DB_URL = "cit-airports.czp0ytib1pp5.us-east-1.rds.amazonaws.com";
	private static final String DB_NAME = "cit_airports";
	private static final String USER = "airports";
	private static final String PASSWORD = "swapneel";

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		conn = DriverManager.getConnection("jdbc:mysql://" + DB_URL + "/" + DB_NAME, USER, PASSWORD);
		System.out.println("Connected.");
		//getConnection();
	}

	
//	public static Connection getConnection() {
//		String driver = "com.oracle.jdbc.Driver";
//		String url = "jdbc:mysql://cit-airports.czp0ytib1pp5.us-east-1.rds.amazonaws.com:3306/";
//		
//		
//		try {
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url);
//			
//			
//		}
//		catch {
//			
//		}
//		
//		
//	}

	
//	public static Connection getConnection() throws Exception{
//		try {
//			
//			String url = "jdbc:mysql://cit-airports.czp0ytib1pp5.us-east-1.rds.amazonaws.com:3306/";
//			String userName = "airports";
//			String password = "swqpneel";
//			String dbName = "MySQL";
//			String driver = "com.mysql.jdbc.Driver";
//			Connection conn = DriverManager.getConnection(url + dbName, userName, password);
//
//			
//			//Connection conn = DriverManager.getConnection(url, username, password);
//			System.out.println("Connected.");
//			return conn;
//		}
//		catch (Exception e){e.printStackTrace();}
//			return null;
//	}
}
