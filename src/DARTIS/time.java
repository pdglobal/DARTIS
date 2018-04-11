package DARTIS;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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
		int clock = internalMath.compute(time.toString().replace(":", "+"));
		String datetime = String.valueOf(Calendar.DAY_OF_YEAR + dateFormatter) + clock;
		Random rand = new Random();
		MathContext mc = new MathContext(23, RoundingMode.HALF_UP);
		String ret = new BigDecimal(datetime.concat(String.valueOf(estimatedTime).replace(".", "").replace("-", "")))
				.divide(new BigDecimal(rand.nextDouble() * 100.0f), mc).toPlainString().replace(".", "")
				.replaceAll("-", "");
		return strings.stringequalsplit(ret.substring(0,23), String.valueOf(length).length() - 1);
	}
}
