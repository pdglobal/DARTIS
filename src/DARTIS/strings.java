package DARTIS;

import java.util.Arrays;

public class strings {

	public static String[] stringequalsplit(String text, int length) {
		int arraysize = 0;
		if (length < 2) {
			arraysize = text.length() + 1;
		} else {
			arraysize = Math.round(text.length() / length) + (length / 2);
		}
		String[] list = new String[arraysize];
		// System.out.println("Array size = "+arraysize);
		int index = 0;
		int realindex = 1;
		while (index < text.length()) {
			list[realindex] = text.substring(index, Math.min(index + length, text.length()));
			// System.out.println(index+" - "+Math.min(index+length,text.length())+" =
			// "+text.substring(index, Math.min(index+length,text.length())));
			index = index + length;
			realindex += 1;
		}
		list[0] = String.valueOf(realindex - 1);
		return list;
	}

	public static String array2str(double array[][]) {
		String lineSeparator = ";";
		StringBuilder sb = new StringBuilder();

		for (double[] row : array) {
			sb.append(Arrays.toString(row).replace(" ", "").replace("[", "").replace("]", "") + lineSeparator);
		}

		String result = sb.toString();
		return result;
	}
}
