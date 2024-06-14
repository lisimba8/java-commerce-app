import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is used to instantiate a mouse product and provides methods for
 * accessing the mouse's information
 * 
 * @author Luyando
 */
public class Mouse extends Product {
	private int numOfButtons;
	private MouseType type;

	public Mouse(int barcodeID, String brand, String colour, Connectivity connectivity, double originalCost,
			int quantityInStock, double retailPrice, int numOfButtons, MouseType mouseType) {
		super(barcodeID, brand, colour, connectivity, originalCost, quantityInStock, retailPrice);
		this.numOfButtons = numOfButtons;
		this.type = mouseType;
	}

	public int getNumOfButtons() {
		return numOfButtons;
	}

	public MouseType getType() {
		return type;
	}

	/**
	 * This method is called to get an array containing the different number of
	 * buttons for the mouse products available in stock
	 * 
	 * @return string array for the different number of buttons available for the
	 *         mouse products in stock
	 */
	public static String[] getMouseButtons() {
		ArrayList<Product> products = Product.getProducts();
		ArrayList<String> mouseButtons = new ArrayList<String>();
		for (Product product : products) {
			if (product instanceof Mouse) {
				Mouse mouse = (Mouse) product;
				if (!mouseButtons.contains(String.valueOf(mouse.getNumOfButtons()))) {
					mouseButtons.add(String.valueOf(mouse.getNumOfButtons()));
				}
			}
		}
		Collections.sort(mouseButtons, new NumberOfButtonsCompare());
		String[] arr = mouseButtons.toArray(new String[0]);
		return arr;
	}

}
