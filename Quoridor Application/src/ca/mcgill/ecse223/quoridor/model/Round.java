/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.quoridor.model;

// line 39 "../domain_model_v1.2.ump"
// line 79 "../domain_model_v1.2.ump"
public class Round
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Round Attributes
  private String movement;

  //Round Associations
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Round(String aMovement, Game aGame)
  {
    movement = aMovement;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create round due to game");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMovement(String aMovement)
  {
    boolean wasSet = false;
    movement = aMovement;
    wasSet = true;
    return wasSet;
  }

  public String getMovement()
  {
    return movement;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
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
      existingGame.removeRound(this);
    }
    game.addRound(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeRound(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "movement" + ":" + getMovement()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }
}