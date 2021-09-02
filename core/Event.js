export class Event {

  constructor ( ) {
    this.triggered = false
    this.timestamp = null
  }

  happen(event) {
    this.triggered = true
    this.timestamp = new Date().toISOString()
    this.data = event
  }

  hasHappened () {
    return this.triggered
  }
}
