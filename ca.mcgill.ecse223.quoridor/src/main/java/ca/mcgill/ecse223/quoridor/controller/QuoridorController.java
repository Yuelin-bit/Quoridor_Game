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
	 * This is a static method which takes two User parameters to initialize a new game. 
	 * It will return a boolean value to indicate if a new game is successfully initialized.
	 * 
	 * @author Pengnan Fan
	 * @param user1 The first player who will join in the game
	 * @param user2 The second player who will join in the game
	 * @param thinking_time_minute Total thinking time (minute part) per player
	 * @param thinking_time_second Total thinking time (second part) per player
	 * @return A boolean value to indicate if a new game is initialized successfully
	 * 
	 */
	public static boolean initializeNewGame(User user1, User user2, int thinking_time_minute, int thinking_time_second) {
		//TODO: To be implemented
		throw new UnsupportedOperationException();
	}

	/**
	 * This is a static method which takes an game and a new name, then set the latter as 
	 * the name of the white player of the former. It will return a boolean value to 
	 * indicate if the name is updated successfully.
	 * 
	 * @author Pengnan Fan
	 * @param game The game of the specific white player
	 * @param name The new name 
	 * @return A boolean value to indicate if the name of the user has been updated
	 */
	public static boolean setNewNameWhitePlayer(Game game, String name) {
		//TODO: To be implemented
		throw new UnsupportedOperationException();
	}
	
	/**
	 * This is a static method which takes an game and a new name, then set the latter as 
	 * the name of the black player of the former. It will return a boolean value to 
	 * indicate if the name is updated successfully.
	 * 
	 * @author Pengnan Fan
	 * @param game The game of the specific black player
	 * @param name The new name 
	 * @return A boolean value to indicate if the name of the user has been updated
	 */
	public static boolean setNewNameBlackPlayer(Game game, String name) {
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
	
	/**
	 * This static method sets a player's total thinking time by taking two inputs
	 * in the form (min:sec).
	 * It returns true if setting is successful.
	 * 
	 * @author Sun, Gengyi
	 * @param min
	 * @param sec
	 * @return A flag indicating whether the method successfully launched.
	 */
	public static boolean setTotalThinkingTime(Integer min, Integer sec) {
		throw new UnsupportedOperationException();
	}
	/**
	 * This method initialize a new board with 9*9 tiles. Then it 
	 * initializes pawns and walls for the players. Finally it sets
	 * the same thinking time for both players. The white player will
	 * perform a movement first.
	 * It returns true if the initiating is successful.
	 * 
	 * @author Sun, Gengyi
	 * @return A flag indicating whether the method successfully launched.
	 */
	public static boolean initializeBoard() {
		throw new UnsupportedOperationException();
	}

	/**
	 * This static method set the white player's pawn to initial location(e9).
	 * It returns true if the initiating is successful.
	 * 
	 * @author Sun, Gengyi
	 * @param whitePlayer
	 * @return A flag indicating whether the method successfully launched.
	 */
	public static boolean initializeWhitePawn(Player whitePlayer) {
		throw new UnsupportedOperationException();
	}
	/**
	 * This static method sets the black player's pawn to initial position(e1).
	 * It returns true if the initiating is successful.
	 * 
	 * @author Sun, Gengyi
	 * @param blackPlayer
	 * @return A flag indicating whether the method successfully launched.
	 */
	public static boolean initializedBlackPawn(Player blackPlayer) {
		throw new UnsupportedOperationException();
	}
	/**
	 * This static method initializes initial number of walls left for the black player, 
	 * contains all of them in a list.
	 * It returns true if the initiating is successful.
	 * 
	 * @author Sun, Gengyi
	 * @param blackPlayer
	 * @return A flag indicating whether the method successfully launched.
	 */
	public static boolean initializeBlackWall(Player blackPlayer) {
		throw new UnsupportedOperationException();
	}
	/**
	 * 
	 * This static method initializes initial number of walls left for the white player,
	 * contains all of them in a list.
	 * It returns true if the initiating is successful.
	 * 
	 * @author Sun, Gengyi
	 * @param whitePlayer
	 * @return A flag indicating whether the method successfully launched.
	 */
	public static boolean initializeWhiteWall(Player whitePlayer) {
			throw new UnsupportedOperationException();
	}

	/**
	 * This method checks the clock of the current player to count down.
	 * It returns true if the clock is running.
	 * 
	 * @author Sun, Gengyi
	 * @param player
	 * @return A flag indicating whether the method successfully launched.
	 */	
	public static boolean clockIsRunning(Player player) {
		throw new UnsupportedOperationException();
	}
	
}
