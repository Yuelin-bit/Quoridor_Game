package ca.mcgill.ecse223.quoridor.controller;

import ca.mcgill.ecse223.quoridor.model.Board;
import ca.mcgill.ecse223.quoridor.model.Game;
import ca.mcgill.ecse223.quoridor.model.GamePosition;
import ca.mcgill.ecse223.quoridor.model.Player;
import ca.mcgill.ecse223.quoridor.model.Quoridor;

public class QuoridorController {

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
