/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.quoridor.model;
import java.util.*;

// line 1 "../domain_model_v1.2.ump"
// line 44 "../domain_model_v1.2.ump"
public class Game
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Game Attributes
  private boolean isDone;

  //Game Associations
  private List<Player> players;
  private List<Round> rounds;
  private Board board;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game(boolean aIsDone, Board aBoard)
  {
    isDone = aIsDone;
    players = new ArrayList<Player>();
    rounds = new ArrayList<Round>();
    if (aBoard == null || aBoard.getGame() != null)
    {
      throw new RuntimeException("Unable to create Game due to aBoard");
    }
    board = aBoard;
  }

  public Game(boolean aIsDone)
  {
    isDone = aIsDone;
    players = new ArrayList<Player>();
    rounds = new ArrayList<Round>();
    board = new Board(this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setIsDone(boolean aIsDone)
  {
    boolean wasSet = false;
    isDone = aIsDone;
    wasSet = true;
    return wasSet;
  }

  public boolean getIsDone()
  {
    return isDone;
  }
  /* Code from template association_GetMany */
  public Player getPlayer(int index)
  {
    Player aPlayer = players.get(index);
    return aPlayer;
  }

  public List<Player> getPlayers()
  {
    List<Player> newPlayers = Collections.unmodifiableList(players);
    return newPlayers;
  }

  public int numberOfPlayers()
  {
    int number = players.size();
    return number;
  }

  public boolean hasPlayers()
  {
    boolean has = players.size() > 0;
    return has;
  }

  public int indexOfPlayer(Player aPlayer)
  {
    int index = players.indexOf(aPlayer);
    return index;
  }
  /* Code from template association_GetMany */
  public Round getRound(int index)
  {
    Round aRound = rounds.get(index);
    return aRound;
  }

  public List<Round> getRounds()
  {
    List<Round> newRounds = Collections.unmodifiableList(rounds);
    return newRounds;
  }

  public int numberOfRounds()
  {
    int number = rounds.size();
    return number;
  }

  public boolean hasRounds()
  {
    boolean has = rounds.size() > 0;
    return has;
  }

  public int indexOfRound(Round aRound)
  {
    int index = rounds.indexOf(aRound);
    return index;
  }
  /* Code from template association_GetOne */
  public Board getBoard()
  {
    return board;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfPlayersValid()
  {
    boolean isValid = numberOfPlayers() >= minimumNumberOfPlayers() && numberOfPlayers() <= maximumNumberOfPlayers();
    return isValid;
  }
  /* Code from template association_RequiredNumberOfMethod */
  public static int requiredNumberOfPlayers()
  {
    return 2;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPlayers()
  {
    return 2;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfPlayers()
  {
    return 2;
  }
  /* Code from template association_AddMNToOnlyOne */
  public Player addPlayer(String aName, int aNum_wall_left, boolean aIsWinner, Pawn aPawn)
  {
    if (numberOfPlayers() >= maximumNumberOfPlayers())
    {
      return null;
    }
    else
    {
      return new Player(aName, aNum_wall_left, aIsWinner, this, aPawn);
    }
  }

  public boolean addPlayer(Player aPlayer)
  {
    boolean wasAdded = false;
    if (players.contains(aPlayer)) { return false; }
    if (numberOfPlayers() >= maximumNumberOfPlayers())
    {
      return wasAdded;
    }

    Game existingGame = aPlayer.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);

    if (isNewGame && existingGame.numberOfPlayers() <= minimumNumberOfPlayers())
    {
      return wasAdded;
    }

    if (isNewGame)
    {
      aPlayer.setGame(this);
    }
    else
    {
      players.add(aPlayer);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePlayer(Player aPlayer)
  {
    boolean wasRemoved = false;
    //Unable to remove aPlayer, as it must always have a game
    if (this.equals(aPlayer.getGame()))
    {
      return wasRemoved;
    }

    //game already at minimum (2)
    if (numberOfPlayers() <= minimumNumberOfPlayers())
    {
      return wasRemoved;
    }
    players.remove(aPlayer);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRounds()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Round addRound(String aMovement)
  {
    return new Round(aMovement, this);
  }

  public boolean addRound(Round aRound)
  {
    boolean wasAdded = false;
    if (rounds.contains(aRound)) { return false; }
    Game existingGame = aRound.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);
    if (isNewGame)
    {
      aRound.setGame(this);
    }
    else
    {
      rounds.add(aRound);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRound(Round aRound)
  {
    boolean wasRemoved = false;
    //Unable to remove aRound, as it must always have a game
    if (!this.equals(aRound.getGame()))
    {
      rounds.remove(aRound);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addRoundAt(Round aRound, int index)
  {  
    boolean wasAdded = false;
    if(addRound(aRound))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRounds()) { index = numberOfRounds() - 1; }
      rounds.remove(aRound);
      rounds.add(index, aRound);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRoundAt(Round aRound, int index)
  {
    boolean wasAdded = false;
    if(rounds.contains(aRound))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRounds()) { index = numberOfRounds() - 1; }
      rounds.remove(aRound);
      rounds.add(index, aRound);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRoundAt(aRound, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=players.size(); i > 0; i--)
    {
      Player aPlayer = players.get(i - 1);
      aPlayer.delete();
    }
    for(int i=rounds.size(); i > 0; i--)
    {
      Round aRound = rounds.get(i - 1);
      aRound.delete();
    }
    Board existingBoard = board;
    board = null;
    if (existingBoard != null)
    {
      existingBoard.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "isDone" + ":" + getIsDone()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "board = "+(getBoard()!=null?Integer.toHexString(System.identityHashCode(getBoard())):"null");
  }
}