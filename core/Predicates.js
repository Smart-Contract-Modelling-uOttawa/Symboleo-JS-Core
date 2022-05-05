const Predicates = {
  happens(e) {
    return e == null ? false : e.hasHappened();
  },

  weakHappensBefore(e, ts) {
    if (e == null) {
      return false;
    }
    if (Predicates.happens(e) && ts == null) {
      return true;
    }
    return Predicates.happens(e) && e._timestamp <= ts;
  },

  strongHappensBefore(e, ts) {
    if (e == null || ts == null) {
      return false;
    }
    return Predicates.happens(e) && e._timestamp <= ts;
  },

  happensWithin(e, arg1, arg2) {
    if (e == null) {
      return false;
    }
    if ((typeof arg2 === 'string' || arg2 instanceof String)
      && (arg2.indexOf('Power.') !== -1 || arg2.indexOf('Obligation.') !== -1
      || arg2.indexOf('Contract.') !== -1)) {
      return Predicates.happensWithinSituation(e, arg1, arg2);
    }
    return Predicates.happensWithinInterval(e, arg1, arg2);
  },

  happensWithinSituation(e, object, state) {
    if (object == null) {
      return false;
    }
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

  happensWithinInterval(e, start, end) {
    if (start == null) {
      return false;
    }
    if (end == null) {
      return e.hasHappened() && e._timestamp >= start;
    }
    return e.hasHappened() && e._timestamp >= start && e._timestamp <= end;
  },
};

module.exports.Predicates = Predicates;
