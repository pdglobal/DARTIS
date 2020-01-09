package DARTIS;

public class internalMath {
	public static int boolToInt(Boolean b) {
		return b.compareTo(false);
	}

	public static int safeLongToInt(long l) {
		if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
			return 0;
		}
		return (int) l;
	}

	public static int compute(String equation) {
		int result = 0;
		String[] byPluses = equation.split("\\+");
		for (String multipl : byPluses) {
			String[] byMultipl = multipl.split("\\*");
			int multiplResult = 1;
			for (String operand : byMultipl) {
				multiplResult *= Math.round(Float.valueOf(operand));
			}
			result += multiplResult;
		}
		return result;
	}

}
