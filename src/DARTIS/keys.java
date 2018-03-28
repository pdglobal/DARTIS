package DARTIS;

import java.util.Random;

public class keys {
	public static String generate() {
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();
		int size = 9999;
		 for (int i=0 ; i <= size; i++) {
		 for (int a=0 ; a <= 9; a++) {
		 for (int j=0 ; j <= 8; j++)
		 {
		   sb.append(Double.toString(rand.nextDouble()*50)+",");
		 } 
		 if (a == 8) {
		 sb.append(Double.toString(rand.nextDouble()*50)+"/");
		 } else {
	     sb.append(Double.toString(rand.nextDouble()*50)+";");	 
		 }
	}
		 }
	String ret = sb.toString();
	return ret;
	}
}
