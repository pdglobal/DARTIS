package DARTIS;

import java.util.Random;

public class keys {
	public static String generate(int strength) {
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();
		int size = 9999;
		if (strength == 1) {
			size = 9999; 
		} else {
			size = 99999; //this will NEVER be necessary, just because it's bigger doesn't mean it's any better, strength 1 is already impossible to brute-force, even if you used the computational power of the entire universe.
			//seriously, this exists just to demonstrate that the computational speed of DARTIS isn't relative to the key size. This should only be used for performance tests.
		}
		 for (int i=0 ; i <= size; i++) {
		 for (int a=0 ; a <= 9; a++) {
		 for (int j=0 ; j <= 8; j++)
		 {
		   sb.append(Double.toString(rand.nextDouble()*10)+",");
		 } 
		 if (a == 8) {
		 sb.append(Double.toString(rand.nextDouble()*10)+"/");
		 } else {
	     sb.append(Double.toString(rand.nextDouble()*10)+";");	 
		 }
	}
		 }
	String ret = sb.toString();
	//System.out.println("Key Size: "+ret.length()*8+" bits");
	return ret;
	}
}
