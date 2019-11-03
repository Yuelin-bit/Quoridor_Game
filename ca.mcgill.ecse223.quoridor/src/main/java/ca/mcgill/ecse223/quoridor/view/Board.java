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
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;

import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Board extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5127583580230421217L;
	private JPanel contentPane;
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
		tile.setOpaque(false);//透明
		mainLayerPanel.add(tile);
		
		
		
		//create four labels to specify the operation and set the layout for tile
		JLabel lblGrabWallPress = new JLabel("Grab Wall: Press G");
		JLabel lblRotateWallPress = new JLabel("Rotate Wall: Press R");
		JLabel lblMoveWallPress = new JLabel("Move Wall: Press W,A,S,D");
		JLabel lblDropWallPress = new JLabel("Drop Wall: Press T");
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainMenu().setVisible(true);// I am not sure!!!
				setVisible(false);
			}
		});
		GroupLayout gl_tile = new GroupLayout(tile);
		gl_tile.setHorizontalGroup(
			gl_tile.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_tile.createSequentialGroup()
					.addContainerGap(638, Short.MAX_VALUE)
					.addGroup(gl_tile.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_tile.createSequentialGroup()
							.addGroup(gl_tile.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblGrabWallPress)
								.addComponent(lblRotateWallPress)
								.addComponent(lblMoveWallPress, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
								.addComponent(lblDropWallPress, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(178))
						.addGroup(Alignment.TRAILING, gl_tile.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addGap(68))))
		);
		gl_tile.setVerticalGroup(
			gl_tile.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tile.createSequentialGroup()
					.addGap(38)
					.addComponent(btnNewButton)
					.addGap(54)
					.addComponent(lblGrabWallPress)
					.addGap(32)
					.addComponent(lblRotateWallPress)
					.addGap(34)
					.addComponent(lblMoveWallPress)
					.addGap(37)
					.addComponent(lblDropWallPress)
					.addContainerGap(512, Short.MAX_VALUE))
		);
		tile.setLayout(gl_tile);
		
		
		
		Wall wall = new Wall();
		//add KeyBoard listener!
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar()=='g') {					
					mainLayerPanel.add(wall);
					wall.setBackground(Color.GRAY);
					wall.setBounds(120, 60, 10, 110);
					wall.setVisible(true);
					System.out.println("yes");
				}
				if ((e.getKeyChar()=='w')||(e.getKeyCode() == KeyEvent.VK_UP)) {
					int x = wall.getLocation().x;
					int y = wall.getLocation().y;
					wall.setLocation(x, y-60);
				}
				if ((e.getKeyChar()=='a')||(e.getKeyCode() == KeyEvent.VK_LEFT)) {
					int x = wall.getLocation().x;
					int y = wall.getLocation().y;
					wall.setLocation(x-60, y);
				}
				if ((e.getKeyChar()=='s')||(e.getKeyCode() == KeyEvent.VK_DOWN)) {
					int x = wall.getLocation().x;
					int y = wall.getLocation().y;
					wall.setLocation(x, y+60);
				}
				if ((e.getKeyChar()=='d')||(e.getKeyCode() == KeyEvent.VK_RIGHT)) {
					int x = wall.getLocation().x;
					int y = wall.getLocation().y;
					wall.setLocation(x+60, y);
				}
				if (e.getKeyChar()=='r') {
					int x = wall.getLocation().x;
					int y = wall.getLocation().y;
					int old_h = wall.getBounds().height;
					int old_w = wall.getBounds().width;
					if (old_h==10) {
						wall.setBounds(x, y, 10, 110);
					}
					else {
						wall.setBounds(x, y, 110, 10);
					}
				}
				if (e.getKeyChar()=='t') {
					wall.setBackground(Color.red);
					mainLayerPanel.remove(wall);
					wall.setBackground(Color.red);
					mainLayerPanel.add(wall);
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
		});
		
		/*Image image=null;
		image=ImageIO.read(new File("H:\\aa.jpg"));*/
		
	}
}