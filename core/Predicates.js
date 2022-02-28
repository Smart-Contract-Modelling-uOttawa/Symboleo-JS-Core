const Predicates = {
  happens(e) {
    return e != null && e.hasHappened();
  },

  happensBefore(e, ts) {
    return Predicates.happens(e) && e._timestamp < ts;
  },

  happensAfter(e, ts) {
    return Predicates.happens(e) && e._timestamp > ts;
  },

  happensWithin(e, arg1, arg2) {
    if (typeof arg2 === 'string' || arg2 instanceof String) {
      return Predicates.happensWithinSituation(e, arg1, arg2);
    }
    return Predicates.happensWithinDate(e, arg1, arg2);
  },

  happensWithinSituation(e, object, state) {
    return e.hasHappened() && object.state === state; // TODO should check using methods
  },

  happensWithinDate(e, start, end) {
    return e.hasHappened() && e._timestamp >= start && e._timestamp <= end;
  },
};

module.exports.Predicates = Predicates;
