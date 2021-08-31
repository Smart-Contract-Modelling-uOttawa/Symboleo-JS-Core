'use strict'

export class InternalEvent {
  constructor(source, type, name) {
    this.source = source
    this.type = type
    this.name = name
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
    Expired: 'expired',
    Discharged: 'discharged',
    Activated: 'activated',
    Terminated: 'terminated',
    Fulfilled: 'fulfilled',
    Violated: 'violated',
    Suspended: 'suspended',
    Resumed: 'resumed',
  },
  power: {
    Triggered: 'Triggered',
    Expired: 'expired',
    Activated: 'activated',
    Terminated: 'terminated',
    Exerted: 'exerted',
    Suspended: 'suspended',
    Resumed: 'resumed',
  },
  contract: {
    Activated: 'activated',
    Terminated: 'terminated',
    Rescinded: 'rescinded',
    Suspended: 'suspended',
    FulfilledObligations: 'fulfilledObligations',
    RevokedParty: 'RevokedParty',
    AssignedParty: 'AssignedParty',
    Resumed: 'resumed',

  },
  contractEvent: {
    Happened: 'Happened'
  },
}
