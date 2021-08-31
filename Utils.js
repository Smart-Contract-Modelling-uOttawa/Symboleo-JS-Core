export const Utils = {
  addTime(argDate, value, unit) {
    let date
    if (typeof argDate === "string" || argDate instanceof String) {
      date = new Date(argDate)
    }
    
    switch (unit) {
      case 'seconds':
        date.setSeconds(date.getSeconds() + value)
        break
      case 'minutes':
        date.setMinutes(date.getMinutes() + value)
        break
      case 'hours':
        date.setHours(date.getHours() + value)
        break
      case 'days':
        date.setHours(date.getHours() + value * 24) 
        break
      case 'weeks':
        date.setHours(date.getHours() + value * 24 * 7)
        break
      case 'years':
        date.setFullYear(date.getFullYear() + value)
        break
    }

    return date.toISOString()
  }
}