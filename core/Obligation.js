import { LegalPosition } from "./LegalPosition.js"
import { Event } from "./Event.js"
import { Events } from "../Events.js"
import { InternalEvent, InternalEventSource, InternalEventType } from "./InternalEvents.js"


export class Obligation extends LegalPosition {

  constructor(name, creditor, debtor, contract) {
    super(name, creditor, debtor, contract)
    this.setActiveState(ObligationActiveState.Null)
    this.setState(ObligationState.Start)
    this._events = {}
  }

  isViolated() {
    return this.state === ObligationState.Violation
  }

  isInEffect() {
    return this.state === ObligationState.Active && this.activeState === ObligationActiveState.InEffect
  }

  isSuspended() {
    return this.state === ObligationState.Active && this.activeState === ObligationActiveState.Suspension
  }

  isFulfilled(){
    return this.state === ObligationState.Fulfillment
  }

  isActive () {
    this.state === ObligationState.Active
  }

  trigerredConditional() {
    let wasEventProcessed = false

    let aStatus = this.state
    switch (aStatus) {
      case ObligationState.Start:
        this.setState(ObligationState.Create)
        wasEventProcessed = true
        this._events.Triggered = new Event()
        this._events.Triggered.happen()
        Events.emitEvent(this.contract, new InternalEvent(InternalEventSource.obligation, InternalEventType.obligation.Triggered, this))
        break
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed
  }

  trigerredUnconditional() {
    let wasEventProcessed = false

    let aStatus = this.state
    switch (aStatus) {
      case ObligationState.Start:
        this.setActiveState(ObligationActiveState.InEffect)
        wasEventProcessed = true
        this._events.Triggered = new Event()
        this._events.Triggered.happen()
        Events.emitEvent(this.contract, new InternalEvent(InternalEventSource.obligation, InternalEventType.obligation.Triggered, this))
        break
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed
  }

  expired() {
    let wasEventProcessed = false

    let aStatus = this.state
    switch (aStatus) {
      case ObligationState.Create:
        this.setState(ObligationState.Discharge)
        wasEventProcessed = true
        this._events.Expired = new Event()
        this._events.Expired.happen()
        Events.emitEvent(this.contract, new InternalEvent(InternalEventSource.obligation, InternalEventType.obligation.Expired, this))
        break
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed
  }

  discharged() {
    if (!this.contract.isInEffect()) {
      return false
    }
    let wasEventProcessed = false

    let aStatus = this.state
    switch (aStatus) {
      case ObligationState.InEffect:
        this.exitStatus()
        this.setState(ObligationState.Discharge)
        wasEventProcessed = true
        this._events.Discharged = new Event()
        this._events.Discharged.happen()
        Events.emitEvent(this.contract, new InternalEvent(InternalEventSource.obligation, InternalEventType.obligation.Discharged, this))
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

    let aStatus = this.state
    switch (aStatus) {
      case ObligationState.Create:
        this.setActiveState(ObligationActiveState.InEffect)
        wasEventProcessed = true
        this._events.Activated = new Event()
        this._events.Activated.happen()
        Events.emitEvent(this.contract, new InternalEvent(InternalEventSource.obligation, InternalEventType.obligation.Activated, this))
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
      case ObligationState.Active:
        this.exitStatus()
        this.setState(ObligationState.UnsuccessfulTermination)
        wasEventProcessed = true
        this._events.Terminated = new Event()
        this._events.Terminated.happen()
        Events.emitEvent(this.contract, new InternalEvent(InternalEventSource.obligation, InternalEventType.obligation.Terminated, this))
        break
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed
  }

  fulfilled() {
    let wasEventProcessed = false

    let aStatusActive = this.activeState
    switch (aStatusActive) {
      case ObligationActiveState.InEffect:
        this.exitStatus()
        this.setState(ObligationState.Fulfillment)
        wasEventProcessed = true
        this._events.Fulfilled = new Event()
        this._events.Fulfilled.happen()
        Events.emitEvent(this.contract, new InternalEvent(InternalEventSource.obligation, InternalEventType.obligation.Fulfilled, this))
        break
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed
  }

  violated() {
    let wasEventProcessed = false

    let aStatusActive = this.activeState
    switch (aStatusActive) {
      case ObligationActiveState.InEffect:
        this.exitStatus()
        this.setState(ObligationState.Violation)
        wasEventProcessed = true
        this._events.Violated = new Event()
        this._events.Violated.happen()
        Events.emitEvent(this.contract, new InternalEvent(InternalEventSource.obligation, InternalEventType.obligation.Violated, this))
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
      case ObligationActiveState.InEffect:
        this.exitStatusActive()
        this.setActiveState(ObligationActiveState.Suspension)
        wasEventProcessed = true
        this._events.Suspended = new Event()
        this._events.Suspended.happen()
        Events.emitEvent(this.contract, new InternalEvent(InternalEventSource.obligation, InternalEventType.obligation.Suspended, this))

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
      case ObligationActiveState.Suspension:
        this.exitStatusActive()
        this.setActiveState(ObligationActiveState.InEffect)
        wasEventProcessed = true
        this._events.Resumed = new Event()
        this._events.Resumed.happen()
        break
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed
  }

  exitStatus() {
    switch (this.state) {
      case ObligationState.Active:
        this.exitStatusActive()
        break
    }
  }

  setState(aStatus) {
    this.state = aStatus

    // entry actions and do activities
    switch (this.state) {
      case ObligationState.Active:
        if (this.activeState == ObligationActiveState.Null) {
          this.setActiveState(ObligationActiveState.InEffect)
        }
        break
      case ObligationState.Violation:
        break
      case ObligationState.Discharge:
        break
      case ObligationState.Fulfillment:
        break
      case ObligationState.UnsuccessfulTermination:
        break
    }
  }

  exitStatusActive() {
    switch (this.activeState) {
      case ObligationActiveState.InEffect:
        this.setActiveState(ObligationActiveState.Null)
        break
      case ObligationActiveState.Suspension:
        this.setActiveState(ObligationActiveState.Null)
        break
    }
  }

  setActiveState(aStatusActive) {
    this.activeState = aStatusActive
    if (this.state != ObligationState.Active && aStatusActive != ObligationActiveState.Null) {
      this.setState(ObligationState.Active)
    }
  }

}

export const ObligationState = {
  Start: 'Start',
  Create: 'Create',
  Active: 'Active',
  Violation: 'Violation',
  Discharge: 'Discharge',
  Fulfillment: 'Fulfillment',
  UnsuccessfulTermination: 'UnsuccessfulTermination'
}
export const ObligationActiveState = {
  Null: 'Null',
  InEffect: 'InEffect',
  Suspension: 'Suspension'
}
