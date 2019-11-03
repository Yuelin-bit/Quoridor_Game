
package ca.mcgill.ecse223.quoridor.view;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.quoridor.QuoridorApplication;
import ca.mcgill.ecse223.quoridor.controller.QuoridorController;
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
		setBackground(Color.PINK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1080, 720);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblSelectPlayerName = new JLabel("Select Player Name");
		
		JLabel lblWhiteplayer = new JLabel("Whiteplayer");
		
		JLabel lblBlackplayer = new JLabel("Blackplayer");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		
		List<User> us = QuoridorApplication.getQuoridor().getUsers();
		String[] names = new String[us.size()];
		int idx = 0;
		for(User u : us) {
			names[idx++] = u.getName();
		}
		
		comboBox.setModel(new DefaultComboBoxModel(names));
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setEditable(true);

		
		comboBox_1.setModel(new DefaultComboBoxModel(names));
		
		JButton btnConfirm = new JButton("Confirm");
		
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.print(String.valueOf(comboBox.getSelectedItem()));
			}
		});
		
		JButton btnConfirm_1 = new JButton("Confirm");
		
		btnConfirm_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.print(String.valueOf(comboBox_1.getSelectedItem()));
			}
		});
		
		JButton btnBack = new JButton("Back");
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu m = new MainMenu();
				m.setVisible(true);
				setVisible(false);
			}
		});

		JLabel lblThinkingTime = new JLabel("Thinking Time");
		
		textField = new JTextField();
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		
		JLabel lblMin = new JLabel("Minute");		
		JLabel lblSec = new JLabel("Second");
		JButton btnStart = new JButton("Start");
		
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer min = 3;
				Integer sec = 0; 
				if(!textField.getText().equals("")) {
					min = Integer.parseInt(textField.getText());
				}
				if(!textField_1.getText().equals("")) {
					sec = Integer.parseInt(textField_1.getText());
				}
			//	QuoridorController.setTotalThinkingTime(min, sec);
			//  QuoridorController.initializeBoard();
				Board board = new Board();
				board.setVisible(true);
				setVisible(false);
			}
		});

		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(452)
					.addComponent(lblSelectPlayerName)
					.addContainerGap(434, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(375)
					.addComponent(btnStart)
					.addGap(36)
					.addComponent(btnBack)
					.addContainerGap(491, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(217, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblWhiteplayer)
						.addComponent(lblBlackplayer)
						.addComponent(lblThinkingTime))
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblMin)
							.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblSec)
							.addGap(4))
						.addComponent(comboBox_1, 0, 277, Short.MAX_VALUE)
						.addComponent(comboBox, 0, 277, Short.MAX_VALUE))
					.addGap(51)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnConfirm_1)
						.addComponent(btnConfirm))
					.addGap(253))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(44)
					.addComponent(lblSelectPlayerName)
					.addGap(64)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWhiteplayer)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnConfirm))
					.addGap(52)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBlackplayer)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnConfirm_1))
					.addGap(46)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblThinkingTime)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMin)
						.addComponent(lblSec)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(66)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnStart)
						.addComponent(btnBack))
					.addContainerGap(226, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
////=======
//package ca.mcgill.ecse223.quoridor.view;
//
//import java.awt.BorderLayout;
//import java.awt.EventQueue;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import java.awt.Color;
//import javax.swing.JLabel;
//import javax.swing.GroupLayout;
//import javax.swing.GroupLayout.Alignment;
//import javax.swing.JComboBox;
//import javax.swing.JButton;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//
//public class Page extends JFrame {
//
//	private JPanel contentPane;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Page frame = new Page();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
//	public Page() {
//		setBackground(Color.PINK);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(0, 0, 1080, 720);
//		contentPane = new JPanel();
//		contentPane.setBackground(Color.PINK);
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		
//		JLabel lblSelectPlayerName = new JLabel("Select Player Name");
//		
//		JLabel lblWhiteplayer = new JLabel("Whiteplayer");
//		
//		JLabel lblBlackplayer = new JLabel("Blackplayer");
//		
//		JComboBox comboBox = new JComboBox();
//		comboBox.setEditable(true);
//
//		
//		JComboBox comboBox_1 = new JComboBox();
//		comboBox_1.setEditable(true);
//
//		
//		JButton btnConfirm = new JButton("Confirm");
//		
//		JButton btnConfirm_1 = new JButton("Confirm");
//		
//		JButton btnBack = new JButton("Back");
//		
//		JButton btnStart = new JButton("Start");
//		btnStart.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				Board board = new Board();
//				board.setVisible(true);
//				setVisible(false);//close the page.
//			}
//		});
//		GroupLayout gl_contentPane = new GroupLayout(contentPane);
//		gl_contentPane.setHorizontalGroup(
//			gl_contentPane.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_contentPane.createSequentialGroup()
//					.addGap(452)
//					.addComponent(lblSelectPlayerName)
//					.addContainerGap(300, Short.MAX_VALUE))
//				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
//					.addContainerGap(272, Short.MAX_VALUE)
//					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
//						.addComponent(lblWhiteplayer)
//						.addComponent(lblBlackplayer))
//					.addGap(56)
//					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
//						.addComponent(comboBox_1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE))
//					.addGap(51)
//					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
//						.addComponent(btnConfirm_1)
//						.addComponent(btnConfirm))
//					.addGap(253))
//				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
//					.addContainerGap(446, Short.MAX_VALUE)
//					.addComponent(btnStart)
//					.addGap(30)
//					.addComponent(btnBack)
//					.addGap(426))
//		);
//		gl_contentPane.setVerticalGroup(
//			gl_contentPane.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_contentPane.createSequentialGroup()
//					.addGap(44)
//					.addComponent(lblSelectPlayerName)
//					.addGap(64)
//					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
//						.addComponent(lblWhiteplayer)
//						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//						.addComponent(btnConfirm))
//					.addGap(52)
//					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
//						.addComponent(lblBlackplayer)
//						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//						.addComponent(btnConfirm_1))
//					.addGap(60)
//					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
//						.addComponent(btnStart)
//						.addComponent(btnBack))
//					.addContainerGap(326, Short.MAX_VALUE))
//		);
//		contentPane.setLayout(gl_contentPane);
//	}
//}