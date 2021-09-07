package semiproject;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;




public class shuffleGIF extends JFrame{
	JLabel jlb;
	

	shuffleGIF(String path) {
		jlb = new JLabel(new ImageIcon(path));
		jlb.setBounds(0, 0, 500, 480);
		add(jlb);


		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 480);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new shuffleGIF("src\\images\\shuffle.gif");
	}

}
