package DARTIS;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

public class time {
	public static String[] getTimestamp(int length) {
		long estimatedTime = System.nanoTime();
		LocalDate today = LocalDate.now();
		int dateFormatter = today.getYear();
		LocalTime time = LocalTime.now();
		int clock = Integer.parseInt(time.toString().replace(":", "").replace(".", ""));
		String datetime = String.valueOf((Calendar.DAY_OF_YEAR + dateFormatter + clock));
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
		return strings.stringequalsplit(datetime + ERACompressed + estimatedTime, String.valueOf(length).length() - 1);
	}
}
