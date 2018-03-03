package DARTIS;

import java.lang.reflect.Array;

import Jama.Matrix;

public class crypt {

	
	public static String inject(String data, String[] key) {
		String[] temp = strings.stringequalsplit(data, 1);
		double[] insert[] = new double[temp.length/9+1][10];
		int n = 0; int l = 0;
		for (int i = 1; i<=temp.length-1; i++) {
			 int ascii = temp[i].charAt(0);
			insert[l][n] = ascii;
			n += 1;
			if(n > 9) {
				n = 0;
				l += 1;
				}
			}
			while(n < 9) {
			insert[l][n] = 1;
			n += 1;
			}
			String[] timestamp = time.getTimestamp(key.length);
					Matrix matrix = new Matrix(insert);
					Matrix nextLayer = construct.hologram(key[Integer.parseInt(timestamp[1])]);
					matrix = matrix.times(nextLayer);
					String id = timestamp[1]+";";
					for(int i=2;i<=Integer.parseInt(timestamp[0]);i++) {
						if (Integer.parseInt(timestamp[i]) < 1) {
							timestamp[i] = "1";
						}
							id = id+ timestamp[i]+";";
							//System.out.println("i="+i+"="+timestamp[i]);
					}
					for(int i=2;i<=Integer.parseInt(timestamp[0]);i++) {
						if (Integer.parseInt(timestamp[i]) < 1) {
							timestamp[i] = "1";
						}
							matrix = matrix.times(construct.hologram(key[Integer.parseInt(timestamp[i])]));
							//System.out.println(timestamp[i]);
					}
		String verify = crypt.extract(strings.array2str(matrix.getArray()).replace(" ", "").replace("[","").replace("]", "").replace(", ", ",")+"~"+id, key);
		while(verify.equals(data) == false) {
			timestamp = time.getTimestamp(key.length);
			matrix = new Matrix(insert);
			nextLayer = construct.hologram(key[Integer.parseInt(timestamp[1])]);
			matrix = matrix.times(nextLayer);
			id = timestamp[1]+";";
			for(int i=2;i<=Integer.parseInt(timestamp[0]);i++) {
				if (Integer.parseInt(timestamp[i]) < 1) {
					timestamp[i] = "1";
				}
					id = id+ timestamp[i]+";";
					//System.out.println("i="+i+"="+timestamp[i]);
			}
			for(int i=2;i<=Integer.parseInt(timestamp[0]);i++) {
				if (Integer.parseInt(timestamp[i]) < 1) {
					timestamp[i] = "1";
				}
					matrix = matrix.times(construct.hologram(key[Integer.parseInt(timestamp[i])]));
					//System.out.println(timestamp[i]);
			}
        verify = crypt.extract(strings.array2str(matrix.getArray()).replace(" ", "").replace("[","").replace("]", "").replace(", ", ",")+"~"+id, key);
		}
		return strings.array2str(matrix.getArray()).replace(" ", "").replace("[","").replace("]", "").replace(", ", ",")+"~"+id;
	}
	
	public static String extract(String data, String[] key) {
		String[] properties = data.split("~");
		Matrix film = construct.hologram(properties[0]);
		String[] indexList = properties[1].replace(" ", "").replace("[","").replace("]", "").split(";");
		for (int i=0;i<=indexList.length-1;i++) {
			//System.out.println("Inverting Matrix index: "+(indexList.length-1-i) +"="+Integer.parseInt(indexList[(indexList.length-1-i)]));
			film = film.times(construct.hologram(key[Integer.parseInt(indexList[(indexList.length-1-i)])]).inverse());
		}
		double[][] original = film.getArray();
		String flat = strings.array2str(original);
		String[] line = flat.replace(" ", "").replace("[","").replace("]", "").replace(";", ",").split(",");
		String ascii = "";
		for(int i = 0;i<=Array.getLength(line)-1;i++) {
			int chrvalue = internalMath.safeLongToInt(Math.round(Double.parseDouble(line[i])));
			if (chrvalue > 1.1 &&  chrvalue < 500000) {
				String chr = String.valueOf(Character.toChars(chrvalue));
			    //System.out.println("Index "+i+" of "+(Array.getLength(line)-1)+" encoded as "+line[i]+" Decoded to "+ chr);
				ascii += chr;
			}
		}
		return ascii;
	}
	
}
