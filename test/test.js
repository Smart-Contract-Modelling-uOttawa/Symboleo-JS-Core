// eslint-disable-next-line no-unused-vars
const { expect } = require('chai');
const { Power } = require('../core/Power.js');
const { Obligation } = require('../core/Obligation.js');
const { Role } = require('../core/Role.js');
const { SymboleoContract } = require('../core/SymboleoContract.js');
const { Events } = require('../Events.js');

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
