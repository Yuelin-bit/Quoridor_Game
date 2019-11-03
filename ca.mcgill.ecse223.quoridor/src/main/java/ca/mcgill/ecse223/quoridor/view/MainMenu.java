package ca.mcgill.ecse223.quoridor.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		
		
		/*JPanel pnlMain=new JPanel(); //创建面板pnlMain。 
		pnlMain.add(contentPane);
		Icon i =new ImageIcon(MainMenu.class.getResource("/ca/mcgill/ecse223/quoridor/resources/timg.jpeg"));
		JLabel lb = new JLabel(i);
		this.getLayeredPane().add(lb, new Integer(Integer.MIN_VALUE)); 
		lb.setBounds(0, 0,i.getIconWidth(),i.getIconHeight()); 
		contentPane.setOpaque(false);
		pnlMain.add(lb);*/
		
		
		
		JButton btnNewButton = new JButton("New Game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectName page = new SelectName();
				//Page page = new Page();
				page.setVisible(true);
				setVisible(false);//close the mainMenu.
				dispose();
			}
		});
		
		JButton btnNewButton_1 = new JButton("Load Position");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadPosition loadposition = new LoadPosition();
				loadposition.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		
		JButton btnNewButton_2 = new JButton("Register");

		
		
		
		
		
		//JButton btnNewButton_1 = new JButton("New button");
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registeration r = new Registeration();
				r.setVisible(true);
			}
		});
		
		JButton btnNewButton_3 = new JButton("New button");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(410)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnNewButton_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
					.addContainerGap(419, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(72)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addGap(52)
					.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addGap(63)
					.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(128, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
	
	}
	
	 public void paintComponent(Graphics g) {
		   int x = 0, y = 0;
		   ImageIcon icon = new ImageIcon("/ca.mcgill.ecse223.quoridor/src/main/java/ca/mcgill/ecse223/quoridor/resources/tmg.jpeg");// 003.jpg是测试图片在项目的根目录下
		   g.drawImage(icon.getImage(), x, y, getSize().width,
		     getSize().height, this);// 图片会自动缩放
//		    g.drawImage(icon.getImage(), x, y,this);//图片不会自动缩放
		  }
}
