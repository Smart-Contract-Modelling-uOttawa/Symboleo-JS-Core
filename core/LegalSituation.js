const { Situation } = require('./Situation.js');

class LegalSituation extends Situation {
  constructor(aTime) {
    super(aTime);
    this.antecedentOf = [];
    this.consequentOf = [];
  }

  getAntecedentOf(index) {
    return this.antecedentOf[index];
  }

  getAntecedentOfAll() {
    return this.antecedentOf;
  }

  numberOfAntecedentOf() {
    return this.antecedentOf.length;
  }

  hasAntecedentOf() {
    return this.antecedentOf.length > 0;
  }

  indexOfAntecedentOf(aAntecedentOf) {
    const index = this.antecedentOf.findIndex((o) => o.equals(aAntecedentOf));
    return index;
  }

  getConsequentOf(index) {
    return this.consequentOf[index];
  }

  getConsequentOfAll() {
    return this.consequentOf;
  }

  numberOfConsequentOf() {
    return this.consequentOf.length;
  }

  hasConsequentOf() {
    return this.consequentOf.length > 0;
  }

  indexOfConsequentOf(arg) {
    const index = this.consequentOf.findIndex((o) => o.equals(arg));
    return index;
  }

  static minimumNumberOfAntecedentOf() {
    return 0;
  }

  addAntecedentOf(aAntecedentOf) {
    let wasAdded = false;
    if (this.antecedentOf.some((o) => o.equals(aAntecedentOf))) {
      return false;
    }
    const existingAntecedent = aAntecedentOf.getAntecedent();
    const isNewAntecedent = existingAntecedent != null && !this.equals(existingAntecedent);
    if (isNewAntecedent) {
      aAntecedentOf.setAntecedent(this);
    } else {
      this.antecedentOf.push(aAntecedentOf);
    }
    wasAdded = true;
    return wasAdded;
  }

  removeAntecedentOf(aAntecedentOf) {
    let wasRemoved = false;
    if (!this.equals(aAntecedentOf.getAntecedent())) {
      const index = this.antecedentOf.findIndex((o) => o.equals(aAntecedentOf));
      this.antecedentOf.splice(index, 1);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  static minimumNumberOfConsequentOf() {
    return 0;
  }

  addConsequentOf(arg) {
    let wasAdded = false;
    if (this.consequentOf.some((o) => o.equals(arg))) {
      return false;
    }
    const existing = arg.getConsequent();
    const isNew = existing != null && !this.equals(existing);
    if (isNew) {
      arg.setConsequent(this);
    } else {
      this.consequentOf.push(arg);
    }
    wasAdded = true;
    return wasAdded;
  }

  removeConsequentOf(arg) {
    let wasRemoved = false;
    if (!this.equals(arg.getConsequent())) {
      const index = this.consequentOf.findIndex((o) => o.equals(arg));
      this.consequentOf.splice(index, 1);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  delete() {
    for (let i = this.antecedentOf.length; i > 0; i--) {
      const aAntecedentOf = this.antecedentOf[i - 1];
      aAntecedentOf.delete();
    }
    for (let i = this.consequentOf.length; i > 0; i--) {
      const aConsequentOf = this.consequentOf[i - 1];
      aConsequentOf.delete();
    }
    super.delete();
  }
}

module.exports.LegalSituation = LegalSituation;
