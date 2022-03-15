const { LegalPosition } = require('./LegalPosition.js');
const { Event } = require('./Event.js');
const { Events } = require('../Events.js');
const { InternalEvent, InternalEventSource, InternalEventType } = require('./InternalEvents.js');

const PowerState = {
  Start: 'Start',
  Create: 'Create',
  Active: 'Active',
  SuccessfulTermination: 'SuccessfulTermination',
  UnsuccessfulTermination: 'UnsuccessfulTermination',
};
const PowerStateActive = {
  Null: 'Null',
  InEffect: 'InEffect',
  Suspension: 'Suspension',
};

class Power extends LegalPosition {
  constructor(name, creditor, debtor, contract) {
    super(name, creditor, debtor, contract);
    this.setActiveState(PowerStateActive.Null);
    this.setState(PowerState.Start);
    this._events = {};
    this.legalPositions = [];
  }

  isInEffect() {
    return this.state === PowerState.Active && this.activeState === PowerStateActive.InEffect;
  }

  isActive() {
    return this.state === PowerState.Active;
  }

  isCreate() {
    return this.state === PowerState.Create;
  }

  isUnsuccessfulTermination() {
    return this.state === PowerState.UnsuccessfulTermination;
  }

  isSuccessfulTermination() {
    return this.state === PowerState.SuccessfulTermination;
  }

  isSuspended() {
    return this.state === PowerState.Active && this.activeState === PowerStateActive.Suspension;
  }

