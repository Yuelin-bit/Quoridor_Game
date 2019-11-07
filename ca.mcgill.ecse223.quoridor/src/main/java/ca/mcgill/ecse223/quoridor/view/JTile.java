package ca.mcgill.ecse223.quoridor.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class JTile extends JPanel {

	/**
	 * Create the panel.
	 */
	public JTile() {

	}
public void paint(Graphics g){
		
		super.paint(g);
		Font f=new Font("微软雅黑",Font.BOLD,30);
		g.setFont(f);
		int length=60;
		
		
		g.drawRect(60, 50,550, 560); //外圈
		//g.drawRect(70, 60, 480, 540);
		
		for(int j=0;j<9;j++) {
			int ini70 = 70;
		for(int i=0;i<9;i++){
			g.drawLine(ini70, length, ini70+50, length);
			ini70 = ini70 + 60;    
			}
		length = length + 60;
		}
		
		length=50;
		for(int j=0;j<10;j++) {
			int ini70 = 70;			
		for(int i=0;i<9;i++){
			g.drawLine(ini70, length, ini70+50, length);
			ini70 = ini70 + 60;    
			}
		length = length + 60;
		}
		
		int ini70 = 0;
		for(int j=0;j<10;j++) {
		length=60;
	    ini70  = ini70 + 60;
		for(int i=0;i<9;i++){
			g.drawLine(ini70, length, ini70, length+50);
			length = length + 60;    
			}
		}
		
		ini70 = 10;
		for(int j=0;j<10;j++) {
		length=60;
	    ini70  = ini70 + 60;
		for(int i=0;i<9;i++){
			g.drawLine(ini70, length, ini70, length+50);
			length = length + 60;    
			}
		}
		//Graphics2D g2d = (Graphics2D) g;
        //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //g2d.fillArc(200,500,100,200,0,100);
		//g.fillRect(70, 60, 50, 50);
		//g.setColor(Color.GREEN);
	}
//	public void paintComponent(Graphics g)
//	{
//		g.fill
//	}
}

