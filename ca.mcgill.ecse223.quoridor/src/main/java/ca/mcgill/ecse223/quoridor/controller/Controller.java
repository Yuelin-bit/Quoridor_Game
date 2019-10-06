package ca.mcgill.ecse223.quoridor.controller;

import ca.mcgill.ecse223.quoridor.model.Board;
import ca.mcgill.ecse223.quoridor.model.Game;
import ca.mcgill.ecse223.quoridor.model.GamePosition;
import ca.mcgill.ecse223.quoridor.model.Player;
import ca.mcgill.ecse223.quoridor.model.Quoridor;

public class Controller {

	
	public static void setTotalThinkingTime(Player currentPlayer, Integer min, Integer sec) {
		
	}

	public static boolean checkIfEqualInitialTime(Player player1, Player player2) {
		//compare two players' remaining time here
		boolean sameRemainingTime = player1.getRemainingTime() == player2.getRemainingTime();
		return sameRemainingTime;
	}
	
	public static void initializeBoard(Quoridor quoridor, Board board) {
	
	}
	
	public static void initializeWhitePawn(Player whitePlayer) {
		
	}
	public static void initializedBlackPawn(Player blackPlayer) {
		
	}
	public static void setFirstToMove(Player whitePlayer) {
		
	}
	public static void initializeBlackWall(Player blackPlayer) {
		
	}
	public static void initializeWhiteWall(Player whitePlayer) {
		
	}
	public static int checkNumOfWall(Player player) {
		return player.getWalls().size();
	}
	public static void countingClock(Player player) {
		
	}
	public static void showPlayerOnMove(GamePosition gamePosition) {
		gamePosition.getPlayerToMove();
	}
	
}
