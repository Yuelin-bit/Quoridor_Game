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
		
		
		int a = 160;
		int b = 150;
		int tileLength = 50;
		int wallLength = 10;
		int c = (tileLength+wallLength)*9 + wallLength;
		int d = c;
		
		int length=b+wallLength;
		
		g.drawRect(a, b,c, d); //外圈
		//g.drawRect(70, 60, 480, 540);
		
		for(int j=0;j<9;j++) {
			int ini70 = a+wallLength;
		for(int i=0;i<9;i++){
			g.drawLine(ini70, length, ini70+tileLength, length);
			ini70 = ini70 + tileLength + wallLength;    
			}
		length = length + tileLength + wallLength;
		}
		
		length=b + tileLength+wallLength;
		for(int j=0;j<9;j++) {
			int ini70 = a+wallLength;			
		for(int i=0;i<9;i++){
			g.drawLine(ini70, length, ini70+tileLength, length);
			ini70 = ini70 + tileLength+wallLength;    
			}
		length = length + tileLength+wallLength;
		}
		
		int inix = a+wallLength;
		int iniy = b+wallLength;
		for(int j=0;j<9;j++) {
		iniy = b+wallLength;
		for(int i=0;i<9;i++){
			g.drawLine(inix, iniy, inix, iniy+tileLength);
			iniy = iniy + tileLength+wallLength;    
			}
		inix  = inix + tileLength+wallLength;
		}
		
		inix = a+tileLength+wallLength;
		for(int j=0;j<9;j++) {
		iniy = b+wallLength;
		for(int i=0;i<9;i++){
			g.drawLine(inix, iniy, inix, iniy+tileLength);
			iniy = iniy + tileLength+wallLength;    
			}
		inix  = inix + tileLength+wallLength;
		}

	}
//	public void paintComponent(Graphics g)
//	{
//		g.fill
//	}
}

