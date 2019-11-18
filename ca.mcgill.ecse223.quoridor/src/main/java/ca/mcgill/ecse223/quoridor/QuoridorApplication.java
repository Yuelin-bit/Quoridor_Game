package ca.mcgill.ecse223.quoridor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import ca.mcgill.ecse223.quoridor.controller.QuoridorController;
import ca.mcgill.ecse223.quoridor.model.Quoridor;
import ca.mcgill.ecse223.quoridor.view.LoadPosition;
import ca.mcgill.ecse223.quoridor.view.MainMenu;
import ca.mcgill.ecse223.quoridor.view.NewJBoard;

public class QuoridorApplication {

	private static Quoridor quoridor;
	static MainMenu mainMenu;
	public static MainMenu getMainMenu() {
		return mainMenu;
	}
	public static void setMainMenu(MainMenu mainMenu) {
		QuoridorApplication.mainMenu = mainMenu;
	}

	private static NewJBoard jboard;
	private static LoadPosition loadposition;
	private JOptionPane errorHint;
	private String error = null;
	private boolean grab = false;
	private JLabel lblBlackStock;
	private JLabel lblWhiteBlock;
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public JOptionPane getErrorHint() {
		return errorHint;
	}
	public void setErrorHint(JOptionPane errorHint) {
		this.errorHint = errorHint;
	}
	
	public static void setJboard(NewJBoard jboard) {

		QuoridorApplication.jboard = jboard;
	}

	public static NewJBoard getJboard() {
		return jboard;
	}
	
	public static LoadPosition getLoadPosition() {
		return loadposition;
	}

	public static void main(String[] args) {
		// start UI
		mainMenu = new MainMenu();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	QuoridorController.initQuoridorAndBoard();
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