package ca.mcgill.ecse223.quoridor.controller;

import ca.mcgill.ecse223.quoridor.model.Board;
import ca.mcgill.ecse223.quoridor.model.Game;
import ca.mcgill.ecse223.quoridor.model.GamePosition;
import ca.mcgill.ecse223.quoridor.model.Player;
import ca.mcgill.ecse223.quoridor.model.Quoridor;

public class Controller {

	/**
	 * This method sets a player's total thinking time by calling method in Player class.
	 * @param min
	 * @param sec
	 */
	public static void setTotalThinkingTime(Integer min, Integer sec) {
		throw new UnsupportedOperationException();
	}

	/**
	 * This method compares both players' initial thinking time, returns true if they are equal.
	 * @param player1
	 * @param player2
	 * @return
	 */
	public static boolean checkIfEqualInitialTime(Player player1, Player player2) {
		/* Some thoughts on how to implement this method
		//compare two players' remaining time here
		boolean sameRemainingTime = false; //default setting 
		//player1.getRemainingTime() == player2.getRemainingTime();
		return sameRemainingTime;
		 */
		throw new UnsupportedOperationException();

	}

	/**
	 * This method initialize a new board with 9*9 tiles.
	 * @param quoridor
	 * @param board
	 */
	public static void initializeBoard(Quoridor quoridor, Board board) {
		throw new UnsupportedOperationException();

	}

	/**
	 * This method set the white pawn to initial location.
	 * @param whitePlayer
	 */
	public static void initializeWhitePawn(Player whitePlayer) {
		throw new UnsupportedOperationException();

	}
	/**
	 * This method sets the black pawn to initial position.
	 * @param blackPlayer
	 */
	public static void initializedBlackPawn(Player blackPlayer) {
		throw new UnsupportedOperationException();

	}
	/**
	 * This method sets the white player to be the first one who moves.
	 * @param whitePlayer
	 */
	public static void setFirstToMove(Player whitePlayer) {
		throw new UnsupportedOperationException();

	}
	/**
	 * This method initializes initial number of walls left for the black player, 
	 * contains all of them in a list.
	 * @param blackPlayer
	 */
	public static void initializeBlackWall(Player blackPlayer) {
		throw new UnsupportedOperationException();

	}
	/**
	 * This method initializes initial number of walls left for the white player,
	 * contains all of them in a list.
	 * @param whitePlayer
	 */
	public static void initializeWhiteWall(Player whitePlayer) {
		throw new UnsupportedOperationException();

	}
	/**
	 * This method checks for if both players have the same number of walls in stock
	 * at the initial stage of the game.
	 * @param player
	 * @return
	 */
	public static int checkNumOfWall(Player player) {

		throw new UnsupportedOperationException();

	}
	
	/**
	 * This method sets the clock of the current player to count down.
	 * @param player
	 */
	public static void countingClock(Player player) {
		throw new UnsupportedOperationException();

	}
	
	/**
	 * This methods gives which player is on move by getting informations 
	 * from GamePosition class.
	 * @param gamePosition
	 */
	public static void showPlayerOnMove(GamePosition gamePosition) {
		throw new UnsupportedOperationException();

	}

}
