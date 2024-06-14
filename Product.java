import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * This class is an abstract parent class for the keyboard and mouse classes
 * therefore can not be instantiated It provides methods for accessing
 * information about a single product and methods to access and update products
 * in stock as well
 * 
 * @author Luyando
 */
public abstract class Product {
	private int barcodeID; // unique 6 digit number
	private String brand;
	private String colour;
	private Connectivity connectivity;
	private int quantityInStock;
	private double originalCost;
	private double retailPrice;

	public Product(int barcodeID, String brand, String colour, Connectivity connectivity, double originalCost,
			int quantityInStock, double retailPrice) {
		this.barcodeID = barcodeID;
		this.brand = brand;
		this.colour = colour;
		this.connectivity = connectivity;
		this.originalCost = originalCost;
		this.quantityInStock = quantityInStock;
		this.retailPrice = retailPrice;
	}

	public int getBarcodeID() {
		return barcodeID;
	}

	public String getBrand() {
		return brand;
	}

	public String getColour() {
		return colour;
	}

	public Connectivity getConnectivity() {
		return connectivity;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public double getOriginalCost() {
		return originalCost;
	}

	public double getRetailPrice() {
		return retailPrice;
	}

//	@Override
	public boolean equals(Product product) {
		// TODO Auto-generated method stub
		return this.barcodeID == product.barcodeID;
	}

	public boolean equals(int barcode) { // receives a barcode
		// TODO Auto-generated method stub
		return this.barcodeID == barcode;
	}

	/**
	 * This method is called to get and Arraylist of all products in stock
	 * 
	 * @return ArrayList of Product
	 */
	public static ArrayList<Product> getProducts() {
		ArrayList<Product> products = new ArrayList<Product>();
		BufferedReader br = null;

		String fileTxt = null;
		String[] productDetailsArr = null;
		try {
			br = new BufferedReader(new FileReader("Stock.txt"));
			while ((fileTxt = br.readLine()) != null) {
				productDetailsArr = fileTxt.split(",");

				if (productDetailsArr[1].trim().toLowerCase().equals("mouse")) {
					products.add(new Mouse(Integer.parseInt(productDetailsArr[0].trim()),
							productDetailsArr[3].trim().toLowerCase(), productDetailsArr[4].trim().toLowerCase(),
							Connectivity.valueOf(productDetailsArr[5].trim().toUpperCase()),
							Double.parseDouble(productDetailsArr[7].trim()),
							Integer.parseInt(productDetailsArr[6].trim()),
							Double.parseDouble(productDetailsArr[8].trim()),
							Integer.parseInt(productDetailsArr[9].trim()),
							MouseType.valueOf(productDetailsArr[2].trim().toUpperCase())));
				} else {
					products.add(new Keyboard(Integer.parseInt(productDetailsArr[0].trim()),
							productDetailsArr[3].trim().toLowerCase(), productDetailsArr[4].trim().toLowerCase(),
							Connectivity.valueOf(productDetailsArr[5].trim().toUpperCase()),
							Double.parseDouble(productDetailsArr[7].trim()),
							Integer.parseInt(productDetailsArr[6].trim()),
							Double.parseDouble(productDetailsArr[8].trim()),
							KeyboardType.valueOf(productDetailsArr[2].trim().toUpperCase()),
							KeyboardLayout.valueOf(productDetailsArr[9].trim().toUpperCase())));
				}

			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return products;
	}

	public static void saveProducts(ArrayList<Product> products) {
		/**
		 * This method is called to to save an ArrayList of products in to the Stock.txt
		 * file It is called once a product has been added to the array list to save a
		 * new product
		 * 
		 * @param ArrayList of Product
		 */
		BufferedWriter bw = null;
		///////////////
		try {
			bw = new BufferedWriter(new FileWriter("Stock.txt"));
			for (Product product : products) {
				if (product instanceof Keyboard) {
					Keyboard keyboard = (Keyboard) product;
					bw.write(keyboard.getBarcodeID() + ", keyboard," + keyboard.getType() + ", " + keyboard.getBrand()
							+ ", " + keyboard.getColour() + ", " + keyboard.getConnectivity() + ", "
							+ keyboard.getQuantityInStock() + ", " + keyboard.getOriginalCost() + ", "
							+ keyboard.getRetailPrice() + ", " + keyboard.getLayout() + "\n");
				} else {
					Mouse mouse = (Mouse) product;
					bw.write(mouse.getBarcodeID() + ", mouse," + mouse.getType() + ", " + mouse.getBrand() + ", "
							+ mouse.getColour() + ", " + mouse.getConnectivity() + ", " + mouse.getQuantityInStock()
							+ ", " + mouse.getOriginalCost() + ", " + mouse.getRetailPrice() + ", "
							+ mouse.getNumOfButtons() + "\n");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}
		////////////////

	}

	/**
	 * This method is used to add a product to the stock in Stock.txt file
	 * 
	 * @param a single Product (Type Product)
	 */
	public static void addProduct(Product newProduct) {
		// TODO Auto-generated method stub

		ArrayList<Product> products = getProducts();
		products.add(newProduct);
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("Stock.txt"));
			for (Product product : products) {
				if (product instanceof Keyboard) { // used to save new keyboard to file
					Keyboard keyboard = (Keyboard) product;
					bw.write(keyboard.getBarcodeID() + ", keyboard," + keyboard.getType() + ", " + keyboard.getBrand()
							+ ", " + keyboard.getColour() + ", " + keyboard.getConnectivity() + ", "
							+ keyboard.getQuantityInStock() + ", " + keyboard.getOriginalCost() + ", "
							+ keyboard.getRetailPrice() + ", " + keyboard.getLayout() + "\n");
				} else {
					Mouse mouse = (Mouse) product; // used to save new mouse to file
					bw.write(mouse.getBarcodeID() + ", mouse," + mouse.getType() + ", " + mouse.getBrand() + ", "
							+ mouse.getColour() + ", " + mouse.getConnectivity() + ", " + mouse.getQuantityInStock()
							+ ", " + mouse.getOriginalCost() + ", " + mouse.getRetailPrice() + ", "
							+ mouse.getNumOfButtons() + "\n");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method is called to get an array of product IDs.
	 * 
	 * @return String array of all product IDs in stock
	 */
	public static String[] getProductIDs() {
		ArrayList<Product> products = getProducts();
		ArrayList<String> productIDs = new ArrayList<String>();
		for (Product product : products) {
			productIDs.add(String.valueOf(product.getBarcodeID()));
		}
		String[] arr = productIDs.toArray(new String[0]);
		return arr;
	}

	/**
	 * This method is called to get an array of product brands.
	 * 
	 * @return String array of all product brands in stock
	 */
	public static String[] getProductBrands() {
		ArrayList<Product> products = getProducts();
		ArrayList<String> productBrands = new ArrayList<String>();
		for (Product product : products) {
			if (!productBrands.contains(product.getBrand())) {
				productBrands.add(product.getBrand());
			}
		}
		String[] arr = productBrands.toArray(new String[0]);
		return arr;
	}

}
