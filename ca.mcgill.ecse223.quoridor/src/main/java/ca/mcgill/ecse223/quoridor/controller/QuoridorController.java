package ca.mcgill.ecse223.quoridor.controller;

import ca.mcgill.ecse223.quoridor.model.*;
import static ca.mcgill.ecse223.quoridor.model.Game.GameStatus;

public class QuoridorController{
	
	/**
	 * Feature:laod game
	 * 
	 * @author Zirui He
	 * @param name of the file 
	 * @return void
	 */
	public static void loadGame(String filename){ 
		//TO-DO: Write logic to load game
		throw new UnsupportedOperationException();
	}
	
	
	/**
	 * Feature:laod game
	 * 
	 * @author Zirui He 
	 * @return boolean
	 */
	public static boolean validatePosition() {
		//TO-DO: Write logic to load game
		throw new UnsupportedOperationException();
	}
		
	
	/**
	 * Feature:laod game
	 * 
	 * @author Zirui He
	 * @return the string shown on the UI
	 */
	public static String getLoadResult() {
		//TO-DO: Write logic to load game
		throw new UnsupportedOperationException();
	}
	
	
	/**
	 * Feature:switch player
	 * 
	 * @author Zirui He
	 * @param player
	 * @return void
	 */
	public static void makeMove(Player player) {
		//TO-DO: Write logic to load game
		throw new UnsupportedOperationException();
	}

	
	/**
	 * Feature: switch player 
	 * 
	 * @author Zirui He
	 * @param player
	 * @return void
	 */
	public static void startClock(Player player) {
		//TO-DO: Write logic to load game
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Feature: switch player
	 * 
	 * @author Zirui He
	 * @param player
	 * @return void
	 */
	public static void stopClock(Player player) {
		//TO-DO: Write logic to load game
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Feature: switch player
	 * 
	 * @author Zirui He
	 * @param player
	 * @return boolean
	 */
	public static boolean clockIsRunning(Player player) {
		//TO-DO: Write logic to load game
		throw new UnsupportedOperationException();
	}
	
	/**
	 * This is a static method which takes two User parameters to initialize a new game. 
	 * It will return a boolean value to indicate if a new game is successfully initialized.
	 * 
	 * @author Pengnan Fan
	 * @param user1 The first player who will join in the game
	 * @param user2 The second player who will join in the game
	 * @param thinking_time_seconds Thinking time in the unit of second
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

	/**
	 * This is a static method which check the status of a game. It will return a GameStatus
	 * value of the status of a certain status.
	 * 
	 * @author Pengnan Fan
	 * @param game The game to be checked
	 * @return A GameStatus value of the status of the game to be checked
	 */
	public static GameStatus getGameStatus(Game game) {
		//TODO: To be implemented
		throw new UnsupportedOperationException();
	}
	
}
