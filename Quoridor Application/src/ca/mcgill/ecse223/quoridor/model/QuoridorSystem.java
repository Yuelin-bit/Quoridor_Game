/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.quoridor.model;
import java.util.*;

// line 24 "../domain_model_3.0.ump"
// line 78 "../domain_model_3.0.ump"
public class QuoridorSystem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //QuoridorSystem Associations
  private QuoridorGame game;
  private Board board;
  private List<User> AllUsersLoginedIn;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public QuoridorSystem(Board aBoard)
  {
    boolean didAddBoard = setBoard(aBoard);
    if (!didAddBoard)
    {
      throw new RuntimeException("Unable to create system due to board");
    }
    AllUsersLoginedIn = new ArrayList<User>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public QuoridorGame getGame()
  {
    return game;
  }

  public boolean hasGame()
  {
    boolean has = game != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Board getBoard()
  {
    return board;
  }
  /* Code from template association_GetMany */
  public User getAllUsersLoginedIn(int index)
  {
    User aAllUsersLoginedIn = AllUsersLoginedIn.get(index);
    return aAllUsersLoginedIn;
  }

  public List<User> getAllUsersLoginedIn()
  {
    List<User> newAllUsersLoginedIn = Collections.unmodifiableList(AllUsersLoginedIn);
    return newAllUsersLoginedIn;
  }

  public int numberOfAllUsersLoginedIn()
  {
    int number = AllUsersLoginedIn.size();
    return number;
  }

  public boolean hasAllUsersLoginedIn()
  {
    boolean has = AllUsersLoginedIn.size() > 0;
    return has;
  }

  public int indexOfAllUsersLoginedIn(User aAllUsersLoginedIn)
  {
    int index = AllUsersLoginedIn.indexOf(aAllUsersLoginedIn);
    return index;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setGame(QuoridorGame aNewGame)
  {
    boolean wasSet = false;
    if (aNewGame == null)
    {
      QuoridorGame existingGame = game;
      game = null;
      
      if (existingGame != null && existingGame.getSystem() != null)
      {
        existingGame.setSystem(null);
      }
      wasSet = true;
      return wasSet;
    }

    QuoridorGame currentGame = getGame();
    if (currentGame != null && !currentGame.equals(aNewGame))
    {
      currentGame.setSystem(null);
    }

    game = aNewGame;
    QuoridorSystem existingSystem = aNewGame.getSystem();

    if (!equals(existingSystem))
    {
      aNewGame.setSystem(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setBoard(Board aNewBoard)
  {
    boolean wasSet = false;
    if (aNewBoard == null)
    {
      //Unable to setBoard to null, as system must always be associated to a board
      return wasSet;
    }
    
    QuoridorSystem existingSystem = aNewBoard.getSystem();
    if (existingSystem != null && !equals(existingSystem))
    {
      //Unable to setBoard, the current board already has a system, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Board anOldBoard = board;
    board = aNewBoard;
    board.setSystem(this);

    if (anOldBoard != null)
    {
      anOldBoard.setSystem(null);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAllUsersLoginedIn()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addAllUsersLoginedIn(User aAllUsersLoginedIn)
  {
    boolean wasAdded = false;
    if (AllUsersLoginedIn.contains(aAllUsersLoginedIn)) { return false; }
    QuoridorSystem existingSystem = aAllUsersLoginedIn.getSystem();
    if (existingSystem == null)
    {
      aAllUsersLoginedIn.setSystem(this);
    }
    else if (!this.equals(existingSystem))
    {
      existingSystem.removeAllUsersLoginedIn(aAllUsersLoginedIn);
      addAllUsersLoginedIn(aAllUsersLoginedIn);
    }
    else
    {
      AllUsersLoginedIn.add(aAllUsersLoginedIn);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAllUsersLoginedIn(User aAllUsersLoginedIn)
  {
    boolean wasRemoved = false;
    if (AllUsersLoginedIn.contains(aAllUsersLoginedIn))
    {
      AllUsersLoginedIn.remove(aAllUsersLoginedIn);
      aAllUsersLoginedIn.setSystem(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAllUsersLoginedInAt(User aAllUsersLoginedIn, int index)
  {  
    boolean wasAdded = false;
    if(addAllUsersLoginedIn(aAllUsersLoginedIn))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAllUsersLoginedIn()) { index = numberOfAllUsersLoginedIn() - 1; }
      AllUsersLoginedIn.remove(aAllUsersLoginedIn);
      AllUsersLoginedIn.add(index, aAllUsersLoginedIn);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAllUsersLoginedInAt(User aAllUsersLoginedIn, int index)
  {
    boolean wasAdded = false;
    if(AllUsersLoginedIn.contains(aAllUsersLoginedIn))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAllUsersLoginedIn()) { index = numberOfAllUsersLoginedIn() - 1; }
      AllUsersLoginedIn.remove(aAllUsersLoginedIn);
      AllUsersLoginedIn.add(index, aAllUsersLoginedIn);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAllUsersLoginedInAt(aAllUsersLoginedIn, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    QuoridorGame existingGame = game;
    game = null;
    if (existingGame != null)
    {
      existingGame.delete();
      existingGame.setSystem(null);
    }
    Board existingBoard = board;
    board = null;
    if (existingBoard != null)
    {
      existingBoard.delete();
    }
    while (AllUsersLoginedIn.size() > 0)
    {
      User aAllUsersLoginedIn = AllUsersLoginedIn.get(AllUsersLoginedIn.size() - 1);
      aAllUsersLoginedIn.delete();
      AllUsersLoginedIn.remove(aAllUsersLoginedIn);
    }
    
  }

}