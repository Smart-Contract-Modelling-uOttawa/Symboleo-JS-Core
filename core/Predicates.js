export const Predicates = {
  happens(e) {
    return e.hasHappened()
  },

  happensBefore(e, ts) {
    return e.hasHappened() && e.timestamp < ts
  },

  happensAfter(e, ts) {
    return e.hasHappened() && e.timestamp > ts
  },

  happensWitihn(e, arg1, arg2) {
    if (typeof arg2 === "string" || arg2 instanceof String){
      return Predicates.happensWitihnSituation(e, arg1, arg2)
    } else {
      return Predicates.happensWitihnDate(e, arg1, arg2)
    }
  },
  
  happensWitihnSituation(e, object, state) {
    return e.hasHappened() && object.state === state
  },

  happensWitihnDate(e, start, end) {
    return e.hasHappened() && e.timestamp >= start && e.timestamp <= end
  }
}