package ca.mcgill.ecse223.quoridor.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.quoridor.QuoridorApplication;
import ca.mcgill.ecse223.quoridor.controller.PathCheck;
import ca.mcgill.ecse223.quoridor.controller.QuoridorController;
import ca.mcgill.ecse223.quoridor.model.Direction;
import ca.mcgill.ecse223.quoridor.model.Quoridor;
import ca.mcgill.ecse223.quoridor.model.Wall;
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
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * Name : NewJBoard
 * 
 * @author EveryOne
 * @version this is our significant page
 * @exception nothing
 *
 */
public class NewJBoard extends JFrame {

	private JPanel contentPane;
	private JPanel mainLayerPanel;
	

	private JTile tile;
	private JUser users;
	private JLabel txtrSeconds;
	private JLabel whiteUser;
	private JLabel blackUser;
	private JLabel blackTime;
	private JLabel whiteTime;
	private JButton setting;
	private JLabel whiteTurnGUI;
	private SaveGameDialoge saveGame;
	public static int index = 1 ;
	
	public static void plusIndex() {
		index = index+1 ;
	}

	/**
	 * 
	 * The below is generated automatically
	 */
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
	
	private JWall[] JWhiteWallInStock = {jWallCandidate_1,jWallCandidate_2,jWallCandidate_3,jWallCandidate_4,jWallCandidate_5,jWallCandidate_6,jWallCandidate_7,jWallCandidate_8,jWallCandidate_9,jWallCandidate_10};
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
		saveGame = new SaveGameDialoge ();
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
		
		
		
		
		tile = new JTile();
		tile.setBackground(Color.WHITE);
		tile.setBounds(0, 0, 560, 800);
		tile.setOpaque(false);
		mainLayerPanel.add(tile);
		
		
		
		txtrSeconds = new JLabel();
		txtrSeconds.setBounds(235, 94, 64, 20);
		txtrSeconds.setBackground(new Color(245, 245, 220));
		txtrSeconds.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtrSeconds.setText("Seconds");
		
		
		
		JLabel monkey_White = new JLabel();
		monkey_White.setBounds(17, 17, 127, 120);
		monkey_White.setIcon(new ImageIcon(NewJBoard.class.getResource("/ca/mcgill/ecse223/quoridor/resources/monkey_128.png")));
		
		
		
		JLabel cat_Black = new JLabel();
		cat_Black.setBounds(411, 17, 127, 120);
		cat_Black.setIcon(new ImageIcon(NewJBoard.class.getResource("/ca/mcgill/ecse223/quoridor/resources/cat_128.png")));
		
		
		
