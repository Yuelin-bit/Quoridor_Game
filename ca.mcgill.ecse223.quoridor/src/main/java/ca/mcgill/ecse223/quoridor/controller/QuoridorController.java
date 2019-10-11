package ca.mcgill.ecse223.quoridor.controller;

import ca.mcgill.ecse223.quoridor.model.Board;
import ca.mcgill.ecse223.quoridor.model.Game;
import ca.mcgill.ecse223.quoridor.model.GamePosition;
import ca.mcgill.ecse223.quoridor.model.Player;
import ca.mcgill.ecse223.quoridor.model.Quoridor;

public class QuoridorController {

	/**
	 * @author sun gengyi
	 * This method sets a player's total thinking time by calling method in Player class.
	 * @param min
	 * @param sec
	 */
	public static boolean setTotalThinkingTime(Integer min, Integer sec) {
		throw new UnsupportedOperationException();
	}
	/**
	 * @author sun gengyi
	 * This method initialize a new board with 9*9 tiles.
	 */
	public static boolean initializeBoard() {
		throw new UnsupportedOperationException();
	}

	/**
	 * @author sun gengyi
	 * This method set the white pawn to initial location.
	 * @param whitePlayer
	 */
	public static boolean initializeWhitePawn(Player whitePlayer) {
		throw new UnsupportedOperationException();
	}
	/**
	 * @author sun gengyi
	 * This method sets the black pawn to initial position.
	 * @param blackPlayer
	 */
	public static boolean initializedBlackPawn(Player blackPlayer) {
		throw new UnsupportedOperationException();
	}
	/**
	 * @author sun gengyi
	 * This method sets the white player to be the first one who moves.
	 * @param whitePlayer
	 */
	public static boolean setFirstToMove(Player whitePlayer) {
		throw new UnsupportedOperationException();
	}
	/**
	 * @author sun gengyi
	 * This method initializes initial number of walls left for the black player, 
	 * contains all of them in a list.
	 * @param blackPlayer
	 */
	public static boolean initializeBlackWall(Player blackPlayer) {
		throw new UnsupportedOperationException();
	}
	/**
	 * @author sun gengyi
	 * This method initializes initial number of walls left for the white player,
	 * contains all of them in a list.
	 * @param whitePlayer
	 */
	public static boolean initializeWhiteWall(Player whitePlayer) {
			throw new UnsupportedOperationException();
	}

	/**
	 * @author sun gengyi
	 * This method checks the clock of the current player to count down.
	 * @param player
	 */	
	public static boolean clockIsRunning(Player player) {
		throw new UnsupportedOperationException();
	}
	
}
