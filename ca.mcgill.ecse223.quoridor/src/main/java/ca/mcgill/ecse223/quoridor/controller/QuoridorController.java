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
import ca.mcgill.ecse223.quoridor.model.*;
import ca.mcgill.ecse223.quoridor.model.Game.GameStatus;
import ca.mcgill.ecse223.quoridor.model.Game.MoveMode;
import ca.mcgill.ecse223.quoridor.view.JWall;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.*;
import org.jgrapht.util.SupplierUtil;

public class QuoridorController {
	public static List<Move> moveList;
	/**
	 * This method establish a thread that reset board data every second.
	 * @author Sun, Gengyi
	 */
	public static void refreshData() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RefreshData refresh = new RefreshData(QuoridorApplication.getJboard());
		refresh.start();

	}

	/**
	 * Get game result.
	 * @author Sun, Gengyi
	 * @return A string indicating game status
	 */
	public static String getGameResult() {
		if(QuoridorApplication.getQuoridor().getCurrentGame().getGameStatus().equals(GameStatus.WhiteWon)) return "WhiteWon!";
		else if(QuoridorApplication.getQuoridor().getCurrentGame().getGameStatus().equals(GameStatus.BlackWon)) return "BlackWon!";
		else return "Peace and Love(Draw)";
	}
	/**
	 * This method helps communicating through model and UI.
	 * @return A string of white user's time
	 * @author Sun, Gengyi
	 */
	public static String getWhiteName() {
		return QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer().getUser().getName();
	}
	/**
	 * This method helps communicating through model and UI.
	 * @author Sun, Gengyi
	 * @return A string of black user's time
	 */
	public static String getBlackName() {
		return QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer().getUser().getName();
	}
	/**
	 * This method helps communicating through model and UI.
	 * @return A string of white remaining time
	 * @author Sun, Gengyi
	 */
	public static String getWhiteRemainingTime() {
		int min = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer().getRemainingTime().getMinutes();
		int sec = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer().getRemainingTime().getSeconds();
		return min+":"+sec;
	}
	/**
	 * This method helps communicating through model and UI.
	 * @return A string of black remaining time
	 * @author Sun, Gengyi
	 */
	public static String getBlackRemainingTime() {
		int min = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer().getRemainingTime().getMinutes();
		int sec = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer().getRemainingTime().getSeconds();
		return min+":"+sec;
	}
	/**
	 * This method helps communicating through model and UI.
	 * @return A string of white remaining wall
	 * @author Sun, Gengyi
	 */
	public static String getWhiteStocks() {
		int num = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer().getWalls().size();
		return num+"";
	}
	/**
	 * This method helps communicating through model and UI.
	 * @return A string of black remaining wall
	 * @author Sun, Gengyi
	 */
	public static String getBlackStocks() {
		int num = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer().getWalls().size();
		return num+"";
	}

	/**
	 * Refresh and set the environment up.
	 * 
	 * @return 
	 * @return void
	 * @exception nothing
	 * @author Yuelin Liu
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
		//		QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard();
		//		QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard();
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
	 * @throws CloneNotSupportedException 
	 * @exception nothing
	 *
	 */
	public static void releaseWall() throws CloneNotSupportedException 
	{	
		//QuoridorApplication.setJboard(new JBoard());

		WallMove wallmove = QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate();

		if(QuoridorController.verifyOverlapped(wallmove)==true) 
		{
			JOptionPane.showMessageDialog(null, "Illegal drop!");
			return;
		}
		//check if path exist


		String error = PathCheck.pathCheck();
		if(!error.equals("both")) {
			JOptionPane.showMessageDialog(null, "Only "+error+" player has path!");
			return;
		}
		Tile t = wallmove.getTargetTile();
		Player currentPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
		Game currentGame = QuoridorApplication.getQuoridor().getCurrentGame();
		GamePosition currentGamePosition = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition();
		List<Wall> bOnBoard = new ArrayList<Wall>();
		for(int i=0;i<currentGamePosition.getBlackWallsOnBoard().size();i++) {
			bOnBoard.add(currentGamePosition.getBlackWallsOnBoard().get(i));
		}
		List<Wall> wOnBoard = new ArrayList<Wall>();
		for(int i=0;i<currentGamePosition.getWhiteWallsOnBoard().size();i++) {
			wOnBoard.add(currentGamePosition.getWhiteWallsOnBoard().get(i));
		}
		currentGamePosition.setBlackWallsOnBoard(bOnBoard);
		currentGamePosition.setWhiteWallsOnBoard(wOnBoard);
		if(currentPlayer.hasGameAsWhite()) {
			//int id = 1 + QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getId();
			Player blackPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
			currentPlayer.setNextPlayer(blackPlayer);

			//GamePosition currentGamePosition = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition();
			currentGamePosition.addWhiteWallsOnBoard(wallmove.getWallPlaced());
			currentGamePosition.setPlayerToMove(blackPlayer);
			//currentGamePosition.setId(id);
			//QuoridorApplication.getQuoridor().getCurrentGame().addPosition(currentGamePosition);
			//QuoridorApplication.getQuoridor().getCurrentGame().setCurrentPosition(currentGamePosition);

		}else {	
			int id = 1 + QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getId();
			Player whitePlayer = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
			currentPlayer.setNextPlayer(whitePlayer);

			//GamePosition currentGamePosition = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition();
			currentGamePosition.addBlackWallsOnBoard(wallmove.getWallPlaced());
			currentGamePosition.setPlayerToMove(whitePlayer);
			//currentGamePosition.setId(id);
			//QuoridorApplication.getQuoridor().getCurrentGame().addPosition(currentGamePosition);
			//QuoridorApplication.getQuoridor().getCurrentGame().setCurrentPosition(currentGamePosition);
		}



		//moveList.add(wallmove);


		System.out.println("wall position: row= "+QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getRow()+
				"column = "+QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getColumn());

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
			JOptionPane.showMessageDialog(null, "Exceed the board!");
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
	 * Feature:StepForward
	 * 
	 * 
	 * @author Yuelin Liu
	 * @param String string, int oldRom, int oldColumn
	 * description: 
	 * String string: the input string; this contain two chars, the first one is from a to i and the second one is from 1 to 9
	 * int oldRow: the old row number of the wall that is to be moved
	 * int oldColumn: the old column number of the wall that is to be moved
	 * @return String string
	 * This method returns a string that the converted one; the returned string could be "right", "left", "down", "up"
	 * @exception nothing 
	 * 
	 * Functionality:
	 * This method compares and converts the original text to the data needed by the method.
	 */
	public static String convertMove2(String string, int oldRow, int oldColumn) {
		char s1 = string.charAt(0);
		char s2 = string.charAt(1);
		int newRow = 0;
		int newColumn = 0;
		if(s1=='a') newColumn = 1;
		if(s1=='b') newColumn = 2;
		if(s1=='c') newColumn = 3;
		if(s1=='d') newColumn = 4;
		if(s1=='e') newColumn = 5;
		if(s1=='f') newColumn = 6;
		if(s1=='g') newColumn = 7;
		if(s1=='h') newColumn = 8;
		if(s1=='i') newColumn = 9;

		if(s2=='1') newRow = 1;
		if(s2=='2') newRow = 2;
		if(s2=='3') newRow = 3;
		if(s2=='4') newRow = 4;
		if(s2=='5') newRow = 5;
		if(s2=='6') newRow = 6;
		if(s2=='7') newRow = 7;
		if(s2=='8') newRow = 8;
		if(s2=='9') newRow = 9;

		if((newRow==oldRow)&&(newColumn==oldColumn+1)) {
			return "right";
		}
		if((newRow==oldRow)&&(newColumn==oldColumn-1)) {
			return "left";
		}
		if((newRow==oldRow+1)&&(newColumn==oldColumn)) {
			return "down";
		}
		if((newRow==oldRow-1)&&(newColumn==oldColumn)) {
			return "up";
		}	
		return string;
	}


	/**
	 * Feature:StepForward
	 * 
	 * 
	 * @author Yuelin Liu
	 * @param  String string
	 * the input string: this contain two chars, the first one is from a to i and the second one is from 1 to 9
	 * @return ArrayList<Integer> result
	 * This method returns an Arraylist of the converted row number and column number; the returned arraylist contains two element, both from 1 to 9;
	 * @exception nothing 
	 * 
	 * Functionality: 
	 * This method takes the string from the text file, it then convert the two digits to int,
	 * it then set the two int to column and row for future use.
	 */
	public static ArrayList convertMove3(String string) {
		char s1 = string.charAt(0);
		char s2 = string.charAt(1);
		int newRow = 0;
		int newColumn = 0;
		if(s1=='a') newRow = 1;
		if(s1=='b') newRow = 2;
		if(s1=='c') newRow = 3;
		if(s1=='d') newRow = 4;
		if(s1=='e') newRow = 5;
		if(s1=='f') newRow = 6;
		if(s1=='g') newRow = 7;
		if(s1=='h') newRow = 8;
		if(s1=='i') newRow = 9;

		if(s2=='1') newColumn = 1;
		if(s2=='2') newColumn = 2;
		if(s2=='3') newColumn = 3;
		if(s2=='4') newColumn = 4;
		if(s2=='5') newColumn = 5;
		if(s2=='6') newColumn = 6;
		if(s2=='7') newColumn = 7;
		if(s2=='8') newColumn = 8;
		if(s2=='9') newColumn = 9;

		ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(newRow);
		result.add(newColumn);

		return result;
	}
	/**
	 * Feature: StepForward
	 * 
	 * @author Yuelin Liu 
	 * @param this method does not take input parameter
	 * @return void 
	 * 
	 * functionality: This stepForward method moves the current game to the next step in the replay mode.
	 * It first get the oldID from current position in current game; it then increase the oldID by one and set to the newID;
	 * in the end, it set newID to current game. The game then is set to next version in replay mode.
	 * 
	 */

	public static void stepForward() {
		int oldID = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getId();
		int newID = oldID + 1;
		if(newID >= QuoridorApplication.getQuoridor().getCurrentGame().getPositions().size()) {
			newID = QuoridorApplication.getQuoridor().getCurrentGame().getPositions().size() - 1;
		}
		QuoridorApplication.getQuoridor().getCurrentGame().setCurrentPosition(QuoridorApplication.getQuoridor().getCurrentGame().getPositions().get(newID));	
	}


	/**
	 * Feature: StepBackward
	 * 
	 * @author Yuelin Liu
	 * @param this method does not take input parameter
	 * @return void 
	 * 
	 * functionality: This stepBackward method moves the current game to the previous step in the replay mode.
	 * It first get the oldID from current position in the current game; it then decrease the oldID by one and set to the newID;
	 * in the end, it set new ID to current game. The game then is set to the previous version in replay mode.
	 */

	public static void stepBackward() {
		int oldID = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getId();
		int newID = oldID - 1;
		if(newID < 0) {
			newID = 0;
		}
		QuoridorApplication.getQuoridor().getCurrentGame().setCurrentPosition(QuoridorApplication.getQuoridor().getCurrentGame().getPositions().get(newID));	
	}


	/**
	 * Feature: JumpToStart
	 * 
	 * @author Yujing Yang
	 * @param this method does not take input parameter
	 * @return void
	 * 
	 * functionality: This JumpToStart method moves the current game to the first step in the game, when it has not been played.
	 * The ID for the initial step should be 0; so the method set position of currentGame to 0. 
	 * The current game is then jumped to start in replay mode.
	 */

	public static void jumpToStart() {
		QuoridorApplication.getQuoridor().getCurrentGame().setCurrentPosition(QuoridorApplication.getQuoridor().getCurrentGame().getPositions().get(0));
	}

	/**
	 * Feature: JumpToFinal
	 * 
	 * @author Yujing Yang
	 * @param this method does not take input parameter
	 * @return void
	 * 
	 * functionality: This jumpToFinal method moves the current game to the last step in the game, when all steps has been made.
	 * The ID for the last step should be the size of position -1; so the method set position of currentGame to size-1. 
	 * The current game is then jumped to final version in replay mode.
	 */

	public static void jumpToFinal() {
		int ID = QuoridorApplication.getQuoridor().getCurrentGame().getPositions().size() - 1;
		QuoridorApplication.getQuoridor().getCurrentGame().setCurrentPosition(QuoridorApplication.getQuoridor().getCurrentGame().getPositions().get(ID));
	}


	/**
	 * Feature: RotateWall
	 * 
	 * After this method, these two actions should be done
	 * The wall shall be rotated over the board to {string}
	 * A wall move candidate shall exist with {string} at position \\({int}, {int})
	 * 
	 * @param no parameter input
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
	 * @throws CloneNotSupportedException 
	 */

	public static boolean grabWall() throws CloneNotSupportedException {	

		Player currentPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
		GamePosition beforeCurrentGamePosition = (GamePosition) QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().clone();
		GamePosition  currentGamePosition = beforeCurrentGamePosition;
		int id = 1 + QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getId();
		currentGamePosition.setId(id);
		List<Wall> binStock = new ArrayList<Wall>();
		for(int i=0;i<currentGamePosition.getBlackWallsInStock().size();i++) {
			binStock.add(currentGamePosition.getBlackWallsInStock().get(i));
		}
		List<Wall> winStock = new ArrayList<Wall>();
		for(int i=0;i<currentGamePosition.getWhiteWallsInStock().size();i++) {
			winStock.add(currentGamePosition.getWhiteWallsInStock().get(i));
		}
		currentGamePosition.setBlackWallsInStock(binStock);
		currentGamePosition.setWhiteWallsInStock(winStock);
		QuoridorApplication.getQuoridor().getCurrentGame().addPosition(currentGamePosition);
		QuoridorApplication.getQuoridor().getCurrentGame().setCurrentPosition(currentGamePosition);


		try {
			if(currentPlayer.hasGameAsBlack()) {			

				List<Wall> inStock = currentGamePosition.getBlackWallsInStock();		
				Wall grabbedWall = inStock.get(0);

				System.out.println("Black: "+inStock.size());

				Tile WallTile = new Tile(5, 5, QuoridorApplication.getQuoridor().getBoard());
				int a = QuoridorApplication.getQuoridor().getCurrentGame().getMoves().size();
				WallMove WallMove = new WallMove(a+1, (a + 3) / 2, currentPlayer, WallTile,QuoridorApplication.getQuoridor().getCurrentGame(), Direction.Vertical, grabbedWall);	
				QuoridorApplication.getQuoridor().getCurrentGame().setWallMoveCandidate(WallMove);
				currentGamePosition.removeBlackWallsInStock(grabbedWall);
				System.out.println("Black after: "+inStock.size());
				return true;				
			}

			if(currentPlayer.hasGameAsWhite()) {

				List<Wall> inStock = currentGamePosition.getWhiteWallsInStock();
				Wall grabbedWall = inStock.get(0);

				System.out.println("White: "+inStock.size());

				Tile WallTile = new Tile(5, 5, QuoridorApplication.getQuoridor().getBoard());
				int a = QuoridorApplication.getQuoridor().getCurrentGame().getMoves().size();
				WallMove WallMove = new WallMove(a+1, (a + 3) / 2, currentPlayer, WallTile,QuoridorApplication.getQuoridor().getCurrentGame(), Direction.Vertical, grabbedWall);
				QuoridorApplication.getQuoridor().getCurrentGame().setWallMoveCandidate(WallMove);
				currentGamePosition.removeWhiteWallsInStock(grabbedWall);
				System.out.println("White after: "+inStock.size());
				return true;
			}
		}
		catch(Exception e) {
			//QuoridorApplication.getJboard().notifyIllegal2();
		}

		return false;

	}







	/**
	 * 
	 * Feature: SaveGame , SavePosition
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
	 * Feature: SaveGame , SavePosition
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
	 * Feature: SaveGame , SavePosition
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
	 * Feature: SaveGame , SavePosition
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
	 * Feature: SaveGame , SavePosition
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
	 * Feature: SaveGame , SavePosition
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
	 * Feature: SaveGame , SavePosition
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
	 * Feature: IdentifyGameDrawn
	 * This method checks the result of the game is Pending or Drawn
	 *  
	 * 
	 * @author Bozhong Lu
	 * @return String
	 */

	public static String checkGameDrawn() {

		boolean gameDrawn = false;

		ArrayList<ArrayList<Integer>> outer = new ArrayList<ArrayList<Integer>>();
		int numberOfMovesPerformed = QuoridorApplication.getQuoridor().getCurrentGame().numberOfMoves() ;
		int index = numberOfMovesPerformed-1 ;
		if( numberOfMovesPerformed >= 9) {
			for(int i=0 ; i<=8 ; i++) {
				ArrayList<Integer> inner = new ArrayList<Integer>();
				Move currentCheckingMove = QuoridorApplication.getQuoridor().getCurrentGame().getMove(index) ;
				int row = currentCheckingMove.getTargetTile().getRow() ;
				int column = currentCheckingMove.getTargetTile().getColumn();
				inner.add(row);
				inner.add(column);
				outer.add(inner);
				index--;
			}
			//current player moves
			int move9row = outer.get(0).get(0);
			int move9column = outer.get(0).get(1);
			int move7row = outer.get(2).get(0);
			int move7column = outer.get(2).get(1);
			int move5row = outer.get(4).get(0);
			int move5column = outer.get(4).get(1);
			int move3row = outer.get(6).get(0);
			int move3column = outer.get(6).get(1);
			int move1row = outer.get(8).get(0);
			int move1column = outer.get(8).get(1);

			//opponent player moves
			int move8row = outer.get(1).get(0);
			int move8column = outer.get(1).get(1);
			int move6row = outer.get(3).get(0);
			int move6column = outer.get(3).get(1);
			int move4row = outer.get(5).get(0);
			int move4column = outer.get(5).get(1);
			int move2row = outer.get(7).get(0);
			int move2column = outer.get(7).get(1);

			if((move9row == move5row)&&(move5row == move1row)&&(move9column == move5column)&&(move5column == move1column)) {
				if((move3row == move7row)&&(move3column == move7column)) {
					if(((move9row == move7row+1)||(move9row == move7row-1))&&(move9column == move7column)) {
						if((move8row == move4row)&&(move6row == move2row)&&(move8column == move4column)&&(move6column == move2column)) {
							if(((move8row == move6row+1)||(move8row == move6row-1))&&(move8column == move6column)) {
								gameDrawn = true ;
							}else if(((move8column == move6column+1)||(move8column == move6column-1))&&(move8row == move6row)) {
								gameDrawn = true ;
							}
						}
					}else if(((move9column == move7column+1)||(move9column == move7column-1))&&(move9row == move7row)) {
						if((move8row == move4row)&&(move6row == move2row)&&(move8column == move4column)&&(move6column == move2column)) {
							if(((move8row == move6row+1)||(move8row == move6row-1))&&(move8column == move6column)) {
								gameDrawn = true ;
							}else if(((move8column == move6column+1)||(move8column == move6column-1))&&(move8row == move6row)) {
								gameDrawn = true ;
							}
						}
					}
				}
			}
		}


		if (gameDrawn) {
			QuoridorApplication.getQuoridor().getCurrentGame().setGameStatus(GameStatus.Draw);
			return "Drawn" ;
		}
		else {
			return "Pending";
		}

	}



	/**
	 * Feature:IdentifyGameDrawn
	 * 
	 * 
	 * @author Bozhong Lu
	 * @param string String stands for the direction of the wall that is to be moved.
	 * @return void
	 * @exception nothing 
	 */
	public static String convertToMove(String stringRow , String stringCol , int oldRow, int oldColumn) {

		String result = "";
		int newRow = 0;
		int newColumn = 0;
		if(stringCol=="1") newColumn = 1;
		if(stringCol=="2") newColumn = 2;
		if(stringCol=="3") newColumn = 3;
		if(stringCol=="4") newColumn = 4;
		if(stringCol=="5") newColumn = 5;
		if(stringCol=="6") newColumn = 6;
		if(stringCol=="7") newColumn = 7;
		if(stringCol=="8") newColumn = 8;
		if(stringCol=="9") newColumn = 9;

		if(stringRow=="1") newRow = 1;
		if(stringRow=="2") newRow = 2;
		if(stringRow=="3") newRow = 3;
		if(stringRow=="4") newRow = 4;
		if(stringRow=="5") newRow = 5;
		if(stringRow=="6") newRow = 6;
		if(stringRow=="7") newRow = 7;
		if(stringRow=="8") newRow = 8;
		if(stringRow=="9") newRow = 9;

		if((newRow==oldRow)&&(newColumn==oldColumn+1)) {
			result = "right";
		}
		if((newRow==oldRow)&&(newColumn==oldColumn-1)) {
			result = "left";
		}
		if((newRow==oldRow+1)&&(newColumn==oldColumn)) {
			result = "down";
		}
		if((newRow==oldRow-1)&&(newColumn==oldColumn)) {
			result = "up";
		}	
		return result;
	}



	/**
	 * This method will take a file as input and load all the moves and positions into the game
	 * 
	 * @author Zirui He
	 * @param filename
	 * @param white
	 * @param black
	 * @return
	 * @throws Exception
	 */
	public static boolean loadGame(String filename, Player white, Player black) throws Exception {
		Quoridor quoridor = QuoridorApplication.getQuoridor();

		//create game that is initializing
		ArrayList<Player> playersList = new ArrayList<Player>();
		playersList.add(white);
		playersList.add(black);
		QuoridorController.prepareToLoadGame(playersList);

		FileInputStream inputstream = new FileInputStream(filename);
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(inputstream);

		int whiteRound = 1;
		int blackRound = 2;
		int positionId = 1;
		int whiteWallIndex = 0;
		int blackWallIndex = 0;

		List<Wall> whiteWalls = new ArrayList<Wall>();
		List<Wall> blackWalls = new ArrayList<Wall>();
		List<Move> moves = new ArrayList<Move>();

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String delims = "[ .]+";
			String[] split = line.split(delims);
			int moveNum = Integer.parseInt(split[0]);

			//*****************************************************************
			//the first player to move is always white, now set up white player
			//*****************************************************************
			Tile whitetile = null;
			String[] sw = split[1].split("");	//split string by each character
			try {
				whitetile = quoridor.getBoard().getTile((Integer.parseInt(sw[1]) - 1) * 9 + columnNum(sw[0]) - 1);
			} catch(Exception e) {
				throw(new Exception("Out of boundary!", e));
			}
			if (sw.length == 2) {	//check if is pawn move					
				moves.add(new StepMove(moveNum, whiteRound, white, whitetile, quoridor.getCurrentGame()));	//add move to the list
				GamePosition currentGamePosition = (GamePosition) QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().clone();
				PlayerPosition whiteposition = new PlayerPosition(white, whitetile);
				currentGamePosition.setWhitePosition(whiteposition);
				currentGamePosition.setId(positionId);
				QuoridorApplication.getQuoridor().getCurrentGame().addPosition(currentGamePosition);
				QuoridorApplication.getQuoridor().getCurrentGame().setCurrentPosition(currentGamePosition);
				currentGamePosition.setPlayerToMove(black);
				positionId++;
			}
			if (sw.length == 3) { 	//check if is wall move
				Direction direction;
				switch (sw[2]) {
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
				moves.add(new WallMove(moveNum, whiteRound, white, whitetile, quoridor.getCurrentGame(), direction, wall)); 	//put wall on the board
				GamePosition currentGamePosition = (GamePosition) QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().clone();
				List<Wall> wOnBoard = new ArrayList<Wall>();
				for(int i=0;i<currentGamePosition.getWhiteWallsOnBoard().size();i++) {
					wOnBoard.add(currentGamePosition.getWhiteWallsOnBoard().get(i));
				}
				wOnBoard.add(wall);
				//				List<Wall> wWalls = currentGamePosition.getWhiteWallsOnBoard();
				//				wWalls.add(wall);
				currentGamePosition.setWhiteWallsOnBoard(wOnBoard);
				//currentGamePosition.addWhiteWallsOnBoard(wall);
				currentGamePosition.removeWhiteWallsInStock(wall);
				currentGamePosition.setId(positionId);
				QuoridorApplication.getQuoridor().getCurrentGame().addPosition(currentGamePosition);
				QuoridorApplication.getQuoridor().getCurrentGame().setCurrentPosition(currentGamePosition);
				currentGamePosition.setPlayerToMove(black);
				positionId++;
				whiteWalls.add(wall);			
			}

			//*****************************************************************
			//the second player to move is always black, now set up black player
			//*****************************************************************
			Tile blacktile = null;
			String[] sb = split[2].split("");	//split string by each character
			try {
				blacktile = quoridor.getBoard().getTile((Integer.parseInt(sb[1]) - 1) * 9 + columnNum(sb[0]) - 1);
			} catch(Exception e) {
				throw(new Exception("Out of boundary!", e));
			}
			if (sb.length == 2) {	//check if is pawn move					
				moves.add(new StepMove(moveNum, blackRound, black, blacktile, quoridor.getCurrentGame()));	//add move to the list
				GamePosition currentGamePosition = (GamePosition) QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().clone();
				PlayerPosition blackposition = new PlayerPosition(white, blacktile);
				currentGamePosition.setBlackPosition(blackposition);
				currentGamePosition.setId(positionId);
				QuoridorApplication.getQuoridor().getCurrentGame().addPosition(currentGamePosition);
				QuoridorApplication.getQuoridor().getCurrentGame().setCurrentPosition(currentGamePosition);
				currentGamePosition.setPlayerToMove(white);
				positionId++;
			}
			if (sb.length == 3) { 	//check if is wall move
				Direction direction;
				switch (sb[2]) {
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
				moves.add(new WallMove(moveNum, blackRound, black, blacktile, quoridor.getCurrentGame(), direction, wall)); 	//put wall on the board
				GamePosition currentGamePosition = (GamePosition) QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().clone();
				List<Wall> bOnBoard = new ArrayList<Wall>();
				for(int i=0;i<currentGamePosition.getBlackWallsOnBoard().size();i++) {
					bOnBoard.add(currentGamePosition.getBlackWallsOnBoard().get(i));
				}
				bOnBoard.add(wall);
				//				List<Wall> bWalls = currentGamePosition.getBlackWallsOnBoard();
				//				bWalls.add(wall);
				currentGamePosition.setBlackWallsOnBoard(bOnBoard);
				//currentGamePosition.addBlackWallsOnBoard(wall);
				currentGamePosition.removeBlackWallsInStock(wall);
				currentGamePosition.setId(positionId);
				QuoridorApplication.getQuoridor().getCurrentGame().addPosition(currentGamePosition);
				QuoridorApplication.getQuoridor().getCurrentGame().setCurrentPosition(currentGamePosition);
				currentGamePosition.setPlayerToMove(white);
				positionId++;
				blackWalls.add(wall);			
			}

		}

		//quoridor.getCurrentGame().setGameStatus(GameStatus.ReadyToStart);
		if (QuoridorController.checkGameResult() == "whiteWon" || QuoridorController.checkGameResult() == "blackWon") {
			quoridor.getCurrentGame().setGameStatus(GameStatus.Replay);
		} else {
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
			quoridor.getCurrentGame().setGameStatus(GameStatus.ReadyToStart);
		}

		return true;
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
	 * Feature: IdentifyGameDrawn , IdentifyGameWon
	 * This method will check the game result and return a string to notify the result 
	 * 
	 * @author Bozhong Lu , Zirui He
	 * @return game result in string
	 */
	public static String checkGameResult() {
		Player white = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
		Destination whiteDest = white.getDestination();
		Direction whiteDir = whiteDest.getDirection();
		int whiteTarget = whiteDest.getTargetNumber();
		int whiteRow = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getRow();
		boolean whiteWon = (whiteDir == Direction.Horizontal) && (whiteTarget == whiteRow);

		Player black = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
		Destination blackDest = black.getDestination();
		Direction blackDir = blackDest.getDirection();
		int blackTarget = blackDest.getTargetNumber();
		int blackRow = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getRow();
		boolean blackWon = (blackDir == Direction.Horizontal) && (blackTarget == blackRow);

		//check Game Drawn
		boolean gameDrawn = false;

		ArrayList<ArrayList<Integer>> outer = new ArrayList<ArrayList<Integer>>();
		int numberOfMovesPerformed = QuoridorApplication.getQuoridor().getCurrentGame().numberOfMoves() ;
		int index = numberOfMovesPerformed-1 ;
		if( numberOfMovesPerformed >= 9) {
			for(int i=0 ; i<=8 ; i++) {
				ArrayList<Integer> inner = new ArrayList<Integer>();
				Move currentCheckingMove = QuoridorApplication.getQuoridor().getCurrentGame().getMove(index) ;
				int row = currentCheckingMove.getTargetTile().getRow() ;
				int column = currentCheckingMove.getTargetTile().getColumn();
				inner.add(row);
				inner.add(column);
				outer.add(inner);
				index--;
			}
			//current player moves
			int move9row = outer.get(0).get(0);
			int move9column = outer.get(0).get(1);
			int move7row = outer.get(2).get(0);
			int move7column = outer.get(2).get(1);
			int move5row = outer.get(4).get(0);
			int move5column = outer.get(4).get(1);
			int move3row = outer.get(6).get(0);
			int move3column = outer.get(6).get(1);
			int move1row = outer.get(8).get(0);
			int move1column = outer.get(8).get(1);

			//opponent player moves
			int move8row = outer.get(1).get(0);
			int move8column = outer.get(1).get(1);
			int move6row = outer.get(3).get(0);
			int move6column = outer.get(3).get(1);
			int move4row = outer.get(5).get(0);
			int move4column = outer.get(5).get(1);
			int move2row = outer.get(7).get(0);
			int move2column = outer.get(7).get(1);

			if((move9row == move5row)&&(move5row == move1row)&&(move9column == move5column)&&(move5column == move1column)) {
				if((move3row == move7row)&&(move3column == move7column)) {
					if(((move9row == move7row+1)||(move9row == move7row-1))&&(move9column == move7column)) {
						if((move8row == move4row)&&(move6row == move2row)&&(move8column == move4column)&&(move6column == move2column)) {
							if(((move8row == move6row+1)||(move8row == move6row-1))&&(move8column == move6column)) {
								gameDrawn = true ;
							}else if(((move8column == move6column+1)||(move8column == move6column-1))&&(move8row == move6row)) {
								gameDrawn = true ;
							}
						}
					}else if(((move9column == move7column+1)||(move9column == move7column-1))&&(move9row == move7row)) {
						if((move8row == move4row)&&(move6row == move2row)&&(move8column == move4column)&&(move6column == move2column)) {
							if(((move8row == move6row+1)||(move8row == move6row-1))&&(move8column == move6column)) {
								gameDrawn = true ;
							}else if(((move8column == move6column+1)||(move8column == move6column-1))&&(move8row == move6row)) {
								gameDrawn = true ;
							}
						}
					}
				}
			}
		}


		if (gameDrawn) {
			QuoridorApplication.getQuoridor().getCurrentGame().setGameStatus(GameStatus.Draw);
			return "Drawn" ;
		} else if (whiteWon && !blackWon) {
			QuoridorApplication.getQuoridor().getCurrentGame().setGameStatus(GameStatus.WhiteWon);
			return "whiteWon";
		} 
		else if (!whiteWon && blackWon) {
			QuoridorApplication.getQuoridor().getCurrentGame().setGameStatus(GameStatus.BlackWon);
			return "blackWon";
		}
		else {
			return "pending";
		}

	}


	/**
	 * Feature:IdentifyGameWon
	 * This method return game result if one player's clock count to zero
	 * 
	 * @author Zirui He
	 * @param player
	 */
	public static String clockCountToZero(Player player) {
		Time timeremain = player.getRemainingTime();
		Time zero = new Time(0);
		if (timeremain.equals(zero)) {
			if (player.hasGameAsBlack()) {
				QuoridorApplication.getQuoridor().getCurrentGame().setGameStatus(GameStatus.WhiteWon);
				return "whiteWon";
			} else {
				QuoridorApplication.getQuoridor().getCurrentGame().setGameStatus(GameStatus.BlackWon);
				return "blackWon";
			}
		} else {
			return "pending";
		}
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



		if (player.hasGameAsBlack()) {

			player.setNextPlayer(white);
			QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().setPlayerToMove(white);
			//QuoridorApplication.getJboard().whiteTurn();
		}else {

			player.setNextPlayer(black);
			QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().setPlayerToMove(black);
			//QuoridorApplication.getJboard().blackTurn();
		}

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
	public static void initializeEmptyBoard() {
		//	QuoridorApplication.setJBoard(new JBoard());
		Board board = new Board(QuoridorApplication.getQuoridor());
		//Board board = QuoridorApplication.getQuoridor().getBoard();
		for(int i = 1; i<= 9; i++) {
			for(int j = 1; j<=9; j++) {
				Tile tile = new Tile(i, j, board);
				board.addTile(tile);
			}
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

		Board board = QuoridorApplication.getQuoridor().getBoard();
		Game game = QuoridorApplication.getQuoridor().getCurrentGame();
		QuoridorApplication.getQuoridor().setBoard(board);
		Player whitePlayer = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
		Player blackPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
		whitePlayer.setGameAsWhite(game);
		Tile initWhite = QuoridorApplication.getQuoridor().getBoard().getTile(8*9+4);
		Tile initBlack = QuoridorApplication.getQuoridor().getBoard().getTile(4);
		PlayerPosition initialWhite = new PlayerPosition(whitePlayer, initWhite);
		PlayerPosition initialBlack = new PlayerPosition(whitePlayer, initBlack);

		GamePosition g = new GamePosition(0, initialWhite, initialBlack, whitePlayer, game);
		game.addPosition(g);
		game.setCurrentPosition(g);
		initializeWhiteWall(g,whitePlayer);
		initializeBlackWall(g,blackPlayer);
		g.setPlayerToMove(whitePlayer);


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
		int blackIndex = 1;
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
		int whiteIndex = 11;
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
		new Game(GameStatus.Initializing, MoveMode.WallMove, QuoridorApplication.getQuoridor());
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

	public static int pawnStepUp(int row) {
		if(row>1) return row-1; 
		else return -1;	
	}
	public static int pawnStepDown(int row) {
		if(row<9) return row+1; 
		else return -2;	
	}
	public static int pawnStepLeft(int column) {
		if(column>1) return column-1; 
		else return -3;	
	}
	public static int pawnStepRight(int column) {
		if(column<9) return column+1; 
		else return -3;	
	}
	public static int pawnJumpUp(int row) {
		if(row>2) return row-2; 
		else return -5;	
	}
	public static int pawnJumpDown(int row) {
		if(row<8) return row+2; 
		else return -6;	
	}
	public static int pawnJumpLeft(int column) {
		if(column>2) return column-2; 
		else return -7;	
	}
	public static int pawnJumpRight(int column) {
		if(column<8) return column+2; 
		else return -8;	
	}




	/**
	 * We are not using this method right now
	 * 
	 * @author Gengyi Sun
	 * @param String string, String string2
	 * @return boolean
	 */ 
	public static boolean movePawn(String moveMode, String moveDir) {

		Player player = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
		Player whitePlayer = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
		Player blackPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();

		PlayerPosition whitePosition = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition();
		PlayerPosition blackPosition = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition();

		int id = 1 + QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getId();
		Game game = QuoridorApplication.getQuoridor().getCurrentGame();
		Tile whiteTile = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile();
		Tile blackTile = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile();

		Tile tile;
		if(player.equals(whitePlayer)) {

			tile = whitePosition.getTile();
			int row = tile.getRow();
			int column = tile.getColumn();
			if(moveMode.equals("step")) {

				switch(moveDir) {
				case "up":
					row = pawnStepUp(row);
					break;
				case "down":
					row = pawnStepDown(row);
					break;
				case "left":
					column = pawnStepLeft(column);
					break;
				case "right":
					column = pawnStepRight(column);	
					break;
				default:
					row = -1;
					column = -1;

				}
			}else if(moveMode.equals("jump")){
				switch(moveDir) {
				case "up":
					row = pawnJumpUp(row);
					break;
				case "down":
					row = pawnJumpDown(row);
					break;
				case "left":
					column = pawnJumpLeft(column);
					break;
				case "right":
					column = pawnJumpRight(column);	
					break;
				default:
					row = -1;
					column = -1;

				}
			}
			if(row<=0||column<=0) {
				QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().setPlayerToMove(player);

				return false;
			}

			Tile newTile = QuoridorApplication.getQuoridor().getBoard().getTile((row-1)*9+(column-1));
			PlayerPosition newWhitePosition = new PlayerPosition(whitePlayer,newTile);		
			PlayerPosition newBlackPosition = new PlayerPosition(blackPlayer,blackTile);			

			GamePosition currentGamePosition = new GamePosition(id, newWhitePosition, newBlackPosition, blackPlayer,game);
			QuoridorApplication.getQuoridor().getCurrentGame().addPosition(currentGamePosition);
			QuoridorApplication.getQuoridor().getCurrentGame().setCurrentPosition(currentGamePosition);
		}
		else if (player.equals(blackPlayer)){

			tile = blackPosition.getTile();
			int row = tile.getRow();
			int column = tile.getColumn();
			if(moveMode.equals("step")) {

				switch(moveDir) {
				case "up":
					row = pawnStepUp(row);
					break;
				case "down":
					row = pawnStepDown(row);
					break;
				case "left":
					column = pawnStepLeft(column);
					break;
				case "right":
					column = pawnStepRight(column);	
					break;
				default:
					row = -1;
					column = -1;

				}
			}else if(moveMode.equals("jump")){
				switch(moveDir) {
				case "up":
					row = pawnJumpUp(row);
					break;
				case "down":
					row = pawnJumpDown(row);
					break;
				case "left":
					column = pawnJumpLeft(column);
					break;
				case "right":
					column = pawnJumpRight(column);	
					break;
				default:
					row = -1;
					column = -1;

				}
			}
			if(row<=0||column<=0) {
				QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().setPlayerToMove(player);

				return false;
			}
			Tile newTile = QuoridorApplication.getQuoridor().getBoard().getTile((row-1)*9+(column-1));
			PlayerPosition newBlackPosition = new PlayerPosition(blackPlayer,newTile);		
			PlayerPosition newWhitePosition = new PlayerPosition(whitePlayer,whiteTile);			
			GamePosition currentGamePosition = new GamePosition(id, newBlackPosition, newWhitePosition, whitePlayer,game);
			QuoridorApplication.getQuoridor().getCurrentGame().addPosition(currentGamePosition);
			QuoridorApplication.getQuoridor().getCurrentGame().setCurrentPosition(currentGamePosition);
		}
		else { 
			QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().setPlayerToMove(player);

			return false;
		}
		return true;

	}
	/**
	 * this method will move a pawn to certain direction and return the result
	 * 
	 * @author Zirui He, Gengyi Sun
	 * @param String string, String string2
	 * @return boolean
	 * @throws CloneNotSupportedException 
	 */ 
	public static boolean movePlayer(String string, String string2) throws CloneNotSupportedException {


		Player whitePlayer = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
		Player blackPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();

		PlayerPosition whitePosition = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition();
		PlayerPosition blackPosition = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition();

		int id = 1 + QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getId();
		Game game = QuoridorApplication.getQuoridor().getCurrentGame();
		Tile whiteTile = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile();
		Tile blackTile = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile();

		Tile tile;
		if(string.equals("white")) {

			tile = whitePosition.getTile();
			int row = tile.getRow();
			int column = tile.getColumn();
			System.out.println("White Before Tile:"+row+","+column+"=======================================================");


			switch(string2) {
			case "up":
				row = pawnStepUp(row);
				break;
			case "down":
				row = pawnStepDown(row);
				break;
			case "left":
				column = pawnStepLeft(column);
				break;
			case "right":
				column = pawnStepRight(column);	
				break;
			default:
				row = -1;
				column = -1;


			}

			if(row<=0||column<=0) {
				QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().setPlayerToMove(whitePlayer);
				return false;
			}

			Tile newTile = QuoridorApplication.getQuoridor().getBoard().getTile(9*(row-1)+(column-1));
			PlayerPosition newWhitePosition = new PlayerPosition(whitePlayer,newTile);		
			//PlayerPosition newBlackPosition = new PlayerPosition(blackPlayer,blackTile);			

			//GamePosition currentGamePosition = new GamePosition(id, newWhitePosition, newBlackPosition, blackPlayer,game);
			GamePosition currentGamePosition = (GamePosition) QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().clone();
			//GamePosition currentGamePosition = new GamePosition(id, newWhitePosition, blackPosition, blackPlayer, game);
			currentGamePosition.setWhitePosition(newWhitePosition);
			currentGamePosition.setPlayerToMove(blackPlayer);
			currentGamePosition.setId(id);
			QuoridorApplication.getQuoridor().getCurrentGame().addPosition(currentGamePosition);
			QuoridorApplication.getQuoridor().getCurrentGame().setCurrentPosition(currentGamePosition);
			int x = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getRow();
			int y = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getColumn();

			System.out.println("White After Tile:"+x+","+y+"=======================================================");

		}
		else if (string.equals("black")){

			tile = blackPosition.getTile();
			int row = tile.getRow();
			int column = tile.getColumn();
			System.out.println("Black Before Tile:"+row+","+column+"=======================================================");

			switch(string2) {
			case "up":
				row = pawnStepUp(row);
				break;
			case "down":
				row = pawnStepDown(row);
				break;
			case "left":
				column = pawnStepLeft(column);
				break;
			case "right":
				column = pawnStepRight(column);	
				break;
			default:
				row = -1;
				column = -1;

			}

			if(row<=0||column<=0) {
				QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().setPlayerToMove(whitePlayer);

				return false;
			}
			Tile newTile = QuoridorApplication.getQuoridor().getBoard().getTile((row-1)*9+(column-1));
			PlayerPosition newBlackPosition = new PlayerPosition(blackPlayer,newTile);		
			//PlayerPosition newWhitePosition = new PlayerPosition(whitePlayer,whiteTile);			
			//GamePosition currentGamePosition = new GamePosition(id, newBlackPosition, newWhitePosition, whitePlayer,game);
			//GamePosition currentGamePosition = new GamePosition(id, whitePosition, newBlackPosition, whitePlayer, game);
			GamePosition currentGamePosition = (GamePosition) QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().clone();
			currentGamePosition.setBlackPosition(newBlackPosition);
			currentGamePosition.setPlayerToMove(whitePlayer);
			currentGamePosition.setId(id);
			QuoridorApplication.getQuoridor().getCurrentGame().addPosition(currentGamePosition);
			QuoridorApplication.getQuoridor().getCurrentGame().setCurrentPosition(currentGamePosition);
			int x = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getRow();
			int y = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getColumn();

			System.out.println("Black After Tile:"+x+","+y+"=======================================================");

		}
		else {
			return false;
		}
		return true;

	}

	/**
	 * This method takes two parameters: one for suggest player and one for direction. It will proceed a jump move
	 * for the given player. It is assumed that the movement is legal.
	 *  
	 * @author Pengnan Fan 
	 * @param player
	 * @param direction
	 * @return A boolean variable to suggest if the movement has been proceed.
	 */

	public static boolean jumpPlayer(String player, String direction) {
		Player p;
		Tile currentTile;
		Tile targetTile;
		Game g = QuoridorApplication.getQuoridor().getCurrentGame();
		int x, y;
		PlayerPosition pos;
		p = g.getCurrentPosition().getPlayerToMove();
		if (player.equals("white")&&p.hasGameAsBlack()) {
			throw new IllegalArgumentException("Wrong player: player CANNOT be "+player);
		} else if (player.equals("black")&&p.hasGameAsWhite()) {
			throw new IllegalArgumentException("Wrong player: player CANNOT be "+player);
		}
		if (p.hasGameAsBlack()) {
			currentTile = g.getCurrentPosition().getBlackPosition().getTile();
		} else if (p.hasGameAsWhite()) {
			currentTile = g.getCurrentPosition().getWhitePosition().getTile();
		} else {
			throw new UnsupportedOperationException("Something goes wrong");
		}
		x = currentTile.getColumn();
		y = currentTile.getRow();
		if (direction.equals("up")) {
			y-=2;
		} else if (direction.equals("down")) {
			y+=2;
		} else if (direction.equals("left")) {
			x-=2;
		} else if (direction.equals("right")) {
			x+=2;
		} else {
			throw new IllegalArgumentException("Wrong direction: direction CANNOT be "+direction);
		}
		targetTile = QuoridorApplication.getQuoridor().getBoard().getTile((y-1)*9+x-1);
		pos = new PlayerPosition(p, targetTile);
		boolean result = false;
		if (p.hasGameAsBlack()) {
			result = g.getCurrentPosition().setBlackPosition(pos);
			if (result) {
				g.getCurrentPosition().setPlayerToMove(g.getWhitePlayer());
			}
		} else if(p.hasGameAsWhite()) {
			result = g.getCurrentPosition().setWhitePosition(pos);
			if (result) {
				g.getCurrentPosition().setPlayerToMove(g.getBlackPlayer());
			}
		} else {
			throw new UnsupportedOperationException("Something goes wrong");
		}
		return result;
	}

	/**
	 * This method will let the current player resign immediately and end the game.
	 * @author Pengnan Fan
	 * @return A boolean value to suggest if it is successful.
	 */
	public static boolean resign() {
		boolean result = false;
		Game currentGame = QuoridorApplication.getQuoridor().getCurrentGame();
		if (currentGame == null) {
			throw new RuntimeException("No current game exist");
		}
		Player currentPlayer = currentGame.getCurrentPosition().getPlayerToMove();
		if (currentPlayer == null) {
			throw new RuntimeException("No current player");
		}
		if (currentPlayer.hasGameAsBlack()) {
			result = currentGame.setGameStatus(GameStatus.WhiteWon);
		} else if (currentPlayer.hasGameAsWhite()) {
			result = currentGame.setGameStatus(GameStatus.BlackWon);
		} else {
			throw new RuntimeException("Unable to resign game");
		}
		return result;
	}

	/**
	 * This method will set the current game to be in replay mode.
	 * @author Pengnan Fan
	 * @return A boolean value to suggest if it is successful.
	 */
	public static boolean replay() {
		boolean result = false;
		Game currentGame = QuoridorApplication.getQuoridor().getCurrentGame();
		if (currentGame == null) {
			throw new RuntimeException("No current game exist");
		}
		result = currentGame.setGameStatus(GameStatus.Replay);
		return result;
	}

	//helper method

	/**
	 * this helper method change the single character that represent column to integer 
	 * 
	 * @author Zirui He
	 * @param x
	 * @return int
	 */
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

	/**
	 * this method get users from model and use the users to create two players and 
	 * connect players to the model
	 * 
	 * @author Zirui He
	 * @param userName1
	 * @param userName2
	 * @return ArrayList<Player>
	 */
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

	/**
	 * This is the method form stepDefinition written by Marton
	 * 
	 * @param players
	 */
	public static void prepareToLoadGame(ArrayList<Player> players) {
		Quoridor quoridor = QuoridorApplication.getQuoridor();
		// There are total 36 tiles in the first four rows and
		// indexing starts from 0 -> tiles with indices 36 and 36+8=44 are the starting
		// positions
		Tile player1StartPos = quoridor.getBoard().getTile(76);
		Tile player2StartPos = quoridor.getBoard().getTile(4);

		Game game = new Game(GameStatus.Initializing, MoveMode.PlayerMove, quoridor);
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

	/**
	 * This is the method form stepDefinition written by Marton
	 */
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
