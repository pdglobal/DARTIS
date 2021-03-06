package DARTIS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Jama.Matrix;

public class crypt {

	public static String inject(byte[] data, String[] key) {
		int array_size = (int) Math.ceil(data.length / 9);
		double insert[][] = new double[array_size + 1][10];
		int n = 0;
		int l = 0;
		for (int i = 0; i <= data.length - 1; i++) {
			if (data[i] == 0) {
				data[i] = (byte) 1337997331;
			}
			insert[l][n] = data[i];
			n += 1;
			if (n > 9) {
				n = 0;
				l += 1;
			}
		}
		while (l < array_size + 1) {
		while (n < 10) {
			insert[l][n] = 0;
			n += 1;
		}
		l += 1;
	}
		String[] timestamp = time.getTimestamp(key.length);
		Matrix matrix = new Matrix(insert);
		Matrix nextLayer = construct.hologram(key[Integer.parseInt(timestamp[1])]);
		matrix = matrix.times(nextLayer);
		String id = timestamp[1] + ";";
		for (int i = 2; i <= Integer.parseInt(timestamp[0]); i++) {
			if (Integer.parseInt(timestamp[i]) < 1) {
				timestamp[i] = "1";
			}
			id = id + timestamp[i] + ";";
			matrix = matrix.times(construct.hologram(key[Integer.parseInt(timestamp[i])]));
		}
		String ret = strings.array2str(matrix.getArray()) + "~" + id;
		byte[] verify = crypt.extract(ret, key);
		while (!Arrays.equals(verify, data)) {

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
			ret = strings.array2str(matrix.getArray()) + "~" + id;
			verify = crypt.extract(ret, key);
		}
		return ret;
	}

	public static byte[] extract(String data, String[] key) {
		String[] properties = data.split("~");
		if (properties.length < 2) {
			String retx = "Malformated encryption string!";
			return retx.toString().getBytes();
		}
		Matrix film = construct.hologram(properties[0].replace("E+", "E"));
		String[] indexList = properties[1].replace(" ", "").replace("[", "").replace("]", "").split(";");
		for (int i = 0; i <= indexList.length - 1; i++) {
			film = film
					.times(construct.hologram(key[Integer.parseInt(indexList[(indexList.length - 1 - i)])]).inverse());
		}

		return processMatrixToByte(film.getArray());
	}

	public static byte[] processMatrixToByte(double[][] arr) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < arr.length; i++) {
			//turn 2D array into 1D list
			for (int j = 0; j < arr[i].length; j++) {
				list.add(internalMath.safeLongToInt(Math.round(arr[i][j])));
			}
		}

		//find and remove any empty values
		int indexof = list.indexOf(0);

		while (indexof != -1) {
			list.remove(indexof);
			indexof = list.indexOf(0);
		}

		//find padding bytes and convert them to zeros
		indexof = list.indexOf((byte) 1337997331);

		while (indexof != -1) {
			list.set(indexof, 0);
			indexof = list.indexOf((byte) 1337997331);
		}
		
		// create 1D byte array
		byte[] vector = new byte[list.size()];
		//copy list to byte array
		for (int i = 0; i < vector.length; i++) {

			vector[i] = (byte) (list.get(i).intValue());
		}
		return vector;// return 1D byte array;
	}

}