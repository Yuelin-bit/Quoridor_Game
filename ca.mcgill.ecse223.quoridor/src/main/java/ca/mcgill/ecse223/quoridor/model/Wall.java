/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.quoridor.model;
import java.util.*;

// line 30 "../../../../../QuoridorGame.ump"
public class Wall
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, Wall> wallsById = new HashMap<Integer, Wall>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Wall Attributes
  private int id;

  //Wall Associations
  private Player owner;
  private WallMove move;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Wall(int aId, Player aOwner)
  {
    if (!setId(aId))
    {
      throw new RuntimeException("Cannot create due to duplicate id");
    }
    boolean didAddOwner = setOwner(aOwner);
    if (!didAddOwner)
    {
      throw new RuntimeException("Unable to create wall due to owner");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    Integer anOldId = getId();
    if (hasWithId(aId)) {
      return wasSet;
    }
    id = aId;
    wasSet = true;
    if (anOldId != null) {
      wallsById.remove(anOldId);
    }
    wallsById.put(aId, this);
    return wasSet;
  }

  public int getId()
  {
    return id;
  }
  /* Code from template attribute_GetUnique */
  public static Wall getWithId(int aId)
  {
    return wallsById.get(aId);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithId(int aId)
  {
    return getWithId(aId) != null;
  }
  /* Code from template association_GetOne */
  public Player getOwner()
  {
    return owner;
  }
  /* Code from template association_GetOne */
  public WallMove getMove()
  {
    return move;
  }

  public boolean hasMove()
  {
    boolean has = move != null;
    return has;
  }
  /* Code from template association_SetOneToAtMostN */
  public boolean setOwner(Player aOwner)
  {
    boolean wasSet = false;
    //Must provide owner to wall
    if (aOwner == null)
    {
      return wasSet;
    }

    //owner already at maximum (10)
    if (aOwner.numberOfWalls() >= Player.maximumNumberOfWalls())
    {
      return wasSet;
    }
    
    Player existingOwner = owner;
    owner = aOwner;
    if (existingOwner != null && !existingOwner.equals(aOwner))
    {
      boolean didRemove = existingOwner.removeWall(this);
      if (!didRemove)
      {
        owner = existingOwner;
        return wasSet;
      }
    }
    owner.addWall(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setMove(WallMove aNewMove)
  {
    boolean wasSet = false;
    if (move != null && !move.equals(aNewMove) && equals(move.getWallPlaced()))
    {
      //Unable to setMove, as existing move would become an orphan
      return wasSet;
    }

    move = aNewMove;
    Wall anOldWallPlaced = aNewMove != null ? aNewMove.getWallPlaced() : null;

    if (!this.equals(anOldWallPlaced))
    {
      if (anOldWallPlaced != null)
      {
        anOldWallPlaced.move = null;
      }
      if (move != null)
      {
        move.setWallPlaced(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    wallsById.remove(getId());
    Player placeholderOwner = owner;
    this.owner = null;
    if(placeholderOwner != null)
    {
      placeholderOwner.removeWall(this);
    }
    WallMove existingMove = move;
    move = null;
    if (existingMove != null)
    {
      existingMove.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "owner = "+(getOwner()!=null?Integer.toHexString(System.identityHashCode(getOwner())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "move = "+(getMove()!=null?Integer.toHexString(System.identityHashCode(getMove())):"null");
  }
}