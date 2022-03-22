/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 68 "model.ump"
// line 202 "model.ump"
public class Event
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Event Associations
  private TimePoint time;
  private Situation postState;
  private Situation preState;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Event(TimePoint aTime)
  {
    if (!setTime(aTime))
    {
      throw new RuntimeException("Unable to create Event due to aTime. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public TimePoint getTime()
  {
    return time;
  }
  /* Code from template association_GetOne */
  public Situation getPostState()
  {
    return postState;
  }

  public boolean hasPostState()
  {
    boolean has = postState != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Situation getPreState()
  {
    return preState;
  }

  public boolean hasPreState()
  {
    boolean has = preState != null;
    return has;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setTime(TimePoint aNewTime)
  {
    boolean wasSet = false;
    if (aNewTime != null)
    {
      time = aNewTime;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setPostState(Situation aPostState)
  {
    boolean wasSet = false;
    Situation existingPostState = postState;
    postState = aPostState;
    if (existingPostState != null && !existingPostState.equals(aPostState))
    {
      existingPostState.removePreEvent(this);
    }
    if (aPostState != null)
    {
      aPostState.addPreEvent(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setPreState(Situation aPreState)
  {
    boolean wasSet = false;
    Situation existingPreState = preState;
    preState = aPreState;
    if (existingPreState != null && !existingPreState.equals(aPreState))
    {
      existingPreState.removePostEvent(this);
    }
    if (aPreState != null)
    {
      aPreState.addPostEvent(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    time = null;
    if (postState != null)
    {
      Situation placeholderPostState = postState;
      this.postState = null;
      placeholderPostState.removePreEvent(this);
    }
    if (preState != null)
    {
      Situation placeholderPreState = preState;
      this.preState = null;
      placeholderPreState.removePostEvent(this);
    }
  }

}