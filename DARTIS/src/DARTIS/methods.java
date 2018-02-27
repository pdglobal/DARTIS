package DARTIS;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import Jama.Matrix;

public class methods {

	public static class strings {

		public static String[] stringequalsplit(String text,int length) {
			int arraysize = 0;
			if (length < 2) {
				arraysize = text.length()+1;
			} else {
				arraysize = Math.round(text.length()/length)+(length/2);
			}
			String[] list = new String[arraysize];
			//System.out.println("Array size = "+arraysize);
			int index = 0;
			int realindex = 1;
			while (index<text.length()) {
			    list[realindex] = text.substring(index, Math.min(index+length,text.length()));
			    //System.out.println(index+" - "+Math.min(index+length,text.length())+" = "+text.substring(index, Math.min(index+length,text.length())));
			    index=index+length;
			    realindex+=1;
			}
			list[0] = String.valueOf(realindex-1);
		return list;
		}
		
		public static String array2str(double[] array[]) {
		String lineSeparator = ";";
		StringBuilder sb = new StringBuilder();

		for (double[] row : array) {
		    sb.append(Arrays.toString(row).replace(" ", "").replace("[","").replace("]", "")+lineSeparator);
		}

		String result = sb.toString();
		return result;
		}
	}
	
	public static class construct {

		public static Matrix hologram(String blueprint) {
			String[] baseArray = blueprint.split(";");
			double[][] cluster = new double[baseArray.length][10];
			for (int i = 0; i <= baseArray.length-1; i++) { 
			    String[] temp = baseArray[i].split(",");
			    for (int n = 0; n <= temp.length-1; n++) {
			    	cluster[i][n] = Double.parseDouble(temp[n]);
			    }
			}
	    Matrix ret = new Matrix(cluster);
	    return ret;
		}
	}
	
	public static class crypt {
		
		public static String inject(String data, String[] key) {
			String[] temp = methods.strings.stringequalsplit(data, 1);
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
				String[] timestamp = methods.time.getTimestamp(key.length);
						Matrix matrix = new Matrix(insert);
						Matrix nextLayer = methods.construct.hologram(key[Integer.parseInt(timestamp[1])]);
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
								matrix = matrix.times(methods.construct.hologram(key[Integer.parseInt(timestamp[i])]));
								//System.out.println(timestamp[i]);
						}
			String verify = methods.crypt.extract(methods.strings.array2str(matrix.getArray()).replace(" ", "").replace("[","").replace("]", "").replace(", ", ",")+"~"+id, key);
			while(verify.equals(data) == false) {
				timestamp = methods.time.getTimestamp(key.length);
				matrix = new Matrix(insert);
				nextLayer = methods.construct.hologram(key[Integer.parseInt(timestamp[1])]);
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
						matrix = matrix.times(methods.construct.hologram(key[Integer.parseInt(timestamp[i])]));
						//System.out.println(timestamp[i]);
				}
	        verify = methods.crypt.extract(methods.strings.array2str(matrix.getArray()).replace(" ", "").replace("[","").replace("]", "").replace(", ", ",")+"~"+id, key);
			}
			return methods.strings.array2str(matrix.getArray()).replace(" ", "").replace("[","").replace("]", "").replace(", ", ",")+"~"+id;
		}
		
		public static String extract(String data, String[] key) {
			String[] properties = data.split("~");
			Matrix film = methods.construct.hologram(properties[0]);
			String[] indexList = properties[1].replace(" ", "").replace("[","").replace("]", "").split(";");
			for (int i=0;i<=indexList.length-1;i++) {
				//System.out.println("Inverting Matrix index: "+(indexList.length-1-i) +"="+Integer.parseInt(indexList[(indexList.length-1-i)]));
				film = film.times(methods.construct.hologram(key[Integer.parseInt(indexList[(indexList.length-1-i)])]).inverse());
			}
			double[][] original = film.getArray();
			String flat = methods.strings.array2str(original);
			String[] line = flat.replace(" ", "").replace("[","").replace("]", "").replace(";", ",").split(",");
			String ascii = "";
			for(int i = 0;i<=Array.getLength(line)-1;i++) {
				int chrvalue = methods.internalMath.safeLongToInt(Math.round(Double.parseDouble(line[i])));
				if (chrvalue > 1.1 &&  chrvalue < 500000) {
					String chr = String.valueOf(Character.toChars(chrvalue));
				    //System.out.println("Index "+i+" of "+(Array.getLength(line)-1)+" encoded as "+line[i]+" Decoded to "+ chr);
					ascii += chr;
				}
			}
			return ascii;
		}
	}
	
	public static class key {
		
		public static String[] load(String key) {
			String[] kfb = key.split("/",-1);
			return kfb;
		}
		
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
	
	public static class internalMath {
		
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
	
	public static class time {
		    public static String[] getTimestamp(int length) {
			long estimatedTime = System.nanoTime();
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy");
			SimpleDateFormat dateFormatter2 = new SimpleDateFormat("mmss");
			SimpleDateFormat dateFormatter3 = new SimpleDateFormat("hh");
			Date now = new Date();
			String datetime = String.valueOf((Calendar.DAY_OF_YEAR)).concat(String.valueOf(Integer.parseInt(dateFormatter.format(now))+Integer.parseInt(dateFormatter2.format(now))+Integer.parseInt(dateFormatter3.format(now))));
			String datetime_era = String.valueOf((Calendar.ERA));
			  String s = datetime_era;
			  byte[] bytes = s.getBytes();
			  StringBuilder binary = new StringBuilder();
			  for (byte b : bytes)
			  {
			     int val = b;
			     for (int i = 0; i < 8; i++)
			     {
			        binary.append((val & 128) == 0 ? 0 : 1);
			        val <<= 1;
			     }
			  }
			//System.out.println("DateTime = "+datetime+binary+" + "+estimatedTime+" = "+String.valueOf(datetime)+String.valueOf(binary)+Long.toString(estimatedTime));
			  String[] ERA =  methods.strings.stringequalsplit(String.valueOf(binary), 4);
			  int ERACompressed = Integer.parseInt(ERA[1])+Integer.parseInt(ERA[2]);
			return methods.strings.stringequalsplit(String.valueOf(datetime)+String.valueOf(ERACompressed)+Long.toString(estimatedTime), String.valueOf(length).length()-1);
		}
	}
	
	public static class CharUtils {
		
		public static int CharToASCII(final char character){
			return (int)character;
		}
		public static char ASCIIToChar(final int ascii){
			return (char)ascii;
		}
	}
	
	public class ArrayHandle {
	    public Object[] reverse(Object[] arr) {
	        List<Object> list = Arrays.asList(arr);
	        Collections.reverse(list);
	        return list.toArray();
	    }
	}
}