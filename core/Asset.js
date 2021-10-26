export class Asset {

  constructor () {
    this._contract = null
    this._owners = null
  }

  addOwner(party) {
    this._owners.push(party)
    party._assets.push(this)
  }

}
