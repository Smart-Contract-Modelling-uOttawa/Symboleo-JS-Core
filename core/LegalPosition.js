const { SymboleoContract } = require('./SymboleoContract.js');

class LegalPosition {
  constructor(name, creditor, debtor, contract) {
    this.name = name;
    this.creditor = creditor;
    this.debtor = debtor;
    this.contract = contract;
    this.performer = [];
    this.liable = [];
    this.rightHolder = [];
    this.asset = null;
    this.antecedent = null;
    this.consequent = null;
    this.trigger = null;
  }

  getPerformer(index) {
    const aPerformer = this.performer[index];
    return aPerformer;
  }

  getPerformerAll() {
    return this.performer;
  }

  numberOfPerformer() {
    return this.performer.length;
  }

  hasPerformer() {
    return this.performer.length > 0;
  }

  indexOfPerformer(aPerformer) {
    const index = this.performer.findIndex((o) => o.equals(aPerformer));
    return index;
  }

  getLiable(index) {
    const a = this.liable[index];
    return a;
  }

  getLiableAll() {
    return this.liable;
  }

  numberOfLiable() {
    return this.liable.length;
  }

  hasLiable() {
    return this.liable.length > 0;
  }

  indexOfLiable(aLiable) {
    const index = this.liable.findIndex((o) => o.equals(aLiable));
    return index;
  }

  getRightHolder(index) {
    const a = this.rightHolder[index];
    return a;
  }

  getRightHolderAll() {
    return this.rightHolder;
  }

  numberOfRightHolder() {
    return this.rightHolder.length;
  }

  hasRightHolder() {
    return this.rightHolder.length > 0;
  }

  indexOfRightHolder(arg) {
    const index = this.rightHolder.findIndex((o) => o.equals(arg));
    return index;
  }

  static minimumNumberOfPerformer() {
    return 0;
  }

  addPerformer(aPerformer) {
    let wasAdded = false;
    if (this.performer.some((o) => o.equals(aPerformer))) {
      return false;
    }
    this.performer.push(aPerformer);
    if (aPerformer.indexOfPerformerOf(this) !== -1) {
      wasAdded = true;
    } else {
      wasAdded = aPerformer.addPerformerOf(this);
      if (!wasAdded) {
        const index = this.performer.findIndex((o) => o.equals(aPerformer));
        this.performer.splice(index, 1);
      }
    }
    return wasAdded;
  }

  removePerformer(aPerformer) {
    let wasRemoved = false;
    if (!this.performer.some((o) => o.equals(aPerformer))) {
      return wasRemoved;
    }

    const oldIndex = this.performer.findIndex((o) => o.equals(aPerformer));
    this.performer.splice(oldIndex, 1);
    if (aPerformer.indexOfPerformerOf(this) === -1) {
      wasRemoved = true;
    } else {
      wasRemoved = aPerformer.removePerformerOf(this);
      if (!wasRemoved) {
        this.performer.splice(oldIndex, 0, aPerformer);
      }
    }
    return wasRemoved;
  }

  static minimumNumberOfLiable() {
    return 0;
  }

  addLiable(aLiable) {
    let wasAdded = false;
    if (this.liable.some((o) => o.equals(aLiable))) {
      return false;
    }
    this.liable.push(aLiable);
    if (aLiable.indexOfLiableOf(this) !== -1) {
      wasAdded = true;
    } else {
      wasAdded = aLiable.addLiableOf(this);
      if (!wasAdded) {
        const index = this.liable.findIndex((o) => o.equals(aLiable));
        this.liable.splice(index, 1);
      }
    }
    return wasAdded;
  }

  removeLiable(aLiable) {
    let wasRemoved = false;
    if (!this.liable.some((o) => o.equals(aLiable))) {
      return wasRemoved;
    }

    const oldIndex = this.liable.findIndex((o) => o.equals(aLiable));
    this.liable.splice(oldIndex, 1);
    if (aLiable.indexOfLiableOf(this) === -1) {
      wasRemoved = true;
    } else {
      wasRemoved = aLiable.removeLiableOf(this);
      if (!wasRemoved) {
        this.liable.splice(oldIndex, 0, aLiable);
      }
    }
    return wasRemoved;
  }

  static minimumNumberOfRightHolder() {
    return 0;
  }

  addRightHolder(aRightHolder) {
    let wasAdded = false;
    if (this.rightHolder.some((o) => o.equals(aRightHolder))) {
      return false;
    }
    this.rightHolder.push(aRightHolder);
    if (aRightHolder.indexOfRightHolderOf(this) !== -1) {
      wasAdded = true;
    } else {
      wasAdded = aRightHolder.addRightHolderOf(this);
      if (!wasAdded) {
        const index = this.rightHolder.findIndex((o) => o.equals(aRightHolder));
        this.rightHolder.splice(index, 1);
      }
    }
    return wasAdded;
  }

