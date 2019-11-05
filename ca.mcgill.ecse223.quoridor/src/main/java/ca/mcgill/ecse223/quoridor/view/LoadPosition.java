package ca.mcgill.ecse223.quoridor.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.quoridor.controller.QuoridorController;
import ca.mcgill.ecse223.quoridor.model.Player;

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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.awt.event.ActionEvent;

public class LoadPosition extends JFrame {
	
	private int loadNum = 0;
	private JLabel errorLabel;
	private String error = null;
	private JPanel contentPane;
	private JOptionPane errorHint;
	JComboBox<String> comboBox;
	

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
		
		

		
		comboBox = new JComboBox<String>();
		try (Stream<Path> walk = Files.walk(Paths.get(""))) {

			List<String> result = walk.map(x -> x.toString())
					.filter(f -> f.endsWith(".dat")).collect(Collectors.toList());

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
				if (loadNum == 1) {
					errorHint.showMessageDialog(null, "Can't load position twice");
				} else {
					String filename = (String) comboBox.getSelectedItem();
					ArrayList<Player> playersList = QuoridorController.createUsersAndPlayers("Quintus", "Bozhong");
					loadNum = 1;
					try {
						QuoridorController.loadPosition(filename, playersList.get(0), playersList.get(1));
						QuoridorController.validation();
					} catch (Exception e1) {
						errorHint.showMessageDialog(null, "failed to load game");
						
					}
				}
				
				
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
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(328)
					.addComponent(comboBox, 0, 342, Short.MAX_VALUE)
					.addGap(400))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(38)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(943, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(405)
					.addComponent(lblChooseGameTo, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
					.addGap(495))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(447)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
					.addGap(535))
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
					.addGap(255)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
					.addGap(151))
		);
		contentPane.setLayout(gl_contentPane);
	}
}