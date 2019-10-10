package ca.mcgill.ecse223.quoridor.features;

import java.sql.Time;
import java.util.List;
import java.util.Map;

import ca.mcgill.ecse223.quoridor.model.Board;
import ca.mcgill.ecse223.quoridor.model.Direction;
import ca.mcgill.ecse223.quoridor.model.Game;
import ca.mcgill.ecse223.quoridor.model.Game.GameStatus;
import ca.mcgill.ecse223.quoridor.model.Game.MoveMode;
/*import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;*/
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
	
	@Given("The wall move candidate with horizontal at position \\({int}, {int}) is valid")
	public void the_wall_move_candidate_with_horizontal_at_position_is_valid(Integer int1, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("I release the wall in my hand")
	public void i_release_the_wall_in_my_hand() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("I do not have a wall in my hand")
	public void i_do_not_have_a_wall_in_my_hand() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("A wall move is registered with horizontal at position \\({int}, {int})")
	public void a_wall_move_is_registered_with_horizontal_at_position(Integer int1, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("My move is completed")
	public void my_move_is_completed() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("It is not my turn to move")
	public void it_is_not_my_turn_to_move() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("The wall move candidate with vertical at position \\({int}, {int}) is valid")
	public void the_wall_move_candidate_with_vertical_at_position_is_valid(Integer int1, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("A wall move is registered with vertical at position \\({int}, {int})")
	public void a_wall_move_is_registered_with_vertical_at_position(Integer int1, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("The wall move candidate with vertical at position \\({int}, {int}) is invalid")
	public void the_wall_move_candidate_with_vertical_at_position_is_invalid(Integer int1, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("I shall be notified that my wall move is invalid")
	public void i_shall_be_notified_that_my_wall_move_is_invalid() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("No wall move is registered with vertical at position \\({int}, {int})")
	public void no_wall_move_is_registered_with_vertical_at_position(Integer int1, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("The wall move candidate with horizontal at position \\({int}, {int}) is invalid")
	public void the_wall_move_candidate_with_horizontal_at_position_is_invalid(Integer int1, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("No wall move is registered with horizontal at position \\({int}, {int})")
	public void no_wall_move_is_registered_with_horizontal_at_position(Integer int1, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	//The above is DropWall
	
	
	
	@Given("I have more walls on stock")
	public void i_have_more_walls_on_stock() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("I try to grab a wall from my stock")
	public void i_try_to_grab_a_wall_from_my_stock() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("The wall in my hand should disappear from my stock")
	public void the_wall_in_my_hand_should_disappear_from_my_stock() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("A wall move candidate shall be created at initial position")
	public void a_wall_move_candidate_shall_be_created_at_initial_position() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("I have no more walls on stock")
	public void i_have_no_more_walls_on_stock() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("I should be notified that I have no more walls")
	public void i_should_be_notified_that_I_have_no_more_walls() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	
	// The above is GrabWall
	
	
	@Given("A wall move candidate exists with vertical at position \\({int}, {int})")
	public void a_wall_move_candidate_exists_with_vertical_at_position(Integer int1, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("The wall candidate is not at the left edge of the board")
	public void the_wall_candidate_is_not_at_the_left_edge_of_the_board() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("I try to move the wall left")
	public void i_try_to_move_the_wall_left() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("The wall shall be moved over the board to position \\({int}, {int})")
	public void the_wall_shall_be_moved_over_the_board_to_position(Integer int1, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("A wall move candidate shall exist with vertical at position \\({int}, {int})")
	public void a_wall_move_candidate_shall_exist_with_vertical_at_position(Integer int1, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("A wall move candidate exists with horizontal at position \\({int}, {int})")
	public void a_wall_move_candidate_exists_with_horizontal_at_position(Integer int1, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("The wall candidate is not at the right edge of the board")
	public void the_wall_candidate_is_not_at_the_right_edge_of_the_board() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("I try to move the wall right")
	public void i_try_to_move_the_wall_right() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("A wall move candidate shall exist with horizontal at position \\({int}, {int})")
	public void a_wall_move_candidate_shall_exist_with_horizontal_at_position(Integer int1, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("The wall candidate is not at the up edge of the board")
	public void the_wall_candidate_is_not_at_the_up_edge_of_the_board() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("I try to move the wall up")
	public void i_try_to_move_the_wall_up() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("The wall candidate is not at the down edge of the board")
	public void the_wall_candidate_is_not_at_the_down_edge_of_the_board() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("I try to move the wall down")
	public void i_try_to_move_the_wall_down() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("The wall candidate is at the left edge of the board")
	public void the_wall_candidate_is_at_the_left_edge_of_the_board() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("I should be notified that my move is illegal")
	public void i_should_be_notified_that_my_move_is_illegal() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("The wall candidate is at the right edge of the board")
	public void the_wall_candidate_is_at_the_right_edge_of_the_board() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("The wall candidate is at the up edge of the board")
	public void the_wall_candidate_is_at_the_up_edge_of_the_board() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@Given("The wall candidate is at the down edge of the board")
	public void the_wall_candidate_is_at_the_down_edge_of_the_board() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	//The above is MoveWall
	
	
	
	@When("I try to flip the wall")
	public void i_try_to_flip_the_wall() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("The wall shall be rotated over the board to vertical")
	public void the_wall_shall_be_rotated_over_the_board_to_vertical() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("The wall shall be rotated over the board to horizontal")
	public void the_wall_shall_be_rotated_over_the_board_to_horizontal() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("File save_game_test.dat exists in the filesystem")
	public void file_save_game_test_dat_exists_in_the_filesystem() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("The user confirms to overwrite existing file")
	public void the_user_confirms_to_overwrite_existing_file() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("File with save_game_test.dat is updated in the filesystem")
	public void file_with_save_game_test_dat_is_updated_in_the_filesystem() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("The user cancels to overwrite existing file")
	public void the_user_cancels_to_overwrite_existing_file() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("File save_game_test.dat is not changed in the filesystem")
	public void file_save_game_test_dat_is_not_changed_in_the_filesystem() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("A game position is supplied with pawn coordinate {int}:{int}")
	public void a_game_position_is_supplied_with_pawn_coordinate(Integer int1, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("Validation of the position is initiated")
	public void validation_of_the_position_is_initiated() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("The position is ok")
	public void the_position_is_ok() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("The position is error")
	public void the_position_is_error() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("A game position is supplied with wall coordinate {int}:{int}-horizontal")
	public void a_game_position_is_supplied_with_wall_coordinate_horizontal(Integer int1, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("A game position is supplied with wall coordinate {int}:{int}-vertical")
	public void a_game_position_is_supplied_with_wall_coordinate_vertical(Integer int1, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("The position is valid")
	public void the_position_is_valid() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("The position is invalid")
	public void the_position_is_invalid() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}


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
