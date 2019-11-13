package ca.mcgill.ecse223.quoridor.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JButton;

public class JTile extends JPanel {

	/**
	 * Create the panel.
	 */
	public JTile() {
		setLayout(null);
		setBounds(0,0,560,800);
		int sideLength = 47;
		int sideLengthPlus = 56;
		int referX = 26;
		int referY = 194;
		
		
		JButton NewButton11 = new JButton("");
		NewButton11.setBounds(referX,referY,sideLength,sideLength);
		add(NewButton11);
		JButton NewButton12 = new JButton("");
		NewButton12.setBounds(referX+sideLengthPlus*1,referY,sideLength,sideLength);
		add(NewButton12);
		JButton NewButton13 = new JButton("");
		NewButton13.setBounds(referX+sideLengthPlus*2,referY,sideLength,sideLength);
		add(NewButton13);
		JButton NewButton14 = new JButton("");
		NewButton14.setBounds(referX+sideLengthPlus*3,referY,sideLength,sideLength);
		add(NewButton14);
		JButton NewButton15 = new JButton("");
		NewButton15.setBounds(referX+sideLengthPlus*4,referY,sideLength,sideLength);
		add(NewButton15);
		JButton NewButton16 = new JButton("");
		NewButton16.setBounds(referX+sideLengthPlus*5,referY,sideLength,sideLength);
		add(NewButton16);
		JButton NewButton17 = new JButton("");
		NewButton17.setBounds(referX+sideLengthPlus*6,referY,sideLength,sideLength);
		add(NewButton17);
		JButton NewButton18 = new JButton("");
		NewButton18.setBounds(referX+sideLengthPlus*7,referY,sideLength,sideLength);
		add(NewButton18);
		JButton NewButton19 = new JButton("");
		NewButton19.setBounds(referX+sideLengthPlus*8,referY,sideLength,sideLength);
		add(NewButton19);
		
		
		JButton NewButton21 = new JButton("");
		NewButton21.setBounds(referX,referY+sideLengthPlus*1,sideLength,sideLength);
		add(NewButton21);
		JButton NewButton22 = new JButton("");
		NewButton22.setBounds(referX+sideLengthPlus*1,referY+sideLengthPlus*1,sideLength,sideLength);
		add(NewButton22);
		JButton NewButton23 = new JButton("");
		NewButton23.setBounds(referX+sideLengthPlus*2,referY+sideLengthPlus*1,sideLength,sideLength);
		add(NewButton23);
		JButton NewButton24 = new JButton("");
		NewButton24.setBounds(referX+sideLengthPlus*3,referY+sideLengthPlus*1,sideLength,sideLength);
		add(NewButton24);
		JButton NewButton25 = new JButton("");
		NewButton25.setBounds(referX+sideLengthPlus*4,referY+sideLengthPlus*1,sideLength,sideLength);
		add(NewButton25);
		JButton NewButton26 = new JButton("");
		NewButton26.setBounds(referX+sideLengthPlus*5,referY+sideLengthPlus*1,sideLength,sideLength);
		add(NewButton26);
		JButton NewButton27 = new JButton("");
		NewButton27.setBounds(referX+sideLengthPlus*6,referY+sideLengthPlus*1,sideLength,sideLength);
		add(NewButton27);
		JButton NewButton28 = new JButton("");
		NewButton28.setBounds(referX+sideLengthPlus*7,referY+sideLengthPlus*1,sideLength,sideLength);
		add(NewButton28);
		JButton NewButton29 = new JButton("");
		NewButton29.setBounds(referX+sideLengthPlus*8,referY+sideLengthPlus*1,sideLength,sideLength);
		add(NewButton29);
		
		
		
		JButton NewButton31 = new JButton("");
		NewButton31.setBounds(referX,referY+sideLengthPlus*2,sideLength,sideLength);
		add(NewButton31);
		JButton NewButton32 = new JButton("");
		NewButton32.setBounds(referX+sideLengthPlus*1,referY+sideLengthPlus*2,sideLength,sideLength);
		add(NewButton32);
		JButton NewButton33 = new JButton("");
		NewButton33.setBounds(referX+sideLengthPlus*2,referY+sideLengthPlus*2,sideLength,sideLength);
		add(NewButton33);
		JButton NewButton34 = new JButton("");
		NewButton34.setBounds(referX+sideLengthPlus*3,referY+sideLengthPlus*2,sideLength,sideLength);
		add(NewButton34);
		JButton NewButton35 = new JButton("");
		NewButton35.setBounds(referX+sideLengthPlus*4,referY+sideLengthPlus*2,sideLength,sideLength);
		add(NewButton35);
		JButton NewButton36 = new JButton("");
		NewButton36.setBounds(referX+sideLengthPlus*5,referY+sideLengthPlus*2,sideLength,sideLength);
		add(NewButton36);
		JButton NewButton37 = new JButton("");
		NewButton37.setBounds(referX+sideLengthPlus*6,referY+sideLengthPlus*2,sideLength,sideLength);
		add(NewButton37);
		JButton NewButton38 = new JButton("");
		NewButton38.setBounds(referX+sideLengthPlus*7,referY+sideLengthPlus*2,sideLength,sideLength);
		add(NewButton38);
		JButton NewButton39 = new JButton("");
		NewButton39.setBounds(referX+sideLengthPlus*8,referY+sideLengthPlus*2,sideLength,sideLength);
		add(NewButton39);
		
		
		
		
		
		JButton NewButton41 = new JButton("");
		NewButton41.setBounds(referX,referY+sideLengthPlus*3,sideLength,sideLength);
		add(NewButton41);
		JButton NewButton42 = new JButton("");
		NewButton42.setBounds(referX+sideLengthPlus*1,referY+sideLengthPlus*3,sideLength,sideLength);
		add(NewButton42);
		JButton NewButton43 = new JButton("");
		NewButton43.setBounds(referX+sideLengthPlus*2,referY+sideLengthPlus*3,sideLength,sideLength);
		add(NewButton43);
		JButton NewButton44 = new JButton("");
		NewButton44.setBounds(referX+sideLengthPlus*3,referY+sideLengthPlus*3,sideLength,sideLength);
		add(NewButton44);
		JButton NewButton45 = new JButton("");
		NewButton45.setBounds(referX+sideLengthPlus*4,referY+sideLengthPlus*3,sideLength,sideLength);
		add(NewButton45);
		JButton NewButton46 = new JButton("");
		NewButton46.setBounds(referX+sideLengthPlus*5,referY+sideLengthPlus*3,sideLength,sideLength);
		add(NewButton46);
		JButton NewButton47 = new JButton("");
		NewButton47.setBounds(referX+sideLengthPlus*6,referY+sideLengthPlus*3,sideLength,sideLength);
		add(NewButton47);
		JButton NewButton48 = new JButton("");
		NewButton48.setBounds(referX+sideLengthPlus*7,referY+sideLengthPlus*3,sideLength,sideLength);
		add(NewButton48);
		JButton NewButton49 = new JButton("");
		NewButton49.setBounds(referX+sideLengthPlus*8,referY+sideLengthPlus*3,sideLength,sideLength);
		add(NewButton49);
		
		
		JButton NewButton51 = new JButton("");
		NewButton51.setBounds(referX,referY+sideLengthPlus*4,sideLength,sideLength);
		add(NewButton51);
		JButton NewButton52 = new JButton("");
		NewButton52.setBounds(referX+sideLengthPlus*1,referY+sideLengthPlus*4,sideLength,sideLength);
		add(NewButton52);
		JButton NewButton53 = new JButton("");
		NewButton53.setBounds(referX+sideLengthPlus*2,referY+sideLengthPlus*4,sideLength,sideLength);
		add(NewButton53);
		JButton NewButton54 = new JButton("");
		NewButton54.setBounds(referX+sideLengthPlus*3,referY+sideLengthPlus*4,sideLength,sideLength);
		add(NewButton54);
		JButton NewButton55 = new JButton("");
		NewButton55.setBounds(referX+sideLengthPlus*4,referY+sideLengthPlus*4,sideLength,sideLength);
		add(NewButton55);
		JButton NewButton56 = new JButton("");
		NewButton56.setBounds(referX+sideLengthPlus*5,referY+sideLengthPlus*4,sideLength,sideLength);
		add(NewButton56);
		JButton NewButton57 = new JButton("");
		NewButton57.setBounds(referX+sideLengthPlus*6,referY+sideLengthPlus*4,sideLength,sideLength);
		add(NewButton57);
		JButton NewButton58 = new JButton("");
		NewButton58.setBounds(referX+sideLengthPlus*7,referY+sideLengthPlus*4,sideLength,sideLength);
		add(NewButton58);
		JButton NewButton59 = new JButton("");
		NewButton59.setBounds(referX+sideLengthPlus*8,referY+sideLengthPlus*4,sideLength,sideLength);
		add(NewButton59);
		
		
		
		JButton NewButton61 = new JButton("");
		NewButton61.setBounds(referX,referY+sideLengthPlus*5,sideLength,sideLength);
		add(NewButton61);
		JButton NewButton62 = new JButton("");
		NewButton62.setBounds(referX+sideLengthPlus*1,referY+sideLengthPlus*5,sideLength,sideLength);
		add(NewButton62);
		JButton NewButton63 = new JButton("");
		NewButton63.setBounds(referX+sideLengthPlus*2,referY+sideLengthPlus*5,sideLength,sideLength);
		add(NewButton63);
		JButton NewButton64 = new JButton("");
		NewButton64.setBounds(referX+sideLengthPlus*3,referY+sideLengthPlus*5,sideLength,sideLength);
		add(NewButton64);
		JButton NewButton65 = new JButton("");
		NewButton65.setBounds(referX+sideLengthPlus*4,referY+sideLengthPlus*5,sideLength,sideLength);
		add(NewButton65);
		JButton NewButton66 = new JButton("");
		NewButton66.setBounds(referX+sideLengthPlus*5,referY+sideLengthPlus*5,sideLength,sideLength);
		add(NewButton66);
		JButton NewButton67 = new JButton("");
		NewButton67.setBounds(referX+sideLengthPlus*6,referY+sideLengthPlus*5,sideLength,sideLength);
		add(NewButton67);
		JButton NewButton68 = new JButton("");
		NewButton68.setBounds(referX+sideLengthPlus*7,referY+sideLengthPlus*5,sideLength,sideLength);
		add(NewButton68);
		JButton NewButton69 = new JButton("");
		NewButton69.setBounds(referX+sideLengthPlus*8,referY+sideLengthPlus*5,sideLength,sideLength);
		add(NewButton69);
		

		JButton NewButton71 = new JButton("");
		NewButton71.setBounds(referX,referY+sideLengthPlus*6,sideLength,sideLength);
		add(NewButton71);
		JButton NewButton72 = new JButton("");
		NewButton72.setBounds(referX+sideLengthPlus*1,referY+sideLengthPlus*6,sideLength,sideLength);
		add(NewButton72);
		JButton NewButton73 = new JButton("");
		NewButton73.setBounds(referX+sideLengthPlus*2,referY+sideLengthPlus*6,sideLength,sideLength);
		add(NewButton73);
		JButton NewButton74 = new JButton("");
		NewButton74.setBounds(referX+sideLengthPlus*3,referY+sideLengthPlus*6,sideLength,sideLength);
		add(NewButton74);
		JButton NewButton75 = new JButton("");
		NewButton75.setBounds(referX+sideLengthPlus*4,referY+sideLengthPlus*6,sideLength,sideLength);
		add(NewButton75);
		JButton NewButton76 = new JButton("");
		NewButton76.setBounds(referX+sideLengthPlus*5,referY+sideLengthPlus*6,sideLength,sideLength);
		add(NewButton76);
		JButton NewButton77 = new JButton("");
		NewButton77.setBounds(referX+sideLengthPlus*6,referY+sideLengthPlus*6,sideLength,sideLength);
		add(NewButton77);
		JButton NewButton78 = new JButton("");
		NewButton78.setBounds(referX+sideLengthPlus*7,referY+sideLengthPlus*6,sideLength,sideLength);
		add(NewButton78);
		JButton NewButton79 = new JButton("");
		NewButton79.setBounds(referX+sideLengthPlus*8,referY+sideLengthPlus*6,sideLength,sideLength);
		add(NewButton79);
		
		
		JButton NewButton81 = new JButton("");
		NewButton81.setBounds(referX,referY+sideLengthPlus*7,sideLength,sideLength);
		add(NewButton81);
		JButton NewButton82 = new JButton("");
		NewButton82.setBounds(referX+sideLengthPlus*1,referY+sideLengthPlus*7,sideLength,sideLength);
		add(NewButton82);
		JButton NewButton83 = new JButton("");
		NewButton83.setBounds(referX+sideLengthPlus*2,referY+sideLengthPlus*7,sideLength,sideLength);
		add(NewButton83);
		JButton NewButton84 = new JButton("");
		NewButton84.setBounds(referX+sideLengthPlus*3,referY+sideLengthPlus*7,sideLength,sideLength);
		add(NewButton84);
		JButton NewButton85 = new JButton("");
		NewButton85.setBounds(referX+sideLengthPlus*4,referY+sideLengthPlus*7,sideLength,sideLength);
		add(NewButton85);
		JButton NewButton86 = new JButton("");
		NewButton86.setBounds(referX+sideLengthPlus*5,referY+sideLengthPlus*7,sideLength,sideLength);
		add(NewButton86);
		JButton NewButton87 = new JButton("");
		NewButton87.setBounds(referX+sideLengthPlus*6,referY+sideLengthPlus*7,sideLength,sideLength);
		add(NewButton87);
		JButton NewButton88 = new JButton("");
		NewButton88.setBounds(referX+sideLengthPlus*7,referY+sideLengthPlus*7,sideLength,sideLength);
		add(NewButton88);
		JButton NewButton89 = new JButton("");
		NewButton89.setBounds(referX+sideLengthPlus*8,referY+sideLengthPlus*7,sideLength,sideLength);
		add(NewButton89);
		
		
		
		
		JButton NewButton91 = new JButton("");
		NewButton91.setBounds(referX,referY+sideLengthPlus*8,sideLength,sideLength);
		add(NewButton91);
		JButton NewButton92 = new JButton("");
		NewButton92.setBounds(referX+sideLengthPlus*1,referY+sideLengthPlus*8,sideLength,sideLength);
		add(NewButton92);
		JButton NewButton93 = new JButton("");
		NewButton93.setBounds(referX+sideLengthPlus*2,referY+sideLengthPlus*8,sideLength,sideLength);
		add(NewButton93);
		JButton NewButton94 = new JButton("");
		NewButton94.setBounds(referX+sideLengthPlus*3,referY+sideLengthPlus*8,sideLength,sideLength);
		add(NewButton94);
		JButton NewButton95 = new JButton("");
		NewButton95.setBounds(referX+sideLengthPlus*4,referY+sideLengthPlus*8,sideLength,sideLength);
		add(NewButton95);
		JButton NewButton96 = new JButton("");
		NewButton96.setBounds(referX+sideLengthPlus*5,referY+sideLengthPlus*8,sideLength,sideLength);
		add(NewButton96);
		JButton NewButton97 = new JButton("");
		NewButton97.setBounds(referX+sideLengthPlus*6,referY+sideLengthPlus*8,sideLength,sideLength);
		add(NewButton97);
		JButton NewButton98 = new JButton("");
		NewButton98.setBounds(referX+sideLengthPlus*7,referY+sideLengthPlus*8,sideLength,sideLength);
		add(NewButton98);
		JButton NewButton99 = new JButton("");
		NewButton99.setBounds(referX+sideLengthPlus*8,referY+sideLengthPlus*8,sideLength,sideLength);
		add(NewButton99);
		
		
		
		
		
		JButton[][] allButton = new JButton[9][9];
		allButton[1][1] = NewButton11;
	}
	
	
	
public void paint(Graphics g){
		
		super.paint(g);
		Font f=new Font("微软雅黑",Font.BOLD,30);
		g.setFont(f);
		
		
		int a = 16;
		int b = 184;
		int tileLength = 46;
		int wallLength = 10;
		int c = (tileLength+wallLength)*9 + wallLength;
		int d = c;
		
		int length=b+wallLength;
		Color  brown0  = new Color(139, 69, 19);
		g.setColor(brown0);
		g.drawRect(a, b,c, d); 
		g.drawRect(a-1, b-1,c+2, d+2); 
		//g.drawRect(a, b,c, d); 
		Color  brown  = new Color(139, 105, 20);
		
		g.setColor(brown);
		
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
}

