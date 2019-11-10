package ca.mcgill.ecse223.quoridor.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JButton;

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

	public JTextArea getTxtrSeconds() {
		return txtrSeconds;
	}

	public void setTxtrSeconds(JTextArea txtrSeconds) {
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

	private JTextArea txtrSeconds;
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
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewJBoard frame = new NewJBoard();
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
	public NewJBoard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 700, 1000);
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
		tile.setBounds(0, 0, 700, 1000);
		tile.setOpaque(false);
		mainLayerPanel.add(tile);
		
		txtrSeconds = new JTextArea();
		txtrSeconds.setBackground(new Color(245, 245, 220));
		txtrSeconds.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtrSeconds.setText("Seconds");
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(NewJBoard.class.getResource("/ca/mcgill/ecse223/quoridor/resources/monkey_128.png")));
		
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(NewJBoard.class.getResource("/ca/mcgill/ecse223/quoridor/resources/cat_128.png")));
		
		setting = new JButton("");
		setting.setIcon(new ImageIcon(NewJBoard.class.getResource("/ca/mcgill/ecse223/quoridor/resources/30—setting.png")));
		
		whiteUser = new JLabel("white");
		whiteUser.setForeground(Color.WHITE);
		
		blackUser = new JLabel("black");
		blackUser.setForeground(Color.WHITE);
		
		bigTime = new JLabel("bigTime");
		bigTime.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		whiteStock = new JTextArea();
		whiteStock.setText("Stock1");
		
		blackStock = new JTextArea();
		blackStock.setText("Stock2");
		
		whiteTime = new JLabel("Time1");
		whiteTime.setForeground(Color.WHITE);
		
		blackTime = new JLabel("Time2");
		blackTime.setForeground(Color.WHITE);
		
		whitePawn = new JLabel("");
		whitePawn.setForeground(Color.YELLOW);
		whitePawn.setIcon(new ImageIcon(NewJBoard.class.getResource("/ca/mcgill/ecse223/quoridor/resources/32—wqueen.png")));
		whitePawn.setVisible(false);
		blackPawn = new JLabel("");
		blackPawn.setIcon(new ImageIcon(NewJBoard.class.getResource("/ca/mcgill/ecse223/quoridor/resources/32—wqueen.png")));
		blackPawn.setVisible(true);
		
		GroupLayout gl_tile = new GroupLayout(tile);
		gl_tile.setHorizontalGroup(
			gl_tile.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tile.createSequentialGroup()
					.addGroup(gl_tile.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_tile.createSequentialGroup()
							.addGap(29)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_tile.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(whiteUser)
							.addGap(48)))
					.addGroup(gl_tile.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_tile.createSequentialGroup()
							.addGroup(gl_tile.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_tile.createSequentialGroup()
									.addGap(158)
									.addComponent(bigTime))
								.addGroup(gl_tile.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(whiteStock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(70)
									.addComponent(setting, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED, 101, Short.MAX_VALUE))
						.addGroup(gl_tile.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_tile.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_tile.createSequentialGroup()
									.addComponent(whitePawn)
									.addPreferredGap(ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
									.addComponent(txtrSeconds, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(93))
								.addGroup(gl_tile.createSequentialGroup()
									.addComponent(whiteTime)
									.addPreferredGap(ComponentPlacement.RELATED, 251, Short.MAX_VALUE)))))
					.addGroup(gl_tile.createParallelGroup(Alignment.LEADING)
						.addComponent(blackStock, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(blackTime, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(blackPawn))
					.addGroup(gl_tile.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_tile.createSequentialGroup()
							.addGap(28)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_tile.createSequentialGroup()
							.addGap(68)
							.addComponent(blackUser, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)))
					.addGap(37))
		);
		gl_tile.setVerticalGroup(
			gl_tile.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tile.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_tile.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_tile.createSequentialGroup()
							.addGroup(gl_tile.createParallelGroup(Alignment.BASELINE)
								.addComponent(whiteTime)
								.addComponent(blackTime))
							.addGap(13)
							.addComponent(bigTime)
							.addGap(29)
							.addGroup(gl_tile.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_tile.createParallelGroup(Alignment.BASELINE)
									.addComponent(blackPawn)
									.addComponent(txtrSeconds, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(whitePawn))
							.addGap(17))
						.addGroup(Alignment.TRAILING, gl_tile.createParallelGroup(Alignment.LEADING)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_tile.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_tile.createParallelGroup(Alignment.BASELINE)
							.addComponent(whiteStock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(blackStock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(whiteUser))
						.addComponent(setting, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_tile.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(blackUser)))
					.addContainerGap(807, Short.MAX_VALUE))
		);
		tile.setLayout(gl_tile);
		
		users = new JUser();
		users.setBounds(0, 0, 700, 1000);
		users.setOpaque(false);
		mainLayerPanel.add(users);
		
		
		
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