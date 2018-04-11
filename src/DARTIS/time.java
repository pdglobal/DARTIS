package DARTIS;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
public class time {
	public static int counter = 0;
	public static String[] getTimestamp(int length) {
		counter += 1;
		BigDecimal estimatedTime = new BigDecimal(System.nanoTime()).add(new BigDecimal(counter));
		if (counter > 1000) { counter = 0; }
		return strings.stringequalsplit(String.valueOf(estimatedTime).replace(".", ""), String.valueOf(length).length() - 1);
	}
}
