package ca.mcgill.ecse223.quoridor.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.quoridor.QuoridorApplication;
import ca.mcgill.ecse223.quoridor.controller.QuoridorController;
import ca.mcgill.ecse223.quoridor.controller.Stopwatch;
import ca.mcgill.ecse223.quoridor.model.Board;
import ca.mcgill.ecse223.quoridor.model.Direction;
import ca.mcgill.ecse223.quoridor.model.Game;
import ca.mcgill.ecse223.quoridor.model.GamePosition;
import ca.mcgill.ecse223.quoridor.model.Player;
import ca.mcgill.ecse223.quoridor.model.PlayerPosition;
import ca.mcgill.ecse223.quoridor.model.Quoridor;
import ca.mcgill.ecse223.quoridor.model.Tile;
import ca.mcgill.ecse223.quoridor.model.User;
import ca.mcgill.ecse223.quoridor.model.Wall;
import ca.mcgill.ecse223.quoridor.model.WallMove;
import ca.mcgill.ecse223.quoridor.model.Game.GameStatus;
import ca.mcgill.ecse223.quoridor.model.Game.MoveMode;
import ca.mcgill.ecse223.quoridor.view.Pawn.PawnColor;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.LayoutStyle.ComponentPlacement;


public class JBoard extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5127583580230421217L;
//	private QuoridorApplication Application;
	private JPanel contentPane;
	private JTextField display_number_black_stock;
	private JTextField display_number_white_stock;
	private int WALL_INDEX = 0;
	private int BLACK_WALL_INDEX = -1;
	private int WHITE_WALL_INDEX = 0;
	private static final int MAX_WALL =20;
	private List <JWall> WhiteWallList = new ArrayList<JWall>(); 
	public void setWhiteWallList(List<JWall> whiteWallList) {
		WhiteWallList = whiteWallList;
	}
	public void setBlackWallList(List<JWall> blackWallList) {
		BlackWallList = blackWallList;
	}
	public void GrabAllWalls() {
		WhiteWallList.removeAll(WhiteWallList);
		BlackWallList.removeAll(BlackWallList);
	}
	public List<JWall> getWhiteWallList() {
		return WhiteWallList;
	}
	public List<JWall> getBlackWallList() {
		return BlackWallList;
	}
	private List <JWall> BlackWallList = new ArrayList<JWall>();
	private JWall jwall;
	public boolean whiteTurn;
	private JButton SaveGameButton;
	private Player whitePlayer, blackPlayer, currentPlayer;
	public JWall getJwall() {
		return jwall;
	}
	public void setJwall(JWall jwall) {
		this.jwall = jwall;
	}
	private JPanel mainLayerPanel;
	public Pawn blackPawnMove;
	public Pawn whitePawnMove;
	private JTile tile;

	private JOptionPane errorHint;
	private String error = null;
	private String showTurn = null;
	private boolean grab = false;
	private JLabel lblBlackStock;
	private JLabel lblWhiteBlock;
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public JOptionPane getErrorHint() {
		return errorHint;
	}
	public void setErrorHint(JOptionPane errorHint) {
		this.errorHint = errorHint;
	}
	public void notifyIllegal() {
		this.setError("I will give a dialog immediately you release a illegal wall, So do not worry if you notice there are some dialogs when you running the JUnit Test! Just close it!");
		this.errorHint.showMessageDialog(null, error);
	}
	
	public void notifyIllegal2() {
		this.setError("No more walls in hand!");
		this.errorHint.showMessageDialog(null, error);
	}
	
	public String getTurn() {
		return showTurn;
	}
	public void setTurn(String showTurn) {
		this.showTurn = showTurn;
	}
	public void whiteTurn() {
		this.setTurn("It is white player's turn");
		this.errorHint.showMessageDialog(null, showTurn);
	}
	public void blackTurn() {
		this.setTurn("It is black player's turn");
		this.errorHint.showMessageDialog(null, showTurn);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JBoard frame = new JBoard();
					frame.setVisible(true);
				   // QuoridorController.refreshAndSet();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	
	public void ChangeGrabWall() {
	//	if (QuoridorApplication.getQuoridor().getCurrentGame().hasWallMoveCandidate()) {
			jwall.setBounds(360, 300, 10, 110);
			jwall.setBackground(Color.blue);
	//	}
	}
	
	public void ChangeDropWall() {
		mainLayerPanel.remove(jwall);
		mainLayerPanel.add(jwall);
		jwall.setBackground(Color.MAGENTA);
	}
	
//	public void ChangeDropWall() {
//		if(WALL_INDEX<MAX_WALL&&grab) {
////			jwall.setBackground(Color.MAGENTA);
////			mainLayerPanel.remove(jwall);
////			mainLayerPanel.add(jwall);
////			WALL_INDEX++;
//			ChangeDropWall();
//			if(WALL_INDEX<MAX_WALL) {
//				jwall = WallList.get(WALL_INDEX);
//			}
//			grab = false;
//			whiteTurn = !whiteTurn;
//			mainLayerPanel.add(blackPawnMove);
//			mainLayerPanel.add(whitePawnMove);
//			whitePawnMove.setVisible(whiteTurn);	
//			blackPawnMove.setVisible(!whiteTurn);						
//		}
//	}


	public JBoard() {
//		this.Application = Application;
		setFocusable(true);

		//Init
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		getContentPane().add(contentPane);



		//Create new panel to control layers
		mainLayerPanel = new JPanel();		  
		contentPane.add(mainLayerPanel, BorderLayout.CENTER);		 
		mainLayerPanel.setBorder(BorderFactory.createLineBorder(Color.orange, 2));
		mainLayerPanel.setLayout(null);



		//add tile(with GroupLayout) to the mainLayerPanel
		tile = new JTile();
		tile.setBackground(Color.WHITE);
		tile.setBounds(0, 0, 1000, 800);
		tile.setOpaque(false);
		mainLayerPanel.add(tile);

		Pawn whitePawn = new Pawn(PawnColor.WHITE);
		whitePawn.setBounds(310, 540, 50, 50);
		whitePawn.setVisible(true);
		mainLayerPanel.add(whitePawn);


		Pawn blackPawn = new Pawn(PawnColor.BLACK);
		blackPawn.setBounds(310, 60, 50, 50);
		blackPawn.setVisible(true);
		mainLayerPanel.add(blackPawn);



		//create four labels to specify the operation and set the layout for tile
		JLabel lblGrabWallPress = new JLabel("Grab Wall: Press G");
		JLabel lblRotateWallPress = new JLabel("Rotate Wall: Press R");
		JLabel lblMoveWallPress = new JLabel("Move Wall: Press W,A,S,D");
		JLabel lblDropWallPress = new JLabel("Drop Wall: Press T");

		whitePlayer = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
		blackPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
		//Add two pawns over here
		//Set the one's turn is visible
		JLabel playerToMove = new JLabel("WHITE");
		playerToMove.setBounds(650, 550, 100, 50);
		mainLayerPanel.add(playerToMove);
		

		JLabel playerToMove1 = new JLabel("BLACK");
		playerToMove1.setBounds(700, 550, 100, 50);
		mainLayerPanel.add(playerToMove1);
		
//		JLabel whiteTime = new JLabel("3:00");
		JLabel whiteTime = new JLabel(whitePlayer.getRemainingTime().getMinutes()+
				":"+ whitePlayer.getRemainingTime().getSeconds());
		whiteTime.setBounds(650, 570, 100, 50);
		mainLayerPanel.add(whiteTime);
		

//		JLabel blackTime = new JLabel("3:00");
		JLabel blackTime = new JLabel(whitePlayer.getRemainingTime().getMinutes()+
				":"+ whitePlayer.getRemainingTime().getSeconds());
		blackTime.setBounds(700, 570, 100, 50);
		mainLayerPanel.add(blackTime);

		whitePawnMove = new Pawn(PawnColor.WHITE);
		whitePawnMove.setBounds(650, 500, 50, 50);
	
		blackPawnMove = new Pawn(PawnColor.BLACK);
		blackPawnMove.setBounds(700, 500, 50, 50);
		blackPawnMove.setVisible(false);
		blackPawnMove.setVisible(false);
		

		SaveGameButton = new JButton("Save and Back");
		SaveGameButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				SaveGameDialoge s = new SaveGameDialoge();
				s.setVisible(true);


				//new MainMenu().setVisible(true);// I am not sure!!!
				setVisible(false);
			}
		});
		
		lblBlackStock = new JLabel("black stock");
		
		lblWhiteBlock = new JLabel("white stock");
		
		String player = "";
		currentPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
		if (currentPlayer.hasGameAsBlack()) {
			player = "Black";
		} else {
			player = "white";
		}
		JLabel lblNewLabel = new JLabel("It is " + player + "'s turn");
