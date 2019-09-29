/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.quoridor.model;
import java.util.*;

import ca.mcgill.ecse223.quoridor.model.Movement.MoveType;

// line 40 "../domain_model_3.0.ump"
// line 98 "../domain_model_3.0.ump"
public class Position
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum WallDirection { Horizontal, Vertical }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Position Associations
  private List<Wall> wall;
  private List<Pawn> Pawn;
  private Movement ChangedPart;
  private QuoridorGame Onegame;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Position(Movement aChangedPart, QuoridorGame aOnegame)
  {
    wall = new ArrayList<Wall>();
    Pawn = new ArrayList<Pawn>();
    if (aChangedPart == null || aChangedPart.getWholeView() != null)
    {
      throw new RuntimeException("Unable to create Position due to aChangedPart");
    }
    ChangedPart = aChangedPart;
    if (aOnegame == null || aOnegame.getViewOfTheGame() != null)
    {
      throw new RuntimeException("Unable to create Position due to aOnegame");
    }
    Onegame = aOnegame;
  }

  public Position(MoveType aMoveTypeForChangedPart)
  {
    wall = new ArrayList<Wall>();
    Pawn = new ArrayList<Pawn>();
    ChangedPart = new Movement(aMoveTypeForChangedPart, this);
    Onegame = new QuoridorGame(this);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Wall getWall(int index)
  {
    Wall aWall = wall.get(index);
    return aWall;
  }

  public List<Wall> getWall()
  {
    List<Wall> newWall = Collections.unmodifiableList(wall);
    return newWall;
  }

  public int numberOfWall()
  {
    int number = wall.size();
    return number;
  }

  public boolean hasWall()
  {
    boolean has = wall.size() > 0;
    return has;
  }

  public int indexOfWall(Wall aWall)
  {
    int index = wall.indexOf(aWall);
    return index;
  }
  /* Code from template association_GetMany */
  public Pawn getPawn(int index)
  {
    Pawn aPawn = Pawn.get(index);
    return aPawn;
  }

  public List<Pawn> getPawn()
  {
    List<Pawn> newPawn = Collections.unmodifiableList(Pawn);
    return newPawn;
  }

  public int numberOfPawn()
  {
    int number = Pawn.size();
    return number;
  }

  public boolean hasPawn()
  {
    boolean has = Pawn.size() > 0;
    return has;
  }

  public int indexOfPawn(Pawn aPawn)
  {
    int index = Pawn.indexOf(aPawn);
    return index;
  }
  /* Code from template association_GetOne */
  public Movement getChangedPart()
  {
    return ChangedPart;
  }
  /* Code from template association_GetOne */
  public QuoridorGame getOnegame()
  {
    return Onegame;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfWall()
  {
    return 0;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfWall()
  {
    return 20;
  }
  /* Code from template association_AddOptionalNToOne */
  public Wall addWall(String aCoordinate, WallDirection aWallDirection)
  {
    if (numberOfWall() >= maximumNumberOfWall())
    {
      return null;
    }
    else
    {
      return new Wall(aCoordinate, aWallDirection, this);
    }
  }

  public boolean addWall(Wall aWall)
  {
    boolean wasAdded = false;
    if (wall.contains(aWall)) { return false; }
    if (numberOfWall() >= maximumNumberOfWall())
    {
      return wasAdded;
    }

    Position existingWallPositionOnBoard = aWall.getWallPositionOnBoard();
    boolean isNewWallPositionOnBoard = existingWallPositionOnBoard != null && !this.equals(existingWallPositionOnBoard);
    if (isNewWallPositionOnBoard)
    {
      aWall.setWallPositionOnBoard(this);
    }
    else
    {
      wall.add(aWall);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeWall(Wall aWall)
  {
    boolean wasRemoved = false;
    //Unable to remove aWall, as it must always have a WallPositionOnBoard
    if (!this.equals(aWall.getWallPositionOnBoard()))
    {
      wall.remove(aWall);
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
      if(index > numberOfWall()) { index = numberOfWall() - 1; }
      wall.remove(aWall);
      wall.add(index, aWall);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveWallAt(Wall aWall, int index)
  {
    boolean wasAdded = false;
    if(wall.contains(aWall))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWall()) { index = numberOfWall() - 1; }
      wall.remove(aWall);
      wall.add(index, aWall);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addWallAt(aWall, index);
    }
    return wasAdded;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfPawnValid()
  {
    boolean isValid = numberOfPawn() >= minimumNumberOfPawn() && numberOfPawn() <= maximumNumberOfPawn();
    return isValid;
  }
  /* Code from template association_RequiredNumberOfMethod */
  public static int requiredNumberOfPawn()
  {
    return 2;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPawn()
  {
    return 2;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfPawn()
  {
    return 2;
  }
  /* Code from template association_AddMNToOnlyOne */
  public Pawn addPawn(String aCoordinate, Player aControlOf)
  {
    if (numberOfPawn() >= maximumNumberOfPawn())
    {
      return null;
    }
    else
    {
      return new Pawn(aCoordinate, this, aControlOf);
    }
  }

  public boolean addPawn(Pawn aPawn)
  {
    boolean wasAdded = false;
    if (Pawn.contains(aPawn)) { return false; }
    if (numberOfPawn() >= maximumNumberOfPawn())
    {
      return wasAdded;
    }

    Position existingPawnPositionOnBoard = aPawn.getPawnPositionOnBoard();
    boolean isNewPawnPositionOnBoard = existingPawnPositionOnBoard != null && !this.equals(existingPawnPositionOnBoard);

    if (isNewPawnPositionOnBoard && existingPawnPositionOnBoard.numberOfPawn() <= minimumNumberOfPawn())
    {
      return wasAdded;
    }

    if (isNewPawnPositionOnBoard)
    {
      aPawn.setPawnPositionOnBoard(this);
    }
    else
    {
      Pawn.add(aPawn);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePawn(Pawn aPawn)
  {
    boolean wasRemoved = false;
    //Unable to remove aPawn, as it must always have a PawnPositionOnBoard
    if (this.equals(aPawn.getPawnPositionOnBoard()))
    {
      return wasRemoved;
    }

    //PawnPositionOnBoard already at minimum (2)
    if (numberOfPawn() <= minimumNumberOfPawn())
    {
      return wasRemoved;
    }
    Pawn.remove(aPawn);
    wasRemoved = true;
    return wasRemoved;
  }

  public void delete()
  {
    for(int i=wall.size(); i > 0; i--)
    {
      Wall aWall = wall.get(i - 1);
      aWall.delete();
    }
    for(int i=Pawn.size(); i > 0; i--)
    {
      Pawn aPawn = Pawn.get(i - 1);
      aPawn.delete();
    }
    Movement existingChangedPart = ChangedPart;
    ChangedPart = null;
    if (existingChangedPart != null)
    {
      existingChangedPart.delete();
    }
    QuoridorGame existingOnegame = Onegame;
    Onegame = null;
    if (existingOnegame != null)
    {
      existingOnegame.delete();
    }
  }

}