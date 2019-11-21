/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.quoridor.model;
import java.util.*;

// line 58 "../../../../../QuoridorGame.ump"
public class GamePosition
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, GamePosition> gamepositionsById = new HashMap<Integer, GamePosition>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GamePosition Attributes
  private int id;

  //GamePosition Associations
  private PlayerPosition whitePosition;
  private PlayerPosition yellowPosition;
  private PlayerPosition blackPosition;
  private PlayerPosition redPosition;
  private Player playerToMove;
  private List<Wall> whiteWallsOnBoard;
  private List<Wall> yellowWallsOnBoard;
  private List<Wall> blackWallsOnBoard;
  private List<Wall> redWallsOnBoard;
  private List<Wall> whiteWallsInStock;
  private List<Wall> yellowWallsInStock;
  private List<Wall> blackWallsInStock;
  private List<Wall> redWallsInStock;
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GamePosition(int aId, PlayerPosition aWhitePosition, PlayerPosition aYellowPosition, PlayerPosition aBlackPosition, PlayerPosition aRedPosition, Player aPlayerToMove, Game aGame)
  {
    if (!setId(aId))
    {
      throw new RuntimeException("Cannot create due to duplicate id");
    }
    boolean didAddWhitePosition = setWhitePosition(aWhitePosition);
    if (!didAddWhitePosition)
    {
      throw new RuntimeException("Unable to create whiteInGame due to whitePosition");
    }
    boolean didAddYellowPosition = setYellowPosition(aYellowPosition);
    if (!didAddYellowPosition)
    {
      throw new RuntimeException("Unable to create yellowInGame due to yellowPosition");
    }
    boolean didAddBlackPosition = setBlackPosition(aBlackPosition);
    if (!didAddBlackPosition)
    {
      throw new RuntimeException("Unable to create blackInGame due to blackPosition");
    }
    boolean didAddRedPosition = setRedPosition(aRedPosition);
    if (!didAddRedPosition)
    {
      throw new RuntimeException("Unable to create redInGame due to redPosition");
    }
    if (!setPlayerToMove(aPlayerToMove))
    {
      throw new RuntimeException("Unable to create GamePosition due to aPlayerToMove");
    }
    whiteWallsOnBoard = new ArrayList<Wall>();
    yellowWallsOnBoard = new ArrayList<Wall>();
    blackWallsOnBoard = new ArrayList<Wall>();
    redWallsOnBoard = new ArrayList<Wall>();
    whiteWallsInStock = new ArrayList<Wall>();
    yellowWallsInStock = new ArrayList<Wall>();
    blackWallsInStock = new ArrayList<Wall>();
    redWallsInStock = new ArrayList<Wall>();
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create position due to game");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    Integer anOldId = getId();
    if (hasWithId(aId)) {
      return wasSet;
    }
    id = aId;
    wasSet = true;
    if (anOldId != null) {
      gamepositionsById.remove(anOldId);
    }
    gamepositionsById.put(aId, this);
    return wasSet;
  }

  public int getId()
  {
    return id;
  }
  /* Code from template attribute_GetUnique */
  public static GamePosition getWithId(int aId)
  {
    return gamepositionsById.get(aId);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithId(int aId)
  {
    return getWithId(aId) != null;
  }
  /* Code from template association_GetOne */
  public PlayerPosition getWhitePosition()
  {
    return whitePosition;
  }
  /* Code from template association_GetOne */
  public PlayerPosition getYellowPosition()
  {
    return yellowPosition;
  }
  /* Code from template association_GetOne */
  public PlayerPosition getBlackPosition()
  {
    return blackPosition;
  }
  /* Code from template association_GetOne */
  public PlayerPosition getRedPosition()
  {
    return redPosition;
  }
  /* Code from template association_GetOne */
  public Player getPlayerToMove()
  {
    return playerToMove;
  }
  /* Code from template association_GetMany */
  public Wall getWhiteWallsOnBoard(int index)
  {
    Wall aWhiteWallsOnBoard = whiteWallsOnBoard.get(index);
    return aWhiteWallsOnBoard;
  }

  public List<Wall> getWhiteWallsOnBoard()
  {
    List<Wall> newWhiteWallsOnBoard = Collections.unmodifiableList(whiteWallsOnBoard);
    return newWhiteWallsOnBoard;
  }

  public int numberOfWhiteWallsOnBoard()
  {
    int number = whiteWallsOnBoard.size();
    return number;
  }

  public boolean hasWhiteWallsOnBoard()
  {
    boolean has = whiteWallsOnBoard.size() > 0;
    return has;
  }

  public int indexOfWhiteWallsOnBoard(Wall aWhiteWallsOnBoard)
  {
    int index = whiteWallsOnBoard.indexOf(aWhiteWallsOnBoard);
    return index;
  }
  /* Code from template association_GetMany */
  public Wall getYellowWallsOnBoard(int index)
  {
    Wall aYellowWallsOnBoard = yellowWallsOnBoard.get(index);
    return aYellowWallsOnBoard;
  }

  public List<Wall> getYellowWallsOnBoard()
  {
    List<Wall> newYellowWallsOnBoard = Collections.unmodifiableList(yellowWallsOnBoard);
    return newYellowWallsOnBoard;
  }

  public int numberOfYellowWallsOnBoard()
  {
    int number = yellowWallsOnBoard.size();
    return number;
  }

  public boolean hasYellowWallsOnBoard()
  {
    boolean has = yellowWallsOnBoard.size() > 0;
    return has;
  }

  public int indexOfYellowWallsOnBoard(Wall aYellowWallsOnBoard)
  {
    int index = yellowWallsOnBoard.indexOf(aYellowWallsOnBoard);
    return index;
  }
  /* Code from template association_GetMany */
  public Wall getBlackWallsOnBoard(int index)
  {
    Wall aBlackWallsOnBoard = blackWallsOnBoard.get(index);
    return aBlackWallsOnBoard;
  }

  public List<Wall> getBlackWallsOnBoard()
  {
    List<Wall> newBlackWallsOnBoard = Collections.unmodifiableList(blackWallsOnBoard);
    return newBlackWallsOnBoard;
  }

  public int numberOfBlackWallsOnBoard()
  {
    int number = blackWallsOnBoard.size();
    return number;
  }

  public boolean hasBlackWallsOnBoard()
  {
    boolean has = blackWallsOnBoard.size() > 0;
    return has;
  }

  public int indexOfBlackWallsOnBoard(Wall aBlackWallsOnBoard)
  {
    int index = blackWallsOnBoard.indexOf(aBlackWallsOnBoard);
    return index;
  }
  /* Code from template association_GetMany */
  public Wall getRedWallsOnBoard(int index)
  {
    Wall aRedWallsOnBoard = redWallsOnBoard.get(index);
    return aRedWallsOnBoard;
  }

  public List<Wall> getRedWallsOnBoard()
  {
    List<Wall> newRedWallsOnBoard = Collections.unmodifiableList(redWallsOnBoard);
    return newRedWallsOnBoard;
  }

  public int numberOfRedWallsOnBoard()
  {
    int number = redWallsOnBoard.size();
    return number;
  }

  public boolean hasRedWallsOnBoard()
  {
    boolean has = redWallsOnBoard.size() > 0;
    return has;
  }

  public int indexOfRedWallsOnBoard(Wall aRedWallsOnBoard)
  {
    int index = redWallsOnBoard.indexOf(aRedWallsOnBoard);
    return index;
  }
  /* Code from template association_GetMany */
  public Wall getWhiteWallsInStock(int index)
  {
    Wall aWhiteWallsInStock = whiteWallsInStock.get(index);
    return aWhiteWallsInStock;
  }

  public List<Wall> getWhiteWallsInStock()
  {
    List<Wall> newWhiteWallsInStock = Collections.unmodifiableList(whiteWallsInStock);
    return newWhiteWallsInStock;
  }

  public int numberOfWhiteWallsInStock()
  {
    int number = whiteWallsInStock.size();
    return number;
  }

  public boolean hasWhiteWallsInStock()
  {
    boolean has = whiteWallsInStock.size() > 0;
    return has;
  }

  public int indexOfWhiteWallsInStock(Wall aWhiteWallsInStock)
  {
    int index = whiteWallsInStock.indexOf(aWhiteWallsInStock);
    return index;
  }
  /* Code from template association_GetMany */
  public Wall getYellowWallsInStock(int index)
  {
    Wall aYellowWallsInStock = yellowWallsInStock.get(index);
    return aYellowWallsInStock;
  }

  public List<Wall> getYellowWallsInStock()
  {
    List<Wall> newYellowWallsInStock = Collections.unmodifiableList(yellowWallsInStock);
    return newYellowWallsInStock;
  }

  public int numberOfYellowWallsInStock()
  {
    int number = yellowWallsInStock.size();
    return number;
  }

  public boolean hasYellowWallsInStock()
  {
    boolean has = yellowWallsInStock.size() > 0;
    return has;
  }

  public int indexOfYellowWallsInStock(Wall aYellowWallsInStock)
  {
    int index = yellowWallsInStock.indexOf(aYellowWallsInStock);
    return index;
  }
  /* Code from template association_GetMany */
  public Wall getBlackWallsInStock(int index)
  {
    Wall aBlackWallsInStock = blackWallsInStock.get(index);
    return aBlackWallsInStock;
  }

  public List<Wall> getBlackWallsInStock()
  {
    List<Wall> newBlackWallsInStock = Collections.unmodifiableList(blackWallsInStock);
    return newBlackWallsInStock;
  }

  public int numberOfBlackWallsInStock()
  {
    int number = blackWallsInStock.size();
    return number;
  }

  public boolean hasBlackWallsInStock()
  {
    boolean has = blackWallsInStock.size() > 0;
    return has;
  }

  public int indexOfBlackWallsInStock(Wall aBlackWallsInStock)
  {
    int index = blackWallsInStock.indexOf(aBlackWallsInStock);
    return index;
  }
  /* Code from template association_GetMany */
  public Wall getRedWallsInStock(int index)
  {
    Wall aRedWallsInStock = redWallsInStock.get(index);
    return aRedWallsInStock;
  }

  public List<Wall> getRedWallsInStock()
  {
    List<Wall> newRedWallsInStock = Collections.unmodifiableList(redWallsInStock);
    return newRedWallsInStock;
  }

  public int numberOfRedWallsInStock()
  {
    int number = redWallsInStock.size();
    return number;
  }

  public boolean hasRedWallsInStock()
  {
    boolean has = redWallsInStock.size() > 0;
    return has;
  }

  public int indexOfRedWallsInStock(Wall aRedWallsInStock)
  {
    int index = redWallsInStock.indexOf(aRedWallsInStock);
    return index;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setWhitePosition(PlayerPosition aNewWhitePosition)
  {
    boolean wasSet = false;
    if (aNewWhitePosition == null)
    {
      //Unable to setWhitePosition to null, as whiteInGame must always be associated to a whitePosition
      return wasSet;
    }
    
    GamePosition existingWhiteInGame = aNewWhitePosition.getWhiteInGame();
    if (existingWhiteInGame != null && !equals(existingWhiteInGame))
    {
      //Unable to setWhitePosition, the current whitePosition already has a whiteInGame, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    PlayerPosition anOldWhitePosition = whitePosition;
    whitePosition = aNewWhitePosition;
    whitePosition.setWhiteInGame(this);

    if (anOldWhitePosition != null)
    {
      anOldWhitePosition.setWhiteInGame(null);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setYellowPosition(PlayerPosition aNewYellowPosition)
  {
    boolean wasSet = false;
    if (aNewYellowPosition == null)
    {
      //Unable to setYellowPosition to null, as yellowInGame must always be associated to a yellowPosition
      return wasSet;
    }
    
    GamePosition existingYellowInGame = aNewYellowPosition.getYellowInGame();
    if (existingYellowInGame != null && !equals(existingYellowInGame))
    {
      //Unable to setYellowPosition, the current yellowPosition already has a yellowInGame, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    PlayerPosition anOldYellowPosition = yellowPosition;
    yellowPosition = aNewYellowPosition;
    yellowPosition.setYellowInGame(this);

    if (anOldYellowPosition != null)
    {
      anOldYellowPosition.setYellowInGame(null);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setBlackPosition(PlayerPosition aNewBlackPosition)
  {
    boolean wasSet = false;
    if (aNewBlackPosition == null)
    {
      //Unable to setBlackPosition to null, as blackInGame must always be associated to a blackPosition
      return wasSet;
    }
    
    GamePosition existingBlackInGame = aNewBlackPosition.getBlackInGame();
    if (existingBlackInGame != null && !equals(existingBlackInGame))
    {
      //Unable to setBlackPosition, the current blackPosition already has a blackInGame, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    PlayerPosition anOldBlackPosition = blackPosition;
    blackPosition = aNewBlackPosition;
    blackPosition.setBlackInGame(this);

    if (anOldBlackPosition != null)
    {
      anOldBlackPosition.setBlackInGame(null);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setRedPosition(PlayerPosition aNewRedPosition)
  {
    boolean wasSet = false;
    if (aNewRedPosition == null)
    {
      //Unable to setRedPosition to null, as redInGame must always be associated to a redPosition
      return wasSet;
    }
    
    GamePosition existingRedInGame = aNewRedPosition.getRedInGame();
    if (existingRedInGame != null && !equals(existingRedInGame))
    {
      //Unable to setRedPosition, the current redPosition already has a redInGame, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    PlayerPosition anOldRedPosition = redPosition;
    redPosition = aNewRedPosition;
    redPosition.setRedInGame(this);

    if (anOldRedPosition != null)
    {
      anOldRedPosition.setRedInGame(null);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setPlayerToMove(Player aNewPlayerToMove)
  {
    boolean wasSet = false;
    if (aNewPlayerToMove != null)
    {
      playerToMove = aNewPlayerToMove;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfWhiteWallsOnBoard()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addWhiteWallsOnBoard(Wall aWhiteWallsOnBoard)
  {
    boolean wasAdded = false;
    if (whiteWallsOnBoard.contains(aWhiteWallsOnBoard)) { return false; }
    whiteWallsOnBoard.add(aWhiteWallsOnBoard);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeWhiteWallsOnBoard(Wall aWhiteWallsOnBoard)
  {
    boolean wasRemoved = false;
    if (whiteWallsOnBoard.contains(aWhiteWallsOnBoard))
    {
      whiteWallsOnBoard.remove(aWhiteWallsOnBoard);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addWhiteWallsOnBoardAt(Wall aWhiteWallsOnBoard, int index)
  {  
    boolean wasAdded = false;
    if(addWhiteWallsOnBoard(aWhiteWallsOnBoard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWhiteWallsOnBoard()) { index = numberOfWhiteWallsOnBoard() - 1; }
      whiteWallsOnBoard.remove(aWhiteWallsOnBoard);
      whiteWallsOnBoard.add(index, aWhiteWallsOnBoard);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveWhiteWallsOnBoardAt(Wall aWhiteWallsOnBoard, int index)
  {
    boolean wasAdded = false;
    if(whiteWallsOnBoard.contains(aWhiteWallsOnBoard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWhiteWallsOnBoard()) { index = numberOfWhiteWallsOnBoard() - 1; }
      whiteWallsOnBoard.remove(aWhiteWallsOnBoard);
      whiteWallsOnBoard.add(index, aWhiteWallsOnBoard);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addWhiteWallsOnBoardAt(aWhiteWallsOnBoard, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfYellowWallsOnBoard()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addYellowWallsOnBoard(Wall aYellowWallsOnBoard)
  {
    boolean wasAdded = false;
    if (yellowWallsOnBoard.contains(aYellowWallsOnBoard)) { return false; }
    yellowWallsOnBoard.add(aYellowWallsOnBoard);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeYellowWallsOnBoard(Wall aYellowWallsOnBoard)
  {
    boolean wasRemoved = false;
    if (yellowWallsOnBoard.contains(aYellowWallsOnBoard))
    {
      yellowWallsOnBoard.remove(aYellowWallsOnBoard);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addYellowWallsOnBoardAt(Wall aYellowWallsOnBoard, int index)
  {  
    boolean wasAdded = false;
    if(addYellowWallsOnBoard(aYellowWallsOnBoard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfYellowWallsOnBoard()) { index = numberOfYellowWallsOnBoard() - 1; }
      yellowWallsOnBoard.remove(aYellowWallsOnBoard);
      yellowWallsOnBoard.add(index, aYellowWallsOnBoard);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveYellowWallsOnBoardAt(Wall aYellowWallsOnBoard, int index)
  {
    boolean wasAdded = false;
    if(yellowWallsOnBoard.contains(aYellowWallsOnBoard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfYellowWallsOnBoard()) { index = numberOfYellowWallsOnBoard() - 1; }
      yellowWallsOnBoard.remove(aYellowWallsOnBoard);
      yellowWallsOnBoard.add(index, aYellowWallsOnBoard);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addYellowWallsOnBoardAt(aYellowWallsOnBoard, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlackWallsOnBoard()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addBlackWallsOnBoard(Wall aBlackWallsOnBoard)
  {
    boolean wasAdded = false;
    if (blackWallsOnBoard.contains(aBlackWallsOnBoard)) { return false; }
    blackWallsOnBoard.add(aBlackWallsOnBoard);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlackWallsOnBoard(Wall aBlackWallsOnBoard)
  {
    boolean wasRemoved = false;
    if (blackWallsOnBoard.contains(aBlackWallsOnBoard))
    {
      blackWallsOnBoard.remove(aBlackWallsOnBoard);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlackWallsOnBoardAt(Wall aBlackWallsOnBoard, int index)
  {  
    boolean wasAdded = false;
    if(addBlackWallsOnBoard(aBlackWallsOnBoard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlackWallsOnBoard()) { index = numberOfBlackWallsOnBoard() - 1; }
      blackWallsOnBoard.remove(aBlackWallsOnBoard);
      blackWallsOnBoard.add(index, aBlackWallsOnBoard);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBlackWallsOnBoardAt(Wall aBlackWallsOnBoard, int index)
  {
    boolean wasAdded = false;
    if(blackWallsOnBoard.contains(aBlackWallsOnBoard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlackWallsOnBoard()) { index = numberOfBlackWallsOnBoard() - 1; }
      blackWallsOnBoard.remove(aBlackWallsOnBoard);
      blackWallsOnBoard.add(index, aBlackWallsOnBoard);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBlackWallsOnBoardAt(aBlackWallsOnBoard, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRedWallsOnBoard()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addRedWallsOnBoard(Wall aRedWallsOnBoard)
  {
    boolean wasAdded = false;
    if (redWallsOnBoard.contains(aRedWallsOnBoard)) { return false; }
    redWallsOnBoard.add(aRedWallsOnBoard);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRedWallsOnBoard(Wall aRedWallsOnBoard)
  {
    boolean wasRemoved = false;
    if (redWallsOnBoard.contains(aRedWallsOnBoard))
    {
      redWallsOnBoard.remove(aRedWallsOnBoard);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addRedWallsOnBoardAt(Wall aRedWallsOnBoard, int index)
  {  
    boolean wasAdded = false;
    if(addRedWallsOnBoard(aRedWallsOnBoard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRedWallsOnBoard()) { index = numberOfRedWallsOnBoard() - 1; }
      redWallsOnBoard.remove(aRedWallsOnBoard);
      redWallsOnBoard.add(index, aRedWallsOnBoard);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRedWallsOnBoardAt(Wall aRedWallsOnBoard, int index)
  {
    boolean wasAdded = false;
    if(redWallsOnBoard.contains(aRedWallsOnBoard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRedWallsOnBoard()) { index = numberOfRedWallsOnBoard() - 1; }
      redWallsOnBoard.remove(aRedWallsOnBoard);
      redWallsOnBoard.add(index, aRedWallsOnBoard);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRedWallsOnBoardAt(aRedWallsOnBoard, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfWhiteWallsInStock()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addWhiteWallsInStock(Wall aWhiteWallsInStock)
  {
    boolean wasAdded = false;
    if (whiteWallsInStock.contains(aWhiteWallsInStock)) { return false; }
    whiteWallsInStock.add(aWhiteWallsInStock);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeWhiteWallsInStock(Wall aWhiteWallsInStock)
  {
    boolean wasRemoved = false;
    if (whiteWallsInStock.contains(aWhiteWallsInStock))
    {
      whiteWallsInStock.remove(aWhiteWallsInStock);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addWhiteWallsInStockAt(Wall aWhiteWallsInStock, int index)
  {  
    boolean wasAdded = false;
    if(addWhiteWallsInStock(aWhiteWallsInStock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWhiteWallsInStock()) { index = numberOfWhiteWallsInStock() - 1; }
      whiteWallsInStock.remove(aWhiteWallsInStock);
      whiteWallsInStock.add(index, aWhiteWallsInStock);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveWhiteWallsInStockAt(Wall aWhiteWallsInStock, int index)
  {
    boolean wasAdded = false;
    if(whiteWallsInStock.contains(aWhiteWallsInStock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWhiteWallsInStock()) { index = numberOfWhiteWallsInStock() - 1; }
      whiteWallsInStock.remove(aWhiteWallsInStock);
      whiteWallsInStock.add(index, aWhiteWallsInStock);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addWhiteWallsInStockAt(aWhiteWallsInStock, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfYellowWallsInStock()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addYellowWallsInStock(Wall aYellowWallsInStock)
  {
    boolean wasAdded = false;
    if (yellowWallsInStock.contains(aYellowWallsInStock)) { return false; }
    yellowWallsInStock.add(aYellowWallsInStock);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeYellowWallsInStock(Wall aYellowWallsInStock)
  {
    boolean wasRemoved = false;
    if (yellowWallsInStock.contains(aYellowWallsInStock))
    {
      yellowWallsInStock.remove(aYellowWallsInStock);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addYellowWallsInStockAt(Wall aYellowWallsInStock, int index)
  {  
    boolean wasAdded = false;
    if(addYellowWallsInStock(aYellowWallsInStock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfYellowWallsInStock()) { index = numberOfYellowWallsInStock() - 1; }
      yellowWallsInStock.remove(aYellowWallsInStock);
      yellowWallsInStock.add(index, aYellowWallsInStock);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveYellowWallsInStockAt(Wall aYellowWallsInStock, int index)
  {
    boolean wasAdded = false;
    if(yellowWallsInStock.contains(aYellowWallsInStock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfYellowWallsInStock()) { index = numberOfYellowWallsInStock() - 1; }
      yellowWallsInStock.remove(aYellowWallsInStock);
      yellowWallsInStock.add(index, aYellowWallsInStock);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addYellowWallsInStockAt(aYellowWallsInStock, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlackWallsInStock()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addBlackWallsInStock(Wall aBlackWallsInStock)
  {
    boolean wasAdded = false;
    if (blackWallsInStock.contains(aBlackWallsInStock)) { return false; }
    blackWallsInStock.add(aBlackWallsInStock);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlackWallsInStock(Wall aBlackWallsInStock)
  {
    boolean wasRemoved = false;
    if (blackWallsInStock.contains(aBlackWallsInStock))
    {
      blackWallsInStock.remove(aBlackWallsInStock);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlackWallsInStockAt(Wall aBlackWallsInStock, int index)
  {  
    boolean wasAdded = false;
    if(addBlackWallsInStock(aBlackWallsInStock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlackWallsInStock()) { index = numberOfBlackWallsInStock() - 1; }
      blackWallsInStock.remove(aBlackWallsInStock);
      blackWallsInStock.add(index, aBlackWallsInStock);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBlackWallsInStockAt(Wall aBlackWallsInStock, int index)
  {
    boolean wasAdded = false;
    if(blackWallsInStock.contains(aBlackWallsInStock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlackWallsInStock()) { index = numberOfBlackWallsInStock() - 1; }
      blackWallsInStock.remove(aBlackWallsInStock);
      blackWallsInStock.add(index, aBlackWallsInStock);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBlackWallsInStockAt(aBlackWallsInStock, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRedWallsInStock()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addRedWallsInStock(Wall aRedWallsInStock)
  {
    boolean wasAdded = false;
    if (redWallsInStock.contains(aRedWallsInStock)) { return false; }
    redWallsInStock.add(aRedWallsInStock);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRedWallsInStock(Wall aRedWallsInStock)
  {
    boolean wasRemoved = false;
    if (redWallsInStock.contains(aRedWallsInStock))
    {
      redWallsInStock.remove(aRedWallsInStock);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addRedWallsInStockAt(Wall aRedWallsInStock, int index)
  {  
    boolean wasAdded = false;
    if(addRedWallsInStock(aRedWallsInStock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRedWallsInStock()) { index = numberOfRedWallsInStock() - 1; }
      redWallsInStock.remove(aRedWallsInStock);
      redWallsInStock.add(index, aRedWallsInStock);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRedWallsInStockAt(Wall aRedWallsInStock, int index)
  {
    boolean wasAdded = false;
    if(redWallsInStock.contains(aRedWallsInStock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRedWallsInStock()) { index = numberOfRedWallsInStock() - 1; }
      redWallsInStock.remove(aRedWallsInStock);
      redWallsInStock.add(index, aRedWallsInStock);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRedWallsInStockAt(aRedWallsInStock, index);
    }
    return wasAdded;
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
      existingGame.removePosition(this);
    }
    game.addPosition(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    gamepositionsById.remove(getId());
    PlayerPosition existingWhitePosition = whitePosition;
    whitePosition = null;
    if (existingWhitePosition != null)
    {
      existingWhitePosition.delete();
    }
    PlayerPosition existingYellowPosition = yellowPosition;
    yellowPosition = null;
    if (existingYellowPosition != null)
    {
      existingYellowPosition.delete();
    }
    PlayerPosition existingBlackPosition = blackPosition;
    blackPosition = null;
    if (existingBlackPosition != null)
    {
      existingBlackPosition.delete();
    }
    PlayerPosition existingRedPosition = redPosition;
    redPosition = null;
    if (existingRedPosition != null)
    {
      existingRedPosition.delete();
    }
    playerToMove = null;
    whiteWallsOnBoard.clear();
    yellowWallsOnBoard.clear();
    blackWallsOnBoard.clear();
    redWallsOnBoard.clear();
    whiteWallsInStock.clear();
    yellowWallsInStock.clear();
    blackWallsInStock.clear();
    redWallsInStock.clear();
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removePosition(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "whitePosition = "+(getWhitePosition()!=null?Integer.toHexString(System.identityHashCode(getWhitePosition())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "yellowPosition = "+(getYellowPosition()!=null?Integer.toHexString(System.identityHashCode(getYellowPosition())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "blackPosition = "+(getBlackPosition()!=null?Integer.toHexString(System.identityHashCode(getBlackPosition())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "redPosition = "+(getRedPosition()!=null?Integer.toHexString(System.identityHashCode(getRedPosition())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "playerToMove = "+(getPlayerToMove()!=null?Integer.toHexString(System.identityHashCode(getPlayerToMove())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }
}