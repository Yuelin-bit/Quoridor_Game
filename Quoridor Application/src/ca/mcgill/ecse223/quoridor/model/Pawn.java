/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.quoridor.model;

import ca.mcgill.ecse223.quoridor.model.Player.PlayerType;

// line 45 "../domain_model_3.0.ump"
// line 105 "../domain_model_3.0.ump"
public class Pawn
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Pawn Attributes
  private String Coordinate;

  //Pawn Associations
  private Position PawnPositionOnBoard;
  private Player ControlOf;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Pawn(String aCoordinate, Position aPawnPositionOnBoard, Player aControlOf)
  {
    Coordinate = aCoordinate;
    boolean didAddPawnPositionOnBoard = setPawnPositionOnBoard(aPawnPositionOnBoard);
    if (!didAddPawnPositionOnBoard)
    {
      throw new RuntimeException("Unable to create Pawn due to PawnPositionOnBoard");
    }
    if (aControlOf == null || aControlOf.getControlBy() != null)
    {
      throw new RuntimeException("Unable to create Pawn due to aControlOf");
    }
    ControlOf = aControlOf;
  }

  public Pawn(String aCoordinate, Position aPawnPositionOnBoard, PlayerType aPlayerTypeForControlOf, int aNum_of_wall_leftForControlOf, QuoridorGame aGameForControlOf, User aAllUsersLoginedInForControlOf)
  {
    Coordinate = aCoordinate;
    boolean didAddPawnPositionOnBoard = setPawnPositionOnBoard(aPawnPositionOnBoard);
    if (!didAddPawnPositionOnBoard)
    {
      throw new RuntimeException("Unable to create Pawn due to PawnPositionOnBoard");
    }
    ControlOf = new Player(aPlayerTypeForControlOf, aNum_of_wall_leftForControlOf, this, aGameForControlOf, aAllUsersLoginedInForControlOf);
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

  public String getCoordinate()
  {
    return Coordinate;
  }
  /* Code from template association_GetOne */
  public Position getPawnPositionOnBoard()
  {
    return PawnPositionOnBoard;
  }
  /* Code from template association_GetOne */
  public Player getControlOf()
  {
    return ControlOf;
  }
  /* Code from template association_SetOneToAtMostN */
  public boolean setPawnPositionOnBoard(Position aPawnPositionOnBoard)
  {
    boolean wasSet = false;
    //Must provide PawnPositionOnBoard to Pawn
    if (aPawnPositionOnBoard == null)
    {
      return wasSet;
    }

    //PawnPositionOnBoard already at maximum (2)
    if (aPawnPositionOnBoard.numberOfPawn() >= Position.maximumNumberOfPawn())
    {
      return wasSet;
    }
    
    Position existingPawnPositionOnBoard = PawnPositionOnBoard;
    PawnPositionOnBoard = aPawnPositionOnBoard;
    if (existingPawnPositionOnBoard != null && !existingPawnPositionOnBoard.equals(aPawnPositionOnBoard))
    {
      boolean didRemove = existingPawnPositionOnBoard.removePawn(this);
      if (!didRemove)
      {
        PawnPositionOnBoard = existingPawnPositionOnBoard;
        return wasSet;
      }
    }
    PawnPositionOnBoard.addPawn(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Position placeholderPawnPositionOnBoard = PawnPositionOnBoard;
    this.PawnPositionOnBoard = null;
    if(placeholderPawnPositionOnBoard != null)
    {
      placeholderPawnPositionOnBoard.removePawn(this);
    }
    Player existingControlOf = ControlOf;
    ControlOf = null;
    if (existingControlOf != null)
    {
      existingControlOf.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "Coordinate" + ":" + getCoordinate()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "PawnPositionOnBoard = "+(getPawnPositionOnBoard()!=null?Integer.toHexString(System.identityHashCode(getPawnPositionOnBoard())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "ControlOf = "+(getControlOf()!=null?Integer.toHexString(System.identityHashCode(getControlOf())):"null");
  }
}