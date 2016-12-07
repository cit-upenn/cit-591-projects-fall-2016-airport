/**
 * This class represents a garage.
 * @author huongvu
 *
 */
public class Garage {
	
	private String name;
	private double latitude;
	private double longitude;
	private int adaSpaces;
	private int availSpaces;
	private int totalSpaces;
	
	public Garage(String name, double latitude, double longitude, int adaSpaces, int availSpaces, int totalSpaces) {
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.adaSpaces = adaSpaces;
		this.availSpaces = availSpaces;
		this.totalSpaces = totalSpaces;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @return the adaSpaces
	 */
	public int getAdaSpaces() {
		return adaSpaces;
	}

	/**
	 * @return the availSpaces
	 */
	public int getAvailSpaces() {
		return availSpaces;
	}

	/**
	 * @return the totalSpaces
	 */
	public int getTotalSpaces() {
		return totalSpaces;
	}

	

}
