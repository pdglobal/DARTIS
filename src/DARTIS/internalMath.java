package DARTIS;

public class internalMath {
	public static int boolToInt(Boolean b) {
	    return b.compareTo(false);
	}
	public static int safeLongToInt(long l) {
	    if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
	        throw new IllegalArgumentException
	            (l + " cannot be cast to int without changing its value.");
	    }
	    return (int) l;
	}
}
