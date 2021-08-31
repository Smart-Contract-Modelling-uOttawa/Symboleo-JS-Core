import { LegalPosition } from "./LegalPosition"

export class Power extends LegalPosition {

  constructor(name, creditor, debtor, contract) {
    super(name, creditor, debtor, contract)
    this.setPowerActiveState(PowerStateActive.Null)
    this.setPowerState(PowerState.Start)
  }

  isInEffect() {
    return this.state === ObligationState.Active && this.stateActive === ObligationActiveState.InEffect
  }

  trigerredConditional() {
    let wasEventProcessed = false

    let aPowerState = this.state
    switch (aPowerState) {
      case PowerState.Start:
        this.setPowerState(PowerState.Create)
        wasEventProcessed = true
        break
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed
  }

  trigerredUnconditional() {
    let wasEventProcessed = false

    let aPowerState = this.state
    switch (aPowerState) {
      case PowerState.Start:
        this.setPowerActiveState(PowerStateActive.InEffect)
        wasEventProcessed = true
        break
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed
  }

  expired() {
    let wasEventProcessed = false

    let aPowerState = this.state
    switch (aPowerState) {
      case PowerState.Create:
        this.setPowerState(PowerState.UnsuccessfulTermination)
        // this._events.expired = new Event()
        wasEventProcessed = true
        break
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed
  }

  activated() {
    if (!this.contract.isInEffect()) {
      return false
    }
    let wasEventProcessed = false

    let aPowerState = this.state
    switch (aPowerState) {
      case PowerState.Create:
        this.setPowerActiveState(PowerStateActive.InEffect)
        wasEventProcessed = true
        break
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed
  }

  terminated() {
    let wasEventProcessed = false

    let aPowerState = this.state
    switch (aPowerState) {
      case PowerState.Active:
        this.exitPowerState()
        this.setPowerState(PowerState.UnsuccessfulTermination)
        wasEventProcessed = true
        break
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed
  }

  exerted() {
    let wasEventProcessed = false

    let aPowerStateActive = this.activeState
    switch (aPowerStateActive) {
      case PowerStateActive.InEffect:
        this.exitPowerState()
        this.setPowerState(PowerState.SuccessfulTermination)
        wasEventProcessed = true
        break
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed
  }

  expired() {
    let wasEventProcessed = false

    let aStatus = this.state
    let aPowerStateActive = this.activeState

    switch (aStatus) {
      case PowerState.Create:
        this.setPowerState(PowerState.UnsuccessfulTermination)
        wasEventProcessed = true
        break
    }
    switch (aPowerStateActive) {
      case PowerStateActive.InEffect:
        this.exitPowerState()
        this.setPowerState(PowerState.UnsuccessfulTermination)
        wasEventProcessed = true
        break
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed
  }

  suspended() {
    let wasEventProcessed = false

    let aPowerStateActive = this.activeState
    switch (aPowerStateActive) {
      case PowerStateActive.InEffect:
        this.exitPowerStateActive()
        this.setPowerActiveState(PowerStateActive.Suspension)
        wasEventProcessed = true
        break
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed
  }

  resumed() {
    let wasEventProcessed = false

    let aPowerStateActive = this.activeState
    switch (aPowerStateActive) {
      case PowerStateActive.Suspension:
        this.exitPowerStateActive()
        this.setPowerActiveState(PowerStateActive.InEffect)
        wasEventProcessed = true
        break
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed
  }

  exitPowerState() {
    switch (this.state) {
      case PowerState.Active:
        this.exitPowerStateActive()
        break
    }
  }

  setPowerState(aPowerState) {
    this.state = aPowerState

    // entry actions and do activities
    switch (this.state) {
      case PowerState.Active:
        if (this.activeState == PowerStateActive.Null) {
          this.setPowerActiveState(PowerStateActive.InEffect)
        }
        break
      case PowerState.SuccessfulTermination:
        // delete ();
        break
      case PowerState.UnsuccessfulTermination:
        // delete ();
        break
    }
  }

  exitPowerStateActive() {
    switch (this.activeState) {
      case PowerStateActive.InEffect:
        this.setPowerActiveState(PowerStateActive.Null)
        break
      case PowerStateActive.Suspension:
        this.setPowerActiveState(PowerStateActive.Null)
        break
    }
  }

  setPowerActiveState(aPowerStateActive) {
    this.activeState = aPowerStateActive
    if (this.state != PowerState.Active && aPowerStateActive != PowerStateActive.Null) {
      this.setPowerState(PowerState.Active)
    }
  }

}


export const PowerState = {
  Start: 'Start',
  Create: 'Create',
  Active: 'Active',
  SuccessfulTermination: 'SuccessfulTermination',
  UnsuccessfulTermination: 'UnsuccessfulTermination'
}
export const PowerStateActive = {
  Null: 'Null',
  InEffect: 'InEffect',
  Suspension: 'Suspension'
}