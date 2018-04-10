package DARTIS;

import Jama.Matrix;

public class construct {

	public static Matrix hologram(String blueprint) {
		String[] baseArray = blueprint.split(";");
		double[][] cluster = new double[baseArray.length][10];
		for (int i = 0; i <= baseArray.length - 1; i++) {
			String[] temp = baseArray[i].split(",");
			for (int n = 0; n <= temp.length - 1; n++) {
				cluster[i][n] = Double.parseDouble(temp[n]);
			}
		}
		Matrix ret = new Matrix(cluster);
		return ret;
	}

	public static String[] load(String key) {
		String[] kfb = key.split("/", -1);
		return kfb;
	}
}
