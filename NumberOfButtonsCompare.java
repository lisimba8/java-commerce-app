import java.util.Comparator;

public class NumberOfButtonsCompare implements Comparator<String> {

	public int compare(String numOfButtons1, String numOfButtons2) {
		return Integer.parseInt(numOfButtons1) - Integer.parseInt(numOfButtons2);
	}
}
