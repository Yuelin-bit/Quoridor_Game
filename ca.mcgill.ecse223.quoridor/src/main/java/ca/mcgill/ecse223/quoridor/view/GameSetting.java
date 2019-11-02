package ca.mcgill.ecse223.quoridor.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class GameSetting extends JFrame {

	private JPanel contentPane;
	private JTextField txtWhite;
	private JTextField txtBlack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameSetting frame = new GameSetting();
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
	public GameSetting() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1043, 776);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblWhitePlayer = new JLabel("White Player");
		lblWhitePlayer.setFont(new Font("Tahoma", Font.PLAIN, 50));
		
		JLabel lblBlackPlayer = new JLabel("Black Player");
		lblBlackPlayer.setFont(new Font("Tahoma", Font.PLAIN, 50));
		
		txtWhite = new JTextField();
		txtWhite.setForeground(Color.LIGHT_GRAY);
		txtWhite.setFont(new Font("Tahoma", Font.PLAIN, 50));
		txtWhite.setText("Ms. White");
		txtWhite.setColumns(10);
		
		txtBlack = new JTextField();
		txtBlack.setText("Mr. Black");
		txtBlack.setFont(new Font("Tahoma", Font.PLAIN, 50));
		txtBlack.setForeground(Color.LIGHT_GRAY);
		txtBlack.setColumns(10);
		
		JLabel lblThinkingTime = new JLabel("Thinking Time");
		lblThinkingTime.setFont(new Font("Tahoma", Font.PLAIN, 50));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 50));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1 min", "2 min", "3 min", "4 min", "5 min", "6 min", "8 min", "10 min"}));
		JButton btnStart = new JButton("Start");
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 50));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Board board = new Board();
				board.setVisible(true);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(88)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblWhitePlayer)
								.addComponent(lblBlackPlayer, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblThinkingTime, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE))
							.addGap(83)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtBlack, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
								.addComponent(txtWhite, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
								.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(304)
							.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(239, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(110)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtWhite, Alignment.TRAILING)
						.addComponent(lblWhitePlayer, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
					.addGap(68)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblBlackPlayer, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtBlack, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
					.addGap(67)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblThinkingTime, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
					.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addGap(93))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
