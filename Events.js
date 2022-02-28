const { InternalEventSource } = require('./core/InternalEvents.js');

module.exports.Events = {

  init(eventsMap, listeners) {
    this.eventsMap = eventsMap;
    this.listeners = listeners;
  },

  emitEvent(contract, emittedEvent) {
    for (const subscription of this.eventsMap) {
      const events = subscription[0];
      const callback = subscription[1];
      for (const event of events) {
        if (this.eventsMatch(event, emittedEvent)) {
          callback(contract);
        }
      }
    }
  },

  eventsMatch(a, b) {
    if (a.source === b.source && a.type === b.type) {
      if (a.source === InternalEventSource.obligation || a.source === InternalEventSource.power) {
        return a.object.name === b.object.name;
      } if (a.source === InternalEventSource.contract || a.source === InternalEventSource.contractEvent) {
        return a.object._name === b.object._name;
      }
      return false;
    }
    return false;
  },
};
