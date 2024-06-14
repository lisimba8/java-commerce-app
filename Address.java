/**
 * This class provides methods for handling a user's address
 * 
 * @author Luyando
 */
public class Address {
	private int houseNumber;
	private String postcode;
	private String city;

	public Address(int houseNumber, String postcode, String city) {
		this.houseNumber = houseNumber;
		this.postcode = postcode;
		this.city = city;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getCity() {
		return city;
	}

	@Override
	public String toString() {
		return "House Number: " + this.houseNumber + ", Postcode: " + this.postcode + ", City: " + this.city;
	}

}
