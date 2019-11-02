/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.quoridor.model;

// line 89 "../../../../../QuoridorGame.ump"
public class WallMove extends Move
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //WallMove Attributes
  private Direction wallDirection;

  //WallMove Associations
  private Wall wallPlaced;
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public WallMove(int aMoveNumber, int aRoundNumber, Player aPlayer, Tile aTargetTile, Game aGame, Direction aWallDirection, Wall aWallPlaced)
  {
    super(aMoveNumber, aRoundNumber, aPlayer, aTargetTile, aGame);
    wallDirection = aWallDirection;
    boolean didAddWallPlaced = setWallPlaced(aWallPlaced);
    if (!didAddWallPlaced)
    {
      throw new RuntimeException("Unable to create move due to wallPlaced");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setWallDirection(Direction aWallDirection)
  {
    boolean wasSet = false;
    wallDirection = aWallDirection;
    wasSet = true;
    return wasSet;
  }

  public Direction getWallDirection()
  {
    return wallDirection;
  }
  /* Code from template association_GetOne */
  public Wall getWallPlaced()
  {
    return wallPlaced;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }

  public boolean hasGame()
  {
    boolean has = game != null;
    return has;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setWallPlaced(Wall aNewWallPlaced)
  {
    boolean wasSet = false;
    if (aNewWallPlaced == null)
    {
      //Unable to setWallPlaced to null, as move must always be associated to a wallPlaced
      return wasSet;
    }
    
    WallMove existingMove = aNewWallPlaced.getMove();
    if (existingMove != null && !equals(existingMove))
    {
      //Unable to setWallPlaced, the current wallPlaced already has a move, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Wall anOldWallPlaced = wallPlaced;
    wallPlaced = aNewWallPlaced;
    wallPlaced.setMove(this);

    if (anOldWallPlaced != null)
    {
      anOldWallPlaced.setMove(null);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setGame(Game aNewGame)
  {
    boolean wasSet = false;
    if (aNewGame == null)
    {
      Game existingGame = game;
      game = null;
      
      if (existingGame != null && existingGame.getWallMoveCandidate() != null)
      {
        existingGame.setWallMoveCandidate(null);
      }
      wasSet = true;
      return wasSet;
    }

    Game currentGame = getGame();
    if (currentGame != null && !currentGame.equals(aNewGame))
    {
      currentGame.setWallMoveCandidate(null);
    }

    game = aNewGame;
    WallMove existingWallMoveCandidate = aNewGame.getWallMoveCandidate();

    if (!equals(existingWallMoveCandidate))
    {
      aNewGame.setWallMoveCandidate(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Wall existingWallPlaced = wallPlaced;
    wallPlaced = null;
    if (existingWallPlaced != null)
    {
      existingWallPlaced.setMove(null);
    }
    if (game != null)
    {
      game.setWallMoveCandidate(null);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "wallDirection" + "=" + (getWallDirection() != null ? !getWallDirection().equals(this)  ? getWallDirection().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "wallPlaced = "+(getWallPlaced()!=null?Integer.toHexString(System.identityHashCode(getWallPlaced())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }
}