package ca.mcgill.ecse223.quoridor.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.quoridor.QuoridorApplication;
import ca.mcgill.ecse223.quoridor.controller.QuoridorController;
import ca.mcgill.ecse223.quoridor.view.Pawn.PawnColor;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NewJBoard extends JFrame {

	private JPanel contentPane;
	private JPanel mainLayerPanel;
	private JTile tile;
	private JUser users;
	private JLabel txtrSeconds;
	private JLabel whiteUser;
	private JLabel blackUser;
	private JTextArea whiteStock;
	private JLabel blackTime;
	private JLabel whiteTime;
	private JTextArea blackStock;
	private JButton setting;
	private JLabel whiteTurnGUI;
	public JLabel getWhiteTurnGUI() {
		return whiteTurnGUI;
	}
	public void setWhiteTurnGUI(JLabel whiteTurnGUI) {
		this.whiteTurnGUI = whiteTurnGUI;
	}
	public JLabel getBlackTurnGUI() {
		return blackTurnGUI;
	}
	public void setBlackTurnGUI(JLabel blackTurnGUI) {
		this.blackTurnGUI = blackTurnGUI;
	}

	private JLabel blackTurnGUI;
	private JLabel bigTime;
	
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
	
	private JWall[] JWhiteWallInStock = {jWallCandidate_1,jWallCandidate_2,jWallCandidate_3,jWallCandidate_4,jWallCandidate_5,jWallCandidate_6,jWallCandidate_7,jWallCandidate_8,jWallCandidate_9,jWallCandidate_10};
	private JWall[] JBlackWallInStock = {jWallCandidate_11,jWallCandidate_12,jWallCandidate_13,jWallCandidate_14,jWallCandidate_15,jWallCandidate_16,jWallCandidate_17,jWallCandidate_18,jWallCandidate_19,jWallCandidate_20};
	
	private JWall jWallCandidate = null;
	
	private static final int MAX_WALL = 10;
	private int BLACK_WALL_INDEX = 0;//how many times you grabed before
	private int WHITE_WALL_INDEX = 0;
	
	private boolean isWhiteTurn = true;
	

	public boolean isWhiteTurn() {
		return isWhiteTurn;
	}
	public void setWhiteTurn(boolean isWhiteTurn) {
		this.isWhiteTurn = isWhiteTurn;
	}





	/**
	 * Create the frame.
	 */
	public NewJBoard() {
		
		System.out.println(jWallCandidate_1.getLocation().x+" and "+jWallCandidate_1.getLocation().y);

		
		for(int i=0; i<10; i++) {
			JWhiteWallInStock[i].setVisible(false);
		}
		for(int i=0; i<10; i++) {
			JBlackWallInStock[i].setVisible(false);
		}
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 560, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		
		
		mainLayerPanel = new JPanel();		  
		contentPane.add(mainLayerPanel, BorderLayout.CENTER);		 
		mainLayerPanel.setBorder(BorderFactory.createLineBorder(Color.orange, 2));
		mainLayerPanel.setLayout(null);
		
		
		
		
//		Pawn whitePawn = new Pawn(PawnColor.WHITE);
//		whitePawn.setBounds(310, 540, 50, 50);
//		whitePawn.setVisible(true);
//		mainLayerPanel.add(whitePawn);
//
//
//		Pawn blackPawn = new Pawn(PawnColor.BLACK);
//		blackPawn.setBounds(310, 60, 50, 50);
//		blackPawn.setVisible(true);
//		mainLayerPanel.add(blackPawn);
		
		
		
		
		tile = new JTile();
		tile.setBackground(Color.WHITE);
		tile.setBounds(0, 0, 560, 800);
		tile.setOpaque(false);
		mainLayerPanel.add(tile);
		
		
		
//		txtrSeconds = new JLabel();
//		txtrSeconds.setBounds(243, 115, 64, 20);
//		txtrSeconds.setBackground(new Color(245, 245, 220));
//		txtrSeconds.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
//		txtrSeconds.setText("Seconds");
//		
//		
//		
		JLabel monkey_White = new JLabel();
		monkey_White.setBounds(17, 17, 127, 120);
		monkey_White.setIcon(new ImageIcon(NewJBoard.class.getResource("/ca/mcgill/ecse223/quoridor/resources/monkey_128.png")));
		
		
		
		JLabel cat_Black = new JLabel();
		cat_Black.setBounds(403, 17, 127, 120);
		cat_Black.setIcon(new ImageIcon(NewJBoard.class.getResource("/ca/mcgill/ecse223/quoridor/resources/cat_128.png")));
		
		
		
		setting = new JButton("");
		setting.setBounds(296, 135, 30, 28);
		setting.setIcon(new ImageIcon(NewJBoard.class.getResource("/ca/mcgill/ecse223/quoridor/resources/30—setting.png")));
//		
//		
//		
//		whiteUser = new JLabel(QuoridorController.getWhiteName());
//		whiteUser.setBounds(29, 135, 72, 15);
//		whiteUser.setForeground(Color.WHITE);
//		
//		
//		
//		blackUser = new JLabel(QuoridorController.getBlackName());
//		blackUser.setBounds(512, 176, 36, 15);
//		blackUser.setForeground(Color.WHITE);
//		
//		
//		
//		bigTime = new JLabel(QuoridorController.getWhiteRemainingTime());
//		bigTime.setBounds(235, 49, 91, 20);
//		bigTime.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
//		
//		
//		
//		whiteStock = new JTextArea();
//		whiteStock.setBounds(143, 118, 69, 15);
//		whiteStock.setText(QuoridorController.getWhiteStocks());
//		
//		
//		
//		blackStock = new JTextArea();
//		blackStock.setBounds(400, 164, 44, 15);
//		blackStock.setText(QuoridorController.getBlackStocks());
//		
//		
//		
//		whiteTime = new JLabel(QuoridorController.getWhiteRemainingTime());
//		whiteTime.setBounds(157, 38, 72, 15);
//		whiteTime.setForeground(Color.WHITE);
//		
//		
//		
//		blackTime = new JLabel(QuoridorController.getBlackRemainingTime());
//		blackTime.setBounds(400, 38, 39, 15);
//		blackTime.setForeground(Color.WHITE);
		
		
		
		whiteTurnGUI = new JLabel("");
		whiteTurnGUI.setBounds(156, 105, 32, 32);
		whiteTurnGUI.setForeground(Color.YELLOW);
		whiteTurnGUI.setIcon(new ImageIcon(NewJBoard.class.getResource("/ca/mcgill/ecse223/quoridor/resources/32—wqueen.png")));
		whiteTurnGUI.setVisible(true);
		blackTurnGUI = new JLabel("");
		blackTurnGUI.setBounds(360, 94, 32, 32);
		blackTurnGUI.setIcon(new ImageIcon(NewJBoard.class.getResource("/ca/mcgill/ecse223/quoridor/resources/32—wqueen.png")));
		blackTurnGUI.setVisible(false);
		
		
		
		
		tile.setLayout(null);
		
		
		
		//ImageIcon whitePawn = new ImageIcon("/ca.mcgill.ecse223.quoridor/src/main/java/ca/mcgill/ecse223/quoridor/resources/wpawn.png");
		
		
		
		
		
		tile.add(monkey_White);
//		tile.add(whiteUser);
//		tile.add(bigTime);
//		tile.add(whiteStock);
		tile.add(setting);
		tile.add(whiteTurnGUI);
//		tile.add(txtrSeconds);
//		tile.add(whiteTime);
//		tile.add(blackStock);
//		tile.add(blackTime);
		tile.add(blackTurnGUI);
		tile.add(cat_Black);
//		tile.add(blackUser);
		
		
		
		users = new JUser();
		users.setBounds(0, 0, 560, 800);
		users.setOpaque(false);
		mainLayerPanel.add(users);
		
		

        tile.setFocusable(true);
		tile.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar()=='g') {	
					if(jWallCandidate==null) {
						if(isWhiteTurn) {
							if(WHITE_WALL_INDEX<MAX_WALL) {
								jWallCandidate = JWhiteWallInStock[WHITE_WALL_INDEX];
								mainLayerPanel.add(jWallCandidate);//????
								jWallCandidate.setBackground(Color.BLACK);
								jWallCandidate.setBounds(297, 418, 9, 103);
								jWallCandidate.setVisible(true);	
								WHITE_WALL_INDEX++;
							}else {
								JOptionPane.showMessageDialog(null, "You have no more white walls in hand!");
							}
							
						}
						else {
							if(BLACK_WALL_INDEX<MAX_WALL) {
								jWallCandidate = JBlackWallInStock[BLACK_WALL_INDEX];
								mainLayerPanel.add(jWallCandidate);//????
								jWallCandidate.setBackground(Color.GREEN);
								jWallCandidate.setBounds(297, 418, 9, 103);
								jWallCandidate.setVisible(true);
								BLACK_WALL_INDEX++;
							}else {
								JOptionPane.showMessageDialog(null, "You have no more black walls in hand!");
							}
						}
						QuoridorController.grabWall();	
					}else {
						jWallCandidate.setVisible(false);
						jWallCandidate=null;
						if(isWhiteTurn) {
							WHITE_WALL_INDEX--;
							System.out.println("remove white");
						}else {
							BLACK_WALL_INDEX--;
							System.out.println("remove black");
						}
					}
				}
				// up
				if ((e.getKeyChar()=='w')||(e.getKeyCode() == KeyEvent.VK_UP)) {
					if(jWallCandidate!=null) {
						QuoridorController.MoveWall("up");
						int x = jWallCandidate.getLocation().x;
						int y = jWallCandidate.getLocation().y;
						if(jWallCandidate.getHeight()==103) {
							if(y>=250) {
								jWallCandidate.setLocation(x, y-56);
							}
						}
						else{
							if(y>=288) {
								jWallCandidate.setLocation(x, y-56);
							}
						}
					}
				}
				if ((e.getKeyChar()=='a')||(e.getKeyCode() == KeyEvent.VK_LEFT)) {
					if(jWallCandidate!=null) {
						QuoridorController.MoveWall("left");
						int x = jWallCandidate.getLocation().x;
						int y = jWallCandidate.getLocation().y;
						if(jWallCandidate.getHeight()==103) {
							if(x>=129) {
								jWallCandidate.setLocation(x-56, y);
							}
						}
						else{
							if(x>=82) {
								jWallCandidate.setLocation(x-56, y);
							}
						}
					}
				}
				if ((e.getKeyChar()=='s')||(e.getKeyCode() == KeyEvent.VK_DOWN)) {
					if(jWallCandidate!=null) {
						QuoridorController.MoveWall("down");
						int x = jWallCandidate.getLocation().x;
						int y = jWallCandidate.getLocation().y;
						if(jWallCandidate.getHeight()==103) {
							if(y<=530) {
								jWallCandidate.setLocation(x, y+56);
							}
						}
						else{
							if(y<=577) {
								jWallCandidate.setLocation(x, y+56);
							}
						}
					}
				}
				if ((e.getKeyChar()=='d')||(e.getKeyCode() == KeyEvent.VK_RIGHT)) {
					if(jWallCandidate!=null) {
						QuoridorController.MoveWall("right");
						int x = jWallCandidate.getLocation().x;
						int y = jWallCandidate.getLocation().y;
						if(jWallCandidate.getHeight()==103) {
							if(x<=409) {
								jWallCandidate.setLocation(x+56, y);
							}
						}
						else{
							if(x<=362) {
								jWallCandidate.setLocation(x+56, y);
							}
						}
					}
				}
				if (e.getKeyChar()=='r') {
					if(jWallCandidate!=null) {
						QuoridorController.flipWall();
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
				if (e.getKeyChar()=='t') if (e.getKeyChar()=='t') {
					if(jWallCandidate!=null) {
						if(QuoridorController.verifyOverlapped(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate())==false) 
						{
							if(isWhiteTurn) {
								int x = jWallCandidate.getLocation().x;
								int y = jWallCandidate.getLocation().y;
								JWhiteWallInStock[WHITE_WALL_INDEX-1].setLocation(x, y);
								JWhiteWallInStock[WHITE_WALL_INDEX-1].setBackground(Color.BLUE);
								JWhiteWallInStock[WHITE_WALL_INDEX-1].setVisible(true);
								whiteTurnGUI.setVisible(false);
								blackTurnGUI.setVisible(true);
							}else {
								int x = jWallCandidate.getLocation().x;
								int y = jWallCandidate.getLocation().y;
								JBlackWallInStock[BLACK_WALL_INDEX-1].setLocation(x, y);
								JBlackWallInStock[BLACK_WALL_INDEX-1].setBackground(Color.BLUE);
								JBlackWallInStock[BLACK_WALL_INDEX-1].setVisible(true);
								whiteTurnGUI.setVisible(true);
								blackTurnGUI.setVisible(false);
							}
							jWallCandidate = null;
							isWhiteTurn = !isWhiteTurn;
						}	
						QuoridorController.releaseWall();
					}
				}
			}

			
		});
	}
	
	
	
	

	/**
	 * @param whiteUser the whiteUser to set
	 */
	public void setWhiteUser(JLabel whiteUser) {
		this.whiteUser = whiteUser;
	}

	/**
	 * @param blackUser the blackUser to set
	 */
	public void setBlackUser(JLabel blackUser) {
		this.blackUser = blackUser;
	}

	/**
	 * @param whiteStock the whiteStock to set
	 */
	public void setWhiteStock(JTextArea whiteStock) {
		this.whiteStock = whiteStock;
	}

	/**
	 * @param blackTime the blackTime to set
	 */
	public void setBlackTime(JLabel blackTime) {
		this.blackTime = blackTime;
	}

	/**
	 * @param whiteTime the whiteTime to set
	 */
	public void setWhiteTime(JLabel whiteTime) {
		this.whiteTime = whiteTime;
	}

	/**
	 * @param blackStock the blackStock to set
	 */
	public void setBlackStock(JTextArea blackStock) {
		this.blackStock = blackStock;
	}

	/**
	 * @param whitePawn the whitePawn to set
	 */
	public void setWhitePawn(JLabel whitePawn) {
		this.whiteTurnGUI = whitePawn;
	}

	/**
	 * @param blackPawn the blackPawn to set
	 */
	public void setBlackPawn(JLabel blackPawn) {
		this.blackTurnGUI = blackPawn;
	}

	public JLabel getwhitePawn() {
		return whiteTurnGUI;
	}

	public void setwhitePawn(JLabel whitePawn) {
		this.whiteTurnGUI = whitePawn;
	}

	public JLabel getblackPawn() {
		return blackTurnGUI;
	}

	public void setblackPawn(JLabel blackPawn) {
		this.blackTurnGUI = blackPawn;
	}

	/**
	 * @param bigTime the bigTime to set
	 */
	public void setBigTime(JLabel bigTime) {
		this.bigTime = bigTime;
	}
	/**
	 * @return the whiteUser
	 */
	public JLabel getWhiteUser() {
		return whiteUser;
	}

	/**
	 * @return the whiteStock
	 */
	public JTextArea getWhiteStock() {
		return whiteStock;
	}

	/**
	 * @return the whiteTime
	 */
	public JLabel getWhiteTime() {
		return whiteTime;
	}

	/**
	 * @return the whitePawn
	 */
	public JLabel getWhitePawn() {
		return whiteTurnGUI;
	}

	/**
	 * @return the bigTime
	 */
	public JLabel getBigTime() {
		return bigTime;
	}
	
	public JLabel getwhiteUser() {
		return whiteUser;
	}

	public void setwhiteUser(JLabel whiteUser) {
		this.whiteUser = whiteUser;
	}

	public JLabel getblackUser() {
		return blackUser;
	}

	public void setblackUser(JLabel blackUser) {
		this.blackUser = blackUser;
	}

	public JLabel getblackTime() {
		return blackTime;
	}

	public void setblackTime(JLabel blackTime) {
		this.blackTime = blackTime;
	}

	public JLabel getwhiteTime() {
		return whiteTime;
	}

	public void setwhiteTime(JLabel whiteTime) {
		this.whiteTime = whiteTime;
	}

	public JLabel getTxtrSeconds() {
		return txtrSeconds;
	}

	public void setTxtrSeconds(JLabel txtrSeconds) {
		this.txtrSeconds = txtrSeconds;
	}

	public JTextArea getwhiteStock() {
		return whiteStock;
	}

	public void setwhiteStock(JTextArea whiteStock) {
		this.whiteStock = whiteStock;
	}

	public JTextArea getblackStock() {
		return blackStock;
	}

	public void setblackStock(JTextArea blackStock) {
		this.blackStock = blackStock;
	}
}