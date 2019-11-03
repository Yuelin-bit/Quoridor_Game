package ca.mcgill.ecse223.quoridor;

import ca.mcgill.ecse223.quoridor.model.Quoridor;
import ca.mcgill.ecse223.quoridor.view.JBoard;
import ca.mcgill.ecse223.quoridor.view.MainMenu;

public class QuoridorApplication {

	private static Quoridor quoridor;
	static MainMenu mainMenu;
	private static JBoard jboard;
	public static JBoard getJboard() {
		return jboard;
	}

	public static void main(String[] args) {
		// start UI
		mainMenu = new MainMenu();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                mainMenu.setVisible(true);
            }
        });
	}
	
	public static Quoridor getQuoridor() {
		if (quoridor == null) {
			quoridor = new Quoridor();
		}
 		return quoridor;
	}

}
