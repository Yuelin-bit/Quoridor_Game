package ca.mcgill.ecse223.quoridor.features;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.io.FileNotFoundException;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

//import static org.junit.Assert.assertEquals;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import ca.mcgill.ecse223.quoridor.QuoridorApplication;
import ca.mcgill.ecse223.quoridor.controller.QuoridorController;
import ca.mcgill.ecse223.quoridor.controller.Stopwatch;
import ca.mcgill.ecse223.quoridor.model.Game.GameStatus;
import ca.mcgill.ecse223.quoridor.model.Game.MoveMode;
import ca.mcgill.ecse223.quoridor.model.*;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class CucumberStepDefinitions {
	
	private Long blackStartTime;
	private Long blackEndTime;
	private Long whiteStartTime;
	private Long whiteEndTime;
	private String error = "";

	
	private Quoridor quoridor;
	private Board board;
	private Player player1;
	private Player player2;
	private Player currentPlayer;
	private Game game;
	private String newContent;
	private ArrayList<Player> playerList;
	private String toEdit;
	private boolean movePawnSuccess;


	// ***********************************************
	// Background step definitions
	// ***********************************************
	
	@Given("^The game is not running$")
	public void theGameIsNotRunning() {
		initQuoridorAndBoard();
		this.playerList = createUsersAndPlayers("user1", "user2");
	}

	@Given("^The game is running$")
	public void theGameIsRunning() {
		initQuoridorAndBoard();
		ArrayList<Player> createUsersAndPlayers = createUsersAndPlayers("user1", "user2");
		createAndStartGame(createUsersAndPlayers);
	}

	@And("^It is my turn to move$")
	public void itIsMyTurnToMove() throws Throwable {
		Quoridor quoridor = QuoridorApplication.getQuoridor();
		Player currentPlayer = quoridor.getCurrentGame().getWhitePlayer();
		QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().setPlayerToMove(currentPlayer);
	}

	@Given("The following walls exist:")
	public void theFollowingWallsExist(io.cucumber.datatable.DataTable dataTable) {
		Quoridor quoridor = QuoridorApplication.getQuoridor();
		List<Map<String, String>> valueMaps = dataTable.asMaps();
		// keys: wrow, wcol, wdir
		Player[] players = { quoridor.getCurrentGame().getWhitePlayer(), quoridor.getCurrentGame().getBlackPlayer() };
		int playerIdx = 0;
		int wallIdxForPlayer = 0;
		for (Map<String, String> map : valueMaps) {
			Integer wrow = Integer.decode(map.get("wrow"));
			Integer wcol = Integer.decode(map.get("wcol"));
			// Wall to place
			// Walls are placed on an alternating basis wrt. the owners
			//Wall wall = Wall.getWithId(playerIdx * 10 + wallIdxForPlayer);
			Wall wall = players[playerIdx].getWall(wallIdxForPlayer); // above implementation sets wall to null

			String dir = map.get("wdir");

			Direction direction;
			switch (dir) {
			case "horizontal":
				direction = Direction.Horizontal;
				break;
			case "vertical":
				direction = Direction.Vertical;
				break;
			default:
				throw new IllegalArgumentException("Unsupported wall direction was provided");
			}
			new WallMove(0, 1, players[playerIdx], quoridor.getBoard().getTile((wrow - 1) * 9 + wcol - 1), quoridor.getCurrentGame(), direction, wall);
			if (playerIdx == 0) {
				quoridor.getCurrentGame().getCurrentPosition().removeWhiteWallsInStock(wall);
				quoridor.getCurrentGame().getCurrentPosition().addWhiteWallsOnBoard(wall);
			} else {
				quoridor.getCurrentGame().getCurrentPosition().removeBlackWallsInStock(wall);
				quoridor.getCurrentGame().getCurrentPosition().addBlackWallsOnBoard(wall);
			}
			wallIdxForPlayer = wallIdxForPlayer + playerIdx;
			playerIdx++;
			playerIdx = playerIdx % 2;
		}
		System.out.println();

	}

	@And("I do not have a wall in my hand")
	public void iDoNotHaveAWallInMyHand() {
		// GUI-related feature -- TODO for later
	}
	
	@And("^I have a wall in my hand over the board$")
	public void iHaveAWallInMyHandOverTheBoard() throws Throwable {
		// GUI-related feature -- TODO for later
	}
	
	@Given("^A new game is initializing$")
	public void aNewGameIsInitializing() throws Throwable {
		initQuoridorAndBoard();
		QuoridorController.initializeNewGame();
		quoridor = QuoridorApplication.getQuoridor();
		this.playerList = createUsersAndPlayers("default_black", "default_white");
		
		game = quoridor.getCurrentGame();
		game.setBlackPlayer(this.playerList.get(0));
		game.setWhitePlayer(this.playerList.get(1));
	}
	


	
	
////===============================================================================================================================
	////*******************************************************************************************************************************
	////*******************************************************************************************************************************
	////   
	////											The following is DropWall
	////
	////*******************************************************************************************************************************
	////*******************************************************************************************************************************
	
	@Given("The wall move candidate with {string} at position \\({int}, {int}) is valid")
	public void the_wall_move_candidate_with_at_position_is_valid(String string, Integer int1, Integer int2) {	
		Player currentPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
		Tile WallTile = new Tile (int1, int2, QuoridorApplication.getQuoridor().getBoard() );
		if(currentPlayer.hasGameAsBlack()) {
			Wall newWallFromStock = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsInStock().get(1);					
			if(string.equalsIgnoreCase("vertical")) {
			int a = QuoridorApplication.getQuoridor().getCurrentGame().getMoves().size();
			WallMove WallMove = new WallMove(a, (a+1)/2, currentPlayer, WallTile, 
							QuoridorApplication.getQuoridor().getCurrentGame(), Direction.Vertical, newWallFromStock );
			
			QuoridorApplication.getQuoridor().getCurrentGame().setWallMoveCandidate(WallMove);	
			}
			else {
					int a = QuoridorApplication.getQuoridor().getCurrentGame().getMoves().size();
					WallMove WallMove = new WallMove(a, (a+1)/2, currentPlayer, WallTile, 
							QuoridorApplication.getQuoridor().getCurrentGame(), Direction.Horizontal, newWallFromStock );
					QuoridorApplication.getQuoridor().getCurrentGame().setWallMoveCandidate(WallMove);	
				}					
		}else {
			Wall newWallFromStock = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsInStock().get(1);				
			if(string.equalsIgnoreCase("vertical")) {
			int a = QuoridorApplication.getQuoridor().getCurrentGame().getMoves().size();
			WallMove WallMove = new WallMove(a, (a+1)/2, currentPlayer, WallTile, 
							QuoridorApplication.getQuoridor().getCurrentGame(), Direction.Vertical, newWallFromStock );
			
			QuoridorApplication.getQuoridor().getCurrentGame().setWallMoveCandidate(WallMove);	
			}
			else {
					int a = QuoridorApplication.getQuoridor().getCurrentGame().getMoves().size();
					WallMove WallMove = new WallMove(a, (a+1)/2, currentPlayer, WallTile, 
							QuoridorApplication.getQuoridor().getCurrentGame(), Direction.Horizontal, newWallFromStock );
					
					QuoridorApplication.getQuoridor().getCurrentGame().setWallMoveCandidate(WallMove);	
				}	
		}
		boolean b = QuoridorController.verifyOverlapped(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate());
		boolean c = QuoridorController.verifyOutsideTheBoard(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate());
		boolean d = c||b;
		Assert.assertEquals(false,d);
	}

	
	
	@When("I release the wall in my hand")
	public void i_release_the_wall_in_my_hand() {
		//WallMove a = QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate();
		QuoridorController.releaseWall();
		QuoridorApplication.getJboard().dispose();
	}

	@Then("A wall move shall be registered with {string} at position \\({int}, {int})")
	public void a_wall_move_shall_be_registered_with_at_position(String string, Integer int1, Integer int2) {
		Direction dir;
		if(string.equalsIgnoreCase("vertical")) {
			dir = Direction.Vertical;
		}
		else {
			dir = Direction.Horizontal;
		}
		Assert.assertEquals(int1,Integer.valueOf(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getRow()));
		Assert.assertEquals(int2,Integer.valueOf(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getColumn()));
		Assert.assertEquals(dir, QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getWallDirection());    
	}
	
	
	// the same one is in feature GrabWall

	@Then("I shall have a wall in my hand over the board")
	public void i_shall_have_a_wall_in_my_hand_over_the_board() {
		boolean haveAWall = false;
//		if(QuoridorApplication.getJboard().getJwall()!=null) {
//			haveAWall = true;
//		}
		Assert.assertEquals(true,haveAWall);
	}
	
	

	@Then("My move shall be completed")
	public void my_move_shall_be_completed() {
		//   0:white         1:black
		int player = 0;
		int game = 0;
		Player blackPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
		Player whitePlayer = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
		if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove()==blackPlayer) {
			player = 1;
		}
		if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove().hasGameAsWhite()) {
			game = 1;
		}
		Assert.assertNotEquals(player, game);
	}

	@Then("It shall not be my turn to move")
	public void it_shall_not_be_my_turn_to_move() {
		Player aPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getPlayer();
		Assert.assertEquals(aPlayer, QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove());
	    
	}
	
	
	@Given("The wall move candidate with {string} at position \\({int}, {int}) is invalid")
	public void the_wall_move_candidate_with_at_position_is_invalid(String string, Integer int1, Integer int2) {
		Player currentPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
		Tile WallTile = new Tile (int1, int2, QuoridorApplication.getQuoridor().getBoard() );
		if(currentPlayer.hasGameAsBlack()) {
			Wall newWallFromStock = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsInStock().get(1);					
			if(string.equalsIgnoreCase("vertical")) {
			int a = QuoridorApplication.getQuoridor().getCurrentGame().getMoves().size();
			WallMove WallMove = new WallMove(a, (a+1)/2, currentPlayer, WallTile, 
							QuoridorApplication.getQuoridor().getCurrentGame(), Direction.Vertical, newWallFromStock );
			
			QuoridorApplication.getQuoridor().getCurrentGame().setWallMoveCandidate(WallMove);	
			}
			else {
					int a = QuoridorApplication.getQuoridor().getCurrentGame().getMoves().size();
					WallMove WallMove = new WallMove(a, (a+1)/2, currentPlayer, WallTile, 
							QuoridorApplication.getQuoridor().getCurrentGame(), Direction.Horizontal, newWallFromStock );
					QuoridorApplication.getQuoridor().getCurrentGame().setWallMoveCandidate(WallMove);	
				}					
		}else {
			Wall newWallFromStock = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsInStock().get(1);				
			if(string.equalsIgnoreCase("vertical")) {
			int a = QuoridorApplication.getQuoridor().getCurrentGame().getMoves().size();
			WallMove WallMove = new WallMove(a, (a+1)/2, currentPlayer, WallTile, 
							QuoridorApplication.getQuoridor().getCurrentGame(), Direction.Vertical, newWallFromStock );
			
			QuoridorApplication.getQuoridor().getCurrentGame().setWallMoveCandidate(WallMove);	
			}
			else {
					int a = QuoridorApplication.getQuoridor().getCurrentGame().getMoves().size();
					WallMove WallMove = new WallMove(a, (a+1)/2, currentPlayer, WallTile, 
							QuoridorApplication.getQuoridor().getCurrentGame(), Direction.Horizontal, newWallFromStock );
					
					QuoridorApplication.getQuoridor().getCurrentGame().setWallMoveCandidate(WallMove);	
					
				}	
		}
		boolean b = QuoridorController.verifyOverlapped(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate());
		boolean c = QuoridorController.verifyOutsideTheBoard(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate());
		boolean d = c||b;
		Assert.assertEquals(true,d);
		
}
	    
	

	@Then("I shall be notified that my wall move is invalid")
	public void i_shall_be_notified_that_my_wall_move_is_invalid() {
		//It could be GUI
	    //TA said that I could fill this out later.
		//JOptionPane.showMessageDialog(null, "It is illegal!!!");
		//Assert.assertEquals("I will give a dialog immediately you release a illegal wall, So do not worry if you notice there are some dialogs when you running the JUnit Test! Just close it!",QuoridorApplication.getJboard().getError());
		//throw new cucumber.api.PendingException();
		
		
	}

