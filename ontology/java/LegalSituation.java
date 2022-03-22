/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 81 "model.ump"
// line 217 "model.ump"
public class LegalSituation extends Situation
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //LegalSituation Associations
  private List<LegalPosition> antecedentOf;
  private List<LegalPosition> consequentOf;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public LegalSituation(TimeInterval aTime)
  {
    super(aTime);
    antecedentOf = new ArrayList<LegalPosition>();
    consequentOf = new ArrayList<LegalPosition>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public LegalPosition getAntecedentOf(int index)
  {
    LegalPosition aAntecedentOf = antecedentOf.get(index);
    return aAntecedentOf;
  }

  public List<LegalPosition> getAntecedentOf()
  {
    List<LegalPosition> newAntecedentOf = Collections.unmodifiableList(antecedentOf);
    return newAntecedentOf;
  }

  public int numberOfAntecedentOf()
  {
    int number = antecedentOf.size();
    return number;
  }

  public boolean hasAntecedentOf()
  {
    boolean has = antecedentOf.size() > 0;
    return has;
  }

  public int indexOfAntecedentOf(LegalPosition aAntecedentOf)
  {
    int index = antecedentOf.indexOf(aAntecedentOf);
    return index;
  }
  /* Code from template association_GetMany */
  public LegalPosition getConsequentOf(int index)
  {
    LegalPosition aConsequentOf = consequentOf.get(index);
    return aConsequentOf;
  }

  public List<LegalPosition> getConsequentOf()
  {
    List<LegalPosition> newConsequentOf = Collections.unmodifiableList(consequentOf);
    return newConsequentOf;
  }

  public int numberOfConsequentOf()
  {
    int number = consequentOf.size();
    return number;
  }

  public boolean hasConsequentOf()
  {
    boolean has = consequentOf.size() > 0;
    return has;
  }

  public int indexOfConsequentOf(LegalPosition aConsequentOf)
  {
    int index = consequentOf.indexOf(aConsequentOf);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAntecedentOf()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public LegalPosition addAntecedentOf(LegalSituation aConsequent, Contract aContract, Role aDebtor, Role aCreditor)
  {
    return new LegalPosition(this, aConsequent, aContract, aDebtor, aCreditor);
  }

  public boolean addAntecedentOf(LegalPosition aAntecedentOf)
  {
    boolean wasAdded = false;
    if (antecedentOf.contains(aAntecedentOf)) { return false; }
    LegalSituation existingAntecedent = aAntecedentOf.getAntecedent();
    boolean isNewAntecedent = existingAntecedent != null && !this.equals(existingAntecedent);
    if (isNewAntecedent)
    {
      aAntecedentOf.setAntecedent(this);
    }
    else
    {
      antecedentOf.add(aAntecedentOf);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAntecedentOf(LegalPosition aAntecedentOf)
  {
    boolean wasRemoved = false;
    //Unable to remove aAntecedentOf, as it must always have a antecedent
    if (!this.equals(aAntecedentOf.getAntecedent()))
    {
      antecedentOf.remove(aAntecedentOf);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAntecedentOfAt(LegalPosition aAntecedentOf, int index)
  {  
    boolean wasAdded = false;
    if(addAntecedentOf(aAntecedentOf))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAntecedentOf()) { index = numberOfAntecedentOf() - 1; }
      antecedentOf.remove(aAntecedentOf);
      antecedentOf.add(index, aAntecedentOf);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAntecedentOfAt(LegalPosition aAntecedentOf, int index)
  {
    boolean wasAdded = false;
    if(antecedentOf.contains(aAntecedentOf))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAntecedentOf()) { index = numberOfAntecedentOf() - 1; }
      antecedentOf.remove(aAntecedentOf);
      antecedentOf.add(index, aAntecedentOf);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAntecedentOfAt(aAntecedentOf, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfConsequentOf()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public LegalPosition addConsequentOf(LegalSituation aAntecedent, Contract aContract, Role aDebtor, Role aCreditor)
  {
    return new LegalPosition(aAntecedent, this, aContract, aDebtor, aCreditor);
  }

  public boolean addConsequentOf(LegalPosition aConsequentOf)
  {
    boolean wasAdded = false;
    if (consequentOf.contains(aConsequentOf)) { return false; }
    LegalSituation existingConsequent = aConsequentOf.getConsequent();
    boolean isNewConsequent = existingConsequent != null && !this.equals(existingConsequent);
    if (isNewConsequent)
    {
      aConsequentOf.setConsequent(this);
    }
    else
    {
      consequentOf.add(aConsequentOf);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeConsequentOf(LegalPosition aConsequentOf)
  {
    boolean wasRemoved = false;
    //Unable to remove aConsequentOf, as it must always have a consequent
    if (!this.equals(aConsequentOf.getConsequent()))
    {
      consequentOf.remove(aConsequentOf);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addConsequentOfAt(LegalPosition aConsequentOf, int index)
  {  
    boolean wasAdded = false;
    if(addConsequentOf(aConsequentOf))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfConsequentOf()) { index = numberOfConsequentOf() - 1; }
      consequentOf.remove(aConsequentOf);
      consequentOf.add(index, aConsequentOf);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveConsequentOfAt(LegalPosition aConsequentOf, int index)
  {
    boolean wasAdded = false;
    if(consequentOf.contains(aConsequentOf))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfConsequentOf()) { index = numberOfConsequentOf() - 1; }
      consequentOf.remove(aConsequentOf);
      consequentOf.add(index, aConsequentOf);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addConsequentOfAt(aConsequentOf, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=antecedentOf.size(); i > 0; i--)
    {
      LegalPosition aAntecedentOf = antecedentOf.get(i - 1);
      aAntecedentOf.delete();
    }
    for(int i=consequentOf.size(); i > 0; i--)
    {
      LegalPosition aConsequentOf = consequentOf.get(i - 1);
      aConsequentOf.delete();
    }
    super.delete();
  }

}