import java.util.ArrayList;

/**
 * This class is used to instantiate an Admin and provides methods for the Admin
 * to access and create products
 * 
 * @author Luyando
 */
public class Admin extends User {
	public Admin(int userID, String username, String name, Address address) {
		super(userID, username, name, address);
	}
	// can add new product and view all products
	// create functions for these

	/**
	 * this method id called to get an arraylist of all products available in stock
	 * 
	 * @return Array list of products
	 */
	public static ArrayList<Product> viewAllProducts() {
		ArrayList<Product> products = Product.getProducts();
		return products;
	}

	/**
	 * this method is called to add a new product to the stock of products
	 * 
	 * @param a single product (Type Product)
	 */
	public static void addNewProduct(Product product) {
		Product.addProduct(product);
	}
}
