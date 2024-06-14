/**
 * This class is an abstract parent class for the customer and admin classes
 * therefore can not be instantiated It provides methods to access information
 * about a single user
 * 
 * @author Luyando
 */
public abstract class User {
	private int userID;
	private String username;
	private Address address;
	private String name;

	public User(int userID, String username, String name, Address address) {
		this.userID = userID;
		this.username = username;
		this.name = name;
		this.address = address;
	}

	public int getUserID() {
		return userID;
	}

	public String getUsername() {
		return username;
	}

	public Address getAddress() {
		return address;
	}

	public String getName() {
		return name;
	}

}
