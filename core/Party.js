const { SymboleoContract } = require('./SymboleoContract.js');

class Party {
  constructor(
    id,
    allRoles,
    contracts = [],
    assets = [],
    performerOf = [],
    liableOf = [],
    rightHolderOf = [],
  ) {
    this._id = id;
    this._assets = assets;
    this._performerOf = performerOf;
    this._liableOf = liableOf;
    this._rightHolderOf = rightHolderOf;
    this._contracts = contracts;

    this._roles = [];
    const didAddRoles = this.setRoles(allRoles);
    if (!didAddRoles) {
      throw new Error('Unable to create Party, must have at least 1 roles.');
    }
  }

  getContract(index) {
    return this._contracts[index];
  }

  getContracts() {
    return this._contracts;
  }

  numberOfContracts() {
    return this._contracts.length;
  }

  hasContracts() {
    return this._contracts.length > 0;
  }

  getRole(index) {
    return this._roles[index];
  }

  getRoles() {
    return this._roles;
  }

  numberOfRoles() {
    return this._roles.length;
  }

  hasRoles() {
    return this._roles.length > 0;
  }

  getAsset(index) {
    return this._assets[index];
  }

  getAssets() {
    return this._assets;
  }

  numberOfAssets() {
    return this._assets.length;
  }

  hasAssets() {
    return this._assets.length > 0;
  }

  getPerformerOf(index) {
    return this._performerOf[index];
  }

  getPerformerOfList() {
    return this._performerOf;
  }

  numberOfPerformerOf() {
    return this._performerOf.length;
  }

  hasPerformerOf() {
    return this._performerOf.length > 0;
  }

  getLiableOf(index) {
    return this._liableOf[index];
  }

  getLiableOfList() {
    return this._liableOf;
  }

  numberOfLiableOf() {
    return this._liableOf.length;
  }

  hasLiableOf() {
    return this._liableOf.length > 0;
  }

  getRightHolderOf(index) {
    return this._rightHolderOf[index];
  }

  getRightHolderOfList() {
    return this._rightHolderOf;
  }

  numberOfRightHolderOf() {
    return this._rightHolderOf.length;
  }

  hasRightHolderOf() {
    return this._rightHolderOf.length > 0;
  }

  indexOfContract(aContract) {
    const index = this._contracts.findIndex((o) => o.equals(aContract));
    return index;
  }

  indexOfAsset(aAsset) {
    const index = this._assets.findIndex((o) => o.equals(aAsset));
    return index;
  }

  indexOfRightHolderOf(a) {
    const index = this._rightHolderOf.findIndex((o) => o.equals(a));
    return index;
  }

  indexOfLiableOf(a) {
    const index = this._liableOf.findIndex((o) => o.equals(a));
    return index;
  }

  indexOfPerformerOf(a) {
    const index = this._performerOf.findIndex((o) => o.equals(a));
    return index;
  }

  static minimumNumberOfContracts() {
    return 1;
  }

  isNumberOfContractsValid() {
    const isValid = this.numberOfContracts() >= this.minimumNumberOfContracts();
    return isValid;
  }

  addContract(aContract) {
    let wasAdded = false;
    if (this._contracts.some((o) => o.equals(aContract))) { return false; }
    this._contracts.push(aContract);

    if (aContract.indexOfParty(this) !== -1) {
      wasAdded = true;
    } else {
      wasAdded = aContract.addParty(this);
      if (!wasAdded) {
        const index = this._contracts.findIndex((o) => o.equals(aContract));
        this._contracts.splice(index, 1);
      }
    }
    return wasAdded;
  }

  removeContract(aContract) {
    let wasRemoved = false;
    if (!this._contracts.some((o) => o.equals(aContract))) {
      return wasRemoved;
    }

    if (this.numberOfContracts() <= this.minimumNumberOfContracts()) {
      return wasRemoved;
    }

    const oldIndex = this._contracts.findIndex(aContract);
    this._contracts.splice(oldIndex, 1);
    if (aContract.indexOfParty(this) === -1) {
      wasRemoved = true;
    } else {
      wasRemoved = aContract.removeParty(this);
      if (!wasRemoved) {
        this._contracts.splice(oldIndex, 0, aContract);
      }
    }
    return wasRemoved;
  }

  static minimumNumberOfRoles() {
    return 1;
  }

