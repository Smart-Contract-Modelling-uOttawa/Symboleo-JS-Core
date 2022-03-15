/* eslint-disable max-len */
// eslint-disable-next-line no-unused-vars
const { expect } = require('chai');
const { Power } = require('../core/Power.js');
const { InternalEvent, InternalEventSource, InternalEventType } = require('../core/InternalEvents.js');
const { Obligation } = require('../core/Obligation.js');
const { Role } = require('../core/Role.js');
const { SymboleoContract } = require('../core/SymboleoContract.js');
const { Events } = require('../Events.js');
const { Event } = require('../core/Event.js');
const { Str, Utils } = require('../Utils.js');
const { Predicates } = require('../core/Predicates.js');

describe('Power', () => {
  const contract = new SymboleoContract('test_contract');
  const creditor = new Role('creditor', contract);
  const debtor = new Role('debtor', contract);
  const power = new Power('power', creditor, debtor, contract);
  const power2 = new Power('power2', creditor, debtor, contract);
  const power3 = new Power('power3', creditor, debtor, contract);
  const power4 = new Power('power4', creditor, debtor, contract);
  const power5 = new Power('power5', creditor, debtor, contract);
  Events.init([], {});
  contract.activated();

  it('should be on start state after instantiation', () => {
    expect(power.isCreate()).to.equal(false);
    expect(power.isActive()).to.equal(false);
    expect(power.isUnsuccessfulTermination()).to.equal(false);
    expect(power.isSuspended()).to.equal(false);
    expect(power.isInEffect()).to.equal(false);
    expect(power.isSuccessfulTermination()).to.equal(false);
  });

  it('should be on create state after triggering', () => {
    power.trigerredConditional();
    expect(power.isCreate()).to.equal(true);
    expect(power.isActive()).to.equal(false);
    expect(power.isUnsuccessfulTermination()).to.equal(false);
    expect(power.isSuspended()).to.equal(false);
    expect(power.isInEffect()).to.equal(false);
    expect(power.isSuccessfulTermination()).to.equal(false);
  });

  it('should be active', () => {
    power.activated();
    expect(power.isActive()).to.equal(true);
    expect(power.isUnsuccessfulTermination()).to.equal(false);
    expect(power.isSuspended()).to.equal(false);
    expect(power.isCreate()).to.equal(false);
    expect(power.isInEffect()).to.equal(true);
    expect(power.isSuccessfulTermination()).to.equal(false);
  });

  it('should be expired', () => {
    power.expired();
    expect(power.isActive()).to.equal(false);
    expect(power.isUnsuccessfulTermination()).to.equal(true);
    expect(power.isSuspended()).to.equal(false);
    expect(power.isCreate()).to.equal(false);
    expect(power.isInEffect()).to.equal(false);
    expect(power.isSuccessfulTermination()).to.equal(false);
  });

  it('should be expired', () => {
    power2.trigerredConditional();
    power2.expired();
    expect(power2.isActive()).to.equal(false);
    expect(power2.isUnsuccessfulTermination()).to.equal(true);
    expect(power2.isSuspended()).to.equal(false);
    expect(power2.isCreate()).to.equal(false);
    expect(power2.isInEffect()).to.equal(false);
    expect(power2.isSuccessfulTermination()).to.equal(false);
  });

  it('should be suspended', () => {
    power3.trigerredUnconditional();
    power3.suspended();
    expect(power3.isActive()).to.equal(true);
    expect(power3.isUnsuccessfulTermination()).to.equal(false);
    expect(power3.isSuspended()).to.equal(true);
    expect(power3.isCreate()).to.equal(false);
    expect(power3.isInEffect()).to.equal(false);
    expect(power3.isSuccessfulTermination()).to.equal(false);
  });

  it('should be resumed', () => {
    power3.resumed();
    expect(power3.isActive()).to.equal(true);
    expect(power3.isUnsuccessfulTermination()).to.equal(false);
    expect(power3.isSuspended()).to.equal(false);
    expect(power3.isCreate()).to.equal(false);
    expect(power3.isInEffect()).to.equal(true);
    expect(power3.isSuccessfulTermination()).to.equal(false);
  });

  it('should be exerted', () => {
    power3.exerted();
    expect(power3.isActive()).to.equal(false);
    expect(power3.isUnsuccessfulTermination()).to.equal(false);
    expect(power3.isSuspended()).to.equal(false);
    expect(power3.isCreate()).to.equal(false);
    expect(power3.isInEffect()).to.equal(false);
    expect(power3.isSuccessfulTermination()).to.equal(true);
  });

  it('should be terminated', () => {
    power4.trigerredUnconditional();
    power4.terminated();
    expect(power4.isActive()).to.equal(false);
    expect(power4.isUnsuccessfulTermination()).to.equal(true);
    expect(power4.isSuspended()).to.equal(false);
    expect(power4.isCreate()).to.equal(false);
    expect(power4.isInEffect()).to.equal(false);
    expect(power4.isSuccessfulTermination()).to.equal(false);
  });

  it('should be terminated after suspension', () => {
    power5.trigerredUnconditional();
    power5.suspended();
    power5.terminated();
    expect(power5.isActive()).to.equal(false);
    expect(power5.isUnsuccessfulTermination()).to.equal(true);
    expect(power5.isSuspended()).to.equal(false);
    expect(power5.isCreate()).to.equal(false);
    expect(power5.isInEffect()).to.equal(false);
    expect(power5.isSuccessfulTermination()).to.equal(false);
  });

  it('should return false after unsuccessful termination', () => {
    const res = power5.activated();
    expect(res).to.equal(false);

    const res2 = power5.suspended();
    expect(res2).to.equal(false);

    const res3 = power5.exerted();
    expect(res3).to.equal(false);

    const res4 = power5.terminated();
    expect(res4).to.equal(false);

    const res5 = power5.resumed();
    expect(res5).to.equal(false);

    const res6 = power5.expired();
    expect(res6).to.equal(false);

    const res7 = power3.trigerredUnconditional();
    expect(res7).to.equal(false);

    const res8 = power3.trigerredConditional();
    expect(res8).to.equal(false);
  });

  it('should return false after successful termination', () => {
    const res = power3.activated();
    expect(res).to.equal(false);

    const res2 = power3.suspended();
    expect(res2).to.equal(false);

    const res3 = power3.exerted();
    expect(res3).to.equal(false);

    const res4 = power3.terminated();
    expect(res4).to.equal(false);

    const res5 = power3.resumed();
    expect(res5).to.equal(false);

    const res6 = power3.expired();
    expect(res6).to.equal(false);

    const res7 = power3.trigerredUnconditional();
    expect(res7).to.equal(false);

    const res8 = power3.trigerredConditional();
    expect(res8).to.equal(false);
  });
});