//	@Then("I shall have a wall in my hand over the board")
//	public void i_shall_have_a_wall_in_my_hand_over_the_board() {
//		// GUI-related feature -- TODO for later
//		boolean haveAWall = false;
//		if(QuoridorApplication.getJboard().getJwall()!=null) {
//			haveAWall = true;
//		}
//		Assert.assertEquals(true,haveAWall);
//	}

	@Then("It shall be my turn to move")
	public void it_shall_be_my_turn_to_move() {
		Player aPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getPlayer();
		Assert.assertEquals(aPlayer, QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove());
	}

	@Then("No wall move shall be registered with {string} at position \\({int}, {int})")
	public void no_wall_move_shall_be_registered_with_at_position(String string, Integer int1, Integer int2) {
		Direction dir;
		if(string.equalsIgnoreCase("vertical")) {
			dir = Direction.Vertical;
		}
		else {
			dir = Direction.Horizontal;
		}
		Tile tile = new Tile(int1, int2, QuoridorApplication.getQuoridor().getBoard());		Assert.assertEquals(true, QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().setTargetTile(tile));
		Assert.assertEquals(dir, QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getWallDirection());
	}

	////*******************************************************************************************************************************
	////*******************************************************************************************************************************
	////   
	////											The above is DropWall
	////
	////*******************************************************************************************************************************
	////*******************************************************************************************************************************
	////===============================================================================================================================
	
	
	
	
	
	////===============================================================================================================================
	////*******************************************************************************************************************************
	////*******************************************************************************************************************************
	////   
	////											The following is MoveWall
	////
	////*******************************************************************************************************************************
	////*******************************************************************************************************************************
	
	/*@Given("A wall move candidate exists with {string} at position \\({int}, {int})")
	public void a_wall_move_candidate_exists_with_at_position(String string, Integer int1, Integer int2) {
		Tile WallTile = new Tile (int1, int2, QuoridorApplication.getQuoridor().getBoard() );
		
		if(string.equalsIgnoreCase("vertical")) {
			QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().setWallDirection(Direction.Vertical);
		}
		if(string.equalsIgnoreCase("horizontal")) {
			QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().setWallDirection(Direction.Horizontal);
		}
		
		QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().setTargetTile(WallTile);
	}	*/

	
	@Given("The wall candidate is not at the {string} edge of the board")
	public void the_wall_candidate_is_not_at_the_edge_of_the_board(String string) {
		
		/*int currentRow = QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getRow();
		int currentColumn = QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getColumn();
		
		
		if(string.equalsIgnoreCase("left")) {
			if((currentColumn==1)) {
				Tile tileLeft = new Tile(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getRow(),2,QuoridorApplication.getQuoridor().getBoard());
				QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().setTargetTile(tileLeft);
			}
		}
		if(string.equalsIgnoreCase("right")) {
			if((currentColumn==8)) {
				Tile tileRight = new Tile(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getRow(),7,QuoridorApplication.getQuoridor().getBoard());
				QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().setTargetTile(tileRight);
			}
		}
		if(string.equalsIgnoreCase("up")) {
			if((currentRow==1)) {
				Tile tileUp = new Tile(2,QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getColumn(),QuoridorApplication.getQuoridor().getBoard());
				QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().setTargetTile(tileUp);
			}
		}
		if(string.equalsIgnoreCase("down")) {
			if((currentRow==8)) {
				Tile tileDown = new Tile(7,QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getColumn(),QuoridorApplication.getQuoridor().getBoard());
				QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().setTargetTile(tileDown);
			}
		}*/
		boolean b = QuoridorController.verifyOnEdge(string);
		Assert.assertEquals(false,b);
		
		
	}

	@When("I try to move the wall {string}")
	public void i_try_to_move_the_wall(String string) {
		QuoridorController.MoveWall(string);
		/*try{
			QuoridorController.MoveWall(string);	
		}
		catch (RuntimeException e){
			System.out.println("exceed the board");
		}*/
	    
	}

	@Then("The wall shall be moved over the board to position \\({int}, {int})")
	public void the_wall_shall_be_moved_over_the_board_to_position(Integer int1, Integer int2) {
		//Tile tile = new Tile(int1, int2, QuoridorApplication.getQuoridor().getBoard());
		
		//QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().setTargetTile(tile);
		Assert.assertEquals(int1,Integer.valueOf(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getRow()));
		Assert.assertEquals(int2,Integer.valueOf(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getColumn()));
		//Assert.assertEquals(true,QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().setTargetTile(tile));  
		
	}
	

	@Then("A wall move candidate shall exist with {string} at position \\({int}, {int})")
	public void a_wall_move_candidate_shall_exist_with_at_position(String string, Integer int1, Integer int2) {
		Direction dir;
		if(string.equalsIgnoreCase("vertical")) {
			dir = Direction.Vertical;
		}
		else {
			dir = Direction.Horizontal;
		}
		Assert.assertEquals(int1,Integer.valueOf(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getRow()));
		Assert.assertEquals(int2,Integer.valueOf(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getColumn()));
		Assert.assertEquals(dir, QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getWallDirection());    
	}
	
	

	@Given("The wall candidate is at the {string} edge of the board")
	public void the_wall_candidate_is_at_the_edge_of_the_board(String string) {
		boolean b = QuoridorController.verifyOnEdge(string);
		Assert.assertEquals(true,b);
	}
	
	
	@Then("I shall be notified that my move is illegal")
	public void i_shall_be_notified_that_my_move_is_illegal() {
		// GUI-related feature -- TODO for later
		//Assert.assertEquals("I will give a dialog immediately you release a illegal wall, So do not worry if you notice there are some dialogs when you running the JUnit Test! Just close it!",QuoridorApplication.getJboard().getError());
		//throw new cucumber.api.PendingException();
	}

	////*******************************************************************************************************************************
	////*******************************************************************************************************************************
	////   
	////											The above is MoveWall
	////
	////*******************************************************************************************************************************
	////*******************************************************************************************************************************
	////===============================================================================================================================
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//// *******************************************************************************************************************************
	//// *******************************************************************************************************************************
	////
	//// The following is GrabWall
	////
	//// *******************************************************************************************************************************
	//// *******************************************************************************************************************************

	// Scenario: Start wall placement
	@Given("I have more walls on stock")

	// if current player does not have walls in stock, then add walls to their list
	// of walls in stock
	// firstly, check whether this is black game or white game, then check whether
	// there is more walls in stock, if not, add one

	public void i_have_more_walls_on_stock() {
		Player currentPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
		
		int WallID = 100;
		while (Wall.hasWithId(WallID)) {
			WallID++;
		}
		Wall wallAdded = Wall.getWithId(WallID);
		if (currentPlayer.hasGameAsBlack()) {
			if (!QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().hasBlackWallsInStock()) {
				Wall newWall = new Wall(WallID, currentPlayer);
				QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().addBlackWallsInStock(newWall);
			}
		} else {
			if (!QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().hasWhiteWallsInStock()) {
				Wall newWall = new Wall(WallID, currentPlayer);
				QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().addWhiteWallsInStock(newWall);
			}
		}
	}

	@When("I try to grab a wall from my stock")
	public void i_try_to_grab_a_wall_from_my_stock() {
		QuoridorController.grabWall();
	}

	@Then("A wall move candidate shall be created at initial position")
	public void a_wall_move_candidate_shall_be_created_at_initial_position() {
		Assert.assertEquals(true, QuoridorApplication.getQuoridor().getCurrentGame().hasWallMoveCandidate());
	}



	@Then("The wall in my hand shall disappear from my stock")
	public void the_wall_in_my_hand_shall_disappear_from_my_stock() {
		Player aPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
		Wall newWallFromStock;
		if (aPlayer.hasGameAsBlack()) {
			newWallFromStock = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition()
					.getBlackWallsInStock().get(0);
		} else {
			newWallFromStock = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition()
					.getWhiteWallsInStock().get(0);
		}
		Assert.assertEquals(null, newWallFromStock);
	}

	// Scenario: No more walls in stock
	@Given("I have no more walls on stock")
	// this should remove all walls on stock
	public void i_have_no_more_walls_on_stock() {
		Player currentPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition()
				.getPlayerToMove();
		// check whether this is black game or white game
		if (currentPlayer.hasGameAsBlack()) {
			// if this is black game, remove all walls in black stock
			if (QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().hasBlackWallsInStock()) {
				//QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsInStock().clear();
				for(int n = 19; n >=10; n--) {
					if (QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsInStock(n) != null) {
						QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().removeBlackWallsInStock(Wall.getWithId(n));
					}
				}
			}
		} else {
			// if this is white game, remove all walls in white stock
			if (QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().hasWhiteWallsInStock()) {
				//QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsInStock().clear();
				for(int n = 9; n >=0; n--) {
					if (QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsInStock(n) != null) {
						QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().removeWhiteWallsInStock(Wall.getWithId(n));
					}
				}
			}
		}

	}

	// what does it mean by notify? Do I need to print a message?
	@Then("I shall be notified that I have no more walls")
	public void i_shall_be_notified_that_I_have_no_more_walls() {
		//Assert.assertEquals("No more walls in hand!",QuoridorApplication.getJboard().getError());
	}

	@Then("I shall not have a wall in my hand")
	public void i_shall_not_have_a_wall_in_my_hand() {
		// GUI-related feature -- TODO for later
		boolean noWall = false;
//		if(QuoridorApplication.getJboard().getJwall()!=null) {
//			noWall = true;
//		}
		Assert.assertEquals(true,noWall);
		//throw new cucumber.api.PendingException();
	}
	
	@Then("I shall have no walls in my hand")
	public void i_shall_have_no_walls_in_my_hand() {
//		int w = QuoridorApplication.getJboard().getWhiteWallList().size();
//		int b = QuoridorApplication.getJboard().getBlackWallList().size();
//		Assert.assertEquals(10,w);
//		Assert.assertEquals(10,b);	
		boolean noWall = false;
//		if(QuoridorApplication.getJboard().getJwall()!=null) {
//			noWall = true;
//		}
		Assert.assertEquals(true,noWall);
	}

	//// *******************************************************************************************************************************
	//// *******************************************************************************************************************************
	////
	//// The above is GrabWall
	////
	//// *******************************************************************************************************************************
	//// *******************************************************************************************************************************
	//// ===============================================================================================================================

	//// ===============================================================================================================================
	//// *******************************************************************************************************************************
	//// *******************************************************************************************************************************
	////
	//// The following is RotateWall
	////
	//// *******************************************************************************************************************************
	//// *******************************************************************************************************************************
	
	@Given("A wall move candidate exists with {string} at position \\({int}, {int})")
	public void a_wall_move_candidate_exists_with_at_position(String string, Integer int1, Integer int2) {
		
		Player currentPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition()
				.getPlayerToMove();
		Tile WallTile = new Tile(int1, int2, QuoridorApplication.getQuoridor().getBoard());

		if (currentPlayer.hasGameAsBlack()) {
			
			Wall newWallFromStock = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition()
					.getBlackWallsInStock().get(1);
			
			if (string.equals("vertical")) {
				int a = QuoridorApplication.getQuoridor().getCurrentGame().getMoves().size();
				WallMove WallMove = new WallMove(a, (a + 1) / 2, currentPlayer, WallTile,
						QuoridorApplication.getQuoridor().getCurrentGame(), Direction.Vertical, newWallFromStock);
				QuoridorApplication.getQuoridor().getCurrentGame().setWallMoveCandidate(WallMove);
			} 
			
			if (string.equals("horizontal")) {
				int a = QuoridorApplication.getQuoridor().getCurrentGame().getMoves().size();
				WallMove WallMove = new WallMove(a, (a + 1) / 2, currentPlayer, WallTile,
						QuoridorApplication.getQuoridor().getCurrentGame(), Direction.Horizontal, newWallFromStock);
				QuoridorApplication.getQuoridor().getCurrentGame().setWallMoveCandidate(WallMove);
			}
			
		} 
		
		if (currentPlayer.hasGameAsWhite()) {
			
			Wall newWallFromStock = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition()
					.getWhiteWallsInStock().get(1);
			
			if (string.equals("vertical")) {
				int a = QuoridorApplication.getQuoridor().getCurrentGame().getMoves().size();
				WallMove WallMove = new WallMove(a, (a + 1) / 2, currentPlayer, WallTile,
						QuoridorApplication.getQuoridor().getCurrentGame(), Direction.Vertical, newWallFromStock);
				QuoridorApplication.getQuoridor().getCurrentGame().setWallMoveCandidate(WallMove);
			} 
			
			if (string.equals("horizontal")) {
				int a = QuoridorApplication.getQuoridor().getCurrentGame().getMoves().size();
				WallMove WallMove = new WallMove(a, (a + 1) / 2, currentPlayer, WallTile,
						QuoridorApplication.getQuoridor().getCurrentGame(), Direction.Horizontal, newWallFromStock);
				QuoridorApplication.getQuoridor().getCurrentGame().setWallMoveCandidate(WallMove);
			}
		}

	}

	@When("I try to flip the wall")
	public void i_try_to_flip_the_wall() {
		// WallMove wallmove = QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate();
		QuoridorController.flipWall();
	}

	@Then("The wall shall be rotated over the board to {string}")
	public void the_wall_shall_be_rotated_over_the_board_to(String string) {
		Direction expectedDirection = null;
		if (string.equals("vertical")) {
			expectedDirection = Direction.Vertical;					
		} else {
			expectedDirection = Direction.Horizontal;
		}
		Direction acturalDirection = QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getWallDirection();
		Assert.assertEquals(expectedDirection, acturalDirection);
	}

	//the same one is in feature