//		Player currentPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();

		Stopwatch watch = new Stopwatch(whitePlayer);
		watch.start();
		Time time = currentPlayer.getRemainingTime();
		JLabel lblNewLabel_1 = new JLabel("Remaining Time:" + time);
		
		
		GroupLayout gl_tile = new GroupLayout(tile);
		gl_tile.setHorizontalGroup(
			gl_tile.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_tile.createSequentialGroup()
					.addGap(59)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 447, Short.MAX_VALUE)
					.addComponent(SaveGameButton)
					.addGap(44))
				.addGroup(Alignment.TRAILING, gl_tile.createSequentialGroup()
					.addContainerGap(651, Short.MAX_VALUE)
					.addGroup(gl_tile.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblGrabWallPress)
						.addComponent(lblRotateWallPress)
						.addComponent(lblMoveWallPress, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
						.addComponent(lblDropWallPress, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(165))
				.addGroup(gl_tile.createSequentialGroup()
					.addGap(174)
					.addComponent(lblBlackStock)
					.addPreferredGap(ComponentPlacement.RELATED, 359, Short.MAX_VALUE)
					.addComponent(lblWhiteBlock)
					.addGap(321))
		);
		gl_tile.setVerticalGroup(
			gl_tile.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tile.createSequentialGroup()
					.addGroup(gl_tile.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_tile.createSequentialGroup()
							.addGap(33)
							.addComponent(SaveGameButton)
							.addGap(59)
							.addComponent(lblGrabWallPress)
							.addGap(32)
							.addComponent(lblRotateWallPress)
							.addGap(34)
							.addComponent(lblMoveWallPress)
							.addGap(37)
							.addComponent(lblDropWallPress))
						.addGroup(gl_tile.createSequentialGroup()
							.addGap(11)
							.addGroup(gl_tile.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))))
					.addGap(159)
					.addGroup(gl_tile.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_tile.createSequentialGroup()
							.addGap(183)
							.addComponent(lblBlackStock)
							.addContainerGap(154, Short.MAX_VALUE))
						.addGroup(gl_tile.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblWhiteBlock)
							.addGap(155))))
		);
		tile.setLayout(gl_tile);
		
		for (int i = 0; i < 10; i++) {
	         JWall jwall = new JWall();        
	         BlackWallList.add(jwall);      
	         mainLayerPanel.add(BlackWallList.get(i));
	         BlackWallList.get(i).setBackground(Color.GRAY);
	         BlackWallList.get(i).setBounds(50+35*i,650,10,110);
	         BlackWallList.get(i).setVisible(true);
	         System.out.println(i + "black");
	    }
	   for (int i = 0; i < 10; i++) {        
	      JWall jwall = new JWall();        
	         WhiteWallList.add(jwall);      
	         mainLayerPanel.add(WhiteWallList.get(i));
	         WhiteWallList.get(i).setBackground(Color.GRAY);
	         WhiteWallList.get(i).setBounds (600+35*i,650,10,110);
	         WhiteWallList.get(i).setVisible(true);
	         System.out.println(i + "white");
	   }
		