  addRole(aRole) {
    let wasAdded = false;
    if (this._roles.some((o) => o.equals(aRole))) {
      return false;
    }
    const existingParty = aRole.getParty();
    if (existingParty != null
      && existingParty.numberOfRoles() <= this.minimumNumberOfRoles()) {
      return wasAdded;
    }
    if (existingParty != null) {
      const index = existingParty._roles.findIndex((o) => o.equals(aRole));
      existingParty._roles.splice(index, 1);
    }
    this._roles.push(aRole);
    this.setParty(aRole, this);
    wasAdded = true;
    return wasAdded;
  }

  removeRole(aRole) {
    let wasRemoved = false;
    if (this._roles.some((o) => o.equals(aRole))
      && this.numberOfRoles() > this.minimumNumberOfRoles()) {
      const index = this._roles.findIndex((o) => o.equals(aRole));
      this._roles.splice(index, 1);
      this.setParty(aRole, null);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  setRoles(newRoles) {
    let wasSet = false;
    if (newRoles.length < this.minimumNumberOfRoles()) {
      return wasSet;
    }

    const checkNewRoles = [];
    const partyToNewRoles = {};
    for (const aRole of newRoles) {
      if (checkNewRoles.some((o) => o.equals(aRole))) {
        return wasSet;
      }
      if (aRole.getParty() != null && !this.equals(aRole.getParty())) {
        const existingParty = aRole.getParty();
        if (!partyToNewRoles[existingParty._id] != null) {
          partyToNewRoles[existingParty._id] = existingParty.numberOfRoles();
        }
        const currentCount = partyToNewRoles[existingParty._id];
        const nextCount = currentCount - 1;
        if (nextCount < 1) {
          return wasSet;
        }
        partyToNewRoles[existingParty._id] = nextCount;
      }
      checkNewRoles.push(aRole);
    }

    this._roles = this._roles.filter((o) => !checkNewRoles.some((d) => o.equals(d)));

    for (const orphan of this._roles) {
      this.setParty(orphan, null);
    }
    this._roles = [];
    for (const aRole of newRoles) {
      if (aRole.getParty() != null) {
        const index = aRole.getParty()._roles.findIndex((o) => o.equals(aRole));
        aRole.getParty()._roles.splice(index, 1);
      }
      this.setParty(aRole, this);
      this._roles.push(aRole);
    }
    wasSet = true;
    return wasSet;
  }

  static setParty(argRole, party) {
    /* eslint no-param-reassign: "error" */
    argRole._party = party;
  }

  static minimumNumberOfAssets() {
    return 0;
  }

  addAsset(aAsset) {
    let wasAdded = false;
    if (this._assets.some(aAsset.equals)) {
      return false;
    }
    this._assets.push(aAsset);
    if (aAsset.indexOfOwner(this) !== -1) {
      wasAdded = true;
    } else {
      wasAdded = aAsset.addOwner(this);
      if (!wasAdded) {
        const index = this._assets.findIndex((o) => o.equals(aAsset));
        this._assets.splice(index, 1);
      }
    }
    return wasAdded;
  }

  removeAsset(aAsset) {
    let wasRemoved = false;
    if (!this.assets.some((o) => o.equals(aAsset))) {
      return wasRemoved;
    }

    const oldIndex = this._assets.findIndex((o) => o.equals(aAsset));
    this._assets.splice(oldIndex, 1);
    if (aAsset.indexOfOwner(this) === -1) {
      wasRemoved = true;
    } else {
      wasRemoved = aAsset.removeOwner(this);
      if (!wasRemoved) {
        this._assets.splice(oldIndex, 0, aAsset);
      }
    }
    return wasRemoved;
  }

  static minimumNumberOfPerformerOf() {
    return 0;
  }

  addPerformerOf(aPerformerOf) {
    let wasAdded = false;
    if (this._performerOf.some((o) => o.equals(aPerformerOf))) {
      return false;
    }
    this._performerOf.push(aPerformerOf);
    if (aPerformerOf.indexOfPerformer(this) !== -1) {
      wasAdded = true;
    } else {
      wasAdded = aPerformerOf.addPerformer(this);
      if (!wasAdded) {
        const index = this._performerOf.findIndex((o) => o.equals(aPerformerOf));
        this._performerOf.splice(index, 1);
      }
    }
    return wasAdded;
  }

  removePerformerOf(aPerformerOf) {
    let wasRemoved = false;
    if (!this._performerOf.some((o) => o.equals(aPerformerOf))) {
      return wasRemoved;
    }

    const oldIndex = this._performerOf.findIndex((o) => o.equals(aPerformerOf));
    this._performerOf.splice(oldIndex, 1);
    if (aPerformerOf.indexOfPerformer(this) === -1) {
      wasRemoved = true;
    } else {
      wasRemoved = aPerformerOf.removePerformer(this);
      if (!wasRemoved) {
        this._performerOf.splice(oldIndex, 0, aPerformerOf);
      }
    }
    return wasRemoved;
  }

  static minimumNumberOfLiableOf() {
    return 0;
  }

  addLiableOf(aLiableOf) {
    let wasAdded = false;
    if (this._liableOf.some((o) => o.equals(aLiableOf))) {
      return false;
    }
    this._liableOf.push(aLiableOf);
    if (aLiableOf.indexOfLiable(this) !== -1) {
      wasAdded = true;
    } else {
      wasAdded = aLiableOf.addLiable(this);
      if (!wasAdded) {
        const index = this._liableOf.findIndex((o) => o.equals(aLiableOf));
        this._liableOf.splice(index, 1);
      }
    }
    return wasAdded;
  }

  removeLiableOf(aLiableOf) {
    let wasRemoved = false;
    if (!this._liableOf.contains(aLiableOf)) {
      return wasRemoved;
    }

    const oldIndex = this._liableOf.indexOf(aLiableOf);
    this._liableOf.splice(oldIndex, 1);
    if (aLiableOf.indexOfLiable(this) === -1) {
      wasRemoved = true;
    } else {
      wasRemoved = aLiableOf.removeLiable(this);
      if (!wasRemoved) {
        this._liableOf.splice(oldIndex, 0, aLiableOf);
      }
    }
    return wasRemoved;
  }

  static minimumNumberOfRightHolderOf() {
    return 0;
  }

  addRightHolderOf(aRightHolderOf) {
    let wasAdded = false;
    if (this._rightHolderOf.some((o) => o.equals(aRightHolderOf))) {
      return false;
    }
    this._rightHolderOf.push(aRightHolderOf);
    if (aRightHolderOf.indexOfRightHolder(this) !== -1) {
      wasAdded = true;
    } else {
      wasAdded = aRightHolderOf.addRightHolder(this);
      if (!wasAdded) {
        const index = this._rightHolderOf.findIndex((o) => o.equals(aRightHolderOf));
        this._rightHolderOf.splice(index, 1);
      }
    }
    return wasAdded;
  }

  removeRightHolderOf(aRightHolderOf) {
    let wasRemoved = false;
    if (this._rightHolderOf.contains(aRightHolderOf)) {
      return wasRemoved;
    }

    const oldIndex = this._rightHolderOf.indexOf(aRightHolderOf);
    this._rightHolderOf.splice(oldIndex, 1);
    if (aRightHolderOf.indexOfRightHolder(this) === -1) {
      wasRemoved = true;
    } else {
      wasRemoved = aRightHolderOf.removeRightHolder(this);
      if (!wasRemoved) {
        this._liableOf.splice(oldIndex, 0, aRightHolderOf);
      }
    }
    return wasRemoved;
  }

  delete() {
    for (const aContract of this._contracts) {
      if (aContract.numberOfParties() <= SymboleoContract.minimumNumberOfParties()) {
        aContract.delete();
      } else {
        aContract.removeParty(this);
      }
    }
    for (const aRole of this._roles) {
      this.setParty(aRole, null);
    }
    this._roles = [];

    for (const aAsset of this._assets) {
      aAsset.removeOwner(this);
    }
    this._assets = [];

    for (const aPerformerOf of this._performerOf) {
      aPerformerOf.removePerformer(this);
    }
    this._performerOf = [];

    for (const aLiableOf of this._liableOf) {
      aLiableOf.removeLiable(this);
    }
    this._liableOf = [];
    for (const aRightHolderOf of this._rightHolderOf) {
      aRightHolderOf.removeRightHolder(this);
    }
    this._rightHolderOf = [];
  }

  equals(obj) {
    return obj._id != null && obj instanceof Party && obj._id === this._id;
  }
}

module.exports.Party = Party;
