const Utils = {
  addTime(argDate, value, unit) {
    let date;
    if (typeof argDate === 'string' || argDate instanceof String) {
      date = new Date(argDate);
    }

    // eslint-disable-next-line default-case
    switch (unit) {
      case 'seconds':
        date.setSeconds(date.getSeconds() + value);
        break;
      case 'minutes':
        date.setMinutes(date.getMinutes() + value);
        break;
      case 'hours':
        date.setHours(date.getHours() + value);
        break;
      case 'days':
        date.setHours(date.getHours() + value * 24);
        break;
      case 'weeks':
        date.setHours(date.getHours() + value * 24 * 7);
        break;
      case 'years':
        date.setFullYear(date.getFullYear() + value);
        break;
    }

    return date.toISOString();
  },
};

const Str = {
  substring(a, start, end) {
    return a.substring(start, end);
  },

  replaceAll(a, pattern, replace) {
    return a.replaceAll(pattern, replace);
  },

  replace(a, pattern, replace) {
    return a.replace(pattern, replace);
  },

  concat(a, b) {
    return a.concat(b);
  },

  toLowerCase(a) {
    return a.toLowerCase();
  },

  toUpperCase(a) {
    return a.toUpperCase();
  },

  trim(a) {
    return a.trim();
  },

  trimStart(a) {
    return a.trimStart();
  },

  trimEnd(a) {
    return a.trimEnd();
  },
};

module.exports.Utils = Utils;
module.exports.Str = Str;
