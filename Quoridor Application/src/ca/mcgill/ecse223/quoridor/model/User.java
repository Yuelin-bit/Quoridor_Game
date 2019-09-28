/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.quoridor.model;

import ca.mcgill.ecse223.quoridor.model.Player.PlayerType;

// line 18 "../domain_model_v1.3 SunGengyi.ump"
// line 72 "../domain_model_v1.3 SunGengyi.ump"
public class User {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// User Attributes
	private String UserName;
	private String UserID;

	// User Associations
	private QuoridorSystem s;
	private Player player;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public User(String aUserName, String aUserID, Player aPlayer) {
		UserName = aUserName;
		UserID = aUserID;
		if (aPlayer == null || aPlayer.getUser() != null) {
			throw new RuntimeException("Unable to create User due to aPlayer");
		}
		player = aPlayer;
	}

	public User(String aUserName, String aUserID, PlayerType aPlayerTypeForPlayer, Pawn aPawnForPlayer) {
		UserName = aUserName;
		UserID = aUserID;
		player = new Player(aPlayerTypeForPlayer, this, aPawnForPlayer);
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public boolean setUserName(String aUserName) {
		boolean wasSet = false;
		UserName = aUserName;
		wasSet = true;
		return wasSet;
	}

	public boolean setUserID(String aUserID) {
		boolean wasSet = false;
		UserID = aUserID;
		wasSet = true;
		return wasSet;
	}

	public String getUserName() {
		return UserName;
	}

	public String getUserID() {
		return UserID;
	}

	/* Code from template association_GetOne */
	public QuoridorSystem getS() {
		return s;
	}

	public boolean hasS() {
		boolean has = s != null;
		return has;
	}

	/* Code from template association_GetOne */
	public Player getPlayer() {
		return player;
	}

	/* Code from template association_SetOptionalOneToMany */
	public boolean setS(QuoridorSystem aS) {
		boolean wasSet = false;
		QuoridorSystem existingS = s;
		s = aS;
		if (existingS != null && !existingS.equals(aS)) {
			existingS.removeU(this);
		}
		if (aS != null) {
			aS.addU(this);
		}
		wasSet = true;
		return wasSet;
	}

	public void delete() {
		if (s != null) {
			QuoridorSystem placeholderS = s;
			this.s = null;
			placeholderS.removeU(this);
		}
		Player existingPlayer = player;
		player = null;
		if (existingPlayer != null) {
			existingPlayer.delete();
		}
	}

	public String toString() {
		return super.toString() + "[" + "UserName" + ":" + getUserName() + "," + "UserID" + ":" + getUserID() + "]"
				+ System.getProperties().getProperty("line.separator") + "  " + "s = "
				+ (getS() != null ? Integer.toHexString(System.identityHashCode(getS())) : "null")
				+ System.getProperties().getProperty("line.separator") + "  " + "player = "
				+ (getPlayer() != null ? Integer.toHexString(System.identityHashCode(getPlayer())) : "null");
	}
}