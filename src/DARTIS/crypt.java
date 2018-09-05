package DARTIS;

import java.util.Random;

import Jama.Matrix;

public class crypt {

	public static String inject(String data, String[] key) {
		Random rand = new Random();
		String[] temp = strings.stringequalsplit(data, 1);
		int array_size = (int) Math.ceil(temp.length / 9);
		double insert[][] = new double[array_size][10];
		int n = 0;
		int l = 0;
		for (int i = 1; i <= temp.length - 1; i++) {
			insert[l][n] = temp[i].charAt(0);
			n += 1;
			if (n > 9) {
				n = 0;
				l += 1;
			}
		}
		while (n < 10) {
			insert[l][n] = rand.nextDouble();
			n += 1;
		}
		String[] timestamp = time.getTimestamp(key.length);
		Matrix matrix = new Matrix(insert);
		Matrix nextLayer = construct.hologram(key[Integer.parseInt(timestamp[1])]);
		matrix = matrix.times(nextLayer);
		String id = timestamp[1] + ";";
		int layers = Integer.parseInt(timestamp[0]);
		for (int i = 2; i <= layers; i++) {
			if (Integer.parseInt(timestamp[i]) < 1) {
				timestamp[i] = "1";
			}
			id = id + timestamp[i] + ";";
			matrix = matrix.times(construct.hologram(key[Integer.parseInt(timestamp[i])]));
		}
		String ret = strings.array2str(matrix.getArray()).replace(" ", "").replace("[", "").replace("]", "")
				.replace(", ", ",").replace("E", "E+") + "~" + id;
		String verify = crypt.extract(ret, key);
		while (!verify.equals(data)) {
			timestamp = time.getTimestamp(key.length);
			matrix = new Matrix(insert);
			nextLayer = construct.hologram(key[Integer.parseInt(timestamp[1])]);
			matrix = matrix.times(nextLayer);
			id = timestamp[1] + ";";
			for (int i = 2; i <= Integer.parseInt(timestamp[0]); i++) {
				if (Integer.parseInt(timestamp[i]) < 1) {
					timestamp[i] = "1";
				}
				id = id + timestamp[i] + ";";
				matrix = matrix.times(construct.hologram(key[Integer.parseInt(timestamp[i])]));
			}
			ret = strings.array2str(matrix.getArray()).replace(" ", "").replace("[", "").replace("]", "")
					.replace(", ", ",").replace("E", "E+") + "~" + id;
			verify = crypt.extract(ret, key);
		}
		return ret;
	}

	public static String extract(String data, String[] key) {
		String[] properties = data.split("~");
		if (properties.length < 2) {
			return "Malformated encryption string!";
		}
		Matrix film = construct.hologram(properties[0].replace("E+", "E"));
		String[] indexList = properties[1].replace(" ", "").replace("[", "").replace("]", "").split(";");
		for (int i = 0; i <= indexList.length - 1; i++) {
			film = film.times(construct.hologram(key[Integer.parseInt(indexList[(indexList.length - 1 - i)])]).inverse());
		}
		double[][] original = film.getArray();
		String flat = strings.array2str(original);
		String[] line = flat.replace(" ", "").replace("[", "").replace("]", "").replace(";", ",").split(",");
		String ascii = "";
		for (int i = 0; i < line.length; i++) {
			int chrvalue = internalMath.safeLongToInt(Math.round(Double.parseDouble(line[i])));
			if (chrvalue > 1.1 && chrvalue < 500000) {
				String chr = String.valueOf(Character.toChars(chrvalue));
				ascii += chr;
			}
		}
		return ascii;
	}
}
