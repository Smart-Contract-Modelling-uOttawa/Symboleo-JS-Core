'use strict'

export class Event {

  constructor ( ) {
    this.triggered = false
    this.timestamp = null
  }

  happen(event) {
    this.triggered = true
    this.timestamp = Date.now()
    this.data = event
  }

  hasHappened () {
    return this.triggered
  }
}
