const { LegalPosition } = require('./LegalPosition.js');
const { Event } = require('./Event.js');
const { Events } = require('../Events.js');
const {
  InternalEvent,
  InternalEventSource, InternalEventType,
} = require('./InternalEvents.js');

const ObligationState = {
  Start: 'Start',
  Create: 'Create',
  Active: 'Active',
  Violation: 'Violation',
  Discharge: 'Discharge',
  Fulfillment: 'Fulfillment',
  UnsuccessfulTermination: 'UnsuccessfulTermination',
};
const ObligationActiveState = {
  Null: 'Null',
  InEffect: 'InEffect',
  Suspension: 'Suspension',
};

class Obligation extends LegalPosition {
  constructor(name, creditor, debtor, contract, surviving) {
    super(name, creditor, debtor, contract);
    this.setActiveState(ObligationActiveState.Null);
    this.setState(ObligationState.Start);
    this._events = {};
    this._surviving = surviving;
  }

  isViolated() {
    return this.state === ObligationState.Violation;
  }

  isInEffect() {
    return this.state === ObligationState.Active
    && this.activeState === ObligationActiveState.InEffect;
  }

  isCreated() {
    return this.state === ObligationState.Create;
  }

  isSuspended() {
    return this.state === ObligationState.Active
     && this.activeState === ObligationActiveState.Suspension;
  }

  isFulfilled() {
    return this.state === ObligationState.Fulfillment;
  }

  isActive() {
    return this.state === ObligationState.Active;
  }

  isUnsuccessfulTermination() {
    return this.state === ObligationState.UnsuccessfulTermination;
  }

  isDischarged() {
    return this.state === ObligationState.Discharge;
  }

  // checks that is in an end state
  isFinished() {
    return this.state === ObligationState.Violation
      || this.state === ObligationState.Discharge
      || this.state === ObligationState.UnsuccessfulTermination
      || this.state === ObligationState.Fulfillment;
  }

