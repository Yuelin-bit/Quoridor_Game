/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.quoridor.model;

// line 72 "../../../../../QuoridorGame.ump"
public abstract class Move
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Move Attributes
  private int moveNumber;
  private int roundNumber;

  //Move Associations
  private Player player;
  private Tile targetTile;
  private Move nextMove;
  private Game game;
  private Move prevMove;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Move(int aMoveNumber, int aRoundNumber, Player aPlayer, Tile aTargetTile, Game aGame)
  {
    moveNumber = aMoveNumber;
    roundNumber = aRoundNumber;
    if (!setPlayer(aPlayer))
    {
      throw new RuntimeException("Unable to create Move due to aPlayer");
    }
    if (!setTargetTile(aTargetTile))
    {
      throw new RuntimeException("Unable to create Move due to aTargetTile");
    }
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create move due to game");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMoveNumber(int aMoveNumber)
  {
    boolean wasSet = false;
    moveNumber = aMoveNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setRoundNumber(int aRoundNumber)
  {
    boolean wasSet = false;
    roundNumber = aRoundNumber;
    wasSet = true;
    return wasSet;
  }

  public int getMoveNumber()
  {
    return moveNumber;
  }

  public int getRoundNumber()
  {
    return roundNumber;
  }
  /* Code from template association_GetOne */
  public Player getPlayer()
  {
    return player;
  }
  /* Code from template association_GetOne */
  public Tile getTargetTile()
  {
    return targetTile;
  }
  /* Code from template association_GetOne */
  public Move getNextMove()
  {
    return nextMove;
  }

  public boolean hasNextMove()
  {
    boolean has = nextMove != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetOne */
  public Move getPrevMove()
  {
    return prevMove;
  }

  public boolean hasPrevMove()
  {
    boolean has = prevMove != null;
    return has;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setPlayer(Player aNewPlayer)
  {
    boolean wasSet = false;
    if (aNewPlayer != null)
    {
      player = aNewPlayer;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setTargetTile(Tile aNewTargetTile)
  {
    boolean wasSet = false;
    if (aNewTargetTile != null)
    {
      targetTile = aNewTargetTile;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setNextMove(Move aNewNextMove)
  {
    boolean wasSet = false;
    if (aNewNextMove == null)
    {
      Move existingNextMove = nextMove;
      nextMove = null;
      
      if (existingNextMove != null && existingNextMove.getPrevMove() != null)
      {
        existingNextMove.setPrevMove(null);
      }
      wasSet = true;
      return wasSet;
    }

    Move currentNextMove = getNextMove();
    if (currentNextMove != null && !currentNextMove.equals(aNewNextMove))
    {
      currentNextMove.setPrevMove(null);
    }

    nextMove = aNewNextMove;
    Move existingPrevMove = aNewNextMove.getPrevMove();

    if (!equals(existingPrevMove))
    {
      aNewNextMove.setPrevMove(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGame(Game aGame)
  {
    boolean wasSet = false;
    if (aGame == null)
    {
      return wasSet;
    }

    Game existingGame = game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      existingGame.removeMove(this);
    }
    game.addMove(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setPrevMove(Move aNewPrevMove)
  {
    boolean wasSet = false;
    if (aNewPrevMove == null)
    {
      Move existingPrevMove = prevMove;
      prevMove = null;
      
      if (existingPrevMove != null && existingPrevMove.getNextMove() != null)
      {
        existingPrevMove.setNextMove(null);
      }
      wasSet = true;
      return wasSet;
    }

    Move currentPrevMove = getPrevMove();
    if (currentPrevMove != null && !currentPrevMove.equals(aNewPrevMove))
    {
      currentPrevMove.setNextMove(null);
    }

    prevMove = aNewPrevMove;
    Move existingNextMove = aNewPrevMove.getNextMove();

    if (!equals(existingNextMove))
    {
      aNewPrevMove.setNextMove(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    player = null;
    targetTile = null;
    if (nextMove != null)
    {
      nextMove.setPrevMove(null);
    }
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeMove(this);
    }
    if (prevMove != null)
    {
      prevMove.setNextMove(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "moveNumber" + ":" + getMoveNumber()+ "," +
            "roundNumber" + ":" + getRoundNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "targetTile = "+(getTargetTile()!=null?Integer.toHexString(System.identityHashCode(getTargetTile())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }
}