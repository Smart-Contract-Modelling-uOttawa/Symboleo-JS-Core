const { InternalEventSource } = require('./core/InternalEvents.js');
const { InternalEventType } = require('./core/InternalEvents.js');

const EventsObject = {

  init(eventsMap, listeners) {
    EventsObject.eventsMap = eventsMap;
    EventsObject.listeners = listeners;
  },

  emitEvent(contract, emittedEvent) {
    const obligationViolated = emittedEvent.source === InternalEventSource.obligation
      && emittedEvent.type === InternalEventType.obligation.Violated;
    const effects = { powerNames: [] };
    for (const subscription of EventsObject.eventsMap) {
      const events = subscription[0];
      const callback = subscription[1];
      for (const event of events) {
        if (EventsObject.eventsMatch(emittedEvent, event)) {
          const res = callback(contract);
          if (res != null && res.powerCreated) {
            effects.powerCreated = true;
            effects.powerNames.push(res.powerName);
          }
        }
      }
    }
    if (emittedEvent.source === InternalEventSource.obligation) {
      // eslint-disable-next-line no-param-reassign
      emittedEvent.object._createdPowerNames = effects.powerNames;
    }
    if (obligationViolated && effects.powerCreated !== true) {
      // if an obligation was violated and no power was triggered
      // then we call unsuccessfullyTerminateContract
      EventsObject.listeners.unsuccessfullyTerminateContract(contract);
      return;
    }
    if (emittedEvent.source === InternalEventSource.obligation
        && !obligationViolated) {
      // check if we can successfully terminate the contract
      EventsObject.listeners.successfullyTerminateContract(contract);
    }
  },

  eventsMatch(a, b) {
    if (b.object == null) {
      return false;
    }
    if (a.source === b.source && a.type === b.type) {
      if (a.source === InternalEventSource.obligation
        || a.source === InternalEventSource.power) {
        return a.object.name === b.object.name;
      } if (a.source === InternalEventSource.contract
        || a.source === InternalEventSource.contractEvent) {
        return a.object._name === b.object._name;
      }
      return false;
    }
    return false;
  },
};

module.exports.Events = EventsObject;