  trigerredUnconditional() {
    let wasEventProcessed = false;

    const aStatus = this.state;
    switch (aStatus) {
      case ObligationState.Start:
        this.setActiveState(ObligationActiveState.InEffect);
        wasEventProcessed = true;
        this._events.Activated = new Event();
        this._events.Activated.happen();
        Events.emitEvent(
          this.contract,
          new InternalEvent(
            InternalEventSource.obligation,
            InternalEventType.obligation.Activated,

            this,
          ),
        );
        break;
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  trigerredConditional() {
    let wasEventProcessed = false;

    const aStatus = this.state;
    switch (aStatus) {
      case ObligationState.Start:
        this.setState(ObligationState.Create);
        wasEventProcessed = true;
        this._events.Triggered = new Event();
        this._events.Triggered.happen();
        Events.emitEvent(
          this.contract,
          new InternalEvent(
            InternalEventSource.obligation,
            InternalEventType.obligation.Triggered,

            this,
          ),
        );
        break;
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  expired() {
    let wasEventProcessed = false;

    const aStatus = this.state;
    switch (aStatus) {
      case ObligationState.Create:
        this.setState(ObligationState.Discharge);
        wasEventProcessed = true;
        this._events.Expired = new Event();
        this._events.Expired.happen();
        Events.emitEvent(
          this.contract,
          new InternalEvent(InternalEventSource.obligation, InternalEventType.obligation.Expired, this),
        );
        break;
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  discharged() {
    if (!this.contract.isInEffect()) {
      return false;
    }
    let wasEventProcessed = false;

    const aStatusActive = this.activeState;
    switch (aStatusActive) {
      case ObligationActiveState.InEffect:
        this.exitStatus();
        this.setState(ObligationState.Discharge);
        wasEventProcessed = true;
        this._events.Discharged = new Event();
        this._events.Discharged.happen();
        Events.emitEvent(
          this.contract,
          new InternalEvent(InternalEventSource.obligation, InternalEventType.obligation.Discharged, this),
        );
        break;
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  activated() {
    if (!this.contract.isInEffect()) {
      return false;
    }
    let wasEventProcessed = false;

    const aStatus = this.state;
    switch (aStatus) {
      case ObligationState.Create:
        this.setActiveState(ObligationActiveState.InEffect);
        wasEventProcessed = true;
        this._events.Activated = new Event();
        this._events.Activated.happen();
        Events.emitEvent(
          this.contract,
          new InternalEvent(
            InternalEventSource.obligation,
            InternalEventType.obligation.Activated,

            this,
          ),
        );
        break;
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  terminated() {
    let wasEventProcessed = false;

    const aStatus = this.state;
    switch (aStatus) {
      case ObligationState.Active:
        this.exitStatus();
        this.setState(ObligationState.UnsuccessfulTermination);
        wasEventProcessed = true;
        this._events.Terminated = new Event();
        this._events.Terminated.happen();
        Events.emitEvent(
          this.contract,
          new InternalEvent(InternalEventSource.obligation, InternalEventType.obligation.Terminated, this),
        );
        break;
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  fulfilled() {
    let wasEventProcessed = false;

    const aStatusActive = this.activeState;
    switch (aStatusActive) {
      case ObligationActiveState.InEffect:
        this.exitStatus();
        this.setState(ObligationState.Fulfillment);
        wasEventProcessed = true;
        this._events.Fulfilled = new Event();
        this._events.Fulfilled.happen();
        Events.emitEvent(
          this.contract,
          new InternalEvent(InternalEventSource.obligation, InternalEventType.obligation.Fulfilled, this),
        );
        break;
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  violated() {
    let wasEventProcessed = false;

    const aStatusActive = this.activeState;
    switch (aStatusActive) {
      case ObligationActiveState.InEffect:
        this.exitStatus();
        this.setState(ObligationState.Violation);
        wasEventProcessed = true;
        this._events.Violated = new Event();
        this._events.Violated.happen();
        Events.emitEvent(
          this.contract,
          new InternalEvent(
            InternalEventSource.obligation,
            InternalEventType.obligation.Violated,
            this,
          ),
        );
        break;
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  suspended() {
    let wasEventProcessed = false;

    const aStatusActive = this.activeState;
    switch (aStatusActive) {
      case ObligationActiveState.InEffect:
        this.exitStatusActive();
        this.setActiveState(ObligationActiveState.Suspension);
        wasEventProcessed = true;
        this._events.Suspended = new Event();
        this._events.Suspended.happen();
        Events.emitEvent(
          this.contract,
          new InternalEvent(
            InternalEventSource.obligation,
            InternalEventType.obligation.Suspended,
            this,
          ),
        );

        break;
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  resumed() {
    let wasEventProcessed = false;

    const aStatusActive = this.activeState;
    switch (aStatusActive) {
      case ObligationActiveState.Suspension:
        this.exitStatusActive();
        this.setActiveState(ObligationActiveState.InEffect);
        wasEventProcessed = true;
        this._events.Resumed = new Event();
        this._events.Resumed.happen();
        Events.emitEvent(
          this.contract,
          new InternalEvent(
            InternalEventSource.obligation,
            InternalEventType.obligation.Resumed,

            this,
          ),
        );
        break;
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  exitStatus() {
    switch (this.state) {
      case ObligationState.Active:
        this.exitStatusActive();
        break;
      default:
        break;
    }
  }

  setState(aStatus) {
    this.state = aStatus;

    // entry actions and do activities
    switch (this.state) {
      case ObligationState.Active:
        if (this.activeState === ObligationActiveState.Null) {
          this.setActiveState(ObligationActiveState.InEffect);
        }
        break;
      case ObligationState.Violation:
        break;
      case ObligationState.Discharge:
        break;
      case ObligationState.Fulfillment:
        break;
      case ObligationState.UnsuccessfulTermination:
        break;
      default: break;
    }
  }

  exitStatusActive() {
    switch (this.activeState) {
      case ObligationActiveState.InEffect:
        this.setActiveState(ObligationActiveState.Null);
        break;
      case ObligationActiveState.Suspension:
        this.setActiveState(ObligationActiveState.Null);
        break;
      default: break;
    }
  }

  setActiveState(aStatusActive) {
    this.activeState = aStatusActive;
    if (this.state !== ObligationState.Active
      && aStatusActive !== ObligationActiveState.Null) {
      this.setState(ObligationState.Active);
    }
  }

  delete() {
    super.delete();
  }
}

module.exports.Obligation = Obligation;
module.exports.ObligationActiveState = ObligationActiveState;
module.exports.ObligationState = ObligationState;