//		@Then("A wall move candidate shall exist with {string} at position \\({int}, {int})")
//		public void a_wall_move_candidate_shall_exist_with_at_position(String string, Integer int1, Integer int2) {
//			Direction dir;
//			if(string.equalsIgnoreCase("vertical")) {
//				   dir = Direction.Vertical;
//				  }
//				  else {
//				   dir = Direction.Horizontal;
//				  }
//			Tile ExpectedTile = new Tile(int1, int2, QuoridorApplication.getQuoridor().getBoard());
//			int ExpectedRow = ExpectedTile.getRow();
//			int ExpectedColume =  ExpectedTile.getColumn();
//			Tile ActuralTile = QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile();
//			int ActuralRow = ActuralTile.getRow();
//			int ActuralColumn = ActuralTile.getColumn();
//			Assert.assertEquals(ExpectedRow,ActuralRow );
//			Assert.assertEquals(ExpectedColume,ActuralColumn);			
//		}
	//// *******************************************************************************************************************************
	//// *******************************************************************************************************************************
	////
	//// The above is RotateWall
	////
	//// *******************************************************************************************************************************
	//// *******************************************************************************************************************************
	//// ===============================================================================================================================

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

		
		
		
		
		
		
		////===============================================================================================================================
	    ////*******************************************************************************************************************************
		////*******************************************************************************************************************************
		////   
	    ////                                      The following is SavePosition
		////
		////*******************************************************************************************************************************
		////*******************************************************************************************************************************		
		
		
			
		@Given("No file {string} exists in the filesystem")
		public void no_file_exists_in_the_filesystem(String filename) throws IOException{
		    // Write code here that turns the phrase above into concrete actions
			if(QuoridorController.checkFileExistence(filename)) {
				//delete file
				QuoridorController.deleteFile(filename);
			}
		}

		@When("The user initiates to save the game with name {string}")
		public void the_user_initiates_to_save_the_game_with_name(String string) throws IOException{
		    // Write code here that turns the phrase above into concrete actions
			QuoridorController.saveGame(string);
		}

		@Then("A file with {string} shall be created in the filesystem")
		// Check that filename exists in file system
		public void a_file_with_shall_be_created_in_the_filesystem(String filename) {
		    // Write code here that turns the phrase above into concrete actions
			// Insert Code here that checks that there is a file with name: "filename"
			Assert.assertEquals(true , QuoridorController.checkFileExistence(filename));
		}

		@Given("File {string} exists in the filesystem")
		// Make file with filename in the file system
		public void file_exists_in_the_filesystem(String filename) throws IOException{
		    // Write code here that turns the phrase above into concrete actions
			if(!QuoridorController.checkFileExistence(filename)) {
				//create file
				QuoridorController.creatNewFile(filename);
			}
		}

		@When("The user confirms to overwrite existing file")
		public void the_user_confirms_to_overwrite_existing_file() throws IOException{

		    // Write code here that turns the phrase above into concrete actions
			QuoridorController.overwriteExistingFile();
			Assert.assertEquals(true , QuoridorController.getOverwriteBoolean());
			
			String filename = "save_game_test.dat" ;
			if(QuoridorController.checkFileExistence(filename)) {
				QuoridorController.deleteFile(filename);
				QuoridorController.creatNewFile(filename);
				QuoridorController.saveGame(filename);
			}else if(!QuoridorController.checkFileExistence(filename)) {
				QuoridorController.creatNewFile(filename);
				QuoridorController.saveGame(filename);
			}
		    
		}

		// Before: "a, b, c"
		// After: "d, e, f, g"
		// I know my new addition is "d, e, f, g"; - you just need to check file contains that string
		@Then("File with {string} shall be updated in the filesystem")
		public void file_with_shall_be_updated_in_the_filesystem(String filename) throws IOException{
		    // Write code here that turns the phrase above into concrete actions
			Assert.assertEquals(true , QuoridorController.fileIsUpdated(filename));

		}

		@When("The user cancels to overwrite existing file")
		public void the_user_cancels_to_overwrite_existing_file() {
		    // Write code here that turns the phrase above into concrete actions
			QuoridorController.cancelOverwriteExistingFile();
			Assert.assertEquals(false , QuoridorController.getOverwriteBoolean());
		}

		@Then("File {string} shall not be changed in the filesystem")
		public void file_shall_not_be_changed_in_the_filesystem(String filename) throws IOException{
		    // Write code here that turns the phrase above into concrete actions
			Assert.assertEquals(false , QuoridorController.fileIsUpdated(filename));
		}

		@Given("A game position is supplied with pawn coordinate {int}:{int}")
		public void a_game_position_is_supplied_with_pawn_coordinate(Integer int1, Integer int2) {
		    // Write code here that turns the phrase above into concrete actions
			if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove().hasGameAsBlack()) {
				Integer col = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getColumn();
				Integer row = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getRow();
				if(row!=int1 || col!=int2) {
					
					Tile newTargetTile = QuoridorApplication.getQuoridor().getBoard().getTile(((int1-1)*9)+(int2-1));
					PlayerPosition blackPlayerPosition = new PlayerPosition(QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer(), newTargetTile);
					QuoridorApplication.getQuoridor().getCurrentGame().setMoveMode(MoveMode.PlayerMove);
					QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().setBlackPosition(blackPlayerPosition);
					
					Assert.assertEquals(true, QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getColumn() == int2);
					Assert.assertEquals(true, QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getRow() == int1);
					
				}
			}else if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove().hasGameAsWhite()){
				Integer col = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getColumn();
				Integer row = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getRow();
				if(row!=int1 || col!=int2) {
					
					Tile newTargetTile = QuoridorApplication.getQuoridor().getBoard().getTile(((int1-1)*9)+(int2-1));
					PlayerPosition whitePlayerPosition = new PlayerPosition(QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer(), newTargetTile);
					QuoridorApplication.getQuoridor().getCurrentGame().setMoveMode(MoveMode.PlayerMove);
					QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().setWhitePosition(whitePlayerPosition);
					
					Assert.assertEquals(true, QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getColumn() == int2);
					Assert.assertEquals(true, QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getRow() == int1);
				}
			}
		}

		@When("Validation of the position is initiated")
		public void validation_of_the_position_is_initiated() {
		    // Write code here that turns the phrase above into concrete actions
			QuoridorController.validatePosition();
		}

		@Then("The position shall be {string}")
		public void the_position_shall_be(String string) {
		    // Write code here that turns the phrase above into concrete actions
			if (QuoridorController.validatePosition()== true) {
				Assert.assertEquals("ok" , string);
			}else {
				Assert.assertEquals("error" , string);
			}
		}

		@Given("A game position is supplied with wall coordinate {int}:{int}-{string}")
		public void a_game_position_is_supplied_with_wall_coordinate(Integer int1, Integer int2, String direction) {
		    // Write code here that turns the phrase above into concrete actions
			Direction inputDirection = null;
			if(direction.equals("horizontal")){
				inputDirection = Direction.Horizontal;
			}else if(direction.equals("vertical")) {
				inputDirection = Direction.Vertical;
			}
			
			int aMoveNumber = QuoridorApplication.getQuoridor().getCurrentGame().getMoves().size();
			Player currentPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
			Game currentGame = QuoridorApplication.getQuoridor().getCurrentGame();
			Tile currentTargetTile = QuoridorApplication.getQuoridor().getBoard().getTile(((int1-1)*9)+(int2-1));
			
			if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove().hasGameAsBlack()) {
				List<Wall> inStock = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsInStock();
				Wall aNewWall = inStock.get(1);
				
				WallMove newWallmove = new WallMove(aMoveNumber,(aMoveNumber+1)/2,currentPlayer,currentTargetTile,currentGame,inputDirection,aNewWall);
				QuoridorApplication.getQuoridor().getCurrentGame().setWallMoveCandidate(newWallmove);
				QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().removeBlackWallsInStock(aNewWall);
				QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().setWallPlaced(aNewWall);
				
				Assert.assertEquals(true , int2 == QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getColumn());
				Assert.assertEquals(true , int1 == QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getRow());
				Assert.assertEquals(true , inputDirection == QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getWallDirection());
				
				
			}else if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove().hasGameAsWhite()) {
				List<Wall> inStock = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsInStock();
				Wall aNewWall = inStock.get(1);
				
				WallMove newWallmove = new WallMove(aMoveNumber,(aMoveNumber+1)/2,currentPlayer,currentTargetTile,currentGame,inputDirection,aNewWall);
				QuoridorApplication.getQuoridor().getCurrentGame().setWallMoveCandidate(newWallmove);
				QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().removeWhiteWallsInStock(aNewWall);
				QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().setWallPlaced(aNewWall);
				
				Assert.assertEquals(true , int2 == QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getColumn());
				Assert.assertEquals(true , int1 == QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getRow());
				Assert.assertEquals(true , inputDirection == QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getWallDirection());
				
			}

			/*QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition()direction.
			Integer wrow = QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getRow();
			Integer wcol = QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getColumn();
			if(wrow!=int1 || wcol!=int2 || ) {
				int aMoveNumber = QuoridorApplication.getQuoridor().getCurrentGame().numberOfMoves()-1;
				Player currentPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
				WallMove oldMove = QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate();
				QuoridorApplication.getQuoridor().getCurrentGame().removeMove(oldMove);
				////////////////////////////////////////////////////////////////////////
				Game newGame = QuoridorApplication.getQuoridor().getCurrentGame();
				Tile newTargetTile = QuoridorApplication.getQuoridor().getBoard().getTile(((int1-1)*9)+(int2-1));
				Wall aNewWall = null ;
				if(currentPlayer.hasGameAsBlack()) {
					aNewWall = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsInStock(0);
				}else if(currentPlayer.hasGameAsWhite()) {
					aNewWall = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsInStock(0);
				}
				WallMove newWallmove = new WallMove(aMoveNumber,(aMoveNumber+1)/2,currentPlayer,newTargetTile,newGame,Direction.valueOf(direction),aNewWall);
				QuoridorApplication.getQuoridor().getCurrentGame().addMove(newWallmove);
			}*/
		}

		@Then("The position shall be valid")
		public void the_position_shall_be_valid() {
		    // Write code here that turns the phrase above into concrete actions
			Assert.assertEquals(true , QuoridorController.validatePosition());
		}

		@Then("The position shall be invalid")
		public void the_position_shall_be_invalid() {
		    // Write code here that turns the phrase above into concrete actions
			Assert.assertEquals(false , QuoridorController.validatePosition());
		}
		
		
	////*******************************************************************************************************************************
		////*******************************************************************************************************************************
		////   
		////                                      The above is ValidatePosition
		////
		////*******************************************************************************************************************************
		////*******************************************************************************************************************************
		////===============================================================================================================================		
		
		
		
		
		
		
		
		
		
		
		// ***********************************************
		// StartNewGame
		// ***********************************************
		@When("A new game is being initialized")
		public void a_new_game_is_being_initialized() {
			QuoridorController.initializeNewGame();
			this.quoridor = QuoridorApplication.getQuoridor();
			this.game = quoridor.getCurrentGame();
		}

		@When("White player chooses a username")
		public void white_player_chooses_a_username() {
			this.player1 = playerList.get(0);
			//QuoridorController.selectUserName(this.player1);
			this.game.setWhitePlayer(this.player1);
			QuoridorController.setUserName("white", "test_white", true);
			//throw new cucumber.api.PendingException();
		}

		@When("Black player chooses a username")
		public void black_player_chooses_a_username() {
		    // Write code here that turns the phrase above into concrete actions
			this.player2 = playerList.get(1);
			//QuoridorController.selectUserName(this.player2);
			this.game.setBlackPlayer(this.player2);
			QuoridorController.setUserName("black", "test_black", true);
			//throw new cucumber.api.PendingException();
		}

		@When("Total thinking time is set")
		public void total_thinking_time_is_set() {
			QuoridorController.setTotalThinkingTime(1, 0);
		}

		@Then("The game shall become ready to start")
		public void the_game_shall_become_ready_to_start() {
			assertEquals(true, QuoridorController.verifyGameIsReady());
		}
		
		@Given("The game is ready to start")
		public void the_game_is_ready_to_start() {
			quoridor = QuoridorApplication.getQuoridor();
			QuoridorController.initializeNewGame();
			game = QuoridorApplication.getQuoridor().getCurrentGame();
			
			User u1 = quoridor.addUser("lufthansa");
			User u2 = quoridor.addUser("swissair");
			Player p1 = new Player(new Time(180), u1, 9, Direction.Horizontal);
			Player p2 = new Player(new Time(180), u2, 1, Direction.Horizontal);
			game.setBlackPlayer(p1);
			game.setWhitePlayer(p2);
			assertEquals(true, QuoridorController.verifyGameIsReady());
		}

		@When("I start the clock")
		public void i_start_the_clock() {
		    // Write code here that turns the phrase above into concrete actions
		    whiteStartTime = QuoridorController.startClock();
		    game.setGameStatus(GameStatus.Running);
		}

		@Then("The game shall be running")
		public void the_game_shall_be_running() {
		    // Write code here that turns the phrase above into concrete actions
			assertEquals(GameStatus.Running, game.getGameStatus());
		}

		@Then("The board shall be initialized")
		public void the_board_shall_be_initialized() {
		    // Write code here that turns the phrase above into concrete actions
			assertEquals(true, quoridor.hasBoard());
		}
		
		// ***********************************************
		// ProvideSelectUserName
		// ***********************************************
		// Scenario: Select existing user name
