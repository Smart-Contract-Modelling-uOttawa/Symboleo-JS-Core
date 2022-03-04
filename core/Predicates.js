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
    switch (state) {
      case 'Power.Create':
        return e.hasHappened() && object.isCreate();
      case 'Power.UnsuccessfulTermination':
        return e.hasHappened() && object.isUnsuccessfulTermination();
      case 'Power.Active':
        return e.hasHappened() && object.isActive();
      case 'Power.InEffect':
        return e.hasHappened() && object.isInEffect();
      case 'Power.Suspension':
        return e.hasHappened() && object.isSuspended();
      case 'Power.SuccessfulTermination':
        return e.hasHappened() && object.isSuccessfulTermination();

      case 'Obligation.Create':
        return e.hasHappened() && object.isCreated();
      case 'Obligation.Discharge':
        return e.hasHappened() && object.isDischarged();
      case 'Obligation.Active':
        return e.hasHappened() && object.isActive();
      case 'Obligation.InEffect':
        return e.hasHappened() && object.isInEffect();
      case 'Obligation.Suspension':
        return e.hasHappened() && object.isSuspended();
      case 'Obligation.Violation':
        return e.hasHappened() && object.isViolated();
      case 'Obligation.Fulfillment':
        return e.hasHappened() && object.isFulfilled();
      case 'Obligation.UnsuccessfulTermination':
        return e.hasHappened() && object.isUnsuccessfulTermination();

      case 'Contract.Form':
        return e.hasHappened() && object.isForm();
      case 'Contract.UnAssign':
        return e.hasHappened() && object.isUnassign();
      case 'Contract.InEffect':
        return e.hasHappened() && object.isInEffect();
      case 'Contract.Suspension':
        return e.hasHappened() && object.isSuspended();
      case 'Contract.Rescission':
        return e.hasHappened() && object.isRescission();
      case 'Contract.SuccessfulTermination':
        return e.hasHappened() && object.isSuccessfulTermination();
      case 'Contract.UnsuccessfulTermination':
        return e.hasHappened() && object.isUnsuccessfulTermination();
      case 'Contract.Active':
        return e.hasHappened() && object.isActive();
      default:
        return false;
    }
  },

  happensWithinDate(e, start, end) {
    return e.hasHappened() && e._timestamp >= start && e._timestamp <= end;
  },
};

module.exports.Predicates = Predicates;
