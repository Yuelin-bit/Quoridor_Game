/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.quoridor.model;
import java.sql.Time;
import java.util.*;

// line 34 "../../../../../QuoridorGame.ump"
public class Player
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes
  private Time remainingTime;

  //Player Associations
  private User user;
  private Destination destination;
  private List<Wall> walls;
  private Player nextPlayer;
  private Game gameAsWhite;
  private Game gameAsBlack;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(Time aRemainingTime, User aUser, Destination aDestination)
  {
    remainingTime = aRemainingTime;
    if (!setUser(aUser))
    {
      throw new RuntimeException("Unable to create Player due to aUser");
    }
    if (aDestination == null || aDestination.getPlayer() != null)
    {
      throw new RuntimeException("Unable to create Player due to aDestination");
    }
    destination = aDestination;
    walls = new ArrayList<Wall>();
  }

  public Player(Time aRemainingTime, User aUser, int aTargetNumberForDestination, Direction aDirectionForDestination)
  {
    remainingTime = aRemainingTime;
    boolean didAddUser = setUser(aUser);
    if (!didAddUser)
    {
      throw new RuntimeException("Unable to create player due to user");
    }
    destination = new Destination(aTargetNumberForDestination, aDirectionForDestination, this);
    walls = new ArrayList<Wall>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setRemainingTime(Time aRemainingTime)
  {
    boolean wasSet = false;
    remainingTime = aRemainingTime;
    wasSet = true;
    return wasSet;
  }

  public Time getRemainingTime()
  {
    return remainingTime;
  }
  /* Code from template association_GetOne */
  public User getUser()
  {
    return user;
  }
  /* Code from template association_GetOne */
  public Destination getDestination()
  {
    return destination;
  }
  /* Code from template association_GetMany */
  public Wall getWall(int index)
  {
    Wall aWall = walls.get(index);
    return aWall;
  }

  public List<Wall> getWalls()
  {
    List<Wall> newWalls = Collections.unmodifiableList(walls);
    return newWalls;
  }

  public int numberOfWalls()
  {
    int number = walls.size();
    return number;
  }

  public boolean hasWalls()
  {
    boolean has = walls.size() > 0;
    return has;
  }

  public int indexOfWall(Wall aWall)
  {
    int index = walls.indexOf(aWall);
    return index;
  }
  /* Code from template association_GetOne */
  public Player getNextPlayer()
  {
    return nextPlayer;
  }

  public boolean hasNextPlayer()
  {
    boolean has = nextPlayer != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Game getGameAsWhite()
  {
    return gameAsWhite;
  }

  public boolean hasGameAsWhite()
  {
    boolean has = gameAsWhite != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Game getGameAsBlack()
  {
    return gameAsBlack;
  }

  public boolean hasGameAsBlack()
  {
    boolean has = gameAsBlack != null;
    return has;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setUser(User aNewUser)
  {
    boolean wasSet = false;
    if (aNewUser != null)
    {
      user = aNewUser;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfWalls()
  {
    return 0;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfWalls()
  {
    return 10;
  }
  /* Code from template association_AddOptionalNToOne */
  public Wall addWall(int aId)
  {
    if (numberOfWalls() >= maximumNumberOfWalls())
    {
      return null;
    }
    else
    {
      return new Wall(aId, this);
    }
  }

  public boolean addWall(Wall aWall)
  {
    boolean wasAdded = false;
    if (walls.contains(aWall)) { return false; }
    if (numberOfWalls() >= maximumNumberOfWalls())
    {
      return wasAdded;
    }

    Player existingOwner = aWall.getOwner();
    boolean isNewOwner = existingOwner != null && !this.equals(existingOwner);
    if (isNewOwner)
    {
      aWall.setOwner(this);
    }
    else
    {
      walls.add(aWall);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeWall(Wall aWall)
  {
    boolean wasRemoved = false;
    //Unable to remove aWall, as it must always have a owner
    if (!this.equals(aWall.getOwner()))
    {
      walls.remove(aWall);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addWallAt(Wall aWall, int index)
  {  
    boolean wasAdded = false;
    if(addWall(aWall))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWalls()) { index = numberOfWalls() - 1; }
      walls.remove(aWall);
      walls.add(index, aWall);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveWallAt(Wall aWall, int index)
  {
    boolean wasAdded = false;
    if(walls.contains(aWall))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWalls()) { index = numberOfWalls() - 1; }
      walls.remove(aWall);
      walls.add(index, aWall);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addWallAt(aWall, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setNextPlayer(Player aNewNextPlayer)
  {
    boolean wasSet = false;
    nextPlayer = aNewNextPlayer;
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setGameAsWhite(Game aNewGameAsWhite)
  {
    boolean wasSet = false;
    if (aNewGameAsWhite == null)
    {
      Game existingGameAsWhite = gameAsWhite;
      gameAsWhite = null;
      
      if (existingGameAsWhite != null && existingGameAsWhite.getWhitePlayer() != null)
      {
        existingGameAsWhite.setWhitePlayer(null);
      }
      wasSet = true;
      return wasSet;
    }

    Game currentGameAsWhite = getGameAsWhite();
    if (currentGameAsWhite != null && !currentGameAsWhite.equals(aNewGameAsWhite))
    {
      currentGameAsWhite.setWhitePlayer(null);
    }

    gameAsWhite = aNewGameAsWhite;
    Player existingWhitePlayer = aNewGameAsWhite.getWhitePlayer();

    if (!equals(existingWhitePlayer))
    {
      aNewGameAsWhite.setWhitePlayer(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setGameAsBlack(Game aNewGameAsBlack)
  {
    boolean wasSet = false;
    if (aNewGameAsBlack == null)
    {
      Game existingGameAsBlack = gameAsBlack;
      gameAsBlack = null;
      
      if (existingGameAsBlack != null && existingGameAsBlack.getBlackPlayer() != null)
      {
        existingGameAsBlack.setBlackPlayer(null);
      }
      wasSet = true;
      return wasSet;
    }

    Game currentGameAsBlack = getGameAsBlack();
    if (currentGameAsBlack != null && !currentGameAsBlack.equals(aNewGameAsBlack))
    {
      currentGameAsBlack.setBlackPlayer(null);
    }

    gameAsBlack = aNewGameAsBlack;
    Player existingBlackPlayer = aNewGameAsBlack.getBlackPlayer();

    if (!equals(existingBlackPlayer))
    {
      aNewGameAsBlack.setBlackPlayer(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    user = null;
    Destination existingDestination = destination;
    destination = null;
    if (existingDestination != null)
    {
      existingDestination.delete();
    }
    while (walls.size() > 0)
    {
      Wall aWall = walls.get(walls.size() - 1);
      aWall.delete();
      walls.remove(aWall);
    }
    
    nextPlayer = null;
    if (gameAsWhite != null)
    {
      gameAsWhite.setWhitePlayer(null);
    }
    if (gameAsBlack != null)
    {
      gameAsBlack.setBlackPlayer(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "remainingTime" + "=" + (getRemainingTime() != null ? !getRemainingTime().equals(this)  ? getRemainingTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "user = "+(getUser()!=null?Integer.toHexString(System.identityHashCode(getUser())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "destination = "+(getDestination()!=null?Integer.toHexString(System.identityHashCode(getDestination())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "gameAsWhite = "+(getGameAsWhite()!=null?Integer.toHexString(System.identityHashCode(getGameAsWhite())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "gameAsBlack = "+(getGameAsBlack()!=null?Integer.toHexString(System.identityHashCode(getGameAsBlack())):"null");
  }
}