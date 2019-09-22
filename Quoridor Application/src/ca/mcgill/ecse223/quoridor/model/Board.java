/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.quoridor.model;
import java.util.*;

// line 16 "../domain_model_v1.2.ump"
// line 58 "../domain_model_v1.2.ump"
public class Board
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Board Attributes
  private List<String> movement_player1;
  private List<String> movement_player2;

  //Board Associations
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Board(Game aGame)
  {
    movement_player1 = new ArrayList<String>();
    movement_player2 = new ArrayList<String>();
    if (aGame == null || aGame.getBoard() != null)
    {
      throw new RuntimeException("Unable to create Board due to aGame");
    }
    game = aGame;
  }

  public Board(boolean aIsDoneForGame)
  {
    movement_player1 = new ArrayList<String>();
    movement_player2 = new ArrayList<String>();
    game = new Game(aIsDoneForGame, this);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template attribute_SetMany */
  public boolean addMovement_player1(String aMovement_player1)
  {
    boolean wasAdded = false;
    wasAdded = movement_player1.add(aMovement_player1);
    return wasAdded;
  }

  public boolean removeMovement_player1(String aMovement_player1)
  {
    boolean wasRemoved = false;
    wasRemoved = movement_player1.remove(aMovement_player1);
    return wasRemoved;
  }
  /* Code from template attribute_SetMany */
  public boolean addMovement_player2(String aMovement_player2)
  {
    boolean wasAdded = false;
    wasAdded = movement_player2.add(aMovement_player2);
    return wasAdded;
  }

  public boolean removeMovement_player2(String aMovement_player2)
  {
    boolean wasRemoved = false;
    wasRemoved = movement_player2.remove(aMovement_player2);
    return wasRemoved;
  }
  /* Code from template attribute_GetMany */
  public String getMovement_player1(int index)
  {
    String aMovement_player1 = movement_player1.get(index);
    return aMovement_player1;
  }

  public String[] getMovement_player1()
  {
    String[] newMovement_player1 = movement_player1.toArray(new String[movement_player1.size()]);
    return newMovement_player1;
  }

  public int numberOfMovement_player1()
  {
    int number = movement_player1.size();
    return number;
  }

  public boolean hasMovement_player1()
  {
    boolean has = movement_player1.size() > 0;
    return has;
  }

  public int indexOfMovement_player1(String aMovement_player1)
  {
    int index = movement_player1.indexOf(aMovement_player1);
    return index;
  }
  /* Code from template attribute_GetMany */
  public String getMovement_player2(int index)
  {
    String aMovement_player2 = movement_player2.get(index);
    return aMovement_player2;
  }

  public String[] getMovement_player2()
  {
    String[] newMovement_player2 = movement_player2.toArray(new String[movement_player2.size()]);
    return newMovement_player2;
  }

  public int numberOfMovement_player2()
  {
    int number = movement_player2.size();
    return number;
  }

  public boolean hasMovement_player2()
  {
    boolean has = movement_player2.size() > 0;
    return has;
  }

  public int indexOfMovement_player2(String aMovement_player2)
  {
    int index = movement_player2.indexOf(aMovement_player2);
    return index;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }

  public void delete()
  {
    Game existingGame = game;
    game = null;
    if (existingGame != null)
    {
      existingGame.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }
}