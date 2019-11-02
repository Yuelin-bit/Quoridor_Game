/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.quoridor.model;
import java.util.*;

// line 42 "../../../../../QuoridorGame.ump"
public class Game
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum GameStatus { Initializing, ReadyToStart, Running, WhiteWon, BlackWon, Draw, Replay }
  public enum MoveMode { WallMove, PlayerMove }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Game Attributes
  private GameStatus gameStatus;
  private MoveMode moveMode;

  //Game Associations
  private List<Move> moves;
  private GamePosition currentPosition;
  private List<GamePosition> positions;
  private WallMove wallMoveCandidate;
  private Player whitePlayer;
  private Player blackPlayer;
  private Quoridor quoridor;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game(GameStatus aGameStatus, MoveMode aMoveMode, Quoridor aQuoridor)
  {
    gameStatus = aGameStatus;
    moveMode = aMoveMode;
    moves = new ArrayList<Move>();
    positions = new ArrayList<GamePosition>();
    boolean didAddQuoridor = setQuoridor(aQuoridor);
    if (!didAddQuoridor)
    {
      throw new RuntimeException("Unable to create currentGame due to quoridor");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setGameStatus(GameStatus aGameStatus)
  {
    boolean wasSet = false;
    gameStatus = aGameStatus;
    wasSet = true;
    return wasSet;
  }

  public boolean setMoveMode(MoveMode aMoveMode)
  {
    boolean wasSet = false;
    moveMode = aMoveMode;
    wasSet = true;
    return wasSet;
  }

  public GameStatus getGameStatus()
  {
    return gameStatus;
  }

  public MoveMode getMoveMode()
  {
    return moveMode;
  }
  /* Code from template association_GetMany */
  public Move getMove(int index)
  {
    Move aMove = moves.get(index);
    return aMove;
  }

  public List<Move> getMoves()
  {
    List<Move> newMoves = Collections.unmodifiableList(moves);
    return newMoves;
  }

  public int numberOfMoves()
  {
    int number = moves.size();
    return number;
  }

  public boolean hasMoves()
  {
    boolean has = moves.size() > 0;
    return has;
  }

  public int indexOfMove(Move aMove)
  {
    int index = moves.indexOf(aMove);
    return index;
  }
  /* Code from template association_GetOne */
  public GamePosition getCurrentPosition()
  {
    return currentPosition;
  }

  public boolean hasCurrentPosition()
  {
    boolean has = currentPosition != null;
    return has;
  }
  /* Code from template association_GetMany */
  public GamePosition getPosition(int index)
  {
    GamePosition aPosition = positions.get(index);
    return aPosition;
  }

  public List<GamePosition> getPositions()
  {
    List<GamePosition> newPositions = Collections.unmodifiableList(positions);
    return newPositions;
  }

  public int numberOfPositions()
  {
    int number = positions.size();
    return number;
  }

  public boolean hasPositions()
  {
    boolean has = positions.size() > 0;
    return has;
  }

  public int indexOfPosition(GamePosition aPosition)
  {
    int index = positions.indexOf(aPosition);
    return index;
  }
  /* Code from template association_GetOne */
  public WallMove getWallMoveCandidate()
  {
    return wallMoveCandidate;
  }

  public boolean hasWallMoveCandidate()
  {
    boolean has = wallMoveCandidate != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Player getWhitePlayer()
  {
    return whitePlayer;
  }

  public boolean hasWhitePlayer()
  {
    boolean has = whitePlayer != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Player getBlackPlayer()
  {
    return blackPlayer;
  }

  public boolean hasBlackPlayer()
  {
    boolean has = blackPlayer != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Quoridor getQuoridor()
  {
    return quoridor;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMoves()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addMove(Move aMove)
  {
    boolean wasAdded = false;
    if (moves.contains(aMove)) { return false; }
    Game existingGame = aMove.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);
    if (isNewGame)
    {
      aMove.setGame(this);
    }
    else
    {
      moves.add(aMove);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMove(Move aMove)
  {
    boolean wasRemoved = false;
    //Unable to remove aMove, as it must always have a game
    if (!this.equals(aMove.getGame()))
    {
      moves.remove(aMove);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMoveAt(Move aMove, int index)
  {  
    boolean wasAdded = false;
    if(addMove(aMove))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMoves()) { index = numberOfMoves() - 1; }
      moves.remove(aMove);
      moves.add(index, aMove);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMoveAt(Move aMove, int index)
  {
    boolean wasAdded = false;
    if(moves.contains(aMove))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMoves()) { index = numberOfMoves() - 1; }
      moves.remove(aMove);
      moves.add(index, aMove);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMoveAt(aMove, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setCurrentPosition(GamePosition aNewCurrentPosition)
  {
    boolean wasSet = false;
    currentPosition = aNewCurrentPosition;
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPositions()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public GamePosition addPosition(int aId, PlayerPosition aWhitePosition, PlayerPosition aBlackPosition, Player aPlayerToMove)
  {
    return new GamePosition(aId, aWhitePosition, aBlackPosition, aPlayerToMove, this);
  }

  public boolean addPosition(GamePosition aPosition)
  {
    boolean wasAdded = false;
    if (positions.contains(aPosition)) { return false; }
    Game existingGame = aPosition.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);
    if (isNewGame)
    {
      aPosition.setGame(this);
    }
    else
    {
      positions.add(aPosition);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePosition(GamePosition aPosition)
  {
    boolean wasRemoved = false;
    //Unable to remove aPosition, as it must always have a game
    if (!this.equals(aPosition.getGame()))
    {
      positions.remove(aPosition);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPositionAt(GamePosition aPosition, int index)
  {  
    boolean wasAdded = false;
    if(addPosition(aPosition))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPositions()) { index = numberOfPositions() - 1; }
      positions.remove(aPosition);
      positions.add(index, aPosition);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePositionAt(GamePosition aPosition, int index)
  {
    boolean wasAdded = false;
    if(positions.contains(aPosition))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPositions()) { index = numberOfPositions() - 1; }
      positions.remove(aPosition);
      positions.add(index, aPosition);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPositionAt(aPosition, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setWallMoveCandidate(WallMove aNewWallMoveCandidate)
  {
    boolean wasSet = false;
    if (aNewWallMoveCandidate == null)
    {
      WallMove existingWallMoveCandidate = wallMoveCandidate;
      wallMoveCandidate = null;
      
      if (existingWallMoveCandidate != null && existingWallMoveCandidate.getGame() != null)
      {
        existingWallMoveCandidate.setGame(null);
      }
      wasSet = true;
      return wasSet;
    }

    WallMove currentWallMoveCandidate = getWallMoveCandidate();
    if (currentWallMoveCandidate != null && !currentWallMoveCandidate.equals(aNewWallMoveCandidate))
    {
      currentWallMoveCandidate.setGame(null);
    }

    wallMoveCandidate = aNewWallMoveCandidate;
    Game existingGame = aNewWallMoveCandidate.getGame();

    if (!equals(existingGame))
    {
      aNewWallMoveCandidate.setGame(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setWhitePlayer(Player aNewWhitePlayer)
  {
    boolean wasSet = false;
    if (aNewWhitePlayer == null)
    {
      Player existingWhitePlayer = whitePlayer;
      whitePlayer = null;
      
      if (existingWhitePlayer != null && existingWhitePlayer.getGameAsWhite() != null)
      {
        existingWhitePlayer.setGameAsWhite(null);
      }
      wasSet = true;
      return wasSet;
    }

    Player currentWhitePlayer = getWhitePlayer();
    if (currentWhitePlayer != null && !currentWhitePlayer.equals(aNewWhitePlayer))
    {
      currentWhitePlayer.setGameAsWhite(null);
    }

    whitePlayer = aNewWhitePlayer;
    Game existingGameAsWhite = aNewWhitePlayer.getGameAsWhite();

    if (!equals(existingGameAsWhite))
    {
      aNewWhitePlayer.setGameAsWhite(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setBlackPlayer(Player aNewBlackPlayer)
  {
    boolean wasSet = false;
    if (aNewBlackPlayer == null)
    {
      Player existingBlackPlayer = blackPlayer;
      blackPlayer = null;
      
      if (existingBlackPlayer != null && existingBlackPlayer.getGameAsBlack() != null)
      {
        existingBlackPlayer.setGameAsBlack(null);
      }
      wasSet = true;
      return wasSet;
    }

    Player currentBlackPlayer = getBlackPlayer();
    if (currentBlackPlayer != null && !currentBlackPlayer.equals(aNewBlackPlayer))
    {
      currentBlackPlayer.setGameAsBlack(null);
    }

    blackPlayer = aNewBlackPlayer;
    Game existingGameAsBlack = aNewBlackPlayer.getGameAsBlack();

    if (!equals(existingGameAsBlack))
    {
      aNewBlackPlayer.setGameAsBlack(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setQuoridor(Quoridor aNewQuoridor)
  {
    boolean wasSet = false;
    if (aNewQuoridor == null)
    {
      //Unable to setQuoridor to null, as currentGame must always be associated to a quoridor
      return wasSet;
    }
    
    Game existingCurrentGame = aNewQuoridor.getCurrentGame();
    if (existingCurrentGame != null && !equals(existingCurrentGame))
    {
      //Unable to setQuoridor, the current quoridor already has a currentGame, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Quoridor anOldQuoridor = quoridor;
    quoridor = aNewQuoridor;
    quoridor.setCurrentGame(this);

    if (anOldQuoridor != null)
    {
      anOldQuoridor.setCurrentGame(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (moves.size() > 0)
    {
      Move aMove = moves.get(moves.size() - 1);
      aMove.delete();
      moves.remove(aMove);
    }
    
    currentPosition = null;
    while (positions.size() > 0)
    {
      GamePosition aPosition = positions.get(positions.size() - 1);
      aPosition.delete();
      positions.remove(aPosition);
    }
    
    WallMove existingWallMoveCandidate = wallMoveCandidate;
    wallMoveCandidate = null;
    if (existingWallMoveCandidate != null)
    {
      existingWallMoveCandidate.delete();
      existingWallMoveCandidate.setGame(null);
    }
    Player existingWhitePlayer = whitePlayer;
    whitePlayer = null;
    if (existingWhitePlayer != null)
    {
      existingWhitePlayer.delete();
      existingWhitePlayer.setGameAsWhite(null);
    }
    Player existingBlackPlayer = blackPlayer;
    blackPlayer = null;
    if (existingBlackPlayer != null)
    {
      existingBlackPlayer.delete();
      existingBlackPlayer.setGameAsBlack(null);
    }
    Quoridor existingQuoridor = quoridor;
    quoridor = null;
    if (existingQuoridor != null)
    {
      existingQuoridor.setCurrentGame(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "gameStatus" + "=" + (getGameStatus() != null ? !getGameStatus().equals(this)  ? getGameStatus().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "moveMode" + "=" + (getMoveMode() != null ? !getMoveMode().equals(this)  ? getMoveMode().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "currentPosition = "+(getCurrentPosition()!=null?Integer.toHexString(System.identityHashCode(getCurrentPosition())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "wallMoveCandidate = "+(getWallMoveCandidate()!=null?Integer.toHexString(System.identityHashCode(getWallMoveCandidate())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "whitePlayer = "+(getWhitePlayer()!=null?Integer.toHexString(System.identityHashCode(getWhitePlayer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "blackPlayer = "+(getBlackPlayer()!=null?Integer.toHexString(System.identityHashCode(getBlackPlayer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "quoridor = "+(getQuoridor()!=null?Integer.toHexString(System.identityHashCode(getQuoridor())):"null");
  }
}