//
//		for (int i = 0; i < MAX_WALL; i++) {
//		      JWall jwall = new JWall();
//		      WallList.add(jwall);      
//		      mainLayerPanel.add(WallList.get(i));
//		      WallList.get(i).setBackground(Color.GRAY);
//		      
//		      if (i==0) {
//		       WallList.get(i).setBounds(50, 650, 10, 110);
//		      }
//		      if (0<i && i<10) {
//		       WallList.get(i).setBounds(50+35*i, 650, 10, 110);
//		      }
//		      if(i==10) {
//		       WallList.get(i).setBounds(600, 650, 10, 110);
//		      }
//		      if(i>10) {
//		       WallList.get(i).setBounds(600+35*(i-10), 650, 10, 110);
//		      }
//		      
//		      WallList.get(i).setVisible(true);
//		   // System.out.println(i + "yes");
//		     }
		
		

		whiteTurn = true;
		mainLayerPanel.add(whitePawnMove);
		whitePawnMove.setVisible(whiteTurn);	

		//add KeyBoard listener!
		
		
//		QuoridorApplication.getQuoridor().setBoard(null);
//		QuoridorApplication.getQuoridor().setCurrentGame(null);
//		QuoridorApplication.setJboard(null);
		//QuoridorApplication.getQuoridor().getUsers().remove(0);
		//QuoridorApplication.getQuoridor().getUsers().remove(1);
