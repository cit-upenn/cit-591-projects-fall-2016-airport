package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.DatabaseMetaData;

/**
 * This class pulls data from the Oracle database hosted in Amazon Web Services.
 * @author veronikaalex
 *
 */
public class Database {
	
	// Database information and credentials
	private final String DB_URL = "cit591-airports.czp0ytib1pp5.us-east-1.rds.amazonaws.com";
	private final String DB_NAME = "ORCL";
	private final String USER = "airports";
	private final String PASSWORD = "swapneel";  
	private ArrayList<Integer> delays = new ArrayList<Integer>();
	
	
	/**
	 * Constructor.
	 */
	public Database() {
		
	}
	
	
	/**
	 * Connects to database, querys database based on input flight time and date.
	 * @return ArrayList of flight delays for a specific month, day of week, and time frame
	 */
	public ArrayList<Integer> pullFlightDelayData() {
		Connection conn = null;
		Statement stmt = null;

		try {
			// Standard setup
			DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
			conn = DriverManager.getConnection("jdbc:oracle:thin:@//" + DB_URL + "/" + DB_NAME, USER, PASSWORD);
			stmt = conn.createStatement();

			// Build your sql string
			String sqlQuery;
			sqlQuery = "SELECT * FROM FLIGHTDELAYS WHERE DAY_OF_WEEK = 4 AND MONTH = 11 AND CRS_DEP_TIME > 1300 AND CRS_DEP_TIME < 1400";

			// Execute it
			//System.out.println("Executing query... " + sqlQuery);
			ResultSet rs = stmt.executeQuery(sqlQuery);

			// Iterate over rs to retrieve results
			while(rs.next()) {
				//Retrieve by column name
				int month  = rs.getInt("MONTH");
				int dayOfWeek = rs.getInt("DAY_OF_WEEK");
				int departTime = rs.getInt("CRS_DEP_TIME");
				String carr = rs.getString("CARRIER");
				int delayTime = rs.getInt("DEP_DELAY");
				delays.add(delayTime);
//				System.out.println("Month: " + month + "   " + "Day of Week: " + dayOfWeek + "   " + "Depart Time: " + departTime + 
//						"   " + "Delay: " + delayTime);
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
		return delays;
	}
	
	
	
	
	
	
}
