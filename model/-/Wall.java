/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package -;

// line 47 "../domain_model_v1.3 SunGengyi.ump"
// line 100 "../domain_model_v1.3 SunGengyi.ump"
public class Wall
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum WallDirection { Horizontal, Vertical }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Wall Attributes
  private String Coordinate;
  private WallDirection WallDirection;

  //Wall Associations
  private Position pst;
  private Player player;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Wall(String aCoordinate, WallDirection aWallDirection, Player aPlayer)
  {
    Coordinate = aCoordinate;
    WallDirection = aWallDirection;
    boolean didAddPlayer = setPlayer(aPlayer);
    if (!didAddPlayer)
    {
      throw new RuntimeException("Unable to create wall due to player");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCoordinate(String aCoordinate)
  {
    boolean wasSet = false;
    Coordinate = aCoordinate;
    wasSet = true;
    return wasSet;
  }

  public boolean setWallDirection(WallDirection aWallDirection)
  {
    boolean wasSet = false;
    WallDirection = aWallDirection;
    wasSet = true;
    return wasSet;
  }

  public String getCoordinate()
  {
    return Coordinate;
  }

  public WallDirection getWallDirection()
  {
    return WallDirection;
  }
  /* Code from template association_GetOne */
  public Position getPst()
  {
    return pst;
  }

  public boolean hasPst()
  {
    boolean has = pst != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Player getPlayer()
  {
    return player;
  }
  /* Code from template association_SetOptionalOneToOptionalN */
  public boolean setPst(Position aPst)
  {
    boolean wasSet = false;
    if (aPst != null && aPst.numberOfW() >= Position.maximumNumberOfW())
    {
      return wasSet;
    }

    Position existingPst = pst;
    pst = aPst;
    if (existingPst != null && !existingPst.equals(aPst))
    {
      existingPst.removeW(this);
    }
    if (aPst != null)
    {
      aPst.addW(this);
    }
    wasSet = true;
    return wasSet;
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
    if (pst != null)
    {
      Position placeholderPst = pst;
      this.pst = null;
      placeholderPst.removeW(this);
    }
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
            "Coordinate" + ":" + getCoordinate()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "WallDirection" + "=" + (getWallDirection() != null ? !getWallDirection().equals(this)  ? getWallDirection().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "pst = "+(getPst()!=null?Integer.toHexString(System.identityHashCode(getPst())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null");
  }
}