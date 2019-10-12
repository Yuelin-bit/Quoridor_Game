package ca.mcgill.ecse223.quoridor.controller;


import ca.mcgill.ecse223.quoridor.model.Player;

public class QuoridorController {
	
	/**
	 * Feature:laod game
	 * This method load a game by input a filename
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
	 * This method validate if all the pawn and wall position at board 
	 * is with the board boundary 
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
	 * This method return the result of loading game by showing a string
	 * "Failed to load game" or "Successfully load game!"
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
	 * This method let player to make a move in the game
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
	 * This method start a clock to count the time passed for the player
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
	 * This method stop a clock the player
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
	 * This method test if the clock of the player is still running
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
	 * It returns true if the clock is counting down.
	 * 
	 * @author Sun, Gengyi
	 * @param player
	 * @return A flag indicating whether the method successfully launched.
	 */	
	public static boolean clockIsCountingDown(Player player) {
		throw new UnsupportedOperationException();
	}

}
