package ca.mcgill.ecse223.quoridor.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

//import org.apache.commons.lang3.time.StopWatch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import java.sql.Time;

import ca.mcgill.ecse223.quoridor.QuoridorApplication;
import ca.mcgill.ecse223.quoridor.model.*;
import ca.mcgill.ecse223.quoridor.model.Game.GameStatus;
import ca.mcgill.ecse223.quoridor.model.Game.MoveMode;

public class QuoridorController {
	
	
	/**
	 * Feature:DropWall
	 * 
	 * I will check whether the WallMove candidate could be set at specified tile.
	 * 
     * @author Yuelin Liu
	 * @param wallmove WallMove candidate to be check whether it is valid and set it.
	 * @return boolean
	 * @exception UnsupportedOperationException
	 *
	 */
	public static boolean verifyOverlapped(WallMove wallmove) {
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
						return true;
					}
				}
				if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard().get(i).getMove().getWallDirection()==Direction.Horizontal) {
					if((newColumn==currentColumn)&&(currentRow==newRow)) {
						return true;
					}
				}	
			}
			for(int i=0; i<sizeW; i++) {
				int newRow = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard().get(i).getMove().getTargetTile().getRow();
				int newColumn = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard().get(i).getMove().getTargetTile().getColumn();
				if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard().get(i).getMove().getWallDirection()==Direction.Vertical) {
					if((newColumn==currentColumn)&&(newRow<=currentRow+1)&&(newRow>=currentRow-1)) {
						return true;
					}
				}
				if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard().get(i).getMove().getWallDirection()==Direction.Horizontal) {
					if((newColumn==currentColumn)&&(currentRow==newRow)) {
						return true;
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
						return true;
					}
				}
				if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard().get(i).getMove().getWallDirection()==Direction.Vertical) {
					if((newColumn==currentColumn)&&(currentRow==newRow)) {
						return true;
					}
				}	
			}
			for(int i=0; i<sizeW; i++) {
				int newRow = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard().get(i).getMove().getTargetTile().getRow();
				int newColumn = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard().get(i).getMove().getTargetTile().getColumn();
				if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard().get(i).getMove().getWallDirection()==Direction.Horizontal) {
					if((newRow==currentRow)&&(newColumn<=currentColumn+1)&&(newColumn>=currentColumn-1)) {
						return true;
					}
				}
				if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard().get(i).getMove().getWallDirection()==Direction.Vertical) {
					if((newColumn==currentColumn)&&(currentRow==newRow)) {
						return true;
					}
				}	
			}
			
		}
		return false;
	}
	
	
	/**
	 * Feature:DropWall
	 * 
	 * 
	 * I will check whether the WallMove candidate could not be set at specified tile.
	 * 
     * @author Yuelin Liu
	 * @param wallmove WallMove candidate to be check whether it is invalid and set it
	 * @return boolean
	 * @exception UnsupportedOperationException
	 *
	 */
	public static boolean verifyWallMoveInvalid(WallMove wallmove) {
		throw new UnsupportedOperationException();
	}
	
	
	/**
	 * Feature:DropWall
	 * 
	 * An action to release the wall, to be more specific, set the wall candidate at certain tile.
	 * 
     * @author Yuelin Liu
	 * @param wallmove WallMove candidate to be released.
	 * @return void
	 * @exception UnsupportedOperationException
	 *
	 */
	public static void ReleaseWall(WallMove wallmove) 
	{	
		Tile t = wallmove.getTargetTile();
		Player currentPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
		if(currentPlayer.hasGameAsBlack()) {
			Player whitePlayer = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
			QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().setPlayerToMove(whitePlayer);
			//QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove().setGameAsBlack(aNewGameAsBlack)
		}
		if(currentPlayer.hasGameAsBlack()) {
			Player blackPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
			QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().setPlayerToMove(blackPlayer);
			//QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove().setGameAsBlack(aNewGameAsBlack)
		}
	
		
		if(currentPlayer.hasGameAsBlack()) {
			QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().addBlackWallsOnBoard(wallmove.getWallPlaced());
		}
		
		if(currentPlayer.hasGameAsWhite()) {
			QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().addWhiteWallsOnBoard(wallmove.getWallPlaced());
		}
		
	
	}
	
	/**
	 * Feature:MoveWall
	 * 
	 * An action to move the wall candidate from old position to new position.
	 * @author Yuelin Liu
	 * @param string String stands for the direction of the wall that is to be moved.
	 * @return void
	 * @exception UnsupportedOperationException
	 */
	public static void MoveWall(String string)
	{	
		if(string.equalsIgnoreCase("left")) {
				Tile tileLeft = new Tile(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getRow()
						,QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getColumn()-1,QuoridorApplication.getQuoridor().getBoard());
				
				QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().setTargetTile(tileLeft);	
		}
		
		if(string.equalsIgnoreCase("right")) {
				Tile tileRight = new Tile(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getRow()
						,QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getColumn()+1,QuoridorApplication.getQuoridor().getBoard());
				
				QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().setTargetTile(tileRight);
		}
		
		if(string.equalsIgnoreCase("up")) {
				Tile tileUp = new Tile(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getRow()-1
						,QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getColumn(),QuoridorApplication.getQuoridor().getBoard());
				
				QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().setTargetTile(tileUp);
		}
		
		if(string.equalsIgnoreCase("down")) {
				Tile tileDown = new Tile(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getRow()+1
						,QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getColumn(),QuoridorApplication.getQuoridor().getBoard());
				
				QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().setTargetTile(tileDown);
		}
		//TODO: Write logic
		//throw new UnsupportedOperationException();
	}
	
	
	
	/**
	 * Feature:MoveWall
	 * 
	 * I will check and make sure the wall candidate is not at the edge of the board in order to guarantee all walls are within the valid board.
	 * @author Yuelin Liu
	 * @param  string String stands for direction and tile Tile stands for the targetTile.
	 * @return Tile that is not at the edge of Tile
	 *
	 */
	public static Tile getNonEdgeTile(String string, Tile tile)
	{	
		//TODO: Write logic
    throw new UnsupportedOperationException();
  }
	
	/**
	 * Feature:MoveWall
	 * 
	 * I will check and make sure the wall candidate is at the edge of the board and make sure it wont't go beyond.
	 * @author Yuelin Liu
	 * @param  string String stands for direction and tile Tile stands for the targetTile.
	 * @return Tile that is at the edge of Tile
	 *
	 */
	public static Tile getEdgeTile(String string,Tile tile) {
		//TODO: Write logic
				throw new UnsupportedOperationException();
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
	 * @return boolean
	 */

	public static boolean FlipWall(WallMove wallmove) {	
		throw new UnsupportedOperationException();
	}

	/**
	 * Feature: GrabWall
	 * 
	 * After this method, these two actions should be done
	 * A wall move candidate shall be created at initial position
	 * I shall have a wall in my hand over the board
	 * The wall in my hand shall disappear from my stock
	 * 
	 * @param Wall
	 * @author Yujing Yang
	 * @return boolean
	 */
	
	public static boolean GrabWall(Wall GrabbedWall) {	
		throw new UnsupportedOperationException();
	}

	
	
	
	
	
	
	
	
	/**
	 * 
     * Feature:SavePosition
     *
	 * @author Bozhong Lu
	 * Checks that file with specified name exists in my folder
	 * @param filename
	 * @return whether the file with the specified name exists
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
			if(wallDirection == Direction.valueOf("VERTICAL")) {
				wallDirectionToWrite = "v";
			}else if(wallDirection == Direction.valueOf("HORIZONTAL")){
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
			if(wallDirection == Direction.valueOf("VERTICAL")) {
				wallDirectionToWrite = "v";
			}else if(wallDirection == Direction.valueOf("HORIZONTAL")){
				wallDirectionToWrite = "h";
			}
			whiteWallPositionToWrite = whiteWallPositionToWrite + wallColumnToWrite + wallRowToWrite + wallDirectionToWrite ;
			writer.write(whiteWallPositionToWrite);
			
		}
	}

	/**
	 * Feature:SavePosition
	 * 
	 * @author Bozhong Lu
	 * @param filename
	 * @return String: the content of the new file;
	 */
	// 1. Compute the string to be saved
	// 2. Save to file 
	// 3. Return the computed string 
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
	/**
	 * Feature:SavePosition
	 * 
	 * @author Bozhong Lu
	 * @param none
	 * @return void
	 */
	public static void overwriteExistingFile() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Feature:SavePosition
	 * 
	 * @author Bozhong Lu
	 * @param none
	 * @return void
	 */
	public static void cancelOverwriteExistingFile() {
		throw new UnsupportedOperationException();
	}
	
//	/**
//	 * Feature:ValidatePosition
//	 * 
//	 * @author Bozhong Lu
//	 * @param none
//	 * @return boolean
//	 */
//	public static boolean validatePosition() {
//		throw new UnsupportedOperationException();
//	}
	
	/**
	 * Feature:SavePosition
	 * 
	 * @author Bozhong Lu
	 * @param filename
	 * @return boolean
	 */
	public static boolean fileIsUpdated(String filename) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Feature:SavePosition
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
	 * Feature:laod game
	 * This method load a game by input a filename
	 * 
	 * @author Zirui He
	 * @param filename 
	 * @throws FileNotFoundException 
	 */
	public static boolean loadGame(String filename) throws FileNotFoundException{
		
		//start game
		ArrayList<Player> createUsersAndPlayers = createUsersAndPlayers("user1", "user2");
		createAndStartGame(createUsersAndPlayers);
		
		Quoridor quoridor = QuoridorApplication.getQuoridor();
		FileInputStream inputstream = new FileInputStream(filename);
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(inputstream);

		for (int i = 0; i < 2; i++) {	// i=0 and i=1 represent first two lines in the file
			String move = scanner.nextLine();	//move store one line of data
			String delims = "[ :,]+";	
			String[] split = move.split(delims);	//parse the string into smaller strings
			
			if (split[0].equals("B")) {		//if this line store black player's data
				Player blackplayer = quoridor.getCurrentGame().getBlackPlayer();
				int blackWallIndex = 0;
				for (int j = 1; j < split.length; j++) {	//start from the second argument in the string and loop to the end
					int moveNumber = i;
					int roundNumber = j;
					Tile tile = null;
					String[] s = split[j].split("");	//split string by each character
					try {
						tile = quoridor.getBoard().getTile((Integer.parseInt(s[1]) - 1) * 9 + columnNum(s[0]) - 1);
					} catch(Exception e) {
						return false;
					}
					//Tile tile = new Tile(rowNum(s[0]), Integer.parseInt(s[1]), quoridor.getBoard());
					if (s.length == 2) {	//check if is pawn move
						PlayerPosition blackposition = new PlayerPosition(blackplayer, tile);
						quoridor.getCurrentGame().getCurrentPosition().setBlackPosition(blackposition);
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
						Wall wall = blackplayer.getWall(blackWallIndex);
						blackWallIndex++;
						new WallMove(moveNumber, roundNumber, blackplayer, tile, quoridor.getCurrentGame(), direction, wall); 	//put wall on the board
						quoridor.getCurrentGame().getCurrentPosition().addBlackWallsOnBoard(wall);
						//quoridor.getCurrentGame().getCurrentPosition().getBlackWallsInStock().remove(wall);	//remove wall from the stack
						quoridor.getCurrentGame().getCurrentPosition().removeBlackWallsInStock(wall);
					}
							
				}
				QuoridorController.completeMove(blackplayer);
				
			}
			
			if (split[0].equals("W")) {
				Player whiteplayer = quoridor.getCurrentGame().getWhitePlayer();
				int whiteWallIndex = 0;
				for (int j = 1; j < split.length; j++) {
					int moveNumber = i;
					int roundNumber = j;
					Tile tile = null;
					String[] s = split[j].split("");
					try {
						tile = quoridor.getBoard().getTile((Integer.parseInt(s[1]) - 1) * 9 + columnNum(s[0]) - 1);
					} catch(Exception e) {
						return false;
					}
					//Tile tile = new Tile(rowNum(s[0]), Integer.parseInt(s[1]), quoridor.getBoard());
					if (s.length == 2) {
						PlayerPosition whiteposition = new PlayerPosition(whiteplayer, tile);
						quoridor.getCurrentGame().getCurrentPosition().setWhitePosition(whiteposition);
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
						Wall wall = whiteplayer.getWall(1);
						whiteWallIndex++;
						new WallMove(moveNumber, roundNumber, whiteplayer, tile, quoridor.getCurrentGame(), direction, wall);
						quoridor.getCurrentGame().getCurrentPosition().addWhiteWallsOnBoard(wall);
						quoridor.getCurrentGame().getCurrentPosition().removeWhiteWallsInStock(wall);
					}
							
				}
				QuoridorController.completeMove(whiteplayer);
				
			}
		}
		return true;

	}
	
	
	/**
	 * Feature:load game, ValidatePosition
	 * This method validate if all the pawn and wall position at board 
	 * is with the board boundary 
	 * 
	 * @author Bozhong Lu, Yuelin Liu
	 * @return boolean
	 */
	public static boolean validatePosition() {
		//TO-DO: Write logic to validate position
				boolean isValid = true;
				Quoridor quoridor = QuoridorApplication.getQuoridor();
				//Validate WallMove 
				List<Wall> blackWalls = quoridor.getCurrentGame().getCurrentPosition().getBlackWallsOnBoard();
				List<Wall> whiteWalls = quoridor.getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard();
				List<Wall> wallList = new ArrayList<Wall>();
				wallList.addAll(blackWalls);
				wallList.addAll(whiteWalls);
				
				for (int i = 0; i < wallList.size(); i++) {
					int thisWallColumn = wallList.get(i).getMove().getTargetTile().getColumn();
					int thisWallRow = wallList.get(i).getMove().getTargetTile().getRow();
					Direction thisWallDirection = wallList.get(i).getMove().getWallDirection();
					if (thisWallColumn < 1 || thisWallRow < 1 || thisWallColumn > 9 || thisWallRow > 9 ) {
						isValid = false;
						break;
					}
					if (i == (wallList.size() - 1)) break;

					for (int j = i + 1; j < wallList.size(); j++) {
						int nextWallColumn = wallList.get(j).getMove().getTargetTile().getColumn();
						int nextWallRow = wallList.get(j).getMove().getTargetTile().getRow();
						Direction nextWallDirection = wallList.get(j).getMove().getWallDirection();
						if (nextWallColumn < 1 || nextWallRow < 1 || nextWallColumn > 9 || nextWallRow > 9 ) {
							isValid = false;
							break;
						}
						
						if (thisWallDirection == Direction.Vertical) {
							if(nextWallDirection == Direction.Vertical) {
								if((nextWallColumn == thisWallColumn)&&((nextWallRow == thisWallRow+1)||(nextWallRow == thisWallRow-1)||(nextWallRow == thisWallRow))) {
									isValid = false;
								}
							}else {
								if((nextWallColumn == thisWallColumn)&&(nextWallRow == thisWallRow)) {
									isValid = false;
								}
							}
						}
						else {
							if(nextWallDirection == Direction.Vertical) {
								if((thisWallColumn == nextWallColumn)&&(thisWallRow == nextWallRow)) {
									isValid = false;
								}
							}else {
								if ((thisWallRow == nextWallRow)&&((thisWallColumn == nextWallColumn-1)||(thisWallColumn == nextWallColumn+1)||(thisWallColumn == nextWallColumn))) {
									isValid = false;
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
					isValid = false;
				}
				
				return isValid;

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
	 */
	public static void completeMove(Player player) {

		Player white = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
		Player black = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
		if (player.hasGameAsBlack()) {
			player.setNextPlayer(white);
			QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().setPlayerToMove(white);
		}else {
			player.setNextPlayer(black);
			QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().setPlayerToMove(black);
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
		//TO-DO: Write logic to load game
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
		//TO-DO: Write logic to load game
		Long endTime = System.nanoTime();
		return endTime;
	}
	
	/**
	 * Feature: switch player
	 * This method test if the clock of the player is still running
	 * 
	 * @author Zirui He & Gengyi
	 * @param player
	 * @return boolean
	 * Feature: switch player & InitialzeBoard
	 */
	public static boolean clockIsRunning(Player player) {
		//TO-DO: Write logic to load game
		throw new UnsupportedOperationException();
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
		//TODO GUI
		Board board = new Board(QuoridorApplication.getQuoridor());
		Player whitePlayer = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
		Player blackPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
		initializeWhitePawn(whitePlayer,board);
		initializeBlackPawn(blackPlayer, board);
		initializeWhiteWall(whitePlayer);
		initializeBlackWall(blackPlayer);
		QuoridorApplication.getQuoridor().setBoard(board);
	}

	/**
	 * Feature: InitialzeBoard
	 * This static method set the white player's pawn to initial location(e9).
	 * It returns true if the initiating is successful.
	 * 
	 * @author Sun, Gengyi
	 * @param whitePlayer
	 * @return A flag indicating whether the method successfully launched.
	 */
	public static void initializeWhitePawn(Player whitePlayer, Board board) {
		//TODO GUI
		if(whitePlayer!= null && board != null) {
		Tile init = new Tile('e', 9, board);
		PlayerPosition initial = new PlayerPosition(whitePlayer, init);
		QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().setWhitePosition(initial);
		}
	}

	/**
	 * Feature: InitialzeBoard
	 * This static method sets the black player's pawn to initial position(e1).
	 * It returns true if the initiating is successful.
	 * 
	 * @author Sun, Gengyi
	 * @param blackPlayer
	 * @return A flag indicating whether the method successfully launched.
	 */
	public static void initializeBlackPawn(Player blackPlayer, Board board) {
		//TODO GUI
		if(blackPlayer!= null && board != null) {
			Tile init = new Tile('e', 0, board);
			PlayerPosition initial = new PlayerPosition(blackPlayer, init);
			QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().setWhitePosition(initial);
			}
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
	public static boolean initializeBlackWall(Player blackPlayer) {
		//TODO GUI
		if(!blackPlayer.hasWalls()) {
			blackPlayer.addWall(10);
			return true;
		}
		else return false;
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
	public static boolean initializeWhiteWall(Player whitePlayer) {
		//TODO GUI

		if(!whitePlayer.hasWalls()) {
			whitePlayer.addWall(10);
			return true;
		}	
		else return false;
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
	public static boolean setUserName(Player player, String name) {
		if (player == null) {
			throw new IllegalArgumentException("Player is invalid");
		}
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Invalid name");
		}
		User u = User.getWithName(name);
		if (u == null) {
			u = new User(name, QuoridorApplication.getQuoridor());
		}
		return player.setUser(u);
	}
	
	/**
	 * This is a static method which takes a player. Then it will allows the player to select
	 * his/her user name. It will return a boolean value to indicate if the name is updated 
	 * successfully.
	 * 
	 * @author Pengnan Fan
	 * @param game The game of the specific white player
	 * @param name The new name 
	 * @return A boolean value to indicate if the name of the user has been updated
	 */
	public static boolean selectUserName(Player player) {
		//TODO: Need support from front end
		if (player == null) {
			throw new IllegalArgumentException("Player is invalid");
		}
		//Receive a name from front end
		return setUserName(player, "name");
		//throw new UnsupportedOperationException();
	}
	
//	/**
//	 * This is a static method which link an user u and a player p. It will return a boolean
//	 * value to suggest if it is successful.
//	 * 
//   * @author Pengnan Fan
//	 * @param u The user to link
//	 * @param p The player to link
//	 * @return A boolean value to suggest if it is successful.
//	 */
//	public static boolean linkUserAndPlayer(User u, Player p) {
//		//TODO: To be implemented
//		p.setUser(u);
//		throw new UnsupportedOperationException();
//	}
	
//	/**
//	 * This is a static method which takes two inputs, a game and a player. It will set
//	 * the player to be the next one to play of the game. It will return a boolean to
//	 * suggest it is successful.
//	 * 
//	 * @author Pengnan Fan
//	 * @param g The game to change the player
//	 * @param p The player to be set as the next player
//	 * @return A boolean variable to suggest if it is successful
//	 */
//	public static boolean setNextPlayer(Game g, Player p) {
//		//TODO: To be implemented
//		throw new UnsupportedOperationException();
//	}
	
//	/**
//	 * This is a static method which verify if a game is ready to start. If so, it 
//	 * will set the status as ReadyToStart. It will return a boolean value to suggest
//	 * if it is successful.
//	 * 
//	 * @author Pengnan Fan
//	 * @param g The game to be verified.
//	 * @return A boolean variable to suggest if it is successful
//	 */
//	public static boolean verifyNewGame() {
//		//TODO: To be implemented
//		Game g = QuoridorApplication.getQuoridor().getCurrentGame();
//		if (g == null) {
//			throw new IllegalArgumentException("There is no game");
//		}
//		return g.getGameStatus().equals(GameStatus.ReadyToStart);
//	}
	
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
	 
	 private static ArrayList<Player> createUsersAndPlayers(String userName1, String userName2) {
			Quoridor quoridor = QuoridorApplication.getQuoridor();
			User user1 = quoridor.addUser(userName1);
			User user2 = quoridor.addUser(userName2);

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

		private static void createAndStartGame(ArrayList<Player> players) {
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
	 
	 
}
