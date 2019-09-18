/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.quoridor.model;

// line 33 "../domain_model_v1.2.ump"
// line 73 "../domain_model_v1.2.ump"
public class Pawn
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Pawn Attributes
  private String position;

  //Pawn Associations
  private Player player;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Pawn(String aPosition, Player aPlayer)
  {
    position = aPosition;
    if (aPlayer == null || aPlayer.getPawn() != null)
    {
      throw new RuntimeException("Unable to create Pawn due to aPlayer");
    }
    player = aPlayer;
  }

  public Pawn(String aPosition, String aNameForPlayer, int aNum_wall_leftForPlayer, boolean aIsWinnerForPlayer, Game aGameForPlayer)
  {
    position = aPosition;
    player = new Player(aNameForPlayer, aNum_wall_leftForPlayer, aIsWinnerForPlayer, aGameForPlayer, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPosition(String aPosition)
  {
    boolean wasSet = false;
    position = aPosition;
    wasSet = true;
    return wasSet;
  }

  public String getPosition()
  {
    return position;
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
            "position" + ":" + getPosition()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null");
  }
}