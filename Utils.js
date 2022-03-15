const Utils = {
  addTime(argDate, value, unit) {
    const date = new Date(argDate);

    // eslint-disable-next-line default-case
    switch (unit) {
      case 'seconds':
        date.setUTCSeconds(date.getUTCSeconds() + value);
        break;
      case 'minutes':
        date.setUTCMinutes(date.getUTCMinutes() + value);
        break;
      case 'hours':
        date.setUTCHours(date.getUTCHours() + value);
        break;
      case 'days':
        date.setUTCDate(date.getUTCDate() + value);
        break;
      case 'weeks':
        date.setUTCDate(date.getUTCDate() + value * 7);
        break;
      case 'months':
        date.setUTCMonth(date.getUTCMonth() + value);
        break;
      case 'years':
        date.setUTCFullYear(date.getUTCFullYear() + value);
        break;
    }

    return date.toISOString();
  },
};

function escapeRegExp(string) {
  return string.replace(/[.*+?^${}()|[\]\\]/g, '\\$&');
}

const Str = {
  substring(a, start, end) {
    return a.substring(start, end);
  },

  replace(a, pattern, replace) {
    return a.replace(pattern, replace);
  },

  replaceAll(a, find, replace) {
    return a.replace(new RegExp(escapeRegExp(find), 'g'), replace);
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
