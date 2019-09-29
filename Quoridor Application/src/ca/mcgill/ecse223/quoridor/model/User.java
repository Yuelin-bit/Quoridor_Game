/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.quoridor.model;

// line 34 "../domain_model_3.0.ump"
// line 93 "../domain_model_3.0.ump"
public class User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String UserName;
  private String UserID;

  //User Associations
  private QuoridorSystem system;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aUserName, String aUserID)
  {
    UserName = aUserName;
    UserID = aUserID;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setUserName(String aUserName)
  {
    boolean wasSet = false;
    UserName = aUserName;
    wasSet = true;
    return wasSet;
  }

  public boolean setUserID(String aUserID)
  {
    boolean wasSet = false;
    UserID = aUserID;
    wasSet = true;
    return wasSet;
  }

  public String getUserName()
  {
    return UserName;
  }

  public String getUserID()
  {
    return UserID;
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
  /* Code from template association_SetOptionalOneToMany */
  public boolean setSystem(QuoridorSystem aSystem)
  {
    boolean wasSet = false;
    QuoridorSystem existingSystem = system;
    system = aSystem;
    if (existingSystem != null && !existingSystem.equals(aSystem))
    {
      existingSystem.removeAllUsersLoginedIn(this);
    }
    if (aSystem != null)
    {
      aSystem.addAllUsersLoginedIn(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    if (system != null)
    {
      QuoridorSystem placeholderSystem = system;
      this.system = null;
      placeholderSystem.removeAllUsersLoginedIn(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "UserName" + ":" + getUserName()+ "," +
            "UserID" + ":" + getUserID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "system = "+(getSystem()!=null?Integer.toHexString(System.identityHashCode(getSystem())):"null");
  }
}