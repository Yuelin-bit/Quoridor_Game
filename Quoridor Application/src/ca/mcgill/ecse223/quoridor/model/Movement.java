/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.quoridor.model;
import java.util.*;

// line 68 "../domain_model_3.0.ump"
// line 127 "../domain_model_3.0.ump"
public class Movement
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum MoveType { JumpPawn, PlaceWall, NormalMove }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Movement Attributes
  private MoveType MoveType;

  //Movement Associations
  private List<Movement> next;
  private List<Movement> previous;
  private Position WholeView;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Movement(MoveType aMoveType, Position aWholeView)
  {
    MoveType = aMoveType;
    next = new ArrayList<Movement>();
    previous = new ArrayList<Movement>();
    if (aWholeView == null || aWholeView.getChangedPart() != null)
    {
      throw new RuntimeException("Unable to create Movement due to aWholeView");
    }
    WholeView = aWholeView;
  }

  public Movement(MoveType aMoveType, QuoridorGame aOnegameForWholeView)
  {
    MoveType = aMoveType;
    next = new ArrayList<Movement>();
    previous = new ArrayList<Movement>();
    WholeView = new Position(this, aOnegameForWholeView);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMoveType(MoveType aMoveType)
  {
    boolean wasSet = false;
    MoveType = aMoveType;
    wasSet = true;
    return wasSet;
  }

  public MoveType getMoveType()
  {
    return MoveType;
  }
  /* Code from template association_GetMany */
  public Movement getNext(int index)
  {
    Movement aNext = next.get(index);
    return aNext;
  }

  public List<Movement> getNext()
  {
    List<Movement> newNext = Collections.unmodifiableList(next);
    return newNext;
  }

  public int numberOfNext()
  {
    int number = next.size();
    return number;
  }

  public boolean hasNext()
  {
    boolean has = next.size() > 0;
    return has;
  }

  public int indexOfNext(Movement aNext)
  {
    int index = next.indexOf(aNext);
    return index;
  }
  /* Code from template association_GetMany */
  public Movement getPrevious(int index)
  {
    Movement aPrevious = previous.get(index);
    return aPrevious;
  }

  public List<Movement> getPrevious()
  {
    List<Movement> newPrevious = Collections.unmodifiableList(previous);
    return newPrevious;
  }

  public int numberOfPrevious()
  {
    int number = previous.size();
    return number;
  }

  public boolean hasPrevious()
  {
    boolean has = previous.size() > 0;
    return has;
  }

  public int indexOfPrevious(Movement aPrevious)
  {
    int index = previous.indexOf(aPrevious);
    return index;
  }
  /* Code from template association_GetOne */
  public Position getWholeView()
  {
    return WholeView;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfNext()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addNext(Movement aNext)
  {
    boolean wasAdded = false;
    if (next.contains(aNext)) { return false; }
    next.add(aNext);
    if (aNext.indexOfPrevious(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aNext.addPrevious(this);
      if (!wasAdded)
      {
        next.remove(aNext);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeNext(Movement aNext)
  {
    boolean wasRemoved = false;
    if (!next.contains(aNext))
    {
      return wasRemoved;
    }

    int oldIndex = next.indexOf(aNext);
    next.remove(oldIndex);
    if (aNext.indexOfPrevious(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aNext.removePrevious(this);
      if (!wasRemoved)
      {
        next.add(oldIndex,aNext);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addNextAt(Movement aNext, int index)
  {  
    boolean wasAdded = false;
    if(addNext(aNext))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfNext()) { index = numberOfNext() - 1; }
      next.remove(aNext);
      next.add(index, aNext);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveNextAt(Movement aNext, int index)
  {
    boolean wasAdded = false;
    if(next.contains(aNext))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfNext()) { index = numberOfNext() - 1; }
      next.remove(aNext);
      next.add(index, aNext);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addNextAt(aNext, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPrevious()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addPrevious(Movement aPrevious)
  {
    boolean wasAdded = false;
    if (previous.contains(aPrevious)) { return false; }
    previous.add(aPrevious);
    if (aPrevious.indexOfNext(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPrevious.addNext(this);
      if (!wasAdded)
      {
        previous.remove(aPrevious);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removePrevious(Movement aPrevious)
  {
    boolean wasRemoved = false;
    if (!previous.contains(aPrevious))
    {
      return wasRemoved;
    }

    int oldIndex = previous.indexOf(aPrevious);
    previous.remove(oldIndex);
    if (aPrevious.indexOfNext(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPrevious.removeNext(this);
      if (!wasRemoved)
      {
        previous.add(oldIndex,aPrevious);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPreviousAt(Movement aPrevious, int index)
  {  
    boolean wasAdded = false;
    if(addPrevious(aPrevious))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPrevious()) { index = numberOfPrevious() - 1; }
      previous.remove(aPrevious);
      previous.add(index, aPrevious);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePreviousAt(Movement aPrevious, int index)
  {
    boolean wasAdded = false;
    if(previous.contains(aPrevious))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPrevious()) { index = numberOfPrevious() - 1; }
      previous.remove(aPrevious);
      previous.add(index, aPrevious);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPreviousAt(aPrevious, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Movement> copyOfNext = new ArrayList<Movement>(next);
    next.clear();
    for(Movement aNext : copyOfNext)
    {
      aNext.removePrevious(this);
    }
    ArrayList<Movement> copyOfPrevious = new ArrayList<Movement>(previous);
    previous.clear();
    for(Movement aPrevious : copyOfPrevious)
    {
      aPrevious.removeNext(this);
    }
    Position existingWholeView = WholeView;
    WholeView = null;
    if (existingWholeView != null)
    {
      existingWholeView.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "MoveType" + "=" + (getMoveType() != null ? !getMoveType().equals(this)  ? getMoveType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "WholeView = "+(getWholeView()!=null?Integer.toHexString(System.identityHashCode(getWholeView())):"null");
  }
}