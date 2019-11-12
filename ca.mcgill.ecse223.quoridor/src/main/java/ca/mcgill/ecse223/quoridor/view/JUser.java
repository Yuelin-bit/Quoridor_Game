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
		
		
		g.drawRect(8, 8,80, 16); 
		g.setColor(Color.gray);
		g.fillRect(8, 8, 536, 160);
		
		
		Color  wheat  = new Color(245, 245, 220);
	    g.setColor(wheat);
		g.fillOval(216, 12, 108, 108);
		
		g.setColor(Color.black);
		g.drawLine(232, 84, 310, 84);
		
		g.setColor(Color.DARK_GRAY);
		g.fill3DRect(11, 11, 194, 148, false);
		g.fill3DRect(344, 11, 194, 148, false);
		
		
		
		
}

}
