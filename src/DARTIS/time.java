package DARTIS;

import java.math.BigDecimal;

public class time {
	public static int counter = 0;

	public static String[] getTimestamp(int length) {
		counter += 1;
		BigDecimal estimatedTime = new BigDecimal(System.nanoTime()).add(new BigDecimal(counter));
		if (counter > 1000) {
			counter = 0;
		}
		if (estimatedTime.toString().substring(0, 1).equals("-")) {
			estimatedTime = new BigDecimal(new StringBuilder(estimatedTime.abs().toPlainString()).reverse().toString()); //I know this isn't anywhere near an optimal solution, looking for any suggestions on a better way to handle this
		}
		return strings.stringequalsplit((estimatedTime).toPlainString().replace(".", ""),
				String.valueOf(length).length() - 1);
	}
}
