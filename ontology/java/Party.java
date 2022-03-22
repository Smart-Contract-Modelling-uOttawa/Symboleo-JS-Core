/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 38 "model.ump"
// line 169 "model.ump"
public class Party
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Party Associations
  private List<Contract> contracts;
  private List<Role> roles;
  private List<Asset> assets;
  private List<LegalPosition> performerOf;
  private List<LegalPosition> liableOf;
  private List<LegalPosition> rightHolderOf;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Party(Role... allRoles)
  {
    contracts = new ArrayList<Contract>();
    roles = new ArrayList<Role>();
    boolean didAddRoles = setRoles(allRoles);
    if (!didAddRoles)
    {
      throw new RuntimeException("Unable to create Party, must have at least 1 roles. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    assets = new ArrayList<Asset>();
    performerOf = new ArrayList<LegalPosition>();
    liableOf = new ArrayList<LegalPosition>();
    rightHolderOf = new ArrayList<LegalPosition>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Contract getContract(int index)
  {
    Contract aContract = contracts.get(index);
    return aContract;
  }

  public List<Contract> getContracts()
  {
    List<Contract> newContracts = Collections.unmodifiableList(contracts);
    return newContracts;
  }

  public int numberOfContracts()
  {
    int number = contracts.size();
    return number;
  }

  public boolean hasContracts()
  {
    boolean has = contracts.size() > 0;
    return has;
  }

  public int indexOfContract(Contract aContract)
  {
    int index = contracts.indexOf(aContract);
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
  public LegalPosition getPerformerOf(int index)
  {
    LegalPosition aPerformerOf = performerOf.get(index);
    return aPerformerOf;
  }

  public List<LegalPosition> getPerformerOf()
  {
    List<LegalPosition> newPerformerOf = Collections.unmodifiableList(performerOf);
    return newPerformerOf;
  }

  public int numberOfPerformerOf()
  {
    int number = performerOf.size();
    return number;
  }

  public boolean hasPerformerOf()
  {
    boolean has = performerOf.size() > 0;
    return has;
  }

  public int indexOfPerformerOf(LegalPosition aPerformerOf)
  {
    int index = performerOf.indexOf(aPerformerOf);
    return index;
  }
  /* Code from template association_GetMany */
  public LegalPosition getLiableOf(int index)
  {
    LegalPosition aLiableOf = liableOf.get(index);
    return aLiableOf;
  }

  public List<LegalPosition> getLiableOf()
  {
    List<LegalPosition> newLiableOf = Collections.unmodifiableList(liableOf);
    return newLiableOf;
  }

  public int numberOfLiableOf()
  {
    int number = liableOf.size();
    return number;
  }

  public boolean hasLiableOf()
  {
    boolean has = liableOf.size() > 0;
    return has;
  }

  public int indexOfLiableOf(LegalPosition aLiableOf)
  {
    int index = liableOf.indexOf(aLiableOf);
    return index;
  }
  /* Code from template association_GetMany */
  public LegalPosition getRightHolderOf(int index)
  {
    LegalPosition aRightHolderOf = rightHolderOf.get(index);
    return aRightHolderOf;
  }

  public List<LegalPosition> getRightHolderOf()
  {
    List<LegalPosition> newRightHolderOf = Collections.unmodifiableList(rightHolderOf);
    return newRightHolderOf;
  }

  public int numberOfRightHolderOf()
  {
    int number = rightHolderOf.size();
    return number;
  }

  public boolean hasRightHolderOf()
  {
    boolean has = rightHolderOf.size() > 0;
    return has;
  }

  public int indexOfRightHolderOf(LegalPosition aRightHolderOf)
  {
    int index = rightHolderOf.indexOf(aRightHolderOf);
    return index;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfContractsValid()
  {
    boolean isValid = numberOfContracts() >= minimumNumberOfContracts();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfContracts()
  {
    return 1;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addContract(Contract aContract)
  {
    boolean wasAdded = false;
    if (contracts.contains(aContract)) { return false; }
    contracts.add(aContract);
    if (aContract.indexOfParty(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aContract.addParty(this);
      if (!wasAdded)
      {
        contracts.remove(aContract);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeContract(Contract aContract)
  {
    boolean wasRemoved = false;
    if (!contracts.contains(aContract))
    {
      return wasRemoved;
    }

    if (numberOfContracts() <= minimumNumberOfContracts())
    {
      return wasRemoved;
    }

    int oldIndex = contracts.indexOf(aContract);
    contracts.remove(oldIndex);
    if (aContract.indexOfParty(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aContract.removeParty(this);
      if (!wasRemoved)
      {
        contracts.add(oldIndex,aContract);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setContracts(Contract... newContracts)
  {
    boolean wasSet = false;
    ArrayList<Contract> verifiedContracts = new ArrayList<Contract>();
    for (Contract aContract : newContracts)
    {
      if (verifiedContracts.contains(aContract))
      {
        continue;
      }
      verifiedContracts.add(aContract);
    }

    if (verifiedContracts.size() != newContracts.length || verifiedContracts.size() < minimumNumberOfContracts())
    {
      return wasSet;
    }

    ArrayList<Contract> oldContracts = new ArrayList<Contract>(contracts);
    contracts.clear();
    for (Contract aNewContract : verifiedContracts)
    {
      contracts.add(aNewContract);
      if (oldContracts.contains(aNewContract))
      {
        oldContracts.remove(aNewContract);
      }
      else
      {
        aNewContract.addParty(this);
      }
    }

    for (Contract anOldContract : oldContracts)
    {
      anOldContract.removeParty(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addContractAt(Contract aContract, int index)
  {  
    boolean wasAdded = false;
    if(addContract(aContract))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfContracts()) { index = numberOfContracts() - 1; }
      contracts.remove(aContract);
      contracts.add(index, aContract);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveContractAt(Contract aContract, int index)
  {
    boolean wasAdded = false;
    if(contracts.contains(aContract))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfContracts()) { index = numberOfContracts() - 1; }
      contracts.remove(aContract);
      contracts.add(index, aContract);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addContractAt(aContract, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRoles()
  {
    return 1;
  }
  /* Code from template association_AddMNToOptionalOne */
  public boolean addRole(Role aRole)
  {
    boolean wasAdded = false;
    if (roles.contains(aRole)) { return false; }
    Party existingParty = aRole.getParty();
    if (existingParty != null && existingParty.numberOfRoles() <= minimumNumberOfRoles())
    {
      return wasAdded;
    }
    else if (existingParty != null)
    {
      existingParty.roles.remove(aRole);
    }
    roles.add(aRole);
    setParty(aRole,this);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRole(Role aRole)
  {
    boolean wasRemoved = false;
    if (roles.contains(aRole) && numberOfRoles() > minimumNumberOfRoles())
    {
      roles.remove(aRole);
      setParty(aRole,null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_SetMNToOptionalOne */
  public boolean setRoles(Role... newRoles)
  {
    boolean wasSet = false;
    if (newRoles.length < minimumNumberOfRoles())
    {
      return wasSet;
    }

    ArrayList<Role> checkNewRoles = new ArrayList<Role>();
    HashMap<Party,Integer> partyToNewRoles = new HashMap<Party,Integer>();
    for (Role aRole : newRoles)
    {
      if (checkNewRoles.contains(aRole))
      {
        return wasSet;
      }
      else if (aRole.getParty() != null && !this.equals(aRole.getParty()))
      {
        Party existingParty = aRole.getParty();
        if (!partyToNewRoles.containsKey(existingParty))
        {
          partyToNewRoles.put(existingParty, Integer.valueOf(existingParty.numberOfRoles()));
        }
        Integer currentCount = partyToNewRoles.get(existingParty);
        int nextCount = currentCount - 1;
        if (nextCount < 1)
        {
          return wasSet;
        }
        partyToNewRoles.put(existingParty, Integer.valueOf(nextCount));
      }
      checkNewRoles.add(aRole);
    }

    roles.removeAll(checkNewRoles);

    for (Role orphan : roles)
    {
      setParty(orphan, null);
    }
    roles.clear();
    for (Role aRole : newRoles)
    {
      if (aRole.getParty() != null)
      {
        aRole.getParty().roles.remove(aRole);
      }
      setParty(aRole, this);
      roles.add(aRole);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_GetPrivate */
  private void setParty(Role aRole, Party aParty)
  {
    try
    {
      java.lang.reflect.Field mentorField = aRole.getClass().getDeclaredField("party");
      mentorField.setAccessible(true);
      mentorField.set(aRole, aParty);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Issue internally setting aParty to aRole", e);
    }
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssets()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addAsset(Asset aAsset)
  {
    boolean wasAdded = false;
    if (assets.contains(aAsset)) { return false; }
    assets.add(aAsset);
    if (aAsset.indexOfOwner(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aAsset.addOwner(this);
      if (!wasAdded)
      {
        assets.remove(aAsset);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeAsset(Asset aAsset)
  {
    boolean wasRemoved = false;
    if (!assets.contains(aAsset))
    {
      return wasRemoved;
    }

    int oldIndex = assets.indexOf(aAsset);
    assets.remove(oldIndex);
    if (aAsset.indexOfOwner(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aAsset.removeOwner(this);
      if (!wasRemoved)
      {
        assets.add(oldIndex,aAsset);
      }
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
  public static int minimumNumberOfPerformerOf()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addPerformerOf(LegalPosition aPerformerOf)
  {
    boolean wasAdded = false;
    if (performerOf.contains(aPerformerOf)) { return false; }
    performerOf.add(aPerformerOf);
    if (aPerformerOf.indexOfPerformer(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPerformerOf.addPerformer(this);
      if (!wasAdded)
      {
        performerOf.remove(aPerformerOf);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removePerformerOf(LegalPosition aPerformerOf)
  {
    boolean wasRemoved = false;
    if (!performerOf.contains(aPerformerOf))
    {
      return wasRemoved;
    }

    int oldIndex = performerOf.indexOf(aPerformerOf);
    performerOf.remove(oldIndex);
    if (aPerformerOf.indexOfPerformer(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPerformerOf.removePerformer(this);
      if (!wasRemoved)
      {
        performerOf.add(oldIndex,aPerformerOf);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPerformerOfAt(LegalPosition aPerformerOf, int index)
  {  
    boolean wasAdded = false;
    if(addPerformerOf(aPerformerOf))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPerformerOf()) { index = numberOfPerformerOf() - 1; }
      performerOf.remove(aPerformerOf);
      performerOf.add(index, aPerformerOf);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePerformerOfAt(LegalPosition aPerformerOf, int index)
  {
    boolean wasAdded = false;
    if(performerOf.contains(aPerformerOf))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPerformerOf()) { index = numberOfPerformerOf() - 1; }
      performerOf.remove(aPerformerOf);
      performerOf.add(index, aPerformerOf);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPerformerOfAt(aPerformerOf, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLiableOf()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addLiableOf(LegalPosition aLiableOf)
  {
    boolean wasAdded = false;
    if (liableOf.contains(aLiableOf)) { return false; }
    liableOf.add(aLiableOf);
    if (aLiableOf.indexOfLiable(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aLiableOf.addLiable(this);
      if (!wasAdded)
      {
        liableOf.remove(aLiableOf);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeLiableOf(LegalPosition aLiableOf)
  {
    boolean wasRemoved = false;
    if (!liableOf.contains(aLiableOf))
    {
      return wasRemoved;
    }

    int oldIndex = liableOf.indexOf(aLiableOf);
    liableOf.remove(oldIndex);
    if (aLiableOf.indexOfLiable(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aLiableOf.removeLiable(this);
      if (!wasRemoved)
      {
        liableOf.add(oldIndex,aLiableOf);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLiableOfAt(LegalPosition aLiableOf, int index)
  {  
    boolean wasAdded = false;
    if(addLiableOf(aLiableOf))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLiableOf()) { index = numberOfLiableOf() - 1; }
      liableOf.remove(aLiableOf);
      liableOf.add(index, aLiableOf);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLiableOfAt(LegalPosition aLiableOf, int index)
  {
    boolean wasAdded = false;
    if(liableOf.contains(aLiableOf))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLiableOf()) { index = numberOfLiableOf() - 1; }
      liableOf.remove(aLiableOf);
      liableOf.add(index, aLiableOf);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLiableOfAt(aLiableOf, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRightHolderOf()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addRightHolderOf(LegalPosition aRightHolderOf)
  {
    boolean wasAdded = false;
    if (rightHolderOf.contains(aRightHolderOf)) { return false; }
    rightHolderOf.add(aRightHolderOf);
    if (aRightHolderOf.indexOfRightHolder(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aRightHolderOf.addRightHolder(this);
      if (!wasAdded)
      {
        rightHolderOf.remove(aRightHolderOf);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeRightHolderOf(LegalPosition aRightHolderOf)
  {
    boolean wasRemoved = false;
    if (!rightHolderOf.contains(aRightHolderOf))
    {
      return wasRemoved;
    }

    int oldIndex = rightHolderOf.indexOf(aRightHolderOf);
    rightHolderOf.remove(oldIndex);
    if (aRightHolderOf.indexOfRightHolder(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aRightHolderOf.removeRightHolder(this);
      if (!wasRemoved)
      {
        rightHolderOf.add(oldIndex,aRightHolderOf);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addRightHolderOfAt(LegalPosition aRightHolderOf, int index)
  {  
    boolean wasAdded = false;
    if(addRightHolderOf(aRightHolderOf))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRightHolderOf()) { index = numberOfRightHolderOf() - 1; }
      rightHolderOf.remove(aRightHolderOf);
      rightHolderOf.add(index, aRightHolderOf);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRightHolderOfAt(LegalPosition aRightHolderOf, int index)
  {
    boolean wasAdded = false;
    if(rightHolderOf.contains(aRightHolderOf))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRightHolderOf()) { index = numberOfRightHolderOf() - 1; }
      rightHolderOf.remove(aRightHolderOf);
      rightHolderOf.add(index, aRightHolderOf);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRightHolderOfAt(aRightHolderOf, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Contract> copyOfContracts = new ArrayList<Contract>(contracts);
    contracts.clear();
    for(Contract aContract : copyOfContracts)
    {
      if (aContract.numberOfParties() <= Contract.minimumNumberOfParties())
      {
        aContract.delete();
      }
      else
      {
        aContract.removeParty(this);
      }
    }
    for(Role aRole : roles)
    {
      setParty(aRole,null);
    }
    roles.clear();
    ArrayList<Asset> copyOfAssets = new ArrayList<Asset>(assets);
    assets.clear();
    for(Asset aAsset : copyOfAssets)
    {
      aAsset.removeOwner(this);
    }
    ArrayList<LegalPosition> copyOfPerformerOf = new ArrayList<LegalPosition>(performerOf);
    performerOf.clear();
    for(LegalPosition aPerformerOf : copyOfPerformerOf)
    {
      aPerformerOf.removePerformer(this);
    }
    ArrayList<LegalPosition> copyOfLiableOf = new ArrayList<LegalPosition>(liableOf);
    liableOf.clear();
    for(LegalPosition aLiableOf : copyOfLiableOf)
    {
      aLiableOf.removeLiable(this);
    }
    ArrayList<LegalPosition> copyOfRightHolderOf = new ArrayList<LegalPosition>(rightHolderOf);
    rightHolderOf.clear();
    for(LegalPosition aRightHolderOf : copyOfRightHolderOf)
    {
      aRightHolderOf.removeRightHolder(this);
    }
  }

}