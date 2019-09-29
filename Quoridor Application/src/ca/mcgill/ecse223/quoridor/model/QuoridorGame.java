/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.quoridor.model;
import java.util.*;

// line 28 "../domain_model_3.0.ump"
// line 83 "../domain_model_3.0.ump"
public class QuoridorGame
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum PlayerType { Black, White }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //QuoridorGame Associations
  private QuoridorSystem system;
  private List<Player> Participant;
  private Position ViewOfTheGame;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public QuoridorGame(Position aViewOfTheGame)
  {
    Participant = new ArrayList<Player>();
    if (aViewOfTheGame == null || aViewOfTheGame.getOnegame() != null)
    {
      throw new RuntimeException("Unable to create QuoridorGame due to aViewOfTheGame");
    }
    ViewOfTheGame = aViewOfTheGame;
  }

  public QuoridorGame(Movement aChangedPartForViewOfTheGame)
  {
    Participant = new ArrayList<Player>();
    ViewOfTheGame = new Position(aChangedPartForViewOfTheGame, this);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public QuoridorSystem getSystem()
  {
    return system;
  }

  public boolean hasSystem()
  {
    boolean has = system != null;
    return has;
  }
  /* Code from template association_GetMany */
  public Player getParticipant(int index)
  {
    Player aParticipant = Participant.get(index);
    return aParticipant;
  }

  public List<Player> getParticipant()
  {
    List<Player> newParticipant = Collections.unmodifiableList(Participant);
    return newParticipant;
  }

  public int numberOfParticipant()
  {
    int number = Participant.size();
    return number;
  }

  public boolean hasParticipant()
  {
    boolean has = Participant.size() > 0;
    return has;
  }

  public int indexOfParticipant(Player aParticipant)
  {
    int index = Participant.indexOf(aParticipant);
    return index;
  }
  /* Code from template association_GetOne */
  public Position getViewOfTheGame()
  {
    return ViewOfTheGame;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setSystem(QuoridorSystem aNewSystem)
  {
    boolean wasSet = false;
    if (aNewSystem == null)
    {
      QuoridorSystem existingSystem = system;
      system = null;
      
      if (existingSystem != null && existingSystem.getGame() != null)
      {
        existingSystem.setGame(null);
      }
      wasSet = true;
      return wasSet;
    }

    QuoridorSystem currentSystem = getSystem();
    if (currentSystem != null && !currentSystem.equals(aNewSystem))
    {
      currentSystem.setGame(null);
    }

    system = aNewSystem;
    QuoridorGame existingGame = aNewSystem.getGame();

    if (!equals(existingGame))
    {
      aNewSystem.setGame(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfParticipantValid()
  {
    boolean isValid = numberOfParticipant() >= minimumNumberOfParticipant() && numberOfParticipant() <= maximumNumberOfParticipant();
    return isValid;
  }
  /* Code from template association_RequiredNumberOfMethod */
  public static int requiredNumberOfParticipant()
  {
    return 2;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfParticipant()
  {
    return 2;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfParticipant()
  {
    return 2;
  }
  /* Code from template association_AddMNToOnlyOne */
  public Player addParticipant(PlayerType aPlayerType, int aNum_of_wall_left, Pawn aControlBy, User aAllUsersLoginedIn)
  {
    if (numberOfParticipant() >= maximumNumberOfParticipant())
    {
      return null;
    }
    else
    {
      return new Player(aPlayerType, aNum_of_wall_left, aControlBy, this, aAllUsersLoginedIn);
    }
  }

  public boolean addParticipant(Player aParticipant)
  {
    boolean wasAdded = false;
    if (Participant.contains(aParticipant)) { return false; }
    if (numberOfParticipant() >= maximumNumberOfParticipant())
    {
      return wasAdded;
    }

    QuoridorGame existingGame = aParticipant.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);

    if (isNewGame && existingGame.numberOfParticipant() <= minimumNumberOfParticipant())
    {
      return wasAdded;
    }

    if (isNewGame)
    {
      aParticipant.setGame(this);
    }
    else
    {
      Participant.add(aParticipant);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeParticipant(Player aParticipant)
  {
    boolean wasRemoved = false;
    //Unable to remove aParticipant, as it must always have a game
    if (this.equals(aParticipant.getGame()))
    {
      return wasRemoved;
    }

    //game already at minimum (2)
    if (numberOfParticipant() <= minimumNumberOfParticipant())
    {
      return wasRemoved;
    }
    Participant.remove(aParticipant);
    wasRemoved = true;
    return wasRemoved;
  }

  public void delete()
  {
    if (system != null)
    {
      system.setGame(null);
    }
    for(int i=Participant.size(); i > 0; i--)
    {
      Player aParticipant = Participant.get(i - 1);
      aParticipant.delete();
    }
    Position existingViewOfTheGame = ViewOfTheGame;
    ViewOfTheGame = null;
    if (existingViewOfTheGame != null)
    {
      existingViewOfTheGame.delete();
    }
  }

}