  removeRightHolder(aRightHolder) {
    let wasRemoved = false;
    if (!this.rightHolder.some((o) => o.equals(aRightHolder))) {
      return wasRemoved;
    }

    const oldIndex = this.rightHolder.findIndex(aRightHolder);
    this.rightHolder.splice(oldIndex, 1);
    if (aRightHolder.indexOfRightHolderOf(this) === -1) {
      wasRemoved = true;
    } else {
      wasRemoved = aRightHolder.removeRightHolderOf(this);
      if (!wasRemoved) {
        this.rightHolder.splice(oldIndex, 0, aRightHolder);
      }
    }
    return wasRemoved;
  }

  setContract(aContract) {
    let wasSet = false;
    if (aContract == null) {
      return wasSet;
    }

    if (this.contract != null
      && this.contract.numberOfLegalPositions()
      <= SymboleoContract.minimumNumberOfLegalPositions()) {
      return wasSet;
    }

    const existingContract = this.contract;
    this.contract = aContract;
    if (existingContract != null && !existingContract.equals(aContract)) {
      const didRemove = existingContract.removeLegalPosition(this);
      if (!didRemove) {
        this.contract = existingContract;
        return wasSet;
      }
    }
    this.contract.addLegalPosition(this);
    wasSet = true;
    return wasSet;
  }

  setDebtor(aDebtor) {
    let wasSet = false;
    if (aDebtor == null) {
      return wasSet;
    }

    const existingDebtor = this.debtor;
    this.debtor = aDebtor;
    if (existingDebtor != null && !existingDebtor.equals(aDebtor)) {
      existingDebtor.removeDebt(this);
    }
    this.debtor.addDebt(this);
    wasSet = true;
    return wasSet;
  }

  setCreditor(aCreditor) {
    let wasSet = false;
    if (aCreditor == null) {
      return wasSet;
    }

    const existingCreditor = this.creditor;
    this.creditor = aCreditor;
    if (existingCreditor != null && !existingCreditor.equals(aCreditor)) {
      existingCreditor.removeCredit(this);
    }
    this.creditor.addCredit(this);
    wasSet = true;
    return wasSet;
  }

  setAsset(aAsset) {
    let wasSet = false;
    const existingAsset = this.asset;
    this.asset = aAsset;
    if (existingAsset != null && !existingAsset.equals(aAsset)) {
      existingAsset.removeLegalPosition(this);
    }
    if (aAsset != null) {
      aAsset.addLegalPosition(this);
    }
    wasSet = true;
    return wasSet;
  }

  setAntecedent(aAntecedent) {
    let wasSet = false;
    if (aAntecedent == null) {
      return wasSet;
    }

    const existingAntecedent = this.antecedent;
    this.antecedent = aAntecedent;
    if (existingAntecedent != null && !existingAntecedent.equals(aAntecedent)) {
      existingAntecedent.removeAntecedentOf(this);
    }
    this.antecedent.addAntecedentOf(this);
    wasSet = true;
    return wasSet;
  }

  setConsequent(aConsequent) {
    let wasSet = false;
    if (aConsequent == null) {
      return wasSet;
    }

    const existingConsequent = this.consequent;
    this.consequent = aConsequent;
    if (existingConsequent != null && !existingConsequent.equals(aConsequent)) {
      existingConsequent.removeConsequentOf(this);
    }
    this.consequent.addConsequentOf(this);
    wasSet = true;
    return wasSet;
  }

  setTrigger(aNewTrigger) {
    let wasSet = false;
    this.trigger = aNewTrigger;
    wasSet = true;
    return wasSet;
  }

  delete() {
    for (const aPerformer of this.performer) {
      aPerformer.removePerformerOf(this);
    }
    this.performer = [];
    for (const aLiable of this.liable) {
      aLiable.removeLiableOf(this);
    }
    this.liable = [];
    for (const aRightHolder of this.rightHolder) {
      aRightHolder.removeRightHolderOf(this);
    }
    this.rightHolder = [];

    const placeholderContract = this.contract;
    this.contract = null;
    if (placeholderContract != null) {
      placeholderContract.removeLegalPosition(this);
    }
    const placeholderDebtor = this.debtor;
    this.debtor = null;
    if (placeholderDebtor != null) {
      placeholderDebtor.removeDebt(this);
    }
    const placeholderCreditor = this.creditor;
    this.creditor = null;
    if (placeholderCreditor != null) {
      placeholderCreditor.removeCredit(this);
    }
    if (this.asset != null) {
      const placeholderAsset = this.asset;
      this.asset = null;
      placeholderAsset.removeLegalPosition(this);
    }
  }

  equals(obj) {
    return obj.name != null
      && obj instanceof LegalPosition && obj.name === this.name;
  }
}

module.exports.LegalPosition = LegalPosition;
