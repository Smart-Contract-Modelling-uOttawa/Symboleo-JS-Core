export class Event {
  constructor() {
    this._triggered = false;
    this._timestamp = null;
  }

  happen(event) {
    this._triggered = true;
    this._timestamp = new Date().toISOString();
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
