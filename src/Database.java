package src;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 * This class pulls data from the Oracle database hosted in Amazon Web Services.
 * @author veronikaalex
 *
 */
public class Database {

	// Database information and credentials
	Connection conn = null;
	Statement stmt = null;
	//Database credentials have been removed for public repo security purposes
	private final String DB_URL = "xxxxx";
	private final String DB_NAME = "xxxx";
	private final String USER = "xxxxxxxx";
	private final String PASSWORD = "xxxxxxxx";
	private int dayOfWeek;
	private int month;
	private String strMonth ;
	private int time;
	private int lowerTime;
	private int upperTime;
	private int dayOfMonth;
	private ArrayList<Integer> delays = new ArrayList<Integer>();
	private ArrayList<Integer> customsWait = new ArrayList<Integer>();


	/**
	 * Constructor for the database class
	 * @param dayOfWeek int value for the day of week. Values start at 1, such that 1 = Monday and 7 = Sunday
	 * @param month int value for month, starting at 1
	 * @param time on 24 hr clock
	 * @param dayOfMonth date
	 */
	public Database(int dayOfWeek, int month, int time, int dayOfMonth){
		//dayOfWeek is an integer index for the weekday, where 1 = Monday and 7 = Sunday
		this.dayOfWeek = dayOfWeek;
		//Integer month (1-12)
		this.month = month;
		//Time of day, in 24 hour clock
		this.time = time;
		//Day of month
		this.dayOfMonth = dayOfMonth;
	}

	
	/**
	 * Converts the integer month value to a three-letter string for querying the database
	 * Method is only necessary in cases where the customs wait times are needed
	 */
	public void convertMonth(){
		String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"} ;
		this.strMonth = months[this.month - 1] ;
	}

	
	
	/**
	 * Getter method for month.
	 * @return month as a string
	 */
	public String getMonth() {
		return strMonth;
	}
	
	
	
	/**
	 * Takes the input time and uses it to find the lower and upper time bounds for querying the database.
	 */
	public void convertTime(){
		//use integer division to find the hour/minute and lower bound
		int inputHour = time / 100;
		int inputMin = time - (inputHour * 100);
		int setHour = 0;
		int setMin = 0;

		//if minute is less than 30, the lower bound will be in the previous hour, so account for that
		if(inputMin < 30){
			setMin = 60 - (30-inputMin);
			setHour = (inputHour - 1) * 100;
		}
		else{
			setHour = inputHour * 100;
			setMin = inputMin - 30; }

		//Set lowerTime and upperTime using the lowerTime
		lowerTime = setHour + setMin ;

		//Check to make sure the minute for the upper bound is right
		if(inputMin > 30){
			setMin = 30 - (60 - inputMin);
			setHour = (inputHour + 1)*100;
		}
		else{
			setHour = inputHour * 100;
			setMin = inputMin + 30;
		}

		//set the uppertime
		upperTime = setHour + setMin;

	}
	
	
	
	/**
	 * Getter method for lowerTime.
	 * @return lower bound of time input.
	 */
	public int getLowerTime() {
		return lowerTime;
	}
	
	
	
	/**
	 * Getter method for upperTime.
	 * @return upper bound of time input.
	 */
	public int getUpperTime() {
		return upperTime;
	}
	

	
	/**
	 * Connects to database, querys database based on input flight time and date.
	 * @return ArrayList of flight delays for a specific month, day of week, and time frame
	 */
	public ArrayList<Integer> pullFlightDelayData() {
		convertTime();

		try {
			// Standard setup
			DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
			conn = DriverManager.getConnection("jdbc:oracle:thin:@//" + DB_URL + "/" + DB_NAME, USER, PASSWORD);
			stmt = conn.createStatement();

			// Build sql string
			String sqlQuery;
			sqlQuery = "SELECT * FROM FLIGHTDELAYS WHERE DAY_OF_WEEK = " + dayOfWeek 
					+ " AND MONTH = " + month + " AND CRS_DEP_TIME > " + lowerTime + " AND CRS_DEP_TIME < " + upperTime;


			// Execute sql string
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


	
	/**
	 * Creates query string and uses it to query the Customs Wait Data table in the remote database
	 * @return ArrayList customsWait, the list of customs wait time for the particular queried day
	 */
	public ArrayList<Integer> queryCustomsData(){
		convertMonth();

		try {
			// Standard setup
			DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
			conn = DriverManager.getConnection("jdbc:oracle:thin:@//" + DB_URL + "/" + DB_NAME, USER, PASSWORD);
			stmt = conn.createStatement();

			// Build your sql string
			String sqlQuery;
			sqlQuery = "select * from customs_wait_times where arrival_date like \'%" + dayOfMonth + "-" + strMonth  + "%\'";

			// Execute it
			//System.out.println("Executing query... " + sqlQuery);
			ResultSet rs = stmt.executeQuery(sqlQuery);

			// Iterate over rs to retrieve results
			while(rs.next()) {
				//Retrieve by column name
				int avgWaitTime = rs.getInt("AVG_WAIT");
				customsWait.add(avgWaitTime);
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
		return customsWait;
	}
}
