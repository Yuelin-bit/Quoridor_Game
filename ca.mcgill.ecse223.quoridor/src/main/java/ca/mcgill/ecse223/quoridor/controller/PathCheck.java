package ca.mcgill.ecse223.quoridor.controller;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;

import ca.mcgill.ecse223.quoridor.QuoridorApplication;
import ca.mcgill.ecse223.quoridor.model.Board;
import ca.mcgill.ecse223.quoridor.model.Destination;
import ca.mcgill.ecse223.quoridor.model.Direction;
import ca.mcgill.ecse223.quoridor.model.Game;
import ca.mcgill.ecse223.quoridor.model.GamePosition;
import ca.mcgill.ecse223.quoridor.model.Player;
import ca.mcgill.ecse223.quoridor.model.PlayerPosition;
import ca.mcgill.ecse223.quoridor.model.Quoridor;
import ca.mcgill.ecse223.quoridor.model.Tile;
import ca.mcgill.ecse223.quoridor.model.Wall;
import ca.mcgill.ecse223.quoridor.model.WallMove;
/**
 * Check if path exist controller
 * @author Sun Gengyi
 *
 */
public class PathCheck {


	/**
	 * @author Sun, Gengyi
	 * @return A string indicating whose path is available.
	 */
	@SuppressWarnings("unchecked")
	public static String pathCheck() {

		DefaultUndirectedGraph<Tile, DefaultEdge> graph = initializeGraph();
		Player white = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
		Player black = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
		String result = "";
		boolean whiteHasPath = playPath(white, graph);
		boolean blackHasPath = playPath(black, graph);

		if(whiteHasPath&&blackHasPath) result = "both";
		else if(whiteHasPath)result = "white";
		else if(blackHasPath) result = "black";
		else result = "none";
		System.out.println(result+"-----------------------");
		return result;
	}
	/**
	 * @author Sun, Gengyi
	 * Checking for player's available path.
	 * @param player
	 * @return A boolean indicating whether player has a path to destination.
	 */
	public static boolean pathCheckPlayer(Player player) {
		DefaultUndirectedGraph<Tile, DefaultEdge> graph = initializeGraph();
		return testPath(player, graph);	
	}

	/**
	 * 	Showing what color is the player.
	 * @author Sun, Gengyi
	 * @param p
	 * @return	a boolean indicating if player is white player.
	 * @throws RuntimeException
	 */
	public static boolean isWhitePlayer(Player p) throws RuntimeException {
		if(p==null) throw new RuntimeException("Player cannot be null!");
		Player whitePlayer = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
		Player blackPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
		if(p.equals(blackPlayer)) return false;
		else if (p.equals(whitePlayer)) return true;
		else throw new RuntimeException("Player is not in the game!");

	}

