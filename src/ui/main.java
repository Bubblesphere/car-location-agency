package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class main extends JFrame {
	private static final long serialVersionUID = 1L;
	public main() {
		setBounds(100, 100, 965, 744);
		JLabel lblHelloWorld = new JLabel("Hello World!");
		getContentPane().add(lblHelloWorld, BorderLayout.NORTH);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}