describe('Obligation', () => {
  const contract = new SymboleoContract('test_contract');
  const creditor = new Role('creditor', contract);
  const debtor = new Role('debtor', contract);
  const o = new Obligation('obligation', creditor, debtor, contract);
  const o2 = new Obligation('obligation2', creditor, debtor, contract);
  const o3 = new Obligation('obligation3', creditor, debtor, contract);
  const o4 = new Obligation('obligation4', creditor, debtor, contract);
  const o5 = new Obligation('obligation5', creditor, debtor, contract);
  const o6 = new Obligation('obligation5', creditor, debtor, contract);
  Events.init([], {});
  contract.activated();

  it('should be on start state after instantiation', () => {
    expect(o.isCreated()).to.equal(false);
    expect(o.isActive()).to.equal(false);
    expect(o.isUnsuccessfulTermination()).to.equal(false);
    expect(o.isSuspended()).to.equal(false);
    expect(o.isInEffect()).to.equal(false);
    expect(o.isViolated()).to.equal(false);
    expect(o.isFulfilled()).to.equal(false);
    expect(o.isDischarged()).to.equal(false);
  });

  it('should be on create state after triggering', () => {
    o.trigerredConditional();
    expect(o.isCreated()).to.equal(true);
    expect(o.isActive()).to.equal(false);
    expect(o.isUnsuccessfulTermination()).to.equal(false);
    expect(o.isSuspended()).to.equal(false);
    expect(o.isInEffect()).to.equal(false);
    expect(o.isViolated()).to.equal(false);
    expect(o.isFulfilled()).to.equal(false);
    expect(o.isDischarged()).to.equal(false);
  });

  it('should be active', () => {
    o.activated();
    expect(o.isCreated()).to.equal(false);
    expect(o.isActive()).to.equal(true);
    expect(o.isUnsuccessfulTermination()).to.equal(false);
    expect(o.isSuspended()).to.equal(false);
    expect(o.isInEffect()).to.equal(true);
    expect(o.isViolated()).to.equal(false);
    expect(o.isFulfilled()).to.equal(false);
    expect(o.isDischarged()).to.equal(false);
  });

  it('should be discharged', () => {
    o.discharged();
    expect(o.isCreated()).to.equal(false);
    expect(o.isActive()).to.equal(false);
    expect(o.isUnsuccessfulTermination()).to.equal(false);
    expect(o.isSuspended()).to.equal(false);
    expect(o.isInEffect()).to.equal(false);
    expect(o.isViolated()).to.equal(false);
    expect(o.isFulfilled()).to.equal(false);
    expect(o.isDischarged()).to.equal(true);
  });

  it('should be expired', () => {
    o2.trigerredConditional();
    o2.expired();
    expect(o2.isCreated()).to.equal(false);
    expect(o2.isActive()).to.equal(false);
    expect(o2.isUnsuccessfulTermination()).to.equal(false);
    expect(o2.isSuspended()).to.equal(false);
    expect(o2.isInEffect()).to.equal(false);
    expect(o2.isViolated()).to.equal(false);
    expect(o2.isFulfilled()).to.equal(false);
    expect(o2.isDischarged()).to.equal(true);
  });

  it('should be suspended', () => {
    o3.trigerredUnconditional();
    o3.suspended();
    expect(o3.isCreated()).to.equal(false);
    expect(o3.isActive()).to.equal(true);
    expect(o3.isUnsuccessfulTermination()).to.equal(false);
    expect(o3.isSuspended()).to.equal(true);
    expect(o3.isInEffect()).to.equal(false);
    expect(o3.isViolated()).to.equal(false);
    expect(o3.isFulfilled()).to.equal(false);
    expect(o3.isDischarged()).to.equal(false);
  });

  it('should be resumed', () => {
    o3.resumed();
    expect(o3.isCreated()).to.equal(false);
    expect(o3.isActive()).to.equal(true);
    expect(o3.isUnsuccessfulTermination()).to.equal(false);
    expect(o3.isSuspended()).to.equal(false);
    expect(o3.isInEffect()).to.equal(true);
    expect(o3.isViolated()).to.equal(false);
    expect(o3.isFulfilled()).to.equal(false);
    expect(o3.isDischarged()).to.equal(false);
  });

  it('should be fulfilled', () => {
    o3.fulfilled();
    expect(o3.isCreated()).to.equal(false);
    expect(o3.isActive()).to.equal(false);
    expect(o3.isUnsuccessfulTermination()).to.equal(false);
    expect(o3.isSuspended()).to.equal(false);
    expect(o3.isInEffect()).to.equal(false);
    expect(o3.isViolated()).to.equal(false);
    expect(o3.isFulfilled()).to.equal(true);
    expect(o3.isDischarged()).to.equal(false);
  });

  it('should be terminated', () => {
    o4.trigerredUnconditional();
    o4.terminated();
    expect(o4.isCreated()).to.equal(false);
    expect(o4.isActive()).to.equal(false);
    expect(o4.isUnsuccessfulTermination()).to.equal(true);
    expect(o4.isSuspended()).to.equal(false);
    expect(o4.isInEffect()).to.equal(false);
    expect(o4.isViolated()).to.equal(false);
    expect(o4.isFulfilled()).to.equal(false);
    expect(o4.isDischarged()).to.equal(false);
  });

  it('should be violated', () => {
    o5.trigerredUnconditional();
    o5.violated();
    expect(o5.isCreated()).to.equal(false);
    expect(o5.isActive()).to.equal(false);
    expect(o5.isUnsuccessfulTermination()).to.equal(false);
    expect(o5.isSuspended()).to.equal(false);
    expect(o5.isInEffect()).to.equal(false);
    expect(o5.isViolated()).to.equal(true);
    expect(o5.isFulfilled()).to.equal(false);
    expect(o5.isDischarged()).to.equal(false);
  });

  it('should be discharged after resume', () => {
    o6.trigerredUnconditional();
    o6.suspended();
    o6.resumed();
    o6.discharged();
    expect(o6.isCreated()).to.equal(false);
    expect(o6.isActive()).to.equal(false);
    expect(o6.isUnsuccessfulTermination()).to.equal(false);
    expect(o6.isSuspended()).to.equal(false);
    expect(o6.isInEffect()).to.equal(false);
    expect(o6.isViolated()).to.equal(false);
    expect(o6.isFulfilled()).to.equal(false);
    expect(o6.isDischarged()).to.equal(true);
  });

  it('should return false after unsuccessful termination', () => {
    const res = o4.activated();
    expect(res).to.equal(false);

    const res2 = o4.suspended();
    expect(res2).to.equal(false);

    const res3 = o4.fulfilled();
    expect(res3).to.equal(false);

    const res4 = o4.terminated();
    expect(res4).to.equal(false);

    const res5 = o4.resumed();
    expect(res5).to.equal(false);

    const res6 = o4.expired();
    expect(res6).to.equal(false);

    const res67 = o4.discharged();
    expect(res67).to.equal(false);

    const res8 = o4.violated();
    expect(res8).to.equal(false);

    const res9 = o4.trigerredUnconditional();
    expect(res9).to.equal(false);

    const res10 = o4.trigerredConditional();
    expect(res10).to.equal(false);
  });

  it('should return false after successful termination', () => {
    const res = o3.activated();
    expect(res).to.equal(false);

    const res2 = o3.suspended();
    expect(res2).to.equal(false);

    const res3 = o3.fulfilled();
    expect(res3).to.equal(false);

    const res4 = o3.terminated();
    expect(res4).to.equal(false);

    const res5 = o3.resumed();
    expect(res5).to.equal(false);

    const res6 = o3.expired();
    expect(res6).to.equal(false);

    const res67 = o3.discharged();
    expect(res67).to.equal(false);

    const res8 = o3.violated();
    expect(res8).to.equal(false);

    const res9 = o3.trigerredUnconditional();
    expect(res9).to.equal(false);

    const res10 = o3.trigerredConditional();
    expect(res10).to.equal(false);
  });

  it('should return false after violation', () => {
    const res = o5.activated();
    expect(res).to.equal(false);

    const res2 = o5.suspended();
    expect(res2).to.equal(false);

    const res3 = o5.fulfilled();
    expect(res3).to.equal(false);

    const res4 = o5.terminated();
    expect(res4).to.equal(false);

    const res5 = o5.resumed();
    expect(res5).to.equal(false);

    const res6 = o5.expired();
    expect(res6).to.equal(false);

    const res67 = o5.discharged();
    expect(res67).to.equal(false);

    const res8 = o5.violated();
    expect(res8).to.equal(false);

    const res9 = o5.trigerredUnconditional();
    expect(res9).to.equal(false);

    const res10 = o5.trigerredConditional();
    expect(res10).to.equal(false);
  });

  it('should return false after discharge', () => {
    const res = o.activated();
    expect(res).to.equal(false);

    const res2 = o.suspended();
    expect(res2).to.equal(false);

    const res3 = o.fulfilled();
    expect(res3).to.equal(false);

    const res4 = o.terminated();
    expect(res4).to.equal(false);

    const res5 = o5.resumed();
    expect(res5).to.equal(false);

    const res6 = o.expired();
    expect(res6).to.equal(false);

    const res67 = o.discharged();
    expect(res67).to.equal(false);

    const res8 = o.violated();
    expect(res8).to.equal(false);

    const res9 = o.trigerredUnconditional();
    expect(res9).to.equal(false);

    const res10 = o.trigerredConditional();
    expect(res10).to.equal(false);
  });
});

