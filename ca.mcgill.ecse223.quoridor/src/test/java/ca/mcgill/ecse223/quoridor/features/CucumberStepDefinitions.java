package ca.mcgill.ecse223.quoridor.features;

import static org.junit.Assert.assertEquals;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import ca.mcgill.ecse223.quoridor.controller.QuoridorController;
import ca.mcgill.ecse223.quoridor.QuoridorApplication;
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
	
	@Given("^A new game is initializing$")
	public void aNewGameIsInitializing() throws Throwable {
		initQuoridorAndBoard();
		ArrayList<Player> players = createUsersAndPlayers("user1", "user2");
		new Game(GameStatus.Initializing, MoveMode.PlayerMove, players.get(0), players.get(1), QuoridorApplication.getQuoridor());
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
	
	// ***********************************************
	// Load Position
	// ***********************************************
	@When("I initiate to load a saved game {string}")
	public void i_initiate_to_load_a_saved_game(String string) {
		QuoridorController.loadGame(string);
	    throw new cucumber.api.PendingException();
	}

	@When("The position to load is valid")
	public void the_position_to_load_is_valid() {
	    assertEquals(true, QuoridorController.validatePosition());
	    throw new cucumber.api.PendingException();
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
	    throw new cucumber.api.PendingException();
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
	    throw new cucumber.api.PendingException();
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
	    throw new cucumber.api.PendingException();
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
	    throw new cucumber.api.PendingException();
	}

	@Then("Both players shall have {int} in their stacks")
	public void both_players_shall_have_in_their_stacks(Integer int1) {
	    Integer blackwall = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer().getWalls().size();
	    Integer whitewall = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer().getWalls().size();
	    assertEquals(blackwall, int1);
	    assertEquals(whitewall, int1);
	    throw new cucumber.api.PendingException();
	}

	@When("The position to load is invalid")
	public void the_position_to_load_is_invalid() {
		assertEquals(false, QuoridorController.validatePosition());
	    throw new cucumber.api.PendingException();
	}

	@Then("The load shall return an error") //what is return error
	public void the_load_shall_return_an_error() {
	    assertEquals(false, QuoridorController.getLoadResult());
	    throw new cucumber.api.PendingException();
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
		Player player;
		if(string == "black") {
			player = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
		}else {
			player = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
		}
		QuoridorController.startClock(player);
	}

	@Given("The clock of {string} is stopped")
	public void the_clock_of_is_stopped(String string) {
		Player player;
		if(string == "black") {
			player = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
		}else {
			player = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
		}
		QuoridorController.stopClock(player);
	}

	@When("Player {string} completes his move") // how to check one has complete move? just create a move method in controller
	public void player_completes_his_move(String string) {
		Player player;
		if(string == "black") {
			player = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
		}else {
			player = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
		}
	    QuoridorController.makeMove(player);
	}

	@Then("The user interface shall be showing it is {string} turn")
	public void the_user_interface_shall_be_showing_it_is_turn(String string) {
	    
	}
	
	@Then("The clock of {string} shall be stopped")
	public void the_clock_of_shall_be_stopped(String string) {
		Player player;
		if(string == "black") {
			player = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
		}else {
			player = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
		}
		assertEquals(false, QuoridorController.clockIsRunning(player));
	}

	@Then("The clock of {string} shall be running")
	public void the_clock_of_shall_be_running(String string) {
		Player player;
		if(string == "black") {
			player = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
		}else {
			player = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
		}
		assertEquals(true, QuoridorController.clockIsRunning(player));
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
