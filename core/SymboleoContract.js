'use strict'

export class SymboleoContract {

  constructor() {
    this.id = parseInt(Math.random() * 10000000)
    this.setStatusActive(ContractActiveStates.Null)
    this.setStatus(ContractStates.Form)
    this.obligations = {}
    this.powers = {}
  }

  isInEffect() {
    return this.status === ContractStates.Active && this.statusActive === ContractActiveStates.InEffect
  }

  addObligation(key, obl) {
    this.obligations[key] = obl
  }

  addPower(key, power) {
    this.powers[key] = power
  }

  activated() {
    let wasEventProcessed = false

    let aStatus = this.status
    switch (aStatus) {
      case ContractStates.Form:
        setStatusActive(ContractActiveStates.InEffect)
        wasEventProcessed = true
        break
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed
  }

  terminated() {
    let wasEventProcessed = false

    let aStatus = this.status
    switch (aStatus) {
      case ContractStates.Active:
        exitStatus()
        setStatus(ContractStates.UnsuccessfulTermination)
        wasEventProcessed = true
        break
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed
  }

  rescinded() {
    let wasEventProcessed = false

    let aStatusActive = this.statusActive
    switch (aStatusActive) {
      case ContractActiveStates.InEffect:
        exitStatusActive()
        setStatusActive(ContractActiveStates.Rescission)
        wasEventProcessed = true
        break
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed
  }

  suspended() {
    let wasEventProcessed = false

    let aStatusActive = this.statusActive
    switch (aStatusActive) {
      case ContractActiveStates.InEffect:
        this.exitStatusActive()
        this.setStatusActive(ContractActiveStates.Suspension)
        wasEventProcessed = true
        break
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed
  }

  // revokedParty() {
  //   wasEventProcessed = false;

  //   aStatusActive = statusActive;
  //   switch (aStatusActive)
  //   {
  //     case InEffect:
  //       exitStatusActive();
  //       setStatusActive(StatusActive.Unassign);
  //       wasEventProcessed = true;
  //       break;
  //     default:
  //       // Other states do respond to this event
  //   }

  //   return wasEventProcessed;
  // }

  fulfilledActiveObligations() {
    let wasEventProcessed = false

    let aStatusActive = this.statusActive
    switch (aStatusActive) {
      case ContractActiveStates.InEffect:
        exitStatus()
        setStatus(ContractStates.SuccessfulTermination)
        wasEventProcessed = true
        break
      default:
      // Other states do respond to this event
    }
    return wasEventProcessed
  }

  resumed() {
    let wasEventProcessed = false

    let aStatusActive = this.statusActive
    switch (aStatusActive) {
      case Suspension:
        exitStatusActive()
        setStatusActive(ContractActiveStates.InEffect)
        wasEventProcessed = true
        break
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed
  }

  // assignedParty()
  // {
  //   wasEventProcessed = false;

  //   aStatusActive = statusActive;
  //   switch (aStatusActive)
  //   {
  //     case Unassign:
  //       exitStatusActive();
  //       setStatusActive(StatusActive.InEffect);
  //       wasEventProcessed = true;
  //       break;
  //     default:
  //       // Other states do respond to this event
  //   }

  //   return wasEventProcessed;
  // }

  exitStatus() {
    switch (this.status) {
      case ContractStates.Active:
        exitStatusActive()
        break
    }
  }

  setStatus(aStatus) {
    this.status = aStatus
    // entry actions and do activities
    switch (status) {
      case ContractStates.Active:
        if (this.statusActive == ContractActiveStates.Null) {
          setStatusActive(ContractActiveStates.InEffect)
        }
        break
      case ContractStates.SuccessfulTermination:
        // delete();
        break
      case ContractStates.UnsuccessfulTermination:
        // delete();
        break
    }
  }

  exitStatusActive() {
    switch (this.statusActive) {
      case ContractStates.InEffect:
        setStatusActive(ContractActiveStates.Null)
        break
      case ContractStates.Suspension:
        setStatusActive(ContractActiveStates.Null)
        break
      case ContractStates.Unassign:
        setStatusActive(ContractActiveStates.Null)
        break
      case ContractStates.Rescission:
        setStatusActive(ContractActiveStates.Null)
        break
    }
  }

  setStatusActive(aStatusActive) {
    this.statusActive = aStatusActive
    if (this.status != ContractStates.Active && aStatusActive != ContractActiveStates.Null) {
      this.setStatus(ContractStates.Active)
    }

    // entry actions and do activities
    switch (statusActive) {
      case ContractActiveStates.Rescission:
        // delete();
        break
    }
  }
}

export const ContractStates = {
  Form: 'Form',
  Active: 'Active',
  SuccessfulTermination: 'SuccessfulTermination',
  UnsuccessfulTermination: 'UnsuccessfulTermination'
}

export const ContractActiveStates = {
  Null: 'Null',
  InEffect: 'InEffect',
  Suspension: 'Suspension',
  Unassign: 'Unassign',
  Rescission: 'Rescission'
}