describe('Contract', () => {
  const c = new SymboleoContract('test_contract1');
  const c2 = new SymboleoContract('test_contract2');
  const c3 = new SymboleoContract('test_contract3');
  const c4 = new SymboleoContract('test_contract4');
  Events.init([], {});

  it('should be on form state after instantiation', () => {
    expect(c.isInEffect()).to.equal(false);
    expect(c.isActive()).to.equal(false);
    expect(c.isForm()).to.equal(true);
    expect(c.isUnassign()).to.equal(false);
    expect(c.isRescission()).to.equal(false);
    expect(c.isSuspended()).to.equal(false);
    expect(c.isUnsuccessfulTermination()).to.equal(false);
    expect(c.isSuccessfulTermination()).to.equal(false);
  });

  it('should be on active state after activation', () => {
    c.activated();
    expect(c.isInEffect()).to.equal(true);
    expect(c.isActive()).to.equal(true);
    expect(c.isForm()).to.equal(false);
    expect(c.isUnassign()).to.equal(false);
    expect(c.isRescission()).to.equal(false);
    expect(c.isSuspended()).to.equal(false);
    expect(c.isUnsuccessfulTermination()).to.equal(false);
    expect(c.isSuccessfulTermination()).to.equal(false);
  });

  it('should be on suspended state after suspension', () => {
    c.suspended();
    expect(c.isInEffect()).to.equal(false);
    expect(c.isActive()).to.equal(true);
    expect(c.isForm()).to.equal(false);
    expect(c.isUnassign()).to.equal(false);
    expect(c.isRescission()).to.equal(false);
    expect(c.isSuspended()).to.equal(true);
    expect(c.isUnsuccessfulTermination()).to.equal(false);
    expect(c.isSuccessfulTermination()).to.equal(false);
  });

  it('should be on active state after resumption', () => {
    c.resumed();
    expect(c.isInEffect()).to.equal(true);
    expect(c.isActive()).to.equal(true);
    expect(c.isForm()).to.equal(false);
    expect(c.isUnassign()).to.equal(false);
    expect(c.isRescission()).to.equal(false);
    expect(c.isSuspended()).to.equal(false);
    expect(c.isUnsuccessfulTermination()).to.equal(false);
    expect(c.isSuccessfulTermination()).to.equal(false);
  });

  it('should be on successfulTermination state', () => {
    c.fulfilledActiveObligations();
    expect(c.isInEffect()).to.equal(false);
    expect(c.isActive()).to.equal(false);
    expect(c.isForm()).to.equal(false);
    expect(c.isUnassign()).to.equal(false);
    expect(c.isRescission()).to.equal(false);
    expect(c.isSuspended()).to.equal(false);
    expect(c.isUnsuccessfulTermination()).to.equal(false);
    expect(c.isSuccessfulTermination()).to.equal(true);
  });

  it('should be on rescission state', () => {
    c2.activated();
    c2.rescinded();
    expect(c2.isInEffect()).to.equal(false);
    expect(c2.isActive()).to.equal(true);
    expect(c2.isForm()).to.equal(false);
    expect(c2.isUnassign()).to.equal(false);
    expect(c2.isRescission()).to.equal(true);
    expect(c2.isSuspended()).to.equal(false);
    expect(c2.isUnsuccessfulTermination()).to.equal(false);
    expect(c2.isSuccessfulTermination()).to.equal(false);
  });

  it('should terminate from InEffect state', () => {
    c3.activated();
    c3.terminated();
    expect(c3.isInEffect()).to.equal(false);
    expect(c3.isActive()).to.equal(false);
    expect(c3.isForm()).to.equal(false);
    expect(c3.isUnassign()).to.equal(false);
    expect(c3.isRescission()).to.equal(false);
    expect(c3.isSuspended()).to.equal(false);
    expect(c3.isUnsuccessfulTermination()).to.equal(true);
    expect(c3.isSuccessfulTermination()).to.equal(false);
  });

  it('should terminate from Suspension state', () => {
    c4.activated();
    c4.suspended();
    c4.terminated();
    expect(c4.isInEffect()).to.equal(false);
    expect(c4.isActive()).to.equal(false);
    expect(c4.isForm()).to.equal(false);
    expect(c4.isUnassign()).to.equal(false);
    expect(c4.isRescission()).to.equal(false);
    expect(c4.isSuspended()).to.equal(false);
    expect(c4.isUnsuccessfulTermination()).to.equal(true);
    expect(c4.isSuccessfulTermination()).to.equal(false);
  });

  it('should not work after termination', () => {
    const res = c4.suspended();
    expect(res).to.equal(false);

    const res2 = c4.activated();
    expect(res2).to.equal(false);

    const res3 = c4.terminated();
    expect(res3).to.equal(false);

    const res4 = c4.rescinded();
    expect(res4).to.equal(false);

    const res5 = c4.fulfilledActiveObligations();
    expect(res5).to.equal(false);

    const res6 = c4.resumed();
    expect(res6).to.equal(false);
  });

  it('should not work after successfull termination', () => {
    const res = c.suspended();
    expect(res).to.equal(false);

    const res2 = c.activated();
    expect(res2).to.equal(false);

    const res3 = c.terminated();
    expect(res3).to.equal(false);

    const res4 = c.rescinded();
    expect(res4).to.equal(false);

    const res5 = c.fulfilledActiveObligations();
    expect(res5).to.equal(false);

    const res6 = c.resumed();
    expect(res6).to.equal(false);
  });
});

