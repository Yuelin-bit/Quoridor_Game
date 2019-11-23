package ca.mcgill.ecse223.quoridor.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class JUser4 extends JPanel {

	/**
	 * Create the panel.
	 */
	public JUser4() {

	}
public void paint(Graphics g){
		
		super.paint(g);
		
		
		//g.drawRect(8, 8,80, 16); 
		g.setColor(Color.gray);
		g.fillRect(8, 8, 796, 131);//up
		g.fillRect(8, 8, 134, 776);//left
		g.fillRect(8, 655, 799, 130);//down
		g.fillRect(657, 8, 150, 770);//right
		
		
		Color  wheat  = new Color(245, 245, 220);
		
	    
	    g.setColor(Color.RED);
	    g.setColor(wheat);
	    g.fillRect(144, 140, 512, 513);
	    

//		
		g.setColor(Color.DARK_GRAY);
		g.fill3DRect(9, 9, 132, 132, false);//upleft
		g.fill3DRect(9, 655, 132, 130, false);//downleft
		
		g.fill3DRect(658, 9, 148, 132, false);//upright
		g.fill3DRect(658, 655, 148, 130, false);//downright

		

		
		
		
		
}
}
