/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package -;
import java.util.*;

// line 26 "../domain_model_v1.3 SunGengyi.ump"
// line 77 "../domain_model_v1.3 SunGengyi.ump"
public class Position
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum WallDirection { Horizontal, Vertical }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Position Associations
  private List<Wall> w;
  private List<Pawn> pw;
  private QuoridorGame g;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Position()
  {
    w = new ArrayList<Wall>();
    pw = new ArrayList<Pawn>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Wall getW(int index)
  {
    Wall aW = w.get(index);
    return aW;
  }

  public List<Wall> getW()
  {
    List<Wall> newW = Collections.unmodifiableList(w);
    return newW;
  }

  public int numberOfW()
  {
    int number = w.size();
    return number;
  }

  public boolean hasW()
  {
    boolean has = w.size() > 0;
    return has;
  }

  public int indexOfW(Wall aW)
  {
    int index = w.indexOf(aW);
    return index;
  }
  /* Code from template association_GetMany */
  public Pawn getPw(int index)
  {
    Pawn aPw = pw.get(index);
    return aPw;
  }

  public List<Pawn> getPw()
  {
    List<Pawn> newPw = Collections.unmodifiableList(pw);
    return newPw;
  }

  public int numberOfPw()
  {
    int number = pw.size();
    return number;
  }

  public boolean hasPw()
  {
    boolean has = pw.size() > 0;
    return has;
  }

  public int indexOfPw(Pawn aPw)
  {
    int index = pw.indexOf(aPw);
    return index;
  }
  /* Code from template association_GetOne */
  public QuoridorGame getG()
  {
    return g;
  }

  public boolean hasG()
  {
    boolean has = g != null;
    return has;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfW()
  {
    return 0;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfW()
  {
    return 20;
  }
  /* Code from template association_AddOptionalNToOptionalOne */
  public boolean addW(Wall aW)
  {
    boolean wasAdded = false;
    if (w.contains(aW)) { return false; }
    if (numberOfW() >= maximumNumberOfW())
    {
      return wasAdded;
    }

    Position existingPst = aW.getPst();
    if (existingPst == null)
    {
      aW.setPst(this);
    }
    else if (!this.equals(existingPst))
    {
      existingPst.removeW(aW);
      addW(aW);
    }
    else
    {
      w.add(aW);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeW(Wall aW)
  {
    boolean wasRemoved = false;
    if (w.contains(aW))
    {
      w.remove(aW);
      aW.setPst(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addWAt(Wall aW, int index)
  {  
    boolean wasAdded = false;
    if(addW(aW))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfW()) { index = numberOfW() - 1; }
      w.remove(aW);
      w.add(index, aW);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveWAt(Wall aW, int index)
  {
    boolean wasAdded = false;
    if(w.contains(aW))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfW()) { index = numberOfW() - 1; }
      w.remove(aW);
      w.add(index, aW);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addWAt(aW, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPw()
  {
    return 0;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfPw()
  {
    return 2;
  }
  /* Code from template association_AddOptionalNToOptionalOne */
  public boolean addPw(Pawn aPw)
  {
    boolean wasAdded = false;
    if (pw.contains(aPw)) { return false; }
    if (numberOfPw() >= maximumNumberOfPw())
    {
      return wasAdded;
    }

    Position existingPst = aPw.getPst();
    if (existingPst == null)
    {
      aPw.setPst(this);
    }
    else if (!this.equals(existingPst))
    {
      existingPst.removePw(aPw);
      addPw(aPw);
    }
    else
    {
      pw.add(aPw);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePw(Pawn aPw)
  {
    boolean wasRemoved = false;
    if (pw.contains(aPw))
    {
      pw.remove(aPw);
      aPw.setPst(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPwAt(Pawn aPw, int index)
  {  
    boolean wasAdded = false;
    if(addPw(aPw))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPw()) { index = numberOfPw() - 1; }
      pw.remove(aPw);
      pw.add(index, aPw);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePwAt(Pawn aPw, int index)
  {
    boolean wasAdded = false;
    if(pw.contains(aPw))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPw()) { index = numberOfPw() - 1; }
      pw.remove(aPw);
      pw.add(index, aPw);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPwAt(aPw, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setG(QuoridorGame aNewG)
  {
    boolean wasSet = false;
    if (aNewG == null)
    {
      QuoridorGame existingG = g;
      g = null;
      
      if (existingG != null && existingG.getPst() != null)
      {
        existingG.setPst(null);
      }
      wasSet = true;
      return wasSet;
    }

    QuoridorGame currentG = getG();
    if (currentG != null && !currentG.equals(aNewG))
    {
      currentG.setPst(null);
    }

    g = aNewG;
    Position existingPst = aNewG.getPst();

    if (!equals(existingPst))
    {
      aNewG.setPst(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (w.size() > 0)
    {
      Wall aW = w.get(w.size() - 1);
      aW.delete();
      w.remove(aW);
    }
    
    while (pw.size() > 0)
    {
      Pawn aPw = pw.get(pw.size() - 1);
      aPw.delete();
      pw.remove(aPw);
    }
    
    if (g != null)
    {
      g.setPst(null);
    }
  }

}