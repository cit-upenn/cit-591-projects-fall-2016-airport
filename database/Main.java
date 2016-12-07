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
