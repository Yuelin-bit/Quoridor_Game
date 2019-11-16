package ca.mcgill.ecse223.quoridor.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.quoridor.controller.QuoridorController;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class NewJBoard extends JFrame {

	private JPanel contentPane;
	private JPanel mainLayerPanel;
	private JTile tile;
	private JUser users;
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

	private JLabel txtrSeconds;
	private JLabel whiteUser;
	private JLabel blackUser;
	private JTextArea whiteStock;
	private JLabel blackTime;
	private JLabel whiteTime;
	private JTextArea blackStock;
	private JButton setting;
	private JLabel whitePawn;
	private JLabel blackPawn;
	

	private JLabel bigTime;

	

	/**
	 * Create the frame.
	 */
	public NewJBoard() {
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
		txtrSeconds.setBounds(243, 115, 64, 20);
		txtrSeconds.setBackground(new Color(245, 245, 220));
		txtrSeconds.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtrSeconds.setText("Seconds");
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(19, 20, 127, 120);
		lblNewLabel.setIcon(new ImageIcon(NewJBoard.class.getResource("/ca/mcgill/ecse223/quoridor/resources/monkey_128.png")));
		
		JLabel label = new JLabel();
		label.setBounds(472, 44, 127, 120);
		label.setIcon(new ImageIcon(NewJBoard.class.getResource("/ca/mcgill/ecse223/quoridor/resources/cat_128.png")));
		
		setting = new JButton("");
		setting.setBounds(296, 135, 30, 28);
		setting.setIcon(new ImageIcon(NewJBoard.class.getResource("/ca/mcgill/ecse223/quoridor/resources/30—setting.png")));
		
		whiteUser = new JLabel(QuoridorController.getWhiteName());
		whiteUser.setBounds(29, 135, 72, 15);
		whiteUser.setForeground(Color.WHITE);
		
		blackUser = new JLabel(QuoridorController.getBlackName());
		blackUser.setBounds(512, 176, 36, 15);
		blackUser.setForeground(Color.WHITE);
		
		bigTime = new JLabel(QuoridorController.getWhiteRemainingTime());
		bigTime.setBounds(235, 49, 91, 20);
		bigTime.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		whiteStock = new JTextArea();
		whiteStock.setBounds(143, 118, 69, 15);
		whiteStock.setText(QuoridorController.getWhiteStocks());
		
		blackStock = new JTextArea();
		blackStock.setBounds(400, 164, 44, 15);
		blackStock.setText(QuoridorController.getBlackStocks());
		
		whiteTime = new JLabel(QuoridorController.getWhiteRemainingTime());
		whiteTime.setBounds(157, 38, 72, 15);
		whiteTime.setForeground(Color.WHITE);
		
		blackTime = new JLabel(QuoridorController.getBlackRemainingTime());
		blackTime.setBounds(400, 38, 39, 15);
		blackTime.setForeground(Color.WHITE);
		
		whitePawn = new JLabel("");
		whitePawn.setBounds(167, 66, 32, 32);
		whitePawn.setForeground(Color.YELLOW);
		whitePawn.setIcon(new ImageIcon(NewJBoard.class.getResource("/ca/mcgill/ecse223/quoridor/resources/32—wqueen.png")));
		whitePawn.setVisible(true);
		blackPawn = new JLabel("");
		blackPawn.setBounds(400, 115, 32, 32);
		blackPawn.setIcon(new ImageIcon(NewJBoard.class.getResource("/ca/mcgill/ecse223/quoridor/resources/32—wqueen.png")));
		blackPawn.setVisible(false);
		tile.setLayout(null);
		tile.add(lblNewLabel);
		tile.add(whiteUser);
		tile.add(bigTime);
		tile.add(whiteStock);
		tile.add(setting);
		tile.add(whitePawn);
		tile.add(txtrSeconds);
		tile.add(whiteTime);
		tile.add(blackStock);
		tile.add(blackTime);
		tile.add(blackPawn);
		tile.add(label);
		tile.add(blackUser);
		
		users = new JUser();
		users.setBounds(0, 0, 560, 800);
		users.setOpaque(false);
		mainLayerPanel.add(users);
		
		JWall jwall = new JWall();            
        jwall.setBackground(Color.GRAY);
        jwall.setBounds(50+35,650,10,110);
        jwall.setVisible(true);
        mainLayerPanel.add(jwall);
        
        
     
		
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
		this.whitePawn = whitePawn;
	}

	/**
	 * @param blackPawn the blackPawn to set
	 */
	public void setBlackPawn(JLabel blackPawn) {
		this.blackPawn = blackPawn;
	}

	public JLabel getwhitePawn() {
		return whitePawn;
	}

	public void setwhitePawn(JLabel whitePawn) {
		this.whitePawn = whitePawn;
	}

	public JLabel getblackPawn() {
		return blackPawn;
	}

	public void setblackPawn(JLabel blackPawn) {
		this.blackPawn = blackPawn;
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
		return whitePawn;
	}

	/**
	 * @return the bigTime
	 */
	public JLabel getBigTime() {
		return bigTime;
	}
}