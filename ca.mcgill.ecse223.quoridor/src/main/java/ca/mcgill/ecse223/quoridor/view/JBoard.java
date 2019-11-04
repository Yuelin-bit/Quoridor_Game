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
import ca.mcgill.ecse223.quoridor.model.WallMove;
import ca.mcgill.ecse223.quoridor.view.Pawn.PawnColor;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
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
	private static final int MAX_WALL =20;
	private List <JWall> WallList = new ArrayList<JWall>(); 
	private JWall jwall;

	private JLabel lblNewLabel;
	private JButton SaveGameButton;
	private JLabel lblNewLabel_1;
	public JWall getJwall() {
		return jwall;
	}
	public void setJwall(JWall jwall) {
		this.jwall = jwall;
	}
	private JPanel mainLayerPanel;
	private JTextField textField;
	private JTextField textField_1;
	public Pawn blackPawnMove;
	public Pawn whitePawnMove;
	private Tile tile;

	private JOptionPane errorHint;
	private String error = null;
	private boolean grab = false;
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
		this.setError("Illegal");
		this.errorHint.showMessageDialog(null, error);
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */


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
		tile = new Tile();
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

		//Add two pawns over here
		//Set the one's turn is visible
		JLabel playerToMove = new JLabel("WHITE");
		playerToMove.setBounds(650, 550, 100, 50);
		mainLayerPanel.add(playerToMove);
		playerToMove.setVisible(true);	

		JLabel playerToMove1 = new JLabel("BLACK");
		playerToMove1.setBounds(700, 550, 100, 50);
		mainLayerPanel.add(playerToMove1);
		playerToMove1.setVisible(true);

		whitePawnMove = new Pawn(PawnColor.WHITE);
		whitePawnMove.setBounds(650, 500, 50, 50);
		//TODO 
		//whitePawnMove.setVisible(true);
		mainLayerPanel.add(whitePawnMove);

		blackPawnMove = new Pawn(PawnColor.BLACK);
		blackPawnMove.setBounds(700, 500, 50, 50);
		//TODO
		//blackPawnMove.setVisible(false);

		//blackPawnMove.setVisible(true);
		mainLayerPanel.add(blackPawnMove);

		SaveGameButton = new JButton("Save and Back");
		SaveGameButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				SaveGameDialoge s = new SaveGameDialoge();
				s.setVisible(true);


				//new MainMenu().setVisible(true);// I am not sure!!!
				setVisible(false);
			}
		});

		lblNewLabel = new JLabel("Black S");

		lblNewLabel_1 = new JLabel("White S");

		textField = new JTextField();
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		GroupLayout gl_tile = new GroupLayout(tile);
		gl_tile.setHorizontalGroup(
				gl_tile.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_tile.createSequentialGroup()
						.addContainerGap(651, Short.MAX_VALUE)
						.addGroup(gl_tile.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_tile.createSequentialGroup()
										.addGroup(gl_tile.createParallelGroup(Alignment.LEADING, false)
												.addComponent(lblGrabWallPress)
												.addComponent(lblRotateWallPress)
												.addComponent(lblMoveWallPress, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
												.addComponent(lblDropWallPress, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGroup(gl_tile.createSequentialGroup()
														.addComponent(lblNewLabel)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_tile.createSequentialGroup()
														.addComponent(lblNewLabel_1)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
										.addGap(165))
								.addGroup(Alignment.TRAILING, gl_tile.createSequentialGroup()
										.addComponent(SaveGameButton)
										.addGap(44))))
				);
		gl_tile.setVerticalGroup(
				gl_tile.createParallelGroup(Alignment.LEADING)
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
						.addComponent(lblDropWallPress)
						.addGap(69)
						.addGroup(gl_tile.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(38)
						.addGroup(gl_tile.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_1)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(353, Short.MAX_VALUE))
				);
		tile.setLayout(gl_tile);



		for (int i = 0; i < MAX_WALL; i++) {
			JWall jwall = new JWall();
			WallList.add(jwall);
		}
		jwall = WallList.get(WALL_INDEX);


		//add KeyBoard listener!
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
				}
				if ((e.getKeyChar()=='w')||(e.getKeyCode() == KeyEvent.VK_UP)) {
					if(WALL_INDEX<MAX_WALL&&grab) {
						//QuoridorController.MoveWall("up");
						int x = jwall.getLocation().x;
						int y = jwall.getLocation().y;
						if(((x)>=120)&&((x)<=540)&&((y-60)>=60)&&((y-60)<=480))
							jwall.setLocation(x, y-60);
					}
				}
				if ((e.getKeyChar()=='a')||(e.getKeyCode() == KeyEvent.VK_LEFT)) {
					if(WALL_INDEX<MAX_WALL&&grab) {
						//QuoridorController.MoveWall("left");
						int x = jwall.getLocation().x;
						int y = jwall.getLocation().y;
						if(((x-60)>=120)&&((x-60)<=540)&&((y)>=60)&&((y)<=480))
							jwall.setLocation(x-60, y);
					}
				}
				if ((e.getKeyChar()=='s')||(e.getKeyCode() == KeyEvent.VK_DOWN)) {
					if(WALL_INDEX<MAX_WALL&&grab) {
						//QuoridorController.MoveWall("down");
						int x = jwall.getLocation().x;
						int y = jwall.getLocation().y;
						if(((x)>=120)&&((x)<=540)&&((y+60)>=60)&&((y+60)<=480))
							jwall.setLocation(x, y+60);
					}
				}
				if ((e.getKeyChar()=='d')||(e.getKeyCode() == KeyEvent.VK_RIGHT)) {
					if(WALL_INDEX<MAX_WALL&&grab) {
						//QuoridorController.MoveWall("right");
						int x = jwall.getLocation().x;
						int y = jwall.getLocation().y;
						if(((x+60)>=120)&&((x+60)<=540)&&((y)>=60)&&((y)<=480))
							jwall.setLocation(x+60, y);
					}
				}
				if (e.getKeyChar()=='r'&&grab) {
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
					if(WALL_INDEX<MAX_WALL&&grab) {
						jwall.setBackground(Color.MAGENTA);
						mainLayerPanel.remove(jwall);
						mainLayerPanel.add(jwall);
						WALL_INDEX++;
						if(WALL_INDEX<MAX_WALL) {
							jwall = WallList.get(WALL_INDEX);
						}
						grab = false;
					}
				}

			}
		});

		/*Image image=null;
		image=ImageIO.read(new File("H:\\aa.jpg"));*/


	}
}