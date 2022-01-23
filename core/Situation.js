export class Situation {
  constructor(aTime) {
    this.preEvents = [];
    this.postEvents = [];
    this.setTime(aTime);
  }

  setTime(aNewTime) {
    let wasSet = false;
    if (aNewTime != null) {
      this.time = aNewTime;
      wasSet = true;
    }
    return wasSet;
  }
}
