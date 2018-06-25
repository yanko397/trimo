package trimo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Menu extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private Image image;
	private JButton start;
	private JButton settings;
	private JButton info;
	private JButton exit;
	
	public Menu(){
		super("Menü");
		ImageIcon icon = new ImageIcon("res/menu.png");
		image = icon.getImage();
		buttons();
		repaint();
	}

	public void start(){
		Trimo game = new Trimo();

		game.frame.setResizable(false);
		game.frame.setTitle(game.title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();
	}
	
	public void buttons(){
		
		start = new JButton("Start");
		start.setBounds(126, 70, 140, 40);
		start.addActionListener(this);
		add(start);

		settings = new JButton("Einstellungen");
		settings.setBounds(126, 125, 140, 30);
		settings.addActionListener(this);
		add(settings);
		
		info = new JButton("Credits");
		info.setBounds(126, 170, 140, 30);
		info.addActionListener(this);
		add(info);
		
		exit = new JButton("Schließen");
		exit.setBounds(126, 215, 140, 30);
		exit.addActionListener(this);
		add(exit);
	}

	public void paint(Graphics g){
		super.paint(g);
		Graphics2D f2 = (Graphics2D)g;
		f2.drawImage(image,  3,  26,  null);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == start){
			setVisible(false);
			start();
		}
		
		if(e.getSource() == settings){

		}

		if(e.getSource() == info){
			Object[] options = {"goil!"};
			JOptionPane.showOptionDialog(null, "Skript: Yanko397\nGrafiken: Yanko397, 3nte\nBegonnen: 5.11.16", "Credits", JOptionPane.DEFAULT_OPTION , JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		}

		if(e.getSource() == exit){
			System.exit(0);
		}
	}
}
