package ca.mcgill.ecse223.quoridor.features;
//import static org.junit.Assert.assertEquals;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	
	// ***********************************************
	// Scenario and scenario outline step definitions
	// ***********************************************

	/*
	 * TODO Insert your missing step definitions here
	 * 
	 * Call the methods of the controller that will manipulate the model once they
	 * are implemented
	 * 
	 */
	
	
	@Given("The wall move candidate with {string} at position \\({int}, {int}) is valid")
	public void the_wall_move_candidate_with_at_position_is_valid(String string, Integer int1, Integer int2) {		
			Tile tile = new Tile(int1, int2, QuoridorApplication.getQuoridor().getBoard());
			Player aPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
			int a = QuoridorApplication.getQuoridor().getCurrentGame().getMoves().size();
			Wall newWallFromStock;
			WallMove wallmove;
			Direction dir;
			if(string.equalsIgnoreCase("vertical")) {
				dir = Direction.Vertical;
			}
			else {
				dir = Direction.Horizontal;
			}
			if(aPlayer.hasGameAsBlack()) {
				newWallFromStock = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsInStock().get(0);
			}
			else {
				newWallFromStock = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsInStock().get(0);
			}
			
			if((QuoridorController.TileValid(tile,dir)==false)) {
					wallmove = new WallMove(a+1, (a+1)/2, aPlayer, tile, 
					QuoridorApplication.getQuoridor().getCurrentGame(), dir, newWallFromStock );
					QuoridorApplication.getQuoridor().getCurrentGame().setWallMoveCandidate(wallmove);	
			}
	}

	
	@When("I release the wall in my hand")
	public void i_release_the_wall_in_my_hand() {
		QuoridorController.ReleaseWall();
	    throw new cucumber.api.PendingException();
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
		Assert.assertEquals(tile, QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition());
		Assert.assertEquals(dir, QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getWallDirection());
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@Then("I shall not have a wall in my hand")
	public void i_shall_not_have_a_wall_in_my_hand() {
		// GUI-related feature -- TODO for later
		throw new cucumber.api.PendingException();
	}

	@Then("My move shall be completed")
	public void my_move_shall_be_completed() {
		int a = QuoridorApplication.getQuoridor().getCurrentGame().getMoves().size();
		Assert.assertEquals(true, QuoridorApplication.getQuoridor().getCurrentGame().getMove(a).hasNextMove());
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@Then("It shall not be my turn to move")
	public void it_shall_not_be_my_turn_to_move() {
		Assert.assertEquals(true, QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove().hasNextPlayer());
	    // Write code here that turns the phrase above into concrete actions
	   
	}
	
	
	@Given("The wall move candidate with {string} at position \\({int}, {int}) is invalid")
	public void the_wall_move_candidate_with_at_position_is_invalid(String string, Integer int1, Integer int2) {
		Tile tile = new Tile(int1, int2, QuoridorApplication.getQuoridor().getBoard());
		Player aPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
		int a = QuoridorApplication.getQuoridor().getCurrentGame().getMoves().size();
		Wall newWallFromStock;
		WallMove wallmove;
		Direction dir;
		if(string.equalsIgnoreCase("vertical")) {
			dir = Direction.Vertical;
		}
		else {
			dir = Direction.Horizontal;
		}
		if(aPlayer.hasGameAsBlack()) {
			newWallFromStock = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getBlackWallsInStock().get(0);
		}
		else {
			newWallFromStock = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getWhiteWallsInStock().get(0);
		}
		
		if((QuoridorController.TileValid(tile,dir)==true)) {
				wallmove = new WallMove(a+1, (a+1)/2, aPlayer, tile, 
				QuoridorApplication.getQuoridor().getCurrentGame(), dir, newWallFromStock );
				QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().delete();;	
		}
}
	    
	

	@Then("I shall be notified that my wall move is invalid")
	public void i_shall_be_notified_that_my_wall_move_is_invalid() {
		//It could be GUI
	    //TA said that I could fill this out later.
		throw new cucumber.api.PendingException();
	}

	@Then("I shall have a wall in my hand over the board")
	public void i_shall_have_a_wall_in_my_hand_over_the_board() {
		// GUI-related feature -- TODO for later
		throw new cucumber.api.PendingException();
	    
	}

	@Then("It shall be my turn to move")
	public void it_shall_be_my_turn_to_move() {
		Assert.assertEquals(false, QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove().hasNextPlayer());
	    // Write code here that turns the phrase above into concrete actions
	    
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
		Assert.assertEquals(false,QuoridorApplication.getQuoridor().getCurrentGame().);
		
	   
	}
	
	////
	////
	////   
	////
	////
	////   Move Wall
	
	@Given("A wall move candidate exists with {string} at position \\({int}, {int})")
	public void a_wall_move_candidate_exists_with_at_position(String string, Integer int1, Integer int2) {
		Player currentPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove();
		Tile WallTile = new Tile (int1, int2, QuoridorApplication.getQuoridor().getBoard() );
					
		if(currentPlayer.hasGameAsBlack()) {
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

	
	@Given("The wall candidate is not at the {string} edge of the board")
	public void the_wall_candidate_is_not_at_the_edge_of_the_board(String string) {
	    // Write code here that turns the phrase above into concrete actions
		//QuoridorController.ExceedBoard(int a);
		if(string.equals("vertical")) {
			Assert.assertEquals(false, QuoridorController.ExceedBoard(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getRow()));
	}
		if(string.equals("horizontal")) {
			Assert.assertEquals(false,  QuoridorController.ExceedBoard(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getColumn()));
		}
	}

	@When("I try to move the wall {string}")
	public void i_try_to_move_the_wall(String string) {
		QuoridorController.MoveWall(string);	
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@Then("The wall shall be moved over the board to position \\({int}, {int})")
	public void the_wall_shall_be_moved_over_the_board_to_position(Integer int1, Integer int2) {
		Tile tile = new Tile(int1, int2, QuoridorApplication.getQuoridor().getBoard());
		Assert.assertEquals(true,QuoridorApplication.getQuoridor().getCurrentGame().setCurrentPosition(tile));
		
	    // Write code here that turns the phrase above into concrete actions
	    
	}

	@Then("A wall move candidate shall exist with {string} at position \\({int}, {int})")
	public void a_wall_move_candidate_shall_exist_with_at_position(String string, Integer int1, Integer int2) {
	    Board newBoard = QuoridorApplication.getQuoridor().getBoard();
		Tile tile = new Tile(int1, int2, newBoard);
		Assert.assertEquals(tile, QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition());
		Assert.assertEquals(string, QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getWallDirection());
		//Assert.assertEquals(int1, QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getRow());
		//Assert.assertEquals(int2, QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getColumn());
	    // Write code here that turns the phrase above into concrete actions    
	}

	@Given("The wall candidate is at the {string} edge of the board")
	public void the_wall_candidate_is_at_the_edge_of_the_board(String string) {
	    // Write code here that turns the phrase above into concrete actions
		if(string.equals("vertical")) {
			Assert.assertEquals(true, QuoridorController.ExceedBoard(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getRow()));
	}
		if(string.equals("horizontal")) {
			Assert.assertEquals(true,  QuoridorController.ExceedBoard(QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile().getColumn()));
		}
	   
	}
	@Then("I shall be notified that my move is illegal")
	public void i_shall_be_notified_that_my_move_is_illegal() {
		//It could be GUI
	    //TA said that I could fill this out later.
		throw new cucumber.api.PendingException();
	   
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
		
		Game game = new Game(GameStatus.Running, MoveMode.PlayerMove, players.get(0), players.get(1), quoridor);

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
