/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.quoridor.model;

// line 23 "../domain_model_v1.2.ump"
// line 65 "../domain_model_v1.2.ump"
public class Wall
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum WallDirction { Horizontal, Vertical }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Wall Attributes
  private String position;
  private WallDirction direction;

  //Wall Associations
  private Player player;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Wall(String aPosition, WallDirction aDirection, Player aPlayer)
  {
    position = aPosition;
    direction = aDirection;
    boolean didAddPlayer = setPlayer(aPlayer);
    if (!didAddPlayer)
    {
      throw new RuntimeException("Unable to create wall due to player");
    }
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

  public boolean setDirection(WallDirction aDirection)
  {
    boolean wasSet = false;
    direction = aDirection;
    wasSet = true;
    return wasSet;
  }

  public String getPosition()
  {
    return position;
  }

  public WallDirction getDirection()
  {
    return direction;
  }
  /* Code from template association_GetOne */
  public Player getPlayer()
  {
    return player;
  }
  /* Code from template association_SetOneToAtMostN */
  public boolean setPlayer(Player aPlayer)
  {
    boolean wasSet = false;
    //Must provide player to wall
    if (aPlayer == null)
    {
      return wasSet;
    }

    //player already at maximum (10)
    if (aPlayer.numberOfWalls() >= Player.maximumNumberOfWalls())
    {
      return wasSet;
    }
    
    Player existingPlayer = player;
    player = aPlayer;
    if (existingPlayer != null && !existingPlayer.equals(aPlayer))
    {
      boolean didRemove = existingPlayer.removeWall(this);
      if (!didRemove)
      {
        player = existingPlayer;
        return wasSet;
      }
    }
    player.addWall(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Player placeholderPlayer = player;
    this.player = null;
    if(placeholderPlayer != null)
    {
      placeholderPlayer.removeWall(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "position" + ":" + getPosition()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "direction" + "=" + (getDirection() != null ? !getDirection().equals(this)  ? getDirection().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null");
  }
}