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
import ca.mcgill.ecse223.quoridor.controller.ReplayRefresh;
//import ca.mcgill.ecse223.quoridor.controller.nothing;
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
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 
 * Name : NewJBoard
 * 
 * @author EveryOne
 * @version this is our significant page
 * @exception nothing
 *
 */

public class NewJBoardReplay extends JFrame {

	private JPanel contentPane;
	private JPanel mainLayerPanel;
	private JTileR tile;
	private JUser users;
	private JButton setting;
	private JLabel whiteTurnGUI;
	/**
	 * 
	 * The below is generated automatically
	 */
	public JTileR getTile() {
		return tile;
	}
	public void setTile(JTileR tile) {
		this.tile = tile;
	}
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
	private JLabel whiteStock;
	public JLabel getWhiteStock() {
		return whiteStock;
	}
	public void setWhiteStock(JLabel whiteStock) {
		this.whiteStock = whiteStock;
	}
	public JLabel getBlackStock() {
		return blackStock;
	}
	public void setBlackStock(JLabel blackStock) {
		this.blackStock = blackStock;
	}

	private JLabel blackStock;
	
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
	
	public JWall[] getJWhiteWallOnBoard() {
		return JWhiteWallOnBoard;
	}
	public void setJWhiteWallOnBoard(JWall[] jWhiteWallOnBoard) {
		JWhiteWallOnBoard = jWhiteWallOnBoard;
	}
	public JWall[] getJBlackWallOnBoard() {
		return JBlackWallOnBoard;
	}
	public void setJBlackWallOnBoard(JWall[] jBlackWallOnBoard) {
		JBlackWallOnBoard = jBlackWallOnBoard;
	}

	private JWall[] JWhiteWallInStock = {jWallCandidate_1,jWallCandidate_2,jWallCandidate_3,jWallCandidate_4,jWallCandidate_5,jWallCandidate_6,jWallCandidate_7,jWallCandidate_8,jWallCandidate_9,jWallCandidate_10};
	public JWall[] getJWhiteWallInStock() {
		return JWhiteWallInStock;
	}
	public void setJWhiteWallInStock(JWall[] jWhiteWallInStock) {
		JWhiteWallInStock = jWhiteWallInStock;
	}
	public JWall[] getJBlackWallInStock() {
		return JBlackWallInStock;
	}
	public void setJBlackWallInStock(JWall[] jBlackWallInStock) {
		JBlackWallInStock = jBlackWallInStock;
	}

	private JWall[] JBlackWallInStock = {jWallCandidate_11,jWallCandidate_12,jWallCandidate_13,jWallCandidate_14,jWallCandidate_15,jWallCandidate_16,jWallCandidate_17,jWallCandidate_18,jWallCandidate_19,jWallCandidate_20};
	private JWall[] JWhiteWallOnBoard = new JWall[10];
	private JWall[] JBlackWallOnBoard = new JWall[10];
	
		
	private JWall jWallCandidate = null;
	
	public JWall getjWallCandidate() {
		return jWallCandidate;
	}
	public void setjWallCandidate(JWall jWallCandidate) {
		this.jWallCandidate = jWallCandidate;
	}

	private static final int MAX_WALL = 10;
	private int BLACK_WALL_INDEX = 0;//how many times you grabed before
	private int WHITE_WALL_INDEX = 0;
	
	private boolean isWhiteTurn = true;
	private JLabel instruction1;
	private JLabel instruction2;
	private JLabel instruction3;
	private JLabel instruction4;
	private JLabel lblWalls;
	private JLabel label;
	private JLabel lblNewLabel;
	private JButton btnStart;
	private JButton btnFinal;
	private JButton btnBack;
	private JButton btnNext;
	
	public boolean isWhiteTurn() {
		return isWhiteTurn;
	}
	public void setWhiteTurn(boolean isWhiteTurn) {
		this.isWhiteTurn = isWhiteTurn;
	}

