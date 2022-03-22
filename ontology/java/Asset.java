/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 46 "model.ump"
// line 180 "model.ump"
public class Asset
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Asset Associations
  private List<Party> owners;
  private List<LegalPosition> legalPositions;
  private Contract contract;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Asset(Contract aContract)
  {
    owners = new ArrayList<Party>();
    legalPositions = new ArrayList<LegalPosition>();
    boolean didAddContract = setContract(aContract);
    if (!didAddContract)
    {
      throw new RuntimeException("Unable to create asset due to contract. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Party getOwner(int index)
  {
    Party aOwner = owners.get(index);
    return aOwner;
  }

  public List<Party> getOwners()
  {
    List<Party> newOwners = Collections.unmodifiableList(owners);
    return newOwners;
  }

  public int numberOfOwners()
  {
    int number = owners.size();
    return number;
  }

  public boolean hasOwners()
  {
    boolean has = owners.size() > 0;
    return has;
  }

  public int indexOfOwner(Party aOwner)
  {
    int index = owners.indexOf(aOwner);
    return index;
  }
  /* Code from template association_GetMany */
  public LegalPosition getLegalPosition(int index)
  {
    LegalPosition aLegalPosition = legalPositions.get(index);
    return aLegalPosition;
  }

  public List<LegalPosition> getLegalPositions()
  {
    List<LegalPosition> newLegalPositions = Collections.unmodifiableList(legalPositions);
    return newLegalPositions;
  }

  public int numberOfLegalPositions()
  {
    int number = legalPositions.size();
    return number;
  }

  public boolean hasLegalPositions()
  {
    boolean has = legalPositions.size() > 0;
    return has;
  }

  public int indexOfLegalPosition(LegalPosition aLegalPosition)
  {
    int index = legalPositions.indexOf(aLegalPosition);
    return index;
  }
  /* Code from template association_GetOne */
  public Contract getContract()
  {
    return contract;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOwners()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addOwner(Party aOwner)
  {
    boolean wasAdded = false;
    if (owners.contains(aOwner)) { return false; }
    owners.add(aOwner);
    if (aOwner.indexOfAsset(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aOwner.addAsset(this);
      if (!wasAdded)
      {
        owners.remove(aOwner);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeOwner(Party aOwner)
  {
    boolean wasRemoved = false;
    if (!owners.contains(aOwner))
    {
      return wasRemoved;
    }

    int oldIndex = owners.indexOf(aOwner);
    owners.remove(oldIndex);
    if (aOwner.indexOfAsset(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aOwner.removeAsset(this);
      if (!wasRemoved)
      {
        owners.add(oldIndex,aOwner);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOwnerAt(Party aOwner, int index)
  {  
    boolean wasAdded = false;
    if(addOwner(aOwner))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOwners()) { index = numberOfOwners() - 1; }
      owners.remove(aOwner);
      owners.add(index, aOwner);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOwnerAt(Party aOwner, int index)
  {
    boolean wasAdded = false;
    if(owners.contains(aOwner))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOwners()) { index = numberOfOwners() - 1; }
      owners.remove(aOwner);
      owners.add(index, aOwner);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOwnerAt(aOwner, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLegalPositions()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addLegalPosition(LegalPosition aLegalPosition)
  {
    boolean wasAdded = false;
    if (legalPositions.contains(aLegalPosition)) { return false; }
    Asset existingAsset = aLegalPosition.getAsset();
    if (existingAsset == null)
    {
      aLegalPosition.setAsset(this);
    }
    else if (!this.equals(existingAsset))
    {
      existingAsset.removeLegalPosition(aLegalPosition);
      addLegalPosition(aLegalPosition);
    }
    else
    {
      legalPositions.add(aLegalPosition);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLegalPosition(LegalPosition aLegalPosition)
  {
    boolean wasRemoved = false;
    if (legalPositions.contains(aLegalPosition))
    {
      legalPositions.remove(aLegalPosition);
      aLegalPosition.setAsset(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLegalPositionAt(LegalPosition aLegalPosition, int index)
  {  
    boolean wasAdded = false;
    if(addLegalPosition(aLegalPosition))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLegalPositions()) { index = numberOfLegalPositions() - 1; }
      legalPositions.remove(aLegalPosition);
      legalPositions.add(index, aLegalPosition);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLegalPositionAt(LegalPosition aLegalPosition, int index)
  {
    boolean wasAdded = false;
    if(legalPositions.contains(aLegalPosition))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLegalPositions()) { index = numberOfLegalPositions() - 1; }
      legalPositions.remove(aLegalPosition);
      legalPositions.add(index, aLegalPosition);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLegalPositionAt(aLegalPosition, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setContract(Contract aContract)
  {
    boolean wasSet = false;
    if (aContract == null)
    {
      return wasSet;
    }

    Contract existingContract = contract;
    contract = aContract;
    if (existingContract != null && !existingContract.equals(aContract))
    {
      existingContract.removeAsset(this);
    }
    contract.addAsset(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Party> copyOfOwners = new ArrayList<Party>(owners);
    owners.clear();
    for(Party aOwner : copyOfOwners)
    {
      aOwner.removeAsset(this);
    }
    while( !legalPositions.isEmpty() )
    {
      legalPositions.get(0).setAsset(null);
    }
    Contract placeholderContract = contract;
    this.contract = null;
    if(placeholderContract != null)
    {
      placeholderContract.removeAsset(this);
    }
  }

}