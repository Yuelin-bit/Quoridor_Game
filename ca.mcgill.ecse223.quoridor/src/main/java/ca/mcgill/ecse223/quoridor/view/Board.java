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

import ca.mcgill.ecse223.quoridor.controller.QuoridorController;
import ca.mcgill.ecse223.quoridor.model.WallMove;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
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


public class Board extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5127583580230421217L;
	private JPanel contentPane;
	private JTextField display_number_black_stock;
	private JTextField display_number_white_stock;
	private int WALL_INDEX = 0;
	private static final int MAX_WALL =20;
	private List <Wall> WallList = new ArrayList<>();	
	private Wall wall;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Board frame = new Board();
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
	
	
	public Board() {
		
		setFocusable(true);
		
		//Init
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		getContentPane().add(contentPane);
		
		
		
		//Create new panel to control layers
		JPanel mainLayerPanel = new JPanel();		  
		contentPane.add(mainLayerPanel, BorderLayout.CENTER);		 
		mainLayerPanel.setBorder(BorderFactory.createLineBorder(Color.orange, 2));
		mainLayerPanel.setLayout(null);
		
		
		
		//add tile(with GroupLayout) to the mainLayerPanel
		Tile tile = new Tile();
		tile.setBackground(Color.WHITE);
		tile.setBounds(0, 0, 1000, 800);
		tile.setOpaque(false);
		mainLayerPanel.add(tile);
		
		
		
		//create four labels to specify the operation and set the layout for tile
		JLabel lblGrabWallPress = new JLabel("Grab Wall: Press G");
		JLabel lblRotateWallPress = new JLabel("Rotate Wall: Press R");
		JLabel lblMoveWallPress = new JLabel("Move Wall: Press W,A,S,D");
		JLabel lblDropWallPress = new JLabel("Drop Wall: Press T");
		
		JLabel lblBlackStock = new JLabel("Number of Wall in Black Stock: ");		
		JLabel lblWhiteStock = new JLabel("Number of Wall in White Stock: ");



		display_number_black_stock = new JTextField();
		display_number_black_stock.setColumns(10);
		display_number_black_stock.setText("black number");

		display_number_white_stock = new JTextField();
		display_number_white_stock.setColumns(10);
		display_number_white_stock.setText("white number");

		GroupLayout gl_tile = new GroupLayout(tile);
		gl_tile.setHorizontalGroup(
				gl_tile.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_tile.createSequentialGroup()
						.addGap(642)
						.addGroup(gl_tile.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_tile.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblGrabWallPress)
										.addComponent(lblRotateWallPress)
										.addComponent(lblMoveWallPress, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
										.addComponent(lblDropWallPress, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(lblWhiteStock)
								.addComponent(lblBlackStock))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_tile.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(display_number_black_stock)
								.addComponent(display_number_white_stock))
						.addGap(23))
				);
		gl_tile.setVerticalGroup(
				gl_tile.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tile.createSequentialGroup()
						.addGroup(gl_tile.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_tile.createSequentialGroup()
										.addGap(121)
										.addComponent(lblGrabWallPress)
										.addGap(32)
										.addComponent(lblRotateWallPress)
										.addGap(34)
										.addComponent(lblMoveWallPress)
										.addGap(37)
										.addComponent(lblDropWallPress)
										.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
										.addComponent(lblBlackStock))
								.addGroup(Alignment.TRAILING, gl_tile.createSequentialGroup()
										.addContainerGap()
										.addComponent(display_number_black_stock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGap(18)
						.addGroup(gl_tile.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblWhiteStock)
								.addComponent(display_number_white_stock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(415, Short.MAX_VALUE))
				);
		tile.setLayout(gl_tile);





		for (int i = 0; i < MAX_WALL; i++) {
			Wall wall = new Wall();
			WallList.add(wall);
		}
		wall = WallList.get(WALL_INDEX);
		System.out.print("hh");
		//add KeyBoard listener!
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				if (e.getKeyChar()=='g') {
					if(WALL_INDEX<MAX_WALL) {
						mainLayerPanel.add(wall);
						wall.setBackground(Color.GRAY);
						wall.setBounds(360, 300, 10, 110);
						wall.setVisible(true);
					}
				}				

				if (e.getKeyChar()=='w') {
					if(WALL_INDEX<MAX_WALL) {
						int x = wall.getLocation().x;
						int y = wall.getLocation().y;
						wall.setLocation(x, y-60);

					}
				}
				if (e.getKeyChar()=='a') {
					if(WALL_INDEX<MAX_WALL) {
						mainLayerPanel.add(wall);
						int x = wall.getLocation().x;
						int y = wall.getLocation().y;
						wall.setLocation(x-60, y);

					}
				}
				if (e.getKeyChar()=='s') {
					if(WALL_INDEX<MAX_WALL) {
						int x = wall.getLocation().x;
						int y = wall.getLocation().y;
						wall.setLocation(x, y+60);

					}
				}
				if (e.getKeyChar()=='d') {
					if(WALL_INDEX<MAX_WALL) {
						int x = wall.getLocation().x;
						int y = wall.getLocation().y;
						wall.setLocation(x+60, y);

					}

				}
				if (e.getKeyChar()=='r') {
					if(WALL_INDEX<MAX_WALL) {

						int x = wall.getLocation().x;
						int y = wall.getLocation().y;
						int old_h = wall.getBounds().height;
						int old_w = wall.getBounds().width;
						if (old_h==10) {
							// horizontal
							wall.setBounds(x, y, 10, 110);
							wall.setLocation(wall.getLocation().x+50 , wall.getLocation().y-50);
						}
						else {
							// vertical
							wall.setBounds(x, y, 110, 10);
							wall.setLocation(wall.getLocation().x-50 , wall.getLocation().y+50);								
						}
					}
				}
				if (e.getKeyChar()=='t') {
					if(WALL_INDEX<MAX_WALL) {
						wall.setBackground(Color.MAGENTA);
						mainLayerPanel.remove(wall);
						mainLayerPanel.add(wall);
						WALL_INDEX++;
						wall = WallList.get(WALL_INDEX);
						/*int x = wall.getLocation().x;
					int y = wall.getLocation().y;
					int old_h = wall.getBounds().height;
					int old_w = wall.getBounds().width;
					Wall wall2 = new Wall();
					JPanel wall = new JPanel();
					wall2.setBackground(Color.red);
					wall2.setBounds(x, y, old_w, old_h);
					wall2.setVisible(true);
					mainLayerPanel.remove(wall);
					wall.setVisible(false);
					mainLayerPanel.add(wall2);
					System.out.println("yes2");*/
					}
				}

			}
		});



	}
}