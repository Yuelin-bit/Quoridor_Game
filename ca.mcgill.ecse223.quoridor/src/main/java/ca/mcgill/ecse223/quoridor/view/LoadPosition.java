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
import javax.swing.JComboBox;

public class LoadPosition extends JFrame {

	private JPanel contentPane;

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
			}
		});
		
		JComboBox<String> FileName = new JComboBox<String>();

				try (Stream<Path> walk = Files.walk(Paths.get(""))) {

					List<String> result = walk.map(x -> x.toString())
							.filter(f -> f.endsWith(".dat")).collect(Collectors.toList());

					for (String file : result) {
						FileName.addItem(file);
					}

				} catch (IOException e1) {
					e1.printStackTrace();
				}	

		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu m = new MainMenu();
				m.setVisible(true);
				setVisible(false);	
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(458)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(537))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(16)
					.addComponent(btnNewButton_1)
					.addContainerGap(979, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(333)
					.addComponent(lblChooseGameTo, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
					.addGap(418))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(320)
					.addComponent(FileName, 0, 356, Short.MAX_VALUE)
					.addGap(394))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addComponent(btnNewButton_1)
					.addGap(53)
					.addComponent(lblChooseGameTo, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(FileName, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
					.addGap(208)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(234))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
