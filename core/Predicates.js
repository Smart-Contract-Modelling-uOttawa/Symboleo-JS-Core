export const Predicates = {
  happens(e) {
    return e != null && e.hasHappened()
  },

  happensBefore(e, ts) {
    return Predicates.happens(e) && e.timestamp < ts
  },

  happensAfter(e, ts) {
    return Predicates.happens(e) && e.timestamp > ts
  },

  happensWithin(e, arg1, arg2) {
    if (typeof arg2 === "string" || arg2 instanceof String){
      return Predicates.happensWithinSituation(e, arg1, arg2)
    } else {
      return Predicates.happensWithinDate(e, arg1, arg2)
    }
  },
  
  happensWithinSituation(e, object, state) {
    return e.hasHappened() && object.state === state // TODO should check using methods
  },

  happensWithinDate(e, start, end) {
    return e.hasHappened() && e.timestamp >= start && e.timestamp <= end
  }
}