describe('Str', () => {
  it('should substring correctly', () => {
    expect(Str.substring('testtest', 1, 4)).to.equal('est');
  });

  it('should replace correctly', () => {
    expect(Str.replace('test_string_string', 'string', 'aidin')).to.equal('test_aidin_string');
  });

  it('should replaceAll correctly', () => {
    expect(Str.replaceAll('string_test_string', 'string', 'aidin')).to.equal('aidin_test_aidin');
  });

  it('should concat correctly', () => {
    expect(Str.concat('string', '_test')).to.equal('string_test');
  });

  it('should toLowerCase correctly', () => {
    expect(Str.toLowerCase('sTrINg')).to.equal('string');
  });

  it('should toUpperCase correctly', () => {
    expect(Str.toUpperCase('sTrINg')).to.equal('STRING');
  });

  it('should trim correctly', () => {
    expect(Str.trim('\tstring ')).to.equal('string');
  });

  it('should trimStart correctly', () => {
    expect(Str.trimStart('\tstring ')).to.equal('string ');
  });

  it('should trimEnd correctly', () => {
    expect(Str.trimEnd('\tstring ')).to.equal('\tstring');
  });
});

describe('Utils.addTime', () => {
  const d = new Date('2022-03-12T22:39:00.000Z');

  it('should add years correctly', () => {
    expect(Utils.addTime(d, 2, 'years')).to.equal('2024-03-12T22:39:00.000Z');
  });

  it('should add months correctly', () => {
    expect(Utils.addTime(d, 2, 'months')).to.equal('2022-05-12T22:39:00.000Z');
  });

  it('should add weeks correctly', () => {
    expect(Utils.addTime(d, 2, 'weeks')).to.equal('2022-03-26T22:39:00.000Z');
  });

  it('should add days correctly', () => {
    expect(Utils.addTime(d, 4, 'days')).to.equal('2022-03-16T22:39:00.000Z');
  });

  it('should add hours correctly', () => {
    expect(Utils.addTime(d, 6, 'hours')).to.equal('2022-03-13T04:39:00.000Z');
  });

  it('should add minutes correctly', () => {
    expect(Utils.addTime(d, 75, 'minutes')).to.equal('2022-03-12T23:54:00.000Z');
  });

  it('should add seconds correctly', () => {
    expect(Utils.addTime(d, 4, 'seconds')).to.equal('2022-03-12T22:39:04.000Z');
  });

  it('should work with string input', () => {
    expect(Utils.addTime('2022-03-12T22:39:00.000Z', 2, 'years')).to.equal('2024-03-12T22:39:00.000Z');
    expect(Utils.addTime('2022-03-12T22:39:00.000Z', 2, 'months')).to.equal('2022-05-12T22:39:00.000Z');
    expect(Utils.addTime('2022-03-12T22:39:00.000Z', 2, 'weeks')).to.equal('2022-03-26T22:39:00.000Z');
    expect(Utils.addTime('2022-03-12T22:39:00.000Z', 4, 'days')).to.equal('2022-03-16T22:39:00.000Z');
    expect(Utils.addTime('2022-03-12T22:39:00.000Z', 6, 'hours')).to.equal('2022-03-13T04:39:00.000Z');
    expect(Utils.addTime('2022-03-12T22:39:00.000Z', 75, 'minutes')).to.equal('2022-03-12T23:54:00.000Z');
    expect(Utils.addTime('2022-03-12T22:39:00.000Z', 4, 'seconds')).to.equal('2022-03-12T22:39:04.000Z');
  });
});

