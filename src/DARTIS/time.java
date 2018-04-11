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
		if (estimatedTime.toString().substring(0, 1).equals("-")) { estimatedTime = new BigDecimal(new StringBuilder(estimatedTime.abs().toPlainString()).reverse().toString()); }
		return strings.stringequalsplit((estimatedTime).toPlainString().replace(".", ""), String.valueOf(length).length() - 1);
	}
}
