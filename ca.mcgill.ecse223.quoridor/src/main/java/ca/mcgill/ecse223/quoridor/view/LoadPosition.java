package ca.mcgill.ecse223.quoridor.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.awt.event.ActionEvent;

public class LoadPosition extends JFrame {

	private JPanel contentPane;
	JComboBox<String> comboBox;

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
		
		JButton btnNewButton = new JButton("Load");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filename = (String) comboBox.getSelectedItem();
			}
		
		});
		

		//JComboBox<String> FileName = new JComboBox<String>();

//				try (Stream<Path> walk = Files.walk(Paths.get(""))) {
//
//					List<String> result = walk.map(x -> x.toString())
//							.filter(f -> f.endsWith(".dat")).collect(Collectors.toList());
//
//					for (String file : result) {
//						FileName.addItem(file);
//					}
//
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}	


		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu m = new MainMenu();
				m.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		
		JComboBox<String> comboBox = new JComboBox<String>();
		try (Stream<Path> walk = Files.walk(Paths.get(""))) {

			List<String> result = walk.map(x -> x.toString())
					.filter(f -> f.endsWith(".dat")).collect(Collectors.toList());

			for (String file : result) {
				comboBox.addItem(file);
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}	
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(420)
					.addComponent(lblChooseGameTo)
					.addGap(410))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(328)
					.addComponent(comboBox, 0, 342, Short.MAX_VALUE)
					.addGap(400))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(453)
					.addComponent(btnNewButton)
					.addContainerGap(542, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(126)
					.addComponent(lblChooseGameTo)
					.addGap(69)
					.addComponent(comboBox)
					.addGap(256)
					.addComponent(btnNewButton)
					.addGap(165))
		);
		contentPane.setLayout(gl_contentPane);
	}
}