describe('Predicates', () => {
  const d = new Date('2022-03-12T22:39:00.000Z');

  const contract = new SymboleoContract('test_contract');
  const contract2 = new SymboleoContract('test_contract2');
  const contract3 = new SymboleoContract('test_contract3');
  const creditor = new Role('creditor', contract);
  const debtor = new Role('debtor', contract);
  const power = new Power('power', creditor, debtor, contract);
  const power2 = new Power('power2', creditor, debtor, contract);
  const o = new Obligation('obligation', creditor, debtor, contract);
  const o2 = new Obligation('obligation2', creditor, debtor, contract);
  const o3 = new Obligation('obligation3', creditor, debtor, contract);
  const o4 = new Obligation('obligation4', creditor, debtor, contract);
  Events.init([], {});

  it('should happen event', () => {
    const e1 = new Event();
    e1.happen();
    expect(Predicates.happens(e1)).to.equal(true);
  });

  it('should happen before timestamp', () => {
    const e1 = new Event();
    e1.happen();
    e1._timestamp = d.toISOString();
    const d2 = new Date(d);
    d2.setUTCMinutes(d2.getUTCMinutes() + 5);
    expect(Predicates.happensBefore(e1, d2.toISOString())).to.equal(true);
  });

  it('should happen after timestamp', () => {
    const e1 = new Event();
    e1.happen();
    e1._timestamp = d.toISOString();
    const d2 = new Date(d);
    d2.setUTCMinutes(d2.getUTCMinutes() - 5);
    expect(Predicates.happensAfter(e1, d2.toISOString())).to.equal(true);
  });

  it('should happen within timestamps', () => {
    const e1 = new Event();
    e1.happen();
    e1._timestamp = d.toISOString();
    const d2 = new Date(d);
    const d3 = new Date(d);
    d2.setUTCMinutes(d2.getUTCMinutes() - 5);
    d3.setUTCMinutes(d3.getUTCMinutes() + 5);
    expect(Predicates.happensAfter(e1, d2.toISOString(), d3.toISOString())).to.equal(true);
  });

  it('should happen within situation', () => {
    const e1 = new Event();
    e1.happen();

    expect(Predicates.happensWithin(e1, contract, 'Contract.Form')).to.equal(true);
    contract.activated();
    expect(Predicates.happensWithin(e1, contract, 'Contract.Active')).to.equal(true);
    expect(Predicates.happensWithin(e1, contract, 'Contract.InEffect')).to.equal(true);

    power.trigerredConditional();
    expect(Predicates.happensWithin(e1, power, 'Power.Create')).to.equal(true);
    power.activated();
    expect(Predicates.happensWithin(e1, power, 'Power.Active')).to.equal(true);
    expect(Predicates.happensWithin(e1, power, 'Power.InEffect')).to.equal(true);
    power.suspended();
    expect(Predicates.happensWithin(e1, power, 'Power.Suspension')).to.equal(true);
    power.resumed();
    power.exerted();
    expect(Predicates.happensWithin(e1, power, 'Power.SuccessfulTermination')).to.equal(true);
    power2.trigerredUnconditional();
    power2.terminated();
    expect(Predicates.happensWithin(e1, power2, 'Power.UnsuccessfulTermination')).to.equal(true);

    o.trigerredConditional();
    expect(Predicates.happensWithin(e1, o, 'Obligation.Create')).to.equal(true);
    o.activated();
    expect(Predicates.happensWithin(e1, o, 'Obligation.Active')).to.equal(true);
    expect(Predicates.happensWithin(e1, o, 'Obligation.InEffect')).to.equal(true);
    o.suspended();
    expect(Predicates.happensWithin(e1, o, 'Obligation.Suspension')).to.equal(true);
    o.resumed();
    o.fulfilled();
    expect(Predicates.happensWithin(e1, o, 'Obligation.Fulfillment')).to.equal(true);
    o2.trigerredUnconditional();
    o2.discharged();
    expect(Predicates.happensWithin(e1, o2, 'Obligation.Discharge')).to.equal(true);
    o3.trigerredUnconditional();
    o3.violated();
    expect(Predicates.happensWithin(e1, o3, 'Obligation.Violation')).to.equal(true);
    o4.trigerredUnconditional();
    o4.terminated();
    expect(Predicates.happensWithin(e1, o4, 'Obligation.UnsuccessfulTermination')).to.equal(true);

    contract.suspended();
    expect(Predicates.happensWithin(e1, contract, 'Contract.Suspension')).to.equal(true);
    contract.resumed();
    contract.fulfilledActiveObligations();
    expect(Predicates.happensWithin(e1, contract, 'Contract.SuccessfulTermination')).to.equal(true);
    contract2.activated();
    contract2.rescinded();
    expect(Predicates.happensWithin(e1, contract2, 'Contract.Rescission')).to.equal(true);
    contract3.activated();
    contract3.terminated();
    expect(Predicates.happensWithin(e1, contract3, 'Contract.UnsuccessfulTermination')).to.equal(true);
  });
});

