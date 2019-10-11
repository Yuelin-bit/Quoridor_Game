package ca.mcgill.ecse223.quoridor.features;

import static org.junit.Assert.assertEquals;

import java.sql.Time;
import java.util.List;
import java.util.Map;

import org.junit.After;

import ca.mcgill.ecse223.quoridor.controller.QuoridorController;
import ca.mcgill.ecse223.quoridor.model.Board;
import ca.mcgill.ecse223.quoridor.model.Direction;
import ca.mcgill.ecse223.quoridor.model.Game;
import ca.mcgill.ecse223.quoridor.model.Game.GameStatus;
import ca.mcgill.ecse223.quoridor.model.Game.MoveMode;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ca.mcgill.ecse223.quoridor.model.GamePosition;
import ca.mcgill.ecse223.quoridor.model.Player;
import ca.mcgill.ecse223.quoridor.model.PlayerPosition;
import ca.mcgill.ecse223.quoridor.model.Quoridor;
import ca.mcgill.ecse223.quoridor.model.Tile;
import ca.mcgill.ecse223.quoridor.model.User;
import ca.mcgill.ecse223.quoridor.model.Wall;
import ca.mcgill.ecse223.quoridor.model.WallMove;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class CucumberStepDefinitions {

	private Quoridor quoridor;
	private Board board;
	private Player player1;
	private Player player2;
	private Player currentPlayer;
	private Game game;

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
		theGameIsNotRunning();
		createAndStartGame();
	}

	@And("^It is my turn to move$")
	public void itIsMyTurnToMove() throws Throwable {
		currentPlayer = player1;
		game.getCurrentPosition().setPlayerToMove(currentPlayer);
	}

	@And("^I have a wall in my hand over the board$")
	public void iHaveAWallInMyHandOverTheBoard() throws Throwable {
		// Walls are in stock for all players
	}

	@Given("The following walls exist:")
	public void theFollowingWallsExist(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> valueMaps = dataTable.asMaps();
		// keys: wrow, wcol, wdir
		Player[] players = { player1, player2 };
		int playerIdx = 0;
		int wallIdxForPlayer = 0;
		for (Map<String, String> map : valueMaps) {
			Integer wrow = Integer.decode(map.get("wrow"));
			Integer wcol = Integer.decode(map.get("wcol"));
			// Wall to place
			// Walls are placed on an alternating basis wrt. the owners
			Wall wall = Wall.getWithId(playerIdx * 10 + wallIdxForPlayer);

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
			new WallMove(0, 1, players[playerIdx], board.getTile((wrow - 1) * 9 + wcol - 1), game, direction, wall);
			if (playerIdx == 0) {
				game.getCurrentPosition().removeWhiteWallsInStock(wall);
				game.getCurrentPosition().addWhiteWallsOnBoard(wall);
			} else {
				game.getCurrentPosition().removeBlackWallsInStock(wall);
				game.getCurrentPosition().addBlackWallsOnBoard(wall);
			}
			wallIdxForPlayer = wallIdxForPlayer + playerIdx;
			playerIdx++;
			playerIdx = playerIdx % 2;
		}
		System.out.println();

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
<<<<<<< HEAD
	@Given("No file save_game_test.dat exists in the filesystem")
	public void no_file_save_game_test_dat_exists_in_the_filesystem() {
	    // Write code here that turns the phrase above into concrete actions
		
	}

	@When("The user initiates to save the game with name save_game_test.dat")
	public void the_user_initiates_to_save_the_game_with_name_save_game_test_dat() {
	    // Write code here that turns the phrase above into concrete actions
			
	}
	
	

	@Then("A file with save_game_test.dat is created in the filesystem")
	public void a_file_with_save_game_test_dat_is_created_in_the_filesystem() {
=======
	
	@Given("A new game is initializing")
	public void a_new_game_is_initializing(Game game) {
	    //TODO: Write code here that turns the phrase above into concrete actions
		QuoridorController.getGameStatus(game);
	    throw new cucumber.api.PendingException();
	}
	
	@When("White player chooses a username")
	public void white_player_chooses_a_username(User user, String name) {
	    //TODO: Write code here that turns the phrase above into concrete actions
		QuoridorController.updateNewUserName(user, name);
	    throw new cucumber.api.PendingException();
	}

	@When("Black player chooses a username")
	public void black_player_chooses_a_username(User user, String name) {
	    //TODO: Write code here that turns the phrase above into concrete actions
		QuoridorController.updateNewUserName(user, name);
	    throw new cucumber.api.PendingException();
	}
	
	@Then("The game is ready to start")
	public void the_game_is_ready_to_start(User user1, User user2, int thinking_time_seconds) {
		//TODO: Write code here that turns the phrase above into concrete actions
		QuoridorController.initializeNewGame(user1, user2, thinking_time_seconds);
		throw new cucumber.api.PendingException();
	}
	
	@When("I initiate to load a saved game quoridor_test_game_{int}.dat")
	public void i_initiate_to_load_a_saved_game_quoridor_test_game__dat(Integer int1) {
		// CALL METHOD FROM CONTROLLER
		// QuoridorController.callMethod(int1);
		// IN CONTROLLER:
		// Write signature so...
		// public void saveGame(int number){ //TO-DO: Write logic to save game
		// throw new UnsupportedOperatingException();
		// }
		QuoridorController.loadGame(int1);
	    throw new cucumber.api.PendingException();
	}

	@When("The position is valid")
	public void the_position_is_valid() {
	    QuoridorController.validatePosition();
	    throw new cucumber.api.PendingException();
	}

	@Then("It is player's turn")
	public void it_is_player_s_turn() {
		assertEquals(true, game.getCurrentPosition().setPlayerToMove(currentPlayer));
	    throw new cucumber.api.PendingException();
	}

	@Then("player is at {int}:{int}")
	public void player_is_at(Integer int1, Integer int2) {
		int row = QuoridorController.getPlayerTile("Player").getRow();
		int col = QuoridorController.getPlayerTile("Player").getColumn();
		assertEquals(row, 7);
		assertEquals(col, 7);
	    throw new cucumber.api.PendingException();
	}

	@Then("white is at {int}:{int}")
	public void white_is_at(Integer int1, Integer int2) {
		int row = QuoridorController.getWhiteTile().getRow();
		int col = QuoridorController.getWhiteTile().getColumn();
		assertEquals(row, 7);
		assertEquals(col, 7);
	    throw new cucumber.api.PendingException();
	}

	@Then("player has a vertical wall at {int}:{int}")
	public void player_has_a_vertical_wall_at(Integer int1, Integer int2) {
>>>>>>> 17fe7dbabf16f2c9511cdb403528b133511079e0
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

<<<<<<< HEAD
	@Given("File save_game_test.dat exists in the filesystem")
	public void file_save_game_test_dat_exists_in_the_filesystem() {
=======
	@Then("white has a horizontal wall at {int}:{int}")
	public void white_has_a_horizontal_wall_at(Integer int1, Integer int2) {
>>>>>>> 17fe7dbabf16f2c9511cdb403528b133511079e0
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

<<<<<<< HEAD
	@When("The user confirms to overwrite existing file")
	public void the_user_confirms_to_overwrite_existing_file() {
=======
	@Then("Both players have {int} in their stacks")
	public void both_players_have_in_their_stacks(Integer int1) {
>>>>>>> 17fe7dbabf16f2c9511cdb403528b133511079e0
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

<<<<<<< HEAD
	@Then("File with save_game_test.dat is updated in the filesystem")
	public void file_with_save_game_test_dat_is_updated_in_the_filesystem() {
=======
	@When("I initiate to load a saved game quoridor_test_game_invalid_pawn.dat")
	public void i_initiate_to_load_a_saved_game_quoridor_test_game_invalid_pawn_dat() {
>>>>>>> 17fe7dbabf16f2c9511cdb403528b133511079e0
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

<<<<<<< HEAD
	@When("The user cancels to overwrite existing file")
	public void the_user_cancels_to_overwrite_existing_file() {
=======
	@When("The position is invalid")
	public void the_position_is_invalid() {
>>>>>>> 17fe7dbabf16f2c9511cdb403528b133511079e0
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

<<<<<<< HEAD
	@Then("File save_game_test.dat is not changed in the filesystem")
	public void file_save_game_test_dat_is_not_changed_in_the_filesystem() {
=======
	@Then("The load returns error")
	public void the_load_returns_error() {
>>>>>>> 17fe7dbabf16f2c9511cdb403528b133511079e0
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

<<<<<<< HEAD
	@Given("A game position is supplied with pawn coordinate {int}:{int}")
	public void a_game_position_is_supplied_with_pawn_coordinate(Integer int1, Integer int2) {
=======
	@When("I initiate to load a saved game quoridor_test_game_invalid_wall_overlap_.dat")
	public void i_initiate_to_load_a_saved_game_quoridor_test_game_invalid_wall_overlap__dat() {
>>>>>>> 17fe7dbabf16f2c9511cdb403528b133511079e0
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

<<<<<<< HEAD
	@When("Validation of the position is initiated")
	public void validation_of_the_position_is_initiated() {
=======
	@When("I initiate to load a saved game quoridor_test_game_invalid_wall_out-of-track.dat")
	public void i_initiate_to_load_a_saved_game_quoridor_test_game_invalid_wall_out_of_track_dat() {
>>>>>>> 17fe7dbabf16f2c9511cdb403528b133511079e0
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

<<<<<<< HEAD
	@Then("The position is ok")
	public void the_position_is_ok() {
=======
	@Given("The player to move is white")
	public void the_player_to_move_is_white() {
>>>>>>> 17fe7dbabf16f2c9511cdb403528b133511079e0
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

<<<<<<< HEAD
	@Then("The position is error")
	public void the_position_is_error() {
=======
	@Given("The clock of white is running")
	public void the_clock_of_white_is_running() {
>>>>>>> 17fe7dbabf16f2c9511cdb403528b133511079e0
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

<<<<<<< HEAD
	@Given("A game position is supplied with wall coordinate {int}:{int}-horizontal")
	public void a_game_position_is_supplied_with_wall_coordinate_horizontal(Integer int1, Integer int2) {
=======
	@Given("The clock of black is stopped")
	public void the_clock_of_black_is_stopped() {
>>>>>>> 17fe7dbabf16f2c9511cdb403528b133511079e0
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

<<<<<<< HEAD
	@Given("A game position is supplied with wall coordinate {int}:{int}-vertical")
	public void a_game_position_is_supplied_with_wall_coordinate_vertical(Integer int1, Integer int2) {
=======
	@When("Player white completes his move")
	public void player_white_completes_his_move() {
>>>>>>> 17fe7dbabf16f2c9511cdb403528b133511079e0
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

<<<<<<< HEAD
	@Then("The position is valid")
	public void the_position_is_valid() {
=======
	@Then("The clock of white is stopped")
	public void the_clock_of_white_is_stopped() {
>>>>>>> 17fe7dbabf16f2c9511cdb403528b133511079e0
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

<<<<<<< HEAD
	@Then("The position is invalid")
	public void the_position_is_invalid() {
=======
	@Then("The clock of black is running")
	public void the_clock_of_black_is_running() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("The player to move is black")
	public void the_player_to_move_is_black() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("The user interface is showing it is black's turn")
	public void the_user_interface_is_showing_it_is_black_s_turn() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("The player to move is black")
	public void the_player_to_move_is_black() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("The clock of black is running")
	public void the_clock_of_black_is_running() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("The clock of white is stopped")
	public void the_clock_of_white_is_stopped() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("Player black completes his move")
	public void player_black_completes_his_move() {
>>>>>>> 17fe7dbabf16f2c9511cdb403528b133511079e0
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

<<<<<<< HEAD
=======
	@Then("The clock of black is stopped")
	public void the_clock_of_black_is_stopped() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("The clock of white is running")
	public void the_clock_of_white_is_running() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("The player to move is white")
	public void the_player_to_move_is_white() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("The user interface is showing it is white's turn")
	public void the_user_interface_is_showing_it_is_white_s_turn() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}


>>>>>>> 17fe7dbabf16f2c9511cdb403528b133511079e0

	// ***********************************************
	// Clean up
	// ***********************************************

	// After each scenario, the test model is discarded
	@After
	public void tearDown() {
		quoridor.delete();
		quoridor = null;
	}

	// ***********************************************
	// Extracted helper methods
	// ***********************************************

	// Place your extracted methods below

	private void initQuoridorAndBoard() {
		quoridor = new Quoridor();
		board = new Board(quoridor);
		// Creating tiles by rows, i.e., the column index changes with every tile
		// creation
		for (int i = 1; i <= 9; i++) { // rows
			for (int j = 1; j <= 9; j++) { // columns
				board.addTile(i, j);
			}
		}
	}

	private void createUsersAndPlayers(String userName1, String userName2) {
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
		player1 = new Player(new Time(thinkingTime), user1, 9, Direction.Horizontal);
		player2 = new Player(new Time(thinkingTime), user2, 1, Direction.Horizontal);

		Player[] players = { player1, player2 };

		// Create all walls. Walls with lower ID belong to player1,
		// while the second half belongs to player 2
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 10; j++) {
				new Wall(i * 10 + j, players[i]);
			}
		}
	}

	private void createAndStartGame() {
		// There are total 36 tiles in the first four rows and
		// indexing starts from 0 -> tiles with indices 36 and 36+8=44 are the starting
		// positions
		Tile player1StartPos = board.getTile(36);
		Tile player2StartPos = board.getTile(44);

		PlayerPosition player1Position = new PlayerPosition(player1, player1StartPos);
		PlayerPosition player2Position = new PlayerPosition(player2, player2StartPos);

		game = new Game(GameStatus.Running, MoveMode.PlayerMove, player1, player2, quoridor);
		GamePosition gamePosition = new GamePosition(0, player1Position, player2Position, player1, game);

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
