//%% NEW FILE Role BEGINS HERE %%

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 40 "model.ump"
public class Role
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Role Associations
  private List<LegalPosition> debt;
  private List<LegalPosition> credit;
  private Party party;
  private Contract contract;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Role(Contract aContract)
  {
    debt = new ArrayList<LegalPosition>();
    credit = new ArrayList<LegalPosition>();
    boolean didAddContract = setContract(aContract);
    if (!didAddContract)
    {
      throw new RuntimeException("Unable to create role due to contract. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public LegalPosition getDebt(int index)
  {
    LegalPosition aDebt = debt.get(index);
    return aDebt;
  }

  public List<LegalPosition> getDebt()
  {
    List<LegalPosition> newDebt = Collections.unmodifiableList(debt);
    return newDebt;
  }

  public int numberOfDebt()
  {
    int number = debt.size();
    return number;
  }

  public boolean hasDebt()
  {
    boolean has = debt.size() > 0;
    return has;
  }

  public int indexOfDebt(LegalPosition aDebt)
  {
    int index = debt.indexOf(aDebt);
    return index;
  }
  /* Code from template association_GetMany */
  public LegalPosition getCredit(int index)
  {
    LegalPosition aCredit = credit.get(index);
    return aCredit;
  }

  public List<LegalPosition> getCredit()
  {
    List<LegalPosition> newCredit = Collections.unmodifiableList(credit);
    return newCredit;
  }

  public int numberOfCredit()
  {
    int number = credit.size();
    return number;
  }

  public boolean hasCredit()
  {
    boolean has = credit.size() > 0;
    return has;
  }

  public int indexOfCredit(LegalPosition aCredit)
  {
    int index = credit.indexOf(aCredit);
    return index;
  }
  /* Code from template association_GetOne */
  public Party getParty()
  {
    return party;
  }

  public boolean hasParty()
  {
    boolean has = party != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Contract getContract()
  {
    return contract;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfDebt()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public LegalPosition addDebt(LegalSituation aAntecedent, LegalSituation aConsequent, Contract aContract, Role aCreditor)
  {
    return new LegalPosition(aAntecedent, aConsequent, aContract, this, aCreditor);
  }

  public boolean addDebt(LegalPosition aDebt)
  {
    boolean wasAdded = false;
    if (debt.contains(aDebt)) { return false; }
    Role existingDebtor = aDebt.getDebtor();
    boolean isNewDebtor = existingDebtor != null && !this.equals(existingDebtor);
    if (isNewDebtor)
    {
      aDebt.setDebtor(this);
    }
    else
    {
      debt.add(aDebt);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeDebt(LegalPosition aDebt)
  {
    boolean wasRemoved = false;
    //Unable to remove aDebt, as it must always have a debtor
    if (!this.equals(aDebt.getDebtor()))
    {
      debt.remove(aDebt);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addDebtAt(LegalPosition aDebt, int index)
  {  
    boolean wasAdded = false;
    if(addDebt(aDebt))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDebt()) { index = numberOfDebt() - 1; }
      debt.remove(aDebt);
      debt.add(index, aDebt);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveDebtAt(LegalPosition aDebt, int index)
  {
    boolean wasAdded = false;
    if(debt.contains(aDebt))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDebt()) { index = numberOfDebt() - 1; }
      debt.remove(aDebt);
      debt.add(index, aDebt);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addDebtAt(aDebt, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCredit()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public LegalPosition addCredit(LegalSituation aAntecedent, LegalSituation aConsequent, Contract aContract, Role aDebtor)
  {
    return new LegalPosition(aAntecedent, aConsequent, aContract, aDebtor, this);
  }

  public boolean addCredit(LegalPosition aCredit)
  {
    boolean wasAdded = false;
    if (credit.contains(aCredit)) { return false; }
    Role existingCreditor = aCredit.getCreditor();
    boolean isNewCreditor = existingCreditor != null && !this.equals(existingCreditor);
    if (isNewCreditor)
    {
      aCredit.setCreditor(this);
    }
    else
    {
      credit.add(aCredit);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCredit(LegalPosition aCredit)
  {
    boolean wasRemoved = false;
    //Unable to remove aCredit, as it must always have a creditor
    if (!this.equals(aCredit.getCreditor()))
    {
      credit.remove(aCredit);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCreditAt(LegalPosition aCredit, int index)
  {  
    boolean wasAdded = false;
    if(addCredit(aCredit))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCredit()) { index = numberOfCredit() - 1; }
      credit.remove(aCredit);
      credit.add(index, aCredit);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCreditAt(LegalPosition aCredit, int index)
  {
    boolean wasAdded = false;
    if(credit.contains(aCredit))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCredit()) { index = numberOfCredit() - 1; }
      credit.remove(aCredit);
      credit.add(index, aCredit);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCreditAt(aCredit, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOptionalOneToMandatoryMany */
  public boolean setParty(Party aParty)
  {
    //
    // This source of this source generation is association_SetOptionalOneToMandatoryMany.jet
    // This set file assumes the generation of a maximumNumberOfXXX method does not exist because 
    // it's not required (No upper bound)
    //   
    boolean wasSet = false;
    Party existingParty = party;

    if (existingParty == null)
    {
      if (aParty != null)
      {
        if (aParty.addRole(this))
        {
          existingParty = aParty;
          wasSet = true;
        }
      }
    } 
    else if (existingParty != null)
    {
      if (aParty == null)
      {
        if (existingParty.minimumNumberOfRoles() < existingParty.numberOfRoles())
        {
          existingParty.removeRole(this);
          existingParty = aParty;  // aParty == null
          wasSet = true;
        }
      } 
      else
      {
        if (existingParty.minimumNumberOfRoles() < existingParty.numberOfRoles())
        {
          existingParty.removeRole(this);
          aParty.addRole(this);
          existingParty = aParty;
          wasSet = true;
        }
      }
    }
    if (wasSet)
    {
      party = existingParty;
    }
    return wasSet;
  }
    /* Code from template association_SetOneToMandatoryMany */
  public boolean setContract(Contract aContract)
  {
    boolean wasSet = false;
    //Must provide contract to role
    if (aContract == null)
    {
      return wasSet;
    }

    if (contract != null && contract.numberOfRoles() <= Contract.minimumNumberOfRoles())
    {
      return wasSet;
    }

    Contract existingContract = contract;
    contract = aContract;
    if (existingContract != null && !existingContract.equals(aContract))
    {
      boolean didRemove = existingContract.removeRole(this);
      if (!didRemove)
      {
        contract = existingContract;
        return wasSet;
      }
    }
    contract.addRole(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=debt.size(); i > 0; i--)
    {
      LegalPosition aDebt = debt.get(i - 1);
      aDebt.delete();
    }
    for(int i=credit.size(); i > 0; i--)
    {
      LegalPosition aCredit = credit.get(i - 1);
      aCredit.delete();
    }
    if (party != null)
    {
      if (party.numberOfRoles() <= 1)
      {
        party.delete();
      }
      else
      {
        Party placeholderParty = party;
        this.party = null;
        placeholderParty.removeRole(this);
      }
    }
    Contract placeholderContract = contract;
    this.contract = null;
    if(placeholderContract != null)
    {
      placeholderContract.removeRole(this);
    }
  }

}



//%% NEW FILE Party BEGINS HERE %%

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 38 "model.ump"
public class Party
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Party Associations
  private Contract contract;
  private List<Role> roles;
  private List<Asset> assets;
  private List<LegalPosition> performerOf;
  private List<LegalPosition> liableOf;
  private List<LegalPosition> rightHolderOf;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Party(Contract aContract, Role... allRoles)
  {
    boolean didAddContract = setContract(aContract);
    if (!didAddContract)
    {
      throw new RuntimeException("Unable to create party due to contract. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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
  /* Code from template association_GetOne */
  public Contract getContract()
  {
    return contract;
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
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setContract(Contract aContract)
  {
    boolean wasSet = false;
    //Must provide contract to party
    if (aContract == null)
    {
      return wasSet;
    }

    if (contract != null && contract.numberOfParties() <= Contract.minimumNumberOfParties())
    {
      return wasSet;
    }

    Contract existingContract = contract;
    contract = aContract;
    if (existingContract != null && !existingContract.equals(aContract))
    {
      boolean didRemove = existingContract.removeParty(this);
      if (!didRemove)
      {
        contract = existingContract;
        return wasSet;
      }
    }
    contract.addParty(this);
    wasSet = true;
    return wasSet;
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
    Contract placeholderContract = contract;
    this.contract = null;
    if(placeholderContract != null)
    {
      placeholderContract.removeParty(this);
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



//%% NEW FILE TimeInterval BEGINS HERE %%

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 73 "model.ump"
public class TimeInterval
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TimeInterval Associations
  private TimePoint start;
  private TimePoint end;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TimeInterval(TimePoint aStart, TimePoint aEnd)
  {
    if (!setStart(aStart))
    {
      throw new RuntimeException("Unable to create TimeInterval due to aStart. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (!setEnd(aEnd))
    {
      throw new RuntimeException("Unable to create TimeInterval due to aEnd. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public TimePoint getStart()
  {
    return start;
  }
  /* Code from template association_GetOne */
  public TimePoint getEnd()
  {
    return end;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setStart(TimePoint aNewStart)
  {
    boolean wasSet = false;
    if (aNewStart != null)
    {
      start = aNewStart;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setEnd(TimePoint aNewEnd)
  {
    boolean wasSet = false;
    if (aNewEnd != null)
    {
      end = aNewEnd;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    start = null;
    end = null;
  }

}



//%% NEW FILE Obligation BEGINS HERE %%

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 85 "model.ump"
public class Obligation extends LegalPosition
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Obligation Attributes
  private boolean surviving;

  //Obligation State Machines
  public enum Status { Start, Create, Active, Violation, Discharge, Fulfillment, UnsuccessfulTermination }
  public enum StatusActive { Null, InEffect, Suspension }
  private Status status;
  private StatusActive statusActive;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Obligation(LegalSituation aAntecedent, LegalSituation aConsequent, Contract aContract, Role aDebtor, Role aCreditor, boolean aSurviving)
  {
    super(aAntecedent, aConsequent, aContract, aDebtor, aCreditor);
    surviving = aSurviving;
    setStatusActive(StatusActive.Null);
    setStatus(Status.Start);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSurviving(boolean aSurviving)
  {
    boolean wasSet = false;
    surviving = aSurviving;
    wasSet = true;
    return wasSet;
  }

  /**
   * true iff this is a surviving obligation.
   */
  public boolean getSurviving()
  {
    return surviving;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isSurviving()
  {
    return surviving;
  }

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

  public boolean triggeredConditional()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Start:
        setStatus(Status.Create);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean trigerredUnconditional()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Start:
        setStatusActive(StatusActive.InEffect);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean expired()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Create:
        setStatus(Status.Discharge);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean activated()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Create:
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

  public boolean fulfilled()
  {
    boolean wasEventProcessed = false;
    
    StatusActive aStatusActive = statusActive;
    switch (aStatusActive)
    {
      case InEffect:
        exitStatus();
        setStatus(Status.Fulfillment);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean violated()
  {
    boolean wasEventProcessed = false;
    
    StatusActive aStatusActive = statusActive;
    switch (aStatusActive)
    {
      case InEffect:
        exitStatus();
        setStatus(Status.Violation);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean discharged()
  {
    boolean wasEventProcessed = false;
    
    StatusActive aStatusActive = statusActive;
    switch (aStatusActive)
    {
      case InEffect:
        exitStatus();
        setStatus(Status.Discharge);
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
      case Violation:
        delete();
        break;
      case Discharge:
        delete();
        break;
      case Fulfillment:
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
    }
  }

  private void setStatusActive(StatusActive aStatusActive)
  {
    statusActive = aStatusActive;
    if (status != Status.Active && aStatusActive != StatusActive.Null) { setStatus(Status.Active); }
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "surviving" + ":" + getSurviving()+ "]";
  }
}



//%% NEW FILE TimePoint BEGINS HERE %%

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 79 "model.ump"
public class TimePoint
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TimePoint()
  {}

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {}

}



//%% NEW FILE Asset BEGINS HERE %%

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 46 "model.ump"
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



//%% NEW FILE Event BEGINS HERE %%

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 68 "model.ump"
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



//%% NEW FILE Situation BEGINS HERE %%

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 61 "model.ump"
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



//%% NEW FILE Contract BEGINS HERE %%

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
  /* Code from template association_AddMandatoryManyToOne */
  public Party addParty(Role... allRoles)
  {
    Party aNewParty = new Party(this, allRoles);
    return aNewParty;
  }

  public boolean addParty(Party aParty)
  {
    boolean wasAdded = false;
    if (parties.contains(aParty)) { return false; }
    Contract existingContract = aParty.getContract();
    boolean isNewContract = existingContract != null && !this.equals(existingContract);

    if (isNewContract && existingContract.numberOfParties() <= minimumNumberOfParties())
    {
      return wasAdded;
    }
    if (isNewContract)
    {
      aParty.setContract(this);
    }
    else
    {
      parties.add(aParty);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeParty(Party aParty)
  {
    boolean wasRemoved = false;
    //Unable to remove aParty, as it must always have a contract
    if (this.equals(aParty.getContract()))
    {
      return wasRemoved;
    }

    //contract already at minimum (2)
    if (numberOfParties() <= minimumNumberOfParties())
    {
      return wasRemoved;
    }

    parties.remove(aParty);
    wasRemoved = true;
    return wasRemoved;
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
    
    for(int i=parties.size(); i > 0; i--)
    {
      Party aParty = parties.get(i - 1);
      aParty.delete();
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



//%% NEW FILE LegalPosition BEGINS HERE %%

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 51 "model.ump"
public class LegalPosition
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //LegalPosition Associations
  private List<Party> performer;
  private List<Party> liable;
  private List<Party> rightHolder;
  private LegalSituation antecedent;
  private LegalSituation consequent;
  private LegalSituation trigger;
  private Contract contract;
  private Role debtor;
  private Role creditor;
  private Asset asset;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public LegalPosition(LegalSituation aAntecedent, LegalSituation aConsequent, Contract aContract, Role aDebtor, Role aCreditor)
  {
    performer = new ArrayList<Party>();
    liable = new ArrayList<Party>();
    rightHolder = new ArrayList<Party>();
    boolean didAddAntecedent = setAntecedent(aAntecedent);
    if (!didAddAntecedent)
    {
      throw new RuntimeException("Unable to create antecedentOf due to antecedent. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddConsequent = setConsequent(aConsequent);
    if (!didAddConsequent)
    {
      throw new RuntimeException("Unable to create consequentOf due to consequent. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddContract = setContract(aContract);
    if (!didAddContract)
    {
      throw new RuntimeException("Unable to create legalPosition due to contract. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddDebtor = setDebtor(aDebtor);
    if (!didAddDebtor)
    {
      throw new RuntimeException("Unable to create debt due to debtor. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddCreditor = setCreditor(aCreditor);
    if (!didAddCreditor)
    {
      throw new RuntimeException("Unable to create credit due to creditor. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Party getPerformer(int index)
  {
    Party aPerformer = performer.get(index);
    return aPerformer;
  }

  public List<Party> getPerformer()
  {
    List<Party> newPerformer = Collections.unmodifiableList(performer);
    return newPerformer;
  }

  public int numberOfPerformer()
  {
    int number = performer.size();
    return number;
  }

  public boolean hasPerformer()
  {
    boolean has = performer.size() > 0;
    return has;
  }

  public int indexOfPerformer(Party aPerformer)
  {
    int index = performer.indexOf(aPerformer);
    return index;
  }
  /* Code from template association_GetMany */
  public Party getLiable(int index)
  {
    Party aLiable = liable.get(index);
    return aLiable;
  }

  public List<Party> getLiable()
  {
    List<Party> newLiable = Collections.unmodifiableList(liable);
    return newLiable;
  }

  public int numberOfLiable()
  {
    int number = liable.size();
    return number;
  }

  public boolean hasLiable()
  {
    boolean has = liable.size() > 0;
    return has;
  }

  public int indexOfLiable(Party aLiable)
  {
    int index = liable.indexOf(aLiable);
    return index;
  }
  /* Code from template association_GetMany */
  public Party getRightHolder(int index)
  {
    Party aRightHolder = rightHolder.get(index);
    return aRightHolder;
  }

  public List<Party> getRightHolder()
  {
    List<Party> newRightHolder = Collections.unmodifiableList(rightHolder);
    return newRightHolder;
  }

  public int numberOfRightHolder()
  {
    int number = rightHolder.size();
    return number;
  }

  public boolean hasRightHolder()
  {
    boolean has = rightHolder.size() > 0;
    return has;
  }

  public int indexOfRightHolder(Party aRightHolder)
  {
    int index = rightHolder.indexOf(aRightHolder);
    return index;
  }
  /* Code from template association_GetOne */
  public LegalSituation getAntecedent()
  {
    return antecedent;
  }
  /* Code from template association_GetOne */
  public LegalSituation getConsequent()
  {
    return consequent;
  }
  /* Code from template association_GetOne */
  public LegalSituation getTrigger()
  {
    return trigger;
  }

  public boolean hasTrigger()
  {
    boolean has = trigger != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Contract getContract()
  {
    return contract;
  }
  /* Code from template association_GetOne */
  public Role getDebtor()
  {
    return debtor;
  }
  /* Code from template association_GetOne */
  public Role getCreditor()
  {
    return creditor;
  }
  /* Code from template association_GetOne */
  public Asset getAsset()
  {
    return asset;
  }

  public boolean hasAsset()
  {
    boolean has = asset != null;
    return has;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPerformer()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addPerformer(Party aPerformer)
  {
    boolean wasAdded = false;
    if (performer.contains(aPerformer)) { return false; }
    performer.add(aPerformer);
    if (aPerformer.indexOfPerformerOf(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPerformer.addPerformerOf(this);
      if (!wasAdded)
      {
        performer.remove(aPerformer);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removePerformer(Party aPerformer)
  {
    boolean wasRemoved = false;
    if (!performer.contains(aPerformer))
    {
      return wasRemoved;
    }

    int oldIndex = performer.indexOf(aPerformer);
    performer.remove(oldIndex);
    if (aPerformer.indexOfPerformerOf(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPerformer.removePerformerOf(this);
      if (!wasRemoved)
      {
        performer.add(oldIndex,aPerformer);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPerformerAt(Party aPerformer, int index)
  {  
    boolean wasAdded = false;
    if(addPerformer(aPerformer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPerformer()) { index = numberOfPerformer() - 1; }
      performer.remove(aPerformer);
      performer.add(index, aPerformer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePerformerAt(Party aPerformer, int index)
  {
    boolean wasAdded = false;
    if(performer.contains(aPerformer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPerformer()) { index = numberOfPerformer() - 1; }
      performer.remove(aPerformer);
      performer.add(index, aPerformer);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPerformerAt(aPerformer, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLiable()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addLiable(Party aLiable)
  {
    boolean wasAdded = false;
    if (liable.contains(aLiable)) { return false; }
    liable.add(aLiable);
    if (aLiable.indexOfLiableOf(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aLiable.addLiableOf(this);
      if (!wasAdded)
      {
        liable.remove(aLiable);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeLiable(Party aLiable)
  {
    boolean wasRemoved = false;
    if (!liable.contains(aLiable))
    {
      return wasRemoved;
    }

    int oldIndex = liable.indexOf(aLiable);
    liable.remove(oldIndex);
    if (aLiable.indexOfLiableOf(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aLiable.removeLiableOf(this);
      if (!wasRemoved)
      {
        liable.add(oldIndex,aLiable);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLiableAt(Party aLiable, int index)
  {  
    boolean wasAdded = false;
    if(addLiable(aLiable))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLiable()) { index = numberOfLiable() - 1; }
      liable.remove(aLiable);
      liable.add(index, aLiable);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLiableAt(Party aLiable, int index)
  {
    boolean wasAdded = false;
    if(liable.contains(aLiable))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLiable()) { index = numberOfLiable() - 1; }
      liable.remove(aLiable);
      liable.add(index, aLiable);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLiableAt(aLiable, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRightHolder()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addRightHolder(Party aRightHolder)
  {
    boolean wasAdded = false;
    if (rightHolder.contains(aRightHolder)) { return false; }
    rightHolder.add(aRightHolder);
    if (aRightHolder.indexOfRightHolderOf(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aRightHolder.addRightHolderOf(this);
      if (!wasAdded)
      {
        rightHolder.remove(aRightHolder);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeRightHolder(Party aRightHolder)
  {
    boolean wasRemoved = false;
    if (!rightHolder.contains(aRightHolder))
    {
      return wasRemoved;
    }

    int oldIndex = rightHolder.indexOf(aRightHolder);
    rightHolder.remove(oldIndex);
    if (aRightHolder.indexOfRightHolderOf(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aRightHolder.removeRightHolderOf(this);
      if (!wasRemoved)
      {
        rightHolder.add(oldIndex,aRightHolder);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addRightHolderAt(Party aRightHolder, int index)
  {  
    boolean wasAdded = false;
    if(addRightHolder(aRightHolder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRightHolder()) { index = numberOfRightHolder() - 1; }
      rightHolder.remove(aRightHolder);
      rightHolder.add(index, aRightHolder);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRightHolderAt(Party aRightHolder, int index)
  {
    boolean wasAdded = false;
    if(rightHolder.contains(aRightHolder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRightHolder()) { index = numberOfRightHolder() - 1; }
      rightHolder.remove(aRightHolder);
      rightHolder.add(index, aRightHolder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRightHolderAt(aRightHolder, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAntecedent(LegalSituation aAntecedent)
  {
    boolean wasSet = false;
    if (aAntecedent == null)
    {
      return wasSet;
    }

    LegalSituation existingAntecedent = antecedent;
    antecedent = aAntecedent;
    if (existingAntecedent != null && !existingAntecedent.equals(aAntecedent))
    {
      existingAntecedent.removeAntecedentOf(this);
    }
    antecedent.addAntecedentOf(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setConsequent(LegalSituation aConsequent)
  {
    boolean wasSet = false;
    if (aConsequent == null)
    {
      return wasSet;
    }

    LegalSituation existingConsequent = consequent;
    consequent = aConsequent;
    if (existingConsequent != null && !existingConsequent.equals(aConsequent))
    {
      existingConsequent.removeConsequentOf(this);
    }
    consequent.addConsequentOf(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setTrigger(LegalSituation aNewTrigger)
  {
    boolean wasSet = false;
    trigger = aNewTrigger;
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setContract(Contract aContract)
  {
    boolean wasSet = false;
    //Must provide contract to legalPosition
    if (aContract == null)
    {
      return wasSet;
    }

    if (contract != null && contract.numberOfLegalPositions() <= Contract.minimumNumberOfLegalPositions())
    {
      return wasSet;
    }

    Contract existingContract = contract;
    contract = aContract;
    if (existingContract != null && !existingContract.equals(aContract))
    {
      boolean didRemove = existingContract.removeLegalPosition(this);
      if (!didRemove)
      {
        contract = existingContract;
        return wasSet;
      }
    }
    contract.addLegalPosition(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setDebtor(Role aDebtor)
  {
    boolean wasSet = false;
    if (aDebtor == null)
    {
      return wasSet;
    }

    Role existingDebtor = debtor;
    debtor = aDebtor;
    if (existingDebtor != null && !existingDebtor.equals(aDebtor))
    {
      existingDebtor.removeDebt(this);
    }
    debtor.addDebt(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setCreditor(Role aCreditor)
  {
    boolean wasSet = false;
    if (aCreditor == null)
    {
      return wasSet;
    }

    Role existingCreditor = creditor;
    creditor = aCreditor;
    if (existingCreditor != null && !existingCreditor.equals(aCreditor))
    {
      existingCreditor.removeCredit(this);
    }
    creditor.addCredit(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setAsset(Asset aAsset)
  {
    boolean wasSet = false;
    Asset existingAsset = asset;
    asset = aAsset;
    if (existingAsset != null && !existingAsset.equals(aAsset))
    {
      existingAsset.removeLegalPosition(this);
    }
    if (aAsset != null)
    {
      aAsset.addLegalPosition(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Party> copyOfPerformer = new ArrayList<Party>(performer);
    performer.clear();
    for(Party aPerformer : copyOfPerformer)
    {
      aPerformer.removePerformerOf(this);
    }
    ArrayList<Party> copyOfLiable = new ArrayList<Party>(liable);
    liable.clear();
    for(Party aLiable : copyOfLiable)
    {
      aLiable.removeLiableOf(this);
    }
    ArrayList<Party> copyOfRightHolder = new ArrayList<Party>(rightHolder);
    rightHolder.clear();
    for(Party aRightHolder : copyOfRightHolder)
    {
      aRightHolder.removeRightHolderOf(this);
    }
    LegalSituation placeholderAntecedent = antecedent;
    this.antecedent = null;
    if(placeholderAntecedent != null)
    {
      placeholderAntecedent.removeAntecedentOf(this);
    }
    LegalSituation placeholderConsequent = consequent;
    this.consequent = null;
    if(placeholderConsequent != null)
    {
      placeholderConsequent.removeConsequentOf(this);
    }
    trigger = null;
    Contract placeholderContract = contract;
    this.contract = null;
    if(placeholderContract != null)
    {
      placeholderContract.removeLegalPosition(this);
    }
    Role placeholderDebtor = debtor;
    this.debtor = null;
    if(placeholderDebtor != null)
    {
      placeholderDebtor.removeDebt(this);
    }
    Role placeholderCreditor = creditor;
    this.creditor = null;
    if(placeholderCreditor != null)
    {
      placeholderCreditor.removeCredit(this);
    }
    if (asset != null)
    {
      Asset placeholderAsset = asset;
      this.asset = null;
      placeholderAsset.removeLegalPosition(this);
    }
  }

}



//%% NEW FILE LegalSituation BEGINS HERE %%

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 81 "model.ump"
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



//%% NEW FILE Power BEGINS HERE %%

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 121 "model.ump"
public class Power extends LegalPosition
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Power State Machines
  public enum Status { Start, Create, Active, SuccessfulTermination, UnsuccessfulTermination }
  public enum StatusActive { Null, InEffect, Suspension }
  private Status status;
  private StatusActive statusActive;

  //Power Associations
  private List<LegalPosition> legalPositions;
  private Contract terminated;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Power(LegalSituation aAntecedent, LegalSituation aConsequent, Contract aContract, Role aDebtor, Role aCreditor)
  {
    super(aAntecedent, aConsequent, aContract, aDebtor, aCreditor);
    legalPositions = new ArrayList<LegalPosition>();
    setStatusActive(StatusActive.Null);
    setStatus(Status.Start);
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

  public boolean triggeredConditional()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Start:
        setStatus(Status.Create);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean trigerredUnconditional()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Start:
        setStatusActive(StatusActive.InEffect);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean expired()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    StatusActive aStatusActive = statusActive;
    switch (aStatus)
    {
      case Create:
        setStatus(Status.UnsuccessfulTermination);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    switch (aStatusActive)
    {
      case InEffect:
        exitStatus();
        setStatus(Status.UnsuccessfulTermination);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean activated()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Create:
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

  public boolean exerted()
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
    }
  }

  private void setStatusActive(StatusActive aStatusActive)
  {
    statusActive = aStatusActive;
    if (status != Status.Active && aStatusActive != StatusActive.Null) { setStatus(Status.Active); }
  }
  /* Code from template association_GetMany */
  public LegalPosition getLegalPosition(int index)
  {
    LegalPosition aLegalPosition = legalPositions.get(index);
    return aLegalPosition;
  }

  /**
   * Unidirectional associations; the 0..1 do not influence code generation
   */
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
  public Contract getTerminated()
  {
    return terminated;
  }

  public boolean hasTerminated()
  {
    boolean has = terminated != null;
    return has;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLegalPositions()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addLegalPosition(LegalPosition aLegalPosition)
  {
    boolean wasAdded = false;
    if (legalPositions.contains(aLegalPosition)) { return false; }
    legalPositions.add(aLegalPosition);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLegalPosition(LegalPosition aLegalPosition)
  {
    boolean wasRemoved = false;
    if (legalPositions.contains(aLegalPosition))
    {
      legalPositions.remove(aLegalPosition);
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
  /* Code from template association_SetOptionalOneToMany */
  public boolean setTerminated(Contract aTerminated)
  {
    boolean wasSet = false;
    Contract existingTerminated = terminated;
    terminated = aTerminated;
    if (existingTerminated != null && !existingTerminated.equals(aTerminated))
    {
      existingTerminated.removeTerminator(this);
    }
    if (aTerminated != null)
    {
      aTerminated.addTerminator(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    legalPositions.clear();
    if (terminated != null)
    {
      Contract placeholderTerminated = terminated;
      this.terminated = null;
      placeholderTerminated.removeTerminator(this);
    }
    super.delete();
  }

}