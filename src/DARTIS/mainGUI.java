package DARTIS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

public class mainGUI {

	private JFrame frame;
	private static JTextArea textArea_1;
	private static JTextArea textArea;
	private static JLabel label;
	private static JLabel lblhz;
	private static JLabel datasize;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainGUI window = new mainGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 884, 802);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setForeground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 868, 826);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setRows(1);
		textArea.setColumns(1);
		textArea.setEnabled(false);
		textArea.setBackground(new Color(0, 0, 0));
		textArea.setForeground(new Color(255, 255, 255));
		textArea.setFont(new Font("Cambria Math", Font.BOLD, 12));
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBounds(0, 230, 868, 377);
		panel.add(textArea);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBackground(new Color(0, 0, 255));
		separator.setBounds(0, 612, 868, 2);
		panel.add(separator);
		
		textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Cambria Math", Font.BOLD, 12));
		textArea_1.setLineWrap(true);
		textArea_1.setForeground(new Color(255, 255, 255));
		textArea_1.setBackground(new Color(0, 0, 0));
		textArea_1.setEnabled(false);
		textArea_1.setEditable(false);
		textArea_1.setBounds(0, 633, 868, 128);
		panel.add(textArea_1);
		
		JLabel lblDecodedData = new JLabel("Decoded Data:");
		lblDecodedData.setBackground(new Color(0, 0, 0));
		lblDecodedData.setFont(new Font("Cambria Math", Font.BOLD, 12));
		lblDecodedData.setForeground(new Color(255, 255, 255));
		lblDecodedData.setBounds(10, 617, 107, 14);
		panel.add(lblDecodedData);
		
		JLabel lblEncodedData = new JLabel("Encoded Data:");
		lblEncodedData.setFont(new Font("Cambria Math", Font.BOLD, 12));
		lblEncodedData.setForeground(new Color(255, 255, 255));
		lblEncodedData.setBounds(10, 217, 107, 14);
		panel.add(lblEncodedData);
		
		JLabel lblDartisDebuggerAnd = new JLabel("DARTIS DEBUGGER AND PERFORMANCE ANALYZER");
		lblDartisDebuggerAnd.setHorizontalAlignment(SwingConstants.CENTER);
		lblDartisDebuggerAnd.setForeground(new Color(255, 255, 255));
		lblDartisDebuggerAnd.setBackground(new Color(0, 0, 0));
		lblDartisDebuggerAnd.setFont(new Font("Cambria Math", Font.BOLD, 26));
		lblDartisDebuggerAnd.setBounds(0, 0, 868, 57);
		panel.add(lblDartisDebuggerAnd);
		
		JLabel lbldatasize = new JLabel("Data Size:");
		lbldatasize.setForeground(new Color(255, 255, 255));
		lbldatasize.setBackground(new Color(0, 0, 0));
		lbldatasize.setFont(new Font("Cambria Math", Font.BOLD, 14));
		lbldatasize.setBounds(567, 84, 69, 31);
		panel.add(lbldatasize);
		
		lblhz = new JLabel("0Hz");
		lblhz.setForeground(new Color(0, 255, 0));
		lblhz.setBackground(new Color(0, 0, 0));
		lblhz.setHorizontalAlignment(SwingConstants.RIGHT);
		lblhz.setFont(new Font("Cambria Math", Font.BOLD, 14));
		lblhz.setBounds(692, 126, 93, 31);
		panel.add(lblhz);
		
		JLabel lblCalculationAccuracy = new JLabel("Calculation Accuracy:");
		lblCalculationAccuracy.setForeground(new Color(255, 255, 255));
		lblCalculationAccuracy.setBackground(new Color(0, 0, 0));
		lblCalculationAccuracy.setFont(new Font("Cambria Math", Font.BOLD, 14));
		lblCalculationAccuracy.setBounds(567, 175, 158, 31);
		panel.add(lblCalculationAccuracy);
		
		label = new JLabel("100%");
		label.setForeground(new Color(0, 255, 0));
		label.setBackground(new Color(0, 0, 0));
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Cambria Math", Font.BOLD, 14));
		label.setBounds(721, 180, 64, 21);
		panel.add(label);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(0, 0, 0));
		separator_1.setBackground(new Color(0, 0, 255));
		separator_1.setBounds(0, 68, 868, 2);
		panel.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(0, 0, 0));
		separator_2.setBackground(new Color(0, 0, 255));
		separator_2.setBounds(0, 212, 868, 2);
		panel.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(new Color(0, 0, 0));
		separator_3.setBackground(new Color(0, 0, 255));
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(555, 68, 2, 142);
		panel.add(separator_3);
		
		JLabel label_1 = new JLabel("Operation Speed:");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Cambria Math", Font.BOLD, 14));
		label_1.setBackground(Color.BLACK);
		label_1.setBounds(567, 126, 128, 31);
		panel.add(label_1);
		
		datasize = new JLabel("0 Bytes");
		datasize.setHorizontalAlignment(SwingConstants.RIGHT);
		datasize.setForeground(Color.GREEN);
		datasize.setFont(new Font("Cambria Math", Font.BOLD, 14));
		datasize.setBackground(Color.BLACK);
		datasize.setBounds(646, 84, 139, 31);
		panel.add(datasize);
	}
	public static JTextArea debugDecode() {
		return textArea_1;
	}
	public static JTextArea encodedData() {
		return textArea;
	}
	public static JLabel accuracyLabel() {
		return label;
	}
	public static JLabel ops() {
		return lblhz;
	}
	public static JLabel getDatasize() {
		return datasize;
	}
}
