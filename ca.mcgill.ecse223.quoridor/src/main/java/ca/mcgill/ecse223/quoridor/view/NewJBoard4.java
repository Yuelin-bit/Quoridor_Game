package ca.mcgill.ecse223.quoridor.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class NewJBoard4 extends JFrame {

	private JPanel contentPane;
	private JPanel mainLayerPanel;
	private JTile4 tile4;
	private JUser4 users4;
	
	
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
	
	private int WHITE_WALL_INDEX = 0;//how many times you grabed before
	private int YELLOW_WALL_INDEX = 0;
	private int BLACK_WALL_INDEX = 0;
	private int RED_WALL_INDEX = 0;
	
	private String trunGUI = "white";


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
		
	}

}
