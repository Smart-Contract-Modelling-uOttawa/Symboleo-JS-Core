export class InternalEvent {
  constructor(source, type, object) {
    this.source = source
    this.type = type
    this.object = object
  }
}

export const InternalEventSource = {
  obligation: 'obligation',
  power: 'power',
  contract: 'contract',
  contractEvent: 'contractEvent',
}

export const InternalEventType = {
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
    Happened: 'Happened'
  },
}