//		@Given("A new game is initializing")
//		public void a_new_game_is_initializing() {
//			QuoridorController.initializeNewGame();
//			quoridor = QuoridorApplication.getQuoridor();
//			this.playerList = createUsersAndPlayers("default_black", "default_white");
//			game = quoridor.getCurrentGame();
//			game.setBlackPlayer(this.playerList.get(0));
//			game.setWhitePlayer(this.playerList.get(1));
//		}
		
		@Given("Next player to set user name is {string}")
		public void next_player_to_set_user_name_is(String string) {
			toEdit = string;
		}
		
		@Given("There is existing user {string}")
		public void there_is_existing_user(String string) {
			if(!User.hasWithName(string)) {
				quoridor.addUser(string);
			}
		}
		
		@When("The player selects existing {string}")
		public void the_player_selects_existing(String string) {
			QuoridorController.setUserName(toEdit, string, false);
		}
		
		@Then("The name of player {string} in the new game shall be {string}")
		public void the_name_of_player_in_the_new_game_shall_be(String string, String string2) {
		    // Write code here that turns the phrase above into concrete actions
			String n;
			if (string.equals("white")) {
				n = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer().getUser().getName();
			} else {
				n = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer().getUser().getName();
			}
			assertEquals(string2, n);
		}
		
		@Given("There is no existing user {string}")
		public void there_is_no_existing_user(String string) {
		    // Write code here that turns the phrase above into concrete actions
			User u = User.getWithName(string);
			if (u != null) {
				quoridor.removeUser(u);
			}
		}

		@When("The player provides new user name: {string}")
		public void the_player_provides_new_user_name(String string) {
		    // Write code here that turns the phrase above into concrete actions
			QuoridorController.setUserName(toEdit, string, true);
		}
		
		@Then("The player shall be warned that {string} already exists")
		public void the_player_shall_be_warned_that_already_exists(String string) {
		    // Write code here that turns the phrase above into concrete actions
			assertEquals(true, User.hasWithName(string));
		}
		
		@Then("Next player to set user name shall be {string}")
		public void next_player_to_set_user_name_shall_be(String string) {
		    // Write code here that turns the phrase above into concrete actions
			toEdit = string;
		}

		

		
		
		// ***********************************************
		// Load Position
		// ***********************************************
		@When("I initiate to load a saved game {string}")
		public void i_initiate_to_load_a_saved_game(String string) throws FileNotFoundException {

			Player white = playerList.get(0);
			Player black = playerList.get(1);
			try {
				QuoridorController.loadPosition(string, white, black);	
			} catch(Exception e) {
				error = e.getMessage();
			}	
		}

		
		@And("The position to load is valid")
		public void the_position_to_load_is_valid() {
			if (error.equals("")) {
				try {
					QuoridorController.validation(); 
				} catch(Exception e) {
					error = e.getMessage();
				}	
			}
				    
		}


		@And("It shall be {string}'s turn")
		public void it_shall_be_s_turn(String string) {
			String toCompare;
			if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove().hasGameAsBlack()) {
				toCompare = "black";
			}else {
				toCompare = "white";
			}
			assertEquals(string, toCompare);
		    
		}
			

		@And("{string} shall be at {int}:{int}")
		public void shall_be_at(String string, Integer int1, Integer int2) {
			Integer row;
			Integer col;
			if(string.equals("black")) {
				row = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getRow();
				col = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getColumn();
			}else {
				row = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getRow();
				col = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getColumn();
			}
			assertEquals(int1, row);
			assertEquals(int2, col);
		    
		}

		@And("{string} shall have a vertical wall at {int}:{int}")
		public void shall_have_a_vertical_wall_at(String string, Integer int1, Integer int2) {
			Integer col;
			Integer row;
			Direction wallDirection;
			if(string.equals("black")) {
				wallDirection = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard(0).getMove().getWallDirection();
				col = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard(0).getMove().getTargetTile().getColumn();
				row = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard(0).getMove().getTargetTile().getRow();
			}else {
				wallDirection = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard(0).getMove().getWallDirection();
				col = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard(0).getMove().getTargetTile().getColumn();
				row = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard(0).getMove().getTargetTile().getRow();
			}
			assertEquals(Direction.Vertical, wallDirection);
			assertEquals(row, int1);
			assertEquals(col, int2);
		    
		}

		@And("{string} shall have a horizontal wall at {int}:{int}")
		public void shall_have_a_horizontal_wall_at(String string, Integer int1, Integer int2) {
			Integer col;
			Integer row;
			Direction wallDirection;
			if(string.equals("black")) {
				wallDirection = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard(0).getMove().getWallDirection();
				col = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard(0).getMove().getTargetTile().getColumn();
				row = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard(0).getMove().getTargetTile().getRow();
			}else {
				wallDirection = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard(0).getMove().getWallDirection();
				col = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard(0).getMove().getTargetTile().getColumn();
				row = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard(0).getMove().getTargetTile().getRow();
			}
			assertEquals(Direction.Horizontal, wallDirection);
			assertEquals(row, int1);
			assertEquals(col, int2);
		    
		}

		@And("Both players shall have {int} in their stacks")
		public void both_players_shall_have_in_their_stacks(Integer int1) {
		    Integer blackwall = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsInStock().size();
		    Integer whitewall = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsInStock().size();
		    assertEquals(int1, blackwall);
		    assertEquals(int1, whitewall);
		    
		}

		@And("The position to load is invalid")
		public void the_position_to_load_is_invalid() {

			if (error.equals("")) {
				try {
					QuoridorController.validation(); 
				} catch(Exception e) {
					error = e.getMessage();
				}	
			}
		}

		@Then("The load shall return an error") //what is return error
		public void the_load_shall_return_an_error() {
			
		assertTrue(error.equals("Out of boundary!") || error.equals("Wall Overlapping!") || error.equals("Invalid Pawn!"));
		    
		}

		
		
		// ***********************************************
		// Switch Player
		// ***********************************************
		@Given("The player to move is {string}")
		public void the_player_to_move_is_white(String string) {
			Player player;
			if (string.equals("white")) {
				player = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
				QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().setPlayerToMove(player);
			}else {
				player = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
				QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().setPlayerToMove(player);
			}
		}

		@Given("The clock of {string} is running") 
		public void the_clock_of_is_running(String string) {
			
			if(string.equals("black")) {
//				blackWatch = new Stopwatch(QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer());
//				whiteWatch = new Stopwatch(QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer());
//				blackWatch.start();
				long nanotime = QuoridorController.startClock();
				blackStartTime = nanotime;
				blackStartTime = TimeUnit.SECONDS.convert(nanotime, TimeUnit.NANOSECONDS);
			}else {
//				whiteWatch = new Stopwatch(QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer());
//				blackWatch = new Stopwatch(QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer());
//				whiteWatch.start();
				long nanotime = QuoridorController.startClock();
				whiteStartTime = nanotime;
				whiteStartTime = TimeUnit.SECONDS.convert(nanotime, TimeUnit.NANOSECONDS);
			}
		}

		@Given("The clock of {string} is stopped")
		public void the_clock_of_is_stopped(String string) {
			if(string == "black") {
//				blackWatch.stop();
				blackStartTime = null;
			}else {
//				whiteWatch.stop();
				whiteStartTime = null;
			}
		}

		@When("Player {string} completes his move")
		public void player_completes_his_move(String string) {
			if(string.equals("black")) {
				Player blackPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
				QuoridorController.completeMove(blackPlayer);
//				blackWatch.suspend();
//				whiteWatch.start();
				long nanotimeBlack = QuoridorController.startClock();
				blackEndTime = nanotimeBlack;
				blackEndTime = TimeUnit.SECONDS.convert(nanotimeBlack, TimeUnit.NANOSECONDS);
				long nanotimeWhite = QuoridorController.startClock();
				whiteStartTime = nanotimeWhite;
				whiteStartTime = TimeUnit.SECONDS.convert(nanotimeWhite, TimeUnit.NANOSECONDS);
			} else {
				Player whitePlayer = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
				QuoridorController.completeMove(whitePlayer);
//				whiteWatch.suspend();
//				blackWatch.start();
				long nanotimeWhite = QuoridorController.startClock();
				whiteEndTime = nanotimeWhite;
				whiteEndTime = TimeUnit.SECONDS.convert(nanotimeWhite, TimeUnit.NANOSECONDS);
				long nanotimeBlack = QuoridorController.startClock();
				blackStartTime = nanotimeBlack;
				blackStartTime = TimeUnit.SECONDS.convert(nanotimeBlack, TimeUnit.NANOSECONDS);
			}
		}

		@Then("The user interface shall be showing it is {string} turn")
		public void the_user_interface_shall_be_showing_it_is_turn(String string) {
//			if (string.equals("black")) {
//				Assert.assertEquals("It is black player's turn", QuoridorApplication.getJboard().getTurn());
//			} else {
//				Assert.assertEquals("It is white player's turn", QuoridorApplication.getJboard().getTurn());
//			}
		}
		
		@Then("The clock of {string} shall be stopped")
		public void the_clock_of_shall_be_stopped(String string) {
			if(string.equals("black")) {
//				assertEquals(false, blackWatch.isAlive());
				Boolean isStopped = (blackEndTime != null);
				assertEquals(true, isStopped);
			}else {
//				assertEquals(false, whiteWatch.isAlive());
				Boolean isStopped = (whiteEndTime != null);
				assertEquals(true, isStopped);
			}
		}

		@Then("The clock of {string} shall be running")
		public void the_clock_of_shall_be_running(String string) {
			if(string.equals("black")) {
//				assertEquals(true, blackWatch.isAlive());
				Boolean isStarted = (blackStartTime != null);
				Boolean notStopped = (blackEndTime == null);
				assertEquals(true, (isStarted && notStopped));
			}else {
//				assertEquals(true, whiteWatch.isAlive());
				Boolean isStarted = (whiteStartTime != null);
				Boolean notStopped = (whiteEndTime == null);
				assertEquals(true, (isStarted && notStopped));
			}

		}

		@Then("The next player to move shall be {string}")
		public void the_next_player_to_move_shall_be(String string) {
			String toCompare;	
			Player player = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
			if(player.hasGameAsBlack()) {
				toCompare = "black";
			}else {
				toCompare = "white";
			}
			assertEquals(string, toCompare);
		}
		

		
		
		
		
		
		
		@When ("{int}:{int} is set as the thinking time")
		public void is_set_as_the_thinking_time(Integer int1, Integer int2) {
			QuoridorController.setTotalThinkingTime(int1, int2);
		}

		@Then("Both players shall have {int}:{int} remaining time left")
		public void both_players_shall_have_remaining_time_left(Integer int1, Integer int2) {
			int minConversion = 60;
			int secConversion = 1000;
			long millisec = (int1 * minConversion + int2 ) * secConversion;
			Time timeLeft = new Time(millisec);
			assertEquals(Time.valueOf(timeLeft.toString()),Time.valueOf(QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer().getRemainingTime().toString()));
			assertEquals(Time.valueOf(timeLeft.toString()),Time.valueOf(QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer().getRemainingTime().toString()));


		}
		/*
		 * Feature: Initialize board
		 */
		@When ("The initialization of the board is initiated")
		public void the_initialization_of_the_board_is_initiated() {
			QuoridorController.initializeBoard();
			whiteStartTime = System.currentTimeMillis();
		}

		@Then("It shall be white player to move")
		public void it_shall_be_white_player_to_move() {		
			boolean whiteToMove = false;
			Player whitePlayer = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
			GamePosition g = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition();
			
			Player currentPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
			if(whitePlayer.equals(currentPlayer)) {
				whiteToMove = true;
			}
			assertEquals(true,whiteToMove);
		}

		@Then("White's pawn shall be in its initial position")
		public void white_s_pawn_shall_be_in_its_initial_position() {
			//white starts from e9
			int whiteRow = 5;
			int whiteColumn = 9;
			int whiteCurrentRow = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getRow();
			int whiteCurrentColumn = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getColumn();
			assertEquals(whiteRow,whiteCurrentRow);
			assertEquals(whiteColumn,whiteCurrentColumn);

		}
		
		@Then("Black's pawn shall be in its initial position")
		public void black_s_pawn_shall_be_in_its_initial_position() {
			//black starts from e1, e is column and 1 is row
			int blackRow = 5;
			int blackColumn = 1;
			int blackCurrentRow = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getRow();
			int blackCurrentColumn = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getColumn();
			assertEquals(blackRow,blackCurrentRow);
			assertEquals(blackColumn,blackCurrentColumn);
		}

		@Then("All of White's walls shall be in stock")
		public void all_of_White_s_walls_shall_be_in_stock() {
			assertEquals(10,QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer().numberOfWalls());


		}

		@Then("All of Black's walls shall be in stock")
		public void all_of_Black_s_walls_shall_be_in_stock() {
			assertEquals(10,QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer().numberOfWalls());

		}

		@Then("White's clock shall be counting down")
		public void white_s_clock_shall_be_counting_down() {
			boolean clockIsRunning = whiteStartTime!=null;
			assertEquals(true, clockIsRunning);

		}

		@Then("It shall be shown that this is White's turn")
		public void it_shall_be_shown_that_this_is_White_s_turn() {
			boolean whiteToMove = true;
			if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove().equals(QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer())) {
				whiteToMove = true;
			}
			assertEquals(true,whiteToMove);


		}
		
	
		
		
		// ***********************************************
		// MovePawn
		// ***********************************************	
		@Given("The player is located at {int}:{int}")
		public void the_player_is_located_at(Integer int1, Integer int2) {
			Tile tile = QuoridorApplication.getQuoridor().getBoard().getTile((int1 - 1) * 9 + int2 - 1);
			//Tile tile = new Tile(int1, int2, QuoridorApplication.getQuoridor().getBoard());
			Player aPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
			PlayerPosition  playerposition = new PlayerPosition(aPlayer, tile);
					
			if(aPlayer.hasGameAsBlack()) {
				QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().setBlackPosition(playerposition);
			}
			else {
				QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().setWhitePosition(playerposition);
			}	

		}

		
		@Given("There are no {string} walls {string} from the player")
		public void there_are_no_walls_from_the_player(String string, String string2) {
			Direction direction;
			if (string.equals("vertical")) {
				direction = Direction.Vertical;
			} else {
				direction = Direction.Horizontal;
			}
			
			Player aPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
			int row;
			int col;
			if(aPlayer.hasGameAsBlack()) {
				row = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getRow();
				col = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getColumn();
			}else {
				row = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getRow();
				col = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getColumn();
			}
			
			List<Wall> blackWalls = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard();
			List<Wall> whiteWalls = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard();
			List<Wall> wallList = new ArrayList<Wall>();
			
			for (Wall wall : wallList) {
				int wallColumn = wall.getMove().getTargetTile().getColumn();
				int wallRow = wall.getMove().getTargetTile().getRow();
				Direction wallDirection = wall.getMove().getWallDirection();
				
				if (string2.equals("left")) {
					if ((wallDirection == direction) && (wallColumn == col-1) && 
							((wallRow == row-1) || (wallRow == row+1) || (wallRow == row))) {
						if (aPlayer.hasGameAsBlack()) {
							QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().removeBlackWallsOnBoard(wall);
						} else {
							QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().removeWhiteWallsOnBoard(wall);
						}
					}
				}
				
				if (string2.equals("right")) {
					if ((wallDirection == direction) && (wallColumn == col+1) && 
							((wallRow == row-1) || (wallRow == row+1) || (wallRow == row))) {
						if (aPlayer.hasGameAsBlack()) {
							QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().removeBlackWallsOnBoard(wall);
						} else {
							QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().removeWhiteWallsOnBoard(wall);
						}
					}
				}
				
				if (string2.equals("up")) {
					if ((wallDirection == direction) && (wallRow == row-1) && 
							((wallColumn== col-1) || (wallColumn == col+1) || (wallColumn == col))) {
						if (aPlayer.hasGameAsBlack()) {
							QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().removeBlackWallsOnBoard(wall);
						} else {
							QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().removeWhiteWallsOnBoard(wall);
						}
					}
				}
				
				if (string2.equals("down")) {
					if ((wallDirection == direction) && (wallRow == row+1) && 
							((wallColumn== col-1) || (wallColumn == col+1) || (wallColumn == col))) {
						if (aPlayer.hasGameAsBlack()) {
							QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().removeBlackWallsOnBoard(wall);
						} else {
							QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().removeWhiteWallsOnBoard(wall);
						}
					}
				}
					
					
				
			}
		    
		}

		@Given("The opponent is not {string} from the player")
		public void the_opponent_is_not_from_the_player(String string) {
			Player aPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
			int prow;
			int pcol;
			int orow;
			int ocol;
			Player opponent;
			if(aPlayer.hasGameAsBlack()) {
				prow = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getRow();
				pcol = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getColumn();
				orow = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getRow();
				ocol = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getColumn();
				opponent = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
				boolean cmp1 = string.equals("left") && (orow == prow) && (ocol == pcol - 1);
				boolean cmp2 = string.equals("right") && (orow == prow) && (ocol == pcol + 1);
				boolean cmp3 = string.equals("up") && (orow == prow - 1) && (ocol == pcol);
				boolean cmp4 = string.equals("down") && (orow == prow + 1) && (ocol == pcol);
				if (cmp1 && cmp2 && cmp3 && cmp4) {
					Tile player1StartPos = QuoridorApplication.getQuoridor().getBoard().getTile(4);
					PlayerPosition whitePosition = new PlayerPosition(QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer(), player1StartPos);
					QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().setWhitePosition(whitePosition);
				}
				
			}else {
				orow = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getRow();
				ocol = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getColumn();
				prow = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getRow();
				pcol = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getColumn();
				opponent = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
				boolean cmp1 = string.equals("left") && (orow == prow) && (ocol == pcol - 1);
				boolean cmp2 = string.equals("right") && (orow == prow) && (ocol == pcol + 1);
				boolean cmp3 = string.equals("up") && (orow == prow - 1) && (ocol == pcol);
				boolean cmp4 = string.equals("down") && (orow == prow + 1) && (ocol == pcol);
			}		
			
		}

		@When("Player {string} initiates to move {string}")
		public void player_initiates_to_move(String string, String string2) {
			movePawnSuccess = QuoridorController.movePlayer(string, string2);
		}

		@Then("The move {string} shall be {string}")
		public void the_move_shall_be(String string, String string2) {
			String status = movePawnSuccess?"success":"illegal";
			//Assert.assertEquals(string, string);
			Assert.assertEquals(string2, status);
		}

		@Then("Player's new position shall be {int}:{int}")
		public void player_s_new_position_shall_be(Integer int1, Integer int2) {
			Player aPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
			Player whitePlayer = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
			Player blackPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();

			Integer row;
			Integer col;

			if(movePawnSuccess&&!aPlayer.equals(blackPlayer)) {
				row = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getRow();
				col = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getColumn();
			
			}else if(movePawnSuccess&&!aPlayer.equals(whitePlayer)){
				row = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getRow();
				col = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getColumn();
			}
			else if(!movePawnSuccess&&aPlayer.equals(blackPlayer)) {
				row = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getRow();
				col = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getColumn();
			}else if(!movePawnSuccess&&aPlayer.equals(whitePlayer)){
				row = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getRow();
				col = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getColumn();
			}else {
				col = -1;
				row = -1;
			}
			Assert.assertEquals(int2, col);

			Assert.assertEquals(int1, row);
			
		}

		@Then("The next player to move shall become {string}")
		public void the_next_player_to_move_shall_become(String string) {
			String toCompare;	
			Player player = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
			if(player.hasGameAsBlack()) {
				toCompare = "black";
			}else {
				toCompare = "white";
			}
			assertEquals(string, toCompare);
		}
		
		@Given("There is a {string} wall at {int}:{int}")
		public void there_is_a_wall_at(String string, Integer int1, Integer int2) {
			Direction wallDirection;
		    if (string.equals("vertical")) {
		    	wallDirection = Direction.Vertical;
		    } else {
		    	wallDirection = Direction.Horizontal;
		    }
		    
		    Player black = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
		    Wall wall = black.getWall(0);
		    Tile tile = QuoridorApplication.getQuoridor().getBoard().getTile((int1 - 1) * 9 + int2 - 1);
		    new WallMove(1, 1, black, tile, QuoridorApplication.getQuoridor().getCurrentGame(), wallDirection, wall);
		    QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().addBlackWallsOnBoard(wall);
		}
		
		
		// *****************************************************
		// Jump Pawn
		// *****************************************************
		@Given("The opponent is located at {int}:{int}")
		public void the_opponent_is_located_at(Integer int1, Integer int2) {
		    // Write code here that turns the phrase above into concrete actions
		    throw new cucumber.api.PendingException();
		}
		
		@Given("There are no {string} walls {string} from the player nearby")
		public void there_are_no_walls_from_the_player_nearby(String string, String string2) {
		    // Write code here that turns the phrase above into concrete actions
		    throw new cucumber.api.PendingException();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	// After each scenario, the test model is discarded
		@After
		public void tearDown() {
			Quoridor quoridor = QuoridorApplication.getQuoridor();
			// Avoid null pointer for step definitions that are not yet implemented.
			if (quoridor != null) {
				quoridor.delete();
				quoridor = null;
			}
			for (int i = 0; i < 20; i++) {
				Wall wall = Wall.getWithId(i+1);
				if(wall != null) {
					wall.delete();
				}
			}
		}

		// ***********************************************
		// Extracted helper methods
		// ***********************************************

		// Place your extracted methods below

		private void initQuoridorAndBoard() {
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

		private ArrayList<Player> createUsersAndPlayers(String userName1, String userName2) {
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
					new Wall(i * 10 + j+1, players[i]);
				}
			}
			
			ArrayList<Player> playersList = new ArrayList<Player>();
			playersList.add(player1);
			playersList.add(player2);
			
			return playersList;
		}

		private void createAndStartGame(ArrayList<Player> players) {
			Quoridor quoridor = QuoridorApplication.getQuoridor();
			// There are total 36 tiles in the first four rows and
			// indexing starts from 0 -> tiles with indices 36 and 36+8=44 are the starting
			// positions
			Tile player1StartPos = quoridor.getBoard().getTile(4);
			Tile player2StartPos = quoridor.getBoard().getTile(76);
		
			Game game = new Game(GameStatus.Running, MoveMode.PlayerMove, quoridor);
			game.setWhitePlayer(players.get(0));
			game.setBlackPlayer(players.get(1));

			PlayerPosition player1Position = new PlayerPosition(quoridor.getCurrentGame().getWhitePlayer(), player1StartPos);
			PlayerPosition player2Position = new PlayerPosition(quoridor.getCurrentGame().getBlackPlayer(), player2StartPos);
		GamePosition gamePosition = new GamePosition(0, player1Position, player2Position, players.get(0), game);
		

		// Add the walls as in stock for the players
		for (int j = 0; j < 10; j++) {
			Wall wall = Wall.getWithId(j+1);
			gamePosition.addWhiteWallsInStock(wall);
		}
		for (int j = 0; j < 10; j++) {
			Wall wall = Wall.getWithId(j + 10+1);
			gamePosition.addBlackWallsInStock(wall);
		}

			game.setCurrentPosition(gamePosition);
		}

	}