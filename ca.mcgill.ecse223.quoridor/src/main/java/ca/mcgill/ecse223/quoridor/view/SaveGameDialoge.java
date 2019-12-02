package ca.mcgill.ecse223.quoridor.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.quoridor.QuoridorApplication;
import ca.mcgill.ecse223.quoridor.controller.QuoridorController;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SaveGameDialoge extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaveGameDialoge frame = new SaveGameDialoge();
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
	public SaveGameDialoge() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("Would you like to save your previous game and exit ?");
		
		JButton SaveGameButton = new JButton("Yes!");
		SaveGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuoridorController.overwriteExistingFile();
				setVisible(false);
				QuoridorApplication.getJboard().setVisible(false);
				QuoridorApplication.getQuoridor().getCurrentGame().delete();
				QuoridorApplication.getMainMenu().setVisible(true);
				
			}
		});
		
		JButton SaveGameButton_1 = new JButton("No");
		SaveGameButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuoridorController.cancelOverwriteExistingFile();
				setVisible(false);
				QuoridorApplication.getJboard().setVisible(false);
				QuoridorApplication.getQuoridor().getCurrentGame().delete();
				QuoridorApplication.getMainMenu().setVisible(true);
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(87)
					.addComponent(SaveGameButton)
					.addPreferredGap(ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
					.addComponent(SaveGameButton_1)
					.addGap(84))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(35)
					.addComponent(label)
					.addContainerGap(41, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(80)
					.addComponent(label)
					.addGap(69)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(SaveGameButton_1)
						.addComponent(SaveGameButton))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
