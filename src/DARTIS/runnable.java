package DARTIS;

import java.net.UnknownHostException;
import java.util.Arrays;

public class runnable {
	
	public static int operations = 0;
    public static int success = 0;
    public static int accuracy = 100;
    public static String hz = "0";
    public static String originalString = "Initializing startup data";
	
    public static void main(String[] args) throws UnknownHostException {
        mainGUI.main(args);
			originalString = "Executing DARTIS operations test on: "+java.net.InetAddress.getLocalHost()+" running on JRE "+System.getProperty("java.version");
			long startTimekey = System.currentTimeMillis();
			String[] key = construct.load(keys.generate());
			System.out.println("Time taken to execute function: ".concat(String.valueOf((System.currentTimeMillis()-startTimekey))).concat(" miliseconds"));
        long startTime = System.currentTimeMillis();
        mainGUI.debugDecode().setText(originalString);
        mainGUI.getDatasize().setText(String.valueOf(originalString.length())+" Bytes");
        while(true) {
        	String encodedText = crypt.inject(originalString.toString().getBytes(), key);
        	mainGUI.encodedData().setText(encodedText);
        	operations += 1;
        	byte[] returnData = crypt.extract(encodedText, key);
        	String retstring = new String(returnData);
        	mainGUI.debugDecode().setText(retstring);
        	operations += 1;
        	if ((System.currentTimeMillis()-startTime)/1000 > 0) {
        	hz = String.valueOf((operations)/((System.currentTimeMillis()-startTime)/1000));
        	}
        	mainGUI.ops().setText(hz+"Hz");
        }
    }
}