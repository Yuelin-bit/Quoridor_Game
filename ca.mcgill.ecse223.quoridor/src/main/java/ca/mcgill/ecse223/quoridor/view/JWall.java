package ca.mcgill.ecse223.quoridor.view;

import java.awt.Color;

import javax.swing.JPanel;

public class JWall extends JPanel{
	
	public JWall() {
	JPanel jwall = new JPanel();
	jwall.setBackground(Color.GRAY);
	jwall.setBounds(82, 85, 10, 100);
	jwall.setVisible(false);
	}
}
