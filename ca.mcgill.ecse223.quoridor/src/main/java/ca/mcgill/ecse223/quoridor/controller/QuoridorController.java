package ca.mcgill.ecse223.quoridor.controller;

import ca.mcgill.ecse223.quoridor.model.*;

public class QuoridorController {
	/**
	 * Feature:DropWall
	 * 
     * @author Yuelin Liu
	 * @param tile and dir Tile and direction to be check whether it is valid
	 * @return Whether the wall_candidate with tile and dir is valid;
	 * @exception UnsupportedOperationException
	 *
	 */
	public static boolean TileValid(Tile tile, Direction dir) {
		throw new UnsupportedOperationException();
	}
	
	
	
	
	/**
	 * Feature:DropWall
	 * 
     * @author Yuelin Liu
	 * @param wallmove WallMove candidate to be released.
	 * @return void
	 * @exception UnsupportedOperationException
	 *
	 */
	public static void ReleaseWall(WallMove wallmove) 
	{	
		//TODO: Write logic
		throw new UnsupportedOperationException();
	}
	
	
	
	
	/**
	 * Feature:MoveWall
	 * 
	 * @author Yuelin Liu
	 * @param string String stands for the direction of the wall that is to be moved.
	 * @return void
	 * @exception UnsupportedOperationException
	 */
	public static void MoveWall(String string)
	{	
		//TODO: Write logic
		throw new UnsupportedOperationException();
	}
	
	
	
	/**
	 * Feature:MoveWall
	 * 
	 * @author Yuelin Liu
	 * @param  a Integer that stands for the row or column of TargetTile.
	 * @return (boolean)Whether the wall_candidate exceeds the valid tile;
	 *
	 */
	public static Tile getNonEdgeTile(String string, Tile tile)
	{	
		//TODO: Write logic
		throw new UnsupportedOperationException();
	}
	
	
	
	/**
	 * Feature:MoveWall
	 * 
	 * @author Yuelin Liu
	 * @param  string String stands for direction and tile Tile stands for the targetTile.
	 * @return a Tile in the edge.
	 *
	 */
	public static Tile getEdgeTile(String string,Tile tile) {
		//TODO: Write logic
				throw new UnsupportedOperationException();
	}
	
}
