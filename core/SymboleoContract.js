import { Event } from "./Event.js"
import { Events } from "../Events.js"
import { InternalEvent, InternalEventSource, InternalEventType } from "./InternalEvents.js"

export class SymboleoContract {

  constructor() {
    this.id = parseInt(Math.random() * 10000000)
    this.setActiveState(ContractActiveStates.Null)
    this.setState(ContractStates.Form)
    this.obligations = {}
    this.survivingObligations = {}
    this.powers = {}
    this._events = {}
  }

  isInEffect() {
    return this.state === ContractStates.Active && this.activeState === ContractActiveStates.InEffect
  }

  activated() {
    let wasEventProcessed = false

    let aStatus = this.state
    switch (aStatus) {
      case ContractStates.Form:
        this.setActiveState(ContractActiveStates.InEffect)
        wasEventProcessed = true
        this._events.Activated = new Event()
        this._events.Activated.happen()
        Events.emitEvent(this, new InternalEvent(InternalEventSource.contract, InternalEventType.contract.Activated, this))
        break
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed
  }

  terminated() {
    let wasEventProcessed = false

    let aStatus = this.state
    switch (aStatus) {
      case ContractStates.Active:
        this.exitStatus()
        this.setState(ContractStates.UnsuccessfulTermination)
        wasEventProcessed = true
        this._events.Terminated = new Event()
        this._events.Terminated.happen()
        Events.emitEvent(this, new InternalEvent(InternalEventSource.contract, InternalEventType.contract.Terminated, this))
        break
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed
  }

  rescinded() {
    let wasEventProcessed = false

    let aStatusActive = this.activeState
    switch (aStatusActive) {
      case ContractActiveStates.InEffect:
        this.exitStatusActive()
        this.setActiveState(ContractActiveStates.Rescission)
        wasEventProcessed = true
        this._events.Rescinded = new Event()
        this._events.Rescinded.happen()
        Events.emitEvent(this, new InternalEvent(InternalEventSource.contract, InternalEventType.contract.Rescinded, this))
        break
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed
  }

  suspended() {
    let wasEventProcessed = false

    let aStatusActive = this.activeState
    switch (aStatusActive) {
      case ContractActiveStates.InEffect:
        this.exitStatusActive()
        this.setActiveState(ContractActiveStates.Suspension)
        wasEventProcessed = true
        this._events.Suspended = new Event()
        this._events.Suspended.happen()
        Events.emitEvent(this, new InternalEvent(InternalEventSource.contract, InternalEventType.contract.Suspended, this))
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
  //       setActiveState(StatusActive.Unassign);
  //       wasEventProcessed = true;
  //       break;
  //     default:
  //       // Other states do respond to this event
  //   }

  //   return wasEventProcessed;
  // }

  fulfilledActiveObligations() {
    let wasEventProcessed = false

    let aStatusActive = this.activeState
    switch (aStatusActive) {
      case ContractActiveStates.InEffect:
        this.exitStatus()
        this.setState(ContractStates.SuccessfulTermination)
        wasEventProcessed = true
        this._events.FulfilledObligations = new Event()
        this._events.FulfilledObligations.happen()
        Events.emitEvent(this, new InternalEvent(InternalEventSource.contract, InternalEventType.contract.FulfilledObligations, this))

        break
      default:
      // Other states do respond to this event
    }
    return wasEventProcessed
  }

  resumed() {
    let wasEventProcessed = false

    let aStatusActive = this.activeState
    switch (aStatusActive) {
      case Suspension:
        this.exitStatusActive()
        this.setActiveState(ContractActiveStates.InEffect)
        wasEventProcessed = true
        this._events.Resumed = new Event()
        this._events.Resumed.happen()
        Events.emitEvent(this, new InternalEvent(InternalEventSource.contract, InternalEventType.contract.Resumed, this))
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
  //       setActiveState(StatusActive.InEffect);
  //       wasEventProcessed = true;
  //       break;
  //     default:
  //       // Other states do respond to this event
  //   }

  //   return wasEventProcessed;
  // }

  exitStatus() {
    switch (this.state) {
      case ContractStates.Active:
        this.exitStatusActive()
        break
    }
  }

  setState(aStatus) {
    this.state = aStatus
    // entry actions and do activities
    switch (this.state) {
      case ContractStates.Active:
        if (this.activeState == ContractActiveStates.Null) {
          this.setActiveState(ContractActiveStates.InEffect)
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
    switch (this.activeState) {
      case ContractStates.InEffect:
        this.setActiveState(ContractActiveStates.Null)
        break
      case ContractStates.Suspension:
        this.setActiveState(ContractActiveStates.Null)
        break
      case ContractStates.Unassign:
        this.setActiveState(ContractActiveStates.Null)
        break
      case ContractStates.Rescission:
        this.setActiveState(ContractActiveStates.Null)
        break
    }
  }

  setActiveState(aStatusActive) {
    this.activeState = aStatusActive
    if (this.state != ContractStates.Active && aStatusActive != ContractActiveStates.Null) {
      this.setState(ContractStates.Active)
    }

    // entry actions and do activities
    switch (aStatusActive) {
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

