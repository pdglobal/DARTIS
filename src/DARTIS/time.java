package DARTIS;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Random;

public class time {
	public static String[] getTimestamp(int length) {
		float estimatedTime = (float) System.nanoTime();
		LocalDate today = LocalDate.now();
		int dateFormatter = today.getYear();
		LocalTime time = LocalTime.now();
		int clock = Integer.parseInt(time.toString().replace(":", "").replace(".", ""));
		String datetime = String.valueOf((Calendar.DAY_OF_YEAR * dateFormatter * clock));
		String datetime_era = String.valueOf((Calendar.ERA));
		String s = datetime_era;
		byte[] bytes = s.getBytes();
		StringBuilder binary = new StringBuilder();
		for (byte b : bytes) {
			int val = b;
			for (int i = 0; i < 8; i++) {
				binary.append((val & 128) == 0 ? 0 : 1);
				val <<= 1;
			}
		}
		// System.out.println("DateTime = "+datetime+binary+" + "+estimatedTime+" =
		// "+String.valueOf(datetime)+String.valueOf(binary)+Long.toString(estimatedTime));
		String[] ERA = strings.stringequalsplit(String.valueOf(binary), 4);
		int ERACompressed = Integer.parseInt(ERA[1]) + Integer.parseInt(ERA[2]);
		Random rand = new Random();
		String ret = String.valueOf(
				new BigDecimal(Math.abs(((Float.valueOf(datetime) * 100.0f) * (ERACompressed * estimatedTime) * 100.0f)
						/ (rand.nextFloat() * 100.0f) * 100.0f)));
		return strings.stringequalsplit(ret, String.valueOf(length).length() - 1);
	}
}
