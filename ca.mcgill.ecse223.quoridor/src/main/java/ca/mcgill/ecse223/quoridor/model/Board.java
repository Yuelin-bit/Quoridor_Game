/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.quoridor.model;
import java.util.*;

// line 15 "../../../../../QuoridorGame.ump"
public class Board
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Board Associations
  private List<Tile> tiles;
  private Quoridor quoridor;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Board(Quoridor aQuoridor)
  {
    tiles = new ArrayList<Tile>();
    boolean didAddQuoridor = setQuoridor(aQuoridor);
    if (!didAddQuoridor)
    {
      throw new RuntimeException("Unable to create board due to quoridor");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Tile getTile(int index)
  {
    Tile aTile = tiles.get(index);
    return aTile;
  }

  public List<Tile> getTiles()
  {
    List<Tile> newTiles = Collections.unmodifiableList(tiles);
    return newTiles;
  }

  public int numberOfTiles()
  {
    int number = tiles.size();
    return number;
  }

  public boolean hasTiles()
  {
    boolean has = tiles.size() > 0;
    return has;
  }

  public int indexOfTile(Tile aTile)
  {
    int index = tiles.indexOf(aTile);
    return index;
  }
  /* Code from template association_GetOne */
  public Quoridor getQuoridor()
  {
    return quoridor;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTiles()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Tile addTile(int aRow, int aColumn)
  {
    return new Tile(aRow, aColumn, this);
  }

  public boolean addTile(Tile aTile)
  {
    boolean wasAdded = false;
    if (tiles.contains(aTile)) { return false; }
    Board existingBoard = aTile.getBoard();
    boolean isNewBoard = existingBoard != null && !this.equals(existingBoard);
    if (isNewBoard)
    {
      aTile.setBoard(this);
    }
    else
    {
      tiles.add(aTile);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTile(Tile aTile)
  {
    boolean wasRemoved = false;
    //Unable to remove aTile, as it must always have a board
    if (!this.equals(aTile.getBoard()))
    {
      tiles.remove(aTile);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTileAt(Tile aTile, int index)
  {  
    boolean wasAdded = false;
    if(addTile(aTile))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTiles()) { index = numberOfTiles() - 1; }
      tiles.remove(aTile);
      tiles.add(index, aTile);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTileAt(Tile aTile, int index)
  {
    boolean wasAdded = false;
    if(tiles.contains(aTile))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTiles()) { index = numberOfTiles() - 1; }
      tiles.remove(aTile);
      tiles.add(index, aTile);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTileAt(aTile, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setQuoridor(Quoridor aNewQuoridor)
  {
    boolean wasSet = false;
    if (aNewQuoridor == null)
    {
      //Unable to setQuoridor to null, as board must always be associated to a quoridor
      return wasSet;
    }
    
    Board existingBoard = aNewQuoridor.getBoard();
    if (existingBoard != null && !equals(existingBoard))
    {
      //Unable to setQuoridor, the current quoridor already has a board, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Quoridor anOldQuoridor = quoridor;
    quoridor = aNewQuoridor;
    quoridor.setBoard(this);

    if (anOldQuoridor != null)
    {
      anOldQuoridor.setBoard(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (tiles.size() > 0)
    {
      Tile aTile = tiles.get(tiles.size() - 1);
      aTile.delete();
      tiles.remove(aTile);
    }
    
    Quoridor existingQuoridor = quoridor;
    quoridor = null;
    if (existingQuoridor != null)
    {
      existingQuoridor.setBoard(null);
    }
  }

}