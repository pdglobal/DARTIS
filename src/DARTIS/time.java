package DARTIS;

import java.math.BigDecimal;

public class time {
	public static int counter = 0;

	public static String[] getTimestamp(int length) {
		counter += 1;
		BigDecimal estimatedTime = new BigDecimal(System.nanoTime()).add(new BigDecimal(counter));
		if (counter > 999) {
			counter = 0;
		}
		if (estimatedTime.toString().substring(0, 1).equals("-")) {
			estimatedTime = new BigDecimal(new StringBuilder(estimatedTime.abs().toPlainString()).reverse().toString());
			// I know this isn't anywhere near an optimal solution, looking for any
			// suggestions on a better way to handle this
		}
		String baseData = (estimatedTime).toPlainString().replace(".", "");
		String timestamp[] = strings.stringequalsplit(baseData, String.valueOf(length).length() - 1);
		String unpadded = "";
		for (int i = 0; i <= (timestamp.length - 2); i++) {
			unpadded = unpadded.concat(timestamp[i]).concat("0");
		}
		timestamp = strings.stringequalsplit(unpadded, String.valueOf(length).length() - 1);
		return timestamp;
	}
}
