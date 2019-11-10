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
	public JLabel getLblUser1() {
		return lblUser1;
	}

	public void setLblUser1(JLabel lblUser1) {
		this.lblUser1 = lblUser1;
	}

	public JLabel getLblUser() {
		return lblUser;
	}

	public void setLblUser(JLabel lblUser) {
		this.lblUser = lblUser;
	}

	public JLabel getLblTime_2() {
		return lblTime_2;
	}

	public void setLblTime_2(JLabel lblTime_2) {
		this.lblTime_2 = lblTime_2;
	}

	public JLabel getLblTime_1() {
		return lblTime_1;
	}

	public void setLblTime_1(JLabel lblTime_1) {
		this.lblTime_1 = lblTime_1;
	}

	public JTextArea getTxtrSeconds() {
		return txtrSeconds;
	}

	public void setTxtrSeconds(JTextArea txtrSeconds) {
		this.txtrSeconds = txtrSeconds;
	}

	public JTextArea getTxtrStock_1() {
		return txtrStock_1;
	}

	public void setTxtrStock_1(JTextArea txtrStock_1) {
		this.txtrStock_1 = txtrStock_1;
	}

	public JTextArea getTxtrStock() {
		return txtrStock;
	}

	public void setTxtrStock(JTextArea txtrStock) {
		this.txtrStock = txtrStock;
	}

	private JTextArea txtrSeconds;
	private JLabel lblUser1;
	private JLabel lblUser;
	private JTextArea txtrStock_1;
	private JLabel lblTime_2;
	private JLabel lblTime_1;
	private JTextArea txtrStock;
	private JButton setting;
	private JLabel qizi1;
	private JLabel qizi2;

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
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(NewJBoard.class.getResource("/ca/mcgill/ecse223/quoridor/resources/monkey_128.png")));
		
		JLabel label = new JLabel("New label");
		label.setIcon(new ImageIcon(NewJBoard.class.getResource("/ca/mcgill/ecse223/quoridor/resources/cat_128.png")));
		
		setting = new JButton("");
		setting.setIcon(new ImageIcon(NewJBoard.class.getResource("/ca/mcgill/ecse223/quoridor/resources/30—setting.png")));
		
		lblUser1 = new JLabel("User1");
		lblUser1.setForeground(Color.WHITE);
		
		lblUser = new JLabel("User2");
		lblUser.setForeground(Color.WHITE);
		
		JLabel lblTime = new JLabel("Time3");
		lblTime.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		txtrStock_1 = new JTextArea();
		txtrStock_1.setText("Stock1");
		
		txtrStock = new JTextArea();
		txtrStock.setText("Stock2");
		
		lblTime_1 = new JLabel("Time1");
		lblTime_1.setForeground(Color.WHITE);
		
		lblTime_2 = new JLabel("Time2");
		lblTime_2.setForeground(Color.WHITE);
		
		qizi1 = new JLabel("");
		qizi1.setForeground(Color.YELLOW);
		qizi1.setIcon(new ImageIcon(NewJBoard.class.getResource("/ca/mcgill/ecse223/quoridor/resources/32—wqueen.png")));
		
		qizi2 = new JLabel("");
		qizi2.setIcon(new ImageIcon(NewJBoard.class.getResource("/ca/mcgill/ecse223/quoridor/resources/32—wqueen.png")));
		
		
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
							.addComponent(lblUser1)
							.addGap(48)))
					.addGroup(gl_tile.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_tile.createSequentialGroup()
							.addGroup(gl_tile.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_tile.createSequentialGroup()
									.addGap(158)
									.addComponent(lblTime))
								.addGroup(gl_tile.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtrStock_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(70)
									.addComponent(setting, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED, 101, Short.MAX_VALUE))
						.addGroup(gl_tile.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_tile.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_tile.createSequentialGroup()
									.addComponent(qizi1)
									.addPreferredGap(ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
									.addComponent(txtrSeconds, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(93))
								.addGroup(gl_tile.createSequentialGroup()
									.addComponent(lblTime_1)
									.addPreferredGap(ComponentPlacement.RELATED, 251, Short.MAX_VALUE)))))
					.addGroup(gl_tile.createParallelGroup(Alignment.LEADING)
						.addComponent(txtrStock, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTime_2, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(qizi2))
					.addGroup(gl_tile.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_tile.createSequentialGroup()
							.addGap(28)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_tile.createSequentialGroup()
							.addGap(68)
							.addComponent(lblUser, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)))
					.addGap(37))
		);
		gl_tile.setVerticalGroup(
			gl_tile.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tile.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_tile.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_tile.createSequentialGroup()
							.addGroup(gl_tile.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTime_1)
								.addComponent(lblTime_2))
							.addGap(13)
							.addComponent(lblTime)
							.addGap(29)
							.addGroup(gl_tile.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_tile.createParallelGroup(Alignment.BASELINE)
									.addComponent(qizi2)
									.addComponent(txtrSeconds, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(qizi1))
							.addGap(17))
						.addGroup(Alignment.TRAILING, gl_tile.createParallelGroup(Alignment.LEADING)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_tile.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_tile.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtrStock_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtrStock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblUser1))
						.addComponent(setting, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_tile.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblUser)))
					.addContainerGap(807, Short.MAX_VALUE))
		);
		tile.setLayout(gl_tile);
		
		users = new JUser();
		users.setBounds(0, 0, 700, 1000);
		users.setOpaque(false);
		mainLayerPanel.add(users);
		
		
		
	}

	public JLabel getQizi1() {
		return qizi1;
	}

	public void setQizi1(JLabel qizi1) {
		this.qizi1 = qizi1;
	}

	public JLabel getQizi2() {
		return qizi2;
	}

	public void setQizi2(JLabel qizi2) {
		this.qizi2 = qizi2;
	}
}
