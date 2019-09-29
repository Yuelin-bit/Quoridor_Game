/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.quoridor.model;

// line 73 "../domain_model_3.0.ump"
// line 133 "../domain_model_3.0.ump"
public class Board
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Board Attributes
  private int size;

  //Board Associations
  private QuoridorSystem system;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Board(int aSize)
  {
    size = aSize;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSize(int aSize)
  {
    boolean wasSet = false;
    size = aSize;
    wasSet = true;
    return wasSet;
  }

  public int getSize()
  {
    return size;
  }
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
  /* Code from template association_SetOptionalOneToOne */
  public boolean setSystem(QuoridorSystem aNewSystem)
  {
    boolean wasSet = false;
    if (system != null && !system.equals(aNewSystem) && equals(system.getBoard()))
    {
      //Unable to setSystem, as existing system would become an orphan
      return wasSet;
    }

    system = aNewSystem;
    Board anOldBoard = aNewSystem != null ? aNewSystem.getBoard() : null;

    if (!this.equals(anOldBoard))
    {
      if (anOldBoard != null)
      {
        anOldBoard.system = null;
      }
      if (system != null)
      {
        system.setBoard(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    QuoridorSystem existingSystem = system;
    system = null;
    if (existingSystem != null)
    {
      existingSystem.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "size" + ":" + getSize()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "system = "+(getSystem()!=null?Integer.toHexString(System.identityHashCode(getSystem())):"null");
  }
}