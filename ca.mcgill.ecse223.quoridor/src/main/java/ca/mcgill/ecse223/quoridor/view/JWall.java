package ca.mcgill.ecse223.quoridor.view;
/**
 * 
 * Name : JWall
 * 
 * @author EveryOne
 * @version this is wall in view
 * @exception nothing
 *
 */

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class JWall extends JPanel{
	
	public JWall() {
	JPanel jwall = new JPanel();
	jwall.setBackground(Color.GRAY);
	jwall.setBounds(82, 85, 10, 100);
	jwall.setVisible(false);
	}
//public void paint(Graphics g){
//		
//		super.paint(g);
//		Color  brown0  = new Color(139, 69, 19);
//		g.setColor(brown0);
//		g.draw3DRect(1, 1, 9, 110, true);
//		
//		
//}
}
