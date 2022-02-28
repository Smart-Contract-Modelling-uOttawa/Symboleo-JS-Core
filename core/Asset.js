class Asset {
  constructor(id, contract, owners, legaPositions) {
    this._id = id;
    this._owners = owners;
    this._legalPositions = legaPositions;
    this.setContract(contract);
  }

  getOwner(index) {
    return this._owners[index];
  }

  getOwners() {
    return this._owners;
  }

  numberOfOwners() {
    return this._owners.length;
  }

  hasOwners() {
    return this._owners.length > 0;
  }

  indexOfOwner(aOwner) {
    const index = this._owners.findIndex((o) => o.equals(aOwner));
    return index;
  }

  getLegalPosition(index) {
    return this._legalPositions[index];
  }

  getLegalPositions() {
    return this._legalPositions;
  }

  numberOfLegalPositions() {
    return this._legalPositions.length;
  }

  hasLegalPositions() {
    return this._legalPositions.length > 0;
  }

  indexOfLegalPosition(aLegalPosition) {
    const index = this._legalPositions.findIndex((o) => o.equals(aLegalPosition));
    return index;
  }

  static minimumNumberOfOwners() {
    return 0;
  }

  addOwner(aOwner) {
    let wasAdded = false;
    if (this._owners.some((o) => o.equals(aOwner))) {
      return false;
    }
    this._owners.push(aOwner);
    if (aOwner.indexOfAsset(this) !== -1) {
      wasAdded = true;
    } else {
      wasAdded = aOwner.addAsset(this);
      if (!wasAdded) {
        const index = this._owners.findIndex((o) => o.equals(aOwner));
        this._owners.splice(index, 1);
      }
    }
    return wasAdded;
  }

  removeOwner(aOwner) {
    let wasRemoved = false;
    if (!this._owners.some((o) => o.equals(aOwner))) {
      return wasRemoved;
    }

    const oldIndex = this._owners.findIndex((o) => o.equals(aOwner));
    this._owners.splice(oldIndex, 1);
    if (aOwner.indexOfAsset(this) === -1) {
      wasRemoved = true;
    } else {
      wasRemoved = aOwner.removeAsset(this);
      if (!wasRemoved) {
        this._owners.splice(oldIndex, 0, aOwner);
      }
    }
    return wasRemoved;
  }

  static minimumNumberOfLegalPositions() {
    return 0;
  }

  addLegalPosition(aLegalPosition) {
    let wasAdded = false;
    if (this._legalPositions.some((o) => o.equals(aLegalPosition))) {
      return false;
    }
    const existingAsset = aLegalPosition.getAsset();
    if (existingAsset == null) {
      aLegalPosition.setAsset(this);
    } else if (!this.equals(existingAsset)) {
      existingAsset.removeLegalPosition(aLegalPosition);
      this.addLegalPosition(aLegalPosition);
    } else {
      this._legalPositions.push(aLegalPosition);
    }
    wasAdded = true;
    return wasAdded;
  }

  removeLegalPosition(aLegalPosition) {
    let wasRemoved = false;
    if (this._legalPositions.some((o) => o.equals(aLegalPosition))) {
      const index = this._legalPositions.some((o) => o.equals(aLegalPosition));
      this._legalPositions.splice(index, 1);
      aLegalPosition.setAsset(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  setContract(aContract) {
    let wasSet = false;
    if (aContract == null) {
      return wasSet;
    }

    const existingContract = this._contract;
    this._contract = aContract;
    if (existingContract != null && !existingContract.equals(aContract)) {
      existingContract.removeAsset(this);
    }
    this._contract.addAsset(this);
    wasSet = true;
    return wasSet;
  }

  delete() {
    for (const aOwner of this._owners) {
      aOwner.removeAsset(this);
    }
    this._owners = [];

    while (this._legalPositions.length !== 0) {
      this._legalPositions[0].setAsset(null);
    }
    const placeholderContract = this._contract;
    this.contract = null;
    if (placeholderContract != null) {
      placeholderContract.removeAsset(this);
    }
  }

  equals(obj) {
    return obj._id != null && obj instanceof Asset && obj._id === this._id;
  }
}

module.exports.Asset = Asset;
