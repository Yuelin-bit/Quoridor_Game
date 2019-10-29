package ca.mcgill.ecse223.quoridor.controller;

import ca.mcgill.ecse223.quoridor.QuoridorApplication;
import ca.mcgill.ecse223.quoridor.model.*;
import ca.mcgill.ecse223.quoridor.model.Game.GameStatus;

public class QuoridorController {
	
	
	/**
	 * Feature:DropWall
	 * 
     * @author Yuelin Liu
	 * @param wallmove WallMove candidate to be check whether it is valid and set it.
	 * @return boolean
	 * @exception UnsupportedOperationException
	 *
	 */
	public static boolean verifyWallMoveValid(WallMove wallmove) {
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
						return false;
					}
				}
				if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard().get(i).getMove().getWallDirection()==Direction.Horizontal) {
					if((newColumn==currentColumn)&&(currentRow==newRow)) {
						return false;
					}
				}	
			}
			for(int i=0; i<sizeW; i++) {
				int newRow = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard().get(i).getMove().getTargetTile().getRow();
				int newColumn = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard().get(i).getMove().getTargetTile().getColumn();
				if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard().get(i).getMove().getWallDirection()==Direction.Vertical) {
					if((newColumn==currentColumn)&&(newRow<=currentRow+1)&&(newRow>=currentRow-1)) {
						return false;
					}
				}
				if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard().get(i).getMove().getWallDirection()==Direction.Horizontal) {
					if((newColumn==currentColumn)&&(currentRow==newRow)) {
						return false;
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
						return false;
					}
				}
				if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard().get(i).getMove().getWallDirection()==Direction.Vertical) {
					if((newColumn==currentColumn)&&(currentRow==newRow)) {
						return false;
					}
				}	
			}
			for(int i=0; i<sizeW; i++) {
				int newRow = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard().get(i).getMove().getTargetTile().getRow();
				int newColumn = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard().get(i).getMove().getTargetTile().getColumn();
				if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard().get(i).getMove().getWallDirection()==Direction.Horizontal) {
					if((newRow==currentRow)&&(newColumn<=currentColumn+1)&&(newColumn>=currentColumn-1)) {
						return false;
					}
				}
				if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard().get(i).getMove().getWallDirection()==Direction.Vertical) {
					if((newColumn==currentColumn)&&(currentRow==newRow)) {
						return false;
					}
				}	
			}
			
		}
		return false;
	}
	
	
	/**
	 * Feature:DropWall
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
			QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().addBlackWallsOnBoard(wallmove.getWallPlaced());
		}
		
		if(currentPlayer.hasGameAsWhite()) {
			QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().addWhiteWallsOnBoard(wallmove.getWallPlaced());
		}
		
	
	}
	
	
	/**
	 * Feature:MoveWall
	 * 
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
		throw new UnsupportedOperationException();
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
	public static String saveGame(Game game , String filename){
		throw new UnsupportedOperationException() ;
	    
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
	 */
	public static void creatNewFile(String filename) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Feature:SavePosition
	 * 
	 * @author Bozhong Lu
	 * @param String filename
	 * @return none
	 */
	public static void deleteFile(String filename) {
		throw new UnsupportedOperationException();
	}
	
	
	
	
	
	
	
	
	
	
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
	 * Feature:laod game, ValidatePosition
	 * This method validate if all the pawn and wall position at board 
	 * is with the board boundary 
	 * 
	 * @author Zirui He, Bozhong Lu
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
	 * Feature: switch player & InitialzeBoard
	 * This method test if the clock of the player is still running.
	 * It returns true if the clock is counting down.
	 * 
	 * @author Zirui He & Gengyi
	 * @param player
	 * @return A flag indicating whether the method successfully launched.
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
	 * @param min
	 * @param sec
	 * @return A flag indicating whether the method successfully launched.
	 */
	public static boolean setTotalThinkingTime(Integer min, Integer sec) {
		throw new UnsupportedOperationException();
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
	public static boolean initializeBoard() {
		throw new UnsupportedOperationException();
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
	public static boolean initializeWhitePawn(Player whitePlayer) {
		throw new UnsupportedOperationException();
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
	public static boolean initializedBlackPawn(Player blackPlayer) {
		throw new UnsupportedOperationException();
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
		throw new UnsupportedOperationException();
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
			throw new UnsupportedOperationException();
	}
	
	/**
	 * This method verify if the total thinking time of a give game is correctly 
	 * set. It will return a boolean variable to suggest if it is valid. 
	 * 
	 * @author Sun, Gengyi
	 * @param g
	 * @return A flag indicating whether the thinking time is valid.
	 */
	public static boolean verifyTotalThinkingTime(Game g) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * This is a static method which takes two User parameters to initialize a new game. 
	 * It will return a boolean value to indicate if a new game is successfully initialized.
	 * 
	 * @author Pengnan Fan
	 * @param user1 The first player who will join in the game
	 * @param user2 The second player who will join in the game
	 * @return A boolean value to indicate if a new game is initialized successfully
	 * 
	 */
	public static boolean initializeNewGame(User user1, User user2) {
		//TODO: To be implemented
		throw new UnsupportedOperationException();
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
		//TODO: To be implemented
		throw new UnsupportedOperationException();
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
	 * This is a static method which link an user u and a player p. It will return a boolean
	 * value to suggest if it is successful.
	 * 
   * @author Pengnan Fan
	 * @param u The user to link
	 * @param p The player to link
	 * @return A boolean value to suggest if it is successful.
	 */
	public static boolean linkUserAndPlayer(User u, Player p) {
		//TODO: To be implemented
		throw new UnsupportedOperationException();
	}
	
	/**
	 * This is a static method which takes two inputs, a game and a player. It will set
	 * the player to be the next one to play of the game. It will return a boolean to
	 * suggest it is successful.
	 * 
	 * @author Pengnan Fan
	 * @param g The game to change the player
	 * @param p The player to be set as the next player
	 * @return A boolean variable to suggest if it is successful
	 */
	public static boolean setNextPlayer(Game g, Player p) {
		//TODO: To be implemented
		throw new UnsupportedOperationException();
	}
	
	/**
	 * This is a static method which verify if a game is ready to start. If so, it 
	 * will set the status as ReadyToStart. It will return a boolean value to suggest
	 * if it is successful.
	 * 
	 * @author Pengnan Fan
	 * @param g The game to be verified.
	 * @return A boolean variable to suggest if it is successful
	 */
	public static boolean verifyNewGame(Game g) {
		//TODO: To be implemented
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
