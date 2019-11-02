/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.quoridor.model;
import java.sql.Time;

// line 67 "../../../../../QuoridorGame.ump"
public class Destination
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Destination Attributes
  private int targetNumber;
  private Direction direction;

  //Destination Associations
  private Player player;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Destination(int aTargetNumber, Direction aDirection, Player aPlayer)
  {
    targetNumber = aTargetNumber;
    direction = aDirection;
    if (aPlayer == null || aPlayer.getDestination() != null)
    {
      throw new RuntimeException("Unable to create Destination due to aPlayer");
    }
    player = aPlayer;
  }

  public Destination(int aTargetNumber, Direction aDirection, Time aRemainingTimeForPlayer, User aUserForPlayer)
  {
    targetNumber = aTargetNumber;
    direction = aDirection;
    player = new Player(aRemainingTimeForPlayer, aUserForPlayer, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTargetNumber(int aTargetNumber)
  {
    boolean wasSet = false;
    targetNumber = aTargetNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setDirection(Direction aDirection)
  {
    boolean wasSet = false;
    direction = aDirection;
    wasSet = true;
    return wasSet;
  }

  public int getTargetNumber()
  {
    return targetNumber;
  }

  public Direction getDirection()
  {
    return direction;
  }
  /* Code from template association_GetOne */
  public Player getPlayer()
  {
    return player;
  }

  public void delete()
  {
    Player existingPlayer = player;
    player = null;
    if (existingPlayer != null)
    {
      existingPlayer.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "targetNumber" + ":" + getTargetNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "direction" + "=" + (getDirection() != null ? !getDirection().equals(this)  ? getDirection().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null");
  }
}