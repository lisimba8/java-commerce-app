import java.util.Comparator;


public class RetailPriceCompare implements Comparator<Product>{
	public int compare(Product product1, Product product2) {
		double val =  product1.getRetailPrice()- product2.getRetailPrice();
		if(val>0) return 1;
		if(val<0) return -1;
		else return 0;
	}

}
