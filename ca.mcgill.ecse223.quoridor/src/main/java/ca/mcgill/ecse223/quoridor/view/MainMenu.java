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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
				if(!QuoridorApplication.getQuoridor().hasBoard()) {

					QuoridorController.initializeEmptyBoard();
				}
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
				if(QuoridorApplication.getQuoridor().hasCurrentGame()) {
					QuoridorApplication.getQuoridor().getCurrentGame().delete();
				}
				QuoridorController.initializeNewGame();
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
				} else {
					JOptionPane.showOptionDialog(null, "You cannot continue game without having a game.",
			                "Warning",
			                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"OK"}, "OK");
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
				if(QuoridorApplication.getQuoridor().hasCurrentGame()) {
					QuoridorApplication.getQuoridor().getCurrentGame().delete();
				}
				NewJBoardAI jai = new NewJBoardAI();
				QuoridorApplication.setJboardAI(jai);
				QuoridorApplication.getJboardAI().setVisible(true);
				
				
				
				
				
				
				
				
				QuoridorController.initializeNewGame();
			    QuoridorController.replay();
				Quoridor quoridorR = QuoridorApplication.getQuoridor();
				//new Game(GameStatus.Initializing, MoveMode.WallMove, QuoridorApplication.getQuoridor());
				if(QuoridorApplication.getQuoridor().getBoard()==null) {
					Quoridor quoridor = QuoridorApplication.getQuoridor();
					Board board = new Board(quoridor);
					// Creating tiles by rows, i.e., the column index changes with every tile
					// creation
					for (int i = 1; i <= 9; i++) { // rows
						for (int j = 1; j <= 9; j++) { // columns
							board.addTile(i, j);
						}
					}
				}
				User user1 = quoridorR.addUser("whiteReplayer");
				User user2 = quoridorR.addUser("blackReplayer");
				int thinkingTime = 180;
				Player player1 = new Player(new Time(thinkingTime), user1, 9, Direction.Horizontal);
				Player player2 = new Player(new Time(thinkingTime), user2, 1, Direction.Horizontal);
				Player[] players = { player1, player2 };
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 10; j++) {
						new Wall(i * 10 + j+1, players[i]);
					}
				}
				
				Tile player1StartPos = quoridorR.getBoard().getTile(76);
				Tile player2StartPos = quoridorR.getBoard().getTile(4);
				QuoridorApplication.getQuoridor().getCurrentGame().setWhitePlayer(player1);
				QuoridorApplication.getQuoridor().getCurrentGame().setBlackPlayer(player2);

				Game gameR = QuoridorApplication.getQuoridor().getCurrentGame();
				PlayerPosition player1Position = new PlayerPosition(quoridorR.getCurrentGame().getWhitePlayer(), player1StartPos);
				PlayerPosition player2Position = new PlayerPosition(quoridorR.getCurrentGame().getBlackPlayer(), player2StartPos);
				GamePosition gamePosition = new GamePosition(0, player1Position, player2Position, player1, gameR);
				
				for (int j = 0; j < 10; j++) {
					Wall wall = Wall.getWithId(j+1);
					gamePosition.addWhiteWallsInStock(wall);
				}
				for (int j = 0; j < 10; j++) {
					Wall wall = Wall.getWithId(j + 10+1);
					gamePosition.addBlackWallsInStock(wall);
				}
				gameR.setCurrentPosition(gamePosition);
				
				
				
				
				
				
				
				
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