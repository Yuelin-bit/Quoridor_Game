package ca.mcgill.ecse223.quoridor.view;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.quoridor.QuoridorApplication;
import ca.mcgill.ecse223.quoridor.controller.QuoridorController;
import ca.mcgill.ecse223.quoridor.model.Direction;
import ca.mcgill.ecse223.quoridor.model.Game.GameStatus;
import ca.mcgill.ecse223.quoridor.model.Player;
import ca.mcgill.ecse223.quoridor.model.Quoridor;
import ca.mcgill.ecse223.quoridor.model.User;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

public class SelectName extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectName frame = new SelectName();
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
	public SelectName() {
		setTitle("New Game");
		setBackground(Color.PINK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 760, 612);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblWhiteplayer = new JLabel("White Player");
		lblWhiteplayer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblBlackplayer = new JLabel("Black Player");
		lblBlackplayer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		List<User> us = QuoridorApplication.getQuoridor().getUsers();
		String[] names = new String[us.size()];
		int idx = 0;
		for(User u : us) {
			names[idx++] = u.getName();
		}
		
		JButton btnBack = new JButton("Back");
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu m = new MainMenu();
				m.setVisible(true);
				setVisible(false);
				dispose();
			}
		});

		JLabel lblThinkingTime = new JLabel("Thinking Time");
		lblThinkingTime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		textField = new JTextField();
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		
		JLabel lblMin = new JLabel("Minute");		
		JLabel lblSec = new JLabel("Second");
		JButton btnStart = new JButton("Start");
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (QuoridorApplication.getQuoridor().getCurrentGame().getGameStatus().equals(GameStatus.ReadyToStart)) {
					JBoard board = new JBoard();
					board.setVisible(true);
					setVisible(false);
					dispose();
				}
			}
		});
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(names));
		comboBox.setEditable(false);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(names));
		comboBox_1.setEditable(false);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setForeground(Color.RED);
		
		JLabel lblUserNameDne = new JLabel();
		lblUserNameDne.setForeground(Color.RED);
		JLabel lblInputMustBe = new JLabel();
		lblInputMustBe.setForeground(Color.RED);

		JButton btnConfirm = new JButton("Confirm");
		QuoridorController.initializeNewGame();
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Check if two names are not equal
				lblNewLabel.setVisible(false);
				lblUserNameDne.setVisible(false);
				String white = String.valueOf(comboBox.getSelectedItem());
				String black = String.valueOf(comboBox_1.getSelectedItem());
				if(white == null || black == null) {
					return;
				}
				if (white.equals(black)) {
					lblUserNameDne.setForeground(Color.RED);
					lblNewLabel.setForeground(Color.RED);
					lblNewLabel.setText("White player CANNOT be the same as black player");
					lblNewLabel.setVisible(true);
					lblUserNameDne.setText("Black player CANNOT be the same as white player");
					lblUserNameDne.setVisible(true);
					return;
				}
				QuoridorApplication.getQuoridor().getCurrentGame().setGameStatus(GameStatus.Initializing);
				boolean result = false;
				try {
					result = QuoridorController.setUserName("white", white, false);
				} catch (IllegalArgumentException ex) {
					lblNewLabel.setForeground(Color.RED);
					lblNewLabel.setText(ex.getMessage());
					lblNewLabel.setVisible(true);
					return;
				}
				if(result) {
					lblNewLabel.setForeground(Color.BLACK);
					lblNewLabel.setText("White player name has been changed");
					lblNewLabel.setVisible(true);
				}
				result = false;
				try {
					result = QuoridorController.setUserName("black", black, false);
				} catch (IllegalArgumentException ex) {
					lblUserNameDne.setForeground(Color.RED);
					lblUserNameDne.setText(ex.getMessage());
					lblUserNameDne.setVisible(true);
					return;
				}
				if(result) {
					lblUserNameDne.setForeground(Color.BLACK);
					lblUserNameDne.setText("Black player name has been changed");
					lblUserNameDne.setVisible(true);
				}
				Integer min = 3;
				Integer sec = 0; 
				
				try {
						min = Integer.parseInt(textField.getText());
						sec = Integer.parseInt(textField_1.getText());
				}catch(Exception ex){
					lblInputMustBe.setText("Input must be number!");
					lblInputMustBe.setVisible(true);
				}
				
//				QuoridorController.setTotalThinkingTime(min, sec);
//				QuoridorController.initializeBoard();
				QuoridorController.verifyGameIsReady();
			}
		});
		

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(56)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblWhiteplayer)
						.addComponent(lblBlackplayer)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnConfirm, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblThinkingTime))
					.addGap(61)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblInputMustBe)
							.addContainerGap())
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblUserNameDne, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
								.addGap(235))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblUserNameDne, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
								.addGap(235))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(lblNewLabel)
									.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblMin)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(lblSec)))))
								.addContainerGap(111, Short.MAX_VALUE)))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(122)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWhiteplayer)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addComponent(lblUserNameDne)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblBlackplayer)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(22)
					.addComponent(lblInputMustBe)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblThinkingTime)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMin)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSec))
					.addGap(115)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBack)
						.addComponent(btnStart)
						.addComponent(btnConfirm))
					.addContainerGap(84, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}

