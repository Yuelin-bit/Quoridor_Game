package ca.mcgill.ecse223.quoridor.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.awt.Color;

//import org.apache.commons.lang3.time.StopWatch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import java.sql.Time;
import javax.swing.JOptionPane;

import ca.mcgill.ecse223.quoridor.QuoridorApplication;
import ca.mcgill.ecse223.quoridor.controller.TOWall.Direction2;
import ca.mcgill.ecse223.quoridor.model.*;
import ca.mcgill.ecse223.quoridor.model.Game.GameStatus;
import ca.mcgill.ecse223.quoridor.model.Game.MoveMode;
import ca.mcgill.ecse223.quoridor.view.JBoard;
import ca.mcgill.ecse223.quoridor.view.JWall;


public class QuoridorController {
	
	
	/**
	 * 
	 * Refresh and set the environment up.
	 * 
     * @author Yuelin Liu
	 * @return 
	 * @return void
	 * @exception nothing
	 *
	 */
	public static void refreshAndSet() {
		QuoridorApplication.getQuoridor().setBoard(null);
	     QuoridorApplication.getQuoridor().setCurrentGame(null);
	     QuoridorApplication.setJboard(null);
	     QuoridorApplication.getQuoridor().getUsers().remove(0);
	     QuoridorApplication.getQuoridor().getUsers().remove(1);
	     GamePosition oldGamePosition = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition();
	     for (int j = 0; j < 10; j++) {
	      Wall wall = Wall.getWithId(j);
	      oldGamePosition.removeWhiteWallsInStock(wall);
	     }
	     for (int j = 0; j < 10; j++) {
	      Wall wall = Wall.getWithId(j + 10);
	      oldGamePosition.removeBlackWallsInStock(wall);
	     }
	     
	     
		Quoridor quoridor = QuoridorApplication.getQuoridor();
		  Board b = new Board(quoridor);
		  Board board = QuoridorApplication.getQuoridor().getBoard();
		  // Creating tiles by rows, i.e., the column index changes with every tile
		  // creation
		  for (int i = 1; i <= 9; i++) { // rows
		   for (int j = 1; j <= 9; j++) { // columns
		    board.addTile(i, j);
		   }
		  }
		  quoridor.addUser("userNam1");
		  quoridor.addUser("11userNam2");
		  User user1 = quoridor.getUser(0);
		  User user2 = quoridor.getUser(1);

		  int thinkingTime = 180;
		  Player player1 = new Player(new Time(thinkingTime), user1, 9, Direction.Horizontal);
		  Player player2 = new Player(new Time(thinkingTime), user2, 1, Direction.Horizontal);

		  Player[] players = { player1, player2 };
		  for (int i = 0; i < 2; i++) {
		   for (int j = 0; j < 10; j++) {
		    new Wall(i * 10 + j, players[i]);
		   }
		  }
		  
		  ArrayList<Player> playersList = new ArrayList<Player>();
		  playersList.add(player1);
		  playersList.add(player2);
		  Tile player1StartPos = quoridor.getBoard().getTile(4);
		  Tile player2StartPos = quoridor.getBoard().getTile(76);
		 
		  Game game = new Game(GameStatus.Running, MoveMode.PlayerMove, quoridor);
		  game.setWhitePlayer(playersList.get(0));
		  game.setBlackPlayer(playersList.get(1));

		  PlayerPosition player1Position = new PlayerPosition(quoridor.getCurrentGame().getWhitePlayer(), player1StartPos);
		  PlayerPosition player2Position = new PlayerPosition(quoridor.getCurrentGame().getBlackPlayer(), player2StartPos);
		 GamePosition gamePosition = new GamePosition(0, player1Position, player2Position, playersList.get(0), game);
		 

		 // Add the walls as in stock for the players
		 for (int j = 1; j < 11; j++) {
		  Wall wall = Wall.getWithId(j);
		  gamePosition.addWhiteWallsInStock(wall);
		 }
		 for (int j = 1; j < 11; j++) {
		  Wall wall = Wall.getWithId(j + 10);
		  gamePosition.addBlackWallsInStock(wall);
		 }

		  game.setCurrentPosition(gamePosition);
		
	}
	
	
	/**
	 * 
	 * Get the white walls on board, and transfer it
	 * 
     * @author Yuelin Liu
	 * @param wallmove WallMove candidate to be check whether it is valid(to avoid overlapping).
	 * @return boolean whether there is a wall in the same position.
	 * @exception nothing
	 *
	 */
	public static ArrayList<TOWall> getWhiteWallsOnBoard(){
		ArrayList<TOWall> w = new ArrayList<TOWall>();
		for(Wall wall : QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard()) {
			TOWall temp = null;
			temp.setRow(wall.getMove().getTargetTile().getRow());
			temp.setColumn(wall.getMove().getTargetTile().getColumn());
			if(wall.getMove().getWallDirection()==Direction.Horizontal)
				temp.setDir(Direction2.Horizontal);
			else{
				temp.setDir(Direction2.Vertical);
			}
			w.add(temp);
		}
		return w;
	}
	
	/**
	 * 
	 * Get the white walls on board, and transfer it
	 * 
     * @author Yuelin Liu
	 * @param wallmove WallMove candidate to be check whether it is valid(to avoid overlapping).
	 * @return boolean whether there is a wall in the same position.
	 * @exception nothing
	 *
	 */
	public static ArrayList<TOWall> getBlackWallsOnBoard(){
		ArrayList<TOWall> w = new ArrayList<TOWall>();
		for(Wall wall : QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard()) {
			TOWall temp = null;
			temp.setRow(wall.getMove().getTargetTile().getRow());
			temp.setColumn(wall.getMove().getTargetTile().getColumn());
			if(wall.getMove().getWallDirection()==Direction.Horizontal)
				temp.setDir(Direction2.Horizontal);
			else{
				temp.setDir(Direction2.Vertical);
			}
			w.add(temp);
		}
		return w;
	}
	
