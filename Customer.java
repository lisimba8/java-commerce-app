import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

// can pay for goods which updates the stock and makes the basket empty
// payment made with credit card or paypal
// can cancel basket which empties it

/**
 * This class is used to instantiate a customer and provide methods for a
 * customer to access, clear and add items to their basket. It also provides
 * methods for customer to pay for items in their basket
 * 
 * @author Luyando
 */
public class Customer extends User {
	private HashMap<Integer, Integer> basket;
	// basket is saved as hashmap with product ID and the amount of that product in
	// the basket
	private Double basketTotal = 0.0;

	public Customer(int userID, String username, String name, Address address) {
		super(userID, username, name, address);
		this.basket = null;
	}

	/**
	 * This method is called to add a product to an individual customer's basket. It
	 * returns the status of this process accordingly
	 * 
	 * @param a single product (Type Product)
	 * @return String showing weather successful or not. Returns reason as well if
	 *         unsuccessful
	 */
	public String addToBasket(Product product) {
		if (product.getQuantityInStock() > 0) { // ensures customer can not buy product with zero stock
			if (this.basket == null) {// if the basket is being used for the first time
				this.basket = new HashMap<Integer, Integer>();
				this.basket.put(product.getBarcodeID(), 1);
			} else {
				Set<Integer> basketKeys = this.basket.keySet(); // using set to check all keys in the hash map
				Integer key = product.getBarcodeID();
				if (basketKeys.contains(product.getBarcodeID())) {
					Integer value = this.basket.get(key);
					if (product.getQuantityInStock() > value && product.getQuantityInStock() > 0) {// ensure that we do
																									// not get more than
																									// in stock
						this.basket.put(key, value + 1); // if we already have the product in the basket, we simply want
															// to
															// increment the quantity in basket
					} else {
						return "Exceeded available stock of this product!";
					}
				} else {
					this.basket.put(key, 1);
				}
			}
			if (product instanceof Mouse) {
				return "Successfully added " + product.getBrand() + " mouse to Basket!";
			} else {
				return "Successfully added " + product.getBrand() + " keyboard to Basket!";
			}
		} else {
			return "This product has zero stock left";
		}
	}

	/**
	 * This method is called to view the contents of a customer's basket. Mainly
	 * used when debugging as the contents are printed to the console
	 */
	public void viewBasket() {
		for (HashMap.Entry<Integer, Integer> basketEntry : this.basket.entrySet()) {
			System.out.println(basketEntry.getKey());
			System.out.println(basketEntry.getValue());
		}
	}

	/**
	 * This method is called to get an Arraylist of a customer's basket. the array
	 * list contains all the products in the user's basket
	 * 
	 * @return ArrayList of Product
	 */
	public ArrayList<Product> getBasketArrayList() {
		ArrayList<Product> basketArrayList = new ArrayList<Product>();
		if (this.basket != null) {
			Set<Integer> basketKeys = this.basket.keySet(); // using set to check all keys in the hash map
			ArrayList<Product> products = Product.getProducts();
			for (Product product : products) {
				if (basketKeys.contains(product.getBarcodeID())) {
					basketArrayList.add(product);
				}
			}

		}

		return basketArrayList;
	}

	/**
	 * This method is called to get the Customer's basket in the form of a hashmap.
	 * the hash map has the key as the product ID and the value as the number of
	 * that product in the basket
	 * 
	 * @return HashMap of products in basket. The key as the product ID and the
	 *         value as the number of that product in the basket
	 */
	public HashMap<Integer, Integer> getBasketHashMap() {
		return this.basket;
	}

	public void clearBasket() {
		/**
		 * This method is called to clear a cutomer's basket
		 */
		this.basket = null;
	}

	/**
	 * This method is called to ensure that payment method is valid
	 * 
	 * @param card number and security code if the method is by visa, or the user's
	 *             email if the method is by paypal
	 * @return string showing if method is valid or not. If valid, an empty string
	 *         is returned
	 */
	public String pay(String cardNumber, String securityCode) { // kept them as string because of the way they are being
																// input
		if (cardNumber.trim().matches("\\d{6}") && securityCode.trim().matches("\\d{3}")) { // ensure card number and
																							// security code are 6 and 3
																							// digit numbers
																							// respectively
			ArrayList<Product> products = Product.getProducts();
			Set<Integer> basketKeys = this.basket.keySet();
			for (Product product : products) {
				if (basketKeys.contains(product.getBarcodeID())) {
					Integer quantityOrdered = this.basket.get(product.getBarcodeID());
					basketTotal += (quantityOrdered * product.getRetailPrice());
					product.setQuantityInStock(product.getQuantityInStock() - quantityOrdered);//
				}
			}
			Product.saveProducts(products);
			this.clearBasket();
			return "";
		} else if (cardNumber.trim().matches("\\d{6}")) {
			return "Security code is invalid";
		} else if (securityCode.trim().matches("\\d{3}")) {
			return "Card Number is invalid";
		} else {
			return "Both Card number entered and Security Code are invalid.";
		}
	}

	/**
	 * This method is called to ensure that payment method is valid
	 * 
	 * @param card number and security code if the method is by visa, or the user's
	 *             email if the method is by paypal
	 * @return string showing if method is valid or not. If valid, an empty string
	 *         is returned
	 */
	public String pay(String email) {
		if (email.trim().matches("^(.+)@(\\S+)$")) {
			ArrayList<Product> products = Product.getProducts();
			Set<Integer> basketKeys = this.basket.keySet();
			for (Product product : products) {
				if (basketKeys.contains(product.getBarcodeID())) {
					Integer quantityOrdered = this.basket.get(product.getBarcodeID());
					basketTotal += (quantityOrdered * product.getRetailPrice());
					product.setQuantityInStock(product.getQuantityInStock() - quantityOrdered);//
				}
			}
			Product.saveProducts(products);
			this.clearBasket();
			return "";
		} else {
			return "Invalid email";
		}
	}

	public Double getBasketTotal() {
		return basketTotal;
	}

}
