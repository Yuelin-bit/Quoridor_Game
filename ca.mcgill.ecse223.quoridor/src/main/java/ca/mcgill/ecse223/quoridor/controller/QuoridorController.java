package ca.mcgill.ecse223.quoridor.controller;

import ca.mcgill.ecse223.quoridor.model.Tile;
import ca.mcgill.ecse223.quoridor.model.User;

public class QuoridorController {
	
	public static void loadGame(int number){ 
		//TO-DO: Write logic to load game
		throw new UnsupportedOperationException();
	}
	
	public static void validatePosition() {
		//TO-DO: Write logic to load game
		throw new UnsupportedOperationException();
	}
	
	public static Tile getPlayerTile(String name) {
		//TO-DO: Write logic to load game
		throw new UnsupportedOperationException();
	}	
	
	public static Tile getWhiteTile() {
		//TO-DO: Write logic to load game
		//game.getCurrentPosition().getWhitePosition().getTile()
		throw new UnsupportedOperationException();
	}
	
	public static Tile getBlackTile() {
		//TO-DO: Write logic to load game
		//game.getCurrentPosition().getBlackPosition().getTile()
		throw new UnsupportedOperationException();
	}
	
	/**
	 * This is a static method which takes two User parameters to initialize a new game. 
	 * It will return a boolean value to indicate if a new game is successfully initialized.
	 * 
	 * @author Pengnan Fan
	 * @param user1 The first player who will join in the game
	 * @param user2 The second player who will join in the game
	 * @return A boolean value to indicate if a new game is initialized successfully
	 * 
	 */
	public static boolean initializeNewGame(User user1, User user2, int thinking_time_seconds) {
		//TODO: To be implemented
		throw new UnsupportedOperationException();
	}
	
	/**
	 * This is a static method which takes an user and a name, then set the latter as 
	 * the new name of the former. It will return a boolean value to indicate if the name
	 * is updated successfully.
	 * 
	 * @author Pengnan Fan
	 * @param aUser The player who want to select a new name
	 * @param name The new name 
	 * @return A boolean value to indicate if the name of the user has been updated
	 */
	public static boolean updateNewUserName(User aUser, String name) {
		//TODO: To be implemented
		throw new UnsupportedOperationException();
	}
	
}