	/**
	 * Feature:DropWall
	 * 
	 * I will check whether the WallMove candidate could be set at specified tile.
	 * 
     * @author Yuelin Liu
	 * @param wallmove WallMove candidate to be check whether it is valid(to avoid overlapping).
	 * @return boolean whether there is a wall in the same position.
	 * @exception nothing
	 *
	 */
	public static boolean verifyOverlapped(WallMove wallmove) {
		boolean result = false;
		int currentRow = wallmove.getTargetTile().getRow();
		int currentColumn = wallmove.getTargetTile().getColumn();
		QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard();
		QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard();
		int sizeB = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard().size();
		int sizeW = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard().size();
		if(wallmove.getWallDirection()==Direction.Vertical) {
			for(int i=0; i<sizeB; i++) {
				int newRow = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard().get(i).getMove().getTargetTile().getRow();
				int newColumn = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard().get(i).getMove().getTargetTile().getColumn();
				if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard().get(i).getMove().getWallDirection()==Direction.Vertical) {
					if((newColumn==currentColumn)&&(newRow<=currentRow+1)&&(newRow>=currentRow-1)) {
						result= true;
					}
				}
				if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard().get(i).getMove().getWallDirection()==Direction.Horizontal) {
					if((newColumn==currentColumn)&&(currentRow==newRow)) {
						result= true;
					}
				}	
			}
			for(int i=0; i<sizeW; i++) {
				int newRow = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard().get(i).getMove().getTargetTile().getRow();
				int newColumn = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard().get(i).getMove().getTargetTile().getColumn();
				if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard().get(i).getMove().getWallDirection()==Direction.Vertical) {
					if((newColumn==currentColumn)&&(newRow<=currentRow+1)&&(newRow>=currentRow-1)) {
						result= true;
					}
				}
				if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard().get(i).getMove().getWallDirection()==Direction.Horizontal) {
					if((newColumn==currentColumn)&&(currentRow==newRow)) {
						result= true;
					}
				}	
			}
		}
		
		if(wallmove.getWallDirection()==Direction.Horizontal) {
			for(int i=0; i<sizeB; i++) {
				int newRow = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard().get(i).getMove().getTargetTile().getRow();
				int newColumn = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard().get(i).getMove().getTargetTile().getColumn();
				if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard().get(i).getMove().getWallDirection()==Direction.Horizontal) {
					if((newRow==currentRow)&&(newColumn<=currentColumn+1)&&(newColumn>=currentColumn-1)) {
						result= true;
					}
				}
				if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard().get(i).getMove().getWallDirection()==Direction.Vertical) {
					if((newColumn==currentColumn)&&(currentRow==newRow)) {
						result= true;
					}
				}	
			}
			for(int i=0; i<sizeW; i++) {
				int newRow = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard().get(i).getMove().getTargetTile().getRow();
				int newColumn = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard().get(i).getMove().getTargetTile().getColumn();
				if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard().get(i).getMove().getWallDirection()==Direction.Horizontal) {
					if((newRow==currentRow)&&(newColumn<=currentColumn+1)&&(newColumn>=currentColumn-1)) {
						result= true;
					}
				}
				if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard().get(i).getMove().getWallDirection()==Direction.Vertical) {
					if((newColumn==currentColumn)&&(currentRow==newRow)) {
						result= true;
					}
				}	
			}
			
		}
		return result;
	}
	
	
	/**
	 * Feature:DropWall
	 * 
	 * 
	 * I will check whether the WallMove candidate could not be set at specified tile.
	 * 
     * @author Yuelin Liu
	 * @param wallmove WallMove candidate to be check whether it is valid to avoid exceeding the board.
	 * @return boolean whether the Wallmove candidate is outside the board
	 * @exception nothing
	 *
	 */
	public static boolean verifyOutsideTheBoard(WallMove wallmove) {
		
		int currentRow = wallmove.getTargetTile().getRow();
		int currentColumn = wallmove.getTargetTile().getColumn();
		if((currentColumn<1)) {
			return true;
		}
		if((currentColumn>8)) {
			return true;
		}
		if((currentRow<1)) {
			return true;
		}
		if((currentRow>8)) {
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * Feature:DropWall
	 * 
	 * An action to release the wall, to be more specific, set the wall candidate at certain tile.
	 * 
     * @author Yuelin Liu
	 * @param wallmove WallMove candidate to be released.
	 * @return void
	 * @exception nothing
	 *
	 */
	public static void releaseWall() 
	{	
		QuoridorApplication.setJboard(new JBoard());
		
		WallMove wallmove = QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate();
		
		if(QuoridorController.verifyOverlapped(wallmove)==true) 
		{
			
		QuoridorApplication.getJboard().notifyIllegal();
		return;
		}
		
		
		Tile t = wallmove.getTargetTile();
		Player currentPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
		Game currentGame = QuoridorApplication.getQuoridor().getCurrentGame();
		
//		JBoard newb = new JBoard();
//		QuoridorApplication.setJboard(newb);
	    
		if((QuoridorController.verifyOverlapped(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate()))
				||(QuoridorController.verifyOutsideTheBoard(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate())))
		{
						System.out.println("cannot drop!!!!!!!!!");
						JOptionPane.showMessageDialog(null, "cannot drop!!!!!!!!!");
						
				}

		if(currentPlayer.hasGameAsWhite()) {
			QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().addWhiteWallsOnBoard(wallmove.getWallPlaced());
			Player blackPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
			currentPlayer.setNextPlayer(blackPlayer);
			currentPlayer.setGameAsWhite(null);
			currentPlayer.setGameAsBlack(currentGame);
		}
		
		else{
			QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().addBlackWallsOnBoard(wallmove.getWallPlaced());
			Player whitePlayer = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
			currentPlayer.setNextPlayer(whitePlayer);
			currentPlayer.setGameAsBlack(null);
			currentPlayer.setGameAsWhite(currentGame);
			QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().setPlayerToMove(whitePlayer);
		}

		System.out.println("hasGameAsBlack(): "+QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove().hasGameAsBlack());

	}
	
	/**
	 * Feature:MoveWall
	 * 
     * @author Yuelin Liu
	 * @param wallmove WallMove candidate to be check whether its target tile is on the specific edge
	 * @return boolean whether its target tile is on the specific edge
	 * @exception nothing
	 *
	 */
	public static boolean verifyOnEdge(String string) {
		
		int currentRow = QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getRow();
		int currentColumn = QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getColumn();
		if(string.equalsIgnoreCase("left")) {
			if((currentColumn==1)) {
				return true;
			}
		}
		if(string.equalsIgnoreCase("right")) {
			if((currentColumn==8)) {
				return true;
			}
		}
		if(string.equalsIgnoreCase("up")) {
			if((currentRow==1)) {
				return true;
			}
		}
		if(string.equalsIgnoreCase("down")) {
			if((currentRow==8)) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Feature:MoveWall
	 * 
	 * An action to move the wall candidate from old position to new position.
	 * @author Yuelin Liu
	 * @param string String stands for the direction of the wall that is to be moved.
	 * @return void
	 * @exception nothing 
	 */
	public static void MoveWall(String string)
	{	
		if(QuoridorController.verifyOnEdge(string)==true) 
		{
			QuoridorApplication.setJboard(new JBoard());
		QuoridorApplication.getJboard().notifyIllegal();
		}
		
			if((string.equalsIgnoreCase("left"))&&(QuoridorController.verifyOnEdge(string)==false)) {
				Tile tileLeft = new Tile(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getRow()
						,QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getColumn()-1,QuoridorApplication.getQuoridor().getBoard());

				QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().setTargetTile(tileLeft);	
			}
			
			if((string.equalsIgnoreCase("right"))&&(QuoridorController.verifyOnEdge(string)==false)) {
				Tile tileRight = new Tile(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getRow()
						,QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getColumn()+1,QuoridorApplication.getQuoridor().getBoard());
					QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().setTargetTile(tileRight);
			}
		
			if((string.equalsIgnoreCase("up"))&&(QuoridorController.verifyOnEdge(string)==false)) {
				Tile tileUp = new Tile(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getRow()-1
						,QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getColumn(),QuoridorApplication.getQuoridor().getBoard());
	
				QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().setTargetTile(tileUp);
			}
		
			if((string.equalsIgnoreCase("down"))&&(QuoridorController.verifyOnEdge(string)==false)) {
				Tile tileDown = new Tile(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getRow()+1
						,QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getColumn(),QuoridorApplication.getQuoridor().getBoard());
				
				QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().setTargetTile(tileDown);
			}
		
			
			//JOptionPane.showMessageDialog(null, "It is illegal!!!");	
	}
	
	
	
	
	
	
	
	/**
	 * Feature: RotateWall
	 * 
	 * After this method, these two actions should be done
	 * The wall shall be rotated over the board to {string}
	 * A wall move candidate shall exist with {string} at position \\({int}, {int})
	 * 
	 * @param WallMove
	 * @author Yujing Yang
	 * @return boolean		return true if flipWall successfully; return false if don't
	 */

	public static boolean flipWall() {	
		//Player currentPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
		Game currentGame = QuoridorApplication.getQuoridor().getCurrentGame();
		Tile originalTail = currentGame.getWallMoveCandidate().getTargetTile();
		if (currentGame.getWallMoveCandidate().getWallDirection()==Direction.Horizontal) {
			QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().setWallDirection(Direction.Vertical);
			QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().setTargetTile(originalTail);
			return false;
		}
		if (currentGame.getWallMoveCandidate().getWallDirection()==Direction.Vertical) {
			QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().setWallDirection(Direction.Horizontal);
			QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().setTargetTile(originalTail);
			return true;
		}		
		return false;
	}

	/**
	 * Feature: GrabWall
	 * 
	 * After this method, these two actions should be done
	 * A wall move candidate shall be created at initial position
	 * I shall have a wall in my hand over the board
	 * The wall in my hand shall disappear from my stock
	 * 
	 * @param void 
	 * @author Yujing Yang
	 * @return boolean		return true if grabWall successfully; return false if don't
	 */
	
	public static boolean grabWall() {	
		
		Player currentPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
		
	
		try {
			if(currentPlayer.hasGameAsBlack()) {			
				List<Wall> inStock = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsInStock();		
				Wall grabbedWall = inStock.get(1);
	
				System.out.println("Black: "+inStock.size());
				
				Tile WallTile = new Tile(5, 5, QuoridorApplication.getQuoridor().getBoard());
				int a = QuoridorApplication.getQuoridor().getCurrentGame().getMoves().size();
				WallMove WallMove = new WallMove(a+1, (a + 3) / 2, currentPlayer, WallTile,QuoridorApplication.getQuoridor().getCurrentGame(), Direction.Vertical, grabbedWall);	
				QuoridorApplication.getQuoridor().getCurrentGame().setWallMoveCandidate(WallMove);
				QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().removeBlackWallsInStock(grabbedWall);
				System.out.println("Black after: "+inStock.size());
				return true;				
			}
		
			if(currentPlayer.hasGameAsWhite()) {
				List<Wall> inStock = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsInStock();
				Wall grabbedWall = inStock.get(1);
	
				System.out.println("White: "+inStock.size());
	
				Tile WallTile = new Tile(5, 5, QuoridorApplication.getQuoridor().getBoard());
				int a = QuoridorApplication.getQuoridor().getCurrentGame().getMoves().size();
				WallMove WallMove = new WallMove(a+1, (a + 3) / 2, currentPlayer, WallTile,QuoridorApplication.getQuoridor().getCurrentGame(), Direction.Vertical, grabbedWall);
				QuoridorApplication.getQuoridor().getCurrentGame().setWallMoveCandidate(WallMove);
				QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().removeWhiteWallsInStock(grabbedWall);
				System.out.println("White after: "+inStock.size());
				return true;
			}
		}
		catch(Exception e) {
			QuoridorApplication.getJboard().notifyIllegal2();
		}

		return false;

	}
	
	
	
	
	
	
	
	/**
	 * 
     * Feature:SavePosition
     *
	 * @author Bozhong Lu
	 * Method that checks file with specified name exists in my folder
	 * @param filename
	 * @return boolean: whether the file with the specified name exists
	 */
	public static boolean checkFileExistence(String filename) {
		File tempFile = new File(filename);
		boolean exists = tempFile.exists();
		return exists ;
	}
	
	
	
	
	// Helper method that writes pawn position and wall positions for Black player (For saveGame)
	private static void saveBlackPlayerPosition(String filename , BufferedWriter writer) throws IOException {
				
		Quoridor quoridor = QuoridorApplication.getQuoridor();
		String characters = new String("abcdefghi");
		ArrayList<Character> characterList = new ArrayList<Character>();
		for(int i = 0; i<characters.length(); i++){
			characterList.add(characters.charAt(i));
		}
				
		writer.write("B: ");
		String blackPositionToWrite = "" ;
		int playerColumn = quoridor.getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getColumn();
		int playerRow = quoridor.getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getRow();
		Character blackColumnToWrite = characterList.get(playerColumn-1);
		String blackRowToWrite = Integer.toString(playerRow) ;
		blackPositionToWrite = blackPositionToWrite + blackColumnToWrite + blackRowToWrite ;
		writer.write(blackPositionToWrite);
				
		List<Wall> blackWallsOnBoard = quoridor.getCurrentGame().getCurrentPosition().getBlackWallsOnBoard();
		for(Wall wall : blackWallsOnBoard) {
			String blackWallPositionToWrite = "," ;
			int wallColumn = wall.getMove().getTargetTile().getColumn();
			int wallRow = wall.getMove().getTargetTile().getRow();
			Direction wallDirection = wall.getMove().getWallDirection();
					
			Character wallColumnToWrite = characterList.get(wallColumn);
			String wallRowToWrite = Integer.toString(wallRow);
			String wallDirectionToWrite = "" ;
			if(wallDirection == Direction.Vertical) {
				wallDirectionToWrite = "v";
			}else if(wallDirection == Direction.Horizontal){
						wallDirectionToWrite = "h";
			}
			blackWallPositionToWrite = blackWallPositionToWrite + wallColumnToWrite + wallRowToWrite + wallDirectionToWrite ;
			writer.write(blackWallPositionToWrite);
		}
	}
	
	// Helper method that writes pawn position and wall positions for White player(For saveGame)
	private static void saveWhitePlayerPosition(String filename , BufferedWriter writer) throws IOException {
		
		Quoridor quoridor = QuoridorApplication.getQuoridor();
		String characters = new String("abcdefghi");
		ArrayList<Character> characterList = new ArrayList<Character>();
		for(int i = 0; i<characters.length(); i++){
			characterList.add(characters.charAt(i));
		}
		writer.write("W: ");
		String whitePositionToWrite = "" ;
		int playerColumn = quoridor.getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getColumn();
		int playerRow = quoridor.getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getRow();
		Character whiteColumnToWrite = characterList.get(playerColumn-1);
		String whiteRowToWrite = Integer.toString(playerRow) ;
		whitePositionToWrite = whitePositionToWrite + whiteColumnToWrite + whiteRowToWrite ;
		writer.write(whitePositionToWrite);
		
		List<Wall> whiteWallsOnBoard = quoridor.getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard();
		for(Wall wall : whiteWallsOnBoard) {
			String whiteWallPositionToWrite = "," ;
			int wallColumn = wall.getMove().getTargetTile().getColumn();
			int wallRow = wall.getMove().getTargetTile().getRow();
			Direction wallDirection = wall.getMove().getWallDirection();
			
			Character wallColumnToWrite = characterList.get(wallColumn);
			String wallRowToWrite = Integer.toString(wallRow);
			String wallDirectionToWrite = "" ;
			if(wallDirection == Direction.Vertical) {
				wallDirectionToWrite = "v";
			}else if(wallDirection == Direction.Horizontal){
				wallDirectionToWrite = "h";
			}
			whiteWallPositionToWrite = whiteWallPositionToWrite + wallColumnToWrite + wallRowToWrite + wallDirectionToWrite ;
			writer.write(whiteWallPositionToWrite);
			
		}
	}
	
	
	/**
	 * Feature:SavePosition
	 * 
	 * Method that writes the current Player positions and Wall Positions in a specific file
	 * The player to move is written at the first line, and the next player to move is written on the second line
	 * 
	 * @author Bozhong Lu
	 * @param String filename
	 * @return nothing
	 * @throws IOException 
	 */
	
	public static void saveGame(String filename)throws IOException{
		
		Quoridor quoridor = QuoridorApplication.getQuoridor();
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		
		//check the current player of the game, write the player in the front line
		if(quoridor.getCurrentGame().getCurrentPosition().getPlayerToMove().hasGameAsBlack()) {
			
			saveBlackPlayerPosition(filename , writer);
			
			writer.newLine();
			
			saveWhitePlayerPosition(filename , writer);
			
		}else if(quoridor.getCurrentGame().getCurrentPosition().getPlayerToMove().hasGameAsWhite()) {
			
			saveWhitePlayerPosition(filename , writer);
			
			writer.newLine();
			
			saveBlackPlayerPosition(filename , writer);
			
		}
		
		writer.close();
		
	}
	
	
	//private boolean variable which indicates 
	//whether the player agrees to overwrite existing file or cancels overwrite existing file
	private static boolean overwriteBoolean = false;
	
	// public getter method for the private overwriteBoolean variable
	public static boolean getOverwriteBoolean() {
		return overwriteBoolean ;
	}
	
	/**
	 * Feature:SavePosition
	 * 
	 * Method that is connected to the User Interface
	 * If the user click the "Yes" Button, this method will return a boolean "true"
	 * which indicates the user agrees to overwrite existing file
	 * 
	 * @author Bozhong Lu
	 * @param none
	 * @return boolean that indicates the user confirms to overwrite existing File
	 */
	//GUI
	public static void overwriteExistingFile() {
		overwriteBoolean = true ;
	}
	
	/**
	 * Feature:SavePosition
	 * 
	 * Method that is connected to the User Interface
	 * If the user click the "No" Button, this method will return a boolean "false"
	 * which indicates the user cancels to overwrite existing file
	 * 
	 * @author Bozhong Lu
	 * @param none
	 * @return boolean that indicates the user cancels to overwrite existing File
	 */
	//GUI
	public static void cancelOverwriteExistingFile() {
		overwriteBoolean = false ;
	}
	
	
	
	/**
	 * Feature:SavePosition
	 * 
	 * Method that check the last modified time of the specified file 
	 * If the file was updated within the past 30s, it means that it was succesfully updated
	 * Otherwise, it was not successfully updated 
	 * 
	 * @author Bozhong Lu
	 * @param filename
	 * @return boolean which indicates whether the specified file has been updated or not
	 * @throws IOException 
	 */
	public static boolean fileIsUpdated(String filename) throws IOException {
		boolean isUpdated = false ;
		File gameFile = new File(filename); 
		//long lastChangedTime = gameFile.lastModified();
		long currentTime = System.currentTimeMillis();
		Path gameFilePath = gameFile.toPath();
	    BasicFileAttributes basicAttribs = Files.readAttributes(gameFilePath, BasicFileAttributes.class);
		
		if(((currentTime - basicAttribs.lastModifiedTime().to(TimeUnit.MILLISECONDS))/1000) <= 30) {
			isUpdated = true ;
		}
		
		return isUpdated ;
	}
	
	/**
	 * Feature:SavePosition
	 * 
	 * Method that creats a new file with name filename
	 * 
	 * @author Bozhong Lu
	 * @param String filename
	 * @return none
	 * @throws IOException 
	 */
	public static void creatNewFile(String filename) throws IOException {
		File gameFile = new File(filename);
        if(gameFile.createNewFile()){
            System.out.println(filename + " File Created in Project root directory");
        }else System.out.println(filename + " already exists in the project root directory");
	}
	
	/**
	 * Feature:SavePosition
	 * 
	 * Method that deletes file filename from file system
	 * 
	 * @author Bozhong Lu
	 * @param String filename
	 * @return none
	 * @throws IOException 
	 */
	public static void deleteFile(String filename) throws IOException {
		File gameFile = new File(filename);
		String filePath = gameFile.getCanonicalPath();
		File gameFilePath = new File(filePath);
		gameFilePath.delete();
	}
	
	
	/**
	 * Feature: ValidatePosition
	 * This method validate if all the pawn and wall position at board 
	 * is within the board boundary 
	 * 
	 * @author Bozhong Lu
	 * @return boolean
	 */
	public static boolean validatePosition() {
		//TO-DO: Write logic to validate position
		boolean wallIsValid = true;
		boolean pawnIsValid = true;
		Quoridor quoridor = QuoridorApplication.getQuoridor();
		//Validate WallMove 
		if(quoridor.getCurrentGame().getMoveMode() == MoveMode.WallMove) {
			//check overlapping of current wall with all other walls on board
			WallMove currentWallMove = quoridor.getCurrentGame().getWallMoveCandidate();
			int row = currentWallMove.getTargetTile().getRow();
			int column = currentWallMove.getTargetTile().getColumn();
			Direction currentWallDirection = currentWallMove.getWallDirection();
			List<Wall> blackWalls = quoridor.getCurrentGame().getCurrentPosition().getBlackWallsOnBoard();
			List<Wall> whiteWalls = quoridor.getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard();
			for(Wall wall : blackWalls) {
				int thisWallColumn = wall.getMove().getTargetTile().getColumn();
				int thisWallRow = wall.getMove().getTargetTile().getRow();
				if(wall.getMove().getWallDirection() == Direction.Vertical ) {
					if(currentWallDirection == Direction.Vertical) {
						if((column == thisWallColumn)&&((row == thisWallRow+1)||(row == thisWallRow-1)||(row == thisWallRow))) {
							wallIsValid = false;
						}
					}else if (currentWallDirection == Direction.Horizontal) {
						if((column == thisWallColumn)&&(row == thisWallRow)) {
							wallIsValid = false;
						}
					}
				}else if(wall.getMove().getWallDirection() == Direction.Horizontal) {
					if(currentWallDirection == Direction.Vertical) {
						if((column == thisWallColumn)&&(row == thisWallRow)) {
							wallIsValid = false;
						}
					}else if (currentWallDirection == Direction.Horizontal) {
						if ((row == thisWallRow)&&((column == thisWallColumn-1)||(column == thisWallColumn+1)||(column == thisWallColumn))) {
							wallIsValid = false;
						}
					}
				}
			}
			for(Wall wall : whiteWalls) {
				int thisWallColumn = wall.getMove().getTargetTile().getColumn();
				int thisWallRow = wall.getMove().getTargetTile().getRow();
				if(wall.getMove().getWallDirection() == Direction.Vertical ) {
					if(currentWallDirection == Direction.Vertical) {
						if((column == thisWallColumn)&&((row == thisWallRow+1)||(row == thisWallRow-1)||(row == thisWallRow))) {
							wallIsValid = false;
						}
					}else if (currentWallDirection == Direction.Horizontal) {
						if((column == thisWallColumn)&&(row == thisWallRow)) {
							wallIsValid = false;
						}
					}
				}else if(wall.getMove().getWallDirection() == Direction.Horizontal) {
					if(currentWallDirection == Direction.Vertical) {
						if((column == thisWallColumn)&&(row == thisWallRow)) {
							wallIsValid = false;
						}
					}else if (currentWallDirection == Direction.Horizontal) {
						if ((row == thisWallRow)&&((column == thisWallColumn-1)||(column == thisWallColumn+1)||(column == thisWallColumn))) {
							wallIsValid = false;
						}
					}
				}
			}
			//Check if the wall is within the boundary
			int currentWallColumn = quoridor.getCurrentGame().getWallMoveCandidate().getTargetTile().getColumn();
			int currentWallRow = quoridor.getCurrentGame().getWallMoveCandidate().getTargetTile().getRow();

			if((currentWallColumn<1)) {
				wallIsValid = false;
			}
			if((currentWallColumn>8)) {
				wallIsValid = false;
			}
			if((currentWallRow<1)) {
				wallIsValid = false;
			}
			if((currentWallRow>8)) {
				wallIsValid = false;
			}
		//Next Task : Check if the pawn is surrounded by walls after this WallMove. If it is surrounded, then WallMove invalid
		//Validate PawnMove	
		}else if(quoridor.getCurrentGame().getMoveMode() == MoveMode.PlayerMove) {
			
			int blackPlayerColumn = quoridor.getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getColumn();
			int blackPlayerRow = quoridor.getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getRow();
			int whitePlayerColumn = quoridor.getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getColumn();
			int whitePlayerRow = quoridor.getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getRow();
			//check whether the position is already occupied by another player
			if((blackPlayerColumn == whitePlayerColumn) && (blackPlayerRow == whitePlayerRow)) {
				pawnIsValid = false ;
			}
			//check whether the position is within the boundary of the board
			if((blackPlayerColumn<1)||(blackPlayerColumn>9)) {
				pawnIsValid = false;
			}
			if((blackPlayerRow<1)||(blackPlayerRow>9)) {
				pawnIsValid = false;
			}
			if((whitePlayerRow<1)||(whitePlayerRow>9)) {
				pawnIsValid = false;
			}
			if((whitePlayerColumn<1)||(whitePlayerColumn>9)) {
				pawnIsValid = false;
			}
			
		} 
		
		if(quoridor.getCurrentGame().getMoveMode() == MoveMode.WallMove) {
			return wallIsValid;
		}else{
			return pawnIsValid;
		}
	}
		
	
	
	
	
	
	/**
	 * Feature:load position
	 * 
	 * This method load a game position by input a filename
	 * 
	 * @author Zirui He
	 * @param filename
	 * @param white
	 * @param black
	 * @return
	 * @throws Exception 
	 */
	public static boolean loadPosition(String filename, Player white, Player black) throws Exception{
		
		Quoridor quoridor = QuoridorApplication.getQuoridor();
		
		//use scanner read the file
		FileInputStream inputstream = new FileInputStream(filename);
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(inputstream);
		String line1 = scanner.nextLine();
		String line2 = scanner.nextLine();
		String currentline = null;
		int blacksteps = 0;
		int whitesteps = 0;
		
		//variables needed to set gamePosition
		Player playerToMove = null;
		PlayerPosition blackposition = null;
		PlayerPosition whiteposition = null;
		List<Wall> whiteWalls = new ArrayList<Wall>();
		List<Wall> blackWalls = new ArrayList<Wall>();

		for (int i = 1; i < 3; i++) {	//i=1 means line1, i=2 means line2
			if (i == 1) {
				currentline = line1;
			} else {
				currentline = line2;
			}
			String delims = "[ :,]+";	
			String[] split = currentline.split(delims);	//parse the currentline into smaller strings
			int length = split.length;
			
			if (split[0].equals("B")) {		//if this line store black player's data
				blacksteps = length;
				int blackWallIndex = 0;
				for (int j = 1; j < split.length; j++) {	//start from the second argument in the string and loop to the end
					int moveNumber = i + (j - 1) * 2;
					int roundNumber = j;
					Tile tile = null;
					String[] s = split[j].split("");	//split string by each character
					try {
						tile = quoridor.getBoard().getTile((Integer.parseInt(s[1]) - 1) * 9 + columnNum(s[0]) - 1);
					} catch(Exception e) {
						throw(new Exception("Out of boundary!", e));
					}
					if (s.length == 2) {	//check if is pawn move
						blackposition = new PlayerPosition(black, tile);
					}
					if (s.length == 3) { 	//check if is wall move
						Direction direction;
						switch (s[2]) {
						case "h":
							direction = Direction.Horizontal;
							break;
						case "v":
							direction = Direction.Vertical;
							break;
						default:
							throw new IllegalArgumentException("Unsupported wall direction was provided");
						}
						Wall wall = black.getWall(blackWallIndex);
						blackWallIndex++;
						new WallMove(moveNumber, roundNumber, black, tile, quoridor.getCurrentGame(), direction, wall); 	//put wall on the board
						blackWalls.add(wall);			
					}
							
				}
				
			}
			
			if (split[0].equals("W")) {		//check if is white player
				whitesteps = length;
				int whiteWallIndex = 0;
				for (int j = 1; j < split.length; j++) {
					int moveNumber = i + (j - 1) * 2;
					int roundNumber = j;
					Tile tile = null;
					String[] s = split[j].split("");
					try {
						tile = quoridor.getBoard().getTile((Integer.parseInt(s[1]) - 1) * 9 + columnNum(s[0]) - 1);
					} catch(Exception e) {
						throw(new Exception("Out of boundary!"));

					}
					
					if (s.length == 2) {
						whiteposition = new PlayerPosition(white, tile);
					}
					if (s.length == 3) {
						Direction direction;
						switch (s[2]) {
						case "h":
							direction = Direction.Horizontal;
							break;
						case "v":
							direction = Direction.Vertical;
							break;
						default:
							throw new IllegalArgumentException("Unsupported wall direction was provided");
						}
						Wall wall = white.getWall(whiteWallIndex);
						whiteWallIndex++;
						new WallMove(moveNumber, roundNumber, white, tile, quoridor.getCurrentGame(), direction, wall);
						whiteWalls.add(wall);

					}
							
				}
				
			}
		}
		
		//check which player to move
		if (line1.charAt(0) == 'B') {
			if (blacksteps > whitesteps) {
				playerToMove = white;
			} else {
				playerToMove = black;
			}
		}
		
		if (line1.charAt(0) == 'W') {
			if (whitesteps > blacksteps) {
				playerToMove = black;
			} else {
				playerToMove = white;
			}
		}
		
		//create game that is initializing
		Game game = new Game(GameStatus.Initializing, MoveMode.PlayerMove, quoridor);
		game.setWhitePlayer(white);
		game.setBlackPlayer(black);
		
		//create current gamePosition
		GamePosition gamePosition = new GamePosition(0, blackposition, whiteposition, playerToMove, game);
		gamePosition.setBlackPosition(blackposition);
		gamePosition.setWhitePosition(whiteposition);
		game.setCurrentPosition(gamePosition);
		for (Wall wall : blackWalls) {
			game.getCurrentPosition().addBlackWallsOnBoard(wall);
		}
		for (Wall wall : whiteWalls) {
			game.getCurrentPosition().addWhiteWallsOnBoard(wall);
		}
		
		//add walls into stock, excepting the wall that's being add on board
		for (int j = whiteWalls.size(); j < 10; j++) {		
			Wall wall = Wall.getWithId(j);
			gamePosition.addWhiteWallsInStock(wall);
		}
		for (int j = blackWalls.size(); j < 10; j++) {
			Wall wall = Wall.getWithId(j + 10);
			gamePosition.addBlackWallsInStock(wall);
		}
						
		return true;

	}
	
	
	/**
	 * Feature:load Position
	 * This method validate if all the pawn and wall position at board 
	 * are not overlapping 
	 * 
	 * @author Zirui He
	 * @return boolean
	 * @throws Exception 
	 */
	public static boolean validation() throws Exception {
		boolean isValid = true;
			Quoridor quoridor = QuoridorApplication.getQuoridor();
			//Validate WallMove 
			List<Wall> blackWalls = quoridor.getCurrentGame().getCurrentPosition().getBlackWallsOnBoard();
			List<Wall> whiteWalls = quoridor.getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard();
			List<Wall> wallList = new ArrayList<Wall>();
			wallList.addAll(blackWalls);
			wallList.addAll(whiteWalls);

			for (int i = 0; i < wallList.size() - 1; i++) {
				int thisWallColumn = wallList.get(i).getMove().getTargetTile().getColumn();
				int thisWallRow = wallList.get(i).getMove().getTargetTile().getRow();
				Direction thisWallDirection = wallList.get(i).getMove().getWallDirection();

				for (int j = i + 1; j < wallList.size(); j++) {
					int nextWallColumn = wallList.get(j).getMove().getTargetTile().getColumn();
					int nextWallRow = wallList.get(j).getMove().getTargetTile().getRow();
					Direction nextWallDirection = wallList.get(j).getMove().getWallDirection();

					if (thisWallDirection == Direction.Vertical) {
						if(nextWallDirection == Direction.Vertical) {
							if((nextWallColumn == thisWallColumn)&&((nextWallRow == thisWallRow+1)||(nextWallRow == thisWallRow-1)||(nextWallRow == thisWallRow))) {
								throw(new Exception("Wall Overlapping!"));
							}
						}else {
							if((nextWallColumn == thisWallColumn)&&(nextWallRow == thisWallRow)) {
								throw(new Exception("Wall Overlapping!"));
							}
						}
					}
					else {
						if(nextWallDirection == Direction.Vertical) {
							if((thisWallColumn == nextWallColumn)&&(thisWallRow == nextWallRow)) {
								throw(new Exception("Wall Overlapping!"));
							}
						}else {
							if ((thisWallRow == nextWallRow)&&((thisWallColumn == nextWallColumn-1)||(thisWallColumn == nextWallColumn+1)||(thisWallColumn == nextWallColumn))) {
								throw(new Exception("Wall Overlapping!"));
							}
						}
					}
				}
			}

			//validate pawn position
			Tile black = quoridor.getCurrentGame().getCurrentPosition().getBlackPosition().getTile();
			Tile white = quoridor.getCurrentGame().getCurrentPosition().getWhitePosition().getTile();
			int bColumn = black.getColumn();
			int bRow = black.getRow();
			int wColumn = white.getColumn();
			int wRow = white.getRow();
			if ((bColumn == wColumn) && (bRow == wRow)) {
				throw(new Exception("Invalid Pawn!"));
			}
			return isValid;

	}


	
	
	/**
	 * Feature:switch player
	 * This method let player to make a move in the game
	 * 
	 * @author Zirui He
	 * @param player
	 */
	public static void completeMove(Player player) {

		Player white = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
		Player black = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
		String message;
		if (player.hasGameAsBlack()) {
			player.setNextPlayer(white);
			QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().setPlayerToMove(white);
			message = "It is white player's turn";
			QuoridorApplication.setJboard(new JBoard());
			QuoridorApplication.getJboard().whiteTurn();
		}else {
			player.setNextPlayer(black);
			QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().setPlayerToMove(black);
			message = "It is black player's turn";
			QuoridorApplication.setJboard(new JBoard());
			QuoridorApplication.getJboard().blackTurn();
		}
		
	}

	
	/**
	 * Feature: switch player 
	 * This method start a clock to count the time passed for the player
	 * 
	 * @author Zirui He
	 * @param player
	 */
	public static Long startClock() {
		Long startTime = System.nanoTime();
		return startTime;
	}
	
	/**
	 * Feature: switch player
	 * This method stop a clock the player
	 * 
	 * @author Zirui He
	 * @param player
	 * @return void
	 */
  
	public static Long stopClock() {
		Long endTime = System.nanoTime();
		return endTime;
	}
	



	/**
	 * Feature: SetTotalThinkingTime
	 * This static method sets a player's total thinking time by taking two inputs
	 * in the form (min:sec).
	 * It returns true if setting is successful.
	 * 
	 * @author Sun, Gengyi
	 * @param min minutes
	 * @param sec seconds
	 * @return A flag indicating whether the method successfully launched.
	 */

	public static void setTotalThinkingTime(Integer min, Integer sec) {
		if(min != null && sec != null) {
		Player whitePlayer = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
		Player blackPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
		long millis = (min*60+sec) * 1000;
		Time totalTime = new Time(millis);
		whitePlayer.setRemainingTime(totalTime);
		blackPlayer.setRemainingTime(totalTime);
		}
		

	}
	
	/**
	 * Feature: InitialzeBoard
	 * This method initialize a new board with 9*9 tiles. Then it 
	 * initializes pawns and walls for the players. Finally it sets
	 * the same thinking time for both players. The white player will
	 * perform a movement first.
	 * It returns true if the initiating is successful.
	 * 
	 * @author Sun, Gengyi
	 * @return A flag indicating whether the method successfully launched.
	 */
	public static void initializeBoard() {
//		QuoridorApplication.setJBoard(new JBoard());
		Board board = new Board(QuoridorApplication.getQuoridor());
		for(int i = 1; i<= 9; i++) {
			for(int j = 1; j<=9; j++) {
				Tile tile = new Tile(i, j, board);
				board.addTile(tile);
			}
		}
		Game game = QuoridorApplication.getQuoridor().getCurrentGame();
		QuoridorApplication.getQuoridor().setBoard(board);
		Player whitePlayer = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
		Player blackPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
		Tile initWhite = new Tile(5, 9, board);
		Tile initBlack = new Tile(5, 1, board);
		PlayerPosition initialWhite = new PlayerPosition(whitePlayer, initWhite);
		PlayerPosition initialBlack = new PlayerPosition(whitePlayer, initBlack);
		
		GamePosition g = new GamePosition(0, initialWhite, initialBlack, whitePlayer, game);
		game.setCurrentPosition(g);
		initializeWhiteWall(g,whitePlayer);
		initializeBlackWall(g,blackPlayer);
		g.setPlayerToMove(whitePlayer);
		//QuoridorApplication.getJboard().mainLayerPanel.setVisible(true);
//		QuoridorApplication.getJboard().whitePawnMove.setVisible();
//		QuoridorApplication.getJboard().whitePawnMove.setVisible(true);
	}

	/**
	 * Feature: InitialzeBoard
	 * This static method initializes initial number of walls left for the black player, 
	 * contains all of them in a list.
	 * It returns true if the initiating is successful.
	 * 
	 * @author Sun, Gengyi
	 * @param blackPlayer
	 * @return A flag indicating whether the method successfully launched.
	 */
	public static List<Wall> initializeBlackWall(GamePosition g,Player blackPlayer) {
		//TODO GUI
		List<Wall> whiteWallsInStock = new ArrayList<Wall>();
		int blackIndex = 0;
		if(!blackPlayer.hasWalls()) {
			for(int i = 0; i<10;i++) {
				Wall wall = new Wall(blackIndex+i, blackPlayer);
				g.addBlackWallsInStock(wall);
			}
		}
		return whiteWallsInStock;

	}
	/**
	 * Feature: InitialzeBoard
	 * This static method initializes initial number of walls left for the white player,
	 * contains all of them in a list.
	 * It returns true if the initiating is successful.
	 * 
	 * @author Sun, Gengyi
	 * @param whitePlayer
	 * @return A flag indicating whether the method successfully launched.
	 */
	public static void initializeWhiteWall(GamePosition g,Player whitePlayer) {
		//TODO GUIint blackIndex = 0;
		int whiteIndex = 10;
		List<Wall> blackWallsInStock= new ArrayList<Wall>();
		if(!whitePlayer.hasWalls()) {
			for(int i = 0; i<10;i++) {
				Wall wall = new Wall(whiteIndex+i, whitePlayer);		
				g.addWhiteWallsInStock(wall);
				}
		}

	}
	
	/**
	 * This method verify if the total thinking time of a give game is correctly 
	 * set. It will return a boolean variable to suggest if it is valid. 
	 * 
	 * @author Sun, Gengyi
	 * @return A flag indicating whether the thinking time is valid.
	 */
	public static boolean verifyTotalThinkingTime() {
		Game g = QuoridorApplication.getQuoridor().getCurrentGame();
		if (g == null) {
			throw new IllegalArgumentException("No game avaliable");
		}
		throw new UnsupportedOperationException();
	}
	
	/**
	 * This is a static method which initializes a new game. 
	 * It will return a boolean value to indicate if a new game is successfully initialized.
	 * 
	 * @author Pengnan Fan
	 * @return A boolean value to indicate if a new game is initialized successfully
	 * 
	 */
	public static boolean initializeNewGame() {
		Game g =new Game(GameStatus.Initializing, MoveMode.WallMove, QuoridorApplication.getQuoridor());
		return QuoridorApplication.getQuoridor().hasCurrentGame();
	}

	/**
	 * This is a static method which takes a player and a new name, then set the latter as 
	 * the name of the former. It will return a boolean value to indicate if the 
	 * name is updated successfully.
	 * 
	 * @author Pengnan Fan
	 * @param game The game of the specific white player
	 * @param name The new name 
	 * @return A boolean value to indicate if the name of the user has been updated
	 */
	public static boolean setUserName(String player, String name, boolean create) {
		if (!player.equals("black") && !player.equals("white")) {
			throw new IllegalArgumentException("Player is invalid");
		}
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Invalid name");
		}
		User u = User.getWithName(name);
		if (u == null && !create) {
			throw new IllegalArgumentException("User: " + name + " does NOT exist");
		} else if(u == null && create) {
			u = new User(name, QuoridorApplication.getQuoridor());
		}
		Player p;
		if (player.equals("black")) {
			p = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
			if (p==null) {
				p = new Player(new Time(0), u, 9, Direction.Horizontal);
			} else {
				p.setUser(u);
			}
			return QuoridorApplication.getQuoridor().getCurrentGame().setBlackPlayer(p);
		} else if (player.equals("white")) {
			p = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
			if (p==null) {
				p = new Player(new Time(0), u, 1, Direction.Horizontal);
			} else {
				p.setUser(u);
			}
			return QuoridorApplication.getQuoridor().getCurrentGame().setWhitePlayer(p);
		} else {
			throw new RuntimeException();
		}
	}
	 /**
	  * This is a method that tells if the game is ready to start.
	  * If so, the GameStatus will be changed to ready to start
	  * and return true
	  * 
	  * @author Pengnan Fan
	  * @return A flag indicating if the game is read to start
	  */
	 public static boolean verifyGameIsReady() {
		 Game g = QuoridorApplication.getQuoridor().getCurrentGame();
		 if (g == null) {
			 System.out.println("no game");
			 return false;
		 }
		 if (!g.hasBlackPlayer()) {
			 System.out.println("no black");
			 return false;
		 }
		 if(!g.hasWhitePlayer()) {
			 System.out.println("no white");
			 return false;
		 }
		 if(!g.getMoveMode().equals(MoveMode.WallMove)) {
			 System.out.println("wrong move mode");
			return false;
		 }
		 if(g.hasMoves()) {
			 System.out.println("has move");
			 return false;
		 }
//		 if(g.hasPositions()) {
//			 System.out.println("has position");
//			 return false;
//		 }
//		 if(g.hasCurrentPosition()) {
//			 System.out.println("has current pos");
//			 return false;
//		 }
//		 if(!QuoridorApplication.getQuoridor().hasBoard()) {
//			 System.out.println("no board");
//			 return false;
//		 }
		 
		 
		 
		 g.setGameStatus(GameStatus.ReadyToStart);
		 return true;
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 /**
	  * This method checks the clock of the current player to count down.
	  * It returns true if the clock is counting down.
	  * 
	  * @author Non
	  * @param String string,Tile tile
	  * @return boolean
	  */ 
	 public static boolean verifyPawnPosition(String string,Tile tile) {
		 throw new UnsupportedOperationException();
	 }
	 
	 /**
	  * This method checks the clock of the current player to count down.
	  * It returns true if the clock is counting down.
	  * 
	  * @author Non
	  * @param String string,String string2
	  * @return boolean
	  */ 
	 public static boolean verifythere_are_no_walls_from_the_player(String string,String string2) {
		 throw new UnsupportedOperationException();
	 }
	
	 
	 /**
	  * This method checks the clock of the current player to count down.
	  * It returns true if the clock is counting down.
	  * 
	  * @author Non
	  * @param String string
	  * @return boolean
	  */ 
	 public static boolean verifytthe_opponent_is_not_from_the_player(String string) {
		 throw new UnsupportedOperationException();
	 }
	 
	 
	 /**
	  * This method checks the clock of the current player to count down.
	  * It returns true if the clock is counting down.
	  * 
	  * @author Non
	  * @param String string, String string2
	  * @return boolean
	  */ 
	 public static boolean movePlayer(String string, String string2) {
		 throw new UnsupportedOperationException();
	 }
	 
	 //helper method
	 
	 private static int columnNum(String x) {
		 int result = 0;
		 if (x.equals("a"))  result = 1;
		 if (x.equals("b"))  result = 2;
		 if (x.equals("c"))  result = 3;
		 if (x.equals("d"))  result = 4;
		 if (x.equals("e"))  result = 5;
		 if (x.equals("f"))  result = 6;
		 if (x.equals("g"))  result = 7;
		 if (x.equals("h"))  result = 8;
		 if (x.equals("i"))  result = 9;
		 return result;
	 }
	 
	 public static ArrayList<Player> createUsersAndPlayers(String userName1, String userName2) {
			Quoridor quoridor = QuoridorApplication.getQuoridor();
//			User user1 = quoridor.addUser(userName1);
//			User user2 = quoridor.addUser(userName2);
			User user1 = null;
			User user2 = null;
			
			for(User user: quoridor.getUsers()) {
				
				if(userName1.equals(user.getName())) {
					user1 = user;
					break;
				}
			}
			
			for(User user: quoridor.getUsers()) {
				
				if(userName2.equals(user.getName())) {
					user2 = user;
					break;
				}
			}
			
			


			int thinkingTime = 180;

			// Players are assumed to start on opposite sides and need to make progress
			// horizontally to get to the other side
			//@formatter:off
			/*
			 *  __________
			 * |          |
			 * |          |
			 * |x->    <-x|
			 * |          |
			 * |__________|
			 * 
			 */
			//@formatter:on
			Player player1 = new Player(new Time(thinkingTime), user1, 9, Direction.Horizontal);
			Player player2 = new Player(new Time(thinkingTime), user2, 1, Direction.Horizontal);

			Player[] players = { player1, player2 };

			// Create all walls. Walls with lower ID belong to player1,
			// while the second half belongs to player 2
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 10; j++) {
					new Wall(i * 10 + j, players[i]);
				}
			}
			
			ArrayList<Player> playersList = new ArrayList<Player>();
			playersList.add(player1);
			playersList.add(player2);
			
			return playersList;
		}

		public static void createAndStartGame(ArrayList<Player> players) {
			Quoridor quoridor = QuoridorApplication.getQuoridor();
			// There are total 36 tiles in the first four rows and
			// indexing starts from 0 -> tiles with indices 36 and 36+8=44 are the starting
			// positions
			Tile player1StartPos = quoridor.getBoard().getTile(36);
			Tile player2StartPos = quoridor.getBoard().getTile(44);
			
			Game game = new Game(GameStatus.Running, MoveMode.PlayerMove, quoridor);
			game.setWhitePlayer(players.get(0));
			game.setBlackPlayer(players.get(1));
			PlayerPosition player1Position = new PlayerPosition(quoridor.getCurrentGame().getWhitePlayer(), player1StartPos);
			PlayerPosition player2Position = new PlayerPosition(quoridor.getCurrentGame().getBlackPlayer(), player2StartPos);

			GamePosition gamePosition = new GamePosition(0, player1Position, player2Position, players.get(0), game);

			// Add the walls as in stock for the players
			for (int j = 0; j < 10; j++) {
				Wall wall = Wall.getWithId(j);
				gamePosition.addWhiteWallsInStock(wall);
			}
			for (int j = 0; j < 10; j++) {
				Wall wall = Wall.getWithId(j + 10);
				gamePosition.addBlackWallsInStock(wall);
			}

			game.setCurrentPosition(gamePosition);
		}
		
		public static void initQuoridorAndBoard() {
			Quoridor quoridor = QuoridorApplication.getQuoridor();
			Board board = new Board(quoridor);
			// Creating tiles by rows, i.e., the column index changes with every tile
			// creation
			for (int i = 1; i <= 9; i++) { // rows
				for (int j = 1; j <= 9; j++) { // columns
					board.addTile(i, j);
				}
			}
		}
	 
	 
}
