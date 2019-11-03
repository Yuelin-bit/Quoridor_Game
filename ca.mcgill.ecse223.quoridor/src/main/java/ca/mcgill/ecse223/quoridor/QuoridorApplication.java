package ca.mcgill.ecse223.quoridor;

import ca.mcgill.ecse223.quoridor.model.Quoridor;
import ca.mcgill.ecse223.quoridor.view.Board;
import ca.mcgill.ecse223.quoridor.view.MainMenu;

public class QuoridorApplication {

	private static Quoridor quoridor;
	static MainMenu mainMenu;
	public static void main(String[] args) {
		// start UI
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
