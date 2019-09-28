/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package -;
import java.util.*;

// line 11 "../domain_model_v1.3 SunGengyi.ump"
// line 64 "../domain_model_v1.3 SunGengyi.ump"
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
  private Position pst;
  private List<Player> p;
  private QuoridorSystem s;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public QuoridorGame(Player... allP)
  {
    p = new ArrayList<Player>();
    boolean didAddP = setP(allP);
    if (!didAddP)
    {
      throw new RuntimeException("Unable to create QuoridorGame, must have 2 p");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Position getPst()
  {
    return pst;
  }

  public boolean hasPst()
  {
    boolean has = pst != null;
    return has;
  }
  /* Code from template association_GetMany */
  public Player getP(int index)
  {
    Player aP = p.get(index);
    return aP;
  }

  public List<Player> getP()
  {
    List<Player> newP = Collections.unmodifiableList(p);
    return newP;
  }

  public int numberOfP()
  {
    int number = p.size();
    return number;
  }

  public boolean hasP()
  {
    boolean has = p.size() > 0;
    return has;
  }

  public int indexOfP(Player aP)
  {
    int index = p.indexOf(aP);
    return index;
  }
  /* Code from template association_GetOne */
  public QuoridorSystem getS()
  {
    return s;
  }

  public boolean hasS()
  {
    boolean has = s != null;
    return has;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setPst(Position aNewPst)
  {
    boolean wasSet = false;
    if (aNewPst == null)
    {
      Position existingPst = pst;
      pst = null;
      
      if (existingPst != null && existingPst.getG() != null)
      {
        existingPst.setG(null);
      }
      wasSet = true;
      return wasSet;
    }

    Position currentPst = getPst();
    if (currentPst != null && !currentPst.equals(aNewPst))
    {
      currentPst.setG(null);
    }

    pst = aNewPst;
    QuoridorGame existingG = aNewPst.getG();

    if (!equals(existingG))
    {
      aNewPst.setG(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_RequiredNumberOfMethod */
  public static int requiredNumberOfP()
  {
    return 2;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfP()
  {
    return 2;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfP()
  {
    return 2;
  }
  /* Code from template association_SetNToOptionalOne */
  public boolean setP(Player... newP)
  {
    boolean wasSet = false;
    ArrayList<Player> checkNewP = new ArrayList<Player>();
    for (Player aP : newP)
    {
      if (checkNewP.contains(aP))
      {
        return wasSet;
      }
      else if (aP.getG() != null && !this.equals(aP.getG()))
      {
        return wasSet;
      }
      checkNewP.add(aP);
    }

    if (checkNewP.size() != minimumNumberOfP())
    {
      return wasSet;
    }

    p.removeAll(checkNewP);
    
    for (Player orphan : p)
    {
      setG(orphan, null);
    }
    p.clear();
    for (Player aP : newP)
    {
      setG(aP, this);
      p.add(aP);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_GetPrivate */
  private void setG(Player aP, QuoridorGame aG)
  {
    try
    {
      java.lang.reflect.Field mentorField = aP.getClass().getDeclaredField("g");
      mentorField.setAccessible(true);
      mentorField.set(aP, aG);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Issue internally setting aG to aP", e);
    }
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setS(QuoridorSystem aS)
  {
    boolean wasSet = false;
    QuoridorSystem existingS = s;
    s = aS;
    if (existingS != null && !existingS.equals(aS))
    {
      existingS.removeG(this);
    }
    if (aS != null)
    {
      aS.addG(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Position existingPst = pst;
    pst = null;
    if (existingPst != null)
    {
      existingPst.delete();
      existingPst.setG(null);
    }
    while (p.size() > 0)
    {
      Player aP = p.get(p.size() - 1);
      aP.delete();
      p.remove(aP);
    }
    
    if (s != null)
    {
      QuoridorSystem placeholderS = s;
      this.s = null;
      placeholderS.removeG(this);
    }
  }

}