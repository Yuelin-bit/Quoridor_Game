/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package -;
import java.util.*;

// line 5 "../domain_model_v1.3 SunGengyi.ump"
// line 58 "../domain_model_v1.3 SunGengyi.ump"
public class QuoridorSystem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //QuoridorSystem Associations
  private List<User> u;
  private List<QuoridorGame> g;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public QuoridorSystem()
  {
    u = new ArrayList<User>();
    g = new ArrayList<QuoridorGame>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public User getU(int index)
  {
    User aU = u.get(index);
    return aU;
  }

  public List<User> getU()
  {
    List<User> newU = Collections.unmodifiableList(u);
    return newU;
  }

  public int numberOfU()
  {
    int number = u.size();
    return number;
  }

  public boolean hasU()
  {
    boolean has = u.size() > 0;
    return has;
  }

  public int indexOfU(User aU)
  {
    int index = u.indexOf(aU);
    return index;
  }
  /* Code from template association_GetMany */
  public QuoridorGame getG(int index)
  {
    QuoridorGame aG = g.get(index);
    return aG;
  }

  public List<QuoridorGame> getG()
  {
    List<QuoridorGame> newG = Collections.unmodifiableList(g);
    return newG;
  }

  public int numberOfG()
  {
    int number = g.size();
    return number;
  }

  public boolean hasG()
  {
    boolean has = g.size() > 0;
    return has;
  }

  public int indexOfG(QuoridorGame aG)
  {
    int index = g.indexOf(aG);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfU()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addU(User aU)
  {
    boolean wasAdded = false;
    if (u.contains(aU)) { return false; }
    QuoridorSystem existingS = aU.getS();
    if (existingS == null)
    {
      aU.setS(this);
    }
    else if (!this.equals(existingS))
    {
      existingS.removeU(aU);
      addU(aU);
    }
    else
    {
      u.add(aU);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeU(User aU)
  {
    boolean wasRemoved = false;
    if (u.contains(aU))
    {
      u.remove(aU);
      aU.setS(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUAt(User aU, int index)
  {  
    boolean wasAdded = false;
    if(addU(aU))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfU()) { index = numberOfU() - 1; }
      u.remove(aU);
      u.add(index, aU);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUAt(User aU, int index)
  {
    boolean wasAdded = false;
    if(u.contains(aU))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfU()) { index = numberOfU() - 1; }
      u.remove(aU);
      u.add(index, aU);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUAt(aU, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfG()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addG(QuoridorGame aG)
  {
    boolean wasAdded = false;
    if (g.contains(aG)) { return false; }
    QuoridorSystem existingS = aG.getS();
    if (existingS == null)
    {
      aG.setS(this);
    }
    else if (!this.equals(existingS))
    {
      existingS.removeG(aG);
      addG(aG);
    }
    else
    {
      g.add(aG);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeG(QuoridorGame aG)
  {
    boolean wasRemoved = false;
    if (g.contains(aG))
    {
      g.remove(aG);
      aG.setS(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGAt(QuoridorGame aG, int index)
  {  
    boolean wasAdded = false;
    if(addG(aG))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfG()) { index = numberOfG() - 1; }
      g.remove(aG);
      g.add(index, aG);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGAt(QuoridorGame aG, int index)
  {
    boolean wasAdded = false;
    if(g.contains(aG))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfG()) { index = numberOfG() - 1; }
      g.remove(aG);
      g.add(index, aG);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGAt(aG, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (u.size() > 0)
    {
      User aU = u.get(u.size() - 1);
      aU.delete();
      u.remove(aU);
    }
    
    while (g.size() > 0)
    {
      QuoridorGame aG = g.get(g.size() - 1);
      aG.delete();
      g.remove(aG);
    }
    
  }

}