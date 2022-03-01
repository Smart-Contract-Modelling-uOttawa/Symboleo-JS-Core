class Event {
  constructor() {
    this._triggered = false;
    this._timestamp = null;
  }

  happen(event) {
    this._triggered = true;
    const d = new Date();
    d.setSeconds(0);
    d.setMilliseconds(0);
    this._timestamp = d.toISOString();
    if (event != null) {
      for (const key of Object.keys(event)) {
        this[key] = event[key];
      }
    }
  }

  hasHappened() {
    return this._triggered;
  }
}

module.exports.Event = Event;
