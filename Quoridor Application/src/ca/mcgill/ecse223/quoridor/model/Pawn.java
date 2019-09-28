/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.quoridor.model;

import ca.mcgill.ecse223.quoridor.model.Player.PlayerType;

// line 33 "../domain_model_v1.3 SunGengyi.ump"
// line 85 "../domain_model_v1.3 SunGengyi.ump"
public class Pawn {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// Pawn Attributes
	private String Coordinate;

	// Pawn Associations
	private Player player;
	private Position pst;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public Pawn(String aCoordinate, Player aPlayer) {
		Coordinate = aCoordinate;
		if (aPlayer == null || aPlayer.getPawn() != null) {
			throw new RuntimeException("Unable to create Pawn due to aPlayer");
		}
		player = aPlayer;
	}

	public Pawn(String aCoordinate, PlayerType aPlayerTypeForPlayer, User aUserForPlayer) {
		Coordinate = aCoordinate;
		player = new Player(aPlayerTypeForPlayer, aUserForPlayer, this);
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public boolean setCoordinate(String aCoordinate) {
		boolean wasSet = false;
		Coordinate = aCoordinate;
		wasSet = true;
		return wasSet;
	}

	public String getCoordinate() {
		return Coordinate;
	}

	/* Code from template association_GetOne */
	public Player getPlayer() {
		return player;
	}

	/* Code from template association_GetOne */
	public Position getPst() {
		return pst;
	}

	public boolean hasPst() {
		boolean has = pst != null;
		return has;
	}

	/* Code from template association_SetOptionalOneToOptionalN */
	public boolean setPst(Position aPst) {
		boolean wasSet = false;
		if (aPst != null && aPst.numberOfPw() >= Position.maximumNumberOfPw()) {
			return wasSet;
		}

		Position existingPst = pst;
		pst = aPst;
		if (existingPst != null && !existingPst.equals(aPst)) {
			existingPst.removePw(this);
		}
		if (aPst != null) {
			aPst.addPw(this);
		}
		wasSet = true;
		return wasSet;
	}

	public void delete() {
		Player existingPlayer = player;
		player = null;
		if (existingPlayer != null) {
			existingPlayer.delete();
		}
		if (pst != null) {
			Position placeholderPst = pst;
			this.pst = null;
			placeholderPst.removePw(this);
		}
	}

	public String toString() {
		return super.toString() + "[" + "Coordinate" + ":" + getCoordinate() + "]"
				+ System.getProperties().getProperty("line.separator") + "  " + "player = "
				+ (getPlayer() != null ? Integer.toHexString(System.identityHashCode(getPlayer())) : "null")
				+ System.getProperties().getProperty("line.separator") + "  " + "pst = "
				+ (getPst() != null ? Integer.toHexString(System.identityHashCode(getPst())) : "null");
	}
}