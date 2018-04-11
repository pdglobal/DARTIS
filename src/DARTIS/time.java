package DARTIS;

import java.math.BigDecimal;

public class time {
	public static int counter = 0;

	public static String[] getTimestamp(int length) {
		counter += 1;
		Runtime runtime = Runtime.getRuntime();
		BigDecimal estimatedTime = new BigDecimal(System.nanoTime()).add(new BigDecimal(counter));
		if (counter > 999) {
			counter = 0;
		}
		if (estimatedTime.toString().substring(0, 1).equals("-")) {
			estimatedTime = new BigDecimal(new StringBuilder(estimatedTime.abs().toPlainString()).reverse().toString());
			// I know this isn't anywhere near an optimal solution, looking for any
			// suggestions on a better way to handle this
		}
		String baseData = (estimatedTime).toPlainString().replace(".", "")
				.concat(String.valueOf(runtime.freeMemory()).replace(".", ""));
		String timestamp[] = strings.stringequalsplit(baseData, String.valueOf(length).length() - 1);
		return timestamp;
	}
}
