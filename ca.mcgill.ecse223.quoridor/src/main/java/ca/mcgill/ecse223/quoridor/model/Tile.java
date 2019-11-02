/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.quoridor.model;

// line 19 "../../../../../QuoridorGame.ump"
public class Tile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Tile Attributes
  private int row;
  private int column;

  //Tile Associations
  private Board board;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Tile(int aRow, int aColumn, Board aBoard)
  {
    row = aRow;
    column = aColumn;
    boolean didAddBoard = setBoard(aBoard);
    if (!didAddBoard)
    {
      throw new RuntimeException("Unable to create tile due to board");
    }
    if (aRow<1||aRow>9)
    {
      throw new RuntimeException("Please provide a valid row [row>=1&&row<=9]");
    }
    if (aColumn<1||aColumn>9)
    {
      throw new RuntimeException("Please provide a valid column [column>=1&&column<=9]");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public int getRow()
  {
    return row;
  }

  public int getColumn()
  {
    return column;
  }
  /* Code from template association_GetOne */
  public Board getBoard()
  {
    return board;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBoard(Board aBoard)
  {
    boolean wasSet = false;
    if (aBoard == null)
    {
      return wasSet;
    }

    Board existingBoard = board;
    board = aBoard;
    if (existingBoard != null && !existingBoard.equals(aBoard))
    {
      existingBoard.removeTile(this);
    }
    board.addTile(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Board placeholderBoard = board;
    this.board = null;
    if(placeholderBoard != null)
    {
      placeholderBoard.removeTile(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "row" + ":" + getRow()+ "," +
            "column" + ":" + getColumn()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "board = "+(getBoard()!=null?Integer.toHexString(System.identityHashCode(getBoard())):"null");
  }
}