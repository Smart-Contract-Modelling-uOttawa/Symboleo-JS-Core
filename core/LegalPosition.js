export class LegalPosition {
  constructor(name, creditor, debtor, contract) {
    this.name = name;
    this.creditor = creditor;
    this.debtor = debtor;
    this.contract = contract;
  }

  equals(obj) {
    return obj.name != null && obj instanceof LegalPosition && obj.name === this.name;
  }
}
