package DARTIS;

import java.net.UnknownHostException;

public class runnable {

	public static int operations = 0;
	public static int success = 0;
	public static int accuracy = 100;
	public static String hz = "0";
	public static String originalString = "Initializing startup data";

	public static void main(String[] args) throws UnknownHostException {
		mainGUI.main(args);
		originalString = "Executing DARTIS operations test on: " + java.net.InetAddress.getLocalHost()
				+ " running on JRE " + System.getProperty("java.version");
		String[] key = construct.load(keys.generate());
		long startTime = System.currentTimeMillis();
		mainGUI.debugDecode().setText(originalString);
		mainGUI.getDatasize().setText(String.valueOf(originalString.length()) + " Bytes");
		while (true) {
			String encodedText = crypt.inject(originalString, key);
			mainGUI.encodedData().setText(encodedText);
			operations += 1;
			String returnData = crypt.extract(encodedText, key);
			mainGUI.debugDecode().setText(returnData);
			operations += 1;
			if ((System.currentTimeMillis() - startTime) / 1000 > 0) {
				hz = String.valueOf((operations) / ((System.currentTimeMillis() - startTime) / 1000));
			}
			mainGUI.ops().setText(hz + "Hz");
		}
	}
}