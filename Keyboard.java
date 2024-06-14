/**
 * This class is used to instantiate a keyboard product and provides methods for
 * accessing the kyeboard's information
 * 
 * @author Luyando
 */
public class Keyboard extends Product {

	private KeyboardType type;
	private KeyboardLayout layout;

	public Keyboard(int barcodeID, String brand, String colour, Connectivity connectivity, double originalCost,
			int quantityInStock, double retailPrice, KeyboardType type, KeyboardLayout layout) {
		super(barcodeID, brand, colour, connectivity, originalCost, quantityInStock, retailPrice);
		this.type = type;
		this.layout = layout;
	}

	public KeyboardType getType() {
		return this.type;
	}

	public KeyboardLayout getLayout() {
		return this.layout;
	}

}
