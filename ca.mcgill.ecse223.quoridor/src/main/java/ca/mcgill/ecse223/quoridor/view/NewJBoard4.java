package ca.mcgill.ecse223.quoridor.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.quoridor.QuoridorApplication;
import ca.mcgill.ecse223.quoridor.controller.QuoridorController;
import ca.mcgill.ecse223.quoridor.view.NewJBoard.SmallWallTO;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewJBoard4 extends JFrame {

	private JPanel contentPane;
	private JPanel mainLayerPanel;
	private JTile4 tile4;
	private JUser4 users4;
	private JLabel whiteTurnGUI;
	private JLabel yellowTurnGUI;
	private JLabel blackTurnGUI;
	private JLabel redTurnGUI;
	private JLabel whiteStock;
	private JLabel yellowStock;
	private JLabel blackStock;
	private JLabel redStock;
	
	
	private JWall jWallCandidate_1= new JWall();
	private JWall jWallCandidate_2= new JWall();
	private JWall jWallCandidate_3= new JWall();
	private JWall jWallCandidate_4= new JWall();
	private JWall jWallCandidate_5= new JWall();
	private JWall jWallCandidate_6= new JWall();
	private JWall jWallCandidate_7= new JWall();
	private JWall jWallCandidate_8= new JWall();
	private JWall jWallCandidate_9= new JWall();
	private JWall jWallCandidate_10= new JWall();
	private JWall jWallCandidate_11= new JWall();
	private JWall jWallCandidate_12= new JWall();
	private JWall jWallCandidate_13= new JWall();
	private JWall jWallCandidate_14= new JWall();
	private JWall jWallCandidate_15= new JWall();
	private JWall jWallCandidate_16= new JWall();
	private JWall jWallCandidate_17= new JWall();
	private JWall jWallCandidate_18= new JWall();
	private JWall jWallCandidate_19= new JWall();
	private JWall jWallCandidate_20= new JWall();
	private JWall jWallCandidate_21= new JWall();
	private JWall jWallCandidate_22= new JWall();
	private JWall jWallCandidate_23= new JWall();
	private JWall jWallCandidate_24= new JWall();
	private JWall jWallCandidate_25= new JWall();
	private JWall jWallCandidate_26= new JWall();
	private JWall jWallCandidate_27= new JWall();
	private JWall jWallCandidate_28= new JWall();
	private JWall jWallCandidate_29= new JWall();
	private JWall jWallCandidate_30= new JWall();
	private JWall jWallCandidate_31= new JWall();
	private JWall jWallCandidate_32= new JWall();
	private JWall jWallCandidate_33= new JWall();
	private JWall jWallCandidate_34= new JWall();
	private JWall jWallCandidate_35= new JWall();
	private JWall jWallCandidate_36= new JWall();
	private JWall jWallCandidate_37= new JWall();
	private JWall jWallCandidate_38= new JWall();
	private JWall jWallCandidate_39= new JWall();
	private JWall jWallCandidate_40= new JWall();
	
	private JWall[] JWhiteWallInStock = {jWallCandidate_1,jWallCandidate_2,jWallCandidate_3,jWallCandidate_4,jWallCandidate_5,jWallCandidate_6,jWallCandidate_7,jWallCandidate_8,jWallCandidate_9,jWallCandidate_10};
	private JWall[] JYellowWallInStock = {jWallCandidate_11,jWallCandidate_12,jWallCandidate_13,jWallCandidate_14,jWallCandidate_15,jWallCandidate_16,jWallCandidate_17,jWallCandidate_18,jWallCandidate_19,jWallCandidate_20};
	private JWall[] JBlackWallInStock = {jWallCandidate_21,jWallCandidate_22,jWallCandidate_23,jWallCandidate_24,jWallCandidate_25,jWallCandidate_26,jWallCandidate_27,jWallCandidate_28,jWallCandidate_29,jWallCandidate_30};
	private JWall[] JRedWallInStock = {jWallCandidate_31,jWallCandidate_32,jWallCandidate_33,jWallCandidate_34,jWallCandidate_35,jWallCandidate_36,jWallCandidate_37,jWallCandidate_38,jWallCandidate_39,jWallCandidate_40};
	
	private JWall[] JWhiteWallOnBoard = new JWall[10];
	private JWall[] JYellowWallOnBoard = new JWall[10];
	private JWall[] JBlackWallOnBoard = new JWall[10];
	private JWall[] JRedWallOnBoard = new JWall[10];
	
	private JWall jWallCandidate = null;
	
	private static final int MAX_WALL = 10;
	private int WHITE_WALL_INDEX = 0;//how many times you grabed before
	private int YELLOW_WALL_INDEX = 0;
	private int BLACK_WALL_INDEX = 0;
	private int RED_WALL_INDEX = 0;
	
	private String turnGUI = "white";


	public String getTurnGUI() {
		return turnGUI;
	}

	public void setTurnGUI(String turnGUI) {
		this.turnGUI = turnGUI;
	}
	
	public JLabel getWhiteTurnGUI() {
		return whiteTurnGUI;
	}

	public void setWhiteTurnGUI(JLabel whiteTurnGUI) {
		this.whiteTurnGUI = whiteTurnGUI;
	}

	public JLabel getYellowTurnGUI() {
		return yellowTurnGUI;
	}

	public void setYellowTurnGUI(JLabel yellowTurnGUI) {
		this.yellowTurnGUI = yellowTurnGUI;
	}

	public JLabel getBlackTurnGUI() {
		return blackTurnGUI;
	}

	public void setBlackTurnGUI(JLabel blackTurnGUI) {
		this.blackTurnGUI = blackTurnGUI;
	}

	public JLabel getRedTurnGUI() {
		return redTurnGUI;
	}

	public void setRedTurnGUI(JLabel redTurnGUI) {
		this.redTurnGUI = redTurnGUI;
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewJBoard4 frame = new NewJBoard4();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewJBoard4() {
		for(int i=0; i<10; i++) {
			JWhiteWallInStock[i].setVisible(false);
		}
		for(int i=0; i<10; i++) {
			JYellowWallInStock[i].setVisible(false);
		}
		for(int i=0; i<10; i++) {
			JBlackWallInStock[i].setVisible(false);
		}
		for(int i=0; i<10; i++) {
			JRedWallInStock[i].setVisible(false);
		}	
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 820, 820);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		mainLayerPanel = new JPanel();		  
		contentPane.add(mainLayerPanel, BorderLayout.CENTER);		 
		mainLayerPanel.setBorder(BorderFactory.createLineBorder(Color.orange, 2));
		mainLayerPanel.setLayout(null);
		
		
		tile4 = new JTile4();
		tile4.setBackground(Color.WHITE);
		tile4.setBounds(0, 0, 820, 820);
		tile4.setOpaque(false);
		mainLayerPanel.add(tile4);
		
		
		users4 = new JUser4();
		users4.setBounds(0, 0, 820, 820);
		users4.setOpaque(false);
		mainLayerPanel.add(users4);
		
		
		whiteTurnGUI = new JLabel("");
		whiteTurnGUI.setBounds(495, 720, 32, 32);
		whiteTurnGUI.setForeground(Color.WHITE);
		whiteTurnGUI.setIcon(new ImageIcon(NewJBoard4.class.getResource("/ca/mcgill/ecse223/quoridor/resources/32-wqueen.png")));
		whiteTurnGUI.setVisible(true);
		tile4.add(whiteTurnGUI);
		
		yellowTurnGUI = new JLabel("");
		yellowTurnGUI.setBounds(53, 483, 32, 32);
		yellowTurnGUI.setForeground(Color.WHITE);
		yellowTurnGUI.setIcon(new ImageIcon(NewJBoard4.class.getResource("/ca/mcgill/ecse223/quoridor/resources/32-wqueen.png")));
		yellowTurnGUI.setVisible(true);
		tile4.add(yellowTurnGUI);
		
		blackTurnGUI = new JLabel("");
		blackTurnGUI.setBounds(495, 96, 32, 32);
		blackTurnGUI.setForeground(Color.WHITE);
		blackTurnGUI.setIcon(new ImageIcon(NewJBoard4.class.getResource("/ca/mcgill/ecse223/quoridor/resources/32-wqueen.png")));
		blackTurnGUI.setVisible(true);
		tile4.add(blackTurnGUI);
		
		redTurnGUI = new JLabel("");
		redTurnGUI.setBounds(722, 498, 32, 32);
		redTurnGUI.setForeground(Color.WHITE);
		redTurnGUI.setIcon(new ImageIcon(NewJBoard4.class.getResource("/ca/mcgill/ecse223/quoridor/resources/32-wqueen.png")));
		redTurnGUI.setVisible(true);
		tile4.add(redTurnGUI);
		
		whiteTurnGUI.setVisible(true);
		yellowTurnGUI.setVisible(false);
		blackTurnGUI.setVisible(false);
		redTurnGUI.setVisible(false);
		
		whiteStock = new JLabel("10");
		whiteStock.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        whiteStock.setForeground(Color.WHITE);
        whiteStock.setBounds(410, 728, 24, 24);
        tile4.add(whiteStock);
        
        yellowStock = new JLabel("10");
        yellowStock.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        yellowStock.setForeground(Color.WHITE);
        yellowStock.setBounds(53, 435, 24, 24);
        tile4.add(yellowStock);
        
        
        blackStock = new JLabel("10");
        blackStock.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        blackStock.setForeground(Color.WHITE);
        blackStock.setBounds(386, 96, 24, 24);
        tile4.add(blackStock);
        
        
        redStock = new JLabel("10");
        redStock.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        redStock.setForeground(Color.WHITE);
        redStock.setBounds(722, 452, 24, 24);
        tile4.add(redStock);
        
        
        
        tile4.setFocusable(true);
        
        JButton btnNewButton = new JButton("Back");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		QuoridorApplication.getMainMenu().setVisible(true);
        		QuoridorApplication.getJboard4().setVisible(false);
        	}
        });
        btnNewButton.setBounds(28, 76, 66, 43);
        tile4.add(btnNewButton);
        tile4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar()=='g') {	
					if(jWallCandidate==null) {
						if(turnGUI=="white") {
							if(WHITE_WALL_INDEX<MAX_WALL) {
								jWallCandidate = JWhiteWallInStock[WHITE_WALL_INDEX];
								tile4.add(jWallCandidate);//????
								jWallCandidate.setBackground(Color.GREEN);
								jWallCandidate.setBounds(424, 374, 9, 103);
								jWallCandidate.setVisible(true);	
								WHITE_WALL_INDEX++;
							}else {
								JOptionPane.showMessageDialog(null, "You have no more white walls in hand!");
							}
							
						}
						else if(turnGUI=="yellow"){
							if(YELLOW_WALL_INDEX<MAX_WALL) {
								jWallCandidate = JYellowWallInStock[YELLOW_WALL_INDEX];
								tile4.add(jWallCandidate);//????
								jWallCandidate.setBackground(Color.YELLOW);
								jWallCandidate.setBounds(424, 374, 9, 103);
								jWallCandidate.setVisible(true);
								YELLOW_WALL_INDEX++;
							}else {
								JOptionPane.showMessageDialog(null, "You have no more yellow walls in hand!");
							}
						}
						else if(turnGUI=="black"){
							if(BLACK_WALL_INDEX<MAX_WALL) {
								jWallCandidate = JBlackWallInStock[BLACK_WALL_INDEX];
								tile4.add(jWallCandidate);//????
								jWallCandidate.setBackground(Color.BLACK);
								jWallCandidate.setBounds(424, 374, 9, 103);
								jWallCandidate.setVisible(true);
								BLACK_WALL_INDEX++;
							}else {
								JOptionPane.showMessageDialog(null, "You have no more black walls in hand!");
							}
						}
						else{
							if(RED_WALL_INDEX<MAX_WALL) {
								jWallCandidate = JRedWallInStock[RED_WALL_INDEX];
								tile4.add(jWallCandidate);//????
								jWallCandidate.setBackground(Color.RED);
								jWallCandidate.setBounds(424, 374, 9, 103);
								jWallCandidate.setVisible(true);
								RED_WALL_INDEX++;
							}else {
								JOptionPane.showMessageDialog(null, "You have no more red walls in hand!");
							}
						}
						//QuoridorController.grabWall();	
					}else {
						jWallCandidate.setVisible(false);
						jWallCandidate=null;
						if(turnGUI=="white") {
							WHITE_WALL_INDEX--;
							System.out.println("remove white");
						}
						else if (turnGUI=="yellow") {
							YELLOW_WALL_INDEX--;
							System.out.println("remove yellow");
						}
						else if (turnGUI=="black"){
							BLACK_WALL_INDEX--;
							System.out.println("remove black");
						}else {
							RED_WALL_INDEX--;
							System.out.println("remove red");
						}
					}
				}
				
				if (e.getKeyChar()=='r') {
					if(jWallCandidate!=null) {
						//QuoridorController.flipWall();
					    int x = jWallCandidate.getLocation().x;
						int y = jWallCandidate.getLocation().y;
						int old_h = jWallCandidate.getBounds().height;
						int old_w = jWallCandidate.getBounds().width;
						if (old_h==9) {
							// horizontal
							jWallCandidate.setBounds(x, y, 9, 103);
							jWallCandidate.setLocation(jWallCandidate.getLocation().x+47 , jWallCandidate.getLocation().y-47);
						}
						else {
							// vertical
							jWallCandidate.setBounds(x, y, 103, 9);
							jWallCandidate.setLocation(jWallCandidate.getLocation().x-47 , jWallCandidate.getLocation().y+47);        
						}
					}
					
				}

				// up
				if ((e.getKeyChar()=='w')||(e.getKeyCode() == KeyEvent.VK_UP)) {
					if(jWallCandidate!=null) {
						//QuoridorController.MoveWall("up");
						int x = jWallCandidate.getLocation().x;
						int y = jWallCandidate.getLocation().y;
						if(jWallCandidate.getHeight()==103) {
							if(y>=206) {
								jWallCandidate.setLocation(x, y-56);
							}
						}
						else{
							if(y>=253) {
								jWallCandidate.setLocation(x, y-56);
							}
						}
					}
				}
				
				if ((e.getKeyChar()=='a')||(e.getKeyCode() == KeyEvent.VK_LEFT)) {
					if(jWallCandidate!=null) {
						//QuoridorController.MoveWall("left");
						int x = jWallCandidate.getLocation().x;
						int y = jWallCandidate.getLocation().y;
						if(jWallCandidate.getHeight()==103) {
							if(x>=256) {
								jWallCandidate.setLocation(x-56, y);
							}
						}
						else{
							if(x>=209) {
								jWallCandidate.setLocation(x-56, y);
							}
						}
					}
				}
				if ((e.getKeyChar()=='s')||(e.getKeyCode() == KeyEvent.VK_DOWN)) {
					if(jWallCandidate!=null) {
						//QuoridorController.MoveWall("down");
						int x = jWallCandidate.getLocation().x;
						int y = jWallCandidate.getLocation().y;
						if(jWallCandidate.getHeight()==103) {
							if(y<=486) {
								jWallCandidate.setLocation(x, y+56);
							}
						}
						else{
							if(y<=533) {
								jWallCandidate.setLocation(x, y+56);
							}
						}
					}
				}
				if ((e.getKeyChar()=='d')||(e.getKeyCode() == KeyEvent.VK_RIGHT)) {
					if(jWallCandidate!=null) {
						//QuoridorController.MoveWall("right");
						int x = jWallCandidate.getLocation().x;
						int y = jWallCandidate.getLocation().y;
						if(jWallCandidate.getHeight()==103) {
							if(x<=536) {
								jWallCandidate.setLocation(x+56, y);
							}
						}
						else{
							if(x<=489) {
								jWallCandidate.setLocation(x+56, y);
							}
						}
					}
				}
				
				
				if (e.getKeyChar()=='t') if (e.getKeyChar()=='t') {
					if(jWallCandidate!=null) {
						//if(QuoridorController.verifyOverlapped(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate())==false) 
						//{
							if(turnGUI=="white") {
								int x = jWallCandidate.getLocation().x;
								int y = jWallCandidate.getLocation().y;
								JWhiteWallInStock[WHITE_WALL_INDEX-1].setLocation(x, y);
								JWhiteWallInStock[WHITE_WALL_INDEX-1].setBackground(Color.BLUE);
								JWhiteWallInStock[WHITE_WALL_INDEX-1].setVisible(true);
								JWhiteWallOnBoard[WHITE_WALL_INDEX-1] = JWhiteWallInStock[WHITE_WALL_INDEX-1];
								whiteStock.setText(transferInt4(10-WHITE_WALL_INDEX));
								whiteTurnGUI.setVisible(false);
								yellowTurnGUI.setVisible(true);
								blackTurnGUI.setVisible(false);
								redTurnGUI.setVisible(false);
								turnGUI="yellow";
							}
							else if(turnGUI=="yellow") {
								int x = jWallCandidate.getLocation().x;
								int y = jWallCandidate.getLocation().y;
								JYellowWallInStock[YELLOW_WALL_INDEX-1].setLocation(x, y);
								JYellowWallInStock[YELLOW_WALL_INDEX-1].setBackground(Color.BLUE);
								JYellowWallInStock[YELLOW_WALL_INDEX-1].setVisible(true);
								JYellowWallOnBoard[YELLOW_WALL_INDEX-1] = JYellowWallInStock[YELLOW_WALL_INDEX-1];
								yellowStock.setText(transferInt4(10-YELLOW_WALL_INDEX));
								whiteTurnGUI.setVisible(false);
								yellowTurnGUI.setVisible(false);
								blackTurnGUI.setVisible(true);
								redTurnGUI.setVisible(false);
								turnGUI="black";
							}
							else if(turnGUI=="black") {
								int x = jWallCandidate.getLocation().x;
								int y = jWallCandidate.getLocation().y;
								JBlackWallInStock[BLACK_WALL_INDEX-1].setLocation(x, y);
								JBlackWallInStock[BLACK_WALL_INDEX-1].setBackground(Color.BLUE);
								JBlackWallInStock[BLACK_WALL_INDEX-1].setVisible(true);
								JBlackWallOnBoard[BLACK_WALL_INDEX-1] = JBlackWallInStock[BLACK_WALL_INDEX-1];
								blackStock.setText(transferInt4(10-BLACK_WALL_INDEX));
								whiteTurnGUI.setVisible(false);
								yellowTurnGUI.setVisible(false);
								blackTurnGUI.setVisible(false);
								redTurnGUI.setVisible(true);
								turnGUI="red";
							}
							else {
								int x = jWallCandidate.getLocation().x;
								int y = jWallCandidate.getLocation().y;
								JRedWallInStock[RED_WALL_INDEX-1].setLocation(x, y);
								JRedWallInStock[RED_WALL_INDEX-1].setBackground(Color.BLUE);
								JRedWallInStock[RED_WALL_INDEX-1].setVisible(true);
								JRedWallOnBoard[RED_WALL_INDEX-1] = JRedWallInStock[RED_WALL_INDEX-1];
								redStock.setText(transferInt4(10-RED_WALL_INDEX));
								whiteTurnGUI.setVisible(true);
								yellowTurnGUI.setVisible(false);
								blackTurnGUI.setVisible(false);
								redTurnGUI.setVisible(false);
								turnGUI="white";
							}
							jWallCandidate = null;
						//}	
						//QuoridorController.releaseWall();
					}
				}
				
				
			}

			
		});
		
	}
	/**
	 * 
	 * 
	 * @author EveryOne
	 * @version convert int to string
	 * @exception nothing
	 *
	 */
	public String transferInt4(int a) {
		if(a==10) return "10";
		if(a==9) return "9";
		if(a==8) return "8";
		if(a==7) return "7";
		if(a==6) return "6";
		if(a==5) return "5";
		if(a==4) return "4";
		if(a==3) return "3";
		if(a==2) return "2";
		if(a==1) return "1";
		if(a==0) return "0";
		return null;
	}
	
	/**
	 * 
	 * 
	 * @author EveryOne
	 * @version inner class to get data of jwalls
	 * @exception nothing
	 *
	 */
	public class SmallWallTO4{
		private boolean isVertical;
		private int rowSmall;
		private int columnSmall;
		SmallWallTO4(boolean isVertical, int rowSmall, int columnSmall){
			this.setColumnSmall(columnSmall);
			this.setRowSmall(rowSmall);
			this.setVertical(isVertical);
		}
		public boolean isVertical() {
			return isVertical;
		}
		public void setVertical(boolean isVertical) {
			this.isVertical = isVertical;
		}
		public int getRowSmall() {
			return rowSmall;
		}
		public void setRowSmall(int rowSmall) {
			this.rowSmall = rowSmall;
		}
		public int getColumnSmall() {
			return columnSmall;
		}
		public void setColumnSmall(int columnSmall) {
			this.columnSmall = columnSmall;
		}
	}
	
	public ArrayList<SmallWallTO4> getListOfSmallWallTO4(){
		ArrayList<SmallWallTO4> listOfGUIWall = new ArrayList<SmallWallTO4>();
		for(int i=0; i<10; i++) {
			if(JWhiteWallOnBoard[i]!=null) {
				boolean isVertical;
				int rowSmall;
				int columnSmall;
				if(JWhiteWallOnBoard[i].getHeight()==9) {
					isVertical = false;
					rowSmall = (JWhiteWallOnBoard[i].getLocation().y - 141) / 56;
					columnSmall = (JWhiteWallOnBoard[i].getLocation().x - 97) / 56;
				}else {
					isVertical = true;
					rowSmall = (JWhiteWallOnBoard[i].getLocation().y - 94) / 56;
					columnSmall = (JWhiteWallOnBoard[i].getLocation().x -144) / 56;
				}
				SmallWallTO4 a = new SmallWallTO4(isVertical, rowSmall, columnSmall);
				listOfGUIWall.add(a);
			}
		}
		for(int i=0; i<10; i++) {
			if(JYellowWallOnBoard[i]!=null) {
				boolean isVertical;
				int rowSmall;
				int columnSmall;
				if(JYellowWallOnBoard[i].getHeight()==9) {
					isVertical = false;
					rowSmall = (JYellowWallOnBoard[i].getLocation().y - 141) / 56;
					columnSmall = (JYellowWallOnBoard[i].getLocation().x - 97) / 56;
				}else {
					isVertical = true;
					rowSmall = (JYellowWallOnBoard[i].getLocation().y - 94) / 56;
					columnSmall = (JYellowWallOnBoard[i].getLocation().x -144) / 56;
				}
				SmallWallTO4 a = new SmallWallTO4(isVertical, rowSmall, columnSmall);
				listOfGUIWall.add(a);
			}
		}
		for(int i=0; i<10; i++) {
			if(JBlackWallOnBoard[i]!=null) {
				boolean isVertical;
				int rowSmall;
				int columnSmall;
				if(JBlackWallOnBoard[i].getHeight()==9) {
					isVertical = false;
					rowSmall = (JBlackWallOnBoard[i].getLocation().y - 141) / 56;
					columnSmall = (JBlackWallOnBoard[i].getLocation().x - 97) / 56;
				}else {
					isVertical = true;
					rowSmall = (JBlackWallOnBoard[i].getLocation().y - 94) / 56;
					columnSmall = (JBlackWallOnBoard[i].getLocation().x -144) / 56;
				}
				SmallWallTO4 a = new SmallWallTO4(isVertical, rowSmall, columnSmall);
				listOfGUIWall.add(a);
			}
		}
		for(int i=0; i<10; i++) {
			if(JRedWallOnBoard[i]!=null) {
				boolean isVertical;
				int rowSmall;
				int columnSmall;
				if(JRedWallOnBoard[i].getHeight()==9) {
					isVertical = false;
					rowSmall = (JRedWallOnBoard[i].getLocation().y - 141) / 56;
					columnSmall = (JRedWallOnBoard[i].getLocation().x - 97) / 56;
				}else {
					isVertical = true;
					rowSmall = (JRedWallOnBoard[i].getLocation().y - 94) / 56;
					columnSmall = (JRedWallOnBoard[i].getLocation().x -144) / 56;
				}
				SmallWallTO4 a = new SmallWallTO4(isVertical, rowSmall, columnSmall);
				listOfGUIWall.add(a);
			}
		}	
		return listOfGUIWall;
	}
}