	/**
	 * Create the frame.
	 */
	public NewJBoardReplay() {
		
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
				
		tile = new JTileR();
		tile.setBackground(Color.WHITE);
		tile.setBounds(0, 0, 560, 800);
		tile.setOpaque(false);
		mainLayerPanel.add(tile);
	
		JLabel monkey_White = new JLabel();
		monkey_White.setBounds(17, 17, 127, 120);
		monkey_White.setIcon(new ImageIcon(NewJBoard.class.getResource("/ca/mcgill/ecse223/quoridor/resources/monkey_128.png")));	
		
		JLabel cat_Black = new JLabel();
		cat_Black.setBounds(411, 17, 127, 120);
		cat_Black.setIcon(new ImageIcon(NewJBoard.class.getResource("/ca/mcgill/ecse223/quoridor/resources/cat_128.png")));		
		
		setting = new JButton("");
		setting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		setting.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				QuoridorApplication.getJboard().setVisible(false);
				QuoridorApplication.getMainMenu().setVisible(true);
			}
		});
		
		setting.setBounds(296, 135, 30, 28);
		setting.setIcon(new ImageIcon(NewJBoard.class.getResource("/ca/mcgill/ecse223/quoridor/resources/30-setting.png")));
		
		whiteTurnGUI = new JLabel("");
		whiteTurnGUI.setBounds(156, 94, 32, 32);
		whiteTurnGUI.setForeground(Color.YELLOW);
		whiteTurnGUI.setIcon(new ImageIcon(NewJBoard.class.getResource("/ca/mcgill/ecse223/quoridor/resources/32-wqueen.png")));
		whiteTurnGUI.setVisible(true);
		blackTurnGUI = new JLabel("");
		blackTurnGUI.setBounds(360, 94, 32, 32);
		blackTurnGUI.setIcon(new ImageIcon(NewJBoard.class.getResource("/ca/mcgill/ecse223/quoridor/resources/32-wqueen.png")));
		blackTurnGUI.setVisible(false);	
		
        whiteStock = new JLabel("10");
        whiteStock.setForeground(Color.WHITE);
        whiteStock.setBounds(166, 134, 16, 16);
               
        blackStock = new JLabel("10");
        blackStock.setForeground(Color.WHITE);
        blackStock.setBounds(393, 134, 16, 16);
		
		tile.setLayout(null);
						
		//ImageIcon whitePawn = new ImageIcon("/ca.mcgill.ecse223.quoridor/src/main/java/ca/mcgill/ecse223/quoridor/resources/wpawn.png");

		tile.add(monkey_White);
		tile.add(whiteStock);
		tile.add(setting);
		tile.add(whiteTurnGUI);
		tile.add(blackTurnGUI);
		tile.add(cat_Black);
		tile.add(blackStock);
		
		JUserR users2 = new JUserR();
		users2.setBounds(0, 0, 560, 800);
		users2.setOpaque(false);
		mainLayerPanel.add(users2);

        tile.setFocusable(true);
        
        
        lblWalls = new JLabel("Walls:");
        lblWalls.setForeground(Color.WHITE);
        lblWalls.setBounds(115, 134, 39, 16);
        tile.add(lblWalls);
        
        label = new JLabel("Walls:");
        label.setForeground(Color.WHITE);
        label.setBounds(353, 134, 39, 16);
        tile.add(label);
        
        lblNewLabel = new JLabel("Back ->");
        lblNewLabel.setBounds(223, 147, 61, 16);
        tile.add(lblNewLabel);
        
        btnStart = new JButton("Start");
        btnStart.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		QuoridorController.jumpToStart();
        		ReplayRefresh.refreshBoardInReplayMode();
        		ReplayRefresh.refreshBoardInReplayMode_wall();
        	}
        });
        
        btnStart.setBounds(211, 56, 61, 32);
        tile.add(btnStart);
        
        btnFinal = new JButton("Final");
        btnFinal.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		QuoridorController.jumpToFinal();
        		ReplayRefresh.refreshBoardInReplayMode();
        		ReplayRefresh.refreshBoardInReplayMode_wall();
        		
        	}
        });
        
        btnFinal.setBounds(274, 56, 61, 32);
        tile.add(btnFinal);
        
        btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		QuoridorController.stepBackward();
        		ReplayRefresh.refreshBoardInReplayMode();
        		ReplayRefresh.refreshBoardInReplayMode_wall();
        	}
        });
        btnBack.setBounds(211, 85, 61, 32);
        tile.add(btnBack);
        
        btnNext = new JButton("Next");
        btnNext.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		QuoridorController.stepForward();
        		ReplayRefresh.refreshBoardInReplayMode();
        		ReplayRefresh.refreshBoardInReplayMode_wall();
        	}
        });
        btnNext.setBounds(274, 85, 61, 32);
        tile.add(btnNext);
        
        JLabel lblReplay = new JLabel("Replay");
        lblReplay.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        lblReplay.setBounds(235, 16, 74, 28);
        tile.add(lblReplay);       

//		tile.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//			}			
//		}		
//				);
	}
	
	/**
	 * 
	 * 
	 * @author EveryOne
	 * @version convert int to string
	 * @exception nothing
	 *
	 */
	
	public String transferInt(int a) {
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
	public class SmallWallTO{
		private boolean isVertical;
		private int rowSmall;
		private int columnSmall;
		SmallWallTO(boolean isVertical, int rowSmall, int columnSmall){
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
	/**
	 * 
	 * 
	 * @author EveryOne
	 * @version get data of jwalls
	 * @exception nothing
	 *
	 */
	
	public ArrayList<SmallWallTO> getListOfSmallWallTO(){
		ArrayList<SmallWallTO> listOfGUIWall = new ArrayList<SmallWallTO>();
		for(int i=0; i<10; i++) {
			if(JBlackWallOnBoard[i]!=null) {
				boolean isVertical;
				int rowSmall;
				int columnSmall;
				if(JBlackWallOnBoard[i].getHeight()==9) {
					isVertical = false;
					rowSmall = (JBlackWallOnBoard[i].getLocation().y - 185) / 56;
					columnSmall = (JBlackWallOnBoard[i].getLocation().x + 30) / 56;
				}else {
					isVertical = true;
					rowSmall = (JBlackWallOnBoard[i].getLocation().y - 138) / 56;
					columnSmall = (JBlackWallOnBoard[i].getLocation().x -17) / 56;
				}
				SmallWallTO a = new SmallWallTO(isVertical, rowSmall, columnSmall);
				listOfGUIWall.add(a);
			}
		}
		
		for(int i=0; i<10; i++) {
			if(JWhiteWallOnBoard[i]!=null) {
				boolean isVertical;
				int rowSmall;
				int columnSmall;
				if(JWhiteWallOnBoard[i].getHeight()==9) {
					isVertical = false;
					rowSmall = (JWhiteWallOnBoard[i].getLocation().y - 185) / 56;
					columnSmall = (JWhiteWallOnBoard[i].getLocation().x + 30) / 56;
				}else {
					isVertical = true;
					rowSmall = (JWhiteWallOnBoard[i].getLocation().y - 138) / 56;
					columnSmall = (JWhiteWallOnBoard[i].getLocation().x -17) / 56;
				}
				SmallWallTO a = new SmallWallTO(isVertical, rowSmall, columnSmall);
				listOfGUIWall.add(a);
			}
		}
		return listOfGUIWall;
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
}