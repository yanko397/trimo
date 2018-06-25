package trimo;

import javax.swing.JFrame;

public class MainMenu {

	public static void main(String[] args) {
		Menu menu = new Menu();
		
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setSize(398, 295);
		menu.setResizable(false);
		menu.setLocationRelativeTo(null);
		menu.setLayout(null);
		menu.setVisible(true);
	}
}