//		GamePosition oldGamePosition = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition();
//		for (int j = 0; j < 10; j++) {
//			Wall wall = Wall.getWithId(j);
//			oldGamePosition.removeWhiteWallsInStock(wall);
//		}
//		for (int j = 0; j < 10; j++) {
//			Wall wall = Wall.getWithId(j + 10);
//			oldGamePosition.removeBlackWallsInStock(wall);
//		}
		
		
		
		jwall = WhiteWallList.get(WHITE_WALL_INDEX);
		
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar()=='g') {	
					grab = true;
					if(WALL_INDEX<MAX_WALL&&grab) {
						mainLayerPanel.add(jwall);
						jwall.setBackground(Color.GRAY);
						jwall.setBounds(360, 300, 10, 110);
						jwall.setVisible(true);						
					}
					QuoridorController.grabWall();
					ChangeGrabWall();
				}
				if ((e.getKeyChar()=='w')||(e.getKeyCode() == KeyEvent.VK_UP)) {
					if(WALL_INDEX<MAX_WALL&&grab) {
						QuoridorController.MoveWall("up");
						int x = jwall.getLocation().x;
						int y = jwall.getLocation().y;
						
						if(jwall.getHeight()==110) {
						if(((x)>=120)&&((x)<=540)&&((y-60)>=60)&&((y-60)<=480))
							jwall.setLocation(x, y-60);}
						
						if(jwall.getHeight()==10) {
							if(((x)>=60)&&((x)<=540)&&((y-60)>=60)&&((y-60)<=540))
								jwall.setLocation(x, y-60);}
						
					}
				}
				if ((e.getKeyChar()=='a')||(e.getKeyCode() == KeyEvent.VK_LEFT)) {
					if(WALL_INDEX<MAX_WALL&&grab) {
						QuoridorController.MoveWall("left");
						int x = jwall.getLocation().x;
						int y = jwall.getLocation().y;
						
						if(jwall.getHeight()==110) {
						if(((x-60)>=120)&&((x-60)<=540)&&((y)>=60)&&((y)<=480))
							jwall.setLocation(x-60, y);}
						
						if(jwall.getHeight()==10) {
							if(((x-60)>=60)&&((x-60)<=540)&&((y)>=60)&&((y)<=540))
								jwall.setLocation(x-60, y);}
					}
				}
				if ((e.getKeyChar()=='s')||(e.getKeyCode() == KeyEvent.VK_DOWN)) {
					if(WALL_INDEX<MAX_WALL&&grab) {
						QuoridorController.MoveWall("down");
						int x = jwall.getLocation().x;
						int y = jwall.getLocation().y;
						if(jwall.getHeight()==110) {
						if(((x)>=120)&&((x)<=540)&&((y+60)>=60)&&((y+60)<=480))
							jwall.setLocation(x, y+60);}
						if(jwall.getHeight()==10) {
							if(((x)>=60)&&((x)<=540)&&((y+60)>=60)&&((y+60)<=540))
								jwall.setLocation(x, y+60);}
						
					}
				}
				if ((e.getKeyChar()=='d')||(e.getKeyCode() == KeyEvent.VK_RIGHT)) {
					if(WALL_INDEX<MAX_WALL&&grab) {
						QuoridorController.MoveWall("right");
						int x = jwall.getLocation().x;
						int y = jwall.getLocation().y;
						if(jwall.getHeight()==110) {
						if(((x+60)>=120)&&((x+60)<=540)&&((y)>=60)&&((y)<=480))
							jwall.setLocation(x+60, y);}
						if(jwall.getHeight()==10) {
							if(((x+60)>=60)&&((x+60)<=540)&&((y)>=60)&&((y)<=540))
								jwall.setLocation(x+60, y);}
					}
				}
				if (e.getKeyChar()=='r'&&grab) {
					QuoridorController.flipWall();
					if(WALL_INDEX<MAX_WALL) {
						int x = jwall.getLocation().x;
						int y = jwall.getLocation().y;
						int old_h = jwall.getBounds().height;
						int old_w = jwall.getBounds().width;
						if (old_h==10) {
							// horizontal
							jwall.setBounds(x, y, 10, 110);
							jwall.setLocation(jwall.getLocation().x+50 , jwall.getLocation().y-50);
						}
						else {
							// vertical
							jwall.setBounds(x, y, 110, 10);
							jwall.setLocation(jwall.getLocation().x-50 , jwall.getLocation().y+50);        
						}
					}
				}
				if (e.getKeyChar()=='t') if (e.getKeyChar()=='t') {
					boolean overlapped = true;
					if(QuoridorController.verifyOverlapped(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate())==false) 
					{
						overlapped = false;
						System.out.println("Not overlapped!");
					}
					
					QuoridorController.releaseWall();
					if((WALL_INDEX<MAX_WALL&&grab)&&(overlapped==false)) {

							ChangeDropWall();
							WALL_INDEX++;
//							if(WALL_INDEX<MAX_WALL) {
//								jwall = WallList.get(WALL_INDEX);
//							}
							grab = false;
							whiteTurn = !whiteTurn;
							mainLayerPanel.add(blackPawnMove);
							mainLayerPanel.add(whitePawnMove);
							whitePawnMove.setVisible(whiteTurn);	
							blackPawnMove.setVisible(!whiteTurn);	
							
							
							if(WALL_INDEX<MAX_WALL) {
						         if (BLACK_WALL_INDEX+1<WHITE_WALL_INDEX) {
						 
						           BLACK_WALL_INDEX++;
						           jwall=BlackWallList.get(BLACK_WALL_INDEX);
						          }else if (BLACK_WALL_INDEX+1>WHITE_WALL_INDEX){
						           WHITE_WALL_INDEX++;
						           jwall=WhiteWallList.get(WHITE_WALL_INDEX);
						          }else {
						           BLACK_WALL_INDEX++;
						           jwall=BlackWallList.get(BLACK_WALL_INDEX);
						          }
						         
						        }
							
							
						}
					
					}

				}

			
		});

		/*Image image=null;
		image=ImageIO.read(new File("H:\\aa.jpg"));*/


	}
}