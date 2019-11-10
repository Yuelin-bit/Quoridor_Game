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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewJBoard extends JFrame {

	private JPanel contentPane;
	public JPanel getContentPane() {
		return contentPane;
	}

	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}

	public JPanel getMainLayerPanel() {
		return mainLayerPanel;
	}

	public void setMainLayerPanel(JPanel mainLayerPanel) {
		this.mainLayerPanel = mainLayerPanel;
	}

	public JTile getTile() {
		return tile;
	}

	public void setTile(JTile tile) {
		this.tile = tile;
	}

	public JUser getUsers() {
		return users;
	}

	public void setUsers(JUser users) {
		this.users = users;
	}

	public JTextArea getTxtrSeconds() {
		return txtrSeconds;
	}

	public void setTxtrSeconds(JTextArea txtrSeconds) {
		this.txtrSeconds = txtrSeconds;
	}

	public JTextArea getTxtrTime() {
		return txtrTime;
	}

	public void setTxtrTime(JTextArea txtrTime) {
		this.txtrTime = txtrTime;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public JTextArea getTxtrUser_1() {
		return txtrUser_1;
	}

	public void setTxtrUser_1(JTextArea txtrUser_1) {
		this.txtrUser_1 = txtrUser_1;
	}

	public JTextArea getTxtrUser() {
		return txtrUser;
	}

	public void setTxtrUser(JTextArea txtrUser) {
		this.txtrUser = txtrUser;
	}

	public JTextArea getTxtrTime_1() {
		return txtrTime_1;
	}

	public void setTxtrTime_1(JTextArea txtrTime_1) {
		this.txtrTime_1 = txtrTime_1;
	}

	public JLabel getLblStock() {
		return lblStock;
	}

	public void setLblStock(JLabel lblStock) {
		this.lblStock = lblStock;
	}

	public JLabel getLblStock_1() {
		return lblStock_1;
	}

	public void setLblStock_1(JLabel lblStock_1) {
		this.lblStock_1 = lblStock_1;
	}

	public JButton getSettingButton() {
		return settingButton;
	}

	public void setSettingButton(JButton settingButton) {
		this.settingButton = settingButton;
	}

	private JPanel mainLayerPanel;
	private JTile tile;
	private JUser users;
	private JTextArea txtrSeconds;
	private JTextArea txtrTime;
	private JTextArea textArea;
	private JTextArea txtrUser_1;
	private JTextArea txtrUser;
	private JTextArea txtrTime_1;
	private JLabel lblStock;
	private JLabel lblStock_1;
	private JButton settingButton;

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
		
		txtrTime = new JTextArea();
		txtrTime.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtrTime.setText("time1");
		
		textArea = new JTextArea();
		textArea.setText("time1");
		textArea.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		txtrUser = new JTextArea();
		txtrUser.setText("User1");
		
		txtrUser_1 = new JTextArea();
		txtrUser_1.setText("User2");
		
		lblStock = new JLabel("Stock1");
		lblStock.setForeground(Color.WHITE);
		
		lblStock_1 = new JLabel("Stock2");
		lblStock_1.setForeground(Color.WHITE);
		
		txtrTime_1 = new JTextArea();
		txtrTime_1.setText("Time3");
		
		settingButton = new JButton("");
		settingButton.setIcon(new ImageIcon(NewJBoard.class.getResource("/ca/mcgill/ecse223/quoridor/resources/30—setting.png")));
		settingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
		GroupLayout gl_tile = new GroupLayout(tile);
		gl_tile.setHorizontalGroup(
			gl_tile.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_tile.createSequentialGroup()
					.addGroup(gl_tile.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_tile.createSequentialGroup()
							.addGap(53)
							.addComponent(txtrUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(41)
							.addComponent(lblStock)
							.addGap(105)
							.addComponent(settingButton, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
							.addComponent(lblStock_1, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_tile.createSequentialGroup()
							.addGap(29)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_tile.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_tile.createSequentialGroup()
									.addGroup(gl_tile.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_tile.createSequentialGroup()
											.addGap(26)
											.addComponent(txtrTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_tile.createSequentialGroup()
											.addGap(150)
											.addComponent(txtrSeconds, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
									.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, gl_tile.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtrTime_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(138)))))
					.addGap(39)
					.addGroup(gl_tile.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_tile.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
							.addGap(37))
						.addGroup(Alignment.TRAILING, gl_tile.createSequentialGroup()
							.addComponent(txtrUser_1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addGap(52))))
		);
		gl_tile.setVerticalGroup(
			gl_tile.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tile.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_tile.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_tile.createSequentialGroup()
							.addGroup(gl_tile.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtrTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtrTime_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(47)
							.addComponent(txtrSeconds, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_tile.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_tile.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_tile.createParallelGroup(Alignment.LEADING)
								.addComponent(txtrUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_tile.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtrUser_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblStock_1))
								.addComponent(lblStock)))
						.addGroup(gl_tile.createSequentialGroup()
							.addGap(15)
							.addComponent(settingButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(805, Short.MAX_VALUE))
		);
		tile.setLayout(gl_tile);
		
		users = new JUser();
		users.setBounds(0, 0, 700, 1000);
		users.setOpaque(false);
		mainLayerPanel.add(users);
		
		
		
	}
}
