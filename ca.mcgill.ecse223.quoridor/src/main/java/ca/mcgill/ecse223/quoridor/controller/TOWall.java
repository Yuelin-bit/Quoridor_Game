/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.quoridor.controller;

// line 16 "../../../../../QuoridorGameTransferObject.ump"
public class TOWall
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Direction2 { Horizontal, Vertical }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOWall Attributes
  private int row;
  private int column;
  private Direction2 dir;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOWall(int aRow, int aColumn, Direction2 aDir)
  {
    row = aRow;
    column = aColumn;
    dir = aDir;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setRow(int aRow)
  {
    boolean wasSet = false;
    row = aRow;
    wasSet = true;
    return wasSet;
  }

  public boolean setColumn(int aColumn)
  {
    boolean wasSet = false;
    column = aColumn;
    wasSet = true;
    return wasSet;
  }

  public boolean setDir(Direction2 aDir)
  {
    boolean wasSet = false;
    dir = aDir;
    wasSet = true;
    return wasSet;
  }

  public int getRow()
  {
    return row;
  }

  public int getColumn()
  {
    return column;
  }

  public Direction2 getDir()
  {
    return dir;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "row" + ":" + getRow()+ "," +
            "column" + ":" + getColumn()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "dir" + "=" + (getDir() != null ? !getDir().equals(this)  ? getDir().toString().replaceAll("  ","    ") : "this" : "null");
  }
}