/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.quoridor.model;
import java.util.*;

// line 95 "../../../../../QuoridorGame.ump"
public class PlayerPosition
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PlayerPosition Associations
  private Player player;
  private Tile tile;
  private GamePosition whiteInGame;
  private GamePosition blackInGame;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlayerPosition(Player aPlayer, Tile aTile)
  {
    if (!setPlayer(aPlayer))
    {
      throw new RuntimeException("Unable to create PlayerPosition due to aPlayer");
    }
    if (!setTile(aTile))
    {
      throw new RuntimeException("Unable to create PlayerPosition due to aTile");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Player getPlayer()
  {
    return player;
  }
  /* Code from template association_GetOne */
  public Tile getTile()
  {
    return tile;
  }
  /* Code from template association_GetOne */
  public GamePosition getWhiteInGame()
  {
    return whiteInGame;
  }

  public boolean hasWhiteInGame()
  {
    boolean has = whiteInGame != null;
    return has;
  }
  /* Code from template association_GetOne */
  public GamePosition getBlackInGame()
  {
    return blackInGame;
  }

  public boolean hasBlackInGame()
  {
    boolean has = blackInGame != null;
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
  public boolean setTile(Tile aNewTile)
  {
    boolean wasSet = false;
    if (aNewTile != null)
    {
      tile = aNewTile;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setWhiteInGame(GamePosition aNewWhiteInGame)
  {
    boolean wasSet = false;
    if (whiteInGame != null && !whiteInGame.equals(aNewWhiteInGame) && equals(whiteInGame.getWhitePosition()))
    {
      //Unable to setWhiteInGame, as existing whiteInGame would become an orphan
      return wasSet;
    }

    whiteInGame = aNewWhiteInGame;
    PlayerPosition anOldWhitePosition = aNewWhiteInGame != null ? aNewWhiteInGame.getWhitePosition() : null;

    if (!this.equals(anOldWhitePosition))
    {
      if (anOldWhitePosition != null)
      {
        anOldWhitePosition.whiteInGame = null;
      }
      if (whiteInGame != null)
      {
        whiteInGame.setWhitePosition(this);
      }
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setBlackInGame(GamePosition aNewBlackInGame)
  {
    boolean wasSet = false;
    if (blackInGame != null && !blackInGame.equals(aNewBlackInGame) && equals(blackInGame.getBlackPosition()))
    {
      //Unable to setBlackInGame, as existing blackInGame would become an orphan
      return wasSet;
    }

    blackInGame = aNewBlackInGame;
    PlayerPosition anOldBlackPosition = aNewBlackInGame != null ? aNewBlackInGame.getBlackPosition() : null;

    if (!this.equals(anOldBlackPosition))
    {
      if (anOldBlackPosition != null)
      {
        anOldBlackPosition.blackInGame = null;
      }
      if (blackInGame != null)
      {
        blackInGame.setBlackPosition(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    player = null;
    tile = null;
    GamePosition existingWhiteInGame = whiteInGame;
    whiteInGame = null;
    if (existingWhiteInGame != null)
    {
      existingWhiteInGame.delete();
    }
    GamePosition existingBlackInGame = blackInGame;
    blackInGame = null;
    if (existingBlackInGame != null)
    {
      existingBlackInGame.delete();
    }
  }

}