describe('Events', () => {
  const contract = new SymboleoContract('test_contract');
  const contract2 = new SymboleoContract('test_contract2');
  const contract3 = new SymboleoContract('test_contract3');
  const creditor = new Role('creditor', contract);
  const debtor = new Role('debtor', contract);
  const p = new Power('power', creditor, debtor, contract);
  const p2 = new Power('power2', creditor, debtor, contract);
  const p3 = new Power('power3', creditor, debtor, contract);
  const o = new Obligation('obligation', creditor, debtor, contract);
  const o2 = new Obligation('obligation2', creditor, debtor, contract);
  const o3 = new Obligation('obligation3', creditor, debtor, contract);
  const o4 = new Obligation('obligation4', creditor, debtor, contract);
  const o5 = new Obligation('obligation5', creditor, debtor, contract);
  const e1 = new Event();
  const e2 = new Event();
  e1._name = 'e1';
  e2._name = 'e2';
  const listenerCalled = {};
  Events.init([
    [[new InternalEvent(InternalEventSource.contract, InternalEventType.contract.Activated, contract)], () => { listenerCalled.a = true; }],
    [[new InternalEvent(InternalEventSource.contract, InternalEventType.contract.Suspended, contract)], () => { listenerCalled.b = true; }],
    [[new InternalEvent(InternalEventSource.contract, InternalEventType.contract.Resumed, contract)], () => { listenerCalled.c = true; }],

    [[new InternalEvent(InternalEventSource.power, InternalEventType.power.Triggered, p)], () => { listenerCalled.d = true; }],
    [[new InternalEvent(InternalEventSource.power, InternalEventType.power.Activated, p)], () => { listenerCalled.e = true; }],
    [[new InternalEvent(InternalEventSource.power, InternalEventType.power.Suspended, p)], () => { listenerCalled.f = true; }],
    [[new InternalEvent(InternalEventSource.power, InternalEventType.power.Resumed, p)], () => { listenerCalled.g = true; }],
    [[new InternalEvent(InternalEventSource.power, InternalEventType.power.Exerted, p)], () => { listenerCalled.h = true; }],
    [[new InternalEvent(InternalEventSource.power, InternalEventType.power.Terminated, p2)], () => { listenerCalled.i = true; }],
    [[new InternalEvent(InternalEventSource.power, InternalEventType.power.Expired, p3)], () => { listenerCalled.j = true; }],

    [[new InternalEvent(InternalEventSource.obligation, InternalEventType.obligation.Triggered, o)], () => { listenerCalled.k = true; }],
    [[new InternalEvent(InternalEventSource.obligation, InternalEventType.obligation.Activated, o)], () => { listenerCalled.l = true; }],
    [[new InternalEvent(InternalEventSource.obligation, InternalEventType.obligation.Suspended, o)], () => { listenerCalled.m = true; }],
    [[new InternalEvent(InternalEventSource.obligation, InternalEventType.obligation.Resumed, o)], () => { listenerCalled.n = true; }],
    [[new InternalEvent(InternalEventSource.obligation, InternalEventType.obligation.Fulfilled, o)], () => { listenerCalled.o = true; }],
    [[new InternalEvent(InternalEventSource.obligation, InternalEventType.obligation.Discharged, o2)], () => { listenerCalled.p = true; }],
    [[new InternalEvent(InternalEventSource.obligation, InternalEventType.obligation.Violated, o3)], () => { listenerCalled.q = true; }],
    [[new InternalEvent(InternalEventSource.obligation, InternalEventType.obligation.Terminated, o4)], () => { listenerCalled.r = true; }],
    [[new InternalEvent(InternalEventSource.obligation, InternalEventType.obligation.Expired, o5)], () => { listenerCalled.s = true; }],

    [[new InternalEvent(InternalEventSource.contract, InternalEventType.contract.FulfilledObligations, contract)], () => { listenerCalled.t = true; }],
    [[new InternalEvent(InternalEventSource.contract, InternalEventType.contract.Rescinded, contract2)], () => { listenerCalled.u = true; }],
    [[new InternalEvent(InternalEventSource.contract, InternalEventType.contract.Terminated, contract3)], () => { listenerCalled.v = true; }],

    [[new InternalEvent(InternalEventSource.contractEvent, InternalEventType.contractEvent.Happened, e1)], () => { listenerCalled.w = true; }],
    [[new InternalEvent(InternalEventSource.contractEvent, InternalEventType.contractEvent.Happened, e2)], () => { listenerCalled.x = true; }],
  ], {});

  it('should trigger events', () => {
    contract.activated();
    expect(listenerCalled.a).to.equal(true);
    expect(contract._events.Activated.hasHappened()).to.equal(true);
    contract.suspended();
    expect(listenerCalled.b).to.equal(true);
    expect(contract._events.Suspended.hasHappened()).to.equal(true);
    contract.resumed();
    expect(listenerCalled.c).to.equal(true);
    expect(contract._events.Resumed.hasHappened()).to.equal(true);

    p.trigerredConditional();
    expect(listenerCalled.d).to.equal(true);
    expect(p._events.Triggered.hasHappened()).to.equal(true);
    p.activated();
    expect(listenerCalled.e).to.equal(true);
    expect(p._events.Activated.hasHappened()).to.equal(true);
    p.suspended();
    expect(listenerCalled.f).to.equal(true);
    expect(p._events.Suspended.hasHappened()).to.equal(true);
    p.resumed();
    expect(listenerCalled.g).to.equal(true);
    expect(p._events.Resumed.hasHappened()).to.equal(true);
    p.exerted();
    expect(listenerCalled.h).to.equal(true);
    expect(p._events.Exerted.hasHappened()).to.equal(true);
    p2.trigerredUnconditional();
    p2.terminated();
    expect(listenerCalled.i).to.equal(true);
    expect(p2._events.Terminated.hasHappened()).to.equal(true);
    p3.trigerredUnconditional();
    p3.expired();
    expect(listenerCalled.j).to.equal(true);
    expect(p3._events.Expired.hasHappened()).to.equal(true);

    o.trigerredConditional();
    expect(listenerCalled.k).to.equal(true);
    expect(o._events.Triggered.hasHappened()).to.equal(true);
    o.activated();
    expect(listenerCalled.l).to.equal(true);
    expect(o._events.Activated.hasHappened()).to.equal(true);
    o.suspended();
    expect(listenerCalled.m).to.equal(true);
    expect(o._events.Suspended.hasHappened()).to.equal(true);
    o.resumed();
    expect(listenerCalled.n).to.equal(true);
    expect(o._events.Resumed.hasHappened()).to.equal(true);
    o.fulfilled();
    expect(listenerCalled.o).to.equal(true);
    expect(o._events.Fulfilled.hasHappened()).to.equal(true);
    o2.trigerredUnconditional();
    o2.discharged();
    expect(listenerCalled.p).to.equal(true);
    expect(o2._events.Discharged.hasHappened()).to.equal(true);
    o3.trigerredUnconditional();
    o3.violated();
    expect(listenerCalled.q).to.equal(true);
    expect(o3._events.Violated.hasHappened()).to.equal(true);
    o4.trigerredUnconditional();
    o4.terminated();
    expect(listenerCalled.r).to.equal(true);
    expect(o4._events.Terminated.hasHappened()).to.equal(true);
    o5.trigerredConditional();
    o5.expired();
    expect(listenerCalled.s).to.equal(true);
    expect(o5._events.Expired.hasHappened()).to.equal(true);

    contract.fulfilledActiveObligations();
    expect(listenerCalled.t).to.equal(true);
    expect(contract._events.FulfilledObligations.hasHappened()).to.equal(true);
    contract2.activated();
    contract2.rescinded();
    expect(listenerCalled.u).to.equal(true);
    expect(contract2._events.Rescinded.hasHappened()).to.equal(true);
    contract3.activated();
    contract3.terminated();
    expect(listenerCalled.v).to.equal(true);
    expect(contract3._events.Terminated.hasHappened()).to.equal(true);

    e1.happen();
    Events.emitEvent(contract, new InternalEvent(InternalEventSource.contractEvent, InternalEventType.contractEvent.Happened, e1));
    expect(e1.hasHappened()).to.equal(true);
    expect(listenerCalled.w).to.equal(true);
    expect(e2.hasHappened()).to.equal(false);
    expect(listenerCalled.x).to.equal(undefined);
    e2.happen();
    Events.emitEvent(contract, new InternalEvent(InternalEventSource.contractEvent, InternalEventType.contractEvent.Happened, e2));
    expect(e2.hasHappened()).to.equal(true);
    expect(listenerCalled.x).to.equal(true);
  });
});