	/**
	 * Connect the current game position as a graph.	 
	 * @author Sun, Gengyi
	 * @return A undirected graph, tiles are the vertices
	 */
	public static DefaultUndirectedGraph initializeGraph(){
		DefaultUndirectedGraph<Tile, DefaultEdge> graph = new DefaultUndirectedGraph<Tile,DefaultEdge>(DefaultEdge.class);

		Tile[] vertices = new Tile[81];
		List<Tile> tiles = QuoridorApplication.getQuoridor().getBoard().getTiles();

		int index = 0;
		for(Tile t: tiles) {
			try {
				vertices[index] = t;
				graph.addVertex(vertices[index]);
				index++;
			}
			catch(ArrayIndexOutOfBoundsException e) {
				System.out.println(e.getMessage());
			}

		}
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= 8; j++) {
				if(!(verticalWallAt(i,j) || verticalWallAt(i-1, j))) { 
					graph.addEdge(vertices[9*(i-1) + (j-1)], vertices[9*(i-1) + (j)]);
				}	
			}
		}
		for (int j = 1; j <= 9; j++) { // columns
			for (int i = 1; i <= 8; i++) { // rows
				if(!(horizontalWallAt(i,j) || horizontalWallAt(i, j-1))) { 
					graph.addEdge(vertices[9*(i-1) + (j-1)], vertices[9*(i) + (j-1)]);
				}	
			}
		}

		return graph;
	}



	/**
	 * Showing a player position of the player input.
	 * @param player
	 * @return	A player position of the player
	 * @throws Exception
	 */
	public static PlayerPosition getPlayerPosition(Player player) throws Exception

	{
		Game game = QuoridorApplication.getQuoridor().getCurrentGame();

		Player whitePlayer = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
		Player blackPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();

		if (player.equals(whitePlayer))
			return game.getCurrentPosition().getWhitePosition();
		else if (player.equals(blackPlayer))
			return game.getCurrentPosition().getBlackPosition();

		else
			throw new Exception("This player is not in the current game");
	}


	/**
	 * Deciding if player has a path to its destination.
	 * @param player
	 * @param boardGraph
	 * @return	 a boolean indicating if player has path
	 */
	private static boolean playPath(Player player, DefaultUndirectedGraph<Tile, DefaultEdge> boardGraph) {
		Board board = QuoridorApplication.getQuoridor().getBoard();
		Destination destination = player.getDestination();
		Tile playerTile = null;
		try {
			playerTile = getPlayerPosition(player).getTile();
		} catch (Exception e) {
			e.printStackTrace();

		}
		//Path algorithm
		DijkstraShortestPath<Tile, DefaultEdge> pathAlgo = new DijkstraShortestPath<Tile, DefaultEdge>(boardGraph);

		if(destination.getDirection().equals(Direction.Horizontal)) {
			int row = destination.getTargetNumber();
			try {
				if(getPlayerPosition(player).getTile().getRow()==9) row = 1;
				else row = 9;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			for(int col = 1; col <= 9; col++ ) {			
				if(pathAlgo.getPath(playerTile, board.getTile(9*(row - 1) + col - 1)) != null) {


					return true;
				}
			}
		} 
		else {
			int col = destination.getTargetNumber();
			col= col==9?1:9;
			for(int row = 1; row<= 9; row++ ) { 
				if(pathAlgo.getPath(playerTile, board.getTile(9*(row - 1) + col - 1))!=null) {
					System.out.print(pathAlgo.getPath(playerTile, board.getTile(9*(row - 1) + col - 1)).getGraph().toString());
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * Deciding if player has a path to its destination.
	 * @param player
	 * @param boardGraph
	 * @return	 a boolean indicating if player has path
	 */
	private static boolean testPath(Player player, DefaultUndirectedGraph<Tile, DefaultEdge> boardGraph) {
		Board board = QuoridorApplication.getQuoridor().getBoard();
		Destination destination = player.getDestination();
		Tile playerTile = null;
		try {
			playerTile = getPlayerPosition(player).getTile();
		} catch (Exception e) {
			e.printStackTrace();

		}
		DijkstraShortestPath<Tile, DefaultEdge> pathAlgo = new DijkstraShortestPath<Tile, DefaultEdge>(boardGraph);

		if(destination.getDirection().equals(Direction.Horizontal)) {
			int row = destination.getTargetNumber();
			row = row ==9?1:9;
			for(int col = 1; col <= 9; col++ ) {			
				if(pathAlgo.getPath(playerTile, board.getTile(9*(row - 1) + col - 1)) != null) {


					return true;
				}
			}
		} 
		else {
			int col = destination.getTargetNumber();
			col= col==9?1:9;
			for(int row = 1; row<= 9; row++ ) { 
				if(pathAlgo.getPath(playerTile, board.getTile(9*(row - 1) + col - 1))!=null) {
					System.out.print(pathAlgo.getPath(playerTile, board.getTile(9*(row - 1) + col - 1)).getGraph().toString());
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * Showing player's color
	 * @param player
	 * @return a string telling you the player's color
	 */
	public static String getColor(Player player) {
		if(player.equals(QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer())) return "black";
		else return "white";
	}
	/**
	 * Showing whether a veritical wall exists at row, col
	 * @param row
	 * @param col
	 * @return	a boolean indicating whether a veritical wall exists at row, col
	 */
	private static boolean verticalWallAt(int row, int col) {
		if(row < 1 || row > 8 || col < 1 || col > 8 ) { 
			return false; 
		}
		else{
			List<Wall> walls = getWallsOnBoard();
			Direction d = QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getWallDirection();
			Tile target = QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile();
			int targetRow = target.getRow();
			int targetCol = target.getColumn();
			Wall wall = getWall(walls, row, col);
			if(row==targetRow&&col==targetCol&&d.equals(Direction.Vertical)) {

				return true;
			}
			else if(wall != null && wall.getMove().getWallDirection().equals(Direction.Vertical)) {

				return true;
			} else return false;
		}
	}
	/**
	 * Showing whether a horizontal wall exists at row, col
	 * @param row
	 * @param col
	 * @return	a boolean indicating whether a veritical wall exists at row, col
	 */
	private static boolean horizontalWallAt(int row, int col) {
		if(row < 1 || row > 8 || col < 1 || col > 8 ) { 
			return false; 
		}else {
			List<Wall> walls = getWallsOnBoard();
			Direction d = QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getWallDirection();
			Tile target = QuoridorApplication.getQuoridor().getCurrentGame().getWallMoveCandidate().getTargetTile();
			int targetRow = target.getRow();
			int targetCol = target.getColumn();
			Wall wall = getWall(walls, row, col);
			if(row==targetRow&&col==targetCol&&d.equals(Direction.Horizontal)) {
				return true;
			}
			else if(wall != null && wall.getMove().getWallDirection().equals(Direction.Horizontal)) {

				return true;
			} else return false;
		}
	}

	/**
	 * Gives a list of wall on board currently
	 * @return a list of wall on board currently
	 */
	private static List<Wall> getWallsOnBoard() {
		GamePosition currentPosition = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition();
		List<Wall> walls = new ArrayList<Wall>();

		walls.addAll(currentPosition.getWhiteWallsOnBoard());
		walls.addAll(currentPosition.getBlackWallsOnBoard());
		return walls;
	}
	/**
	 * Give a specific wall on board at row, col
	 * @param walls
	 * @param row
	 * @param col
	 * @return a specific wall on board at row, col
	 * 
	 */
	private static Wall getWall(List<Wall> walls, int row, int col) {
		Board board = QuoridorApplication.getQuoridor().getBoard();
		for (Wall w : walls) {
			if (w.getMove().getTargetTile().getRow() == row && w.getMove().getTargetTile().getColumn() == col) {
				return w;
			}
		}
		return null;
	}



}
