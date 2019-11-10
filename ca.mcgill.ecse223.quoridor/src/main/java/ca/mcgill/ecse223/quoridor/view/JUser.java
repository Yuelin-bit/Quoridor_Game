package ca.mcgill.ecse223.quoridor.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class JUser extends JPanel {

	/**
	 * Create the panel.
	 */
	public JUser() {

	}
public void paint(Graphics g){
		
		super.paint(g);
		
		
		g.drawRect(10, 10,100, 20); 
		g.setColor(Color.gray);
		g.fillRect(10, 10, 670, 200);
		
		
		Color  wheat  = new Color(245, 245, 220);
	    g.setColor(wheat);
		g.fillOval(270, 15, 135, 135);
		
		g.setColor(Color.black);
		g.drawLine(290, 105, 388, 105);
		
		
		
		
}

}
