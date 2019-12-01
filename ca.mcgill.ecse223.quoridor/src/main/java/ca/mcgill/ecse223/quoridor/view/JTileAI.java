package ca.mcgill.ecse223.quoridor.view;
/**
 * 
 * Name : JTile
 * 
 * @author EveryOne
 * @version This is out main view, we draw 81 tiles and create 81 buttons in order to move pawn.
 * @exception nothing
 *
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.plaf.metal.MetalButtonUI;

import ca.mcgill.ecse223.quoridor.QuoridorApplication;
import ca.mcgill.ecse223.quoridor.controller.PawnBehavior;
import ca.mcgill.ecse223.quoridor.controller.PawnBehavior.MoveDirection;
import ca.mcgill.ecse223.quoridor.view.NewJBoard.SmallWallTO;
import ca.mcgill.ecse223.quoridor.controller.QuoridorController;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class JTileAI extends JPanel {
	JButton[][] allButton = new JButton[10][10];
	boolean isSelectedState = false;
	int whitePawn_row = 9;
	int whitePawn_column = 5;
	int blackPawn_row = 1;
	int blackPawn_column = 5;
	int AIIndex2 = 0;

	/**
	 * Create the panel.
	 */
	public JTileAI() {
		setLayout(null);
		setBounds(0,0,560,800);
		int sideLength = 47;
		int sideLengthPlus = 56;
		int referX = 26;
		int referY = 194;
		
		
		
		JButton NewButton11 = new JButton("");
		JButton NewButton12 = new JButton("");
		JButton NewButton13 = new JButton("");
		JButton NewButton14 = new JButton("");
		JButton NewButton15 = new JButton("");
		JButton NewButton16 = new JButton("");
		JButton NewButton17 = new JButton("");
		JButton NewButton18 = new JButton("");
		JButton NewButton19 = new JButton("");
		JButton NewButton21 = new JButton("");
		JButton NewButton22 = new JButton("");
		JButton NewButton23 = new JButton("");
		JButton NewButton24 = new JButton("");
		JButton NewButton25 = new JButton("");
		JButton NewButton26 = new JButton("");
		JButton NewButton27 = new JButton("");
		JButton NewButton28 = new JButton("");
		JButton NewButton29 = new JButton("");
		JButton NewButton31 = new JButton("");
		JButton NewButton32 = new JButton("");
		JButton NewButton33 = new JButton("");
		JButton NewButton34 = new JButton("");
		JButton NewButton35 = new JButton("");
		JButton NewButton36 = new JButton("");
		JButton NewButton37 = new JButton("");
		JButton NewButton38 = new JButton("");
		JButton NewButton39 = new JButton("");
		JButton NewButton41 = new JButton("");
		JButton NewButton42 = new JButton("");
		JButton NewButton43 = new JButton("");
		JButton NewButton44 = new JButton("");
		JButton NewButton45 = new JButton("");
		JButton NewButton46 = new JButton("");
		JButton NewButton47 = new JButton("");
		JButton NewButton48 = new JButton("");
		JButton NewButton49 = new JButton("");
		JButton NewButton51 = new JButton("");
		JButton NewButton52 = new JButton("");
		JButton NewButton53 = new JButton("");
		JButton NewButton54 = new JButton("");
		JButton NewButton55 = new JButton("");
		JButton NewButton56 = new JButton("");
		JButton NewButton57 = new JButton("");
		JButton NewButton58 = new JButton("");
		JButton NewButton59 = new JButton("");
		JButton NewButton61 = new JButton("");
		JButton NewButton62 = new JButton("");
		JButton NewButton63 = new JButton("");
		JButton NewButton64 = new JButton("");
		JButton NewButton65 = new JButton("");
		JButton NewButton66 = new JButton("");
		JButton NewButton67 = new JButton("");
		JButton NewButton68 = new JButton("");
		JButton NewButton69 = new JButton("");
		JButton NewButton71 = new JButton("");
		JButton NewButton72 = new JButton("");
		JButton NewButton73 = new JButton("");
		JButton NewButton74 = new JButton("");
		JButton NewButton75 = new JButton("");
		JButton NewButton76 = new JButton("");
		JButton NewButton77 = new JButton("");
		JButton NewButton78 = new JButton("");
		JButton NewButton79 = new JButton("");
		JButton NewButton81 = new JButton("");
		JButton NewButton82 = new JButton("");
		JButton NewButton83 = new JButton("");
		JButton NewButton84 = new JButton("");
		JButton NewButton85 = new JButton("");
		JButton NewButton86 = new JButton("");
		JButton NewButton87 = new JButton("");
		JButton NewButton88 = new JButton("");
		JButton NewButton89 = new JButton("");
		JButton NewButton91 = new JButton("");
		JButton NewButton92 = new JButton("");
		JButton NewButton93 = new JButton("");
		JButton NewButton94 = new JButton("");
		JButton NewButton95 = new JButton("");
		JButton NewButton96 = new JButton("");
		JButton NewButton97 = new JButton("");
		JButton NewButton98 = new JButton("");
		JButton NewButton99 = new JButton("");
		
		allButton[1][1] = NewButton11;
		allButton[1][2] = NewButton12;
		allButton[1][3] = NewButton13;
		allButton[1][4] = NewButton14;
		allButton[1][5] = NewButton15;
		allButton[1][6] = NewButton16;
		allButton[1][7] = NewButton17;
		allButton[1][8] = NewButton18;
		allButton[1][9] = NewButton19;
		
		allButton[2][1] = NewButton21;
		allButton[2][2] = NewButton22;
		allButton[2][3] = NewButton23;
		allButton[2][4] = NewButton24;
		allButton[2][5] = NewButton25;
		allButton[2][6] = NewButton26;
		allButton[2][7] = NewButton27;
		allButton[2][8] = NewButton28;
		allButton[2][9] = NewButton29;
		
		allButton[3][1] = NewButton31;
		allButton[3][2] = NewButton32;
		allButton[3][3] = NewButton33;
		allButton[3][4] = NewButton34;
		allButton[3][5] = NewButton35;
		allButton[3][6] = NewButton36;
		allButton[3][7] = NewButton37;
		allButton[3][8] = NewButton38;
		allButton[3][9] = NewButton39;
		
		allButton[4][1] = NewButton41;
		allButton[4][2] = NewButton42;
		allButton[4][3] = NewButton43;
		allButton[4][4] = NewButton44;
		allButton[4][5] = NewButton45;
		allButton[4][6] = NewButton46;
		allButton[4][7] = NewButton47;
		allButton[4][8] = NewButton48;
		allButton[4][9] = NewButton49;
		
		allButton[5][1] = NewButton51;
		allButton[5][2] = NewButton52;
		allButton[5][3] = NewButton53;
		allButton[5][4] = NewButton54;
		allButton[5][5] = NewButton55;
		allButton[5][6] = NewButton56;
		allButton[5][7] = NewButton57;
		allButton[5][8] = NewButton58;
		allButton[5][9] = NewButton59;
		
		allButton[6][1] = NewButton61;
		allButton[6][2] = NewButton62;
		allButton[6][3] = NewButton63;
		allButton[6][4] = NewButton64;
		allButton[6][5] = NewButton65;
		allButton[6][6] = NewButton66;
		allButton[6][7] = NewButton67;
		allButton[6][8] = NewButton68;
		allButton[6][9] = NewButton69;
		
		allButton[7][1] = NewButton71;
		allButton[7][2] = NewButton72;
		allButton[7][3] = NewButton73;
		allButton[7][4] = NewButton74;
		allButton[7][5] = NewButton75;
		allButton[7][6] = NewButton76;
		allButton[7][7] = NewButton77;
		allButton[7][8] = NewButton78;
		allButton[7][9] = NewButton79;
		
		allButton[8][1] = NewButton81;
		allButton[8][2] = NewButton82;
		allButton[8][3] = NewButton83;
		allButton[8][4] = NewButton84;
		allButton[8][5] = NewButton85;
		allButton[8][6] = NewButton86;
		allButton[8][7] = NewButton87;
		allButton[8][8] = NewButton88;
		allButton[8][9] = NewButton89;
		
		allButton[9][1] = NewButton91;
		allButton[9][2] = NewButton92;
		allButton[9][3] = NewButton93;
		allButton[9][4] = NewButton94;
		allButton[9][5] = NewButton95;
		allButton[9][6] = NewButton96;
		allButton[9][7] = NewButton97;
		allButton[9][8] = NewButton98;
		allButton[9][9] = NewButton99;
		
		
		NewButton11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(1,1);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton11.setBounds(referX,referY,sideLength,sideLength);
		add(NewButton11);
		
		NewButton12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(1,2);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton12.setBounds(referX+sideLengthPlus*1,referY,sideLength,sideLength);
		add(NewButton12);
		
		NewButton13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(1,3);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton13.setBounds(referX+sideLengthPlus*2,referY,sideLength,sideLength);
		add(NewButton13);
		
		NewButton14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(1,4);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton14.setBounds(referX+sideLengthPlus*3,referY,sideLength,sideLength);
		add(NewButton14);
		
		NewButton15.setIcon(new ImageIcon(JTile.class.getResource("/ca/mcgill/ecse223/quoridor/resources/bpawn.png")));
		NewButton15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(1,5);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton15.setBounds(referX+sideLengthPlus*4,referY,sideLength,sideLength);
		add(NewButton15);
		
		NewButton16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(1,6);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton16.setBounds(referX+sideLengthPlus*5,referY,sideLength,sideLength);
		add(NewButton16);
		
		NewButton17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(1,7);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton17.setBounds(referX+sideLengthPlus*6,referY,sideLength,sideLength);
		add(NewButton17);
		
		NewButton18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(1,8);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton18.setBounds(referX+sideLengthPlus*7,referY,sideLength,sideLength);
		add(NewButton18);
		
		NewButton19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(1,9);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton19.setBounds(referX+sideLengthPlus*8,referY,sideLength,sideLength);
		add(NewButton19);
		
		
		
		NewButton21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(2,1);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton21.setBounds(referX,referY+sideLengthPlus*1,sideLength,sideLength);
		add(NewButton21);
		
		NewButton22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(2,2);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton22.setBounds(referX+sideLengthPlus*1,referY+sideLengthPlus*1,sideLength,sideLength);
		add(NewButton22);
		
		NewButton23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(2,3);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton23.setBounds(referX+sideLengthPlus*2,referY+sideLengthPlus*1,sideLength,sideLength);
		add(NewButton23);
		
		NewButton24.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(2,4);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton24.setBounds(referX+sideLengthPlus*3,referY+sideLengthPlus*1,sideLength,sideLength);
		add(NewButton24);
		
		NewButton25.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(2,5);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton25.setBounds(referX+sideLengthPlus*4,referY+sideLengthPlus*1,sideLength,sideLength);
		add(NewButton25);
		
		NewButton26.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(2,6);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton26.setBounds(referX+sideLengthPlus*5,referY+sideLengthPlus*1,sideLength,sideLength);
		add(NewButton26);
		
		NewButton27.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(2,7);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton27.setBounds(referX+sideLengthPlus*6,referY+sideLengthPlus*1,sideLength,sideLength);
		add(NewButton27);
		
		NewButton28.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(2,8);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton28.setBounds(referX+sideLengthPlus*7,referY+sideLengthPlus*1,sideLength,sideLength);
		add(NewButton28);
		
		NewButton29.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(2,9);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton29.setBounds(referX+sideLengthPlus*8,referY+sideLengthPlus*1,sideLength,sideLength);
		add(NewButton29);
		
		
		
		
		NewButton31.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(3,1);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton31.setBounds(referX,referY+sideLengthPlus*2,sideLength,sideLength);
		add(NewButton31);
		
		NewButton32.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(3,2);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton32.setBounds(referX+sideLengthPlus*1,referY+sideLengthPlus*2,sideLength,sideLength);
		add(NewButton32);
		
		NewButton33.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(3,3);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton33.setBounds(referX+sideLengthPlus*2,referY+sideLengthPlus*2,sideLength,sideLength);
		add(NewButton33);
		
		NewButton34.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(3,4);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton34.setBounds(referX+sideLengthPlus*3,referY+sideLengthPlus*2,sideLength,sideLength);
		add(NewButton34);
		
		NewButton35.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(3,5);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton35.setBounds(referX+sideLengthPlus*4,referY+sideLengthPlus*2,sideLength,sideLength);
		add(NewButton35);
		
		NewButton36.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(3,6);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton36.setBounds(referX+sideLengthPlus*5,referY+sideLengthPlus*2,sideLength,sideLength);
		add(NewButton36);
		
		NewButton37.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(3,7);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton37.setBounds(referX+sideLengthPlus*6,referY+sideLengthPlus*2,sideLength,sideLength);
		add(NewButton37);
		
		NewButton38.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(3,8);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton38.setBounds(referX+sideLengthPlus*7,referY+sideLengthPlus*2,sideLength,sideLength);
		add(NewButton38);
		
		NewButton39.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(3,9);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton39.setBounds(referX+sideLengthPlus*8,referY+sideLengthPlus*2,sideLength,sideLength);
		add(NewButton39);
		
		
		
		
		
		
		NewButton41.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(4,1);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton41.setBounds(referX,referY+sideLengthPlus*3,sideLength,sideLength);
		add(NewButton41);
	
		NewButton42.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(4,2);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton42.setBounds(referX+sideLengthPlus*1,referY+sideLengthPlus*3,sideLength,sideLength);
		add(NewButton42);
		
		NewButton43.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(4,3);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton43.setBounds(referX+sideLengthPlus*2,referY+sideLengthPlus*3,sideLength,sideLength);
		add(NewButton43);
		
		NewButton44.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(4,4);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton44.setBounds(referX+sideLengthPlus*3,referY+sideLengthPlus*3,sideLength,sideLength);
		add(NewButton44);
		
		NewButton45.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(4,5);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton45.setBounds(referX+sideLengthPlus*4,referY+sideLengthPlus*3,sideLength,sideLength);
		add(NewButton45);
		
		NewButton46.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(4,6);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton46.setBounds(referX+sideLengthPlus*5,referY+sideLengthPlus*3,sideLength,sideLength);
		add(NewButton46);
		
		NewButton47.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(4,7);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton47.setBounds(referX+sideLengthPlus*6,referY+sideLengthPlus*3,sideLength,sideLength);
		add(NewButton47);
		
		NewButton48.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(4,8);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton48.setBounds(referX+sideLengthPlus*7,referY+sideLengthPlus*3,sideLength,sideLength);
		add(NewButton48);
		
		NewButton49.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(4,9);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton49.setBounds(referX+sideLengthPlus*8,referY+sideLengthPlus*3,sideLength,sideLength);
		add(NewButton49);
		
		
		
		NewButton51.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(5,1);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton51.setBounds(referX,referY+sideLengthPlus*4,sideLength,sideLength);
		add(NewButton51);
		
		NewButton52.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(5,2);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton52.setBounds(referX+sideLengthPlus*1,referY+sideLengthPlus*4,sideLength,sideLength);
		add(NewButton52);
		
		NewButton53.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(5,3);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton53.setBounds(referX+sideLengthPlus*2,referY+sideLengthPlus*4,sideLength,sideLength);
		add(NewButton53);
		
		NewButton54.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(5,4);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton54.setBounds(referX+sideLengthPlus*3,referY+sideLengthPlus*4,sideLength,sideLength);
		add(NewButton54);
		
		NewButton55.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(5,5);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton55.setBounds(referX+sideLengthPlus*4,referY+sideLengthPlus*4,sideLength,sideLength);
		add(NewButton55);
		
		NewButton56.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(5,6);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton56.setBounds(referX+sideLengthPlus*5,referY+sideLengthPlus*4,sideLength,sideLength);
		add(NewButton56);
		
		NewButton57.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(5,7);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton57.setBounds(referX+sideLengthPlus*6,referY+sideLengthPlus*4,sideLength,sideLength);
		add(NewButton57);
		
		NewButton58.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(5,8);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton58.setBounds(referX+sideLengthPlus*7,referY+sideLengthPlus*4,sideLength,sideLength);
		add(NewButton58);
		
		NewButton59.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(5,9);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton59.setBounds(referX+sideLengthPlus*8,referY+sideLengthPlus*4,sideLength,sideLength);
		add(NewButton59);
		
		
		
		
		NewButton61.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(6,1);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton61.setBounds(referX,referY+sideLengthPlus*5,sideLength,sideLength);
		add(NewButton61);
		
		NewButton62.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(6,2);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton62.setBounds(referX+sideLengthPlus*1,referY+sideLengthPlus*5,sideLength,sideLength);
		add(NewButton62);
		
		NewButton63.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(6,3);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton63.setBounds(referX+sideLengthPlus*2,referY+sideLengthPlus*5,sideLength,sideLength);
		add(NewButton63);
		
		NewButton64.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(6,4);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton64.setBounds(referX+sideLengthPlus*3,referY+sideLengthPlus*5,sideLength,sideLength);
		add(NewButton64);
		
		NewButton65.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(6,5);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton65.setBounds(referX+sideLengthPlus*4,referY+sideLengthPlus*5,sideLength,sideLength);
		add(NewButton65);
		
		NewButton66.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(6,6);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton66.setBounds(referX+sideLengthPlus*5,referY+sideLengthPlus*5,sideLength,sideLength);
		add(NewButton66);
		
		NewButton67.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(6,7);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton67.setBounds(referX+sideLengthPlus*6,referY+sideLengthPlus*5,sideLength,sideLength);
		add(NewButton67);
		
		NewButton68.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(6,8);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton68.setBounds(referX+sideLengthPlus*7,referY+sideLengthPlus*5,sideLength,sideLength);
		add(NewButton68);
		
		NewButton69.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(6,9);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton69.setBounds(referX+sideLengthPlus*8,referY+sideLengthPlus*5,sideLength,sideLength);
		add(NewButton69);
		

	
		NewButton71.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(7,1);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton71.setBounds(referX,referY+sideLengthPlus*6,sideLength,sideLength);
		add(NewButton71);
		
		NewButton72.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(7,2);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton72.setBounds(referX+sideLengthPlus*1,referY+sideLengthPlus*6,sideLength,sideLength);
		add(NewButton72);
		
		NewButton73.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(7,3);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton73.setBounds(referX+sideLengthPlus*2,referY+sideLengthPlus*6,sideLength,sideLength);
		add(NewButton73);
		
		NewButton74.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(7,4);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton74.setBounds(referX+sideLengthPlus*3,referY+sideLengthPlus*6,sideLength,sideLength);
		add(NewButton74);
		
		NewButton75.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(7,5);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton75.setBounds(referX+sideLengthPlus*4,referY+sideLengthPlus*6,sideLength,sideLength);
		add(NewButton75);
		
		NewButton76.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(7,6);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton76.setBounds(referX+sideLengthPlus*5,referY+sideLengthPlus*6,sideLength,sideLength);
		add(NewButton76);
		
		NewButton77.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(7,7);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton77.setBounds(referX+sideLengthPlus*6,referY+sideLengthPlus*6,sideLength,sideLength);
		add(NewButton77);
		
		NewButton78.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(7,8);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton78.setBounds(referX+sideLengthPlus*7,referY+sideLengthPlus*6,sideLength,sideLength);
		add(NewButton78);
		
		NewButton79.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(7,9);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton79.setBounds(referX+sideLengthPlus*8,referY+sideLengthPlus*6,sideLength,sideLength);
		add(NewButton79);
		
		
		
		NewButton81.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(8,1);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton81.setBounds(referX,referY+sideLengthPlus*7,sideLength,sideLength);
		add(NewButton81);
		
		NewButton82.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(8,2);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton82.setBounds(referX+sideLengthPlus*1,referY+sideLengthPlus*7,sideLength,sideLength);
		add(NewButton82);
		
		NewButton83.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(8,3);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton83.setBounds(referX+sideLengthPlus*2,referY+sideLengthPlus*7,sideLength,sideLength);
		add(NewButton83);
		
		NewButton84.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(8,4);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton84.setBounds(referX+sideLengthPlus*3,referY+sideLengthPlus*7,sideLength,sideLength);
		add(NewButton84);
		
		NewButton85.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(8,5);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton85.setBounds(referX+sideLengthPlus*4,referY+sideLengthPlus*7,sideLength,sideLength);
		add(NewButton85);
		
		NewButton86.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(8,6);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton86.setBounds(referX+sideLengthPlus*5,referY+sideLengthPlus*7,sideLength,sideLength);
		add(NewButton86);
		
		NewButton87.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(8,7);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton87.setBounds(referX+sideLengthPlus*6,referY+sideLengthPlus*7,sideLength,sideLength);
		add(NewButton87);
		
		NewButton88.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(8,8);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton88.setBounds(referX+sideLengthPlus*7,referY+sideLengthPlus*7,sideLength,sideLength);
		add(NewButton88);
	
		NewButton89.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(8,9);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton89.setBounds(referX+sideLengthPlus*8,referY+sideLengthPlus*7,sideLength,sideLength);
		add(NewButton89);
		
		
		
		
		
		NewButton91.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(9,1);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton91.setBounds(referX,referY+sideLengthPlus*8,sideLength,sideLength);
		add(NewButton91);
		
		NewButton92.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(9,2);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton92.setBounds(referX+sideLengthPlus*1,referY+sideLengthPlus*8,sideLength,sideLength);
		add(NewButton92);
		
		NewButton93.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(9,3);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton93.setBounds(referX+sideLengthPlus*2,referY+sideLengthPlus*8,sideLength,sideLength);
		add(NewButton93);
		
		NewButton94.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(9,4);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton94.setBounds(referX+sideLengthPlus*3,referY+sideLengthPlus*8,sideLength,sideLength);
		add(NewButton94);
	
		NewButton95.setIcon(new ImageIcon(JTile.class.getResource("/ca/mcgill/ecse223/quoridor/resources/wpawn.png")));
		NewButton95.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(9,5);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton95.setBounds(referX+sideLengthPlus*4,referY+sideLengthPlus*8,sideLength,sideLength);
		add(NewButton95);
		
		NewButton96.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(9,6);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton96.setBounds(referX+sideLengthPlus*5,referY+sideLengthPlus*8,sideLength,sideLength);
		add(NewButton96);
		
		NewButton97.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(9,7);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton97.setBounds(referX+sideLengthPlus*6,referY+sideLengthPlus*8,sideLength,sideLength);
		add(NewButton97);
		
		NewButton98.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(9,8);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton98.setBounds(referX+sideLengthPlus*7,referY+sideLengthPlus*8,sideLength,sideLength);
		add(NewButton98);
		
		NewButton99.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					takeActions(9,9);
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		NewButton99.setBounds(referX+sideLengthPlus*8,referY+sideLengthPlus*8,sideLength,sideLength);
		add(NewButton99);
		
		
		
		
		
		//JButton[][] allButton = new JButton[9][9];
		
	
		
	}
	boolean dangerous = false;
	public void takeActions(int x, int y) throws CloneNotSupportedException {
		if(isSelectedState==false) {
			if(makeSureLegalTrun(x, y)) {
				findAndGreenAvailableButton(x, y);
				isSelectedState = !isSelectedState;
			}
		}else {
			if(makeSureLegalMove(x, y)) {
				refreshAllWhite();
				if(QuoridorApplication.getJboardAI().isWhiteTurn()==false) {
					if((x==blackPawn_row)&&(y==blackPawn_column)) {
						isSelectedState = !isSelectedState;
						System.out.println("suspend!");
						this.requestFocus();
						return;
					}		
					allButton[x][y].setIcon(new ImageIcon(JTile.class.getResource("/ca/mcgill/ecse223/quoridor/resources/bpawn.png")));
					allButton[blackPawn_row][blackPawn_column].setIcon(null);
//					System.out.println(determineDirection(x,y,blackPawn_row,blackPawn_column));
					if(dangerous==false) {
						String direction = determineDirection(x,y,blackPawn_row,blackPawn_column);
						PawnBehavior pawnBehavior = new PawnBehavior();
						if (pawnBehavior.isLegalStep(checkMoveDirection(direction))) {
							boolean result = QuoridorController.movePlayer("black", direction);
						}
					}
					blackPawn_row = x;
					blackPawn_column = y;
					QuoridorApplication.getJboardAI().setWhiteTurn(true);
					QuoridorApplication.getJboardAI().getWhiteTurnGUI().setVisible(true);
					QuoridorApplication.getJboardAI().getBlackTurnGUI().setVisible(false);
				}else {
					if((x==whitePawn_row)&&(y==whitePawn_column)) {
						isSelectedState = !isSelectedState;
						System.out.println("suspend!");
						this.requestFocus();
						return;
					}
					allButton[x][y].setIcon(new ImageIcon(JTile.class.getResource("/ca/mcgill/ecse223/quoridor/resources/wpawn.png")));
					allButton[whitePawn_row][whitePawn_column].setIcon(null);
//					System.out.println(determineDirection(x,y,whitePawn_row,whitePawn_column));
					if(dangerous==false) {
						String direction = determineDirection(x,y,whitePawn_row,whitePawn_column);
						PawnBehavior pawnBehavior = new PawnBehavior();
						if (pawnBehavior.isLegalStep(checkMoveDirection(direction))) {
							boolean result = QuoridorController.movePlayer("white", direction);
						}
					}
					whitePawn_row = x;
					whitePawn_column = y;
					QuoridorApplication.getJboardAI().setWhiteTurn(false);
					QuoridorApplication.getJboardAI().getWhiteTurnGUI().setVisible(false);
					QuoridorApplication.getJboardAI().getBlackTurnGUI().setVisible(true);
				}
				isSelectedState = !isSelectedState;
				if(QuoridorApplication.getJboardAI().isWhiteTurn()==false) {
					if(AIIndex2==0) {
						takeActions(1,5);
						takeActions(2,5);
						AIIndex2++;
					}
					else if(AIIndex2==1) {
						takeActions(2,5);
						takeActions(3,5);
						AIIndex2++;
					}
					else if(AIIndex2==2) {
						takeActions(3,5);
						takeActions(4,5);
						AIIndex2++;
					}
					else if(AIIndex2==3) {
						takeActions(4,5);
						takeActions(4,4);
						AIIndex2++;
					}
					else if(AIIndex2==4) {
						takeActions(4,4);
						takeActions(3,4);
						AIIndex2++;
					}
				}
				
				
				
			}
			this.requestFocus();
		}
		
	}
	public String determineDirection(int x, int y, int a, int b) {
		if(((x-1)==a)&&(y==b)) {
			return "down";
		}
		if(((x+1)==a)&&(y==b)) {
			return "up";
		}
		if(((x)==a)&&((y-1)==b)) {
			return "right";
		}
		if(((x)==a)&&((y+1)==b)) {
			return "left";
		}
		else {
			return null;
		}
	}
	
	private MoveDirection checkMoveDirection(String direction) {
		MoveDirection movedirection = null;
		if (direction.equals("left")) {
			movedirection = MoveDirection.West;
		}
		if (direction.equals("right")) {
			movedirection = MoveDirection.East;
		}
		if (direction.equals("up")) {
			movedirection = MoveDirection.North;
		}
		if (direction.equals("down")) {
			movedirection = MoveDirection.South;
		}
		return movedirection;
	}
	
	public boolean makeSureLegalTrun(int x, int y) {
		if(QuoridorApplication.getJboardAI().isWhiteTurn()==false) {
			if((x==blackPawn_row)&&(y==blackPawn_column)) {
				return true;
			}else {
				return false;
			}
		}else {
			if((x==whitePawn_row)&&(y==whitePawn_column)) {
				return true;
			}else {
				return false;
			}
		}
	}
	public boolean makeSureLegalMove(int x, int y) {
		ArrayList<ca.mcgill.ecse223.quoridor.view.NewJBoardAI.SmallWallTO> t = QuoridorApplication.getJboardAI().getListOfSmallWallTO();
		if(QuoridorApplication.getJboardAI().isWhiteTurn()==false) {
			if(((x+1)==blackPawn_row)&&(y==blackPawn_column)) {
				return legalMove("up", blackPawn_row, blackPawn_column);
			}
			if(((x+2)==blackPawn_row)&&(y==blackPawn_column)) {
				dangerous = true;
				return legalMove("upJump", blackPawn_row, blackPawn_column);
			}
			if(((x-1)==blackPawn_row)&&(y==blackPawn_column)) {
				return legalMove("down", blackPawn_row, blackPawn_column);
			}
			if(((x-2)==blackPawn_row)&&(y==blackPawn_column)) {
				dangerous = true;
				return legalMove("downJump", blackPawn_row, blackPawn_column);
			}
			if((x==blackPawn_row)&&((y+1)==blackPawn_column)) {
				return legalMove("left", blackPawn_row, blackPawn_column);
			}
			if((x==blackPawn_row)&&((y+2)==blackPawn_column)) {
				dangerous = true;
				return legalMove("leftJump", blackPawn_row, blackPawn_column);
			}
			if((x==blackPawn_row)&&((y-1)==blackPawn_column)) {
				return legalMove("right", blackPawn_row, blackPawn_column);
			}
			if((x==blackPawn_row)&&((y-2)==blackPawn_column)) {
				dangerous = true;
				return legalMove("rightJump", blackPawn_row, blackPawn_column);
			}
			/////////
			if(((x-1)==blackPawn_row)&&((y-1)==blackPawn_column)) {
				dangerous = true;
				return (legalMove("downRightJump", blackPawn_row, blackPawn_column)||legalMove("rightDownJump", blackPawn_row, blackPawn_column));
			}
			if(((x-1)==blackPawn_row)&&((y+1)==blackPawn_column)) {
				dangerous = true;
				return (legalMove("downLeftJump", blackPawn_row, blackPawn_column)||legalMove("leftDownJump", blackPawn_row, blackPawn_column));
			}
			if(((x+1)==blackPawn_row)&&((y-1)==blackPawn_column)) {
				dangerous = true;
				return (legalMove("upRightJump", blackPawn_row, blackPawn_column)||legalMove("rightUpJump", blackPawn_row, blackPawn_column));
			}
			if(((x+1)==blackPawn_row)&&((y+1)==blackPawn_column)) {
				dangerous = true;
				return (legalMove("upLeftJump", blackPawn_row, blackPawn_column)||legalMove("leftUpJump", blackPawn_row, blackPawn_column));
			}
			///////
			if((x==blackPawn_row)&&(y==blackPawn_column)) {
				return true;
			}
			return false;
		}else {
			if(((x+1)==whitePawn_row)&&(y==whitePawn_column)) {
				return legalMove("up", whitePawn_row, whitePawn_column);
			}
			if(((x+2)==whitePawn_row)&&(y==whitePawn_column)) {
				dangerous = true;
				return legalMove("upJump", whitePawn_row, whitePawn_column);
			}
			if(((x-1)==whitePawn_row)&&(y==whitePawn_column)) {
				return legalMove("down", whitePawn_row, whitePawn_column);
			}
			if(((x-2)==whitePawn_row)&&(y==whitePawn_column)) {
				dangerous = true;
				return legalMove("downJump", whitePawn_row, whitePawn_column);
			}
			if((x==whitePawn_row)&&((y+1)==whitePawn_column)) {
				return legalMove("left", whitePawn_row, whitePawn_column);
			}
			if((x==whitePawn_row)&&((y+2)==whitePawn_column)) {
				dangerous = true;
				return legalMove("leftJump", whitePawn_row, whitePawn_column);
			}
			if((x==whitePawn_row)&&((y-1)==whitePawn_column)) {
				return legalMove("right", whitePawn_row, whitePawn_column);
			}
			if((x==whitePawn_row)&&((y-2)==whitePawn_column)) {
				dangerous = true;
				return legalMove("rightJump", whitePawn_row, whitePawn_column);
			}
			if((x==whitePawn_row)&&(y==whitePawn_column)) {
				return true;
			}
			/////////
			if(((x-1)==whitePawn_row)&&((y-1)==whitePawn_column)) {
				dangerous = true;
				return (legalMove("downRightJump", whitePawn_row, whitePawn_column)||legalMove("rightDownJump", whitePawn_row, whitePawn_column));
			}
			if(((x-1)==whitePawn_row)&&((y+1)==whitePawn_column)) {
				dangerous = true;
				return (legalMove("downLeftJump", whitePawn_row, whitePawn_column)||legalMove("leftDownJump", whitePawn_row, whitePawn_column));
			}
			if(((x+1)==whitePawn_row)&&((y-1)==whitePawn_column)) {
				dangerous = true;
				return (legalMove("upRightJump", whitePawn_row, whitePawn_column)||legalMove("rightUpJump", whitePawn_row, whitePawn_column));
			}
			if(((x+1)==whitePawn_row)&&((y+1)==whitePawn_column)) {
				dangerous = true;
				return (legalMove("upLeftJump", whitePawn_row, whitePawn_column)||legalMove("leftUpJump", whitePawn_row, whitePawn_column));
			}
				///////
			return false;
		}
	}
	public void refreshAllWhite() {
		for(int i=1;i<10;i++) {
			for(int j=1;j<10;j++) {
				allButton[i][j].setBackground(Color.WHITE);
				allButton[i][j].setOpaque(false); 
				allButton[i][j].setBorderPainted(true);
			}
		}
	}
	public boolean legalMove(String s, int origin_x, int origin_y) {
		ArrayList<ca.mcgill.ecse223.quoridor.view.NewJBoardAI.SmallWallTO> t = QuoridorApplication.getJboardAI().getListOfSmallWallTO();
		if(s.equalsIgnoreCase("up")) {
			boolean can = true;
			for(int i=0; i<t.size(); i++) {
				if(t.get(i).isVertical()==false) {
					if((t.get(i).getRowSmall()==(origin_x-1))&&((t.get(i).getColumnSmall()==origin_y)||(t.get(i).getColumnSmall()==(origin_y-1)))){
						can = false;
					}
				}
			}
			if((origin_x-1==blackPawn_row)&&(origin_y==blackPawn_column)) {
				can = false;
			}
			if((origin_x-1==whitePawn_row)&&(origin_y==whitePawn_column)) {
				can = false;
			}
			return can;
		}
		else if(s.equalsIgnoreCase("down")){
			boolean can = true;
			for(int i=0; i<t.size(); i++) {
				if(t.get(i).isVertical()==false) {
					if((t.get(i).getRowSmall()==origin_x)&&((t.get(i).getColumnSmall()==origin_y)||(t.get(i).getColumnSmall()==(origin_y-1)))){
						can = false;
					}
				}
			}
			if((origin_x+1==blackPawn_row)&&(origin_y==blackPawn_column)) {
				can = false;
			}
			if((origin_x+1==whitePawn_row)&&(origin_y==whitePawn_column)) {
				can = false;
			}
			return can;
		}
		else if(s.equalsIgnoreCase("left")){
			boolean can = true;
			for(int i=0; i<t.size(); i++) {
				if(t.get(i).isVertical()==true) {
					if((t.get(i).getColumnSmall()==(origin_y-1))&&((t.get(i).getRowSmall()==origin_x)||(t.get(i).getRowSmall()==(origin_x-1)))) {
						can = false;
					}
				}
			}
			if((origin_x==blackPawn_row)&&(origin_y-1==blackPawn_column)) {
				can = false;
			}
			if((origin_x==whitePawn_row)&&(origin_y-1==whitePawn_column)) {
				can = false;
			}
			return can;
		}
		else if(s.equalsIgnoreCase("right")){
			boolean can = true;
			for(int i=0; i<t.size(); i++) {
				if(t.get(i).isVertical()==true) {
					if((t.get(i).getColumnSmall()==origin_y)&&((t.get(i).getRowSmall()==origin_x)||(t.get(i).getRowSmall()==(origin_x-1)))) {
						can = false;
					}
				}
			}
			if((origin_x==blackPawn_row)&&(origin_y+1==blackPawn_column)) {
				can = false;
			}
			if((origin_x==whitePawn_row)&&(origin_y+1==whitePawn_column)) {
				can = false;
			}
			return can;
		}
		else if(s.equalsIgnoreCase("downJump")) {
			boolean can = true;
			if(!(((origin_x+1==blackPawn_row)&&(origin_y==blackPawn_column))
				||((origin_x+1==whitePawn_row)&&(origin_y==whitePawn_column)))) {
				can = false;
			}
			for(int i=0; i<t.size(); i++) {
				if(t.get(i).isVertical()==false) {
					if((t.get(i).getRowSmall()==(origin_x+1))&&((t.get(i).getColumnSmall()==origin_y)||(t.get(i).getColumnSmall()==(origin_y-1)))){
						can = false;
					}
					if((t.get(i).getRowSmall()==(origin_x))&&((t.get(i).getColumnSmall()==origin_y)||(t.get(i).getColumnSmall()==(origin_y-1)))){
						can = false;
					}
				}
			}
			return can;
		}
		else if(s.equalsIgnoreCase("upJump")) {
			boolean can = true;
			if(!(((origin_x-1==blackPawn_row)&&(origin_y==blackPawn_column))
				||((origin_x-1==whitePawn_row)&&(origin_y==whitePawn_column)))) {
				can = false;
			}
			for(int i=0; i<t.size(); i++) {
				if(t.get(i).isVertical()==false) {
					if((t.get(i).getRowSmall()==(origin_x-2))&&((t.get(i).getColumnSmall()==origin_y)||(t.get(i).getColumnSmall()==(origin_y-1)))){
						can = false;
					}
					if((t.get(i).getRowSmall()==(origin_x-1))&&((t.get(i).getColumnSmall()==origin_y)||(t.get(i).getColumnSmall()==(origin_y-1)))){
						can = false;
					}
				}
			}
			return can;
		}
		else if(s.equalsIgnoreCase("rightJump")) {
			boolean can = true;
			if(!(((origin_x==blackPawn_row)&&(origin_y+1==blackPawn_column))
					||((origin_x==whitePawn_row)&&(origin_y+1==whitePawn_column)))) {
					can = false;
			}
			for(int i=0; i<t.size(); i++) {
				if(t.get(i).isVertical()==true) {
					if((t.get(i).getColumnSmall()==origin_y+1)&&((t.get(i).getRowSmall()==origin_x)||(t.get(i).getRowSmall()==(origin_x-1)))) {
						can = false;
					}
					if((t.get(i).getColumnSmall()==origin_y)&&((t.get(i).getRowSmall()==origin_x)||(t.get(i).getRowSmall()==(origin_x-1)))) {
						can = false;
					}
				}
			}
			return can;
		}
		else if(s.equalsIgnoreCase("leftJump")) {
			boolean can = true;
			if(!(((origin_x==blackPawn_row)&&(origin_y-1==blackPawn_column))
					||((origin_x==whitePawn_row)&&(origin_y-1==whitePawn_column)))) {
					can = false;
			}
			for(int i=0; i<t.size(); i++) {
				if(t.get(i).isVertical()==true) {
					if((t.get(i).getColumnSmall()==origin_y-2)&&((t.get(i).getRowSmall()==origin_x)||(t.get(i).getRowSmall()==(origin_x-1)))) {
						can = false;
					}
					if((t.get(i).getColumnSmall()==origin_y-1)&&((t.get(i).getRowSmall()==origin_x)||(t.get(i).getRowSmall()==(origin_x-1)))) {
						can = false;
					}
				}
			}
			return can;
		}
		else if(s.equalsIgnoreCase("downRightJump")) {
			boolean can = true;
			boolean exist = false;
			if(!(((origin_x+1==blackPawn_row)&&(origin_y==blackPawn_column))
					||((origin_x+1==whitePawn_row)&&(origin_y==whitePawn_column)))) {
					can = false;
			}
			for(int i=0; i<t.size(); i++) {
				if(t.get(i).isVertical()==false) {
					if((t.get(i).getRowSmall()==origin_x)&&((t.get(i).getColumnSmall()==origin_y)||(t.get(i).getColumnSmall()==(origin_y-1)))){
						can = false;
					}
					if((t.get(i).getRowSmall()==(origin_x+1))&&((t.get(i).getColumnSmall()==origin_y)||(t.get(i).getColumnSmall()==(origin_y-1)))){
						exist = true;
					}
				}		
			}
			if(origin_x==8) {
				exist = true;
			}
			//System.out.println("can: " + can + ", exist: " + exist);
			if(exist==true) {
				return can;
			}
			return false;
		}
		else if(s.equalsIgnoreCase("rightDownJump")) {
			boolean can = true;
			boolean exist = false;
			if(!(((origin_x==blackPawn_row)&&(origin_y+1==blackPawn_column))
					||((origin_x==whitePawn_row)&&(origin_y+1==whitePawn_column)))) {
					can = false;
			}
			for(int i=0; i<t.size(); i++) {
				if(t.get(i).isVertical()==true) {
					if((t.get(i).getColumnSmall()==origin_y)&&((t.get(i).getRowSmall()==origin_x)||(t.get(i).getRowSmall()==(origin_x-1)))) {
						can = false;
					}
					if((t.get(i).getColumnSmall()==(origin_y+1))&&((t.get(i).getRowSmall()==origin_x)||(t.get(i).getRowSmall()==(origin_x-1)))) {
						exist = true;
					}
				}		
			}
			if(origin_y==8) {
				exist = true;
			}
			if(exist==true) {
				return can;
			}
			return false;
		}
		else if(s.equalsIgnoreCase("downLeftJump")) {
			boolean can = true;
			boolean exist = false;
			if(!(((origin_x+1==blackPawn_row)&&(origin_y==blackPawn_column))
					||((origin_x+1==whitePawn_row)&&(origin_y==whitePawn_column)))) {
					can = false;
			}
			for(int i=0; i<t.size(); i++) {
				if(t.get(i).isVertical()==false) {
					if((t.get(i).getRowSmall()==origin_x)&&((t.get(i).getColumnSmall()==origin_y)||(t.get(i).getColumnSmall()==(origin_y-1)))){
						can = false;
					}
					if((t.get(i).getRowSmall()==(origin_x+1))&&((t.get(i).getColumnSmall()==origin_y)||(t.get(i).getColumnSmall()==(origin_y-1)))){
						exist = true;
					}
				}		
			}
			if(origin_x==8) {
				exist = true;
			}
			//System.out.println("can: " + can + ", exist: " + exist);
			if(exist==true) {
				return can;
			}
			return false;
		}
		else if(s.equalsIgnoreCase("leftDownJump")) {
			boolean can = true;
			boolean exist = false;
			if(!(((origin_x==blackPawn_row)&&(origin_y-1==blackPawn_column))
					||((origin_x==whitePawn_row)&&(origin_y-1==whitePawn_column)))) {
					can = false;
			}
			for(int i=0; i<t.size(); i++) {
				if(t.get(i).isVertical()==true) {
					if((t.get(i).getColumnSmall()==(origin_y-1))&&((t.get(i).getRowSmall()==origin_x)||(t.get(i).getRowSmall()==(origin_x-1)))) {
						can = false;
					}
					if((t.get(i).getColumnSmall()==(origin_y-2))&&((t.get(i).getRowSmall()==origin_x)||(t.get(i).getRowSmall()==(origin_x-1)))) {
						exist = true;
					}
				}		
			}
			if(origin_y==2) {
				exist = true;
			}
			if(exist==true) {
				return can;
			}
			return false;
		}
		else if(s.equalsIgnoreCase("upRightJump")) {
			boolean can = true;
			boolean exist = false;
			if(!(((origin_x-1==blackPawn_row)&&(origin_y==blackPawn_column))
					||((origin_x-1==whitePawn_row)&&(origin_y==whitePawn_column)))) {
					can = false;
			}
			for(int i=0; i<t.size(); i++) {
				if(t.get(i).isVertical()==false) {
					if((t.get(i).getRowSmall()==(origin_x-1))&&((t.get(i).getColumnSmall()==origin_y)||(t.get(i).getColumnSmall()==(origin_y-1)))){
						can = false;
					}
					if((t.get(i).getRowSmall()==(origin_x-2))&&((t.get(i).getColumnSmall()==origin_y)||(t.get(i).getColumnSmall()==(origin_y-1)))){
						exist = true;
					}
				}		
			}
			if(origin_x==2) {
				exist = true;
			}
			//System.out.println("can: " + can + ", exist: " + exist);
			if(exist==true) {
				return can;
			}
			return false;
		}
		else if(s.equalsIgnoreCase("rightUpJump")) {
			boolean can = true;
			boolean exist = false;
			if(!(((origin_x==blackPawn_row)&&(origin_y+1==blackPawn_column))
					||((origin_x==whitePawn_row)&&(origin_y+1==whitePawn_column)))) {
					can = false;
			}
			for(int i=0; i<t.size(); i++) {
				if(t.get(i).isVertical()==true) {
					if((t.get(i).getColumnSmall()==origin_y)&&((t.get(i).getRowSmall()==origin_x)||(t.get(i).getRowSmall()==(origin_x-1)))) {
						can = false;
					}
					if((t.get(i).getColumnSmall()==(origin_y+1))&&((t.get(i).getRowSmall()==origin_x)||(t.get(i).getRowSmall()==(origin_x-1)))) {
						exist = true;
					}
				}		
			}
			if(origin_y==8) {
				exist = true;
			}
			if(exist==true) {
				return can;
			}
			return false;
		}
		else if(s.equalsIgnoreCase("upLeftJump")) {
			boolean can = true;
			boolean exist = false;
			if(!(((origin_x-1==blackPawn_row)&&(origin_y==blackPawn_column))
					||((origin_x-1==whitePawn_row)&&(origin_y==whitePawn_column)))) {
					can = false;
			}
			for(int i=0; i<t.size(); i++) {
				if(t.get(i).isVertical()==false) {
					if((t.get(i).getRowSmall()==(origin_x-1))&&((t.get(i).getColumnSmall()==origin_y)||(t.get(i).getColumnSmall()==(origin_y-1)))){
						can = false;
					}
					if((t.get(i).getRowSmall()==(origin_x-2))&&((t.get(i).getColumnSmall()==origin_y)||(t.get(i).getColumnSmall()==(origin_y-1)))){
						exist = true;
					}
				}	
			}
			if(origin_x==2) {
				exist = true;
			}
			//System.out.println("can: " + can + ", exist: " + exist);
			if(exist==true) {
				return can;
			}
			return false;
		}
		else if(s.equalsIgnoreCase("leftUpJump")) {
			boolean can = true;
			boolean exist = false;
			if(!(((origin_x==blackPawn_row)&&(origin_y-1==blackPawn_column))
					||((origin_x==whitePawn_row)&&(origin_y-1==whitePawn_column)))) {
					can = false;
			}
			for(int i=0; i<t.size(); i++) {
				if(t.get(i).isVertical()==true) {
					if((t.get(i).getColumnSmall()==(origin_y-1))&&((t.get(i).getRowSmall()==origin_x)||(t.get(i).getRowSmall()==(origin_x-1)))) {
						can = false;
					}
					if((t.get(i).getColumnSmall()==(origin_y-2))&&((t.get(i).getRowSmall()==origin_x)||(t.get(i).getRowSmall()==(origin_x-1)))) {
						exist = true;
					}
				}		
			}
			if(origin_y==2) {
				exist = true;
			}
			if(exist==true) {
				return can;
			}
			return false;
		}
		
		else {
			return false;
		}
	}
	public void findAndGreenAvailableButton(int x, int y) {
		ArrayList<ca.mcgill.ecse223.quoridor.view.NewJBoardAI.SmallWallTO> t = QuoridorApplication.getJboardAI().getListOfSmallWallTO();
		for(int i=0; i<t.size(); i++) {
			int r = t.get(i).getRowSmall();
			int c = t.get(i).getColumnSmall();
			boolean v = t.get(i).isVertical();
			//System.out.println("{isVertical: " + v + ", row: "+ r + ", column: " + c + "}");
		}
		if(legalB(x+1,y)) {
			if(legalMove("down", x, y)) {
				greenButton(allButton[x+1][y]);
			}
		}
		if(legalB(x-1,y)) {
			if(legalMove("up", x, y)) {
				greenButton(allButton[x-1][y]);
			}
		}
		if(legalB(x,y+1)) {
			if(legalMove("right", x, y)) {
				greenButton(allButton[x][y+1]);
			}
		}
		if(legalB(x,y-1)) {
			if(legalMove("left", x, y)) {
				greenButton(allButton[x][y-1]);
			}
		}
		if(legalB(x+2,y)) {
			if(legalMove("downJump", x, y)) {
				greenButton(allButton[x+2][y]);
			}
		}
		if(legalB(x-2,y)) {
			if(legalMove("upJump", x, y)) {
				greenButton(allButton[x-2][y]);
			}
		}
		if(legalB(x,y-2)) {
			if(legalMove("leftJump", x, y)) {
				greenButton(allButton[x][y-2]);
			}
		}
		if(legalB(x,y+2)) {
			if(legalMove("rightJump", x, y)) {
				greenButton(allButton[x][y+2]);
			}
		}
		///////////
		if(legalB(x+1,y+1)) {
			if((legalMove("downRightJump", x, y))||(legalMove("rightDownJump", x, y))) {
				greenButton(allButton[x+1][y+1]);
			}
		}
		if(legalB(x+1,y-1)) {
			if((legalMove("downLeftJump", x, y))||(legalMove("leftDownJump", x, y))) {
				greenButton(allButton[x+1][y-1]);
			}
		}
		if(legalB(x-1,y+1)) {
			if((legalMove("upRightJump", x, y))||(legalMove("rightUpJump", x, y))) {
				greenButton(allButton[x-1][y+1]);
			}
		}
		if(legalB(x-1,y-1)) {
			if((legalMove("upLeftJump", x, y))||(legalMove("leftUpJump", x, y))) {
				greenButton(allButton[x-1][y-1]);
			}
		}
	}
	public boolean legalB(int x, int y) {
		if((x>=1)&&(x<=9)&(y>=1)&(y<=9)) {
		return true;
		}
		return false;
	}
	public void greenButton(JButton button) {
		button.setBackground(Color.GREEN);
		button.setOpaque(true); 
		button.setBorderPainted(false);
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

