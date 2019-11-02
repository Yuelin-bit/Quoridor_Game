package ca.mcgill.ecse223.quoridor.view;

import java.awt.Color;

import javax.swing.JPanel;

public class Wall extends JPanel{
	
	public Wall() {
	JPanel wall = new JPanel();
	wall.setBackground(Color.GRAY);
	wall.setBounds(82, 85, 10, 100);
	wall.setVisible(false);
	}
}
