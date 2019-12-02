package ca.mcgill.ecse223.quoridor.controller;

import ca.mcgill.ecse223.quoridor.QuoridorApplication;
import ca.mcgill.ecse223.quoridor.model.model4.Board;
import ca.mcgill.ecse223.quoridor.model.model4.Game;
import ca.mcgill.ecse223.quoridor.model.model4.GamePosition;
import ca.mcgill.ecse223.quoridor.model.model4.Player;
import ca.mcgill.ecse223.quoridor.model.model4.PlayerPosition;
import ca.mcgill.ecse223.quoridor.model.model4.Tile;
import ca.mcgill.ecse223.quoridor.model.model4.Wall;

public class FourPlayerController {
	public static void initializeBoard4() {

		Board board = new Board(QuoridorApplication.getQuoridor4());
		for(int i = 1; i<= 9; i++) {
			for(int j = 1; j<=9; j++) {
				Tile tile = new Tile(i, j, board);
				board.addTile(tile);
			}
		}
		Game game = QuoridorApplication.getQuoridor4().getCurrentGame();
		QuoridorApplication.getQuoridor4().setBoard(board);
		Player whitePlayer = QuoridorApplication.getQuoridor4().getCurrentGame().getWhitePlayer();
		Player yellowPlayer = QuoridorApplication.getQuoridor4().getCurrentGame().getYellowPlayer();
		Player blackPlayer = QuoridorApplication.getQuoridor4().getCurrentGame().getBlackPlayer();
		Player redPlayer = QuoridorApplication.getQuoridor4().getCurrentGame().getRedPlayer();
		
		whitePlayer.setGameAsWhite(game);
		Tile initWhite = new Tile(9, 5, board);
		Tile initYellow = new Tile(5, 9, board);
		Tile initBlack = new Tile(1, 5, board);
		Tile initRed = new Tile(5, 1, board);
		
		
		PlayerPosition initialWhite = new PlayerPosition(whitePlayer, initWhite);
		PlayerPosition initialYellow = new PlayerPosition(whitePlayer, initYellow);
		PlayerPosition initialBlack = new PlayerPosition(whitePlayer, initBlack);
		PlayerPosition initialRed = new PlayerPosition(whitePlayer, initRed);

		GamePosition g = new GamePosition(0, initialWhite, initialYellow, initialBlack, initialRed, whitePlayer, game);
		g.setPlayerToMove(whitePlayer);
		game.setCurrentPosition(g);

		initializeWhiteWall4(g,whitePlayer);
		initializeYellowWall4(g,yellowPlayer);
		initializeBlackWall4(g,blackPlayer);
		initializeRedWall4(g,redPlayer);
		g.setPlayerToMove(whitePlayer);
	}
	
	/**
	 * Feature: InitialzeBoard
	 * This static method initializes initial number of walls left for the white player,
	 * contains all of them in a list.
	 * It returns true if the initiating is successful.
	 * 
	 * @author Sun, Gengyi && Yuelin Liu
	 * @param whitePlayer
	 * @return A flag indicating whether the method successfully launched.
	 */
	public static void initializeWhiteWall4(GamePosition g,Player player) {
		int index = 1;
		if(!player.hasWalls()) {
			for(int i = 0; i<10;i++) {
				Wall wall = new Wall(index+i, player);		
				g.addWhiteWallsInStock(wall);
			}
		}

	}
	
	
	/**
	 * Feature: InitialzeBoard
	 * This static method initializes initial number of walls left for the white player,
	 * contains all of them in a list.
	 * It returns true if the initiating is successful.
	 * 
	 * @author Sun, Gengyi && Yuelin Liu
	 * @param whitePlayer
	 * @return A flag indicating whether the method successfully launched.
	 */
	public static void initializeYellowWall4(GamePosition g,Player player) {
		int index = 11;
		if(!player.hasWalls()) {
			for(int i = 0; i<10;i++) {
				Wall wall = new Wall(index+i, player);		
				g.addYellowWallsInStock(wall);
			}
		}

	}
	
	/**
	 * Feature: InitialzeBoard
	 * This static method initializes initial number of walls left for the white player,
	 * contains all of them in a list.
	 * It returns true if the initiating is successful.
	 * 
	 * @author Sun, Gengyi && Yuelin Liu
	 * @param whitePlayer
	 * @return A flag indicating whether the method successfully launched.
	 */
	public static void initializeBlackWall4(GamePosition g,Player player) {
		int index = 21;
		if(!player.hasWalls()) {
			for(int i = 0; i<10;i++) {
				Wall wall = new Wall(index+i, player);		
				g.addBlackWallsInStock(wall);
			}
		}

	}
	
	
	/**
	 * Feature: InitialzeBoard
	 * This static method initializes initial number of walls left for the white player,
	 * contains all of them in a list.
	 * It returns true if the initiating is successful.
	 * 
	 * @author Sun, Gengyi && Yuelin Liu
	 * @param whitePlayer
	 * @return A flag indicating whether the method successfully launched.
	 */
	public static void initializeRedWall4(GamePosition g,Player player) {
		int index = 31;
		if(!player.hasWalls()) {
			for(int i = 0; i<10;i++) {
				Wall wall = new Wall(index+i, player);		
				g.addRedWallsInStock(wall);
			}
		}

	}
}
