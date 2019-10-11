package ca.mcgill.ecse223.quoridor.controller;

import ca.mcgill.ecse223.quoridor.model.*;
import static ca.mcgill.ecse223.quoridor.model.Game.GameStatus;

public class QuoridorController{
	
	//@Given("No file save_game_test.dat exists in the filesystem")
	//To Do : Check no file save_game_test.dat exists in the filesystem
	public static boolean checkFileNotExist(String filename) {
		throw new UnsupportedOperationException();
	}
	
	//@When("The user initiates to save the game with name save_game_test.dat")
	//To Do : The user initiates to save the game with name save_game_test.dat
	public static void initiateSaveGame() {
		throw new UnsupportedOperationException();
	}
	
	//@Then("A file with save_game_test.dat is created in the filesystem")
	//To Do : Create a file with name save_game_test.dat in the filesystem
	public static void createFile() {
		throw new UnsupportedOperationException();
	}
	
	//@Given("File save_game_test.dat exists in the filesystem")
	//To Do : Check file save_game_test.dat exists in the filesystem
	public static boolean checkFileExist(String filename) {
		throw new UnsupportedOperationException();
	}
	
	//@When("The user confirms to overwrite existing file")
	//To Do : The user confirms to overwrite existing file
	public static void confirmOverwriteExistingFile() {
		throw new UnsupportedOperationException();
	}
	
	//@Then("File with save_game_test.dat is updated in the filesystem")
	//To Do : Update file with save_game_test.dat in the filesystem
	public static void updateFile(String filename) {
		throw new UnsupportedOperationException();
	}
	
	//@When("The user cancels to overwrite existing file")
	//To Do : The user cancels to overwrite existing file
	public static void cancelOverwriteExistingFile() {
		throw new UnsupportedOperationException();
	}
	
	//@Then("File save_game_test.dat is not changed in the filesystem")
	//To Do : Report file save_game_test.dat is not changed in the filesystem
	public static void reportFileNotChanged(String filename) {
		throw new UnsupportedOperationException();
	}
	
	//@Given("A game position is supplied with pawn coordinate {int}:{int}")
	//To Do : Check a game position is supplied with pawn coordinate {int}:{int}
	public static boolean checkPositionHasPawnCoordinate(GamePosition pos) {
		throw new UnsupportedOperationException();
	}
	
	//@When("Validation of the position is initiated")
	//To Do : initiate validation of the position
	public static void intiateValidationPosition() {
		throw new UnsupportedOperationException();
	}
	
	//@Then("The position is ok")
	//To Do : Report the position is ok
	public static void reportPositionIsOK(GamePosition pos) {
		throw new UnsupportedOperationException();
	}
	
	//@Then("The position is error")
	//To Do : Report the position is error
	public static void reportPositionIsError(GamePosition pos) {
		throw new UnsupportedOperationException();
	}
	
	//@Given("A game position is supplied with wall coordinate {int}:{int}-horizontal")
	//To Do : Check a game position is supplied with wall coordinate {int}:{int}-horizontal
	public static boolean checkPositionHasWallCoordinateH(GamePosition pos) {
		throw new UnsupportedOperationException();
	}
	
	//@Given("A game position is supplied with wall coordinate {int}:{int}-vertical")
	//To Do : Check a game position is supplied with wall coordinate {int}:{int}-vertical
	public static boolean checkPositionHasWallCoordinateV(GamePosition pos) {
		throw new UnsupportedOperationException();
	}
	
	//@Then("The position is valid")
	//To Do : Report the position is valid
	public static void reportPositionIsValid(GamePosition pos) {
		throw new UnsupportedOperationException();
	}
	
	//@Then("The position is invalid")
	//To Do : Report the position is invalid 
	public static void reportPositionIsInValid(GamePosition pos) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * This is a static method which takes two User parameters to initialize a new game. 
	 * It will return a boolean value to indicate if a new game is successfully initialized.
	 * 
	 * @author Pengnan Fan
	 * @param user1 The first player who will join in the game
	 * @param user2 The second player who will join in the game
	 * @param thinking_time_seconds Thinking time in the unit of second
	 * @return A boolean value to indicate if a new game is initialized successfully
	 * 
	 */
	public static boolean initializeNewGame(User user1, User user2, int thinking_time_seconds) {
		//TODO: To be implemented
		throw new UnsupportedOperationException();
	}

	/**
	 * This is a static method which takes an user and a name, then set the latter as 
	 * the new name of the former. It will return a boolean value to indicate if the name
	 * is updated successfully.
	 * 
	 * @author Pengnan Fan
	 * @param aUser The player who want to select a new name
	 * @param name The new name 
	 * @return A boolean value to indicate if the name of the user has been updated
	 */
	public static boolean updateNewUserName(User aUser, String name) {
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
	
}