		setting = new JButton("");
		setting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showOptionDialog(null, "Setting",
		                "Setting",
		                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Back to Main Menu", "Resign"}, "Back to Main Menu");
				if (result==0) {
					saveGame.setVisible(true);
//					QuoridorApplication.getJboard().setVisible(false);
//					QuoridorApplication.getMainMenu().setVisible(true);
				} else if(result==1) {
					QuoridorController.resign();
		
				}
			}
		});
		setting.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		setting.setBounds(296, 135, 30, 28);
		setting.setIcon(new ImageIcon(NewJBoard.class.getResource("/ca/mcgill/ecse223/quoridor/resources/30-setting.png")));
		
		
		
		whiteUser = new JLabel(QuoridorController.getWhiteName());
		whiteUser.setBounds(29, 135, 72, 15);
		whiteUser.setForeground(Color.WHITE);
		
		
		
		blackUser = new JLabel(QuoridorController.getBlackName());
		blackUser.setBounds(413, 135, 71, 15);
		blackUser.setForeground(Color.WHITE);
		
		
		
		bigTime = new JLabel(QuoridorController.getWhiteRemainingTime());
		bigTime.setBounds(221, 51, 91, 20);
		bigTime.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		bigTime.setText(QuoridorController.getWhiteRemainingTime());

		
		
		whiteTime = new JLabel(QuoridorController.getWhiteRemainingTime());
		whiteTime.setBounds(132, 38, 72, 15);
		whiteTime.setForeground(Color.WHITE);
		
		
		
		blackTime = new JLabel(QuoridorController.getBlackRemainingTime());
		blackTime.setBounds(360, 38, 39, 15);
		blackTime.setForeground(Color.WHITE);
		
		
		
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
		tile.add(whiteUser);
		tile.add(whiteStock);
		tile.add(bigTime);
		tile.add(setting);
		tile.add(whiteTurnGUI);
		tile.add(txtrSeconds);
		tile.add(whiteTime);
		tile.add(blackTime);
		tile.add(blackTurnGUI);
		tile.add(cat_Black);
		tile.add(blackUser);
		tile.add(blackStock);
		
		
		
		users = new JUser();
		users.setBounds(0, 0, 560, 800);
		users.setOpaque(false);
		mainLayerPanel.add(users);
		

		
		

        tile.setFocusable(true);
        
        instruction1 = new JLabel("Grab Wall: Press G");
        instruction1.setBounds(29, 714, 118, 16);
        tile.add(instruction1);
        
        instruction2 = new JLabel("Drop Wall: Press T");
        instruction2.setBounds(221, 714, 118, 16);
        tile.add(instruction2);
        
        instruction3 = new JLabel("Move Wall: Press W,A,S,D");
        instruction3.setBounds(29, 742, 313, 16);
        tile.add(instruction3);
        
        instruction4 = new JLabel("Flip Wall: Press R");
        instruction4.setBounds(379, 714, 118, 16);
        tile.add(instruction4);
        
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
        

		tile.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar()=='g') {	
					if(jWallCandidate==null) {
						if(isWhiteTurn) {
							if(WHITE_WALL_INDEX<MAX_WALL) {
								jWallCandidate = JWhiteWallInStock[WHITE_WALL_INDEX];
								mainLayerPanel.add(jWallCandidate);
								//getLayeredPane().add(jWallCandidate);
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
								mainLayerPanel.add(jWallCandidate);
								//getLayeredPane().add(jWallCandidate);
								jWallCandidate.setBackground(Color.GREEN);
								jWallCandidate.setBounds(297, 418, 9, 103);
								jWallCandidate.setVisible(true);
								BLACK_WALL_INDEX++;
							}else {
								JOptionPane.showMessageDialog(null, "You have no more black walls in hand!");
							}
						}
						try {
							QuoridorController.grabWall();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
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
						if(!QuoridorController.verifyOverlapped(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate())&&
								PathCheck.pathCheck().equals("both")) 
						{
							if(isWhiteTurn) {
								int x = jWallCandidate.getLocation().x;
								int y = jWallCandidate.getLocation().y;
								JWhiteWallInStock[WHITE_WALL_INDEX-1].setLocation(x, y);
								JWhiteWallInStock[WHITE_WALL_INDEX-1].setBackground(Color.BLUE);
								JWhiteWallInStock[WHITE_WALL_INDEX-1].setVisible(true);
								JWhiteWallOnBoard[WHITE_WALL_INDEX-1] = JWhiteWallInStock[WHITE_WALL_INDEX-1];
								whiteStock.setText(transferInt(10-WHITE_WALL_INDEX));
								whiteTurnGUI.setVisible(false);
								blackTurnGUI.setVisible(true);
								boolean isVertical;
								int rowSmall;
								int columnSmall;
								if(jWallCandidate.getHeight()==9) {
									isVertical = false;
									rowSmall = (jWallCandidate.getLocation().y - 185) / 56;
									columnSmall = (jWallCandidate.getLocation().x + 30) / 56;
								}else {
									isVertical = true;
									rowSmall = (jWallCandidate.getLocation().y - 138) / 56;
									columnSmall = (jWallCandidate.getLocation().x -17) / 56;
								}
								if(!QuoridorController.checkFileExistence("save_game_test.mov")) {
									try {
										QuoridorController.creatNewFile("save_game_test.mov");
										Quoridor quoridor = QuoridorApplication.getQuoridor();
										BufferedWriter writer = new BufferedWriter(new FileWriter("save_game_test.mov"));
										String characters = new String("abcdefghi");
										ArrayList<Character> characterList = new ArrayList<Character>();
										for(int i = 0; i<characters.length(); i++){
											characterList.add(characters.charAt(i));
										}

										String blackPositionToWrite = ""+index+". " ;
										int playerColumn = columnSmall;
										int playerRow = rowSmall;
										Character blackColumnToWrite = characterList.get(playerColumn-1);
										String blackRowToWrite = Integer.toString(playerRow) ;
										blackPositionToWrite = blackPositionToWrite + blackColumnToWrite + blackRowToWrite ;										
											
										String wallDirectionToWrite = "" ;
										if(isVertical) {
											wallDirectionToWrite = "v";
										}else{
											wallDirectionToWrite = "h";
										}
										blackPositionToWrite = blackPositionToWrite + wallDirectionToWrite ;
										writer.write(blackPositionToWrite);	
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
									
									
									File file = new File("save_game_test.mov");
									FileWriter fr = null;
									BufferedWriter br = null;
									try {
										// to append to file, you need to initialize FileWriter using below constructor
										fr = new FileWriter(file, true);
										br = new BufferedWriter(fr);
										Quoridor quoridor = QuoridorApplication.getQuoridor();
										String characters = new String("abcdefghi");
										ArrayList<Character> characterList = new ArrayList<Character>();
										for(int i = 0; i<characters.length(); i++){
											characterList.add(characters.charAt(i));
										}
										String blackPositionToWrite = ""+index+". " ;
										int playerColumn = columnSmall;
										int playerRow = rowSmall;
										Character blackColumnToWrite = characterList.get(playerColumn-1);
										String blackRowToWrite = Integer.toString(playerRow) ;
										blackPositionToWrite = blackPositionToWrite + blackColumnToWrite + blackRowToWrite ;
										//br.newLine();											
										String wallDirectionToWrite = "" ;
										if(isVertical) {
											wallDirectionToWrite = "v";
										}else{
											wallDirectionToWrite = "h";
										}
										blackPositionToWrite = blackPositionToWrite + wallDirectionToWrite ;
										br.write(blackPositionToWrite);
									} catch (IOException e1) {
										e1.printStackTrace();
									} finally {
										try {
											br.close();
											fr.close();
										} catch (IOException e1) {
											e1.printStackTrace();
										}
									}
									
									
									
								}else {
									File file = new File("save_game_test.mov");
									FileWriter fr = null;
									BufferedWriter br = null;
									try {
										// to append to file, you need to initialize FileWriter using below constructor
										fr = new FileWriter(file, true);
										br = new BufferedWriter(fr);
										Quoridor quoridor = QuoridorApplication.getQuoridor();
										String characters = new String("abcdefghi");
										ArrayList<Character> characterList = new ArrayList<Character>();
										for(int i = 0; i<characters.length(); i++){
											characterList.add(characters.charAt(i));
										}
										String blackPositionToWrite = ""+index+". " ;
										int playerColumn = columnSmall;
										int playerRow = rowSmall;
										Character blackColumnToWrite = characterList.get(playerColumn-1);
										String blackRowToWrite = Integer.toString(playerRow) ;
										blackPositionToWrite = blackPositionToWrite + blackColumnToWrite + blackRowToWrite ;
										br.newLine();											
										String wallDirectionToWrite = "" ;
										if(isVertical) {
											wallDirectionToWrite = "v";
										}else{
											wallDirectionToWrite = "h";
										}
										blackPositionToWrite = blackPositionToWrite + wallDirectionToWrite ;
										br.write(blackPositionToWrite);
									} catch (IOException e1) {
										e1.printStackTrace();
									} finally {
										try {
											br.close();
											fr.close();
										} catch (IOException e1) {
											e1.printStackTrace();
										}
									}
								}
								
							}else {
								int x = jWallCandidate.getLocation().x;
								int y = jWallCandidate.getLocation().y;
								JBlackWallInStock[BLACK_WALL_INDEX-1].setLocation(x, y);
								JBlackWallInStock[BLACK_WALL_INDEX-1].setBackground(Color.BLUE);
								JBlackWallInStock[BLACK_WALL_INDEX-1].setVisible(true);
								JBlackWallOnBoard[BLACK_WALL_INDEX-1] = JBlackWallInStock[BLACK_WALL_INDEX-1];
								blackStock.setText(transferInt(10-BLACK_WALL_INDEX));
								whiteTurnGUI.setVisible(true);
								blackTurnGUI.setVisible(false);
								
								boolean isVertical;
								int rowSmall;
								int columnSmall;
								if(jWallCandidate.getHeight()==9) {
									isVertical = false;
									rowSmall = (jWallCandidate.getLocation().y - 185) / 56;
									columnSmall = (jWallCandidate.getLocation().x + 30) / 56;
								}else {
									isVertical = true;
									rowSmall = (jWallCandidate.getLocation().y - 138) / 56;
									columnSmall = (jWallCandidate.getLocation().x -17) / 56;
								}
								if(!QuoridorController.checkFileExistence("save_game_test.mov")) {
									try {
										QuoridorController.creatNewFile("save_game_test.mov");
										Quoridor quoridor = QuoridorApplication.getQuoridor();
										BufferedWriter writer = new BufferedWriter(new FileWriter("save_game_test.mov"));
										String characters = new String("abcdefghi");
										ArrayList<Character> characterList = new ArrayList<Character>();
										for(int i = 0; i<characters.length(); i++){
											characterList.add(characters.charAt(i));
										}

										index++;
										String blackPositionToWrite = " " ;
										int playerColumn = columnSmall;
										int playerRow = rowSmall;
										Character blackColumnToWrite = characterList.get(playerColumn-1);
										String blackRowToWrite = Integer.toString(playerRow) ;
										blackPositionToWrite = blackPositionToWrite + blackColumnToWrite + blackRowToWrite ;									
											
										String wallDirectionToWrite = "" ;
										if(isVertical) {
											wallDirectionToWrite = "v";
										}else{
											wallDirectionToWrite = "h";
										}
										blackPositionToWrite = blackPositionToWrite + wallDirectionToWrite ;
										writer.write(blackPositionToWrite);	
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}else {
									File file = new File("save_game_test.mov");
									FileWriter fr = null;
									BufferedWriter br = null;
									try {
										// to append to file, you need to initialize FileWriter using below constructor
										fr = new FileWriter(file, true);
										br = new BufferedWriter(fr);
										Quoridor quoridor = QuoridorApplication.getQuoridor();
										String characters = new String("abcdefghi");
										ArrayList<Character> characterList = new ArrayList<Character>();
										for(int i = 0; i<characters.length(); i++){
											characterList.add(characters.charAt(i));
										}
										index++;
										String blackPositionToWrite = " " ;
										int playerColumn = columnSmall;
										int playerRow = rowSmall;
										Character blackColumnToWrite = characterList.get(playerColumn-1);
										String blackRowToWrite = Integer.toString(playerRow) ;
										blackPositionToWrite = blackPositionToWrite + blackColumnToWrite + blackRowToWrite ;
										String wallDirectionToWrite = "" ;
										if(isVertical) {
											wallDirectionToWrite = "v";
										}else{
											wallDirectionToWrite = "h";
										}
										blackPositionToWrite = blackPositionToWrite + wallDirectionToWrite ;
										br.write(blackPositionToWrite);	
									} catch (IOException e1) {
										e1.printStackTrace();
									} finally {
										try {
											br.close();
											fr.close();
										} catch (IOException e1) {
											e1.printStackTrace();
										}
									}
								}
							}
							jWallCandidate = null;
							isWhiteTurn = !isWhiteTurn;
						}	
						try {
							QuoridorController.releaseWall();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
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
	public JTile getTile() {
		return tile;
	}
	public void setTile(JTile tile) {
		this.tile = tile;
	}


	
}