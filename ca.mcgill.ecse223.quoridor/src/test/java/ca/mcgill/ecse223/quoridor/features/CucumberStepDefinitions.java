package ca.mcgill.ecse223.quoridor.features;

import static org.junit.Assert.assertEquals;
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
	/*
	 * Feature: Set total thinking time.
	 */
	@When ("{int}:{int} is set as the thinking time")
	public void is_set_as_the_thinking_time(Integer int1, Integer int2) {
		QuoridorController.setTotalThinkingTime(int1, int2);
		throw new cucumber.api.PendingException();
	}

	@Then("Both players shall have {int}:{int} remaining time left")
	public void both_players_shall_have_remaining_time_left(Integer int1, Integer int2) {
		int minConversion = 60;
		int secConversion = 1000;
		long millisec = (int1 * minConversion + int2 ) * secConversion;
		Time timeLeft = new Time(millisec);
		assertEquals(Time.valueOf(timeLeft.toString()),Time.valueOf(QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer().getRemainingTime().toString()));
		assertEquals(Time.valueOf(timeLeft.toString()),Time.valueOf(QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer().getRemainingTime().toString()));
		throw new cucumber.api.PendingException();

	}
	/*
	 * Feature: Initialize board
	 */
	@When ("The initialization of the board is initiated")
	public void the_initialization_of_the_board_is_initiated() {
		QuoridorController.initializeBoard();
		throw new cucumber.api.PendingException();
	}

	@Then("It shall be white player to move")
	public void it_shall_be_white_player_to_move() {		
		boolean whiteToMove = false;
		if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove().equals(QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer())) {
			whiteToMove = true;
		}
		assertEquals(true,whiteToMove);
		throw new cucumber.api.PendingException();

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
		throw new cucumber.api.PendingException();

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
		throw new cucumber.api.PendingException();
	}

	@Then("All of White's walls shall be in stock")
	public void all_of_White_s_walls_shall_be_in_stock() {
		assertEquals(10,QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer().numberOfWalls());
		throw new cucumber.api.PendingException();

	}

	@Then("All of Black's walls shall be in stock")
	public void all_of_Black_s_walls_shall_be_in_stock() {
		assertEquals(10,QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer().numberOfWalls());
		throw new cucumber.api.PendingException();

	}

	//TODO: Duplicate controller method needed
	@Then("White's clock shall be counting down")
	public void white_s_clock_shall_be_counting_down() {
		boolean clockIsRunning = 
				QuoridorController.clockIsRunning(QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer());
		assertEquals(true, clockIsRunning);
		throw new cucumber.api.PendingException();

	}

	@Then("It shall be shown that this is White's turn")
	public void it_shall_be_shown_that_this_is_White_s_turn() {
		//This is a GUI related step. 
		boolean whiteToMove = false;
		if(QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition().getPlayerToMove().equals(QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer())) {
			whiteToMove = true;
		}
		assertEquals(true,whiteToMove);
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
