/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.quoridor.model;

import java.util.*;

// line 39 "../domain_model_v1.3 SunGengyi.ump"
// line 91 "../domain_model_v1.3 SunGengyi.ump"
public class Player {

	// ------------------------
	// ENUMERATIONS
	// ------------------------

	public enum PlayerType {
		Black, White
	}

	public enum WallDirection {
		Horizontal, Vertical
	}

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// Player Attributes
	private PlayerType PlayerType;

	// Player Associations
	private User user;
	private List<Wall> walls;
	private QuoridorGame g;
	private Pawn pawn;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public Player(PlayerType aPlayerType, User aUser, Pawn aPawn) {
		PlayerType = aPlayerType;
		if (aUser == null || aUser.getPlayer() != null) {
			throw new RuntimeException("Unable to create Player due to aUser");
		}
		user = aUser;
		walls = new ArrayList<Wall>();
		if (aPawn == null || aPawn.getPlayer() != null) {
			throw new RuntimeException("Unable to create Player due to aPawn");
		}
		pawn = aPawn;
	}

	public Player(PlayerType aPlayerType, String aUserNameForUser, String aUserIDForUser, String aCoordinateForPawn) {
		PlayerType = aPlayerType;
		user = new User(aUserNameForUser, aUserIDForUser, this);
		walls = new ArrayList<Wall>();
		pawn = new Pawn(aCoordinateForPawn, this);
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public boolean setPlayerType(PlayerType aPlayerType) {
		boolean wasSet = false;
		PlayerType = aPlayerType;
		wasSet = true;
		return wasSet;
	}

	public PlayerType getPlayerType() {
		return PlayerType;
	}

	/* Code from template association_GetOne */
	public User getUser() {
		return user;
	}

	/* Code from template association_GetMany */
	public Wall getWall(int index) {
		Wall aWall = walls.get(index);
		return aWall;
	}

	public List<Wall> getWalls() {
		List<Wall> newWalls = Collections.unmodifiableList(walls);
		return newWalls;
	}

	public int numberOfWalls() {
		int number = walls.size();
		return number;
	}

	public boolean hasWalls() {
		boolean has = walls.size() > 0;
		return has;
	}

	public int indexOfWall(Wall aWall) {
		int index = walls.indexOf(aWall);
		return index;
	}

	/* Code from template association_GetOne */
	public QuoridorGame getG() {
		return g;
	}

	public boolean hasG() {
		boolean has = g != null;
		return has;
	}

	/* Code from template association_GetOne */
	public Pawn getPawn() {
		return pawn;
	}

	/* Code from template association_IsNumberOfValidMethod */
	public boolean isNumberOfWallsValid() {
		boolean isValid = numberOfWalls() >= minimumNumberOfWalls() && numberOfWalls() <= maximumNumberOfWalls();
		return isValid;
	}

	/* Code from template association_RequiredNumberOfMethod */
	public static int requiredNumberOfWalls() {
		return 10;
	}

	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfWalls() {
		return 10;
	}

	/* Code from template association_MaximumNumberOfMethod */
	public static int maximumNumberOfWalls() {
		return 10;
	}

	/* Code from template association_AddMNToOnlyOne */
	public Wall addWall(String aCoordinate, ca.mcgill.ecse223.quoridor.model.Wall.WallDirection aWallDirection) {
		if (numberOfWalls() >= maximumNumberOfWalls()) {
			return null;
		} else {
			Wall return_wall = new Wall(aCoordinate, aWallDirection, this.getUser().getPlayer());
			return return_wall;
		}
	}

	public boolean addWall(Wall aWall) {
		boolean wasAdded = false;
		if (walls.contains(aWall)) {
			return false;
		}
		if (numberOfWalls() >= maximumNumberOfWalls()) {
			return wasAdded;
		}

		Player existingPlayer = aWall.getPlayer();
		boolean isNewPlayer = existingPlayer != null && !this.equals(existingPlayer);

		if (isNewPlayer && existingPlayer.numberOfWalls() <= minimumNumberOfWalls()) {
			return wasAdded;
		}

		if (isNewPlayer) {
			aWall.setPlayer(this);
		} else {
			walls.add(aWall);
		}
		wasAdded = true;
		return wasAdded;
	}

	public boolean removeWall(Wall aWall) {
		boolean wasRemoved = false;
		// Unable to remove aWall, as it must always have a player
		if (this.equals(aWall.getPlayer())) {
			return wasRemoved;
		}

		// player already at minimum (10)
		if (numberOfWalls() <= minimumNumberOfWalls()) {
			return wasRemoved;
		}
		walls.remove(aWall);
		wasRemoved = true;
		return wasRemoved;
	}

	public void delete() {
		User existingUser = user;
		user = null;
		if (existingUser != null) {
			existingUser.delete();
		}
		for (int i = walls.size(); i > 0; i--) {
			Wall aWall = walls.get(i - 1);
			aWall.delete();
		}
		if (g != null) {
			QuoridorGame placeholderG = g;
			this.g = null;
			placeholderG.delete();
		}
		Pawn existingPawn = pawn;
		pawn = null;
		if (existingPawn != null) {
			existingPawn.delete();
		}
	}

	public String toString() {
		return super.toString() + "[" + "]" + System.getProperties().getProperty("line.separator") + "  " + "PlayerType"
				+ "="
				+ (getPlayerType() != null
						? !getPlayerType().equals(this) ? getPlayerType().toString().replaceAll("  ", "    ") : "this"
						: "null")
				+ System.getProperties().getProperty("line.separator") + "  " + "user = "
				+ (getUser() != null ? Integer.toHexString(System.identityHashCode(getUser())) : "null")
				+ System.getProperties().getProperty("line.separator") + "  " + "g = "
				+ (getG() != null ? Integer.toHexString(System.identityHashCode(getG())) : "null")
				+ System.getProperties().getProperty("line.separator") + "  " + "pawn = "
				+ (getPawn() != null ? Integer.toHexString(System.identityHashCode(getPawn())) : "null");
	}
}