  trigerredUnconditional() {
    let wasEventProcessed = false;

    const aPowerState = this.state;
    switch (aPowerState) {
      case PowerState.Start:
        this.setActiveState(PowerStateActive.InEffect);
        wasEventProcessed = true;
        this._events.Activated = new Event();
        this._events.Activated.happen();
        Events.emitEvent(
          this.contract,
          new InternalEvent(InternalEventSource.power, InternalEventType.power.Activated, this),
        );
        break;
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  trigerredConditional() {
    let wasEventProcessed = false;

    const aPowerState = this.state;
    switch (aPowerState) {
      case PowerState.Start:
        this.setState(PowerState.Create);
        wasEventProcessed = true;
        this._events.Triggered = new Event();
        this._events.Triggered.happen();
        Events.emitEvent(
          this.contract,
          new InternalEvent(InternalEventSource.power, InternalEventType.power.Triggered, this),
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

    const aPowerState = this.state;
    switch (aPowerState) {
      case PowerState.Create:
        this.setActiveState(PowerStateActive.InEffect);
        wasEventProcessed = true;
        this._events.Activated = new Event();
        this._events.Activated.happen();
        Events.emitEvent(
          this.contract,
          new InternalEvent(InternalEventSource.power, InternalEventType.power.Activated, this),
        );
        break;
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  terminated() {
    let wasEventProcessed = false;

    const aPowerState = this.state;
    switch (aPowerState) {
      case PowerState.Active:
        this.exitPowerState();
        this.setState(PowerState.UnsuccessfulTermination);
        wasEventProcessed = true;
        this._events.Terminated = new Event();
        this._events.Terminated.happen();
        Events.emitEvent(
          this.contract,
          new InternalEvent(InternalEventSource.power, InternalEventType.power.Terminated, this),
        );
        break;
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  exerted() {
    let wasEventProcessed = false;

    const aPowerStateActive = this.activeState;
    switch (aPowerStateActive) {
      case PowerStateActive.InEffect:
        this.exitPowerState();
        this.setState(PowerState.SuccessfulTermination);
        wasEventProcessed = true;
        this._events.Exerted = new Event();
        this._events.Exerted.happen();
        Events.emitEvent(
          this.contract,
          new InternalEvent(InternalEventSource.power, InternalEventType.power.Exerted, this),
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
    const aPowerStateActive = this.activeState;

    switch (aStatus) {
      case PowerState.Create:
        this.setState(PowerState.UnsuccessfulTermination);
        wasEventProcessed = true;
        this._events.Expired = new Event();
        this._events.Expired.happen();
        Events.emitEvent(
          this.contract,
          new InternalEvent(InternalEventSource.power, InternalEventType.power.Expired, this),
        );
        break;
      default: break;
    }
    switch (aPowerStateActive) {
      case PowerStateActive.InEffect:
        this.exitPowerState();
        this.setState(PowerState.UnsuccessfulTermination);
        wasEventProcessed = true;
        this._events.Expired = new Event();
        this._events.Expired.happen();
        Events.emitEvent(
          this.contract,
          new InternalEvent(InternalEventSource.power, InternalEventType.power.Expired, this),
        );
        break;
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  suspended() {
    let wasEventProcessed = false;

    const aPowerStateActive = this.activeState;
    switch (aPowerStateActive) {
      case PowerStateActive.InEffect:
        this.exitPowerStateActive();
        this.setActiveState(PowerStateActive.Suspension);
        wasEventProcessed = true;
        this._events.Suspended = new Event();
        this._events.Suspended.happen();
        Events.emitEvent(
          this.contract,
          new InternalEvent(InternalEventSource.power, InternalEventType.power.Suspended, this),
        );
        break;
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  resumed() {
    let wasEventProcessed = false;

    const aPowerStateActive = this.activeState;
    switch (aPowerStateActive) {
      case PowerStateActive.Suspension:
        this.exitPowerStateActive();
        this.setActiveState(PowerStateActive.InEffect);
        wasEventProcessed = true;
        this._events.Resumed = new Event();
        this._events.Resumed.happen();
        Events.emitEvent(
          this.contract,
          new InternalEvent(InternalEventSource.power, InternalEventType.power.Resumed, this),
        );
        break;
      default:
      // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  exitPowerState() {
    switch (this.state) {
      case PowerState.Active:
        this.exitPowerStateActive();
        break;
      default: break;
    }
  }

  setState(aPowerState) {
    this.state = aPowerState;

    // entry actions and do activities
    switch (this.state) {
      case PowerState.Active:
        if (this.activeState === PowerStateActive.Null) {
          this.setActiveState(PowerStateActive.InEffect);
        }
        break;
      case PowerState.SuccessfulTermination:
        // delete ();
        break;
      case PowerState.UnsuccessfulTermination:
        // delete ();
        break;
      default: break;
    }
  }

  exitPowerStateActive() {
    switch (this.activeState) {
      case PowerStateActive.InEffect:
        this.setActiveState(PowerStateActive.Null);
        break;
      case PowerStateActive.Suspension:
        this.setActiveState(PowerStateActive.Null);
        break;
      default: break;
    }
  }

  setActiveState(aPowerStateActive) {
    this.activeState = aPowerStateActive;
    if (this.state !== PowerState.Active && aPowerStateActive !== PowerStateActive.Null) {
      this.setState(PowerState.Active);
    }
  }

  getLegalPosition(index) {
    const aLegalPosition = this.legalPositions[index];
    return aLegalPosition;
  }

  getLegalPositions() {
    return this.legalPositions;
  }

  numberOfLegalPositions() {
    return this.legalPositions.length;
  }

  hasLegalPositions() {
    return this.legalPositions.length > 0;
  }

  indexOfLegalPosition(aLegalPosition) {
    const index = this.legalPositions.findIndex((o) => o.equals(aLegalPosition));
    return index;
  }

  getTerminated() {
    return this.terminated;
  }

  hasTerminated() {
    const has = this.terminated != null;
    return has;
  }

  static minimumNumberOfLegalPositions() {
    return 0;
  }

  addLegalPosition(aLegalPosition) {
    let wasAdded = false;
    if (this.legalPositions.some((o) => o.equals(aLegalPosition))) {
      return false;
    }
    this.legalPositions.push(aLegalPosition);
    wasAdded = true;
    return wasAdded;
  }

  removeLegalPosition(aLegalPosition) {
    let wasRemoved = false;
    if (this.legalPositions.some((o) => o.equals(aLegalPosition))) {
      const index = this.legalPositions.findIndex((o) => o.equals(aLegalPosition));
      this.legalPositions.splice(index, 1);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  setTerminated(aTerminated) {
    let wasSet = false;
    const existingTerminated = this.terminated;
    this.terminated = aTerminated;
    if (existingTerminated != null && !existingTerminated.equals(aTerminated)) {
      existingTerminated.removeTerminator(this);
    }
    if (aTerminated != null) {
      aTerminated.addTerminator(this);
    }
    wasSet = true;
    return wasSet;
  }

  delete() {
    this.legalPositions = [];
    if (this.terminated != null) {
      const placeholderTerminated = this.terminated;
      this.terminated = null;
      placeholderTerminated.removeTerminator(this);
    }
    super.delete();
  }
}

module.exports.Power = Power;
module.exports.PowerState = PowerState;
module.exports.PowerStateActive = PowerStateActive;
