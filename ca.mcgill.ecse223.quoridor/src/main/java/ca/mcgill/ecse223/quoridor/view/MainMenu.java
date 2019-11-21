package ca.mcgill.ecse223.quoridor.view;
/**
 * 
 * Name : MainMenu
 * 
 * @author EveryOne
 * @version this is our main page, you can create new users and start new game
 * @exception nothing
 *
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.quoridor.QuoridorApplication;
import ca.mcgill.ecse223.quoridor.controller.QuoridorController;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;

public class MainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
					//QuoridorController.initQuoridorAndBoard();
					QuoridorController.initializeNewGame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setTitle("McQuoridor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 500, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Play 1 on 1");
		btnNewButton.setBounds(168, 165, 166, 75);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuoridorController.initializeNewGame(); //boolean not catched
				SelectName page = new SelectName();
				page.setVisible(true);
				setVisible(false);//close the mainMenu.
				dispose();
			}
		});
		
		JButton btnNewButton_1 = new JButton("Load Position");
		btnNewButton_1.setBounds(168, 525, 166, 75);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadPosition loadposition = new LoadPosition();
				loadposition.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		
		JButton btnNewButton_2 = new JButton("Registeration");
		btnNewButton_2.setBounds(168, 47, 166, 75);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registeration r = new Registeration();
				r.setVisible(true);
			}
		});
		
		JButton continue_old = new JButton("Continue Old Game");
		continue_old.setBounds(168, 641, 166, 75);
		continue_old.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(QuoridorApplication.getQuoridor()!=null) {
					QuoridorApplication.getJboard().setVisible(true);
					QuoridorApplication.getMainMenu().setVisible(false);
				}
			}
		});
		contentPane.setLayout(null);
		contentPane.add(continue_old);
		contentPane.add(btnNewButton_2);
		contentPane.add(btnNewButton_1);
		contentPane.add(btnNewButton);
		
		JButton btnPracticeai = new JButton("Practice (AI)");
		btnPracticeai.setBounds(168, 398, 166, 75);
		contentPane.add(btnPracticeai);
		
		JButton btnPlay = new JButton("Play 1 on 3");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPlay.setBounds(168, 281, 166, 75);
		contentPane.add(btnPlay);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(MainMenu.class.getResource("/ca/mcgill/ecse223/quoridor/resources/timg.jpeg")));
		lblNewLabel.setBackground(Color.ORANGE);
		lblNewLabel.setBounds(0, 0, 494, 790);
		contentPane.add(lblNewLabel);
	}
}
