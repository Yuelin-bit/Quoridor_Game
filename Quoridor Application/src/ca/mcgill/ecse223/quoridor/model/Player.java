/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.quoridor.model;

// line 50 "../domain_model_3.0.ump"
// line 110 "../domain_model_3.0.ump"
public class Player
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum PlayerType { Black, White }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes
  private ca.mcgill.ecse223.quoridor.model.Player.PlayerType PlayerType;
  private int num_of_wall_left;

  //Player Associations
  private Pawn ControlBy;
  private QuoridorGame game;
  private User AllUsersLoginedIn;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(ca.mcgill.ecse223.quoridor.model.Player.PlayerType aPlayerTypeForControlOf, int aNum_of_wall_left, Pawn aControlBy, QuoridorGame aGame, User aAllUsersLoginedIn)
  {
    PlayerType = aPlayerTypeForControlOf;
    num_of_wall_left = aNum_of_wall_left;
    if (aControlBy == null || aControlBy.getControlOf() != null)
    {
      throw new RuntimeException("Unable to create Player due to aControlBy");
    }
    ControlBy = aControlBy;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create Participant due to game");
    }
    if (!setAllUsersLoginedIn(aAllUsersLoginedIn))
    {
      throw new RuntimeException("Unable to create Player due to aAllUsersLoginedIn");
    }
  }

  public Player(ca.mcgill.ecse223.quoridor.model.Player.PlayerType aPlayerType, int aNum_of_wall_left, String aCoordinateForControlBy, Position aPawnPositionOnBoardForControlBy, QuoridorGame aGame, User aAllUsersLoginedIn)
  {
    PlayerType = aPlayerType;
    num_of_wall_left = aNum_of_wall_left;
    ControlBy = new Pawn(aCoordinateForControlBy, aPawnPositionOnBoardForControlBy, this);
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create Participant due to game");
    }
    boolean didAddAllUsersLoginedIn = setAllUsersLoginedIn(aAllUsersLoginedIn);
    if (!didAddAllUsersLoginedIn)
    {
      throw new RuntimeException("Unable to create CurrentUser due to AllUsersLoginedIn");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Player(ca.mcgill.ecse223.quoridor.model.QuoridorGame.PlayerType aPlayerType, int aNum_of_wall_left,
		Pawn aControlBy, QuoridorGame aGame, User aAllUsersLoginedIn) {
	// TODO Auto-generated constructor stub
}

public boolean setPlayerType(PlayerType aPlayerType)
  {
    boolean wasSet = false;
    PlayerType = aPlayerType;
    wasSet = true;
    return wasSet;
  }

  public boolean setNum_of_wall_left(int aNum_of_wall_left)
  {
    boolean wasSet = false;
    num_of_wall_left = aNum_of_wall_left;
    wasSet = true;
    return wasSet;
  }

  public ca.mcgill.ecse223.quoridor.model.Player.PlayerType getPlayerType()
  {
    return PlayerType;
  }

  public int getNum_of_wall_left()
  {
    return num_of_wall_left;
  }
  /* Code from template association_GetOne */
  public Pawn getControlBy()
  {
    return ControlBy;
  }
  /* Code from template association_GetOne */
  public QuoridorGame getGame()
  {
    return game;
  }
  /* Code from template association_GetOne */
  public User getAllUsersLoginedIn()
  {
    return AllUsersLoginedIn;
  }
  /* Code from template association_SetOneToAtMostN */
  public boolean setGame(QuoridorGame aGame)
  {
    boolean wasSet = false;
    //Must provide game to Participant
    if (aGame == null)
    {
      return wasSet;
    }

    //game already at maximum (2)
    if (aGame.numberOfParticipant() >= QuoridorGame.maximumNumberOfParticipant())
    {
      return wasSet;
    }
    
    QuoridorGame existingGame = game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      boolean didRemove = existingGame.removeParticipant(this);
      if (!didRemove)
      {
        game = existingGame;
        return wasSet;
      }
    }
    game.addParticipant(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setAllUsersLoginedIn(User aNewAllUsersLoginedIn)
  {
    boolean wasSet = false;
    if (aNewAllUsersLoginedIn != null)
    {
      AllUsersLoginedIn = aNewAllUsersLoginedIn;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    Pawn existingControlBy = ControlBy;
    ControlBy = null;
    if (existingControlBy != null)
    {
      existingControlBy.delete();
    }
    QuoridorGame placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeParticipant(this);
    }
    AllUsersLoginedIn = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "num_of_wall_left" + ":" + getNum_of_wall_left()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "PlayerType" + "=" + (getPlayerType() != null ? !getPlayerType().equals(this)  ? getPlayerType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "ControlBy = "+(getControlBy()!=null?Integer.toHexString(System.identityHashCode(getControlBy())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "AllUsersLoginedIn = "+(getAllUsersLoginedIn()!=null?Integer.toHexString(System.identityHashCode(getAllUsersLoginedIn())):"null");
  }
}