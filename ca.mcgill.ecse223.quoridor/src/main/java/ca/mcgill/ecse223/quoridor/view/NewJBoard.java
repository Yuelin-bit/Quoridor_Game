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

public class NewJBoard extends JFrame {

	private JPanel contentPane;
	private JPanel mainLayerPanel;
	private JTile tile;
	private JUser users;
	private JTextArea txtrSeconds;

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
		
		
		GroupLayout gl_tile = new GroupLayout(tile);
		gl_tile.setHorizontalGroup(
			gl_tile.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tile.createSequentialGroup()
					.addGap(29)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addGap(150)
					.addComponent(txtrSeconds, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addGap(37))
		);
		gl_tile.setVerticalGroup(
			gl_tile.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tile.createSequentialGroup()
					.addGroup(gl_tile.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_tile.createSequentialGroup()
							.addGap(112)
							.addComponent(txtrSeconds, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_tile.createSequentialGroup()
							.addGap(27)
							.addGroup(gl_tile.createParallelGroup(Alignment.LEADING)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(845, Short.MAX_VALUE))
		);
		tile.setLayout(gl_tile);
		
		users = new JUser();
		users.setBounds(0, 0, 700, 1000);
		users.setOpaque(false);
		mainLayerPanel.add(users);
		
		
		
	}
}
