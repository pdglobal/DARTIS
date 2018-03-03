package DARTIS;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class time {
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
	  String[] ERA =  strings.stringequalsplit(String.valueOf(binary), 4);
	  int ERACompressed = Integer.parseInt(ERA[1])+Integer.parseInt(ERA[2]);
	return strings.stringequalsplit(String.valueOf(datetime)+String.valueOf(ERACompressed)+Long.toString(estimatedTime), String.valueOf(length).length()-1);
}
}
