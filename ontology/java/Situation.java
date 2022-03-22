/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 61 "model.ump"
// line 197 "model.ump"
public class Situation
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Situation Associations
  private List<Event> preEvents;
  private List<Event> postEvents;
  private TimeInterval time;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Situation(TimeInterval aTime)
  {
    preEvents = new ArrayList<Event>();
    postEvents = new ArrayList<Event>();
    if (!setTime(aTime))
    {
      throw new RuntimeException("Unable to create Situation due to aTime. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Event getPreEvent(int index)
  {
    Event aPreEvent = preEvents.get(index);
    return aPreEvent;
  }

  public List<Event> getPreEvents()
  {
    List<Event> newPreEvents = Collections.unmodifiableList(preEvents);
    return newPreEvents;
  }

  public int numberOfPreEvents()
  {
    int number = preEvents.size();
    return number;
  }

  public boolean hasPreEvents()
  {
    boolean has = preEvents.size() > 0;
    return has;
  }

  public int indexOfPreEvent(Event aPreEvent)
  {
    int index = preEvents.indexOf(aPreEvent);
    return index;
  }
  /* Code from template association_GetMany */
  public Event getPostEvent(int index)
  {
    Event aPostEvent = postEvents.get(index);
    return aPostEvent;
  }

  public List<Event> getPostEvents()
  {
    List<Event> newPostEvents = Collections.unmodifiableList(postEvents);
    return newPostEvents;
  }

  public int numberOfPostEvents()
  {
    int number = postEvents.size();
    return number;
  }

  public boolean hasPostEvents()
  {
    boolean has = postEvents.size() > 0;
    return has;
  }

  public int indexOfPostEvent(Event aPostEvent)
  {
    int index = postEvents.indexOf(aPostEvent);
    return index;
  }
  /* Code from template association_GetOne */
  public TimeInterval getTime()
  {
    return time;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPreEvents()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addPreEvent(Event aPreEvent)
  {
    boolean wasAdded = false;
    if (preEvents.contains(aPreEvent)) { return false; }
    Situation existingPostState = aPreEvent.getPostState();
    if (existingPostState == null)
    {
      aPreEvent.setPostState(this);
    }
    else if (!this.equals(existingPostState))
    {
      existingPostState.removePreEvent(aPreEvent);
      addPreEvent(aPreEvent);
    }
    else
    {
      preEvents.add(aPreEvent);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePreEvent(Event aPreEvent)
  {
    boolean wasRemoved = false;
    if (preEvents.contains(aPreEvent))
    {
      preEvents.remove(aPreEvent);
      aPreEvent.setPostState(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPreEventAt(Event aPreEvent, int index)
  {  
    boolean wasAdded = false;
    if(addPreEvent(aPreEvent))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPreEvents()) { index = numberOfPreEvents() - 1; }
      preEvents.remove(aPreEvent);
      preEvents.add(index, aPreEvent);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePreEventAt(Event aPreEvent, int index)
  {
    boolean wasAdded = false;
    if(preEvents.contains(aPreEvent))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPreEvents()) { index = numberOfPreEvents() - 1; }
      preEvents.remove(aPreEvent);
      preEvents.add(index, aPreEvent);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPreEventAt(aPreEvent, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPostEvents()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addPostEvent(Event aPostEvent)
  {
    boolean wasAdded = false;
    if (postEvents.contains(aPostEvent)) { return false; }
    Situation existingPreState = aPostEvent.getPreState();
    if (existingPreState == null)
    {
      aPostEvent.setPreState(this);
    }
    else if (!this.equals(existingPreState))
    {
      existingPreState.removePostEvent(aPostEvent);
      addPostEvent(aPostEvent);
    }
    else
    {
      postEvents.add(aPostEvent);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePostEvent(Event aPostEvent)
  {
    boolean wasRemoved = false;
    if (postEvents.contains(aPostEvent))
    {
      postEvents.remove(aPostEvent);
      aPostEvent.setPreState(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPostEventAt(Event aPostEvent, int index)
  {  
    boolean wasAdded = false;
    if(addPostEvent(aPostEvent))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPostEvents()) { index = numberOfPostEvents() - 1; }
      postEvents.remove(aPostEvent);
      postEvents.add(index, aPostEvent);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePostEventAt(Event aPostEvent, int index)
  {
    boolean wasAdded = false;
    if(postEvents.contains(aPostEvent))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPostEvents()) { index = numberOfPostEvents() - 1; }
      postEvents.remove(aPostEvent);
      postEvents.add(index, aPostEvent);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPostEventAt(aPostEvent, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setTime(TimeInterval aNewTime)
  {
    boolean wasSet = false;
    if (aNewTime != null)
    {
      time = aNewTime;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    while( !preEvents.isEmpty() )
    {
      preEvents.get(0).setPostState(null);
    }
    while( !postEvents.isEmpty() )
    {
      postEvents.get(0).setPreState(null);
    }
    time = null;
  }

}