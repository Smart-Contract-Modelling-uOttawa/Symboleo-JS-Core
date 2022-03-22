/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

/**
 * Symboleo ontology and state machines
 */
// line 4 "model.ump"
// line 158 "model.ump"
public class Contract
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Contract State Machines
  public enum Status { Form, Active, SuccessfulTermination, UnsuccessfulTermination }
  public enum StatusActive { Null, InEffect, Suspension, Unassign, Rescission }
  private Status status;
  private StatusActive statusActive;

  //Contract Associations
  private List<LegalPosition> legalPositions;
  private List<Role> roles;
  private List<Party> parties;
  private List<Asset> assets;
  private List<Contract> subContracts;
  private List<Power> terminators;
  private Contract parentContract;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Contract()
  {
    legalPositions = new ArrayList<LegalPosition>();
    roles = new ArrayList<Role>();
    parties = new ArrayList<Party>();
    assets = new ArrayList<Asset>();
    subContracts = new ArrayList<Contract>();
    terminators = new ArrayList<Power>();
    setStatusActive(StatusActive.Null);
    setStatus(Status.Form);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getStatusFullName()
  {
    String answer = status.toString();
    if (statusActive != StatusActive.Null) { answer += "." + statusActive.toString(); }
    return answer;
  }

  public Status getStatus()
  {
    return status;
  }

  public StatusActive getStatusActive()
  {
    return statusActive;
  }

  public boolean activated()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Form:
        setStatusActive(StatusActive.InEffect);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean terminated()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Active:
        exitStatus();
        setStatus(Status.UnsuccessfulTermination);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean rescinded()
  {
    boolean wasEventProcessed = false;
    
    StatusActive aStatusActive = statusActive;
    switch (aStatusActive)
    {
      case InEffect:
        exitStatusActive();
        setStatusActive(StatusActive.Rescission);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean revokedParty()
  {
    boolean wasEventProcessed = false;
    
    StatusActive aStatusActive = statusActive;
    switch (aStatusActive)
    {
      case InEffect:
        exitStatusActive();
        setStatusActive(StatusActive.Unassign);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean fulfilledActiveObligations()
  {
    boolean wasEventProcessed = false;
    
    StatusActive aStatusActive = statusActive;
    switch (aStatusActive)
    {
      case InEffect:
        exitStatus();
        setStatus(Status.SuccessfulTermination);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean suspended()
  {
    boolean wasEventProcessed = false;
    
    StatusActive aStatusActive = statusActive;
    switch (aStatusActive)
    {
      case InEffect:
        exitStatusActive();
        setStatusActive(StatusActive.Suspension);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean resumed()
  {
    boolean wasEventProcessed = false;
    
    StatusActive aStatusActive = statusActive;
    switch (aStatusActive)
    {
      case Suspension:
        exitStatusActive();
        setStatusActive(StatusActive.InEffect);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean assignedParty()
  {
    boolean wasEventProcessed = false;
    
    StatusActive aStatusActive = statusActive;
    switch (aStatusActive)
    {
      case Unassign:
        exitStatusActive();
        setStatusActive(StatusActive.InEffect);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void exitStatus()
  {
    switch(status)
    {
      case Active:
        exitStatusActive();
        break;
    }
  }

  private void setStatus(Status aStatus)
  {
    status = aStatus;

    // entry actions and do activities
    switch(status)
    {
      case Active:
        if (statusActive == StatusActive.Null) { setStatusActive(StatusActive.InEffect); }
        break;
      case SuccessfulTermination:
        delete();
        break;
      case UnsuccessfulTermination:
        delete();
        break;
    }
  }

  private void exitStatusActive()
  {
    switch(statusActive)
    {
      case InEffect:
        setStatusActive(StatusActive.Null);
        break;
      case Suspension:
        setStatusActive(StatusActive.Null);
        break;
      case Unassign:
        setStatusActive(StatusActive.Null);
        break;
      case Rescission:
        setStatusActive(StatusActive.Null);
        break;
    }
  }

  private void setStatusActive(StatusActive aStatusActive)
  {
    statusActive = aStatusActive;
    if (status != Status.Active && aStatusActive != StatusActive.Null) { setStatus(Status.Active); }

    // entry actions and do activities
    switch(statusActive)
    {
      case Rescission:
        delete();
        break;
    }
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
  /* Code from template association_GetMany */
  public Role getRole(int index)
  {
    Role aRole = roles.get(index);
    return aRole;
  }

  public List<Role> getRoles()
  {
    List<Role> newRoles = Collections.unmodifiableList(roles);
    return newRoles;
  }

  public int numberOfRoles()
  {
    int number = roles.size();
    return number;
  }

  public boolean hasRoles()
  {
    boolean has = roles.size() > 0;
    return has;
  }

  public int indexOfRole(Role aRole)
  {
    int index = roles.indexOf(aRole);
    return index;
  }
  /* Code from template association_GetMany */
  public Party getParty(int index)
  {
    Party aParty = parties.get(index);
    return aParty;
  }

  public List<Party> getParties()
  {
    List<Party> newParties = Collections.unmodifiableList(parties);
    return newParties;
  }

  public int numberOfParties()
  {
    int number = parties.size();
    return number;
  }

  public boolean hasParties()
  {
    boolean has = parties.size() > 0;
    return has;
  }

  public int indexOfParty(Party aParty)
  {
    int index = parties.indexOf(aParty);
    return index;
  }
  /* Code from template association_GetMany */
  public Asset getAsset(int index)
  {
    Asset aAsset = assets.get(index);
    return aAsset;
  }

  public List<Asset> getAssets()
  {
    List<Asset> newAssets = Collections.unmodifiableList(assets);
    return newAssets;
  }

  public int numberOfAssets()
  {
    int number = assets.size();
    return number;
  }

  public boolean hasAssets()
  {
    boolean has = assets.size() > 0;
    return has;
  }

  public int indexOfAsset(Asset aAsset)
  {
    int index = assets.indexOf(aAsset);
    return index;
  }
  /* Code from template association_GetMany */
  public Contract getSubContract(int index)
  {
    Contract aSubContract = subContracts.get(index);
    return aSubContract;
  }

  public List<Contract> getSubContracts()
  {
    List<Contract> newSubContracts = Collections.unmodifiableList(subContracts);
    return newSubContracts;
  }

  public int numberOfSubContracts()
  {
    int number = subContracts.size();
    return number;
  }

  public boolean hasSubContracts()
  {
    boolean has = subContracts.size() > 0;
    return has;
  }

  public int indexOfSubContract(Contract aSubContract)
  {
    int index = subContracts.indexOf(aSubContract);
    return index;
  }
  /* Code from template association_GetMany */
  public Power getTerminator(int index)
  {
    Power aTerminator = terminators.get(index);
    return aTerminator;
  }

  public List<Power> getTerminators()
  {
    List<Power> newTerminators = Collections.unmodifiableList(terminators);
    return newTerminators;
  }

  public int numberOfTerminators()
  {
    int number = terminators.size();
    return number;
  }

  public boolean hasTerminators()
  {
    boolean has = terminators.size() > 0;
    return has;
  }

  public int indexOfTerminator(Power aTerminator)
  {
    int index = terminators.indexOf(aTerminator);
    return index;
  }
  /* Code from template association_GetOne */
  public Contract getParentContract()
  {
    return parentContract;
  }

  public boolean hasParentContract()
  {
    boolean has = parentContract != null;
    return has;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfLegalPositionsValid()
  {
    boolean isValid = numberOfLegalPositions() >= minimumNumberOfLegalPositions();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLegalPositions()
  {
    return 2;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public LegalPosition addLegalPosition(LegalSituation aAntecedent, LegalSituation aConsequent, Role aDebtor, Role aCreditor)
  {
    LegalPosition aNewLegalPosition = new LegalPosition(aAntecedent, aConsequent, this, aDebtor, aCreditor);
    return aNewLegalPosition;
  }

  public boolean addLegalPosition(LegalPosition aLegalPosition)
  {
    boolean wasAdded = false;
    if (legalPositions.contains(aLegalPosition)) { return false; }
    Contract existingContract = aLegalPosition.getContract();
    boolean isNewContract = existingContract != null && !this.equals(existingContract);

    if (isNewContract && existingContract.numberOfLegalPositions() <= minimumNumberOfLegalPositions())
    {
      return wasAdded;
    }
    if (isNewContract)
    {
      aLegalPosition.setContract(this);
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
    //Unable to remove aLegalPosition, as it must always have a contract
    if (this.equals(aLegalPosition.getContract()))
    {
      return wasRemoved;
    }

    //contract already at minimum (2)
    if (numberOfLegalPositions() <= minimumNumberOfLegalPositions())
    {
      return wasRemoved;
    }

    legalPositions.remove(aLegalPosition);
    wasRemoved = true;
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
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfRolesValid()
  {
    boolean isValid = numberOfRoles() >= minimumNumberOfRoles();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRoles()
  {
    return 2;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public Role addRole()
  {
    Role aNewRole = new Role(this);
    return aNewRole;
  }

  public boolean addRole(Role aRole)
  {
    boolean wasAdded = false;
    if (roles.contains(aRole)) { return false; }
    Contract existingContract = aRole.getContract();
    boolean isNewContract = existingContract != null && !this.equals(existingContract);

    if (isNewContract && existingContract.numberOfRoles() <= minimumNumberOfRoles())
    {
      return wasAdded;
    }
    if (isNewContract)
    {
      aRole.setContract(this);
    }
    else
    {
      roles.add(aRole);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRole(Role aRole)
  {
    boolean wasRemoved = false;
    //Unable to remove aRole, as it must always have a contract
    if (this.equals(aRole.getContract()))
    {
      return wasRemoved;
    }

    //contract already at minimum (2)
    if (numberOfRoles() <= minimumNumberOfRoles())
    {
      return wasRemoved;
    }

    roles.remove(aRole);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addRoleAt(Role aRole, int index)
  {  
    boolean wasAdded = false;
    if(addRole(aRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRoles()) { index = numberOfRoles() - 1; }
      roles.remove(aRole);
      roles.add(index, aRole);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRoleAt(Role aRole, int index)
  {
    boolean wasAdded = false;
    if(roles.contains(aRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRoles()) { index = numberOfRoles() - 1; }
      roles.remove(aRole);
      roles.add(index, aRole);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRoleAt(aRole, index);
    }
    return wasAdded;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfPartiesValid()
  {
    boolean isValid = numberOfParties() >= minimumNumberOfParties();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfParties()
  {
    return 2;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addParty(Party aParty)
  {
    boolean wasAdded = false;
    if (parties.contains(aParty)) { return false; }
    parties.add(aParty);
    if (aParty.indexOfContract(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aParty.addContract(this);
      if (!wasAdded)
      {
        parties.remove(aParty);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeParty(Party aParty)
  {
    boolean wasRemoved = false;
    if (!parties.contains(aParty))
    {
      return wasRemoved;
    }

    if (numberOfParties() <= minimumNumberOfParties())
    {
      return wasRemoved;
    }

    int oldIndex = parties.indexOf(aParty);
    parties.remove(oldIndex);
    if (aParty.indexOfContract(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aParty.removeContract(this);
      if (!wasRemoved)
      {
        parties.add(oldIndex,aParty);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setParties(Party... newParties)
  {
    boolean wasSet = false;
    ArrayList<Party> verifiedParties = new ArrayList<Party>();
    for (Party aParty : newParties)
    {
      if (verifiedParties.contains(aParty))
      {
        continue;
      }
      verifiedParties.add(aParty);
    }

    if (verifiedParties.size() != newParties.length || verifiedParties.size() < minimumNumberOfParties())
    {
      return wasSet;
    }

    ArrayList<Party> oldParties = new ArrayList<Party>(parties);
    parties.clear();
    for (Party aNewParty : verifiedParties)
    {
      parties.add(aNewParty);
      if (oldParties.contains(aNewParty))
      {
        oldParties.remove(aNewParty);
      }
      else
      {
        aNewParty.addContract(this);
      }
    }

    for (Party anOldParty : oldParties)
    {
      anOldParty.removeContract(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPartyAt(Party aParty, int index)
  {  
    boolean wasAdded = false;
    if(addParty(aParty))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfParties()) { index = numberOfParties() - 1; }
      parties.remove(aParty);
      parties.add(index, aParty);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePartyAt(Party aParty, int index)
  {
    boolean wasAdded = false;
    if(parties.contains(aParty))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfParties()) { index = numberOfParties() - 1; }
      parties.remove(aParty);
      parties.add(index, aParty);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPartyAt(aParty, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssets()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Asset addAsset()
  {
    return new Asset(this);
  }

  public boolean addAsset(Asset aAsset)
  {
    boolean wasAdded = false;
    if (assets.contains(aAsset)) { return false; }
    Contract existingContract = aAsset.getContract();
    boolean isNewContract = existingContract != null && !this.equals(existingContract);
    if (isNewContract)
    {
      aAsset.setContract(this);
    }
    else
    {
      assets.add(aAsset);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAsset(Asset aAsset)
  {
    boolean wasRemoved = false;
    //Unable to remove aAsset, as it must always have a contract
    if (!this.equals(aAsset.getContract()))
    {
      assets.remove(aAsset);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAssetAt(Asset aAsset, int index)
  {  
    boolean wasAdded = false;
    if(addAsset(aAsset))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssets()) { index = numberOfAssets() - 1; }
      assets.remove(aAsset);
      assets.add(index, aAsset);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAssetAt(Asset aAsset, int index)
  {
    boolean wasAdded = false;
    if(assets.contains(aAsset))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssets()) { index = numberOfAssets() - 1; }
      assets.remove(aAsset);
      assets.add(index, aAsset);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAssetAt(aAsset, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSubContracts()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addSubContract(Contract aSubContract)
  {
    boolean wasAdded = false;
    if (subContracts.contains(aSubContract)) { return false; }
    Contract existingParentContract = aSubContract.getParentContract();
    if (existingParentContract == null)
    {
      aSubContract.setParentContract(this);
    }
    else if (!this.equals(existingParentContract))
    {
      existingParentContract.removeSubContract(aSubContract);
      addSubContract(aSubContract);
    }
    else
    {
      subContracts.add(aSubContract);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSubContract(Contract aSubContract)
  {
    boolean wasRemoved = false;
    if (subContracts.contains(aSubContract))
    {
      subContracts.remove(aSubContract);
      aSubContract.setParentContract(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSubContractAt(Contract aSubContract, int index)
  {  
    boolean wasAdded = false;
    if(addSubContract(aSubContract))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSubContracts()) { index = numberOfSubContracts() - 1; }
      subContracts.remove(aSubContract);
      subContracts.add(index, aSubContract);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSubContractAt(Contract aSubContract, int index)
  {
    boolean wasAdded = false;
    if(subContracts.contains(aSubContract))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSubContracts()) { index = numberOfSubContracts() - 1; }
      subContracts.remove(aSubContract);
      subContracts.add(index, aSubContract);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSubContractAt(aSubContract, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTerminators()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addTerminator(Power aTerminator)
  {
    boolean wasAdded = false;
    if (terminators.contains(aTerminator)) { return false; }
    Contract existingTerminated = aTerminator.getTerminated();
    if (existingTerminated == null)
    {
      aTerminator.setTerminated(this);
    }
    else if (!this.equals(existingTerminated))
    {
      existingTerminated.removeTerminator(aTerminator);
      addTerminator(aTerminator);
    }
    else
    {
      terminators.add(aTerminator);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTerminator(Power aTerminator)
  {
    boolean wasRemoved = false;
    if (terminators.contains(aTerminator))
    {
      terminators.remove(aTerminator);
      aTerminator.setTerminated(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTerminatorAt(Power aTerminator, int index)
  {  
    boolean wasAdded = false;
    if(addTerminator(aTerminator))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTerminators()) { index = numberOfTerminators() - 1; }
      terminators.remove(aTerminator);
      terminators.add(index, aTerminator);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTerminatorAt(Power aTerminator, int index)
  {
    boolean wasAdded = false;
    if(terminators.contains(aTerminator))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTerminators()) { index = numberOfTerminators() - 1; }
      terminators.remove(aTerminator);
      terminators.add(index, aTerminator);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTerminatorAt(aTerminator, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setParentContract(Contract aParentContract)
  {
    boolean wasSet = false;
    Contract existingParentContract = parentContract;
    parentContract = aParentContract;
    if (existingParentContract != null && !existingParentContract.equals(aParentContract))
    {
      existingParentContract.removeSubContract(this);
    }
    if (aParentContract != null)
    {
      aParentContract.addSubContract(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (legalPositions.size() > 0)
    {
      LegalPosition aLegalPosition = legalPositions.get(legalPositions.size() - 1);
      aLegalPosition.delete();
      legalPositions.remove(aLegalPosition);
    }
    
    while (roles.size() > 0)
    {
      Role aRole = roles.get(roles.size() - 1);
      aRole.delete();
      roles.remove(aRole);
    }
    
    ArrayList<Party> copyOfParties = new ArrayList<Party>(parties);
    parties.clear();
    for(Party aParty : copyOfParties)
    {
      if (aParty.numberOfContracts() <= Party.minimumNumberOfContracts())
      {
        aParty.delete();
      }
      else
      {
        aParty.removeContract(this);
      }
    }
    for(int i=assets.size(); i > 0; i--)
    {
      Asset aAsset = assets.get(i - 1);
      aAsset.delete();
    }
    while( !subContracts.isEmpty() )
    {
      subContracts.get(0).setParentContract(null);
    }
    while( !terminators.isEmpty() )
    {
      terminators.get(0).setTerminated(null);
    }
    if (parentContract != null)
    {
      Contract placeholderParentContract = parentContract;
      this.parentContract = null;
      placeholderParentContract.removeSubContract(this);
    }
  }

}