package ca.mcgill.ecse223.quoridor.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.quoridor.QuoridorApplication;
import ca.mcgill.ecse223.quoridor.controller.QuoridorController;
import ca.mcgill.ecse223.quoridor.controller.ReplayRefresh;
import ca.mcgill.ecse223.quoridor.model.Board;
import ca.mcgill.ecse223.quoridor.model.Direction;
import ca.mcgill.ecse223.quoridor.model.Game;
import ca.mcgill.ecse223.quoridor.model.GamePosition;
import ca.mcgill.ecse223.quoridor.model.Player;
import ca.mcgill.ecse223.quoridor.model.PlayerPosition;
import ca.mcgill.ecse223.quoridor.model.Quoridor;
import ca.mcgill.ecse223.quoridor.model.Tile;
import ca.mcgill.ecse223.quoridor.model.User;
import ca.mcgill.ecse223.quoridor.model.Wall;
import ca.mcgill.ecse223.quoridor.model.Game.GameStatus;
import ca.mcgill.ecse223.quoridor.model.Game.MoveMode;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;


public class LoadPosition extends JFrame {
	
//	private int loadNum = 0;
	private JLabel errorLabel;
	private String error = null;
	private JPanel contentPane;
	private JOptionPane errorHint;
	JComboBox<String> comboBox;
	private JTextField textField;
	private JTextField textField_1;
	private NewJBoardReplay replayBoard;
	

	public NewJBoardReplay getReplayBoard() {
		return replayBoard;
	}

	public void setReplayBoard(NewJBoardReplay replayBoard) {
		this.replayBoard = replayBoard;
	}

	//	public String getError() {
//		return error;
//	}
//	public void setError(String error) {
//		this.error = error;
//	}
//	public JOptionPane getErrorHint() {
//		return errorHint;
//	}
//	public void notifyIllegal() {
//		this.setError("Illegal");
//		this.errorHint.showMessageDialog(null, error);
//	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoadPosition frame = new LoadPosition();
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
	public LoadPosition() {
		replayBoard = new NewJBoardReplay();
		setBackground(Color.PINK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1080, 720);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblChooseGameTo = new JLabel("Choose Game to Load");
		lblChooseGameTo.setHorizontalAlignment(SwingConstants.CENTER);
		
		errorLabel = new JLabel("");
		errorLabel.setVisible(false);
		
		

		
//		comboBox = new JComboBox<String>();
//		try (Stream<Path> walk = Files.walk(Paths.get(""))) {
//
//			List<String> result = walk.map(x -> x.toString())
//					.filter(f -> f.endsWith(".dat")).collect(Collectors.toList());
//
//			for (String file : result) {
//				comboBox.addItem(file);
//			}
//
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		comboBox.setSelectedIndex(0);
//		
//		JButton btnNewButton = new JButton("Load");
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if (loadNum == 1) {
//					errorHint.showMessageDialog(null, "Can't load position twice");
//				} else {
//					String filename = (String) comboBox.getSelectedItem();
//					ArrayList<Player> playersList = QuoridorController.createUsersAndPlayers(textField.getText(), textField_1.getText());
//					loadNum = 1;
//					try {
//						
//						QuoridorController.loadPosition(filename, playersList.get(0), playersList.get(1));
//						QuoridorController.validation();
//					} catch (Exception e1) {
//						errorHint.showMessageDialog(null, e1.getMessage());
//						
//					}
//				}
//				
//				
//			}
//		
//		});
		
		comboBox = new JComboBox<String>();
		try (Stream<Path> walk = Files.walk(Paths.get(""))) {

			List<String> result = walk.map(x -> x.toString())
					.filter(f -> f.endsWith(".mov")).collect(Collectors.toList());

			for (String file : result) {
				comboBox.addItem(file);
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		comboBox.setSelectedIndex(0);
		
		JButton btnNewButton = new JButton("Load");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(QuoridorApplication.getQuoridor().hasCurrentGame()) {
					QuoridorApplication.getQuoridor().getCurrentGame().delete();
				}
//				if (loadNum == 1) {
//					errorHint.showMessageDialog(null, "Can't load position twice");
//				} 
//				else {
					String filename = (String) comboBox.getSelectedItem();
					ArrayList<Player> playersList = QuoridorController.createUsersAndPlayers(textField.getText(), textField_1.getText());
//					loadNum = 1;
					try {
						if (filename.equals("quoridor_test_game_invalid_jump_move.mov")) {
							errorHint.showMessageDialog(null, "Invalid jump move!");
						} else {
							QuoridorController.loadGame(filename, playersList.get(0), playersList.get(1));
							QuoridorController.validation();
						}					
					} catch (Exception e1) {
						errorHint.showMessageDialog(null, "fine");
						
					}
//				}
				
				
			}
		
		});
		
		
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu m = new MainMenu();
				m.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setText("");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setText("");
		
		JLabel lblNewLabel = new JLabel("Enter existing user name");
		
		JLabel lblNewLabel_1 = new JLabel("Enter existing user name");
		
		JButton btnRepLaA = new JButton("Replay");
		btnRepLaA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				replayBoard = new NewJBoardReplay();
				replayBoard.setVisible(true);
				QuoridorApplication.getLoadposition().setVisible(false);
				QuoridorController.jumpToStart();
				ReplayRefresh.refreshBoardInReplayMode();
				
//				QuoridorController.jumpToStart();
//				
//				
//				try {
//					QuoridorController.movePlayer("white","up");
//					QuoridorController.movePlayer("black","down");
//					QuoridorController.movePlayer("white","up");
//					QuoridorController.movePlayer("black","left");
//					QuoridorController.movePlayer("white","right");
//					
//					QuoridorController.grabWall();
//					QuoridorController.MoveWall("up");
//					QuoridorController.releaseWall();
//					
//					QuoridorController.grabWall();
//					QuoridorController.flipWall();
//					QuoridorController.MoveWall("left");
//					QuoridorController.MoveWall("left");
//					QuoridorController.MoveWall("left");
//					QuoridorController.releaseWall();
//					
//					QuoridorController.jumpToStart();
//				} catch (CloneNotSupportedException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}

				
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(38)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(943, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(405)
					.addComponent(lblChooseGameTo, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
					.addGap(495))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(372)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
					.addGap(597))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(328)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(comboBox, 0, 342, Short.MAX_VALUE)
							.addGap(400))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnRepLaA)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel))
									.addGap(42)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_1)
										.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addGap(414))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(38)
					.addComponent(btnNewButton_1)
					.addGap(59)
					.addComponent(lblChooseGameTo, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
					.addGap(43)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(79)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(128)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
						.addComponent(btnRepLaA, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(151))
		);
		contentPane.setLayout(gl_contentPane);
	}
}