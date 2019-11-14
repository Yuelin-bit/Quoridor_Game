package ca.mcgill.ecse223.quoridor.view;

import javax.swing.JPanel;
import javax.swing.JButton;

public class JTButton extends JPanel {

	/**
	 * Create the panel.
	 */
	public JTButton() {
		setLayout(null);
		setBounds(0,0,560,800);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 560, 800);
		add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(51, 168, 117, 29);
		panel.add(btnNewButton);

	}
}
