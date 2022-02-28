class InternalEvent {
  constructor(source, type, object) {
    this.source = source;
    this.type = type;
    this.object = object;
  }
}

const InternalEventSource = {
  obligation: 'obligation',
  power: 'power',
  contract: 'contract',
  contractEvent: 'contractEvent',
};

const InternalEventType = {
  obligation: {
    Triggered: 'Triggered',
    Expired: 'Expired',
    Discharged: 'Discharged',
    Activated: 'Activated',
    Terminated: 'Terminated',
    Fulfilled: 'Fulfilled',
    Violated: 'Violated',
    Suspended: 'Suspended',
    Resumed: 'Resumed',
  },
  power: {
    Triggered: 'Triggered',
    Expired: 'Expired',
    Activated: 'Activated',
    Terminated: 'Terminated',
    Exerted: 'Exerted',
    Suspended: 'Suspended',
    Resumed: 'Resumed',
  },
  contract: {
    Activated: 'Activated',
    Terminated: 'Terminated',
    Rescinded: 'Rescinded',
    Suspended: 'Suspended',
    FulfilledObligations: 'FulfilledObligations',
    RevokedParty: 'RevokedParty',
    AssignedParty: 'AssignedParty',
    Resumed: 'Resumed',

  },
  contractEvent: {
    Happened: 'Happened',
  },
};

module.exports.InternalEvent = InternalEvent;
module.exports.InternalEventSource = InternalEventSource;
module.exports.InternalEventType = InternalEventType;
