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
import java.awt.SystemColor;

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
		setBounds(0, 0, 570, 800);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Play 1 on 1");
		btnNewButton.setBounds(74, 340, 166, 75);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuoridorController.initializeNewGame(); //boolean not catched
				SelectName page = new SelectName();
				page.setVisible(true);
				setVisible(false);//close the mainMenu.
				dispose();
			}
		});
		
		JButton btnNewButton_1 = new JButton("Load Game");
		btnNewButton_1.setBounds(328, 197, 166, 75);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadPosition loadposition = new LoadPosition();
				QuoridorApplication.setLoadposition(loadposition);
				loadposition.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		
		JButton btnNewButton_2 = new JButton("Registeration");
		btnNewButton_2.setBounds(74, 197, 166, 75);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registeration r = new Registeration();
				r.setVisible(true);
			}
		});
		
		JButton continue_old = new JButton("Continue Old Game");
		continue_old.setBounds(328, 465, 166, 75);
		continue_old.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(QuoridorApplication.getQuoridor()!=null&&QuoridorApplication.getQuoridor().getCurrentGame()!=null) {
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
		btnPracticeai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuoridorApplication.getJboardAI().setVisible(true);
			}
		});
		btnPracticeai.setBounds(74, 465, 166, 75);
		contentPane.add(btnPracticeai);
		
		JButton btnPlay = new JButton("Play 1 on 3");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewJBoard4 a = new NewJBoard4();
				QuoridorApplication.setJboard4(a);
				QuoridorApplication.getJboard4().setVisible(true);
				QuoridorApplication.getMainMenu().setVisible(false);
			}
		});
		btnPlay.setBounds(328, 340, 166, 75);
		contentPane.add(btnPlay);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(MainMenu.class.getResource("/ca/mcgill/ecse223/quoridor/resources/1575219678_187266.png")));
		lblNewLabel_1.setBounds(127, 0, 569, 124);
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel("New label");
		label.setIcon(new ImageIcon(MainMenu.class.getResource("/ca/mcgill/ecse223/quoridor/resources/1575219678_187266.png")));
		label.setBounds(-332, 0, 466, 124);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(MainMenu.class.getResource("/ca/mcgill/ecse223/quoridor/resources/greyBack2.jpg")));
		lblNewLabel.setBackground(Color.ORANGE);
		lblNewLabel.setBounds(0, 0, 569, 557);
		contentPane.add(lblNewLabel);
		
		JLabel lblGreyback = new JLabel("greyBack");
		lblGreyback.setIcon(new ImageIcon(MainMenu.class.getResource("/ca/mcgill/ecse223/quoridor/resources/greyB.jpg")));
		lblGreyback.setBounds(0, 552, 569, 238);
		contentPane.add(lblGreyback);
		
	
		
		
		
		
		
		
	}
}
