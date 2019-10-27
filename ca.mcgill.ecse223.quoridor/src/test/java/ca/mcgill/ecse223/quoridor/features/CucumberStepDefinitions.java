package ca.mcgill.ecse223.quoridor.features;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

//import static org.junit.Assert.assertEquals;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import ca.mcgill.ecse223.quoridor.QuoridorApplication;
import ca.mcgill.ecse223.quoridor.controller.QuoridorController;
import ca.mcgill.ecse223.quoridor.model.Board;
import ca.mcgill.ecse223.quoridor.model.Direction;
import ca.mcgill.ecse223.quoridor.model.Game;
import ca.mcgill.ecse223.quoridor.model.Game.GameStatus;
import ca.mcgill.ecse223.quoridor.model.Game.MoveMode;
import ca.mcgill.ecse223.quoridor.model.GamePosition;
import ca.mcgill.ecse223.quoridor.model.Player;
import ca.mcgill.ecse223.quoridor.model.PlayerPosition;
import ca.mcgill.ecse223.quoridor.model.*;
import ca.mcgill.ecse223.quoridor.model.Quoridor;
import ca.mcgill.ecse223.quoridor.model.Tile;
import ca.mcgill.ecse223.quoridor.model.User;
import ca.mcgill.ecse223.quoridor.model.Wall;
import ca.mcgill.ecse223.quoridor.model.WallMove;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class CucumberStepDefinitions {

	private boolean isPositionValid;
	private Long blackStartTime;
	private Long blackEndTime;
	private Long whiteStartTime;
	private Long whiteEndTime;
	

	// ***********************************************
	// Background step definitions
	// ***********************************************

	@Given("^The game is not running$")
	public void theGameIsNotRunning() {
		initQuoridorAndBoard();
		createUsersAndPlayers("user1", "user2");
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
		ArrayList<Player> players = createUsersAndPlayers("user1", "user2");
		new Game(GameStatus.Initializing, MoveMode.PlayerMove, QuoridorApplication.getQuoridor());
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
			
			Tile tile = new Tile(int1, int2, QuoridorApplication.getQuoridor().getBoard());
			Player aPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
			int a = QuoridorApplication.getQuoridor().getCurrentGame().getMoves().size();
			Wall newWallFromStock;
			//WallMove wallmove;
			Direction dir;
			if(string.equalsIgnoreCase("vertical")) {
				dir = Direction.Vertical;
			}
			else {
				dir = Direction.Horizontal;
			}
			if(aPlayer.hasGameAsBlack()) {
				newWallFromStock = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsInStock().get(1);
			}
			else {
				newWallFromStock = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsInStock().get(1);
			}
			
			WallMove wallmove = new WallMove(a, (a+1)/2, aPlayer, tile, QuoridorApplication.getQuoridor().getCurrentGame(), dir, newWallFromStock );
			if(QuoridorController.verifyWallMoveValid(wallmove)==false) {
				Assert.fail();
			}
	}

	
	
	@When("I release the wall in my hand")
	public void i_release_the_wall_in_my_hand() {
		QuoridorController.ReleaseWall(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate());
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
		Tile tile = new Tile(int1, int2, QuoridorApplication.getQuoridor().getBoard());
		// not right
		Assert.assertEquals(false, QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().setTargetTile(tile));
		Assert.assertEquals(false, QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().setWallDirection(dir));	
		
	}
	
	

	@Then("I shall not have a wall in my hand")
	public void i_shall_not_have_a_wall_in_my_hand() {
		// GUI-related feature -- TODO for later
		
	}

	@Then("My move shall be completed")
	public void my_move_shall_be_completed() {
	
		Assert.assertEquals(false, QuoridorApplication.getQuoridor().getCurrentGame().hasWallMoveCandidate());
	}

	@Then("It shall not be my turn to move")
	public void it_shall_not_be_my_turn_to_move() {
		Player aPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getPlayer();
		Assert.assertNotEquals(aPlayer, QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove());
	    
	}
	
	
	@Given("The wall move candidate with {string} at position \\({int}, {int}) is invalid")
	public void the_wall_move_candidate_with_at_position_is_invalid(String string, Integer int1, Integer int2) {
		Tile tile = new Tile(int1, int2, QuoridorApplication.getQuoridor().getBoard());
		Player aPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
		int a = QuoridorApplication.getQuoridor().getCurrentGame().getMoves().size();
		Wall newWallFromStock;
		//WallMove wallmove;
		Direction dir;
		if(string.equalsIgnoreCase("vertical")) {
			dir = Direction.Vertical;
		}
		else {
			dir = Direction.Horizontal;
		}
		if(aPlayer.hasGameAsBlack()) {
			newWallFromStock = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsInStock().get(1);
		}
		else {
			newWallFromStock = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsInStock().get(1);
		}
		
		WallMove wallmove = new WallMove(a, (a+1)/2, aPlayer, tile, QuoridorApplication.getQuoridor().getCurrentGame(), dir, newWallFromStock );
		if(QuoridorController.verifyWallMoveInvalid(wallmove)==false) {
			Assert.fail();
		}
}
	    
	

	@Then("I shall be notified that my wall move is invalid")
	public void i_shall_be_notified_that_my_wall_move_is_invalid() {
		//It could be GUI
	    //TA said that I could fill this out later.
		
	}

	@Then("I shall have a wall in my hand over the board")
	public void i_shall_have_a_wall_in_my_hand_over_the_board() {
		// GUI-related feature -- TODO for later
		
	    
	}

	@Then("It shall be my turn to move")
	public void it_shall_be_my_turn_to_move() {
		// if this is my turn to move, then it means I have not finished yet, thus there should not be a next player due to unfinished move
		//Assert.assertEquals(false, QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove().hasNextPlayer());
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
		Tile tile = new Tile(int1, int2, QuoridorApplication.getQuoridor().getBoard());
		// not right
		// if there is no wall exist at that position, then it would return true to set a new wall there
		// if there is a wall already exists at that position, then it would not set another wall there, thus return false.
		Assert.assertEquals(true, QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().setTargetTile(tile));
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
	
//	@Given("A wall move candidate exists with {string} at position \\({int}, {int})")
//	public void a_wall_move_candidate_exists_with_at_position(String string, Integer int1, Integer int2) {
//		Player currentPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
//		Tile WallTile = new Tile (int1, int2, QuoridorApplication.getQuoridor().getBoard() );
//		int a = QuoridorApplication.getQuoridor().getCurrentGame().getMoves().size();
//		
//		if(currentPlayer.hasGameAsBlack()) {
//			Wall newWallFromStock = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsInStock().get(1);				
//				
//				if(string.equalsIgnoreCase("vertical")) {
//					
//					WallMove WallMove = new WallMove(a, (a+1)/2, currentPlayer, WallTile, 
//							QuoridorApplication.getQuoridor().getCurrentGame(), Direction.Vertical, newWallFromStock );
//					QuoridorApplication.getQuoridor().getCurrentGame().setWallMoveCandidate(WallMove);	
//				}else {
//					WallMove WallMove = new WallMove(a, (a+1)/2, currentPlayer, WallTile, 
//							QuoridorApplication.getQuoridor().getCurrentGame(), Direction.Horizontal, newWallFromStock );
//					
//					QuoridorApplication.getQuoridor().getCurrentGame().setWallMoveCandidate(WallMove);	
//				}					
//		}else {
//			
//			Wall newWallFromStock = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsInStock().get(1);				
//				
//				if(string.equalsIgnoreCase("vertical")) {
//				
//					WallMove WallMove = new WallMove(a, (a+1)/2, currentPlayer, WallTile, 
//							QuoridorApplication.getQuoridor().getCurrentGame(), Direction.Vertical, newWallFromStock );
//					QuoridorApplication.getQuoridor().getCurrentGame().setWallMoveCandidate(WallMove);	
//				}else {
//					WallMove WallMove = new WallMove(a, (a+1)/2, currentPlayer, WallTile, 
//							QuoridorApplication.getQuoridor().getCurrentGame(), Direction.Horizontal, newWallFromStock );
//					QuoridorApplication.getQuoridor().getCurrentGame().setWallMoveCandidate(WallMove);	
//				}					
//		}		    
//	}	

	
	@Given("The wall candidate is not at the {string} edge of the board")
	public void the_wall_candidate_is_not_at_the_edge_of_the_board(String string) {
		
		int currentRow = QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getRow();
		int currentColumn = QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getColumn();
		
		
		if((currentColumn<=1)||(currentColumn>=8)||(currentRow<=1)||(currentRow>=8))
			 {
				//  use the exist string and tile to create a new tile that is not at the edge, using method from controller method
				Tile a = QuoridorController.getNonEdgeTile( string ,  QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile());
				QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().setTargetTile(a);
			 }	
		
		
	}

	@When("I try to move the wall {string}")
	public void i_try_to_move_the_wall(String string) {
		QuoridorController.MoveWall(string);	
	    
	}

	@Then("The wall shall be moved over the board to position \\({int}, {int})")
	public void the_wall_shall_be_moved_over_the_board_to_position(Integer int1, Integer int2) {
		Tile tile = new Tile(int1, int2, QuoridorApplication.getQuoridor().getBoard());
		Assert.assertEquals(false,QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().setTargetTile(tile));  
	}
	

	@Then("A wall move candidate shall exist with {string} at position \\({int}, {int})")
	public void a_wall_move_candidate_shall_exist_with_at_position(String string, Integer int1, Integer int2) {
		Tile tile = new Tile(int1, int2, QuoridorApplication.getQuoridor().getBoard());
		Direction dir;
		if(string.equalsIgnoreCase("vertical")) {
			dir = Direction.Vertical;
		}
		else {
			dir = Direction.Horizontal;
		}
		Assert.assertEquals(tile, QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile());
		Assert.assertEquals(dir, QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getWallDirection());    
	}
	
	

	@Given("The wall candidate is at the {string} edge of the board")
	public void the_wall_candidate_is_at_the_edge_of_the_board(String string) {
		int currentRow = QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getRow();
		int currentColumn = QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getColumn();
		
		if(!((currentColumn<=1)||(currentColumn>=8)||(currentRow<=1)||(currentRow>=8)))
		 {
			//  use the exist string and tile to create a new tile that is a tile at the edge, using method from controller method
			Tile a = QuoridorController.getEdgeTile( string ,  QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile());
			QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().setTargetTile(a);
		 }	
	}
	
	@Then("I shall be notified that my move is illegal")
	public void i_shall_be_notified_that_my_move_is_illegal() {
		// GUI-related feature -- TODO for later
		
	   
	}

	////*******************************************************************************************************************************
	////*******************************************************************************************************************************
	////   
	////											The above is MoveWall
	////
	////*******************************************************************************************************************************
	////*******************************************************************************************************************************
	////===============================================================================================================================
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
////===============================================================================================================================
    ////*******************************************************************************************************************************
	////*******************************************************************************************************************************
	////   
    ////                                      The following is GrabWall
	////
	////*******************************************************************************************************************************
	////*******************************************************************************************************************************
	
	// Scenario: Start wall placement
		@Given("I have more walls on stock") 
		
		// if current player does not have walls in stock, then add walls to their list of walls in stock
		// firstly, check whether this is black game or white game, then check whether there is more walls in stock, if not, add one
		
		public void i_have_more_walls_on_stock() {
			Player currentPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
			// initialize a new wall with a different id, so it does not conflict with existed walls
			int WallID = 100;
			while (Wall.hasWithId(WallID)) {
				WallID++;
			}
			Wall wallAdded = Wall.getWithId(WallID);
			// check game is black or white
			// if setGameAsBlack returns true, then it is black game
			if(currentPlayer.hasGameAsBlack()) {
				if (!QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().hasBlackWallsInStock()) {
					// if there is no black walls in stock, then add one
					QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().addBlackWallsInStock(wallAdded);	
				}			
			}else {
				// this is white game
				if (!QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().hasWhiteWallsInStock()) {
					// if there is no white walls in stock, then add one
					QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().addWhiteWallsInStock(wallAdded);	
				}			
			}

		}
		
		@When("I try to grab a wall from my stock")
		public void i_try_to_grab_a_wall_from_my_stock() {	
			Player currentPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();			
			if(currentPlayer.hasGameAsBlack()) {
				// this is a black game
				// check if that Wall is in stock, remove it from stock, and grab it using grab method
				for(int i = 0; i < 20; i++) {
					if (QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsInStock(i) != null) {
						QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsInStock().remove(i);
						Wall GrabbedWall = Wall.getWithId(i);
						// use the controller method
						QuoridorController.GrabWall(GrabbedWall);
						break;
					}
				}					
			}else {
				// this is a white game
				// check if that Wall is in stock, remove it from stock, and grab it using grab method
				for(int i = 0; i < 20; i++) {
					if (QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsInStock(i) != null) {
						QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsInStock().remove(i);
						Wall GrabbedWall = Wall.getWithId(i);
						// use the controller method
						QuoridorController.GrabWall(GrabbedWall);
						break;
					}			
				}				
			}
		}
			
		
		@Then("A wall move candidate shall be created at initial position")
		public void a_wall_move_candidate_shall_be_created_at_initial_position(){
			Assert.assertEquals(true,QuoridorApplication.getQuoridor().getCurrentGame().hasWallMoveCandidate());
			
		}
		
		// the same one is in feature DropWall
		
//		@And("I shall have a wall in my hand over the board")
//		public void i_shall_have_a_wall_in_my_hand_over_the_board() {
//			// Assert.assertEquals(true, QuoridorApplication.getQuoridor().getCurrentGame().hasWallMoveCandidate() );
//		    // This is GUI related part
//		}	
		
		@And("The wall in my hand shall disappear from my stock")
		public void the_wall_in_my_hand_shall_disappear_from_my_stock() {
			Player aPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
			Wall newWallFromStock;
			if(aPlayer.hasGameAsBlack()) {
			    newWallFromStock = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsInStock().get(0);
			   }
			   else {
			    newWallFromStock = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsInStock().get(0);
			   }
			Assert.assertEquals(null,newWallFromStock);
		}
		
	// Scenario: No more walls in stock
		@Given ("I have no more walls on stock")
		// this should remove all walls on stock
		public void i_have_no_more_walls_on_stock() {
			Player currentPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
			// check whether this is black game or white game
			if (currentPlayer.hasGameAsBlack() ) {
				// if this is black game, remove all walls in black stock
				if (QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().hasBlackWallsInStock()) {
					QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsInStock().clear();
				}
			}else {
				// if this is white game, remove all walls in white stock
				if (QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().hasWhiteWallsInStock()) {
					QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsInStock().clear();
				}
			}

		}
		
		// what does it mean by notify? Do I need to print a message?
		@Then ("I shall be notified that I have no more walls")
		public void i_shall_be_notified_that_I_have_no_more_walls() {	   
			// ui
			// the TA said that I could fill this out later.
		}
		@And ("I shall have no walls in my hand")
		public void i_shall_have_no_walls_in_my_hand(){
			Assert.assertEquals(false, QuoridorApplication.getQuoridor().getCurrentGame().hasWallMoveCandidate() );
			
		} 
		
	
    ////*******************************************************************************************************************************
	////*******************************************************************************************************************************
	////   
	////                                      The above is GrabWall
	////
	////*******************************************************************************************************************************
	////*******************************************************************************************************************************
	////===============================================================================================================================
		
		
		
		
	////===============================================================================================================================
    ////*******************************************************************************************************************************
	////*******************************************************************************************************************************
	////   
    ////                                      The following is RotateWall
	////
	////*******************************************************************************************************************************
	////*******************************************************************************************************************************
		@Given("A wall move candidate exists with {string} at position \\({int}, {int})")
		public void a_wall_move_candidate_exists_with_at_position(String string, Integer int1, Integer int2) {			
			Player currentPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
			Tile WallTile = new Tile (int1, int2, QuoridorApplication.getQuoridor().getBoard() );
						
			if(currentPlayer.hasGameAsBlack()) {
				// this is a black game
				// initialize a new wall
				Wall newWallFromStock = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsInStock().get(1);				
					
					if(string.equals("vertical")) {
						
						// initialize a vertical WallMove
						int a = QuoridorApplication.getQuoridor().getCurrentGame().getMoves().size();
						
						WallMove WallMove = new WallMove(a, (a+1)/2, currentPlayer, WallTile, 
								QuoridorApplication.getQuoridor().getCurrentGame(), Direction.Vertical, newWallFromStock );
						
						// set it to WallMoveCandidate
						QuoridorApplication.getQuoridor().getCurrentGame().setWallMoveCandidate(WallMove);	
					}else {
						// initialize a horizontal WallMove
						int a = QuoridorApplication.getQuoridor().getCurrentGame().getMoves().size();
						WallMove WallMove = new WallMove(a, (a+1)/2, currentPlayer, WallTile, 
								QuoridorApplication.getQuoridor().getCurrentGame(), Direction.Horizontal, newWallFromStock );
						// set it to WallMoveCandidate
						QuoridorApplication.getQuoridor().getCurrentGame().setWallMoveCandidate(WallMove);	
					}					
			}else {
				// this is a white game,
				// initialize a new wall
				Wall newWallFromStock = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsInStock().get(1);				
					
					if(string.equals("vertical")) {
						// initialize a vertical WallMove
						int a = QuoridorApplication.getQuoridor().getCurrentGame().getMoves().size();
						WallMove WallMove = new WallMove(a, (a+1)/2, currentPlayer, WallTile, 
								QuoridorApplication.getQuoridor().getCurrentGame(), Direction.Vertical, newWallFromStock );
						// set it to WallMoveCandidate
						QuoridorApplication.getQuoridor().getCurrentGame().setWallMoveCandidate(WallMove);	
					}else {
						// initialize a horizontal WallMove
						int a = QuoridorApplication.getQuoridor().getCurrentGame().getMoves().size();
						WallMove WallMove = new WallMove(a, (a+1)/2, currentPlayer, WallTile, 
								QuoridorApplication.getQuoridor().getCurrentGame(), Direction.Horizontal, newWallFromStock );
						// set it to WallMoveCandidate
						QuoridorApplication.getQuoridor().getCurrentGame().setWallMoveCandidate(WallMove);	
					}					
			}
			
		}
		
		
		@When("I try to flip the wall")
		public void i_try_to_flip_the_wall() {
		   WallMove wallmove = QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate();
		   QuoridorController.FlipWall(wallmove);	    
		}
		

		@Then("The wall shall be rotated over the board to {string}")
		public void the_wall_shall_be_rotated_over_the_board_to(String string) {
			if(string.equals("vertical")){
				 Assert.assertEquals(Direction.Vertical,QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getWallDirection());
			}else {
				 Assert.assertEquals(Direction.Horizontal,QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getWallDirection());
			}	   
		}
		
		//// the same one is in feature 
		
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
//			Assert.assertEquals( dir ,QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getWallDirection());
//			Assert.assertEquals( ExpectedTile ,QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile());			
//		}
	////*******************************************************************************************************************************
	////*******************************************************************************************************************************
	////   
	////                                      The above is RotateWall
	////
	////*******************************************************************************************************************************
	////*******************************************************************************************************************************
	////===============================================================================================================================	
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		@Given("No file {string} exists in the filesystem")
		public void no_file_exists_in_the_filesystem(String filename){
		    // Write code here that turns the phrase above into concrete actions
			if(QuoridorController.checkFileExistence(filename)) {
				//delete file
				QuoridorController.deleteFile(filename);
			}
		    
		}

		@When("The user initiates to save the game with name {string}")
		public void the_user_initiates_to_save_the_game_with_name(String string){
		    // Write code here that turns the phrase above into concrete actions
			Game game = QuoridorApplication.getQuoridor().getCurrentGame();
			QuoridorController.saveGame(game , string);
		    
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
		public void file_exists_in_the_filesystem(String filename){
		    // Write code here that turns the phrase above into concrete actions
			if(!QuoridorController.checkFileExistence(filename)) {
				//create file
				QuoridorController.creatNewFile(filename);
			}
		    
		}

		@When("The user confirms to overwrite existing file")
		public void the_user_confirms_to_overwrite_existing_file() {
		    // Write code here that turns the phrase above into concrete actions
			QuoridorController.overwriteExistingFile();
		    
		}

		// Before: "a, b, c"
		// After: "d, e, f, g"
		// I know my new addition is "d, e, f, g"; - you just need to check file contains that string
		@Then("File with {string} shall be updated in the filesystem")
		public void file_with_shall_be_updated_in_the_filesystem(String filename){
		    // Write code here that turns the phrase above into concrete actions
			Assert.assertEquals(true , QuoridorController.fileIsUpdated(filename));
		    
		}

		@When("The user cancels to overwrite existing file")
		public void the_user_cancels_to_overwrite_existing_file() {
		    // Write code here that turns the phrase above into concrete actions
			QuoridorController.cancelOverwriteExistingFile();
		    
		}

		@Then("File {string} shall not be changed in the filesystem")
		public void file_shall_not_be_changed_in_the_filesystem(String filename){
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
					int aMoveNumber = QuoridorApplication.getQuoridor().getCurrentGame().getMoves().size();
					Player currentPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
					Move oldMove = QuoridorApplication.getQuoridor().getCurrentGame().getMove(aMoveNumber);
					QuoridorApplication.getQuoridor().getCurrentGame().removeMove(oldMove);
					///////////////////////////////////////////////////////////////////////
					Game newGame = QuoridorApplication.getQuoridor().getCurrentGame();
					Board newBoard = QuoridorApplication.getQuoridor().getBoard();
					Tile newTargetTile = new Tile(int1 , int2 , newBoard);
					StepMove newMove = new StepMove(aMoveNumber , (aMoveNumber+1)/2 , currentPlayer , newTargetTile , newGame );
					QuoridorApplication.getQuoridor().getCurrentGame().addMove(newMove);
				}
			}else {
				Integer col = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getColumn();
				Integer row = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getRow();
				if(row!=int1 || col!=int2) {
					int aMoveNumber = QuoridorApplication.getQuoridor().getCurrentGame().getMoves().size();
					Player currentPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
					Move oldMove = QuoridorApplication.getQuoridor().getCurrentGame().getMove(aMoveNumber);
					QuoridorApplication.getQuoridor().getCurrentGame().removeMove(oldMove);
					///////////////////////////////////////////////////////////////////////
					Game newGame = QuoridorApplication.getQuoridor().getCurrentGame();
					Board newBoard = QuoridorApplication.getQuoridor().getBoard();
					Tile newTargetTile = new Tile(int1 , int2 , newBoard);
					StepMove newMove = new StepMove(aMoveNumber , (aMoveNumber+1)/2 , currentPlayer , newTargetTile , newGame );
					QuoridorApplication.getQuoridor().getCurrentGame().addMove(newMove);
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
			String di = QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getWallDirection().toString();
			Integer wrow = QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getRow();
			Integer wcol = QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getColumn();
			if(wrow!=int1 || wcol!=int2 || di!=direction) {
				int aMoveNumber = QuoridorApplication.getQuoridor().getCurrentGame().getMoves().size();
				Player currentPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
				WallMove oldMove = QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate();
				QuoridorApplication.getQuoridor().getCurrentGame().removeMove(oldMove);
				////////////////////////////////////////////////////////////////////////
				Game newGame = QuoridorApplication.getQuoridor().getCurrentGame();
				Board newBoard = QuoridorApplication.getQuoridor().getBoard();
				Tile newTargetTile = new Tile(int1 , int2 , newBoard);
				int WallID = 100 ;
				for(Wall wall : QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard()) {
					while (wall.hasWithId(WallID)){
						WallID++;
					}
				}
				for(Wall wall : QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard()) {
					while (wall.hasWithId(WallID)){
						WallID++;
					}
				}
				Wall aNewWall = new Wall(WallID , currentPlayer);
				
				WallMove newWallmove = new WallMove(aMoveNumber,(aMoveNumber+1)/2,currentPlayer,newTargetTile,newGame,Direction.valueOf(direction),aNewWall);
				QuoridorApplication.getQuoridor().getCurrentGame().addMove(newWallmove);
			}
		    
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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		// ***********************************************
		// StartNewGame
		// ***********************************************
		@When("A new game is being initialized")
		public void a_new_game_is_being_initialized() {
		    // Write code here that turns the phrase above into concrete actions
			List<User> users = QuoridorApplication.getQuoridor().getUsers();
			QuoridorController.initializeNewGame(users.get(0), users.get(1));
			
		}

		@When("White player chooses a username")
		public void white_player_chooses_a_username() {
		    // Write code here that turns the phrase above into concrete actions
			QuoridorController.selectUserName(QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer());
			
		}

		@When("Black player chooses a username")
		public void black_player_chooses_a_username() {
		    // Write code here that turns the phrase above into concrete actions
			QuoridorController.selectUserName(QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer());
			
		}

		@When("Total thinking time is set")
		public void total_thinking_time_is_set() {
		    // Write code here that turns the phrase above into concrete actions
			QuoridorController.verifyTotalThinkingTime(QuoridorApplication.getQuoridor().getCurrentGame());
			
		}

		@Then("The game shall become ready to start")
		public void the_game_shall_become_ready_to_start() {
		    // Write code here that turns the phrase above into concrete actions
			assertEquals(GameStatus.ReadyToStart, QuoridorApplication.getQuoridor().getCurrentGame().getGameStatus());
		    
		}
		
		@Given("The game is ready to start")
		public void the_game_is_ready_to_start() {
		    // Write code here that turns the phrase above into concrete actions
			QuoridorController.verifyNewGame(QuoridorApplication.getQuoridor().getCurrentGame());
			
		}

		@When("I start the clock")
		public void i_start_the_clock() {
		    // Write code here that turns the phrase above into concrete actions
		    QuoridorController.clockIsRunning(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove());
			
		}

		@Then("The game shall be running")
		public void the_game_shall_be_running() {
		    // Write code here that turns the phrase above into concrete actions
			assertEquals(GameStatus.Running, QuoridorApplication.getQuoridor().getCurrentGame().getGameStatus());
			
		}

		@Then("The board shall be initialized")
		public void the_board_shall_be_initialized() {
		    // Write code here that turns the phrase above into concrete actions
			QuoridorController.initializeBoard();
		    
		}
		
		// ***********************************************
		// ProvideSelectUserName
		// ***********************************************
		// Scenario: Select existing user name
		@Given("A new game is initializing")
		public void a_new_game_is_initializing() {
			assertEquals(GameStatus.Initializing, QuoridorController.getGameStatus(QuoridorApplication.getQuoridor().getCurrentGame()));
		    
		}
		
		@Given("Next player to set user name is {string}")
		public void next_player_to_set_user_name_is(String string) {
		    // Write code here that turns the phrase above into concrete actions
			Game g = QuoridorApplication.getQuoridor().getCurrentGame();
			Player next;
			if(string=="black") {
				next = g.getBlackPlayer();
			}else {
				next = g.getWhitePlayer();
			}
			QuoridorController.setNextPlayer(g, next);
			
		}
		
		@Given("There is existing user {string}")
		public void there_is_existing_user(String string) {
		    // Write code here that turns the phrase above into concrete actions
			QuoridorApplication.getQuoridor().addUser(string);
		    
		}
		
		@When("The player selects existing {string}")
		public void the_player_selects_existing(String string) {
		    // Write code here that turns the phrase above into concrete actions
			QuoridorController.linkUserAndPlayer(User.getWithName(string), QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove());
		    
		}
		
		@Then("The name of player {string} in the new game shall be {string}")
		public void the_name_of_player_in_the_new_game_shall_be(String string, String string2) {
		    // Write code here that turns the phrase above into concrete actions
			String n;
			if (string=="white") {
				n = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer().getUser().getName();
			} else {
				n = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer().getUser().getName();
			}
			assertEquals(string, n);
		    
		}
		
		// Scenario: Create new user name
//		@Given("Next player to set user name is {string}")
//		public void next_player_to_set_user_name_is(String string) {
//		    // Write code here that turns the phrase above into concrete actions
//			Game g = QuoridorApplication.getQuoridor().getCurrentGame();
//			Player next;
//			if(string=="black") {
//				next = g.getBlackPlayer();
//			}else {
//				next = g.getWhitePlayer();
//			}
//			g.getCurrentPosition().setPlayerToMove(next);
//			
//		}
		
		@Given("There is no existing user {string}")
		public void there_is_no_existing_user(String string) {
		    // Write code here that turns the phrase above into concrete actions
			QuoridorApplication.getQuoridor().removeUser(User.getWithName(string));
		    
		}

		@When("The player provides new user name: {string}")
		public void the_player_provides_new_user_name(String string) {
		    // Write code here that turns the phrase above into concrete actions
			QuoridorApplication.getQuoridor().addUser(string);
		    
		}
		
//		@Then("The name of player {string} in the new game shall be {string}")
//		public void the_name_of_player_in_the_new_game_shall_be(String string, String string2) {
//		    // Write code here that turns the phrase above into concrete actions
//		    
//		}
		
		// User name already exists
//		@Given("Next player to set user name is {string}")
//		public void next_player_to_set_user_name_is(String string) {
//		    // Write code here that turns the phrase above into concrete actions
//			Game g = QuoridorApplication.getQuoridor().getCurrentGame();
//			Player next;
//			if(string=="black") {
//				next = g.getBlackPlayer();
//			}else {
//				next = g.getWhitePlayer();
//			}
//			g.getCurrentPosition().setPlayerToMove(next);
//			
//		}
		
//		@Given("There is existing user {string}")
//		public void there_is_existing_user(String string) {
//		    // Write code here that turns the phrase above into concrete actions
//		    
//		}

//		@When("The player provides new user name: {string}")
//		public void the_player_provides_new_user_name(String string) {
//		    // Write code here that turns the phrase above into concrete actions
//			QuoridorController.setUserName(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove(), string);
//		    
//		}
		
		@Then("The player shall be warned that {string} already exists")
		public void the_player_shall_be_warned_that_already_exists(String string) {
		    // Write code here that turns the phrase above into concrete actions
			//TODO
			assertEquals(null, User.getWithName(string));
		    
		}
		
		@Then("Next player to set user name shall be {string}")
		public void next_player_to_set_user_name_shall_be(String string) {
		    // Write code here that turns the phrase above into concrete actions
			Game g = QuoridorApplication.getQuoridor().getCurrentGame();
			Player p;
			if(string=="white") {
				p = g.getWhitePlayer();
			} else {
				p = g.getBlackPlayer();
			}
			g.getCurrentPosition().setPlayerToMove(p);
		    
		}

		

		
		
		// ***********************************************
		// Load Position
		// ***********************************************
		@When("I initiate to load a saved game {string}")
		public void i_initiate_to_load_a_saved_game(String string) throws FileNotFoundException {
			QuoridorController.loadGame(string);
		    
		}

		/*@When("The position to load is valid")
		public void the_position_to_load_is_valid() {
		    assertEquals(true, QuoridorController.validatePosition());
	    
		}*/


		/*@Then("It shall be {string}'s turn")
		public void it_shall_be_s_turn(String string) {
			String toCompare;
			if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove().hasGameAsBlack()) {
				toCompare = "black";
			}else {
				toCompare = "white";
			}
			assertEquals(string, toCompare);
		    
		}*/
			

		/*@Then("{string} shall be at {int}:{int}")
		public void shall_be_at(String string, Integer int1, Integer int2) {
			Integer row;
			Integer col;
			if(string == "black") {
				row = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getRow();
				col = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getColumn();
			}else {
				row = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getRow();
				col = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getColumn();
			}
			assertEquals(row, int1);
			assertEquals(col, int2);
		    
		}*/

		/*@Then("{string} shall have a vertical wall at {int}:{int}")
		public void shall_have_a_vertical_wall_at(String string, Integer int1, Integer int2) {
			Integer col;
			Integer row;
			Direction wallDirection;
			if(string == "black") {
				wallDirection = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer().getWall(0).getMove().getWallDirection();
				col = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard(0).getMove().getTargetTile().getColumn();
				row = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard(0).getMove().getTargetTile().getRow();
			}else {
				wallDirection = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer().getWall(0).getMove().getWallDirection();
				col = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard(0).getMove().getTargetTile().getColumn();
				row = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard(0).getMove().getTargetTile().getRow();
			}
			assertEquals(Direction.Vertical, wallDirection);
			assertEquals(row, int1);
			assertEquals(col, int2);
		    
		}*/

		/*@Then("{string} shall have a horizontal wall at {int}:{int}")
		public void shall_have_a_horizontal_wall_at(String string, Integer int1, Integer int2) {
			Integer col;
			Integer row;
			Direction wallDirection;
			if(string == "black") {
				wallDirection = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer().getWall(0).getMove().getWallDirection();
				col = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard(0).getMove().getTargetTile().getColumn();
				row = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard(0).getMove().getTargetTile().getRow();
			}else {
				wallDirection = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer().getWall(0).getMove().getWallDirection();
				col = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard(0).getMove().getTargetTile().getColumn();
				row = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard(0).getMove().getTargetTile().getRow();
			}
			assertEquals(Direction.Horizontal, wallDirection);
			assertEquals(row, int1);
			assertEquals(col, int2);
		    
		}*/

		/*@Then("Both players shall have {int} in their stacks")
		public void both_players_shall_have_in_their_stacks(Integer int1) {
		    Integer blackwall = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer().getWalls().size();
		    Integer whitewall = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer().getWalls().size();
		    assertEquals(blackwall, int1);
		    assertEquals(whitewall, int1);
		    
		}*/

		/*@When("The position to load is invalid")
		public void the_position_to_load_is_invalid() {
			assertEquals(false, QuoridorController.validatePosition());
		    
		}*/

		/*@Then("The load shall return an error") //what is return error
		public void the_load_shall_return_an_error() {
		    assertEquals("Failed to load game", QuoridorController.getLoadResult());
		    
		}*/

		
		
		// ***********************************************
		// Switch Player
		// ***********************************************
		/*@Given("The player to move is {string}")
		public void the_player_to_move_is_white(String string) {
			Player player;
			if (string == "white") {
				player = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
				QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().setPlayerToMove(player);
			}else {
				player = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
				QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().setPlayerToMove(player);
			}
		}*/

		/*@Given("The clock of {string} is running") 
		public void the_clock_of_is_running(String string) {
			Player player;
			if(string == "black") {
				player = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
			}else {
				player = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
			}
			QuoridorController.startClock(player);
		}*/

		/*@Given("The clock of {string} is stopped")
		public void the_clock_of_is_stopped(String string) {
			Player player;
			if(string == "black") {
				player = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
			}else {
				player = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
			}
			QuoridorController.stopClock(player);
		}*/

		/*@When("Player {string} completes his move") // how to check one has complete move? just create a move method in controller
		public void player_completes_his_move(String string) {
			Player player;
			if(string == "black") {
				player = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
			}else {
				player = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
			}
		    QuoridorController.makeMove(player);
		}*/

		/*@Then("The user interface shall be showing it is {string} turn")
		public void the_user_interface_shall_be_showing_it_is_turn(String string) {
		    
		}*/
		
		/*@Then("The clock of {string} shall be stopped")
		public void the_clock_of_shall_be_stopped(String string) {
			Player player;
			if(string == "black") {
				player = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
			}else {
				player = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
			}
			assertEquals(false, QuoridorController.clockIsRunning(player));
		}*/

		/*@Then("The clock of {string} shall be running")
		public void the_clock_of_shall_be_running(String string) {
			Player player;
			if(string == "black") {
				player = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
			}else {
				player = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
			}
			assertEquals(true, QuoridorController.clockIsRunning(player));
		}*/

		/*@Then("The next player to move shall be {string}")
		public void the_next_player_to_move_shall_be(String string) {
			String toCompare;	
			Player player = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove().getNextPlayer();
			if(player.hasGameAsBlack()) {
				toCompare = "black";
			}else {
				toCompare = "white";
			}
			assertEquals(string, toCompare);
		}*/
		
		@When("The position to load is valid")
		public void the_position_to_load_is_valid() {
			isPositionValid = QuoridorController.validatePosition();		    
		}


		@Then("It shall be {string}'s turn")
		public void it_shall_be_s_turn(String string) {
			String toCompare;
			if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove().hasGameAsBlack()) {
				toCompare = "black";
			}else {
				toCompare = "white";
			}
			assertEquals(string, toCompare);
		    
		}
			

		@Then("{string} shall be at {int}:{int}")
		public void shall_be_at(String string, Integer int1, Integer int2) {
			Integer row;
			Integer col;
			if(string == "black") {
				row = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getRow();
				col = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getColumn();
			}else {
				row = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getRow();
				col = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getColumn();
			}
			assertEquals(row, int1);
			assertEquals(col, int2);
		    
		}

		@Then("{string} shall have a vertical wall at {int}:{int}")
		public void shall_have_a_vertical_wall_at(String string, Integer int1, Integer int2) {
			Integer col;
			Integer row;
			Direction wallDirection;
			if(string == "black") {
				wallDirection = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer().getWall(0).getMove().getWallDirection();
				col = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard(0).getMove().getTargetTile().getColumn();
				row = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard(0).getMove().getTargetTile().getRow();
			}else {
				wallDirection = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer().getWall(0).getMove().getWallDirection();
				col = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard(0).getMove().getTargetTile().getColumn();
				row = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard(0).getMove().getTargetTile().getRow();
			}
			assertEquals(Direction.Vertical, wallDirection);
			assertEquals(row, int1);
			assertEquals(col, int2);
		    
		}

		@Then("{string} shall have a horizontal wall at {int}:{int}")
		public void shall_have_a_horizontal_wall_at(String string, Integer int1, Integer int2) {
			Integer col;
			Integer row;
			Direction wallDirection;
			if(string == "black") {
				wallDirection = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer().getWall(0).getMove().getWallDirection();
				col = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard(0).getMove().getTargetTile().getColumn();
				row = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsOnBoard(0).getMove().getTargetTile().getRow();
			}else {
				wallDirection = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer().getWall(0).getMove().getWallDirection();
				col = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard(0).getMove().getTargetTile().getColumn();
				row = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsOnBoard(0).getMove().getTargetTile().getRow();
			}
			assertEquals(Direction.Horizontal, wallDirection);
			assertEquals(row, int1);
			assertEquals(col, int2);
		    
		}

		@Then("Both players shall have {int} in their stacks")
		public void both_players_shall_have_in_their_stacks(Integer int1) {
		    Integer blackwall = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer().getWalls().size();
		    Integer whitewall = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer().getWalls().size();
		    assertEquals(blackwall, int1);
		    assertEquals(whitewall, int1);
		    
		}

		@When("The position to load is invalid")
		public void the_position_to_load_is_invalid() {
			 isPositionValid = QuoridorController.validatePosition();
		    }

		@Then("The load shall return an error") //what is return error
		public void the_load_shall_return_an_error() {

			assertEquals(false, isPositionValid);

			//assertEquals("Failed to load game", QuoridorController.getLoadResult());
		    
		}

		
		
		// ***********************************************
		// Switch Player
		// ***********************************************
		@Given("The player to move is {string}")
		public void the_player_to_move_is_white(String string) {
			Player player;
			if (string == "white") {
				player = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
				QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().setPlayerToMove(player);
			}else {
				player = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
				QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().setPlayerToMove(player);
			}
		}

		@Given("The clock of {string} is running") 
		public void the_clock_of_is_running(String string) {
			if(string == "black") {
				long nanotime = QuoridorController.startClock();
				blackStartTime = TimeUnit.SECONDS.convert(nanotime, TimeUnit.NANOSECONDS);
			}else {
				long nanotime = QuoridorController.startClock();
				whiteStartTime = TimeUnit.SECONDS.convert(nanotime, TimeUnit.NANOSECONDS);
			}
		}

		@Given("The clock of {string} is stopped")
		public void the_clock_of_is_stopped(String string) {
//			if(string == "black") {
//				long nanotime = QuoridorController.startClock();
//				blackEndTime = TimeUnit.SECONDS.convert(nanotime, TimeUnit.NANOSECONDS);
//			}else {
//				long nanotime = QuoridorController.startClock();
//				whiteEndTime = TimeUnit.SECONDS.convert(nanotime, TimeUnit.NANOSECONDS);
//			}
		}

		@When("Player {string} completes his move") // how to check one has complete move? just create a move method in controller
		public void player_completes_his_move(String string) {
			if(string == "black") {
				Player blackPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
				QuoridorController.completeMove(blackPlayer);
				long nanotimeBlack = QuoridorController.startClock();
				blackEndTime = TimeUnit.SECONDS.convert(nanotimeBlack, TimeUnit.NANOSECONDS);
				long nanotimeWhite = QuoridorController.startClock();
				whiteStartTime = TimeUnit.SECONDS.convert(nanotimeWhite, TimeUnit.NANOSECONDS);
			}else {
				Player whitePlayer = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
				QuoridorController.completeMove(whitePlayer);
				long nanotime = QuoridorController.startClock();
				whiteEndTime = TimeUnit.SECONDS.convert(nanotime, TimeUnit.NANOSECONDS);
				long nanotimeBlack = QuoridorController.startClock();
				blackStartTime = TimeUnit.SECONDS.convert(nanotimeBlack, TimeUnit.NANOSECONDS);
			}
		}

		@Then("The user interface shall be showing it is {string} turn")
		public void the_user_interface_shall_be_showing_it_is_turn(String string) {
		    
		}
		
		@Then("The clock of {string} shall be stopped")
		public void the_clock_of_shall_be_stopped(String string) {
			if(string == "black") {
				Boolean isStopped = (blackEndTime != null);
				assertEquals(true, isStopped);
			}else {
				Boolean isStopped = (whiteEndTime != null);
				assertEquals(true, isStopped);
			}
		}

		@Then("The clock of {string} shall be running")
		public void the_clock_of_shall_be_running(String string) {
			if(string == "black") {
				Boolean isStarted = (blackStartTime != null);
				Boolean notStopped = (blackEndTime == null);
				assertEquals(true, (isStarted && notStopped));
			}else {
				Boolean isStarted = (whiteStartTime != null);
				Boolean notStopped = (whiteEndTime == null);
				assertEquals(true, (isStarted && notStopped));
			}

		}

		@Then("The next player to move shall be {string}")
		public void the_next_player_to_move_shall_be(String string) {
			String toCompare;	
			Player player = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove().getNextPlayer();
			if(player.hasGameAsBlack()) {
				toCompare = "black";
			}else {
				toCompare = "white";
			}
			assertEquals(string, toCompare);
		}
		

		/*
		 * Feature: Set total thinking time.
		 */
		/*@When ("{int}:{int} is set as the thinking time")
		public void is_set_as_the_thinking_time(Integer int1, Integer int2) {
			QuoridorController.setTotalThinkingTime(int1, int2);
			
		}*/

		/*@Then("Both players shall have {int}:{int} remaining time left")
		public void both_players_shall_have_remaining_time_left(Integer int1, Integer int2) {
			int minConversion = 60;
			int secConversion = 1000;
			long millisec = (int1 * minConversion + int2 ) * secConversion;
			Time timeLeft = new Time(millisec);
			assertEquals(Time.valueOf(timeLeft.toString()),Time.valueOf(QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer().getRemainingTime().toString()));
			assertEquals(Time.valueOf(timeLeft.toString()),Time.valueOf(QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer().getRemainingTime().toString()));
			
		}*/
		/*
		 * Feature: Initialize board
		 */
		/*@When ("The initialization of the board is initiated")
		public void the_initialization_of_the_board_is_initiated() {
			QuoridorController.initializeBoard();
			
		}*/

		/*@Then("It shall be white player to move")
		public void it_shall_be_white_player_to_move() {		
			boolean whiteToMove = false;
			if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove().equals(QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer())) {
				whiteToMove = true;
			}
			assertEquals(true,whiteToMove);
			
		}*/

		/*@Then("White's pawn shall be in its initial position")
		public void white_s_pawn_shall_be_in_its_initial_position() {
			//white starts from e9
			int whiteRow = 9;
			int whiteColumn = 'e';
			int whiteCurrentRow = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getRow();
			int whiteCurrentColumn = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getColumn();
			assertEquals(whiteRow,whiteCurrentRow);
			assertEquals(whiteColumn,whiteCurrentColumn);
			
		}*/
		
		/*@Then("Black's pawn shall be in its initial position")
		public void black_s_pawn_shall_be_in_its_initial_position() {
			//black starts from e1, e is column and 1 is row
			int blackRow = 1;
			int blackColumn = 'e';
			int blackCurrentRow = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getRow();
			int blackCurrentColumn = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackPosition().getTile().getColumn();
			assertEquals(blackRow,blackCurrentRow);
			assertEquals(blackColumn,blackCurrentColumn);
			
		}*/

		/*@Then("All of White's walls shall be in stock")
		public void all_of_White_s_walls_shall_be_in_stock() {
			assertEquals(10,QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer().numberOfWalls());
			
		}*/

		/*@Then("All of Black's walls shall be in stock")
		public void all_of_Black_s_walls_shall_be_in_stock() {
			assertEquals(10,QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer().numberOfWalls());
			
		}*/

		//TODO: Duplicate controller method needed
		/*@Then("White's clock shall be counting down")
		public void white_s_clock_shall_be_counting_down() {
			boolean clockIsRunning = 
					QuoridorController.clockIsCountingDown(QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer());
			assertEquals(true, clockIsRunning);
			
		}*/

		/*@Then("It shall be shown that this is White's turn")
		public void it_shall_be_shown_that_this_is_White_s_turn() {
			//This is a GUI related step. 
			boolean whiteToMove = false;
			if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove().equals(QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer())) {
				whiteToMove = true;
			}
			assertEquals(true,whiteToMove);
			
		}*/

		/*
		 * Feature: Set total thinking time.
		 */
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
			
		}

		@Then("It shall be white player to move")
		public void it_shall_be_white_player_to_move() {		
			boolean whiteToMove = false;
			if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove().equals(QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer())) {
				whiteToMove = true;
			}
			assertEquals(true,whiteToMove);
			

		}

		@Then("White's pawn shall be in its initial position")
		public void white_s_pawn_shall_be_in_its_initial_position() {
			//white starts from e9
			int whiteRow = 9;
			int whiteColumn = 'e';
			int whiteCurrentRow = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getRow();
			int whiteCurrentColumn = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhitePosition().getTile().getColumn();
			assertEquals(whiteRow,whiteCurrentRow);
			assertEquals(whiteColumn,whiteCurrentColumn);
			

		}
		
		@Then("Black's pawn shall be in its initial position")
		public void black_s_pawn_shall_be_in_its_initial_position() {
			//black starts from e1, e is column and 1 is row
			int blackRow = 1;
			int blackColumn = 'e';
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

		//TODO: Duplicate controller method needed
		@Then("White's clock shall be counting down")
		public void white_s_clock_shall_be_counting_down() {
			boolean clockIsRunning = 
					QuoridorController.clockIsCountingDown(QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer());
			assertEquals(true, clockIsRunning);
			

		}

		@Then("It shall be shown that this is White's turn")
		public void it_shall_be_shown_that_this_is_White_s_turn() {
			//This is a GUI related step. 
			boolean whiteToMove = false;
			if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove().equals(QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer())) {
				whiteToMove = true;
			}
			assertEquals(true,whiteToMove);
//			try {
//			load()
//			fail()
//			} catch {
//				save the variable / set flag ...
//			}
		}
		
		
		
		
		
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//----------------------------------------------------------MovePlayer-------------------------------------------------------------------
		
		@Given("The player is located at {int}:{int}")
		public void the_player_is_located_at(Integer int1, Integer int2) {
			Tile tile = new Tile(int1, int2, QuoridorApplication.getQuoridor().getBoard());
			Player aPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
			String string;
			Wall newWallFromStock;
			//WallMove wallmove;
			
			
			if(aPlayer.hasGameAsBlack()) {
				string = "black";
			}
			else {
				string = "white";
			}	
			QuoridorController.verifyPawnPosition(string,tile);
			if(QuoridorController.verifyPawnPosition(string,tile)==false) {
				Assert.fail();
			}

		}

		@Given("There are no {string} walls {string} from the player")
		public void there_are_no_walls_from_the_player(String string, String string2) {
			
			QuoridorController.verifythere_are_no_walls_from_the_player(string,string2);
			if(QuoridorController.verifythere_are_no_walls_from_the_player(string,string2)==false) {
				Assert.fail();
			}
			
		    
		}

		@Given("The opponent is not {string} from the player")
		public void the_opponent_is_not_from_the_player(String string) {
		    
			if(QuoridorController.verifytthe_opponent_is_not_from_the_player(string)==false) {
				Assert.fail();
			}
			
		}

		@When("Player {string} initiates to move {string}")
		public void player_initiates_to_move(String string, String string2) {
			QuoridorController.movePlayer(string, string2);

		}

		@Then("The move {string} shall be {string}")
		public void the_move_shall_be(String string, String string2) {
			Assert.assertEquals(string, string2);
		}

		@Then("Player's new position shall be {int}:{int}")
		public void player_s_new_position_shall_be(Integer int1, Integer int2) {
			Assert.assertEquals(int1, int2);
		}

		@Then("The next player to move shall become {string}")
		public void the_next_player_to_move_shall_become(String string) {
			Assert.assertEquals(string, QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getWallDirection());
		}

		@Given("There is a {string} wall {string} from the player")
		public void there_is_a_wall_from_the_player(String string, String string2) {
			QuoridorController.verifythere_are_no_walls_from_the_player(string,string2);
			if(QuoridorController.verifythere_are_no_walls_from_the_player(string,string2)==true) {
				Assert.fail();
			}
		}

		@Given("My opponent is not {string} from the player")
		public void my_opponent_is_not_from_the_player(String string) {
			if(QuoridorController.verifytthe_opponent_is_not_from_the_player(string)==true) {
				Assert.fail();
			}
			
		}


		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	// ***********************************************
	// Clean up
	// ***********************************************

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
			Wall wall = Wall.getWithId(i);
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
				new Wall(i * 10 + j, players[i]);
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