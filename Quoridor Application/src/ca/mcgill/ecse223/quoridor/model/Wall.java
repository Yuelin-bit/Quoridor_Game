/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.quoridor.model;

// line 57 "../domain_model_3.0.ump"
// line 118 "../domain_model_3.0.ump"
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
  private ca.mcgill.ecse223.quoridor.model.Position.WallDirection WallDirection;

  //Wall Associations
  private Position WallPositionOnBoard;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Wall(String aCoordinate, ca.mcgill.ecse223.quoridor.model.Position.WallDirection aWallDirection, Position aWallPositionOnBoard)
  {
    Coordinate = aCoordinate;
    WallDirection = aWallDirection;
    boolean didAddWallPositionOnBoard = setWallPositionOnBoard(aWallPositionOnBoard);
    if (!didAddWallPositionOnBoard)
    {
      throw new RuntimeException("Unable to create wall due to WallPositionOnBoard");
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

  public boolean setWallDirection(ca.mcgill.ecse223.quoridor.model.Position.WallDirection aWallDirection)
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

  public ca.mcgill.ecse223.quoridor.model.Position.WallDirection getWallDirection()
  {
    return WallDirection;
  }
  /* Code from template association_GetOne */
  public Position getWallPositionOnBoard()
  {
    return WallPositionOnBoard;
  }
  /* Code from template association_SetOneToAtMostN */
  public boolean setWallPositionOnBoard(Position aWallPositionOnBoard)
  {
    boolean wasSet = false;
    //Must provide WallPositionOnBoard to wall
    if (aWallPositionOnBoard == null)
    {
      return wasSet;
    }

    //WallPositionOnBoard already at maximum (20)
    if (aWallPositionOnBoard.numberOfWall() >= Position.maximumNumberOfWall())
    {
      return wasSet;
    }
    
    Position existingWallPositionOnBoard = WallPositionOnBoard;
    WallPositionOnBoard = aWallPositionOnBoard;
    if (existingWallPositionOnBoard != null && !existingWallPositionOnBoard.equals(aWallPositionOnBoard))
    {
      boolean didRemove = existingWallPositionOnBoard.removeWall(this);
      if (!didRemove)
      {
        WallPositionOnBoard = existingWallPositionOnBoard;
        return wasSet;
      }
    }
    WallPositionOnBoard.addWall(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Position placeholderWallPositionOnBoard = WallPositionOnBoard;
    this.WallPositionOnBoard = null;
    if(placeholderWallPositionOnBoard != null)
    {
      placeholderWallPositionOnBoard.removeWall(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "Coordinate" + ":" + getCoordinate()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "WallDirection" + "=" + (getWallDirection() != null ? !getWallDirection().equals(this)  ? getWallDirection().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "WallPositionOnBoard = "+(getWallPositionOnBoard()!=null?Integer.toHexString(System.identityHashCode(getWallPositionOnBoard())):"null");
  }
}