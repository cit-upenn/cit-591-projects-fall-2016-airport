package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DatabaseMetaData;

public class Main {
	private static final String DB_URL = "cit591-airports.czp0ytib1pp5.us-east-1.rds.amazonaws.com";
	private static final String DB_NAME = "ORCL";
	private static final String USER = "airports";
	private static final String PASSWORD = "swapneel";  
   
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;

		try {
			// Standard setup
			DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
			conn = DriverManager.getConnection("jdbc:oracle:thin:@//" + DB_URL + "/" + DB_NAME, USER, PASSWORD);
			stmt = conn.createStatement();

			// Build your sql string
			String sqlQuery;
			sqlQuery = "SELECT * FROM FLIGHTDELAYS";

			// Execute it
			System.out.println("Executing query... " + sqlQuery);
			ResultSet rs = stmt.executeQuery(sqlQuery);

			// Iterate over rs to retrieve results
			while(rs.next()) {
				//Retrieve by column name
				int month  = rs.getInt("MONTH");
				String carr = rs.getString("CARRIER");
				System.out.println("Month: " + month + "\tCarrier: " + carr);
			} 
			
			// Clean-up time
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			//finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			
			try {
				if (conn != null)
					conn.close();
			} catch(SQLException se){
				se.printStackTrace();
			}
		}
	}
}


















//package database;
//import java.sql.DriverManager;
//import java.sql.Connection;
//
//public class Main {
//	
//
//	// Database information and credentials
//	private static Connection conn;
//	private static final String DB_URL = "cit-airports.czp0ytib1pp5.us-east-1.rds.amazonaws.com";
//	private static final String DB_NAME = "cit_airports";
//	private static final String USER = "airports";
//	private static final String PASSWORD = "swapneel";
//
//	public static void main(String[] args) throws Exception {
//		// TODO Auto-generated method stub
//		conn = DriverManager.getConnection("jdbc:mysql://" + DB_URL + "/" + DB_NAME, USER, PASSWORD);
//		System.out.println("Connected.");
//		//getConnection();
//	}
//
//	
////	public static Connection getConnection() {
////		String driver = "com.oracle.jdbc.Driver";
////		String url = "jdbc:mysql://cit-airports.czp0ytib1pp5.us-east-1.rds.amazonaws.com:3306/";
////		
////		
////		try {
////			Class.forName(driver);
////			conn = DriverManager.getConnection(url);
////			
////			
////		}
////		catch {
////			
////		}
////		
////		
////	}
//
//	
////	public static Connection getConnection() throws Exception{
////		try {
////			
////			String url = "jdbc:mysql://cit-airports.czp0ytib1pp5.us-east-1.rds.amazonaws.com:3306/";
////			String userName = "airports";
////			String password = "swqpneel";
////			String dbName = "MySQL";
////			String driver = "com.mysql.jdbc.Driver";
////			Connection conn = DriverManager.getConnection(url + dbName, userName, password);
////
////			
////			//Connection conn = DriverManager.getConnection(url, username, password);
////			System.out.println("Connected.");
////			return conn;
////		}
////		catch (Exception e){e.printStackTrace();}
////			return null;
////	}
//}
