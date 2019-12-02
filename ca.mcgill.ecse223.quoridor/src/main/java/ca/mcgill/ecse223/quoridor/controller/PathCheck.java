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

public class PathCheck {

	@SuppressWarnings("unchecked")
	public static String pathCheck() {
		DefaultUndirectedGraph <Tile, DefaultEdge> graph = initializeGraph();
		Player white = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
		Player black = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
		String result = "";
		boolean whiteHasPath = PathToDestination(white, graph);
		boolean blackHasPath = PathToDestination(black, graph);

		if(whiteHasPath&&blackHasPath) result = "both";
		else if(whiteHasPath)result = "white";
		else if(blackHasPath) result = "black";
		else result = "none";
		System.out.println(result+"--"+whiteHasPath+"---------------------");
		return result;
	}
	public static boolean pathCheckPlayer(Player player) {
		DefaultUndirectedGraph<Tile, DefaultEdge> graph = initializeGraph();
		return PathToDestination(player, graph);	
	}

	public static boolean isWhitePlayer(Player p) throws RuntimeException {
		if(p==null) throw new RuntimeException("Player cannot be null!");
		Player whitePlayer = QuoridorApplication.getQuoridor().getCurrentGame().getWhitePlayer();
		Player blackPlayer = QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer();
		if(p.equals(blackPlayer)) return false;
		else if (p.equals(whitePlayer)) return true;
		else throw new RuntimeException("Player is not in the game!");

	}

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
					System.out.println("horizontal edge at ("+(i)+","+j+") to ("+(i)+","+(j+1)+")");
					graph.addEdge(vertices[9*(i-1) + (j-1)], vertices[9*(i-1) + (j)]);
				}	
			}
		}
		for (int j = 1; j <= 9; j++) { // columns
			for (int i = 1; i <= 8; i++) { // rows
				if(!(horizontalWallAt(i,j) || horizontalWallAt(i, j-1))) { 
					System.out.println("vertical edge at ("+(i)+","+j+") to ("+(i+1)+","+(j)+")");
					graph.addEdge(vertices[9*(i-1) + (j-1)], vertices[9*(i) + (j-1)]);
				}	
			}
		}

		return graph;
	}










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



	private static boolean PathToDestination(Player player, DefaultUndirectedGraph<Tile, DefaultEdge> boardGraph) {
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
		//		int row = destination.getTargetNumber();
		//		row = row==9?1:9;

		if(destination.getDirection().equals(Direction.Horizontal)) {
			int row = destination.getTargetNumber();
			try {
				if(getPlayerPosition(player).getTile().getRow()==9) row = 1;
				else row = 9;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	public static String getColor(Player player) {
		if(player.equals(QuoridorApplication.getQuoridor().getCurrentGame().getBlackPlayer())) return "black";
		else return "white";
	}
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
			if(row==targetRow && col==targetCol&&d.equals(Direction.Vertical)) {
				return true;
			}
			else if(wall != null && wall.getMove().getWallDirection().equals(Direction.Vertical)) {

				return true;
			} else return false;
		}
	}

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
				System.out.println("wallplaced==========================================");
				return true;
			}
			else if(wall != null && wall.getMove().getWallDirection().equals(Direction.Horizontal)) {
				System.out.println("wall==========================================");

				return true;
			} else return false;
		}
	}
	private static List<Wall> getWallsOnBoard() {
		GamePosition currentPosition = QuoridorApplication.getQuoridor().getCurrentGame().getCurrentPosition();
		List<Wall> walls = new ArrayList<Wall>();

		//if (currentPosition.hasWhiteWallsOnBoard()) {
			walls.addAll(currentPosition.getWhiteWallsOnBoard());
		//}
		//if (currentPosition.hasBlackWallsInStock()) {
			walls.addAll(currentPosition.getBlackWallsOnBoard());
		//}
		return walls;
	}


	private static Wall getWall(List<Wall> walls, int row, int col) {
		Board board = QuoridorApplication.getQuoridor().getBoard();
		for (Wall w : walls) {
			System.out.println(w.getId());
			System.out.println("wall at ("+w.getMove().getTargetTile().getRow()+","+w.getMove().getTargetTile().getColumn()+")");
			if (w.getMove().getTargetTile().getRow() == row && w.getMove().getTargetTile().getColumn() == col) {
				System.out.println("wall found at ("+row+","+col+")");
				return w;
				}
		}
		return null;
	}


}
