

const str2uuid = (strs) => {
    const r = strs.map( (a) =>'uuid(\'' + a + '\')')
  
    return r
  }

//remove timezone info
// const rm_tz = (d) => {

// }

const rmtz = str => str.replace(/[+-]\d\d:\d\d$/, '')

export {str2uuid,rmtz}